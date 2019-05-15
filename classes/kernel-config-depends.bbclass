#
# Kernel configuration dependencies
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#
# Example:
#
#   KERNEL_CONFIG_DEPENDS += "{\
#       option              = CONFIG_BRIDGE, \
#       required            = y|m, \
#       version             = '> 4.14, < 4.16', \
#       version             = '> 4.17', \
#       m_rdepends          = kernel-module-bridge, \
#       m_autoload_script   = bridge, \
#       m_autoload_priority = 30, \
#       m_autoload_early    = false, \
#       m_autoload          = bridge \
#   }"
#
inherit kernel-config

def get_op_ver(s, d):
    i = s.replace(" ", "")
    op  = ""
    ver = ""

    if i.startswith(('<=', '=<', '<<', '==', '!=', '>=', '=>', '>>')):
        op  = i[0:2]
        ver = i[2:]
    elif i.startswith(('<', '>')):
        op  = i[0:1]
        ver = i[1:]
    else:
        raise Exception('Invalid version specification "%s"' % i)

    return (op, ver)

def kernel_config_depends(d):
    import re

    bb.debug(1, 'Check kernel configuration dependencies...');

    kversion = kernel_get_version(d)

    kdep_var  = d.getVar('KERNEL_CONFIG_DEPENDS', True) or ""
    kdep_opts = re.findall("\{(.*?)\}", kdep_var)

    if not kdep_opts:
        bb.debug(1, 'No options defined in KERNEL_CONFIG_DEPENDS');
        return

    for kdep in kdep_opts:

        ver_passed  = False
        ver_checked = False

        if not kdep:
            continue

        pattern = re.compile(r"((?:[^,\"']|\"[^\"]*\"|'[^']*')+)")
        values  = pattern.split(kdep)[1::2]

        if not len(values):
            continue

        pkg = d.expand("${PN}")

        koption = ""
        required = []
        version = ""
        m_rdepends = []
        m_autoload_priority = -1
        m_autoload_script = pkg
        m_autoload_early = False
        m_autoload = []

        pattern = re.compile(r"((?:[^=\"']|\"[^\"]*\"|'[^']*')+)")

        # Read parameters
        for value in values:
            v = pattern.split(value)[1::2]

            if len(v) < 2:
                bb.warn('Invalid parameter "%s" in KERNEL_CONFIG_DEPENDS' % value)
                continue

            v[0] = re.sub("[\"']", "", v[0]).strip()
            v[1] = re.sub("[\"']", "", v[1]).strip()

            if v[0] == 'option':
                koption = v[1]
            elif v[0] == 'required':
                req = v[1].split("|")
                for r in req:
                    required.append(r.strip())
            elif v[0] == 'version':
                comparsions = v[1].split(",")
                if len(comparsions):
                    passed = True
                    for c in comparsions:
                        (op, ver) = get_op_ver(c, d)
                        result = bb.utils.vercmp_string_op(kversion, ver, op)
                        ver_checked = True
                        bb.debug(1, '  Kernel version check: %s %s %s: %s' % (kversion, op, ver, str(result)));
                        if not result:
                            passed = False
                    if passed:
                        ver_passed = True
            elif v[0] == 'm_rdepends':
                m_rdepends.append(v[1])
            elif v[0] == 'm_autoload_script':
                m_autoload_script = v[1]
            elif v[0] == 'm_autoload_early':
                m_autoload_early = v[1].lower() in ("on", "yes", "true", "1")
            elif v[0] == 'm_autoload_priority':
                m_autoload_priority = int(v[1])
                if m_autoload_priority < 0 or m_autoload_priority > 99:
                    bb.warn('KERNEL_CONFIG_DEPENDS has invalid m_autoload_priority = %d' % (m_autoload_priority))
                    m_autoload_priority = -1
            elif v[0] == 'm_autoload':
                m_autoload.append(v[1])
            else:
                bb.warn('KERNEL_CONFIG_DEPENDS has unknown parameter "%s"' % (v[0]))

        if not koption:
            continue

        if ver_checked and not ver_passed:
            bb.debug(1, '  Kernel option %s skipped due kernel version check' % (koption));
            continue

        kvalue = kernel_get_config('%s' % koption, d)

        if m_autoload_priority >= 0:
            m_autoload_script = "%02d-%s" \
                % (m_autoload_priority, m_autoload_script)

        bb.debug(1, '  Kernel option %s = %s' % (koption, kvalue));

        bb.debug(1, "    Readed parameters for kernel option %s:" % koption)
        bb.debug(1, "      required            = %s" % (" or ".join(required)))
        bb.debug(1, "      m_rdepends          = %s" % (", ".join(m_rdepends)))
        bb.debug(1, "      m_autoload_script   = %s" % (m_autoload_script))
        bb.debug(1, "      m_autoload_priority = %s" % (m_autoload_priority))
        bb.debug(1, "      m_autoload_early    = %d" % (m_autoload_early))
        bb.debug(1, "      m_autoload          = %s" % (", ".join(m_autoload)))

        if kvalue not in required:
            bb.warn('It is recommended to set the kernel option %s to %s' \
                % (koption, " or ".join(required)));
            continue

        if kvalue == 'm':
            # add runtime dependencies
            if len(m_rdepends) > 0:
                bb.debug(1, '    Added runtime dependency on "%s" package(s)' \
                    % (", ".join(m_rdepends)))
                d.prependVar('RDEPENDS_%s' % pkg, "%s " \
                    % (" ".join(m_rdepends)))

            # autoload modules
            if len(m_autoload) > 0:

                postinst = d.getVar('pkg_postinst_%s' % pkg, True) or ""

                postinst += "#!/bin/sh\n"
                postinst += "mkdir -p $D${sysconfdir}/modules.d\n"

                for module in m_autoload:
                    postinst += "echo \"%s\" >> $D${sysconfdir}/modules.d/%s\n" \
                        % (module, m_autoload_script)

                if m_autoload_early:
                    postinst += "mkdir -p $D${sysconfdir}/modules-boot.d\n"
                    postinst += "ln -sf ../modules.d/%s $D${sysconfdir}/modules-boot.d/\n" % \
                        (m_autoload_script)

                d.setVar('pkg_postinst_%s' % pkg, postinst)

                bb.debug(1, '    Added script "%s" for "%s" modules autoloading' % \
                    (m_autoload_script, "early" if m_autoload_early else "normal"))

python populate_packages_prepend() {
    kernel_config_depends(d)
}

# Wait for kernel modules ready
do_package[depends] += "virtual/kernel:do_package_write_ipk"
do_package[vardeps] += "KERNEL_CONFIG_DEPENDS"

#
# OpenWrt services control class
#
# Copyright (c) 2018, 2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

REQUIRED_DISTRO_FEATURES_class-target = "procd"
CONFLICT_DISTRO_FEATURES = "sysvinit systemd"

inherit distro_features_check

RDEPENDS_${PN}_append_class-target = " procd "

python __anonymous() {
    # Check for deprecated OPENWRT_* variables
    owrt_packages = d.getVar('OPENWRT_SERVICE_PACKAGES', True)
    if owrt_packages:
        warn_scripts = False
        warn_states  = False
        bb.warn(("OPENWRT_SERVICE_PACKAGES is deprecated, "
                 "please use TANOWRT_SERVICE_PACKAGES instead"))
        d.setVar('TANOWRT_SERVICE_PACKAGES', owrt_packages)

        for pkg in owrt_packages.split():
            owrt_scripts = d.getVar('OPENWRT_SERVICE_SCRIPTS_%s' % pkg, True)
            if owrt_scripts:
                if not warn_scripts:
                    warn_scripts = True
                    bb.warn(("OPENWRT_SERVICE_SCRIPTS is deprecated, "
                             "please use TANOWRT_SERVICE_SCRIPTS instead"))

                d.setVar('TANOWRT_SERVICE_SCRIPTS_%s' % pkg, owrt_scripts)

                for script in owrt_scripts.split():
                    state = d.getVar('OPENWRT_SERVICE_STATE_%s-%s' % (pkg, script), True)
                    if state:
                        if not warn_states:
                            warn_states = True
                            bb.warn(("OPENWRT_SERVICE_STATE is deprecated, "
                                     "please use TANOWRT_SERVICE_STATE instead"))

                        d.setVar('TANOWRT_SERVICE_STATE_%s-%s' % (pkg, script), state)

    if not bb.data.inherits_class('nativesdk', d) \
        and not bb.data.inherits_class('native', d):
        update_owrt_after_parse(d)
}

def get_vardeps(d):
    vars = []

    vars.extend(['TANOWRT_SERVICE_PACKAGES'])
    owrt_packages = d.getVar('TANOWRT_SERVICE_PACKAGES', True)

    if owrt_packages:
        for pkg in owrt_packages.split():
            owrt_scripts = d.getVar('TANOWRT_SERVICE_SCRIPTS_%s' % pkg, True) or ""
            vars.extend(['TANOWRT_SERVICE_SCRIPTS_%s' % pkg])

            if owrt_scripts:
                for script in owrt_scripts.split():
                    vars.extend(['TANOWRT_SERVICE_STATE_%s-%s' % (pkg, script)])

    return " ".join(vars)

do_package[vardeps] += "${@get_vardeps(d)}"

# Recipe parse-time sanity checks
def update_owrt_after_parse(d):
    owrt_packages = d.getVar('TANOWRT_SERVICE_PACKAGES', True)

    if not owrt_packages:
        return

    for pkg in owrt_packages.split():
        if not d.getVar('TANOWRT_SERVICE_SCRIPTS_%s' % pkg, True):
            bb.fatal("%s inherits tanowrt-services but doesn't set TANOWRT_SERVICE_SCRIPTS for package '%s'" % (d.getVar('FILE', True), pkg))

python populate_packages_prepend() {
    import subprocess
    import string
    import os

    D             = d.getVar('D', True)
    sysconfdir    = d.getVar('sysconfdir', True)
    owrt_packages = d.getVar('TANOWRT_SERVICE_PACKAGES', True)
    packages      = d.getVar('PACKAGES', True)

    if owrt_packages:
        bb.debug(1, "TanoWrt: Recipe '%s' packages: %s" % (d.expand('${PN}'), owrt_packages))

        for pkg in owrt_packages.split():

            if not pkg in packages:
                bb.warn("TanoWrt: [%s] Package is not listed in PACKAGES [skipping]" % pkg)
                continue

            owrt_scripts = d.getVar('TANOWRT_SERVICE_SCRIPTS_%s' % pkg, True) or ""
            scripts = owrt_scripts.split()

            if len(scripts) == 0:
                bb.warn("TanoWrt: [%s] Package has no init scripts [skipping]" % (pkg))
                continue
            else:
                bb.debug(1, "TanoWrt: [%s] Package init scripts: %s" % (pkg, " ".join(scripts)))

            for script in scripts:
                state = d.getVar('TANOWRT_SERVICE_STATE_%s-%s' % (pkg, script), True)

                if not state:
                    bb.warn("TanoWrt: [%s] Can't read initial variable TANOWRT_SERVICE_STATE_%s-%s [skipping]" % (pkg, pkg, script))
                    continue

                if state.lower() == 'enabled' or state.lower() == 'enable':
                    state = 'enable';
                elif state.lower() == 'disabled' or state.lower() == 'disable':
                    state = 'disable';
                else:
                    bb.warn("TanoWrt: [%s] Invalid/unknown state '%s' for script '%s' [skipping]" % (pkg, state, script))
                    continue

                check_cmd = [
                    'grep',
                    '-R',
                    '#!/bin/sh /etc/rc.common',
                    D + sysconfdir + '/init.d/' + script
                ]

                ret = subprocess.call(check_cmd)
                if ret != 0:
                    bb.warn("TanoWrt: [%s] Invalid init script '%s'" % (pkg, script))
                    continue

                postinst  = d.getVar('pkg_postinst_%s' % pkg, True) or ""

                postinst += "#!/bin/sh\n"
                postinst += "if [ \"$PKG_UPGRADE\" != 1 ]; then\n"
                postinst += "\tmkdir -p $D${sysconfdir}/rc.d\n"
                postinst += "\tIPKG_INSTROOT=$D /bin/sh $D${sysconfdir}/rc.common $D${sysconfdir}/init.d/" + script + " " + state + "\n"
                postinst += "fi\n"

                bb.debug(1, "TanoWrt: [%s] Service '%s' default state is '%s'" % (pkg, script, state))

                d.setVar('pkg_postinst_%s' % pkg, postinst)
}

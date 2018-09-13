#
# Check kernel configuration in recipe
#
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#

def kernel_get_config(config, d):
    import re

    staging = d.getVar('STAGING_KERNEL_BUILDDIR', True)
    with open(staging + '/.config') as f:
        lines = f.readlines()

    for line in lines:
        match = re.search(r'^' + config + '=(.)$', line)
        if match:
            return match.group(1)

    return "n"

python do_kernel_config_check() {
    kccopts = d.getVarFlags("KERNEL_CONFIG_CHECK") or {}

    bb.debug(1, 'Checking kernel configuration...');

    # KERNEL_CONFIG_CHECK[BRIDGE] = "y,m=kernel-module-bridge"
    if kccopts:
        for option, values_list in sorted(kccopts.items()):
            values = values_list.split(",")
            allowed = []

            bb.debug(1, '  Checking option CONFIG_%s...' % option);

            if not len(values):
                bb.fatal('KERNEL_CONFIG_CHECK[%s] is empty' % option)

            kvalue = kernel_get_config('CONFIG_%s' % option, d)
            d.setVar("KERNEL_CONFIG_%s" % option, kvalue)

            bb.debug(1, '    Option CONFIG_%s=%s' % (option, kvalue));

            ok = False

            for value in values:
                v = value.split("=")

                if len(v) >= 2:
                    avalue = v[0]
                    deps = v[1]
                elif len(v) >= 1:
                    avalue = v[0]
                    deps = ""
                else:
                    continue

                allowed.append(avalue)

                if kvalue == avalue:
                    ok = True
                    pn = d.getVar("PN", True)
                    if deps:
                        bb.debug(1, '      [Ok] Added runtime dependency on %s' % deps);
                        d.prependVar('RDEPENDS_%s' % pn, "%s " % deps)
                    else:
                        bb.debug(1, '      [Ok]');


                    break

            if not ok:
                bb.fatal('Kernel option CONFIG_%s check failed (allowed %s)' % (option, ", ".join(allowed)));
}

addtask kernel_config_check after do_compile before do_install
do_compile[depends] += "virtual/kernel:do_shared_workdir"

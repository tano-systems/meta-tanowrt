#
# OpenWrt services control class
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

# Recipe parse-time sanity checks
def update_owrt_after_parse(d):
    owrt_packages = d.getVar('OPENWRT_SERVICE_PACKAGES', True)

    if not owrt_packages:
        bb.fatal("%s inherits openwrt-services but doesn't set OPENWRT_SERVICE_PACKAGES" % d.getVar('FILE', True))

    for pkg in owrt_packages.split():
        if not d.getVar('OPENWRT_SERVICE_SCRIPTS_%s' % pkg, True):
            bb.fatal("%s inherits openwrt-services but doesn't set OPENWRT_SERVICE_SCRIPTS for package '%s'" % (d.getVar('FILE', True), pkg))

python __anonymous() {
    if not bb.data.inherits_class('nativesdk', d) \
        and not bb.data.inherits_class('native', d):
        update_owrt_after_parse(d)
}

python populate_packages_prepend() {
    import subprocess
    import string
    import os

    D             = d.getVar('D', True)
    sysconfdir    = d.getVar('sysconfdir', True)
    owrt_packages = d.getVar('OPENWRT_SERVICE_PACKAGES', True)
    packages      = d.getVar('PACKAGES', True)

    bb.debug(1, "OpenWrt: Recipe '%s' packages: %s" % (d.expand('${PN}'), owrt_packages))

    for pkg in owrt_packages.split():

        if not pkg in packages:
            bb.warn("OpenWrt: [%s] Package is not listed in PACKAGES [skipping]" % pkg)
            continue

        owrt_scripts = d.getVar('OPENWRT_SERVICE_SCRIPTS_%s' % pkg, True) or ""
        scripts = owrt_scripts.split()

        if len(scripts) == 0:
            bb.warn("OpenWrt: [%s] Package has no init scripts [skipping]" % (pkg))
            continue
        else:
            bb.debug(1, "OpenWrt: [%s] Package init scripts: %s" % (pkg, " ".join(scripts)))

        for script in scripts:
            state = d.getVar('OPENWRT_SERVICE_STATE_%s-%s' % (pkg, script), True)
            
            if not state:
                bb.warn("OpenWrt: [%s] Can't read initial variable OPENWRT_SERVICE_STATE_%s-%s [skipping]" % (pkg, pkg, script))
                continue

            if state.lower() == 'enabled' or state.lower() == 'enable':
                state = 'enable';
            elif state.lower() == 'disabled' or state.lower() == 'disable':
                state = 'disable';
            else:
                bb.warn("OpenWrt: [%s] Invalid/unknown state '%s' for script '%s' [skipping]" % (pkg, state, script))
                continue

            check_cmd = [
                'grep',
                '-R',
                '#!/bin/sh /etc/rc.common',
                D + sysconfdir + '/init.d/' + script
            ]

            ret = subprocess.call(check_cmd)
            if ret != 0:
                bb.warn("OpenWrt: [%s] Invalid init script '%s'" % (pkg, script))
                continue

            postinst  = d.getVar('pkg_postinst_%s' % pkg, True) or ""

            postinst += "#!/bin/sh\n"
            postinst += "mkdir -p $D${sysconfdir}/rc.d\n"
            postinst += "IPKG_INSTROOT=$D /bin/sh $D${sysconfdir}/rc.common $D${sysconfdir}/init.d/" + script + " " + state + "\n"

            bb.debug(1, "OpenWrt: [%s] Service '%s' default state is '%s'" % (pkg, script, state))

            d.setVar('pkg_postinst_%s' % pkg, postinst)
}

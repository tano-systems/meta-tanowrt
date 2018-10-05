# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".tano11"

# Initial timezone
OPENWRT_ZONENAME ?= "Europe/Moscow"
OPENWRT_TIMEZONE ?= "MSK-3"

# System hostname
OPENWRT_HOSTNAME ?= "openwrt"

# Initial hwclock parameters
OPENWRT_HWCLOCK_DEV       ?= "/dev/rtc0"
OPENWRT_HWCLOCK_LOCALTIME ?= "1"

SUMMARY = "Base files from openembedded and openwrt projects"
HOMEPAGE = "http://wiki.openwrt.org/"
RDEPENDS_${PN} += "tzdata"

S = "${WORKDIR}"
LIC_FILES_CHKSUM_remove = " file://${WORKDIR}/../git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "
LIC_FILES_CHKSUM_append = " file://${WORKDIR}/git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://0001-use-sh-not-ash.patch \
"
SRCREV = "${OPENWRT_SRCREV}"

SRC_URI_append = "\
    file://sysctl.conf \
    file://issue \
    file://hostname \
    file://boot.init \
    file://system.init \
    file://system.config \
    file://sysfixtime.init \
"

# Only for x86 and x86-64 architectures
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files-x86:"
MACHINES_X86 = "qemux86 qemux86-64"
SRC_URI_append = "\
	file://01_sysinfo \
	file://02_load_x86_ucode \
	file://15_essential_fs_x86 \
	file://20_check_iso \
	file://79_move_config \
"

SG = "${WORKDIR}/git/openwrt"
STMP = "${WORKDIR}/stmp"

inherit openwrt-base-files
inherit openwrt-version
inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "base-files"

OPENWRT_SERVICE_SCRIPTS_base-files += "boot done sysctl umount gpio_switch led sysfixtime system urandom_seed"
OPENWRT_SERVICE_STATE_base-files-gpio_switch ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-led ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-sysfixtime ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-system ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-urandom_seed ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-boot ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-done ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-sysctl ?= "enabled"
OPENWRT_SERVICE_STATE_base-files-umount ?= "enabled"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGECONFIG ??= "includeopenwrt"

PACKAGECONFIG[includeopenwrt] = ""
PACKAGECONFIG[preferopenwrt] = ""
PACKAGECONFIG[oeoveropenwrt] = ""

BASEFILESISSUEINSTALL ?= "${@bb.utils.contains('PACKAGECONFIG', 'preferopenwrt', '', 'do_install_basefilesissue', d)}"

do_install[vardeps] += "OPENWRT_ZONENAME OPENWRT_TIMEZONE OPENWRT_VERSION_SED"

do_install_append () {
    if [ "${@bb.utils.contains('PACKAGECONFIG', 'includeopenwrt', 'true', 'false', d)}" = "true" ]; then
        rm -rf ${D}${localstatedir}/backups
        rm -rf ${D}${localstatedir}/local

        # We need to munge openwrt base-files before copying
        # Some file come from regular OE base-files and others
        # belong in other recipes, or are not applicable
        rm -rf ${STMP}
        mkdir -p ${STMP}
        cp -dR --preserve=mode,links ${SG}/package/base-files/files/* ${STMP}/

        # procd - earlyinit
        rm -f ${STMP}/etc/inittab
        rm -f ${STMP}/etc/preinit
        rm -f ${STMP}/etc/diag.sh
        rm -f ${STMP}/lib/functions/preinit.sh
        rm -rf ${STMP}/lib/preinit
        rm -f ${STMP}/rom/note
        # We want these to fail if Openwrt adds more to these dirs, so no rm -rf
        rmdir ${STMP}/rom

        # procd - init
        rm -f ${STMP}/lib/functions/service.sh
        rm -f ${STMP}/etc/rc.common
        rm -f ${STMP}/etc/rc.local
        rm -f ${STMP}/usr/libexec/login.sh

        # We want these to fail if Openwrt adds more to these dirs, so no rm -rf
        rmdir ${STMP}/usr/libexec

        # procd - devmanager
        rm -rf ${STMP}/etc/rc.button
        rm -rf ${STMP}/etc/hotplug.d
        rm -f ${STMP}/sbin/hotplug-call

        # From OE base-files or netbase
        rm -f ${STMP}/etc/hosts
        rm -f ${STMP}/etc/rpc
        rm -f ${STMP}/etc/services
        rm -f ${STMP}/etc/protocols
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'preferopenwrt', 'true', 'false', d)}" != "true" ]; then
           rm -f ${STMP}/etc/banner
           ln -sf /etc/issue ${STMP}/etc/banner
        else
           rm -f ${D}/etc/issue
           ln -sf /etc/banner ${D}/etc/issue
        fi

        # For netifd package
        rm -f ${STMP}/lib/functions/network.sh
        rm -f ${STMP}/sbin/wifi
        rm -f ${STMP}/etc/uci-defaults/12_network-generate-ula

        # Not applicable to OE flavour
        rm -f ${STMP}/etc/uci-defaults/10_migrate-shadow
        rm -f ${STMP}/etc/uci-defaults/11_migrate-sysctl

        # These depend on mechanisms not in OE build process
        rm -f ${STMP}/etc/uci-defaults/13_fix_group_user
        # We want this to fail if Openwrt adds more to this dir, so no rm -rf
        rmdir ${STMP}/etc/uci-defaults

        # In base-files-scripts-openwrt
        rm -f ${STMP}/lib/functions.sh
        rm -f ${STMP}/lib/functions/uci-defaults.sh
        rm -f ${STMP}/lib/functions/system.sh
        rm -f ${STMP}/bin/ipcalc.sh

        # In base-files-scripts-sysupgrade
        rm -f ${STMP}/etc/sysupgrade.conf
        rm -f ${STMP}/sbin/sysupgrade
        rm -rf ${STMP}/lib/upgrade
        rm -f ${STMP}/sbin/firstboot

        # Some files in standard base-files don't apply to openwrt flavour
        # These two are about avoiding flash writes
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'preferopenwrt', 'true', 'false', d)}" = "true" ]; then
          rm -f ${D}${sysconfdir}/fstab
          rm -f ${D}${sysconfdir}/mtab
        fi

        # Copy what is applicable to rootfs
        cp -dR --preserve=mode,links ${STMP}/* ${D}
        rm -rf ${STMP}

        # FIXME: Should be OE's busybox crontabs dir
        mkdir -p ${D}${sysconfdir}/crontabs

        # FIXME: Should this change for OE?
        mkdir -p ${D}/overlay

        # Avoid flash writes
        ln -sf /tmp/resolv.conf /tmp/TZ ${D}${sysconfdir}/
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'oeoveropenwrt', 'true', 'false', d)}" != "true" ]; then
            ln -sf /tmp/fstab ${D}${sysconfdir}/fstab
            ln -sf /proc/mounts ${D}${sysconfdir}/mtab
        fi

        chmod 0600 ${D}${sysconfdir}/shadow
        chmod 1777 ${D}/tmp

        sed -i "s#%PATH%#/usr/sbin:/sbin:/usr/bin:/bin#g" \
              ${D}${sysconfdir}/profile

        install -d -m 0755 ${D}${sysconfdir}/config
        install -d -m 0755 ${D}${sysconfdir}/init.d

        install -m 0644 ${S}/sysctl.conf ${D}${sysconfdir}/sysctl.conf
        install -m 0644 ${S}/issue ${D}${sysconfdir}/issue
        install -m 0644 ${S}/hostname ${D}${sysconfdir}/hostname
        install -m 0755 ${S}/system.init ${D}${sysconfdir}/init.d/system
        install -m 0644 ${S}/system.config ${D}${sysconfdir}/config/system
        install -m 0755 ${S}/boot.init ${D}${sysconfdir}/init.d/boot
        install -m 0755 ${S}/sysfixtime.init ${D}${sysconfdir}/init.d/sysfixtime

        install -m 0644 ${S}/profile ${D}${sysconfdir}/profile

        rm ${D}${sysconfdir}/issue.net
        rm ${D}${sysconfdir}/TZ

        # Run VERSION_SED script
        ${OPENWRT_VERSION_SED} \
            ${D}/usr/lib/os-release \
            ${D}${sysconfdir}/device_info \
            ${D}${sysconfdir}/openwrt_version \
            ${D}${sysconfdir}/openwrt_release \
            ${D}${sysconfdir}/issue

        # Setup timezone and zonename in /etc/config/system
        OPENWRT_TIMEZONE_ESCAPED="${@d.getVar('OPENWRT_TIMEZONE', True).replace('/', '\/')}"
        OPENWRT_ZONENAME_ESCAPED="${@d.getVar('OPENWRT_ZONENAME', True).replace('/', '\/')}"

        sed -i -e "s/\(option\s*timezone\).*/\1 \'${OPENWRT_TIMEZONE_ESCAPED}\'/" ${D}${sysconfdir}/config/system
        sed -i -e "s/\(option\s*zonename\).*/\1 \'${OPENWRT_ZONENAME_ESCAPED}\'/" ${D}${sysconfdir}/config/system

        # Setup default hwclock parameters
        OPENWRT_HWCLOCK_DEV_ESCAPED="${@d.getVar('OPENWRT_HWCLOCK_DEV', True).replace('/', '\/')}"
        OPENWRT_HWCLOCK_LOCALTIME_ESCAPED="${@d.getVar('OPENWRT_HWCLOCK_LOCALTIME', True).replace('/', '\/')}"

        sed -i -e "s/\(option\s*hwclock_dev\).*/\1 \'${OPENWRT_HWCLOCK_DEV_ESCAPED}\'/" ${D}${sysconfdir}/config/system
        sed -i -e "s/\(option\s*hwclock_localtime\).*/\1 \'${OPENWRT_HWCLOCK_LOCALTIME_ESCAPED}\'/" ${D}${sysconfdir}/config/system

        # Setup system hostname
        OPENWRT_HOSTNAME_ESCAPED="${@d.getVar('OPENWRT_HOSTNAME', True).replace('/', '\/')}"
        sed -i -e "s/\(option\s*hostname\).*/\1 \'${OPENWRT_HOSTNAME_ESCAPED}\'/" ${D}${sysconfdir}/config/system
        echo "${@d.getVar('OPENWRT_HOSTNAME', True)}" > ${D}${sysconfdir}/hostname

        rm -rf ${D}/var/run
        rm -rf ${D}/run
        ln -s /var/run ${D}/run

        rm -rf ${D}/proc/mounts
        rm -rf ${D}${sysconfdir}/motd
        rm -rf ${D}${sysconfdir}/skel
        rm -rf ${D}${sysconfdir}/filesystems

        if [ "${@bb.utils.contains('MACHINES_X86', '${MACHINE}', 'true', 'false', d)}" = "true" ]; then
            install -dm 0755 ${D}/lib/preinit
            install -m 0644 ${WORKDIR}/01_sysinfo ${D}/lib/preinit/01_sysinfo
            install -m 0644 ${WORKDIR}/02_load_x86_ucode ${D}/lib/preinit/02_load_x86_ucode
            install -m 0644 ${WORKDIR}/15_essential_fs_x86 ${D}/lib/preinit/15_essential_fs_x86
            install -m 0644 ${WORKDIR}/20_check_iso ${D}/lib/preinit/20_check_iso
            install -m 0644 ${WORKDIR}/79_move_config ${D}/lib/preinit/79_move_config
        fi
    fi
}

pkg_preinst_${PN} () {
    :
}

pkg_postinst_${PN}_append() {
    rm -rf $D/var/lock
    mkdir -p $D/var/lock
}

FILES_${PN} = "/"

RDEPENDS_${PN} += "\
                  ${@bb.utils.contains('PACKAGECONFIG', 'includeopenwrt', '${PN}-scripts-openwrt ${PN}-scripts-sysupgrade', '', d)} \
                  "

RSUGGESTS_${PN} += "\
                   ${@bb.utils.contains('PACKAGECONFIG', 'preferopenwrt', '${PN}-scripts-sysupgrade', '', d)} \
                   ${@bb.utils.contains('PACKAGECONFIG', 'oeoveropenwrt', '', 'procd ubox', d)} \
                   "

CONFFILES_${PN} += "\
                   ${sysconfdir}/fstab \
                   ${@['', '${sysconfdir}/hostname'][(d.getVar('hostname', True) != '')]} \
                   ${sysconfdir}/shells \
                   "

PACKAGE_ARCH = "${MACHINE_ARCH}"

CONFFILES_${PN}_append = "\
	${sysconfdir}/resolv.conf \
	${sysconfdir}/nsswitch.conf \
	${sysconfdir}/host.conf \
	${sysconfdir}/shells \
	${sysconfdir}/config/system \
	${sysconfdir}/fstab \
	${sysconfdir}/sysctl.conf \
	${sysconfdir}/shadow \
	${sysconfdir}/hostname \
	${sysconfdir}/group \
	${sysconfdir}/profile \
	${sysconfdir}/passwd \
	${sysconfdir}/sysctl.d/10-default.conf \
"

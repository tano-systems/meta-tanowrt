#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".tano73.${INC_PR}"

RDEPENDS_${PN} += "tano-version"

# Initial timezone
OPENWRT_ZONENAME ?= "Europe/Moscow"
OPENWRT_TIMEZONE ?= "MSK-3"

# System hostname
OPENWRT_HOSTNAME ?= "tanowrt"

# Initial hwclock parameters
OPENWRT_HWCLOCK_DEV       ?= "/dev/rtc0"
OPENWRT_HWCLOCK_LOCALTIME ?= "1"

# Enable/disable overlay partition resize at preinit stage
TANOWRT_ENABLE_OVERLAY_RESIZE ?= "0"

SUMMARY = "Base files from openembedded and openwrt projects"
HOMEPAGE = "http://wiki.openwrt.org/"

RRECOMMENDS_${PN} += "tzdata"
RRECOMMENDS_${PN} += "${@oe.utils.conditional('TANOWRT_ENABLE_OVERLAY_RESIZE', '1', \
	'parted e2fsprogs-resize2fs', '', d)}"

require base-files.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files-arch:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://0001-use-sh-not-ash.patch \
"
SRC_URI_append = "\
    file://shells \
    file://sysctl.conf \
    file://issue \
    file://hostname \
    file://boot.init \
    file://system.init \
    file://system.config \
    file://sysfixtime.init \
    file://preinit/50_partitions_setup \
    file://preinit/51_overlay_resize \
    file://preinit/80_mount_root \
    file://hotplug-call \
    file://sysctl.d/10-default.conf \
    file://sysctl.d/20-noswap.conf \
"

# Only for x86 and x86-64 architectures
FILESEXTRAPATHS_prepend_qemux86 := "${THISDIR}/${PN}/files-arch/x86:"
FILESEXTRAPATHS_prepend_qemux86-64 := "${THISDIR}/${PN}/files-arch/x86:"

ARCH_X86 = "i586 x86_64"
X86_SRC_URI_FILES = "\
	file://preinit/01_sysinfo \
	file://preinit/02_load_x86_ucode \
	file://preinit/15_essential_fs_x86 \
	file://preinit/20_check_iso \
	file://preinit/79_move_config \
"

SRC_URI_append_qemuarm64 = "file://preinit/01_sysinfo"
SRC_URI_append_qemux86 = "${X86_SRC_URI_FILES}"
SRC_URI_append_qemux86-64 = "${X86_SRC_URI_FILES}"

SG = "${WORKDIR}/openwrt"
STMP = "${WORKDIR}/stmp"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "base-files"

TANOWRT_SERVICE_SCRIPTS_base-files += "boot done sysctl umount gpio_switch led sysfixtime system"
TANOWRT_SERVICE_STATE_base-files-gpio_switch ?= "enabled"
TANOWRT_SERVICE_STATE_base-files-led ?= "enabled"
TANOWRT_SERVICE_STATE_base-files-sysfixtime ?= "enabled"
TANOWRT_SERVICE_STATE_base-files-system ?= "enabled"
TANOWRT_SERVICE_STATE_base-files-boot ?= "enabled"
TANOWRT_SERVICE_STATE_base-files-done ?= "enabled"
TANOWRT_SERVICE_STATE_base-files-sysctl ?= "enabled"
TANOWRT_SERVICE_STATE_base-files-umount ?= "enabled"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

BASEFILESISSUEINSTALL ?= "do_install_basefilesissue"

do_install[vardeps] += "OPENWRT_ZONENAME OPENWRT_TIMEZONE OPENWRT_VERSION_SED"

OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_STDERR ?= "y"
OPENWRT_CONFIG_TARGET_PREINIT_TIMEOUT ?= "2"
OPENWRT_CONFIG_TARGET_PREINIT_INIT_PATH ?= "/usr/sbin:/usr/bin:/sbin:/bin"
OPENWRT_CONFIG_TARGET_INIT_ENV ?= ""
OPENWRT_CONFIG_TARGET_INIT_CMD ?= "/sbin/init"
OPENWRT_CONFIG_TARGET_INIT_SUPPRESS_STDERR ?= "y"
OPENWRT_CONFIG_TARGET_PREINIT_IFNAME ?= ""
OPENWRT_CONFIG_TARGET_PREINIT_IP ?= "192.168.1.1"
OPENWRT_CONFIG_TARGET_PREINIT_NETMASK ?= "255.255.255.0"
OPENWRT_CONFIG_TARGET_PREINIT_BROADCAST ?= "192.168.1.255"
OPENWRT_CONFIG_TARGET_PREINIT_SHOW_NETMSG ?= ""
OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_FAILSAFE_NETMSG ?= ""
OPENWRT_CONFIG_TARGET_PREINIT_DISABLE_FAILSAFE ?= ""

do_install_append () {
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

	# From OE base-files or netbase
	rm -f ${STMP}/etc/hosts
	rm -f ${STMP}/etc/rpc
	rm -f ${STMP}/etc/services
	rm -f ${STMP}/etc/protocols
	rm -f ${STMP}/etc/banner
	ln -sf /etc/issue ${STMP}/etc/banner

	# For netifd package
	${@bb.utils.contains('COMBINED_FEATURES', 'wifi', '', 'rm -f ${STMP}/sbin/wifi', d)}

	# Not applicable to OE flavour
	rm -f ${STMP}/etc/uci-defaults/10_migrate-shadow
	rm -f ${STMP}/etc/uci-defaults/11_migrate-sysctl

	# These depend on mechanisms not in OE build process
	rm -f ${STMP}/etc/uci-defaults/13_fix_group_user

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

	# Moved to tano-version.bb
	rm -f ${STMP}/etc/os-release
	rm -f ${STMP}/usr/lib/os-release

	# Copy what is applicable to rootfs
	cp -dR --preserve=mode,links ${STMP}/* ${D}
	rm -rf ${STMP}

	mkdir -p ${D}${sysconfdir}/rc.d

	# FIXME: Should this change for OE?
	mkdir -p ${D}/overlay

	ln -sf /tmp/resolv.conf /tmp/TZ ${D}${sysconfdir}/
	ln -sf /proc/mounts ${D}${sysconfdir}/mtab

	chmod 0600 ${D}${sysconfdir}/shadow
	chmod 1777 ${D}/tmp

	sed -i "s#%PATH%#/usr/sbin:/sbin:/usr/bin:/bin#g" \
		${D}${sysconfdir}/profile

	install -d -m 0755 ${D}${sysconfdir}/config
	install -d -m 0755 ${D}${sysconfdir}/init.d
	install -d -m 0755 ${D}${sysconfdir}/sysctl.d

	install -m 0644 ${WORKDIR}/sysctl.conf ${D}${sysconfdir}/sysctl.conf
	install -m 0644 ${WORKDIR}/sysctl.d/10-default.conf ${D}${sysconfdir}/sysctl.d/10-default.conf
	install -m 0644 ${WORKDIR}/sysctl.d/20-noswap.conf ${D}${sysconfdir}/sysctl.d/20-noswap.conf

	# these files generated in tanowrt-image-buildinfo.bbclass
	rm -f ${D}${sysconfdir}/device_info
	rm -f ${D}${sysconfdir}/openwrt_release
	rm -f ${D}${sysconfdir}/openwrt_version

	install -m 0644 ${WORKDIR}/issue ${D}${sysconfdir}/issue
	install -m 0644 ${WORKDIR}/hostname ${D}${sysconfdir}/hostname
	install -m 0755 ${WORKDIR}/system.init ${D}${sysconfdir}/init.d/system
	install -m 0644 ${WORKDIR}/system.config ${D}${sysconfdir}/config/system
	install -m 0755 ${WORKDIR}/boot.init ${D}${sysconfdir}/init.d/boot
	install -m 0755 ${WORKDIR}/sysfixtime.init ${D}${sysconfdir}/init.d/sysfixtime
	install -m 0755 ${WORKDIR}/hotplug-call ${D}${base_sbindir}/hotplug-call
	install -m 0644 ${WORKDIR}/shells ${D}${sysconfdir}/shells

	install -m 0644 ${WORKDIR}/profile ${D}${sysconfdir}/profile

	install -dm 0755 ${D}/lib/preinit
	install -m 0644 ${WORKDIR}/preinit/80_mount_root ${D}/lib/preinit/80_mount_root
	install -m 0644 ${WORKDIR}/preinit/50_partitions_setup ${D}/lib/preinit/50_partitions_setup

	if [ "${TANOWRT_ENABLE_OVERLAY_RESIZE}" = "1" ]; then
		install -m 0644 ${WORKDIR}/preinit/51_overlay_resize ${D}/lib/preinit/51_overlay_resize
	fi

	rm ${D}${sysconfdir}/issue.net
	rm ${D}${sysconfdir}/TZ

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

	if [ "${@bb.utils.contains('ARCH_X86', '${TARGET_ARCH}', 'true', 'false', d)}" = "true" ]; then
		install -dm 0755 ${D}/lib/preinit
		install -m 0644 ${WORKDIR}/preinit/01_sysinfo ${D}/lib/preinit/01_sysinfo
		install -m 0644 ${WORKDIR}/preinit/02_load_x86_ucode ${D}/lib/preinit/02_load_x86_ucode
		install -m 0644 ${WORKDIR}/preinit/15_essential_fs_x86 ${D}/lib/preinit/15_essential_fs_x86
		install -m 0644 ${WORKDIR}/preinit/20_check_iso ${D}/lib/preinit/20_check_iso
		install -m 0644 ${WORKDIR}/preinit/79_move_config ${D}/lib/preinit/79_move_config
	fi

	if [ "${ROOT_HOME}" != "/home/root" ]; then
		# Make symlink /home/root to real root's home
		mkdir -p ${D}/home
		ln -s ${ROOT_HOME} ${D}/home/root
	fi

	install -dm 0755 ${D}${libdir}/locale

	# Common default PATH
	install -dm 0755 ${D}/lib/preinit
	PREINIT_CONF="${D}/lib/preinit/00_preinit.conf"
	echo "pi_suppress_stderr=\"${OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_STDERR}\"" >${PREINIT_CONF}
	echo "fs_failsafe_wait_timeout=${OPENWRT_CONFIG_TARGET_PREINIT_TIMEOUT}" >>${PREINIT_CONF}
	echo "pi_init_path=\"${OPENWRT_CONFIG_TARGET_PREINIT_INIT_PATH}\"" >>${PREINIT_CONF}
	echo "pi_init_env=\"${OPENWRT_CONFIG_TARGET_INIT_ENV}\"" >>${PREINIT_CONF}
	echo "pi_init_cmd=\"${OPENWRT_CONFIG_TARGET_INIT_CMD}\"" >>${PREINIT_CONF}
	echo "pi_init_suppress_stderr=\"${OPENWRT_CONFIG_TARGET_INIT_SUPPRESS_STDERR}\"" >>${PREINIT_CONF}
	echo "pi_ifname=\"${OPENWRT_CONFIG_TARGET_PREINIT_IFNAME}\"" >>${PREINIT_CONF}
	echo "pi_ip=\"${OPENWRT_CONFIG_TARGET_PREINIT_IP}\"" >>${PREINIT_CONF}
	echo "pi_netmask=\"${OPENWRT_CONFIG_TARGET_PREINIT_NETMASK}\"" >>${PREINIT_CONF}
	echo "pi_broadcast=\"${OPENWRT_CONFIG_TARGET_PREINIT_BROADCAST}\"" >>${PREINIT_CONF}
	echo "pi_preinit_net_messages=\"${OPENWRT_CONFIG_TARGET_PREINIT_SHOW_NETMSG}\"" >>${PREINIT_CONF}
	echo "pi_preinit_no_failsafe_netmsg=\"${OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_FAILSAFE_NETMSG}\"" >>${PREINIT_CONF}
	echo "pi_preinit_no_failsafe=\"${OPENWRT_CONFIG_TARGET_PREINIT_DISABLE_FAILSAFE}\"" >>${PREINIT_CONF}

	sed -i "s#%PATH%#/usr/sbin:/usr/bin:/sbin:/bin#g" \
		${D}${sysconfdir}/preinit \
		${D}${base_sbindir}/hotplug-call

	sed -i 's#:/root:#:${ROOT_HOME}:#' ${D}${sysconfdir}/passwd
}

do_install_append_qemuarm64() {
	install -dm 0755 ${D}/lib/preinit
	install -m 0644 ${WORKDIR}/preinit/01_sysinfo ${D}/lib/preinit/01_sysinfo
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
	${PN}-scripts-openwrt \
	${PN}-scripts-sysupgrade \
	getrandom \
	factory-reset \
"

RSUGGESTS_${PN} += "\
	procd \
	ubox \
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
	${sysconfdir}/shinit \
	${sysconfdir}/passwd \
	${sysconfdir}/sysctl.d/10-default.conf \
	${sysconfdir}/sysctl.d/20-noswap.conf \
	${sysconfdir}/rc.local \
"

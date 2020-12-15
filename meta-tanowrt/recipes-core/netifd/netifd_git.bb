# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano38"

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/netifd.git;name=netifd;branch=master \
	file://100-Fix-IFF_LOWER_UP-define.patch \
	file://0001-Fix-errors-for-clang.patch \
"

# rootfs files
SRC_URI += "\
	file://rootfs/etc/config/network \
	file://rootfs/etc/config/wireless \
	file://rootfs/etc/hotplug.d/iface/00-netstate \
	file://rootfs/etc/hotplug.d/net/20-smp-packet-steering \
	file://rootfs/etc/init.d/network \
	file://rootfs/etc/uci-defaults/14_migrate-dhcp-release \
	file://rootfs/lib/netifd/proto/dhcp.sh \
	file://rootfs/lib/netifd/dhcp.script \
	file://rootfs/lib/network/config.sh \
	file://rootfs/sbin/devstatus \
	file://rootfs/sbin/ifstatus \
	file://rootfs/sbin/ifup \
	file://rootfs/usr/share/udhcpc/default.script \
"

# 14.12.2020
# netifd: fix a typo in vlandev hotplug support
SRCREV_netifd = "88c6003e2b4fdc0c990045ff140bf19b37ba745a"

S = "${WORKDIR}/git"

inherit cmake pkgconfig tanowrt-services update-alternatives

TANOWRT_SERVICE_PACKAGES = "netifd"
TANOWRT_SERVICE_SCRIPTS_netifd += "network"
TANOWRT_SERVICE_STATE_netifd-network ?= "enabled"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"

do_configure_prepend () {
	# replace hardcoded '/lib/' with '${base_libdir}/'
	grep -rnl "/lib/" ${WORKDIR}/rootfs/ | xargs sed -i "s:/lib/:${base_libdir}/:g"
}

do_install_append() {
	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/rootfs/etc/config/network ${D}${sysconfdir}/config/

	if [ "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', '1', '0', d)}" == "1" ]; then
		install -m 0644 ${WORKDIR}/rootfs/etc/config/wireless ${D}${sysconfdir}/config/
	fi

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/rootfs/etc/init.d/network ${D}${sysconfdir}/init.d/

	install -d ${D}${sysconfdir}/uci-defaults
	install -m 0755 ${WORKDIR}/rootfs/etc/uci-defaults/14_migrate-dhcp-release ${D}${sysconfdir}/uci-defaults/

	install -d ${D}${sysconfdir}/hotplug.d/iface
	install -d ${D}${sysconfdir}/hotplug.d/net
	install -m 0755 ${WORKDIR}/rootfs/etc/hotplug.d/iface/00-netstate ${D}${sysconfdir}/hotplug.d/iface/
	install -m 0755 ${WORKDIR}/rootfs/etc/hotplug.d/net/20-smp-packet-steering ${D}${sysconfdir}/hotplug.d/net/

	install -d ${D}${base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/rootfs/lib/netifd/proto/dhcp.sh ${D}${base_libdir}/netifd/proto/
	install -m 0755 ${WORKDIR}/rootfs/lib/netifd/dhcp.script ${D}${base_libdir}/netifd/
	cp -dR --preserve=mode,links ${S}/scripts/* ${D}${base_libdir}/netifd/

	install -d ${D}${base_libdir}/network
	install -m 0755 ${WORKDIR}/rootfs/lib/network/config.sh ${D}${base_libdir}/network/

	install -d ${D}${base_sbindir}
	install -m 0755 ${WORKDIR}/rootfs/sbin/devstatus ${D}${base_sbindir}/
	install -m 0755 ${WORKDIR}/rootfs/sbin/ifstatus ${D}${base_sbindir}/
	install -m 0755 ${WORKDIR}/rootfs/sbin/ifup ${D}${base_sbindir}/
	
	ln -sf ifup ${D}/sbin/ifdown
	ln -sf /usr/sbin/netifd ${D}/sbin/netifd

	install -d ${D}${datadir}/udhcpc
	install -m 0755 ${WORKDIR}/rootfs/usr/share/udhcpc/default.script ${D}${datadir}/udhcpc
}

ALTERNATIVE_${PN} = "ifup ifdown default.script"

ALTERNATIVE_PRIORITY = "40"
ALTERNATIVE_PRIORITY_pkg[default.script] = "60"
ALTERNATIVE_LINK_NAME[ifup] = "${base_sbindir}/ifup"
ALTERNATIVE_LINK_NAME[ifdown] = "${base_sbindir}/ifdown"
ALTERNATIVE_LINK_NAME[default.script] = "/usr/share/udhcpc/default.script"

FILES_${PN} += "\
	${datadir}/udhcpc/default.script* \
	${base_libdir}/netifd/dhcp.script \
	${base_libdir}/netifd/utils.sh \
	${base_libdir}/netifd/netifd-wireless.sh \
	${base_libdir}/netifd/netifd-proto.sh \
	${base_libdir}/netifd/proto/dhcp.sh \
	${base_libdir}/network/config.sh \
	${sysconfdir}/config/network \
	${@bb.utils.contains('COMBINED_FEATURES', 'wifi', '${sysconfdir}/config/wireless', '', d)} \
"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/network \
	${@bb.utils.contains('COMBINED_FEATURES', 'wifi', '${sysconfdir}/config/wireless', '', d)} \
"

RDEPENDS_${PN} += "\
	bridge-utils \
	base-files-scripts-openwrt \
"

inherit kmod/bridge

do_configure[depends] += "virtual/kernel:do_shared_workdir"

#OECMAKE_C_FLAGS += "${@bb.utils.contains('TOOLCHAIN', 'clang', '-Wno-implicit-fallthrough', '', d)}"

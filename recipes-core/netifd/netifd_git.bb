# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano10"

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://git.openwrt.org/project/netifd.git;name=netifd;branch=master \
	file://100-Fix-IFF_LOWER_UP-define.patch \
	file://300-replace-is_error-helper-with-NULL-check.patch \
"

# rootfs files
SRC_URI += "\
	file://rootfs/etc/config/network \
	file://rootfs/etc/hotplug.d/iface/00-netstate \
	file://rootfs/etc/hotplug.d/net/20-smp-tune \
	file://rootfs/etc/init.d/network \
	file://rootfs/lib/netifd/proto/dhcp.sh \
	file://rootfs/lib/netifd/dhcp.script \
	file://rootfs/lib/network/config.sh \
	file://rootfs/sbin/devstatus \
	file://rootfs/sbin/ifdown \
	file://rootfs/sbin/ifstatus \
	file://rootfs/sbin/ifup \
	file://rootfs/usr/share/udhcpc/default.script \
"

# 17.10.2018
# system-linux: enable by default ignore encaplimit for grev6 tunnels
SRCREV_netifd = "841b5d158708ee89d9fa870c40404469cb8e871e"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt openwrt-services update-alternatives

OPENWRT_SERVICE_PACKAGES = "netifd"
OPENWRT_SERVICE_SCRIPTS_netifd += "network"
OPENWRT_SERVICE_STATE_netifd-network ?= "enabled"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"

do_configure_prepend () {
    # replace hardcoded '/lib/' with '${base_libdir}/'
    #grep -rnl "/lib/" ${S}/openwrt/package/network/config/netifd/ | xargs sed -i "s:/lib/:${base_libdir}/:g"
}

do_install_append() {
	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/rootfs/etc/config/network ${D}${sysconfdir}/config/

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/rootfs/etc/init.d/network ${D}${sysconfdir}/init.d/

	install -d ${D}${sysconfdir}/hotplug.d/iface
	install -d ${D}${sysconfdir}/hotplug.d/net
	install -m 0755 ${WORKDIR}/rootfs/etc/hotplug.d/iface/00-netstate ${D}${sysconfdir}/hotplug.d/iface/
	install -m 0755 ${WORKDIR}/rootfs/etc/hotplug.d/net/20-smp-tune ${D}${sysconfdir}/hotplug.d/net/

	install -d ${D}${base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/rootfs/lib/netifd/proto/dhcp.sh ${D}${base_libdir}/netifd/proto/
	install -m 0755 ${WORKDIR}/rootfs/lib/netifd/dhcp.script ${D}${base_libdir}/netifd/
	cp -dR --preserve=mode,links ${S}/scripts/* ${D}${base_libdir}/netifd/

	install -d ${D}${base_libdir}/network
	install -m 0755 ${WORKDIR}/rootfs/lib/network/config.sh ${D}${base_libdir}/network/

	install -d ${D}${base_sbindir}
	install -m 0755 ${WORKDIR}/rootfs/sbin/devstatus ${D}${base_sbindir}/
	install -m 0755 ${WORKDIR}/rootfs/sbin/ifdown ${D}${base_sbindir}/
	install -m 0755 ${WORKDIR}/rootfs/sbin/ifstatus ${D}${base_sbindir}/
	install -m 0755 ${WORKDIR}/rootfs/sbin/ifup ${D}${base_sbindir}/
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
"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/network \
	$(sysconfdir)/config/wireless \
"

RDEPENDS_${PN} += "\
	bridge-utils \
	base-files-scripts-openwrt \
	kmod-bridge \
"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano15"

SUMMARY = "OpenWrt firewall configuration utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/firewall3.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2a8ffaa9ef41014f236ab859378e8900"
SECTION = "base"
DEPENDS = "libubox uci ubus iptables"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/firewall3.git \
           file://firewall.config \
           file://firewall.init \
           file://firewall.hotplug \
           file://firewall.user \
"

# Kernel dependencies and module autoloading
RDEPENDS_${PN} += "\
	kmod-ipt-core \
	kmod-ipt-conntrack \
	kmod-ipt-nat \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'kmod-nf-conntrack6', '', d)} \
"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

# 22.08.2019
# iptables.c: lock the xtables.lock
SRCREV = "8c404ef02f0122ec90b48e122777ff6bfa715d7f"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-services

OPENWRT_SERVICE_PACKAGES                  = "firewall3"
OPENWRT_SERVICE_SCRIPTS_firewall3        += "firewall"
OPENWRT_SERVICE_STATE_firewall3-firewall ?= "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

EXTRA_OECMAKE = "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '-DDISABLE_IPV6=OFF', '-DDISABLE_IPV6=ON', d)}"

do_install_append() {
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/config
    install -d ${D}${sysconfdir}/init.d
    install -d ${D}${sysconfdir}/rc.d
    install -d ${D}${sysconfdir}/hotplug.d/iface
    install -d ${D}${base_sbindir}

    install -m 0755 ${WORKDIR}/firewall.init ${D}${sysconfdir}/init.d/firewall
    install -m 0644 ${WORKDIR}/firewall.config ${D}${sysconfdir}/config/firewall
    install -m 0644 ${WORKDIR}/firewall.user ${D}${sysconfdir}/firewall.user
    install -m 0644 ${WORKDIR}/firewall.hotplug ${D}${sysconfdir}/hotplug.d/iface/20-firewall

    install -d ${D}/usr/share/fw3
    install -m 0644 ${S}/helpers.conf ${D}/usr/share/fw3

    ln -s ${sbindir}/firewall3 ${D}${base_sbindir}/fw3
}

FILES_${PN} += "${libdir}/* /usr/share/fw3/"

RDEPENDS_${PN} += "ipset xtables-addons"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/firewall \
	${sysconfdir}/firewall.user \
"

inherit kernel-config-depends

KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_SYN_COOKIES, \
	required = y \
}"

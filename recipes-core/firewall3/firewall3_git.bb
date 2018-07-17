# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano5"

SUMMARY = "OpenWrt firewall configuration utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/firewall3.git;a=summary"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=17;md5=2a8ffaa9ef41014f236ab859378e8900"
SECTION = "base"
DEPENDS = "libubox uci ubus iptables"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.openwrt.org/project/firewall3.git \
           file://firewall.config \
           file://firewall.init \
           file://firewall.hotplug \
           file://firewall.user \
"

SRC_URI += "\
	file://modules.d/42-ip6tables \
	file://modules.d/ipt-conntrack \
	file://modules.d/ipt-core \
	file://modules.d/ipt-nat \
	file://modules.d/nf-conntrack \
	file://modules.d/nf-conntrack6 \
	file://modules.d/nf-ipt \
	file://modules.d/nf-ipt6 \
	file://modules.d/nf-nat \
"

# 12.07.2018
# firewall3: make reject types selectable by user
SRCREV = "d2bbeb7d42b45e4d97411e5c124845905975fd46"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt-services openwrt-base-files

OPENWRT_SERVICE_PACKAGES              = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN}        ?= "firewall"
OPENWRT_SERVICE_STATE_${PN}-firewall ?= "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

EXTRA_OECMAKE = "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '-DDISABLE_IPV6=OFF', '-DDISABLE_IPV6=ON', d)}"

#CFLAGS += "-Wno-error=format-overflow"
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

    ln -s ${sbindir}/firewall3 ${D}${base_sbindir}/fw3

    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'procd', 'true', 'false', d)}" = "true" ]; then
        MODULES_AUTOLOAD_DIR="${D}${sysconfdir}/modules-boot.d"
        CONF_EXT=""
    else
        MODULES_AUTOLOAD_DIR="${D}${sysconfdir}/modules-load.d"
        CONF_EXT=".conf"
    fi

    # Be prepared for both procd and sysvinit/systemd style module loading
    install -dm 0755 ${MODULES_AUTOLOAD_DIR}

    install -m 0644 ${WORKDIR}/modules.d/ipt-conntrack ${MODULES_AUTOLOAD_DIR}/ipt-conntrack ${CONF_EXT}
    install -m 0644 ${WORKDIR}/modules.d/ipt-core ${MODULES_AUTOLOAD_DIR}/ipt-core ${CONF_EXT}
    install -m 0644 ${WORKDIR}/modules.d/ipt-nat ${MODULES_AUTOLOAD_DIR}/ipt-nat ${CONF_EXT}

    install -m 0644 ${WORKDIR}/modules.d/nf-conntrack ${MODULES_AUTOLOAD_DIR}/nf-conntrack${CONF_EXT}
    install -m 0644 ${WORKDIR}/modules.d/nf-ipt ${MODULES_AUTOLOAD_DIR}/nf-ipt${CONF_EXT}
    install -m 0644 ${WORKDIR}/modules.d/nf-nat ${MODULES_AUTOLOAD_DIR}/nf-nat${CONF_EXT}

    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'true', 'false', d)}" = "true" ]; then
        # Install IPv6 modules
        install -m 0644 ${WORKDIR}/modules.d/42-ip6tables ${MODULES_AUTOLOAD_DIR}/42-ip6tables${CONF_EXT}
        install -m 0644 ${WORKDIR}/modules.d/nf-conntrack6 ${MODULES_AUTOLOAD_DIR}/nf-conntrack6${CONF_EXT}
        install -m 0644 ${WORKDIR}/modules.d/nf-ipt6 ${MODULES_AUTOLOAD_DIR}/nf-ipt6${CONF_EXT}
    fi
}

FILES_${PN} += "${libdir}/*"

RDEPENDS_${PN} = "ipset xtables-addons"

RRECOMMENDS_${PN} = "\
                    kernel-module-ip-tables \
                    kernel-module-xt-tcpudp \
                    kernel-module-iptable-filter \
                    kernel-module-iptable-mangle \
                    kernel-module-iptable-nat \
                    kernel-module-xt-limit \
                    kernel-module-xt-mac \
                    kernel-module-xt-multiport \
                    kernel-module-xt-comment \
                    kernel-module-xt-log \
                    kernel-module-nf-log-common \
                    kernel-module-nf-log-ipv4 \
                    kernel-module-xt-tcpmss \
                    kernel-module-ipt-reject \
                    kernel-module-xt-time \
                    kernel-module-xt-mark \
                    kernel-module-xt-netmap \
                    kernel-module-xt-nat \
                    kernel-module-nf-nat \
                    kernel-module-nf-nat-ipv4 \
                    kernel-module-nf-nat-masquerade-ipv4 \
                    kernel-module-ipt-masquerade \
                    kernel-module-xt-redirect \
                    kernel-module-nf-nat-redirect \
                    kernel-module-iptable-nat \
                    kernel-module-xt-state \
                    kernel-module-xt-ct \
                    kernel-module-nf-defrag-ipv4 \
                    kernel-module-nf-conntrack-ipv4 \
                    kernel-module-nf-conntrack-netlink \
                    kernel-module-xt-conntrack \
                    kernel-module-nf-conntrack \
                    "

RRECOMMENDS_${PN} += "\
                     ${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', '\
                     kernel-module-ip6table-filter \
                     kernel-module-ip6table-mangle \
                     kernel-module-nf-log-ipv6 \
                     kernel-module-ip6t-reject \
                     kernel-module-nf-nat-masquerade-ipv6 \
                     kernel-module-nf-defrag-ipv6 \
                     kernel-module-nf-conntrack-ipv6 \
                     ', '' , d)} \
                     "

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/firewall \
	${sysconfdir}/firewall.user \
"

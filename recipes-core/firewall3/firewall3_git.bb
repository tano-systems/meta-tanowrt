# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano1"

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

SRCREV = "a4d98aea373e04f3fdc3c492c1688ba52ce490a9"

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
        IP6TABLES_CONF=42-ip6tables
        IPTABLES_CONF=40-iptables
    else
        MODULES_AUTOLOAD_DIR="${D}${sysconfdir}/modules-load.d"
        IP6TABLES_CONF=42-ip6tables.conf
        IPTABLES_CONF=40-iptables.conf
    fi

    # Be prepared for both procd and sysvinit/systemd style module loading
    install -dm 0755 ${MODULES_AUTOLOAD_DIR}
    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'true', 'false', d)}" = "true" ]; then

        # Can't indent the here-document because leading spaces confuse
        # kmodloader
        cat >${MODULES_AUTOLOAD_DIR}/${IP6TABLES_CONF} <<EOF
ip6_tables
ip6table_filter
ip6table_mangle
ipt_REJECT
nf_nat_masquerade_ipv6
nf_defrag_ipv6
nf_conntrack_ipv6
EOF
    fi

    # Can't indent the here-document because leading spaces confuse
    # kmodloader
    cat >${MODULES_AUTOLOAD_DIR}/${IPTABLES_CONF} <<EOF
ip_tables
xt_tcpudp
iptable_filter
iptable_mangle
iptable_nat
xt_limit
xt_mac
xt_multiport
xt_comment
xt_LOG
nf_log_common
nf_log_ipv4
xt_tcpmss
ipt_reject
xt_time
xt_mark
xt_nat
xt_NETMAP
nf_nat_ipv4
iptable_nat
ipt_MASQUERADE
xt_REDIRECT
xt_state
xt_CT
xt_conntrack
nf_defrag_ipv4
nf_conntrack_ipv4
nf_conntrack_netlink
nf_nat_masquerade_ipv4
nf_conntrack
EOF
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

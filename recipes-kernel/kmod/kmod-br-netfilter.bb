# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Bridge netfilter support modules"
LICENSE = "MIT & GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit kernel-config-depends

RDEPENDS_${PN} += "kmod-ipt-core"

#
# kmod-br-netfilter
# #################
#

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_BRIDGE_NETFILTER, \
	required          = y|m, \
	m_rdepends        = kernel-module-br-netfilter, \
	m_autoload        = br_netfilter, \
	m_autoload_script = br-netfilter \
}"

SRC_URI += "file://sysctl-nf-conntrack.conf"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/sysctl.d
	install -m 0644 ${WORKDIR}/sysctl-br-netfilter.conf ${D}${sysconfdir}/sysctl.d/11-br-netfilter.conf
}

FILES_${PN} += "${sysconfdir}"

# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano3"
SUMMARY = "Netfilter connection tracking"
LICENSE = "MIT & GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"

inherit kernel-kmod

#
# kmod-nf-conntrack
# #################
#
KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_NF_CONNTRACK_MARK, \
	required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_NF_CONNTRACK_ZONES, \
	required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_CONNTRACK, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-conntrack, \
	m_autoload        = nf_conntrack, \
	m_autoload_script = nf-conntrack \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_DEFRAG_IPV4, \
	required          = y|m, \
	m_rdepends        = kernel-module-nf-defrag-ipv4, \
	m_autoload        = nf_defrag_ipv4, \
	m_autoload_script = nf-conntrack \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option            = CONFIG_NF_CONNTRACK_IPV4, \
	required          = y|m, \
	version           = '< 4.18.0', \
	m_rdepends        = kernel-module-nf-conntrack-ipv4, \
	m_autoload        = nf_conntrack_ipv4, \
	m_autoload_script = nf-conntrack \
}"

# TODO: Need patch from OpenWrt
#KERNEL_CONFIG_DEPENDS += "{\
#	option            = CONFIG_NF_CONNTRACK_RTCACHE, \
#	required          = y|m, \
#	m_rdepends        = kernel-module-nf-conntrack-rtcache, \
#	m_autoload        = nf_conntrack_rtcache, \
#	m_autoload_script = nf-conntrack \
#}"

SRC_URI += "file://sysctl-nf-conntrack.conf"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/sysctl.d
	install -m 0644 ${WORKDIR}/sysctl-nf-conntrack.conf ${D}${sysconfdir}/sysctl.d/11-nf-conntrack.conf
	chown -R root:root ${D}${sysconfdir}
}

FILES_${PN} += "${sysconfdir}"

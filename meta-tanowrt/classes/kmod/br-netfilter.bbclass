#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019, 2020  Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod
inherit kmod/ipt-core

RDEPENDS_${PN} += "kmod-sysctl-br-netfilter"

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
	chown -R root:root ${D}${sysconfdir}
}

FILES_${PN} += "${sysconfdir}"

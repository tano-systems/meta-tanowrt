#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

DESCRIPTION = "Sysctl configuration for br-netfilter kernel module"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${TANOWRT_BASE}/LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"
SECTION = "kernel"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR = "tano1"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

SRC_URI += "file://sysctl-br-netfilter.conf"

do_install_append() {
	install -dm 0755 ${D}${sysconfdir}/sysctl.d
	install -m 0644 ${WORKDIR}/sysctl-br-netfilter.conf ${D}${sysconfdir}/sysctl.d/11-br-netfilter.conf
}

FILES_${PN} += "${sysconfdir}"

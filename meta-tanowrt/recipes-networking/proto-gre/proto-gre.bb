#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020, 2022 Anton Kikin <a.kikin@tano-systems.com>
#
# Generic Routing Encapsulation config support
#
PR = "tano1"

inherit allarch

DESCRIPTION = "Generic Routing Encapsulation config support"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "file://gre.sh file://LICENSE"

FILES:${PN} += "${nonarch_base_libdir}/netifd/proto/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${nonarch_base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/gre.sh ${D}${nonarch_base_libdir}/netifd/proto/gre.sh
}

RDEPENDS:${PN} += "resolveip"

RRECOMMENDS:${PN} += "\
	kernel-module-gre \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'kernel-module-ip6-gre', '', d)} \
"

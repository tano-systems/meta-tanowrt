#
# This file Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
#
# Generic Routing Encapsulation config support
#
PR = "tano0"

inherit allarch

DESCRIPTION = "Generic Routing Encapsulation config support"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "file://gre.sh"

FILES_${PN} += "${base_libdir}/netifd/proto/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/gre.sh ${D}${base_libdir}/netifd/proto/gre.sh
}

RDEPENDS_${PN} += "resolveip"

RRECOMMENDS_${PN} += "\
	kernel-module-gre \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'kernel-module-ip6-gre', '', d)} \
"

#
# This file Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
#
# Virtual eXtensible LAN config support
#
PV = "0.0.2"
PR = "tano2"

inherit allarch

DESCRIPTION = "Virtual eXtensible LAN config support"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

inherit kmod/vxlan

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "file://vxlan.sh"

FILES_${PN} += "${base_libdir}/netifd/proto/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/vxlan.sh ${D}${base_libdir}/netifd/proto/vxlan.sh
}

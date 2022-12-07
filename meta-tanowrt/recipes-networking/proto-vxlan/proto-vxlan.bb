#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020-2022 Anton Kikin <a.kikin@tano-systems.com>
#
# Virtual eXtensible LAN config support
#
PV = "0.0.7"
PR = "tano1"

inherit allarch

DESCRIPTION = "Virtual eXtensible LAN config support"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

inherit kmod/vxlan

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "file://vxlan.sh file://LICENSE"

FILES:${PN} += "${nonarch_base_libdir}/netifd/proto/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${nonarch_base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/vxlan.sh ${D}${nonarch_base_libdir}/netifd/proto/vxlan.sh
}

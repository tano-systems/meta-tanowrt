#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020, 2022 Anton Kikin <a.kikin@tano-systems.com>
#
# Link Aggregation (Channel Bonding) proto handler
#
PV = "2020-03-30"
PR = "tano1"

inherit allarch

DESCRIPTION = "Link Aggregation (Channel Bonding) proto handler"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "file://bonding.sh file://LICENSE"

FILES:${PN} += "${nonarch_base_libdir}/netifd/proto/"

S = "${WORKDIR}"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

do_install() {
	install -dm 0755 ${D}${nonarch_base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/bonding.sh ${D}${nonarch_base_libdir}/netifd/proto/bonding.sh
}

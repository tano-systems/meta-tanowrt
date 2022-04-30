#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022, Rockchip Electronics Co., Ltd
# Copyright (c) 2022, Tano Systems LLC
#

DESCRIPTION = "Rockchip configuration files for udev"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

PV = "1.0.0"
PR = "rk0"

SRC_URI = " \
	file://99-rockchip-permissions.rules \
"

S = "${WORKDIR}"

do_install() {
	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/99-rockchip-permissions.rules ${D}${sysconfdir}/udev/rules.d/99-rockchip-permissions.rules
}

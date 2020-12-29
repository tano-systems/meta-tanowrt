#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

SUMMARY = "TanoWrt base image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${TANOWRT_BASE}/LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit tanowrt-image

CORE_IMAGE_BASE_INSTALL = "\
	packagegroup-tanowrt-base \
	${CORE_IMAGE_EXTRA_INSTALL} \
"

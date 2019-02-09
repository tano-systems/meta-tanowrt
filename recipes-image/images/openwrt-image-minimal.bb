# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com> and
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

PR = "tano3"

SUMMARY = "OpenWrt Minimal Complete Image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${TANO_OPENWRT_BASE}/LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

WKS_FILE = "openwrt-image.wks"
WKS_FILE_DEPENDS = "syslinux syslinux-native dosfstools-native mtools-native"

inherit openwrt-image
inherit rootfs-rm-boot-dir
inherit rootfs-var-in-tmpfs

IMAGE_FEATURES += "\
	empty-root-password \
	${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'wifi', '', d)} \
	${@bb.utils.contains('COMBINED_FEATURES', 'cgroup', 'cgroup', '', d)} \
"

CORE_IMAGE_BASE_INSTALL = '\
	packagegroup-core-boot \
	packagegroup-openwrt-minimal \
	\
	${MACHINE_EXTRA_RDEPENDS} \
	${CORE_IMAGE_EXTRA_INSTALL} \
'

IMAGE_INSTALL ?= "${CORE_IMAGE_BASE_INSTALL}"

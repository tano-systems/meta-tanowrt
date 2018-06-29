# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

SUMMARY = "OpenWrt Minimal Complete Image"
LICENSE = "MIT"

inherit openwrt-image
inherit rootfs-rm-boot-dir

CORE_IMAGE_BASE_INSTALL = '\
	packagegroup-core-boot \
	packagegroup-openwrt-minimal \
	\
	${MACHINE_EXTRA_RDEPENDS} \
	${CORE_IMAGE_EXTRA_INSTALL} \
'

IMAGE_INSTALL ?= "${CORE_IMAGE_BASE_INSTALL}"

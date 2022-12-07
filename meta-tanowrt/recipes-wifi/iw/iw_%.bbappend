#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
#
# nl80211 based CLI configuration utility for wireless devices
#
PR:append = ".tano0"

# Compile always with gcc
TOOLCHAIN = "gcc"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# Patches
SRC_URI += "\
	file://001-nl80211_h_sync.patch \
	file://120-antenna_gain.patch \
	file://130-survey-bss-rx-time.patch \
	file://200-reduce_size.patch \
"

CFLAGS += "\
	-DIW_FULL \
	-DCONFIG_LIBNL20 \
	-D_GNU_SOURCE \
	-flto \
"

EXTRA_OEMAKE += "\
	'IW_FULL=1' \
"

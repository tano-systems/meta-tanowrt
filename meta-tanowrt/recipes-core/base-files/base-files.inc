#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019-2021 Anton Kikin <a.kikin@tano-systems.com>
#

# 04.01.2021
# base-files: read all 3 bytes in get_magic_vfat() at once
OPENWRT_SRCREV = "81655e1450a80276db32c8a8e10de73a256bc664"
OPENWRT_BRANCH = "master"

INC_PR = "19"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI += "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

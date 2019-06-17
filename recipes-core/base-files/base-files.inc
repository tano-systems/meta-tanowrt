# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

# 12.06.2019
# f2fs-tools: fixup SPDX license
OPENWRT_SRCREV = "35a70d626239424fb47e4cc50b565da7622eb2a6"
OPENWRT_BRANCH = "master"

INC_PR = "8"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI += "\
	git://${GIT_OPENWRT_ORG_URL}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"


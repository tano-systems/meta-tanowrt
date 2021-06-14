#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019-2021 Anton Kikin <a.kikin@tano-systems.com>
#

# 14.06.2021
# base-files: fix typo in config_generate MAC check
OPENWRT_SRCREV = "946019637ec9192450c685a61c589a9cbef7637b"
OPENWRT_BRANCH = "master"

INC_PR = "20"

LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSES/GPL-2.0;md5=fbfe04ebfede83e097ba08389d089069"

SRC_URI += "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

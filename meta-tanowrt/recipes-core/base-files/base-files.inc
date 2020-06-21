# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>

# 18.06.2020
# base-files: gpio switch: add named GPIO support
OPENWRT_SRCREV = "c5bf9a8ced6567c6bd9da63c6ff4c4f2f87207fd"
OPENWRT_BRANCH = "master"

INC_PR = "16"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI += "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

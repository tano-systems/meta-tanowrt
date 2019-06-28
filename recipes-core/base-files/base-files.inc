# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

# 03.07.2019
# base-files: add network_get_uptime() to /lib/functions/network.sh
OPENWRT_SRCREV = "1d4c4cbd20580dd211431ab58460a6eebd5e021e"
OPENWRT_BRANCH = "master"

INC_PR = "9"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI += "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"


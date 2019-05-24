# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

# 05.02.2019
# base-files: add support for the new ar8xxx MIB counters settings
OPENWRT_SRCREV = "aac8b52184e727d8ba7a13917a9a3c8b82ec4aa2"
OPENWRT_BRANCH = "master"

INC_PR = "7"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI += "\
	${GIT_OPENWRT_ORG_URL}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"


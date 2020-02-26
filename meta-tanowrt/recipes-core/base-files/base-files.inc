# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>

# 26.02.2020
# ath79: add support for MikroTik RouterBOARD 922UAGS-5HPacD
OPENWRT_SRCREV = "8f93c05a591bd68e4d8eaa0a8468ce2263762004"
OPENWRT_BRANCH = "master"

INC_PR = "13"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI += "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

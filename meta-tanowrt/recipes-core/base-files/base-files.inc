#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
#           Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>
#

# 19.11.2020
# base-files: generated named bridge-vlan sections
OPENWRT_SRCREV = "8134c542e2e41d1156efe2a43d81304554a90540"
OPENWRT_BRANCH = "master"

INC_PR = "18"

LICENSE = "GPL-2.0+"
LIC_FILES_CHKSUM = " file://${WORKDIR}/openwrt/LICENSE;md5=b234ee4d69f5fce4486a80fdaf4a4263"

SRC_URI += "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git;name=openwrt;destsuffix=openwrt;branch=${OPENWRT_BRANCH} \
"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

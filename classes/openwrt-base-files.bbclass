# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

# Use this git SRCREV for all recipes that pull files out of openwrt repository

# 05.02.2019
# ath79: add support for TP-LINK Archer C7 v4 
OPENWRT_SRCREV = "12310f05b7b080b016ec515796be437f4cd30b62"
OPENWRT_BRANCH = "master"

PR_append = ".o6"

LICENSE_append = "&GPL-2.0+"
LIC_FILES_CHKSUM_append = " file://${S}/../git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI_append = "\
	${GIT_OPENWRT_ORG_URL}/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt;branch=${OPENWRT_BRANCH} \
"

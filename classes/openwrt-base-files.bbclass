# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

# Use this git SRCREV for all recipes that pull files out of openwrt repository

# 07.11.2018
# mt76: update to the latest version
OPENWRT_SRCREV = "4fd9a5667eab6a75d8c3b7a1510dfb354ecca969"
OPENWRT_BRANCH = "master"

PR_append = ".o4"

LICENSE_append = "&GPL-2.0+"
LIC_FILES_CHKSUM_append = " file://${S}/../git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI_append = "\
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt;branch=${OPENWRT_BRANCH} \
"


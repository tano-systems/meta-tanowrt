# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

# Use this git SRCREV for all recipes that pull files out of openwrt repository
# Equivalent to tag v18.06.0-rc2

# 01.08.2018
# sdk: bundle usbip userspace sources
OPENWRT_SRCREV = "4f3082583a0f08bf8329c2a6e754fbf4e0bea544"
OPENWRT_BRANCH = "openwrt-18.06"

PR_append = ".owrt1"

LICENSE_append = "&GPL-2.0+"
LIC_FILES_CHKSUM_append = " file://${S}/../git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI_append = "\
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt;branch=${OPENWRT_BRANCH} \
"


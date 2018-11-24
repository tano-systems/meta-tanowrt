# This file Copyright (C) 2015 Khem Raj <raj.khem@gmail.com> and
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

# Use this git SRCREV for all recipes that pull files out of openwrt repository

# 23.11.2018
# kernel: b53: register switch on probe
OPENWRT_SRCREV = "6680fab9474a8a2d66d5ef86e2c392abe0d2c62d"
OPENWRT_BRANCH = "master"

PR_append = ".o4"

LICENSE_append = "&GPL-2.0+"
LIC_FILES_CHKSUM_append = " file://${S}/../git/openwrt/LICENSE;md5=94d55d512a9ba36caa9b7df079bae19f "

SRC_URI_append = "\
	git://github.com/openwrt/openwrt.git;name=openwrt;destsuffix=git/openwrt;branch=${OPENWRT_BRANCH} \
"


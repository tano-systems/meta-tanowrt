# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano1"

DESCRIPTION = "OpenWrt uqmi utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/uqmi.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=20;md5=3f7041e5710007661d762bb6043a69c6"
SECTION = "base"
DEPENDS = "libubox json-c"

SRC_URI = "git://git.openwrt.org/project/uqmi.git \
          "

# 22.06.2017
# uqmi_add_command: fixed command argument assignment
SRCREV = "01944dd7089b15f55b463072e1b46f1144e8aab4"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

B = "${S}"

FILES_${PN}  += "${libdir}/*"

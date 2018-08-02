# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano1"

DESCRIPTION = "OpenWrt MBIM modem utility"
HOMEPAGE = "http://git.openwrt.org/?p=project/umbim.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://mbim.h;beginline=1;endline=13;md5=8c7ce85ebfe23634010c75c30c3eb223"
SECTION = "base"
DEPENDS = "libubox"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.openwrt.org/project/umbim.git \
          "

# 11.05.2016
# add radio_state set/query support
SRCREV = "29aaf43b097ee57f7aa1bb24341db6cc4148cbf3"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

FILES_${PN}  += "${libdir}/*"

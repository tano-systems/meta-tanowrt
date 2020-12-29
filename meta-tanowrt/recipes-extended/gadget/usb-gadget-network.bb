#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2019 Tano Systems. All Rights Reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
DESCRIPTION = "Init script to initialize network (RNDIS) usb gadget"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${TANOWRT_BASE}/LICENSE;md5=aed2cf5a7c273a7c2dcdbd491a3a8416"

PR = "tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "file://usb-gadget-network.init"

do_install() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/usb-gadget-network.init \
	                ${D}${sysconfdir}/init.d/usb-gadget-network
}

ALLOW_EMPTY_${PN} = "1"
FILES_${PN} = "${sysconfdir}/init.d/usb-gadget-network"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "usb-gadget-network"
TANOWRT_SERVICE_SCRIPTS_usb-gadget-network += "usb-gadget-network"
TANOWRT_SERVICE_STATE_usb-gadget-network-usb-gadget-network ?= "enabled"

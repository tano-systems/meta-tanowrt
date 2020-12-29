#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano5"

DEPENDS += "perl-native"

DESCRIPTION = "usbmode - usb_modeswitch replacement"
HOMEPAGE = "http://git.openwrt.org/?p=project/usbmode.git;a=summary"
LICENSE = "PD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/PD;md5=b3597d12946881e13cb3b548d1173851"
SECTION = "base"
DEPENDS = "libusb libubox"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/usbmode.git"

# 18.12.2017
# support PantechMode
SRCREV = "f40f84c27534159066c94dadc0c08e0b255c3e26"

SRC_URI += "\
	file://usbmode.init \
	file://usbmode.hotplug \
	file://data/12d1-1f16 \
"

USB_MODESWITCH_DATA_VERSION ?= "20170806"
USB_MODESWITCH_DATA_PATH = "usb-modeswitch-data-${USB_MODESWITCH_DATA_VERSION}"
USB_MODESWITCH_DATA_FILE = "${USB_MODESWITCH_DATA_PATH}.tar.bz2"

SRC_URI += "\
	http://www.draisberghof.de/usb_modeswitch/${USB_MODESWITCH_DATA_FILE} \
"

SRC_URI[md5sum] = "fb50d15b52e909d742dd16f0a9882316"
SRC_URI[sha256sum] = "ce413ef2a50e648e9c81bc3ea6110e7324a8bf981034fc9ec4467d3562563c2c"

S = "${WORKDIR}/git"

do_fetch[cleandirs] += "${WORKDIR}/data"

inherit cmake pkgconfig tanowrt-services

TANOWRT_SERVICE_PACKAGES = "usbmode"
TANOWRT_SERVICE_SCRIPTS_usbmode += "usbmode"
TANOWRT_SERVICE_STATE_usbmode-usbmode ?= "enabled"

do_configure_append() {
	if [ -d "${WORKDIR}/data" ]; then
		for filevar in ${WORKDIR}/data/*-*
		do
			[ -f "$filevar" ] || continue
			FILENAME=$(basename $filevar)
			NEWFILENAME=${FILENAME//-/:}
			rm "${WORKDIR}/${USB_MODESWITCH_DATA_PATH}/usb_modeswitch.d/${NEWFILENAME}"
			cp "${WORKDIR}/data/${FILENAME}" \
			   "${WORKDIR}/${USB_MODESWITCH_DATA_PATH}/usb_modeswitch.d/${NEWFILENAME}"
		done
	fi
}

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/hotplug.d/usb

	install -m 0755 ${WORKDIR}/usbmode.init ${D}${sysconfdir}/init.d/usbmode
	install -m 0644 ${WORKDIR}/usbmode.hotplug ${D}${sysconfdir}/hotplug.d/usb/20-usb_mode

	perl ${S}/convert-modeswitch.pl \
	     ${WORKDIR}/${USB_MODESWITCH_DATA_PATH}/usb_modeswitch.d/* \
	     > ${D}${sysconfdir}/usb-mode.json
}

FILES_${PN} += "${libdir}/*"

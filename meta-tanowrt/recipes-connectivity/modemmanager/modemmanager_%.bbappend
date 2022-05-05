#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2020 Tano Systems LLC. All rights reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
PR_append = ".tano1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RDEPENDS_${PN} += "dbus"

# Files
SRC_URI += "\
	file://25-modemmanager-net \
	file://25-modemmanager-tty \
	file://25-modemmanager-usb \
	file://modemmanager.common \
	file://modemmanager.init \
	file://modemmanager.proto \
"

PACKAGECONFIG ??= "mbim qmi"

EXTRA_OECONF += "\
	--without-polkit \
	--without-systemdsystemunitdir \
	--disable-rpath \
	--disable-gtk-doc \
"

#
# 	--without-udev \
#

do_install_append() {
	# Remove dirs
	rm -rf ${D}${libdir}/girepository-1.0
	rm -rf ${D}${datadir}/icons
	rm -rf ${D}${datadir}/dbus-1/interfaces

	install -d ${D}${datadir}/ModemManager
	install -m 0644 ${WORKDIR}/modemmanager.common ${D}${datadir}/ModemManager

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/modemmanager.init ${D}${sysconfdir}/init.d/modemmanager

	install -d ${D}${sysconfdir}/hotplug.d/usb
	install -m 0644 ${WORKDIR}/25-modemmanager-usb ${D}${sysconfdir}/hotplug.d/usb/

	install -d ${D}${sysconfdir}/hotplug.d/tty
	install -m 0644 ${WORKDIR}/25-modemmanager-tty ${D}${sysconfdir}/hotplug.d/tty/

	install -d ${D}${sysconfdir}/hotplug.d/net
	install -m 0644 ${WORKDIR}/25-modemmanager-net ${D}${sysconfdir}/hotplug.d/net/

	install -d ${D}${nonarch_base_libdir}/netifd/proto
	install -m 0755 ${WORKDIR}/modemmanager.proto ${D}${nonarch_base_libdir}/netifd/proto/modemmanager.sh
}

FILES_${PN} += "${nonarch_base_libdir}/netifd"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "modemmanager"
TANOWRT_SERVICE_SCRIPTS_modemmanager += "modemmanager"
TANOWRT_SERVICE_STATE_modemmanager-modemmanager ?= "enabled"

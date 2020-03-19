# This file Copyright (C) 2018, 2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano4"

DESCRIPTION = "Generic OpenWrt 3G/4G proto handler"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0;md5=801f80980d171dd6425610833a22dbe6"
SECTION = "net/misc"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://wwan.sh \
	file://wwan.usb \
	file://wwan.usbmisc \
	file://data/* \
"

inherit allarch

do_configure[noexec] = "1"
do_compile[noexec] = "1"
do_fetch[cleandirs] += "${WORKDIR}/data"

do_install_append() {
	install -dm 0755 ${D}/lib/netifd/proto
	install -m 0755 ${WORKDIR}/wwan.sh ${D}/lib/netifd/proto/wwan.sh

	install -dm 0755 ${D}${sysconfdir}/hotplug.d/usb
	install -m 0755 ${WORKDIR}/wwan.usb ${D}${sysconfdir}/hotplug.d/usb/00_wwan.sh

	install -dm 0755 ${D}${sysconfdir}/hotplug.d/usbmisc
	install -m 0755 ${WORKDIR}/wwan.usbmisc ${D}${sysconfdir}/hotplug.d/usbmisc/00_wwan.sh

	install -dm 0755 ${D}/lib/network/wwan

	#
	# In order to keep the GIT repo free of filenames with colons,
	# we name the files xxxx-yyyy and rename here after copying to the build directory
	#
	for filevar in ${WORKDIR}/data/*-*
	do
		[ -f "$filevar" ] || continue
		FILENAME=$(basename $filevar)
		NEWFILENAME=${FILENAME//-/:}
		cp "${WORKDIR}/data/${FILENAME}" \
		   "${D}/lib/network/wwan/${NEWFILENAME}"
	done
}

FILES_${PN} += "${sysconfidir}/ /lib/"

#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2020 Tano Systems LLC. All rights reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
PR_append = ".tano0"

EXTRA_OECONF += "\
	--disable-static \
	--disable-gtk-doc \
	--disable-gtk-doc-html \
	--disable-gtk-doc-pdf \
	--disable-silent-rules \
	--enable-firmware-update \
	--enable-introspection=no \
"

#
#	--enable-mbim-qmux \
#	--without-udev \
#	--without-udev-base-dir
#

FILES_${PN} = "${libdir} ${libexecdir}"

PACKAGES += "${PN}-utils"
FILES_${PN}-utils = "${bindir}/"
SUMMARY_${PN}-utils = "Utilities to talk to QMI enabled modems"
RDEPENDS_${PN}-utils = "${PN}"
PROVIDES += "qmi-utils"
RPROVIDES_${PN}-utils += "qmi-utils"

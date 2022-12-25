#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2020 Tano Systems LLC. All rights reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
PR:append = ".tano0"

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

FILES:${PN} = "${libdir} ${libexecdir}"

PACKAGES += "${PN}-utils"
FILES:${PN}-utils = "${bindir}/"
SUMMARY:${PN}-utils = "Utilities to talk to QMI enabled modems"
RDEPENDS:${PN}-utils = "${PN}"
PROVIDES += "qmi-utils"
RPROVIDES:${PN}-utils += "qmi-utils"

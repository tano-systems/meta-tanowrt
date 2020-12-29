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
	--enable-introspection=no \
"

#
#	--without-udev \
#	--without-udev-base-dir
#

FILES_${PN} = "${libdir} ${libexecdir}"

PACKAGES += "${PN}-utils"
FILES_${PN}-utils = "${bindir}/"
SUMMARY_${PN}-utils = "Utilities to talk to MBIM enabled modems"
RDEPENDS_${PN}-utils = "${PN}"
PROVIDES += "mbim-utils"
RPROVIDES_${PN}-utils += "mbim-utils"

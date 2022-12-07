#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano0"

do_install:append() {
	install -d ${D}${datadir}/fonts/truetype
	for i in *.ttf; do
		ln -s ${prefix}/share/fonts/ttf/${i} ${D}${prefix}/share/fonts/truetype/${i}
	done
}

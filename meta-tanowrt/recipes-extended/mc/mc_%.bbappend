#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano1"

do_install:append() {
	install -d ${D}${libexecdir}/mc/extfs.d
	touch ${D}${libexecdir}/mc/extfs.d/.keepdir
}

# Fix "cannot open /usr/libexec/mc/extfs.d directory" warning at mc start
FILES:${PN} += "${libexecdir}/mc/extfs.d/.keepdir"

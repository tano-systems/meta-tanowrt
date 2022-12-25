#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".swi0"

do_install:append() {
	rm -rf ${D}${sysconfdir}/init.d/dropbear.wrapper
}

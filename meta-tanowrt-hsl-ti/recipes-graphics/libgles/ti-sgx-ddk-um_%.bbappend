#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano0"

INSANE_SKIP:${PN}:remove = "dev-deps"
INSANE_SKIP:${PN}:remove = "dev-so"

do_install () {
	oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT}
	rm -rf ${D}${includedir}/GL
	chown -R root:root ${D}
}

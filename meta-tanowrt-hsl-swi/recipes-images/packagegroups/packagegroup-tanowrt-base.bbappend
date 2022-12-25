#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".swi0"

RDEPENDS:${PN}-tools:remove = "\
	cpulimit \
"

RDEPENDS:${PN}-luci:remove = "\
	luci-app-ledtrig-usbport \
"

RDEPENDS:${PN}:append = "\
	swi-initscripts \
	bsinfo-stub \
"

RDEPENDS:${PN}-tools:append = "\
	picocom \
"

# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".swi0"

RDEPENDS_${PN}-tools_remove = "\
	cpulimit \
"

RDEPENDS_${PN}-luci_remove = "\
	luci-app-ledtrig-usbport \
"

RDEPENDS_${PN}_append = "\
	swi-initscripts \
	bsinfo-stub \
"

RDEPENDS_${PN}-tools_append = "\
	picocom \
"

#
# SPDX-License-Identifier: MIT
#

do_install:append() {
	install -d ${D}${libdir}
	install -m 0644 ${B}/extensions/libiptext*.so ${D}${libdir}
}

FILES:${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP:${PN} = "dev-so"

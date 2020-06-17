# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

PR_append = ".tano1"

SRC_URI += "file://600-shared-libext.patch"

do_install_append() {
	install -d ${D}${libdir}
	install -m 0644 ${B}/extensions/libiptext*.so ${D}${libdir}
}

FILES_${PN} += "${libdir}/*.so"
FILES_SOLIBSDEV = ""
INSANE_SKIP_${PN} = "dev-so"

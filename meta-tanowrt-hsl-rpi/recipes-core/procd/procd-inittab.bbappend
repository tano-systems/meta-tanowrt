# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".rpi1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

do_install_rpi3() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab
}

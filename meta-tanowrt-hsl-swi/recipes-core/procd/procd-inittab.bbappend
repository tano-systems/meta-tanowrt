# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".swi0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

do_install_mangoh-green-wp7607() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab
}

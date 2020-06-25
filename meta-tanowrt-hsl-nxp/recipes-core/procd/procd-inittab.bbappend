# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR_append_ls1028ardb = ".nxp1"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

do_install_ls1028ardb() {
	install -d ${D}${sysconfdir}
	install -m 0644 ${WORKDIR}/inittab ${D}${sysconfdir}/inittab
}

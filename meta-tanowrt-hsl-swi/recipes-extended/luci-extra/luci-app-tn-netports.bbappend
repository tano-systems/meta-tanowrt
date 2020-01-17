# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".swi0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:"

# Files
SRC_URI += "\
	file://luci_netports.config \
"

do_install_append_mangoh-green-wp7607() {
	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/luci_netports.config ${D}${sysconfdir}/config/luci_netports
}

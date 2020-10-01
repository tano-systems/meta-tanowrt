# Copyright (c) 2020 Tano Systems LLC

PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "cronie"
TANOWRT_SERVICE_SCRIPTS_cronie += "crond"
TANOWRT_SERVICE_STATE_cronie-crond ?= "enabled"

do_install_append() {
	rm -rf ${D}/var
	rm -rf ${D}${sysconfdir}/sysconfig
	mkdir -p ${D}${sysconfdir}/crontabs
}

# SPOOL_DIR the directory where all the user cron tabs reside
EXTRA_OECONF += "SPOOL_DIR=${sysconfdir}/crontabs"

FILES_${PN} += "${sysconfdir}/crontabs"
CONFFILES_${PN} += "${sysconfdir}/crontabs/"

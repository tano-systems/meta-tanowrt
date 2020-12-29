#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# Files
SRC_URI += "\
	file://smstools3.conf \
	file://smstools3.init \
"

# Patches
SRC_URI += "\
	file://002-Makefile.patch \
	file://004-modem-processes.patch \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "smstools3"
TANOWRT_SERVICE_SCRIPTS_smstools3 += "smstools3"
TANOWRT_SERVICE_STATE_smstools3-smstools3 ?= "disabled"

#CFLAGS += "-D USE_ICONV"
CFLAGS += "-D NUMBER_OF_MODEMS=1"
CFLAGS += "-D DISABLE_INET_SOCKET"
CFLAGS += "-W -Wall"
CFLAGS += "-D_FILE_OFFSET_BITS=64"

do_install_append() {
	# Remove sysvinit script
	rm -rf ${D}${sysconfdir}/init.d/${INITSCRIPT_NAME}

	# Remove spool directories
	# It will be created by init script
	rm -rf ${D}${localstatedir}

	# Install procd init script
	install -m 0755 ${WORKDIR}/smstools3.init "${D}${sysconfdir}/init.d/smstools3"

	# Install configuration file
	install -m 644 ${WORKDIR}/smstools3.conf "${D}${sysconfdir}/smsd.conf"
}

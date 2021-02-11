#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano0"
SRCREV = "950f541889063df1043a0227a9715a57080cbd35"

do_install_append() {
	#
	# For compatibility with legacy applications
	# searching fw_printenv/fw_setenv in /sbin
	#
	install -d ${D}${base_sbindir}
	ln -sf ${bindir}/fw_printenv ${D}${base_sbindir}/fw_printenv
	ln -sf ${bindir}/fw_setenv ${D}${base_sbindir}/fw_setenv
}

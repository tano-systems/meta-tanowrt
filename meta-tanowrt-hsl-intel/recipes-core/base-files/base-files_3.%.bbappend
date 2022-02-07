#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

PR_append = ".intel0"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files/:"

INTEL_X86_PREINIT_FILES = "\
	file://preinit/01_sysinfo \
	file://preinit/02_load_x86_ucode \
	file://preinit/15_essential_fs_x86 \
	file://preinit/20_check_iso \
	file://preinit/79_move_config \
"

INTEL_X86_PREINIT_INSTALL = "0"
INTEL_X86_PREINIT_INSTALL:corei7-64-intel-common = "1"
INTEL_X86_PREINIT_INSTALL:core2-32-intel-common = "1"
INTEL_X86_PREINIT_INSTALL:skylake-64-intel-common = "1"

SRC_URI:append = " ${@oe.utils.conditional('INTEL_X86_PREINIT_INSTALL', '1', '${INTEL_X86_PREINIT_FILES}', '', d)}"

do_install_intel_x86_preinit() {
	if [ "${INTEL_X86_PREINIT_INSTALL}" = "1" ]; then
		install -dm 0755 ${D}/lib/preinit
		install -m 0644 ${WORKDIR}/preinit/01_sysinfo ${D}/lib/preinit/01_sysinfo
		install -m 0644 ${WORKDIR}/preinit/02_load_x86_ucode ${D}/lib/preinit/02_load_x86_ucode
		install -m 0644 ${WORKDIR}/preinit/15_essential_fs_x86 ${D}/lib/preinit/15_essential_fs_x86
		install -m 0644 ${WORKDIR}/preinit/20_check_iso ${D}/lib/preinit/20_check_iso
		install -m 0644 ${WORKDIR}/preinit/79_move_config ${D}/lib/preinit/79_move_config
	fi
}

do_install:append() {
	do_install_intel_x86_preinit
}

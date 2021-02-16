# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.

UBOOT_LOCALVERSION_append = "-${PR}"

# Always write localversion to .scmversion
do_compile_prepend() {
	echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
	echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
}

FILES_${PN} += "${sysconfdir}/fw_env.config"

inherit u-boot-envimage

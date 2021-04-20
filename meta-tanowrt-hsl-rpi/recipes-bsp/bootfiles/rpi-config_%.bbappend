#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano0"

RPI_CMDLINE ?= ""

do_deploy_append() {
	if [ -n "${RPI_CMDLINE}" ]; then
		echo "${RPI_CMDLINE}" > ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/cmdline.txt
	fi
}

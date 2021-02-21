# Copyright (C) 2021 Tano Systems LLC. All Rights Reserved.
# SPDX-License-Identifier: MIT

BIN_FW_SETENV="/usr/bin/fw_setenv"
BIN_FW_PRINTENV="/usr/bin/fw_printenv"

#
# $1 - variable
#
swupdate_bl_getenv() {
	local variable=${1}

	[ -x "${BIN_FW_PRINTENV}" ] || {
		${SWU_LOG_CMD} error "Can not find ${BIN_FW_PRINTENV}"
		exit 1
	}

	echo $(${BIN_FW_PRINTENV} -n ${variable} 2>/dev/null)
}

#
# $1 - variable
# $2 - value
#
swupdate_bl_setenv() {
	local variable=${1}
	local value=${2}

	[ -x "${BIN_FW_SETENV}" ] || {
		${SWU_LOG_CMD} error "Can not find ${BIN_FW_SETENV}"
		exit 1
	}

	${BIN_FW_SETENV} ${variable} ${value} 2>/dev/null
}

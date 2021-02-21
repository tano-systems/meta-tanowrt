# Copyright (C) 2021 Tano Systems LLC. All Rights Reserved.
# SPDX-License-Identifier: MIT

BIN_GRUB_EDITENV="/usr/bin/grub-editenv"

#
# $1 - variable
#
swupdate_bl_getenv() {
	local variable=${1}

	[ -x "${BIN_GRUB_EDITENV}" ] || {
		${SWU_LOG_CMD} error "Can not find ${BIN_GRUB_EDITENV}"
		exit 1
	}

	echo $(${BIN_GRUB_EDITENV} ${SWU_GRUBENV} list 2>/dev/null \
		| grep -e "^${variable}=" \
		| cut -d= -f 2)
}

#
# $1 - variable
# $2 - value
#
swupdate_bl_setenv() {
	local variable=${1}
	local value=${2}

	[ -x "${BIN_GRUB_EDITENV}" ] || {
		${SWU_LOG_CMD} error "Can not find ${BIN_GRUB_EDITENV}"
		exit 1
	}

	[ "${SWU_GRUBENV_RO}" = "1" ] && \
		mount "${SWU_GRUBENV_MOUNTPOINT}" -o remount,rw

	if [ "${value}" = "" ]; then
		${BIN_GRUB_EDITENV} ${SWU_GRUBENV} unset ${variable} 2>/dev/null
	else
		${BIN_GRUB_EDITENV} ${SWU_GRUBENV} set ${variable}=${value} 2>/dev/null
	fi

	[ "${SWU_GRUBENV_RO}" = "1" ] && \
		mount "${SWU_GRUBENV_MOUNTPOINT}" -o remount,ro
}

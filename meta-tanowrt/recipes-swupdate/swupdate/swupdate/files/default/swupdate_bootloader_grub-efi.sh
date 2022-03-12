# Copyright (C) 2021-2022 Tano Systems LLC. All Rights Reserved.
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
# Gets current grubenv mountpoint mount mode (rw or ro)
#
get_grubenv_mountpoint_mode() {
	local options
	local mode="unknown"
	options=$(grep " ${SWU_GRUBENV_MOUNTPOINT} " /proc/mounts | awk '{ print $4 }' | tr "," " ")
	for option in ${options}; do
		if [ "${option}" = "rw" ] || [ "${option}" = "ro" ]; then
			mode="${option}"
			break;
		fi
	done

	echo -n "${mode}"
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

	# Save current mount mode
	local mountmode=$(get_grubenv_mountpoint_mode)

	if [ "${SWU_GRUBENV_RO}" = "1" ]; then
		# Remount to RW mode if current mode is different
		[ "${mountmode}" != "rw" ] && \
			mount "${SWU_GRUBENV_MOUNTPOINT}" -o remount,rw
	fi

	if [ "${value}" = "" ]; then
		${BIN_GRUB_EDITENV} ${SWU_GRUBENV} unset ${variable} 2>/dev/null
	else
		${BIN_GRUB_EDITENV} ${SWU_GRUBENV} set ${variable}=${value} 2>/dev/null
	fi

	if [ "${SWU_GRUBENV_RO}" = "1" ]; then
		# Remount to RO mode if initial mode is not RW
		[ "${mountmode}" != "rw" ] && \
			mount "${SWU_GRUBENV_MOUNTPOINT}" -o remount,ro
	fi
}

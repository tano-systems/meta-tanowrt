# Copyright (C) 2021 Tano Systems LLC. All Rights Reserved.
# SPDX-License-Identifier: MIT

. /usr/lib/swupdate/swupdate_config

#
# Load bootloader support script
#
if [ -f "/usr/lib/swupdate/swupdate_bootloader_${SWU_BOOTLOADER}.sh" ]; then
	. /usr/lib/swupdate/swupdate_bootloader_${SWU_BOOTLOADER}.sh
else
	${SWU_LOG_CMD} error "Can not find bootloader support script"
	exit 1
fi

#
# Common get/set functions
#
swupdate_get_swu_upgrade_available() {
	echo $(swupdate_bl_getenv "swu_upgrade_available" 2>/dev/null)
}

swupdate_set_swu_upgrade_available() {
	swupdate_bl_setenv "swu_upgrade_available" "${1}"
}

swupdate_get_swu_ustate() {
	echo $(swupdate_bl_getenv "swu_ustate" 2>/dev/null)
}

swupdate_get_swu_clear_overlay() {
	echo $(swupdate_bl_getenv "swu_clear_overlay" 2>/dev/null)
}

swupdate_set_swu_clear_overlay() {
	swupdate_bl_setenv "swu_clear_overlay" "${1}"
}

swupdate_get_swu_clear_overlay_force() {
	echo $(swupdate_bl_getenv "swu_clear_overlay_force" 2>/dev/null)
}

swupdate_set_swu_clear_overlay_force() {
	swupdate_bl_setenv "swu_clear_overlay_force" "${1}"
}

swupdate_get_swu_state() {
	echo $(swupdate_bl_getenv "swu_state" 2>/dev/null)
}

swupdate_set_swu_state() {
	swupdate_bl_setenv "swu_state" "${1}"
}

swupdate_get_swu_active() {
	echo $(swupdate_bl_getenv "swu_active" 2>/dev/null)
}

swupdate_set_swu_bootcount() {
	swupdate_bl_setenv "swu_bootcount" "${1}"
}

#
# This must be called at last init stage
#
swupdate_init_boot_done() {
	local swu_upgrade_available=$(swupdate_get_swu_upgrade_available)

	if [ "${swu_upgrade_available}" = "1" ]; then
		swupdate_set_swu_bootcount "0"
		swupdate_set_swu_upgrade_available "0"
		swupdate_set_swu_state "ok"

		${SWU_LOG_CMD} notice "Firmware upgrade successfully done"

		local swu_clear_overlay=$(swupdate_get_swu_clear_overlay)
		if [ "${swu_clear_overlay}" = "1" ]; then
			${SWU_LOG_CMD} notice "Requested overlay clearing. Rebooting now..."
			reboot -f
		fi
	fi
}

swupdate_preinit_clear_overlay() {
	swupdate_preinit_hook_clear_overlay
	swupdate_set_swu_clear_overlay "0"
	swupdate_set_swu_clear_overlay_force "0"
}

#
# Called in preinit stage before overlay is mounted
#
swupdate_preinit_before_overlay() {
	local swu_state=$(swupdate_get_swu_state)

	if [ "${swu_state}" = "ok" ]; then
		local swu_clear_overlay=$(swupdate_get_swu_clear_overlay)
		if [ "${swu_clear_overlay}" = "1" ]; then
			swupdate_preinit_clear_overlay
		fi
	elif [ "${swu_state}" != "" ]; then
		local swu_clear_overlay_force=$(swupdate_get_swu_clear_overlay_force)
		if [ "${swu_clear_overlay_force}" = "1" ]; then
			swupdate_preinit_clear_overlay
		fi
	fi

	boot_run_hook swupdate_preinit_before_overlay
}

#
# Set UCI state
#
swupdate_uci_save_state() {
	local swu_state="$(swupdate_get_swu_state)"
	local uci_state="$(uci -q get swupdate.state.state)"

	[ -z "${swu_state}" ] && swu_state="ok"

	if [ "$(uci -q get swupdate.state)" != "internal" ]; then
		uci -q set swupdate.state="internal"
	fi

	if [ "${swu_state}" = "failed" ]; then
		if [ "${uci_state}" != "${swu_state}" ]; then
			uci -q set swupdate.state.state="${swu_state}"
			uci -q set swupdate.state.user_confirmed="0"
			uci -q commit swupdate
		fi
	else
		if [ "${uci_state}" != "${swu_state}" ]; then
			uci -q set swupdate.state.state="${swu_state}"
			uci -q commit swupdate
		fi
	fi
}

#
# Called in preinit stage after overlay is mounted
#
swupdate_preinit() {
	swupdate_uci_save_state
	boot_run_hook swupdate_preinit
}

#
# $1 - 1/0
#
swupdate_set_clear_overlay() {
	swupdate_set_swu_clear_overlay "${1}"
	swupdate_set_swu_clear_overlay_force "0"
}

#
# HOOKS
#

#
# Reset (cleanup) overlay filesystem in preinit stage
# when it is NOT mounted (no reboot is required)
#
swupdate_clear_overlay_ubivol() {
	local mtd_partition="rootfs_data"
	local ubi_volume="rootfs_data"

	[ -f "/tmp/overlay_partition" ] && mtd_partition=$(cat /tmp/overlay_partition)
	[ -f "/tmp/overlay_volume" ] && ubi_volume=$(cat /tmp/overlay_volume)

	. /lib/upgrade/nand.sh

	local ubi_device=$(nand_find_ubi ${mtd_partition})
	local ubi_vol_device=$(nand_find_volume ${ubi_device} ${ubi_volume})

	/usr/sbin/ubiupdatevol /dev/${ubi_vol_device} --truncate > /dev/null 2>&1
}

swupdate_clear_overlay_blkdev() {
	local partition="rootfs_data"

	[ -f "/tmp/overlay_partition" ] && partition=$(cat /tmp/overlay_partition)

	local OVERLAY_INFO=$(block info | grep "LABEL=\"${partition}\"")
	local OVERLAY_DEVICE=$(echo "${OVERLAY_INFO}" | awk -F : '{ print $1 }')

	if [ -z "${OVERLAY_DEVICE}" ]; then
		${SWU_LOG_CMD} error \
			"Cannot find block device for overlay partition \"${partition}\""
		return 1
	fi

	mkfs.${SWU_OVERLAY_FS} -f "${OVERLAY_DEVICE}" > /dev/null 2>&1
	if [ "$?" != "0" ]; then
		${SWU_LOG_CMD} error \
			"Failed to clear overlay filesystem on partition \"${partition}\""
		return 1
	fi
}

swupdate_preinit_hook_clear_overlay() {
	[ -x /sbin/factory-reset ] && /sbin/factory-reset --no-reboot --only-scripts

	if [ "${SWU_OVERLAY_CONTAINER}" = "ubivol" ]; then
		swupdate_clear_overlay_ubivol
	elif [ "${SWU_OVERLAY_CONTAINER}" = "blkdev" ]; then
		swupdate_clear_overlay_blkdev
	else
		${SWU_LOG_CMD} error \
			"Unsupported SWU_OVERLAY_CONTAINER '${SWU_OVERLAY_CONTAINER}'"
	fi
}

#
# Get active system number from bootloader
# environment variables
#
swupdate_get_active_system_bootloader() {
	local swu_active=$(swupdate_get_swu_active)

	if [ "${swu_active}" = "0" -o "${swu_active}" = "1" ]; then
		echo "${swu_active}"; return 0
	fi

	echo "0"
}

#
# Get active system number from /proc/cmdline
#
# /proc/cmdline must have following options:
#
#   - for active system A:
#        "rootfs_partition=<system_a_part_name>" or/and "swu_active=0"
#
#   - for active system B:
#        "rootfs_partition=<system_b_part_name>" or/and "swu_active=1"
#
swupdate_get_active_system_cmdline() {
	for i in $(cat /proc/cmdline); do
		case "$i" in
			swu_active=*)
				local ACTIVE=$(echo ${i#swu_active=})
				if [ "${ACTIVE}" = "0" -o "${ACTIVE}" = "1" ]; then
					echo "${ACTIVE}"; return 0
				fi
				;;

			rootfs_partition=*)
				local PART=$(echo ${i#rootfs_partition=})
				if [ "${PART}" = "${SWU_SYSTEM_A_PART}" ]; then
					echo "0"; return 0
				elif [ "${PART}" = "${SWU_SYSTEM_B_PART}" ]; then
					echo "1"; return 0
				fi
				;;
		esac
	done

	return 1
}

#
# Get active system number
#
swupdate_get_active_system() {
	local system

	system="$(swupdate_get_active_system_cmdline)"
	[ "$?" = "0" ] || system="$(swupdate_get_active_system_bootloader)"

	echo "${system}"
}

#
# Include user scripts (only from ROM storage)
#
if [ -d "/rom/usr/lib/swupdate/swupdate.d" ]; then
	for include in /rom/usr/lib/swupdate/swupdate.d/*; do
		. ${include}
	done
fi

#!/bin/sh
#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC. All Rights Reserved.
#
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# Factory reset script
# Version 1.0.0
#
# shellcheck shell=bash
#

# -----------------------------------------------------------------------------

RESET_SCRIPTS_DIR="/lib/factory-reset.d"

export REBOOT=1
export HELP=0
export CLEAR_OVERLAY=1

# -----------------------------------------------------------------------------

log_debug() { :; }

do_run_reset_scripts() {
	local action="$1"

	log_debug "Running scripts from ${RESET_SCRIPTS_DIR} with $action action..."
	(
		cd ${RESET_SCRIPTS_DIR} 2>/dev/null || return 0
		scripts="$(ls)"
		[ -z "$scripts" ] && return 0
		for script in $scripts; do
			if [ -x "$script" ]; then
				log_debug "${script}: Running..."
				"./${script}" "${action}" >/dev/null 2>/dev/null
				log_debug "${script}: Returned $?"
			else
				log_debug "${script}: Skipped (no executable permission)"
			fi
		done
	)
}

# -----------------------------------------------------------------------------

# Parse options
while [ -n "$1" ]; do
	case "$1" in
		-n|--no-reboot) export REBOOT=0;;
		-h|--help) export HELP=1; break;;
		-s|--only-scripts) export CLEAR_OVERLAY=0;;
		-*)
			echo "Invalid option: $1" >&2
			exit 1
			;;
		*)
			break
			;;
	esac
	shift;
done

[ ${HELP} -gt 0 ] && {
	cat <<EOF
Usage: $0 [<option>...]

option:
	-n | --no-reboot     Do not reboot.
	-s | --only-scripts  Do not clear overlay filesystem.
	                     Run only user custom scripts from ${RESET_SCRIPTS_DIR}.
	-h | --help          Display this help.

EOF
	exit 1
}

# Run custom user scripts
do_run_reset_scripts "reset"

if [ ${CLEAR_OVERLAY} -gt 0 ]; then
	# Reset overlay and reboot if REBOOT > 0
	if [ ${REBOOT} -gt 0 ]; then
		log_debug "Clearing overlay and rebooting..."
		/sbin/jffs2reset -r -y >/dev/null 2>/dev/null
	else
		log_debug "Clearing overlay without rebooting..."
		/sbin/jffs2reset -y >/dev/null 2>/dev/null
	fi
else
	[ ${REBOOT} -gt 0 ] && {
		log_debug "Rebooting..."
		reboot
	}
fi

# -----------------------------------------------------------------------------

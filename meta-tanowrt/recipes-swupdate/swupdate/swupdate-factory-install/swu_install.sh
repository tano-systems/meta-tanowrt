#!/bin/sh
#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021-2022 Tano Systems LLC. All Rights Reserved.
#
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
# SWUPDATE factory installation script
# Version 1.2.0
#
# shellcheck shell=bash
# shellcheck disable=SC3043
#

#
# Execution order (all tags are executed only if founded):
#
# 0. global
# 1. verify
#    1.1. mtd_required
#    1.2. blkdev_required
# 2. preinstall
#    2.1. mounts
#    2.2. preexecute
#    2.3. mtd_*
#         2.3.1. mtd_erase
#         2.3.2. mtd_write
#         2.3.3. mtd_ubiformat
#         2.3.4. mtd_ubimkvol
#         2.3.5. mtd_ubiupdatevol
#    2.4. blkdev_*
#         2.4.1. blkdev_erase
#         2.4.2. blkdev_parts
#         2.4.3. blkdev_write
#         2.4.4. blkdev_mkfs
#    2.5. postexecute
# 3. install
#    3.1. mounts
#    3.2. preexecute
#    3.3. images
#    3.4. postexecute
# 4. postinstall
#    4.1. preexecute
#    4.2. mtd_*
#         4.2.1. mtd_erase
#         4.2.2. mtd_write
#         4.2.3. mtd_ubiupdatevol
#    4.3. blkdev_*
#         4.3.1. blkdev_erase
#         4.3.2. blkdev_write
#    4.4. postexecute
#

# -----------------------------------------------------------------------------

# shellcheck disable=SC1091
. /usr/share/libubox/jshn.sh
. /lib/functions.sh
. /lib/upgrade/nand.sh

# Read SWU_CONF and other SWUPDATE-related variables
. /usr/lib/swupdate/swupdate_config

# -----------------------------------------------------------------------------

# Path to the installation JSON
SWU_INSTALL_JSON=${SWU_INSTALL_JSON:-swu_install.json}

# Path to the board specific hooks script
SWU_INSTALL_BOARD=${SWU_INSTALL_BOARD:-./swu_install.board}

DRY_RUN=${DRY_RUN:-1}
ENABLE_LAST_ACTION=${ENABLE_LAST_ACTION:-1}
LAST_ACTION=${LAST_ACTION:-reboot}
LAST_ACTION_DELAY=${LAST_ACTION_DELAY:-5}
UTF=${UTF:-1}
PSPLASH=${PSPLASH:-1}
REMOTE_DOWNLOADER=${REMOTE_DOWNLOADER:-wget}

STAGES_ORDER="\
	global:do_global_config \
	verify \
	preinstall \
	install \
	postinstall \
"

STAGES_VERIFY_ORDER="\
	mtd_required \
	blkdev_required \
"

STAGES_PREINSTALL_ORDER="\
	mounts \
	preexecute:do_execute \
	mtd_erase \
	mtd_write \
	mtd_ubiformat \
	mtd_ubimkvol \
	mtd_ubiupdatevol \
	blkdev_erase \
	blkdev_parts \
	blkdev_mkfs \
	blkdev_write \
	postexecute:do_execute \
"

STAGES_INSTALL_ORDER="\
	mounts \
	preexecute:do_execute \
	images \
	postexecute:do_execute \
"

STAGES_POSTINSTALL_ORDER="\
	preexecute:do_execute \
	mtd_erase \
	mtd_write \
	mtd_ubiupdatevol \
	blkdev_erase \
	blkdev_write \
	postexecute:do_execute \
"

BIN_SWUPDATE="/usr/bin/swupdate"
BIN_MOUNT="/bin/mount"
BIN_MKDIR="/bin/mkdir"
BIN_FLASH_ERASE="/usr/sbin/flash_erase"
BIN_UBIFORMAT="/usr/sbin/ubiformat"
BIN_UBIATTACH="/usr/sbin/ubiattach"
BIN_UBIDETACH="/usr/sbin/ubidetach"
BIN_UBIMKVOL="/usr/sbin/ubimkvol"
BIN_UBIUPDATEVOL="/usr/sbin/ubiupdatevol"
BIN_NANDWRITE="/usr/sbin/nandwrite"
BIN_DD="/bin/dd"
BIN_SGDISK="/usr/sbin/sgdisk"
BIN_PARTED="/usr/sbin/parted"
BIN_PARTPROBE="/usr/sbin/partprobe"
BIN_CURL="/usr/bin/curl"
BIN_WGET="/usr/bin/wget"
BIN_PSPLASH="/usr/bin/psplash"
BIN_UCI="/usr/bin/uci"

# -----------------------------------------------------------------------------

PSPLASH_INIT_SCRIPTS="/etc/psplash-init.d"
PSPLASH_TMPDIR="/tmp/psplash-swu-install"
PSPLASH_FIFO="${PSPLASH_TMPDIR}/psplash_fifo"

swu_psplash_progress() {
	[ "${PSPLASH}" = "0" ] && return 0
	local progress="${1}"
	if [ -p "${PSPLASH_FIFO}" ]; then
		echo "PROGRESS ${progress}" > "${PSPLASH_FIFO}"
	fi
}

swu_psplash_msg() {
	[ "${PSPLASH}" = "0" ] && return 0
	local msg="${1}"
	if [ -p "${PSPLASH_FIFO}" ]; then
		echo "MSG ${msg}" > "${PSPLASH_FIFO}"
	fi
}

swu_psplash_start() {
	[ "${PSPLASH}" = "0" ] && return 0

	if [ ! -x "${BIN_PSPLASH}" ]; then
		PSPLASH=0
	else
		local PSPLASH_PID

		# Kill existing psplash instance if running
		PSPLASH_PID=$(pgrep "psplash")
		[ -n "${PSPLASH_PID}" ] && kill -9 "${PSPLASH_PID}"

		# Run custom init scripts
		if [ -d "${PSPLASH_INIT_SCRIPTS}" ]; then
			local files
			files=$(ls -1 "${PSPLASH_INIT_SCRIPTS}")
			for file in $files; do
				# shellcheck disable=SC1090
				source "${PSPLASH_INIT_SCRIPTS}/$file"
			done
		fi

		if [ -x "${BIN_UCI}" ]; then
			${BIN_UCI} -q set psplash.config.startup_msg='Initializing...'
			${BIN_UCI} -q commit psplash
		fi

		# Run psplash
		mkdir -p ${PSPLASH_TMPDIR}
		TMPDIR="${PSPLASH_TMPDIR}" ${BIN_PSPLASH} 2>/dev/null &

		wait_for_file "${PSPLASH_FIFO}" 2 || {
			# Failed to start psplash, disable it
			PSPLASH=0
		}
	fi
}

# -----------------------------------------------------------------------------

SWU_LOG=1
SWU_COUNT_PROGRESS=1
SWU_INSTALL_ACTIONS=0
SWU_INSTALL_PROGRESS=0

swu_install_progress_init() {
	swu_psplash_start

	if [ -p "${PSPLASH_FIFO}" ]; then
		swu_psplash_msg "Factory SWU installation in progress..."
		swu_psplash_progress "0"
	fi
}

swu_install_progress() {
	if [ "${SWU_COUNT_PROGRESS}" = "1" ]; then
		SWU_INSTALL_ACTIONS=$((SWU_INSTALL_ACTIONS + 1))
	else
		SWU_INSTALL_PROGRESS=$((SWU_INSTALL_PROGRESS + 1))

		local PSPLASH_PROGRESS=$((SWU_INSTALL_PROGRESS * 100 / SWU_INSTALL_ACTIONS))
		swu_psplash_progress "${PSPLASH_PROGRESS}"
	fi
}

# -----------------------------------------------------------------------------

# List of files/dirs for removing at exit
at_exit_rm=""

# -----------------------------------------------------------------------------

SWU_JSON_PATH=""

swu_json_select() {
	if [ "${1}" = ".." ]; then
		# shellcheck disable=SC2001
		SWU_JSON_PATH=$(echo "${SWU_JSON_PATH}" | sed 's![/][^/]*$!!')
	else
		SWU_JSON_PATH="${SWU_JSON_PATH}/${1}"
	fi

	json_select "$1"
}

# -----------------------------------------------------------------------------

# Empty board specific hooks
# Can be redefined in SWU_INSTALL_BOARD script
swu_install_hook_heartbeat() { :; }
swu_install_hook_failure() { :; }
swu_install_hook_done() { :; }

# -----------------------------------------------------------------------------

swu_eval_value() {
	eval echo "$1"
}

# -----------------------------------------------------------------------------

VT_COLOR_BLUE='\033[0;94m'
VT_COLOR_GREEN='\033[0;92m'
VT_COLOR_RED='\033[0;91m'
VT_COLOR_YELLOW='\033[0;93m'
VT_COLOR_WHITE='\033[0;97m'
VT_RESET='\033[0m'

swu_echo_repeat() {
	local char=$1
	local num=$2
	v=$(printf "%-${num}s" "")
	echo -n "${v// /${char}}"
}

swu_echo_box() {
	local str="$*"
	local len="${#str}"

	local LTC="┌"
	local RTC="┐"
	local LBC="└"
	local RBC="┘"
	local HL="─"
	local VL="│"

	if [ "${UTF}" = "0" ]; then
		# Use ASCII chars for box drawing
		LTC="+"; HL="-"; RTC="+"
		LBC="+"; VL="|"; RBC="+"
	fi

	echo -n "${LTC}"; swu_echo_repeat "${HL}" $((len + 4)); echo "${RTC}"
	echo -n "${VL}";  swu_echo_repeat " "     $((len + 4)); echo "${VL}"
	echo    "${VL}  ${str}  ${VL}"
	echo -n "${VL}";  swu_echo_repeat " "     $((len + 4)); echo "${VL}"
	echo -n "${LBC}"; swu_echo_repeat "${HL}" $((len + 4)); echo "${RBC}"
}

swu_log_error() {
	echo -e "${VT_COLOR_RED}ERROR @ ${SWU_JSON_PATH}: $*${VT_RESET}"
}

swu_log_fatal() {
	echo -e "${VT_COLOR_RED}"
	swu_echo_box "FATAL ERROR: $*"
	echo -e "${VT_RESET}"
}

swu_log_success_box() {
	[ "${SWU_LOG}" = "0" ] && return 0
	echo -e "${VT_COLOR_GREEN}"
	swu_echo_box "SUCCESS: $*"
	echo -e "${VT_RESET}"
}

swu_log_section() {
	[ "${SWU_LOG}" = "0" ] && return 0

	echo -e "${VT_COLOR_WHITE}"
	swu_echo_box "$*"
	echo -n -e "${VT_RESET}"
}

swu_log_subsection() {
	[ "${SWU_LOG}" = "0" ] && return 0

	local filler="═"
	[ "${UTF}" = "0" ] && filler="="
	echo -e "${VT_COLOR_WHITE}"
	echo "$*"
	swu_echo_repeat "${filler}" 80
	echo -e "${VT_RESET}"
}

#
# $1 - param name
# $2 - param value
#
swu_log_param() {
	[ "${SWU_LOG}" = "0" ] && return 0
	swu_log_info "* ${1}: ${2}"
}

swu_log_action() {
	[ "${SWU_LOG}" = "0" ] && return 0

	local filler="─"
	[ "${UTF}" = "0" ] && filler="-"
	echo -e "${VT_COLOR_BLUE}"
	echo "$*"
	swu_echo_repeat "${filler}" 60
	echo -e "${VT_RESET}"
}

swu_log_info() {
	[ "${SWU_LOG}" = "0" ] && return 0
	echo -e "${VT_COLOR_WHITE}$*${VT_RESET}"
}

swu_log_warning() {
	[ "${SWU_LOG}" = "0" ] && return 0
	echo -e "${VT_COLOR_YELLOW}WARNING @ ${SWU_JSON_PATH}: $*${VT_RESET}"
}

swu_log_notice() {
	[ "${SWU_LOG}" = "0" ] && return 0
	echo -e "${VT_COLOR_BLUE}$*${VT_RESET}"
}

swu_log_success() {
	[ "${SWU_LOG}" = "0" ] && return 0
	echo -e "${VT_COLOR_GREEN}$*${VT_RESET}"
}

# -----------------------------------------------------------------------------

check_binaries() {
	local status=0

	for BIN in "$@"; do
		if [ ! -x "${BIN}" ]; then
			swu_log_error "Executable '${BIN}' not exists"
			status=1
		fi
	done

	return ${status}
}

# -----------------------------------------------------------------------------

#
# $1 - device
# $2 - timeout (seconds)
#
wait_for_file() {
	local device=${1}
	local timeout=${2}
	local start
	
	start=$(date +%s)
	while true; do
		test -e "${device}" && return 0
		sleep 1
		local now
		now=$(date +%s)
		local waited=$((now - start))
		if [ "${waited}" -gt "${timeout}" ]; then
			return 1
		fi
	done
}

# -----------------------------------------------------------------------------

#
# $1 - command with arguments
# $2 - ignore exit code (default 0)
# $3 - required exit code (default 0)
#
bin_execute() {
	local command="${1}"
	local ignore_exit_code="${2:-0}"
	local required_exit_code="${3:-0}"

	local cmd_exit_code

	swu_log_info "Executing command '${command}'..."

	if [ "${SWU_COUNT_PROGRESS}" = "1" ]; then
		swu_log_success "Executed '${command}' with exit code ${required_exit_code}" \
			"(verify simulation)"
		return "${required_exit_code}"
	else
		eval "${command}"
		cmd_exit_code=$?
	fi

	if [ "${ignore_exit_code}" = "0" ]; then
		if [ "${cmd_exit_code}" != "${required_exit_code}" ]; then
			swu_log_error "Execution of '${command}' failed with" \
				"code ${cmd_exit_code} (required ${required_exit_code})"
			return 1
		else
			swu_log_success "Executed '${command}' with exit code ${cmd_exit_code}"
		fi
	else
		swu_log_success "Executed '${command}' with exit code ${cmd_exit_code} (ignored)"
		return "${cmd_exit_code}"
	fi

	return 0
}

# -----------------------------------------------------------------------------

UBI_DEFAULT_VID_HDR_OFFSET=""
UBI_DEFAULT_SUB_PAGE_SIZE=""

do_global_config_ubi() {
	swu_log_subsection "UBI configuration"

	json_get_var UBI_DEFAULT_VID_HDR_OFFSET \
		"vid_hdr_offset" "${UBI_DEFAULT_VID_HDR_OFFSET}"

	json_get_var UBI_DEFAULT_SUB_PAGE_SIZE \
		"sub_page_size" "${UBI_DEFAULT_SUB_PAGE_SIZE}"

	swu_log_param "Default VID header offset" "${UBI_DEFAULT_VID_HDR_OFFSET:-not specified}"
	swu_log_param "Default sub-page size" "${UBI_DEFAULT_SUB_PAGE_SIZE:-not specified}"
}

do_global_config_kernel() {
	swu_log_subsection "Kernel configuration"

	local printk
	json_get_var printk "printk" ""

	swu_log_param "Kernel logging level" "${printk:-not specified}"

	if [ -n "${printk}" ]; then
		[ "${SWU_COUNT_PROGRESS}" = "0" ] && sysctl -w kernel.printk="${printk}" > /dev/null
	fi
}

do_global_config() {
	local global_keys
	json_get_keys global_keys

	swu_log_section "Global configuration"

	local action=""
	json_get_var action "last_action"

	if [ -n "${action}" ]; then
		if [ "${action}" != "reboot" ] && \
		   [ "${action}" != "halt" ] && \
		   [ "${action}" != "poweroff" ]; then
			swu_log_warning "Invalid last action value '${action}'." \
				"Using default '${LAST_ACTION}'"
		else
			LAST_ACTION="${action}"
		fi
	fi

	local action_delay=""
	json_get_var action_delay "last_action_delay"

	if [ -n "${action_delay}" ]; then
		LAST_ACTION_DELAY="${action_delay}"
	fi

	swu_log_param "Last action" "${LAST_ACTION}"
	swu_log_param "Last action delay" "${LAST_ACTION_DELAY} seconds"

	for key in ${global_keys}; do
		if json_is_a "${key}" object; then
			local config_func="do_global_config_${key}"

			if ! eval "type ${config_func}" 2>/dev/null >/dev/null; then
				swu_log_error "Function '${config_func}' is not declared"
				return 1
			fi

			swu_json_select "${key}"
				${config_func} || return $?
			swu_json_select ..
		fi
	done

	return 0
}

#
# $1 - order
#
execute_substages_funcs() {
	local substage
	local order="${1}"
	
	# shellcheck disable=SC2034
	local keys
	json_get_keys keys

	for substage in ${order}; do
		local substage_name="${substage%:*}"
		local substage_func="${substage#*:}"

		if [ "${substage_name}" = "${substage_func}" ]; then
			substage_func="do_${substage_name}"
		fi

		if list_contains keys "${substage_name}"; then
			if ! eval "type ${substage_func}" 2>/dev/null >/dev/null; then
				swu_log_error "Function '${substage_func}' is not declared"
				return 1
			fi

			swu_json_select "${substage_name}"
				${substage_func} || return $?
			swu_json_select ..
		fi
	done
}

#
# $1 - variable for local image file path
#
get_image_url() {
	local _image_source
	local _image_url

	json_get_var _image_source "image_source" "local"

	if [ "${_image_source}" != "local" ] && \
	   [ "${_image_source}" != "remote" ]; then
		swu_log_error "Unsupported image source '${_image_source}'"
		return 1
	fi

	swu_log_param "Image source" "${_image_source}"

	json_get_var _image_url "image_url" ""
	if [ "${_image_url}" = "" ]; then
		swu_log_error "No 'image_url' specified"
		return 1
	fi

	swu_log_param "Image URL" "${_image_url}"

	if [ "${_image_source}" = "local" ]; then
		if [ "${SWU_COUNT_PROGRESS}" = "0" ] && [ ! -f "${_image_url}" ]; then
			swu_log_error "Can not find local file '${_image_url}'"
			return 1
		fi
	elif [ "${_image_source}" = "remote" ]; then
		local tmpfile
		tmpfile=$(mktemp -t)
		append at_exit_rm "${tmpfile}"

		if [ "${REMOTE_DOWNLOADER}" = "curl" ]; then
			check_binaries ${BIN_CURL} || return $?
			bin_execute "${BIN_CURL} ${_image_url} --output ${tmpfile}" || return $?
		elif [ "${REMOTE_DOWNLOADER}" = "wget" ]; then
			check_binaries ${BIN_WGET} || return $?
			bin_execute "${BIN_WGET} -O ${tmpfile} ${_image_url}" || return $?
		else
			swu_log_error "No available downloader for remote image source"
			return 1
		fi

		swu_install_progress

		_image_url="${tmpfile}"
		swu_log_param "Local image URL" "${_image_url}"
	fi

	export -- "${1}"="${_image_url}"
}

#
# $1 - mtd name
# $2 - var to storing index value
#
get_mtdindex() {
	local _mtdindex
	if [ "${SWU_COUNT_PROGRESS}" = "1" ]; then
		_mtdindex="XXX"
	else
		_mtdindex=$(find_mtd_index "${1}" 2> /dev/null)
		if [ -z "${_mtdindex}" ]; then
			swu_log_error "MTD device '${1}' not found"
			return 1
		fi
	fi

	export -- "${2}"="${_mtdindex}"
}

#
# $1 - block device full path
#
blkdev_validate() {
	if [ -z "${1}" ]; then
		swu_log_error "No 'device' is specified"
		return 1
	fi

	[ "${SWU_COUNT_PROGRESS}" = "1" ] && return 0

	if [ ! -b "${1}" ]; then
		swu_log_error "Specified invalid block device '${1}'"
		return 1
	fi
}

#
# $1 - block device full path
# $2 - var for storing block device size in bytes
#
get_blkdev_size() {
	local _size

	_size=$(cat /sys/class/block/${1#/dev/}/size)
	_size=$(($_size * 512))

	export -- "${2}"="${_size}"
}

#
# $1 - mtd device name
# $2 - var for storing ubi device name
#
get_ubidev_for_mtd() {
	local _ubidev
	if [ "${SWU_COUNT_PROGRESS}" = "1" ]; then
		_ubidev="ubiXXX"
	else
		_ubidev=$(nand_find_ubi "${1}")
		if [ -z "${_ubidev}" ]; then
			swu_log_error "Cannot find UBI device for MTD device '${1}'"
			return 1
		fi
	fi

	export -- "${2}"="${_ubidev}"
}

#
# $1 - ubi device
# $2 - ubi volume name
# $3 - var for storing ubi volume device name
#
get_ubivoldev_for_ubivolname() {
	local _ubivoldev
	if [ "${SWU_COUNT_PROGRESS}" = "1" ]; then
		_ubivoldev="ubiXXX_Y"
	else
		_ubivoldev=$(nand_find_volume "${1}" "${2}")
		if [ -z "${_ubivoldev}" ]; then
			swu_log_error "Cannot find UBI volume '${2}' in UBI device '${1}'"
			return 1
		fi
	fi

	export -- "${3}"="${_ubivoldev}"
}

# -----------------------------------------------------------------------------
#
# __      __       _  __ _           _   _
# \ \    / /      (_)/ _(_)         | | (_)
#  \ \  / /__ _ __ _| |_ _  ___ __ _| |_ _  ___  _ __
#   \ \/ / _ \ '__| |  _| |/ __/ _` | __| |/ _ \| '_ \
#    \  /  __/ |  | | | | | (_| (_| | |_| | (_) | | | |
#     \/ \___|_|  |_|_| |_|\___\__,_|\__|_|\___/|_| |_|
#
# -----------------------------------------------------------------------------

do_mtd_required() {
	swu_log_subsection "Checking required MTD devices..."

	local mtd
	local mtds
	json_get_values mtds

	for mtd in ${mtds}; do
		local mtdindex
		get_mtdindex "${mtd}" mtdindex || return $?
		swu_log_success "MTD device ${mtd} found (/dev/mtd${mtdindex})"
	done
}

do_blkdev_required() {
	swu_log_subsection "Checking required block devices..."

	local dev
	local devs
	json_get_values devs

	for dev in ${devs}; do
		blkdev_validate "${dev}" || return $?
		swu_log_success "Block device ${dev} found"
	done
}

do_verify() {
	swu_log_section "Verification"
	execute_substages_funcs "${STAGES_VERIFY_ORDER}" || return $?
}

# -----------------------------------------------------------------------------
#
#  __  __                   _
# |  \/  |                 | |
# | \  / | ___  _   _ _ __ | |_ ___
# | |\/| |/ _ \| | | | '_ \| __/ __|
# | |  | | (_) | |_| | | | | |_\__ \
# |_|  |_|\___/ \__,_|_| |_|\__|___/
# 
# -----------------------------------------------------------------------------

do_mounts() {
	swu_log_subsection "Mounting filesystems..."

	check_binaries ${BIN_MOUNT} ${BIN_MKDIR} || return $?

	local mount
	local mounts
	json_get_keys mounts

	for mount in ${mounts}; do
		swu_json_select "${mount}"
			swu_log_action "Mount ${mount}"

			local device=""
			local target=""
			local create_target=""
			local fstype=""

			json_get_vars device target create_target fstype

			if [ -z "${device}" ] || [ -z "${target}" ]; then
				swu_log_error "No 'device' or/and 'target' specified for mountpoint"
				return 1
			fi

			swu_log_param "Device" "${device}"
			swu_log_param "Target" "${target}"

			local mount_args=""

			if [ -n "${fstype}" ]; then
				append mount_args "-t ${fstype}"
				swu_log_param "Filsystem" "${fstype}"
			else
				swu_log_param "Filsystem" "auto"
			fi

			if [ "${create_target}" = "1" ]; then
				swu_log_param "Create target" "yes"
			else
				swu_log_param "Create target" "no"
			fi

			append mount_args "${device}"
			append mount_args "${target}"

			if [ "${create_target}" = "1" ]; then
				if [ ! -d "${target}" ]; then
					bin_execute "${BIN_MKDIR} -p \"${target}\"" || return $?
					swu_log_success "Target created"
				fi
			fi

			bin_execute "${BIN_MOUNT} ${mount_args}" || return $?
			swu_install_progress

			swu_log_success "Mounted"
		swu_json_select ..
	done
}

# -----------------------------------------------------------------------------
#
#  ______                     _
# |  ____|                   | |
# | |__  __  _____  ___ _   _| |_ ___
# |  __| \ \/ / _ \/ __| | | | __/ _ \
# | |____ >  <  __/ (__| |_| | ||  __/
# |______/_/\_\___|\___|\__,_|\__\___|
# 
# -----------------------------------------------------------------------------

do_execute() {
	swu_log_subsection "Executing custom commands..."

	check_binaries ${BIN_MOUNT} ${BIN_MKDIR} || return $?

	local cmd
	local commands
	json_get_keys commands

	for cmd in ${commands}; do
		swu_json_select "${cmd}"
			swu_log_action "Command ${cmd}"

			local command=""
			local required_exit_code=0
			local ignore_exit_code=0

			json_get_var command "command" ""
			json_get_var required_exit_code "required_exit_code" "0"
			json_get_var ignore_exit_code "ignore_exit_code" "0"

			if [ -z "${command}" ]; then
				swu_log_error "No 'command' specified"
				return 1
			fi

			swu_log_param "Command" "${command}"
			if [ "${ignore_exit_code}" = "0" ]; then
				swu_log_param "Required exit code " "${required_exit_code}"
			else
				swu_log_param "Required exit code" "ignore"
			fi

			bin_execute \
				"${command}" ${ignore_exit_code} ${required_exit_code} || return $?

			swu_install_progress
		swu_json_select ..
	done
}

# -----------------------------------------------------------------------------
#
#  _____          _           _        _ _
# |  __ \        (_)         | |      | | |
# | |__) | __ ___ _ _ __  ___| |_ __ _| | |
# |  ___/ '__/ _ \ | '_ \/ __| __/ _` | | |
# | |   | | |  __/ | | | \__ \ || (_| | | |
# |_|   |_|  \___|_|_| |_|___/\__\__,_|_|_|
#
# -----------------------------------------------------------------------------

do_mtd_erase() {
	swu_log_subsection "Erasing MTD partitions..."

	check_binaries ${BIN_FLASH_ERASE} || return $?

	local mtd
	local mtds
	json_get_values mtds

	for mtd in ${mtds}; do
		swu_log_action "MTD device ${mtd}"

		local mtdindex
		get_mtdindex "${mtd}" mtdindex || return $?

		bin_execute "${BIN_FLASH_ERASE} /dev/mtd${mtdindex} 0 0" || return $?
		swu_install_progress

		swu_log_success "Erased /dev/mtd${mtdindex} (${mtd})"
	done
}

do_mtd_nandwrite() {
	local mtd
	local offset

	check_binaries ${BIN_NANDWRITE} || return $?

	local mtd
	json_get_var mtd "mtd" ""

	local mtdindex
	get_mtdindex "${mtd}" mtdindex || return $?

	swu_log_param "Device" "/dev/mtd${mtdindex} (${mtd})"

	json_get_var offset "offset" "0"
	swu_log_param "Offset" "${offset} bytes"

	local image_url
	get_image_url image_url || return $?

	local NANDWRITE_ARGS="/dev/mtd${mtdindex} --start=${offset} ${image_url}"

	bin_execute "${BIN_NANDWRITE} ${NANDWRITE_ARGS}" || return $?
	swu_install_progress

	swu_log_success "Writed '${image_url} to /dev/mtd${mtdindex}" \
		"(${mtd}) with offset ${offset}"
}

do_mtd_write() {
	swu_log_subsection "Writing MTD devices..."

	local write
	local writes
	json_get_keys writes

	for write in ${writes}; do
		swu_json_select "${write}"
			swu_log_action "Write ${write}"

			local type=""
			json_get_var type "type" ""

			case "${type}" in
				nandwrite)
					do_mtd_nandwrite || return $?
					;;

				*)
					swu_log_error "Unknown/unsupported MTD write type '${type}'"
					return 1
					;;
			esac
		swu_json_select ..
	done
}

do_mtd_ubiformat() {
	swu_log_subsection "Formatting MTD devices for UBI..."

	check_binaries ${BIN_UBIFORMAT} || return $?

	local mtd
	local mtds
	json_get_keys mtds

	for mtd in ${mtds}; do
		swu_log_action "UBI partition ${mtd}"
		swu_json_select "${mtd}"
			local mtdname
			json_get_var mtdname "mtd" ""

			local mtdindex
			get_mtdindex "${mtdname}" mtdindex || return $?

			swu_log_param "Device" "/dev/mtd${mtdindex} (${mtdname})"

			local UBIFORMAT_ARGS="/dev/mtd${mtdindex} --yes"

			local sub_page_size=""
			local vid_hdr_offset=""

			json_get_var vid_hdr_offset "vid_hdr_offset" "${UBI_DEFAULT_VID_HDR_OFFSET}"
			if [ -n "${vid_hdr_offset}" ]; then
				UBIFORMAT_ARGS="${UBIFORMAT_ARGS} --vid-hdr-offset=${vid_hdr_offset}"
				swu_log_param "VID header offset" "${vid_hdr_offset}"
			fi

			json_get_var sub_page_size "sub_page_size" "${UBI_DEFAULT_SUB_PAGE_SIZE}"
			if [ -n "${sub_page_size}" ]; then
				UBIFORMAT_ARGS="${UBIFORMAT_ARGS} --sub-page-size=${sub_page_size}"
				swu_log_param "Sub-page size" "${sub_page_size}"
			fi

			bin_execute "${BIN_UBIFORMAT} ${UBIFORMAT_ARGS}" || return $?
			swu_install_progress

			swu_log_success "Formatted /dev/mtd${mtdindex} (${mtdname})"
		swu_json_select ..
	done
}

#
# $1 - mtdindex
# $2 - mtdname (used only for logs)
#
do_ubi_attach() {
	check_binaries ${BIN_UBIATTACH} || return $?

	local attach="1"
	json_get_var attach "attach" "1"

	if [ "${attach}" = "1" ]; then
		#
		# Attaching UBI
		#
		local ubiattach_args=""

		local mtdindex=${1}
		local mtdname=${2}

		swu_log_info "Attaching UBI partition /dev/mtd${mtdindex} (${mtdname})..."

		local ubi_dev_num=""
		json_get_var ubi_dev_num "ubi_dev_num" ""

		local ubiattach_extra=""
		json_get_var ubiattach_extra "ubiattach_extra" ""

		append ubiattach_args "--dev-path=/dev/mtd${mtdindex}"

		if [ -n "${ubi_dev_num}" ]; then
			append ubiattach_args "--devn=${ubi_dev_num}"
			swu_log_param "UBI device number" "${ubi_dev_num}"
		fi

		local vid_hdr_offset=""
		json_get_var vid_hdr_offset "vid_hdr_offset" "${UBI_DEFAULT_VID_HDR_OFFSET}"
		if [ -n "${vid_hdr_offset}" ]; then
			append ubiattach_args "--vid-hdr-offset=${vid_hdr_offset}"
			swu_log_param "VID header offset" "${vid_hdr_offset}"
		fi

		local max_beb_per1024=""
		json_get_var max_beb_per1024 "max_beb_per1024" ""
		if [ -n "${max_beb_per1024}" ]; then
			append ubiattach_args "--max-beb-per1024=${max_beb_per1024}"
			swu_log_param "Maximum expected number of bad blocks per 1024 blocks" \
				"${max_beb_per1024}"
		fi

		if [ "${ubiattach_extra}" != "" ]; then
			append ubiattach_args "${ubiattach_extra}"
			swu_log_param "Extra arguments for ubiattach" "${ubiattach_extra}"
		fi

		bin_execute "${BIN_UBIATTACH} ${ubiattach_args}" || return $?
		swu_install_progress
	fi
}

#
# $1 - mtdindex
# $2 - mtdname (used only for logs)
#
do_ubi_detach() {
	check_binaries ${BIN_UBIDETACH} || return $?

	local detach="1"
	json_get_var detach "detach" "1"

	if [ "${detach}" = "1" ]; then
		#
		# Detaching UBI
		#
		local ubidetach_args=""

		local mtdindex=${1}
		local mtdname=${2}

		swu_log_info "Detaching UBI partition /dev/mtd${mtdindex} (${mtdname})..."

		local ubi_dev_num=""
		json_get_var ubi_dev_num "ubi_dev_num" ""

		if [ -n "${ubi_dev_num}" ]; then
			append ubidetach_args "--devn=${ubi_dev_num}"
			swu_log_param  "UBI device number" "${ubi_dev_num}"
		else
			append ubidetach_args "--mtdn=${mtdindex}"
		fi

		bin_execute "${BIN_UBIDETACH} ${ubidetach_args}" || return $?
		swu_install_progress
	fi
}

do_mtd_ubimkvol() {
	swu_log_subsection "Creating UBI volumes..."

	check_binaries ${BIN_UBIMKVOL} || return $?

	local mtd
	local mtds
	json_get_keys mtds

	for mtd in ${mtds}; do
		swu_log_action "MTD device ${mtd}"
		swu_json_select "${mtd}"
			local mtdindex
			local mtdname
			json_get_var mtdname "mtd" ""

			local mtdindex
			get_mtdindex "${mtdname}" mtdindex || return $?

			swu_log_param "MTD device: /dev/mtd${mtdindex} (${mtdname})"

			#
			# Attaching UBI partition
			#
			do_ubi_attach "${mtdindex}" "${mtdname}" || return $?

			#
			# Create UBI volumes
			#
			swu_json_select "volumes"
				local volume
				local volumes
				json_get_keys volumes

				for volume in ${volumes}; do
					swu_json_select "${volume}"
						local ubimkvol_args=""
						local ubimkvol_extra=""
						local ubivol_name=""
						local ubivol_type=""
						local ubivol_size=""
						local ubivol_id=""
						local alignment=""

						swu_log_info "Creating UBI volume ${volume}..."

						local ubidev
						get_ubidev_for_mtd "${mtdname}" ubidev || return $?

						swu_log_param "UBI device" "${ubidev}"
						append ubimkvol_args "/dev/${ubidev}"

						json_get_var alignment "alignment" ""
						if [ -n "${alignment}" ]; then
							append ubimkvol_args "--alignment=${alignment}"
							swu_log_param "Alignment" "${alignment}"
						fi

						json_get_var ubivol_id "id" ""
						if [ -n "${ubivol_id}" ]; then
							append ubimkvol_args "--vol_id=${ubivol_id}"
							swu_log_param "Volume ID" "${ubivol_id}"
						fi

						json_get_var ubivol_name "name" ""
						if [ "${ubivol_name}" != "" ]; then
							append ubimkvol_args "--name=${ubivol_name}"
							swu_log_param "Name" "${ubivol_name}"
						fi

						json_get_var ubivol_type "type" ""
						if [ "${ubivol_type}" != "" ]; then
							append ubimkvol_args "--type=${ubivol_type}"
							swu_log_param "Type" "${ubivol_type}"
						fi

						json_get_var ubivol_size "size" ""
						if [ "${ubivol_size}" = "max" ]; then
							append ubimkvol_args "--maxavsize"
							swu_log_param "Size" "(maximum available size)"
						elif [ "${ubivol_size}" != "" ]; then
							append ubimkvol_args "--size=${ubivol_size}"
							swu_log_param "Size" "${ubivol_size}"
						fi

						json_get_var ubimkvol_extra "extra" ""
						if [ "${ubimkvol_extra}" != "" ]; then
							append ubimkvol_args "${ubimkvol_extra}"
							swu_log_param "Extra arguments for ubimkvol" "${ubimkvol_extra}"
						fi

						bin_execute "${BIN_UBIMKVOL} ${ubimkvol_args}" || return $?
						swu_install_progress
					swu_json_select ..
				done
			swu_json_select ..

			#
			# Detaching UBI partition
			#
			do_ubi_detach "${mtdindex}" "${mtdname}" || return $?

			swu_log_success "UBI volume created"
		swu_json_select ..
	done
}

do_mtd_ubiupdatevol() {
	swu_log_subsection "Updating UBI volumes..."

	check_binaries ${BIN_UBIATTACH} ${BIN_UBIDETACH} ${BIN_UBIUPDATEVOL} || return $?

	local mtd
	local mtds
	json_get_keys mtds

	for mtd in ${mtds}; do
		swu_log_action "MTD device ${mtd}"
		swu_json_select "${mtd}"
			local mtdname
			json_get_var mtdname "mtd" ""

			local mtdindex
			get_mtdindex "${mtdname}" mtdindex || return $?

			swu_log_param "MTD device" "/dev/mtd${mtdindex} (${mtdname})"

			#
			# Attaching UBI partition
			#
			do_ubi_attach "${mtdindex}" "${mtdname}" || return $?

			#
			# Updating UBI volumes
			#
			swu_json_select "volumes"
				local volume
				local volumes
				json_get_keys volumes

				for volume in ${volumes}; do
					swu_json_select "${volume}"
						local ubiupdatevol_args=""
						local ubiupdatevol_extra=""
						local ubivol_name
						local size
						local skip

						swu_log_info "Updating UBI volume ${volume}..."

						json_get_var ubivol_name "name" ""
						if [ "${ubivol_name}" != "" ]; then
							swu_log_param "Name" "${ubivol_name}"
						fi

						if [ -z "${ubivol_name}" ]; then
							swu_log_error "No 'name' is specified"
							return 1
						fi

						local ubidev
						get_ubidev_for_mtd "${mtdname}" ubidev || return $?

						local ubivol
						get_ubivoldev_for_ubivolname "${ubidev}" "${ubivol_name}" ubivol || return $?

						swu_log_param "UBI device" "${ubidev}"
						swu_log_param "UBI volume device" "${ubivol}"

						append ubiupdatevol_args "/dev/${ubivol}"

						json_get_var skip "skip" ""
						if [ -n "${skip}" ]; then
							append ubiupdatevol_args "--skip=${skip}"
							swu_log_param "Skip" "${skip} bytes"
						fi

						json_get_var size "size" ""
						if [ -n "${size}" ]; then
							append ubiupdatevol_args "--size=${size}"
							swu_log_param "Size" "${size} bytes"
						fi

						json_get_var ubiupdatevol_extra "extra" ""
						if [ "${ubiupdatevol_extra}" != "" ]; then
							append ubiupdatevol_args "${ubiupdatevol_extra}"
							swu_log_param "Extra arguments for ubiupdatevol" \
								"${ubiupdatevol_extra}"
						fi

						local image_url
						get_image_url image_url || return $?

						append ubiupdatevol_args "${image_url}"

						bin_execute "${BIN_UBIUPDATEVOL} ${ubiupdatevol_args}" || return $?
						swu_install_progress
					swu_json_select ..
				done
			swu_json_select ..

			#
			# Detaching UBI
			#
			do_ubi_detach "${mtdindex}" "${mtdname}" || return $?

			swu_log_success "UBI volume updated"
		swu_json_select ..
	done
}

do_preinstall() {
	swu_log_section "Pre-installation"
	execute_substages_funcs "${STAGES_PREINSTALL_ORDER}" || return $?
}

# -----------------------------------------------------------------------------

do_blkdev_erase() {
	swu_log_subsection "Erasing block devices..."

	check_binaries ${BIN_DD} || return $?

	local blkdev
	local blkdevs
	json_get_keys blkdevs

	for blkdev in ${blkdevs}; do
		swu_json_select "${blkdev}"
			swu_log_action "Block device ${blkdev}"

			local device=""
			local bs=""
			local count="1"
			local skip=""
			local seek=""

			local dd_args=""

			json_get_vars device bs count skip seek

			blkdev_validate "${device}" || return $?

			swu_log_param "Device" "${device}"

			dd_args="if=/dev/zero of=${device}"

			if [ -n "${bs}" ]; then
				swu_log_param "Block size" "${bs}"
				append dd_args "bs=${bs}"
			else
				swu_log_error "Block size ('bs') have to be set"
				return 1
			fi

			if [ -n "${count}" ]; then
				swu_log_param "Count" "${count}"
				append dd_args "count=${count}"
			fi

			if [ -n "${skip}" ]; then
				swu_log_param "Skip" "${skip}"
				append dd_args "skip=${skip}"
			fi

			if [ -n "${seek}" ]; then
				swu_log_param "Seek" "${seek}"
				append dd_args "seek=${seek}"
			fi

			bin_execute "${BIN_DD} ${dd_args}" || return $?
			swu_install_progress

			swu_log_success "Block device ${device} erased"
		swu_json_select ..
	done
}

do_blkdev_mkfs() {
	# shellcheck disable=SC2034
	local fstypes_supported="fat ext2 ext3 ext4"

	swu_log_subsection "Creating filesystems on block devices..."

	local blkdev
	local blkdevs
	json_get_keys blkdevs

	for blkdev in ${blkdevs}; do
		swu_json_select "${blkdev}"
			swu_log_action "Block device ${blkdev}"

			local device=""
			local label=""
			local fstype=""
			local extra_args=""
			local mkfs_args=""

			json_get_vars device label fstype extra_args

			blkdev_validate "${device}" || return $?

			if [ -z "${fstype}" ]; then
				swu_log_error "No 'fstype' is specified"
				return 1
			fi

			swu_log_param "Device" "${device}"
			swu_log_param "Filesystem type" "${fstype}"

			if ! list_contains fstypes_supported "${fstype}"; then
				swu_log_error "Unsupported filesystem type '${fstype}'"
				return 1
			fi

			local mkfs_bin="/sbin/mkfs.${fstype}"

			if [ "${fstype}" = "ext4" ] || \
			   [ "${fstype}" = "ext3" ] || \
			   [ "${fstype}" = "ext2" ]; then
				append mkfs_args "-F"
			elif [ "${fstype}" = "fat" ]; then
				mkfs_bin="/usr/sbin/mkfs.${fstype}"
			fi

			if [ -n "${label}" ]; then
				swu_log_param "Label" "${label}"
				if [ "${fstype}" = "ext4" ] || \
				   [ "${fstype}" = "ext3" ] || \
				   [ "${fstype}" = "ext2" ]; then
					append mkfs_args "-L ${label}"
				elif [ "${fstype}" = "fat" ]; then
					append mkfs_args "-n ${label}"
				fi
			fi

			check_binaries ${mkfs_bin} || return $?

			if [ -n "${extra_args}" ]; then
				swu_log_param "Extra arguments" "${extra_args}"
				append mkfs_args "${extra_args}"
			fi

			append mkfs_args "${device}"

			bin_execute "${mkfs_bin} ${mkfs_args}" || return $?
			swu_install_progress

			swu_log_success "Created ${fstype} filesystem on ${device}"
		swu_json_select ..
	done
}

do_blkdev_parts_do_partitions() {
	local device=${1}
	local part
	local partitions
	json_get_keys partitions

	local part_n=1

	for part in ${partitions}; do
		swu_json_select "${part}"
			local name
			local type
			local fstype
			local start
			local end
			local boot
			local uuid

			local parted_args="-s ${device} --script mkpart"

			swu_log_info "Partition ${part_n}:"

			json_get_var name "name" ""
			json_get_var type "type" "primary"
			json_get_var fstype "fstype" ""
			json_get_var start "start" ""
			json_get_var end "end" ""
			json_get_var boot "boot" ""
			json_get_var uuid "uuid" ""

			if [ "${type}" != "primary" ] && \
			   [ "${type}" != "extended" ] && \
			   [ "${type}" != "logical" ]; then
				swu_log_error "Invalid partition type '${type}' is specified"
				return 1
			fi

			[ -n "${name}" ] && swu_log_param "Name" "${name}"
			swu_log_param "Partition type" "${type}"

			append parted_args "${type}"

			if [ -n "${fstype}" ]; then
				swu_log_param "Filesystem type" "${fstype}"
				append parted_args "${fstype}"
			fi

			if [ -n "${start}" ]; then
				swu_log_param "Start" "${start}"
				append parted_args "${start}"
			fi

			if [ -n "${end}" ]; then
				swu_log_param "End" "${end}"
				append parted_args "${end}"
			fi

			if [ -n "${uuid}" ]; then
				swu_log_param "UUID" "${uuid}"
			fi

			if [ -n "${boot}" ]; then
				swu_log_param "Boot flag" "${boot}"
			fi

			bin_execute "${BIN_PARTED} ${parted_args}" || return $?
			swu_install_progress

			if [ -n "${name}" ]; then
				bin_execute \
					"${BIN_PARTED} -s ${device} --script name ${part_n} '${name}'" \
					|| return $?
				swu_install_progress
			fi

			if [ -n "${boot}" ]; then
				if [ "${boot}" = "1" ]; then
					bin_execute \
						"${BIN_PARTED} -s ${device} --script set ${part_n} boot on" \
						|| return $?
				else
					bin_execute \
						"${BIN_PARTED} -s ${device} --script set ${part_n} boot off" \
						|| return $?
				fi
				swu_install_progress
			fi

			if [ -n "${uuid}" ]; then
				bin_execute \
					"${BIN_SGDISK} --partition-guid=${part_n}:${uuid} ${device}" \
					|| return $?
				swu_install_progress
			fi

			part_n=$((part_n + 1))
		swu_json_select ..
	done
}

do_blkdev_parts() {
	swu_log_subsection "Creating partitions on block devices..."

	local blkdev
	local blkdevs
	json_get_keys blkdevs

	check_binaries ${BIN_PARTED} ${BIN_PARTPROBE} || return $?

	for blkdev in ${blkdevs}; do
		swu_json_select "${blkdev}"
			swu_log_action "Block device ${blkdev}"

			local device
			local label
			local blkdev_size
			local expression
			local create_partitions

			json_get_var device "device" ""
			json_get_var label "label" ""
			json_get_var expression "expression" ""

			blkdev_validate "${device}" || return $?

			if [ -z "${label}" ]; then
				swu_log_error "No label is specified"
				return 1
			fi

			if [ "${label}" != "gpt" ] && [ "${label}" != "msdos" ]; then
				swu_log_error "Specified unsupported label '${label}'." \
					"Supported only 'gpt' or 'msdos' label"
				return 1
			fi

			swu_log_param "Device" "${device}"
			swu_log_param "Label" "${label}"

			get_blkdev_size "${device}" blkdev_size || return $?

			swu_log_param "Block device size" "${blkdev_size}"

			if [ -n "${expression}" ]; then
				if ! eval [ ${expression} ]; then
					swu_log_info "Expression '${expression}' is false, skipping partitions creation";
					create_partitions="0"
				else
					swu_log_info "Expression '${expression}' is true, creating partitions";
					create_partitions="1"
				fi
			else
				create_partitions="1"
			fi

			if [ "${create_partitions}" = "1" ]; then
				if json_is_a "partitions" array; then
					bin_execute \
						"${BIN_PARTED} -s ${device} --script mklabel ${label}" || return $?
					swu_install_progress

					swu_json_select "partitions"
						bin_execute "${BIN_PARTPROBE}" || return $?
						do_blkdev_parts_do_partitions "${device}" || return $?
						bin_execute "${BIN_PARTED} -s ${device} --script print" || return $?
						bin_execute "${BIN_PARTPROBE}" || return $?
						swu_install_progress
					swu_json_select ..
				else
					swu_log_error "No partitions specified"
					return 1
				fi

				swu_log_success "Created partitions on block device ${device}"
			fi
		swu_json_select ..
	done
}

do_blkdev_write() {
	swu_log_subsection "Writing images to block devices..."

	check_binaries ${BIN_DD} || return $?

	local blkdev
	local blkdevs
	json_get_keys blkdevs

	for blkdev in ${blkdevs}; do
		swu_log_action "Block device ${blkdev}"
		swu_json_select "${blkdev}"
			local device
			json_get_var device "device" ""

			blkdev_validate "${device}" || return $?

			swu_log_param "Device" "${device}"

			if json_is_a "images" array; then
				swu_json_select "images"
					local images
					json_get_keys images

					for image in ${images}; do
						swu_json_select "${image}"
							swu_log_info "Image ${image}:"

							local image_url
							get_image_url image_url || return $?

							local dd_args="if=${image_url} of=${device}"

							local offset
							json_get_var offset "offset" ""
							if [ -n "${offset}" ] && [ "${offset}" != "0" ]; then
								swu_log_param "Offset" "${offset} bytes"
								append dd_args "bs=${offset} seek=1"
							fi

							bin_execute "${BIN_DD} ${dd_args}" || return $?
							swu_install_progress
						swu_json_select ..
					done
				swu_json_select ..
			else
				swu_log_error "No images specified"
				return 1
			fi

			swu_log_success "Writed images to block device ${device}"
		swu_json_select ..
	done
}

# -----------------------------------------------------------------------------
#
#  _____           _        _ _
# |_   _|         | |      | | |
#   | |  _ __  ___| |_ __ _| | |
#   | | | '_ \/ __| __/ _` | | |
#  _| |_| | | \__ \ || (_| | | |
# |_____|_| |_|___/\__\__,_|_|_|
#
# -----------------------------------------------------------------------------

do_install_file() {
	local image_url
	get_image_url image_url || return $?

	local destination=""
	json_get_var destination "destination" ""
	[ -z "${destination}" ] && {
		swu_log_error "No destination specified"
		return 1
	}

	swu_log_param "Destination" "${destination}"

	# Create destination directory if not exists
	local dir=$(dirname ${destination})
	[ ! -d "${dir}" ] && {
		bin_execute "mkdir -p ${dir}" || return $?
	}

	# Copy file to destination
	bin_execute "cp -f ${image_url} ${destination}" || return $?

	swu_install_progress
}

do_install_swupdate() {
	local image_url
	get_image_url image_url || return $?

	if ! json_is_a "selections" array; then
		swu_log_error "No 'selections' specified"
		return 1
	fi

	local SW_ARGS_EXTRA=""
	json_get_var SW_ARGS_EXTRA "extra" ""
	[ -n "${SW_ARGS_EXTRA}" ] && swu_log_param "Extra arguments" "${SW_ARGS_EXTRA}"

	local dry_run
	json_get_var dry_run "dry_run" "1"
	swu_log_param "Dry run" "${dry_run}"

	local install
	json_get_var install "install" "1"
	swu_log_param "Install" "${install}"

	swu_json_select "selections"
		local selections
		local selections_expanded=""
		json_get_values selections

		for selection in ${selections}; do
			append selections_expanded "$(swu_eval_value "${selection}")"
		done

		swu_log_param "Selections" "${selections}"
		swu_log_param "Selections (expanded)" "${selections_expanded}"

		local runs
		[ "${dry_run}" = "1" ] && append runs "dry"
		[ "${install}" = "1" ] && append runs "install"

		local run
		for run in ${runs}; do
			for selection in ${selections_expanded}; do
				if [ "${selection}" = "SWU_SYSTEM_A_SELECTION" ]; then
					selection="${SWU_SYSTEM_A_SELECTION}"
				elif [ "${selection}" = "SWU_SYSTEM_B_SELECTION" ]; then
					selection="${SWU_SYSTEM_B_SELECTION}"
				fi

				local SW_SELECT="${selection}"
				local SW_IMAGE="${image_url}"
				local SW_ARGS="-e ${SW_SELECT} -v -M -f ${SWU_CONF} -i ${SW_IMAGE}"

				[ "${run}" = "dry" ] && append SW_ARGS "-n"

				swu_log_action "[${run}] run for selection '${selection}'"

				bin_execute "SWU_FACTORY_INSTALL=1 ${BIN_SWUPDATE} ${SW_ARGS} ${SW_ARGS_EXTRA}" || return $?
				swu_install_progress

				swu_log_success "Selection '${selection}' [${run}] run success"
			done
		done
	swu_json_select ..
}

do_images() {
	check_binaries ${BIN_SWUPDATE} || return $?

	swu_log_section "Installing images"

	local images
	json_get_keys images

	for image in ${images}; do
		swu_json_select "${image}"
			swu_log_subsection "Installing image ${image}..."

			local install_type
			json_get_var install_type "type" ""
			swu_log_param "Type" "${install_type}"

			case "${install_type}" in
				swupdate)
					do_install_swupdate || return $?
					;;

				file)
					do_install_file || return $?
					;;

				*)
					swu_log_error "Unknown/unsupported installation type '${install_type}'"
					return 1
					;;
			esac
		swu_json_select ..
	done
}

do_install() {
	swu_log_section "Installation"
	execute_substages_funcs "${STAGES_INSTALL_ORDER}" || return $?
}

# -----------------------------------------------------------------------------
#
#  _____          _   _           _        _ _ 
# |  __ \        | | (_)         | |      | | |
# | |__) |__  ___| |_ _ _ __  ___| |_ __ _| | |
# |  ___/ _ \/ __| __| | '_ \/ __| __/ _` | | |
# | |  | (_) \__ \ |_| | | | \__ \ || (_| | | |
# |_|   \___/|___/\__|_|_| |_|___/\__\__,_|_|_|
#
# -----------------------------------------------------------------------------

do_postinstall() {
	swu_log_section "Post-installation"
	execute_substages_funcs "${STAGES_POSTINSTALL_ORDER}" || return $?
}

# -----------------------------------------------------------------------------

#
# $1 - command
# $2 - description
# $3 - delay (seconds)
#
swu_install_do_last_action() {
	local DELAY="${3:-5}"

	while [ "${DELAY}" != "0" ]; do
		swu_log_info "${2} in ${DELAY} seconds..."
		swu_psplash_msg "${2} in ${DELAY} seconds..."
		sleep 1
		DELAY=$((DELAY - 1))
	done

	swu_log_info "${2} now..."
	swu_psplash_msg "${2} now..."

	${1}
}

swu_install_last_action() {
	[ "${ENABLE_LAST_ACTION}" = "1" ] || return 0

	case "${LAST_ACTION}" in
		reboot)
			swu_install_do_last_action "/sbin/reboot -f" "Rebooting" 5
			;;

		poweroff|halt)
			swu_install_do_last_action "/sbin/poweroff -f" "Powering off" 5
			;;
	esac
}

swu_do_stages() {
	local stage
	for stage in ${STAGES_ORDER}; do
		local stage_name="${stage%:*}"
		local stage_func="${stage#*:}"

		if [ "${stage_name}" = "${stage_func}" ]; then
			stage_func="do_${stage_name}"
		fi

		if json_is_a "${stage_name}" object || json_is_a "${stage_name}" array; then
			if ! eval "type ${stage_func}" 2>/dev/null >/dev/null; then
				swu_log_error "Function '${stage_func}' is not declared"
				swu_log_fatal "Stage '${stage_name}' failed"
				swu_install_failure
				exit 1
			fi

			swu_json_select "${stage_name}"
				${stage_func} || {
					swu_log_fatal "Stage '${stage_name}' failed"
					swu_install_failure
					exit 1
				}
			swu_json_select ..
		fi
	done
}

swu_install_main() {
	SWU_COUNT_PROGRESS=1
	SWU_LOG=${DRY_RUN}
	swu_do_stages

	if [ "${SWU_INSTALL_ACTIONS}" = "0" ]; then
		swu_log_info "No actions required to perform. Exiting..."
		exit 0
	fi

	if [ "${DRY_RUN}" = "0" ]; then
		SWU_COUNT_PROGRESS=0
		SWU_LOG=1
		swu_do_stages
	else
		SWU_INSTALL_PROGRESS="${SWU_INSTALL_ACTIONS}"
	fi

	if [ "${SWU_INSTALL_ACTIONS}" = "${SWU_INSTALL_PROGRESS}" ]; then
		sync
		swu_log_success_box "Installation successfully completed"
		swu_install_done
		[ "${DRY_RUN}" = "0" ] && [ "${ENABLE_LAST_ACTION}" = "1" ] && swu_install_last_action
	else
		swu_log_fatal "Installation incompleted" \
			"(${SWU_INSTALL_PROGRESS}/${SWU_INSTALL_ACTIONS} actions executed)"
		swu_install_failure
		exit 1
	fi

	exit 0
}

# -----------------------------------------------------------------------------

SWU_INSTALL_HEARTBEAT_PID=0
SWU_INSTALL_HEARTBEAT_DELAY=3

# Include board specific script
if [ -f "${SWU_INSTALL_BOARD}" ]; then
	# shellcheck disable=SC1090
	. "${SWU_INSTALL_BOARD}"
fi

swu_install_heartbeat_loop() {
	while :; do
		swu_install_hook_heartbeat
		sleep ${SWU_INSTALL_HEARTBEAT_DELAY}
	done
}

swu_install_heartbeat_stop() {
	if [ ${SWU_INSTALL_HEARTBEAT_PID} -gt 0 ]; then
		kill -9 ${SWU_INSTALL_HEARTBEAT_PID} 2>/dev/null
	fi
}

swu_install_failure() {
	swu_psplash_msg "FAILED (${SWU_JSON_PATH})"
	swu_install_heartbeat_stop
	swu_install_hook_failure
}

swu_install_done() {
	swu_psplash_msg "Installation sucessfully completed"
	swu_install_heartbeat_stop
	swu_install_hook_done
}

swu_install_on_exit() {
	swu_install_heartbeat_stop
	for f in ${at_exit_rm}; do
		rm -rf "${f}"
	done
}

trap swu_install_on_exit INT TERM EXIT

# Check installation JSON
if [ ! -f "${SWU_INSTALL_JSON}" ]; then
	swu_log_error "Cannot find installation JSON at '${SWU_INSTALL_JSON}'"
	exit 1
fi

sysctl -w kernel.printk="3 4 1 3" > /dev/null

swu_install_heartbeat_loop &
SWU_INSTALL_HEARTBEAT_PID=$!

json_load_file "${SWU_INSTALL_JSON}" || {
	swu_log_error "Failed to load JSON from '${SWU_INSTALL_JSON}'"
	exit 1
}

swu_install_progress_init
swu_install_main

# -----------------------------------------------------------------------------

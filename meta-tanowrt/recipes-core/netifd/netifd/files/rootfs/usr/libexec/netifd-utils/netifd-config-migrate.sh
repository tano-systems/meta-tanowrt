#!/bin/sh
#
# SPDX-License-Identifier: MIT
#
# netifd configuration migration script
# 
# Copyright (C) 2021 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#

. /lib/functions.sh

MIGRATED_IFS=0
MIGRATED_DEVS=0

UCI="/usr/sbin/uci -q"

migrate_interfaces() {
	local config="$1"
	local type=""
	local ports=""
	local ifname=""
	local device=""

	config_get type   "${config}" "type"
	config_get ports  "${config}" "ports"
	config_get ifname "${config}" "ifname"
	config_get device "${config}" "device"

	if [ "${type}" = "bridge" ] && [ -z "${ports}" ] && [ -n "${ifname}" ]; then
		#
		# Migrate 'interface' sections with bridge type
		# to 'interface' section with link to 'device' section
		#
		# Before:
		#
		# config interface 'lan'
		#     option ifname 'port1 port2 port3'
		#     option type 'bridge'
		#     option stp '1'
		#     option igmp_snooping '1'
		#     option proto 'dhcp'
		#
		# After:
		#
		# config device
		#     option name 'br-lan'
		#     option type 'bridge'
		#     option stp '1'
		#     option igmp_snooping '1'
		#     list ports 'port1'
		#     list ports 'port2'
		#     list ports 'port3'
		#
		# config interface 'lan'
		#     option device 'br-lan'
		#     option proto 'dhcp'
		#
		local new=$(${UCI} add network device)

		${UCI} set network.${new}.name="br-${config}"
		${UCI} set network.${new}.type="bridge"

		# interface.ifname -> device.ports
		${UCI} delete network.${config}.ifname
		for interface in ${ifname}; do
			${UCI} add_list network.${new}.ports="${interface}"
		done

		local migrate_options="mtu macaddr stp igmp_snooping"

		for option in ${migrate_options}; do
			local value=""
			config_get value "${config}" "${option}"
			[ -z "$value" ] || {
				${UCI} set network.${new}.${option}="${value}"
				${UCI} delete network.${config}.${option}
			}
		done

		${UCI} delete network.${config}.type
		${UCI} set network.${config}.device="br-${config}"

		MIGRATED_IFS=$((${MIGRATED_IFS} + 1))
	elif [ -z "${device}" ] && [ -n "${ifname}" ]; then
		#
		# Migrate 'ifname' to 'device' option for
		# all 'interface' sections.
		#
		${UCI} delete network.${config}.ifname
		${UCI} set network.${config}.device="${ifname}"

		MIGRATED_IFS=$((${MIGRATED_IFS} + 1))
	fi
}

migrate_devices() {
	local config="$1"
	local type
	local ports
	local ifname

	config_get type "${config}" "type"
	config_get ports "${config}" "ports"
	config_get ifname "${config}" "ifname"

	if [ "${type}" = "bridge" ] && [ -z "${ports}" ] && [ -n "${ifname}" ]; then
		#
		# Migrate 'ifname' to 'ports' option for 'device' sections
		# with bridge type (type=bridge).
		#
		# Before:
		#
		# config device
		#     option name 'br-lan'
		#     option type 'bridge'
		#     option ifname 'port1 port2 port3'
		#
		# After:
		#
		# config device
		#     option name 'br-lan'
		#     option type 'bridge'
		#     list ports 'port1'
		#     list ports 'port2'
		#     list ports 'port3'
		#
		${UCI} delete network.${config}.ifname
		for interface in ${ifname}; do
			${UCI} add_list network.${config}.ports="${interface}"
		done

		MIGRATED_DEVS=$((${MIGRATED_DEVS} + 1))
	fi
}

config_load "network"
config_foreach migrate_interfaces "interface"

config_load "network"
config_foreach migrate_devices "device"

[ "${MIGRATED_IFS}" = "0" ] && [ "${MIGRATED_DEVS}" = "0" ] && exit 0

uci commit network

logger -t netifd "Migrated configurations:" \
                 "${MIGRATED_IFS} interface(s)," \
                 "${MIGRATED_DEVS} device(s)"

exit 0

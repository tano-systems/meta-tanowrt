#!/bin/sh
#
# SPDX-License-Identifier: MIT
#
# MSTPD UCI config migration script.
# 
# Copyright (C) 2020 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
# After commit c0a1916007c7d79ddfc23c05f5b507b113b25a12 (20.04.2020)
# the configuration of port parameters in the mstpd UCI configuration
# has a different structure. Now the port parameters are stored in
# the corresponding section with `bridge_port` type and with the port
# name for each port. Previously, the port parameters stored in the
# `bridge` section in the form `port_<name>_<option> <value>`.
#
# This script migrates old configuration structure to new. For example,
# this configuration:
#
#     config bridge 'lan'
#         port_<port1>_<option1> <value1>
#         port_<port1>_<option2> <value2>
#         port_<port1>_<option3> <value3>
#         port_<port2>_<option4> <value4>
#         port_<port2>_<option5> <value5>
#         port_<port2>_<option6> <value6>
#
# will be converted to this:
#
#     config bridge_port <port1>
#         option <option1> <value1>
#         option <option2> <value2>
#         option <option3> <value3>
#
#     config bridge_port <port2>
#         option <option4> <value4>
#         option <option5> <value5>
#         option <option6> <value6>
#

. /lib/functions.sh

CONFIG_TYPE=
CONFIG_SID=

MIGRATED=0

config_cb() {
	export CONFIG_TYPE=$1
	export CONFIG_SID=$2
}

option_cb() {
	local OPTION=$1
	local VALUE=$2

	[ "${CONFIG_TYPE}" != "bridge" ] && return

	local PORTOPT=`echo ${OPTION} | sed -n "s/^port_\(\S*\)_\(\S*\)$/\1.\2/p"`
	local PORT=${PORTOPT%%.*}

	[ -z "${PORTOPT}" -o -z "${PORT}" ] && return

	uci -q set mstpd.${PORT}=bridge_port
	uci -q set mstpd.${PORTOPT}=${VALUE}
	uci -q delete mstpd.${CONFIG_SID}.${OPTION}
	MIGRATED=$((${MIGRATED} + 1))
}

config_load mstpd
[ "${MIGRATED}" = "0" ] && exit 0

uci commit mstpd
logger -t mstpd "Migrated ${MIGRATED} port option(s) to new config structure"

exit 0

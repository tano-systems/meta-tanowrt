##########################################################################
#
# Copyright (C) 2018-2020, Tano Systems,
# Anton Kikin <a.kikin@tano-systems.com>
#
##########################################################################

. "$IPKG_INSTROOT/lib/functions.sh"
. "$IPKG_INSTROOT/lib/functions/network.sh"
. "$IPKG_INSTROOT/usr/share/libubox/jshn.sh"

# The brctl executable
BRCTL_BIN='/usr/sbin/brctl'

# The mstpctl executable
MSTPCTL_BIN='/sbin/mstpctl'

# The mstpd executable
MSTPD_BIN='/sbin/mstpd'

# Tag for log
MSTPD_LOG_TAG="mstpd"

function mstpd_log_error()
{
	logger -p error -s -t "$MSTPD_LOG_TAG" $*
}

function mstpd_log_debug()
{
	:
	#logger -p debug -t "$MSTPD_LOG_TAG" $*
}

function mstpd_log_info()
{
	logger -p info -t "$MSTPD_LOG_TAG" $*
}

mstpd_sanity_checks()
{
	if [ ! -x "$MSTPCTL_BIN" ]; then
		mstpd_log_error "lib: 'mstpctl' binary does not exist or is not executable"
		exit 1
	fi

	if [ ! -x "$MSTPD_BIN" ]; then
		mstpd_log_error "lib: 'mstpd' binary does not exist or is not executable"
		exit 1
	fi

	if [ ! -x "$BRCTL_BIN" ]; then
		mstpd_log_error "lib: 'brctl' binary does not exist or is not executable"
		exit 1
	fi

	# Make sure this script is being run as root.
	if [ "$(id -u)" != '0' ]; then
		mstpd_log_error 'lib: script must be run as root'
		exit 1
	fi
}

mstpd_bridge_check()
{
	local bridges
	local br="$1"

	# Read the mstpd config
	config_load 'mstpd'
	config_get bridges 'global' 'bridge'

	if list_contains bridges "$br"; then
		# bridge is under MSTPd control
		return 0
	else
		# bridge is not under MSTPd control
		return 1
	fi
}

#
# Read current STP state for specified bridge:
#   0 - no STP or specified interface is not bridge
#   1 - kernel STP
#   2 - user STP
#
mstpd_bridge_stp_state()
{
	local br="$1"
	local state="0"

	if [ -e "/sys/class/net/${br}/bridge/stp_state" ]; then
		state=`cat /sys/class/net/${br}/bridge/stp_state`
	fi

	return $state
}

##########################################################################
#
# Configure bridge port by mstpctl tool
#
# Arguments:
#   $1 - bridge netifd interface name
#   $2 - bridge system interface name
#   $3 - port system interface name
#
# Example:
#   mstpd_configure_port lan br-lan sw1p1
#
##########################################################################
mstpd_configure_port()
{
	local br_ifname="$1"
	local br_sysname="$2"
	local br_port="$3"

	config_load 'mstpd'
	config_get mstpctl_portpathcost   "$br_port" "pathcost"
	config_get mstpctl_portadminedge  "$br_port" "adminedge"
	config_get mstpctl_portautoedge   "$br_port" "autoedge"
	config_get mstpctl_portp2p        "$br_port" "p2p"
	config_get mstpctl_portbpduguard  "$br_port" "bpduguard"
	config_get mstpctl_portbpdufilter "$br_port" "bpdufilter"
	config_get mstpctl_portrestrrole  "$br_port" "restrrole"
	config_get mstpctl_portrestrtcn   "$br_port" "restrtcn"
	config_get mstpctl_portnetwork    "$br_port" "network"
	config_get mstpctl_porttreeprio   "$br_port" "treeprio"
	config_get mstpctl_porttreecost   "$br_port" "treecost"

	if [ "$mstpctl_portpathcost" ]; then
		${MSTPCTL_BIN} setportpathcost "$br_sysname" "$br_port" "$mstpctl_portpathcost"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportpathcost $mstpctl_portpathcost"
	fi

	if [ "$mstpctl_portadminedge" ]; then
		${MSTPCTL_BIN} setportadminedge "$br_sysname" "$br_port" "$mstpctl_portadminedge"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportadminedge $mstpctl_portadminedge"
	fi

	if [ "$mstpctl_portautoedge" ]; then
		${MSTPCTL_BIN} setportautoedge "$br_sysname" "$br_port" "$mstpctl_portautoedge"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportautoedge $mstpctl_portautoedge"
	fi

	if [ "$mstpctl_portp2p" ]; then
		${MSTPCTL_BIN} setportp2p "$br_sysname" "$br_port" "$mstpctl_portp2p"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportp2p $mstpctl_portp2p"
	fi

	if [ "$mstpctl_portbpduguard" ]; then
		${MSTPCTL_BIN} setbpduguard "$br_sysname" "$br_port" "$mstpctl_portbpduguard"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setbpduguard $mstpctl_portbpduguard"
	fi

	if [ "$mstpctl_portbpdufilter" ]; then
		${MSTPCTL_BIN} setportbpdufilter "$br_sysname" "$br_port" "$mstpctl_portbpdufilter"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportbpdufilter $mstpctl_portbpdufilter"
	fi

	if [ "$mstpctl_portrestrrole" ]; then
		${MSTPCTL_BIN} setportrestrrole "$br_sysname" "$br_port" "$mstpctl_portrestrrole"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportrestrrole $mstpctl_portrestrrole"
	fi

	if [ "$mstpctl_portrestrtcn" ]; then
		${MSTPCTL_BIN} setportrestrtcn "$br_sysname" "$br_port" "$mstpctl_portrestrtcn"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportrestrtcn $mstpctl_portrestrtcn"
	fi

	if [ "$mstpctl_portnetwork" ]; then
		${MSTPCTL_BIN} setportnetwork "$br_sysname" "$br_port" "$mstpctl_portnetwork"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportnetwork $mstpctl_portnetwork"
	fi

	if [ "$mstpctl_porttreeprio" ]; then
		${MSTPCTL_BIN} settreeportprio "$br_sysname" "$br_port" 0 "$mstpctl_porttreeprio"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': settreeportprio $mstpctl_porttreeprio"
	fi

	if [ "$mstpctl_porttreecost" ]; then
		${MSTPCTL_BIN} settreeportcost "$br_sysname" "$br_port" 0 "$mstpctl_porttreecost"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': settreeportcost $mstpctl_porttreecost"
	fi

	if [ "$mstpctl_portpathcost" ]; then
		${MSTPCTL_BIN} setportpathcost "$br_sysname" "$br_port" "$mstpctl_portpathcost"
		mstpd_log_debug "lib: port '$br_sysname:$br_port': setportpathcost $mstpctl_portpathcost"
	fi
}

##########################################################################
#
# Configure bridge by mstpctl tool
#
# Arguments:
#   $1 - bridge system interface name
#
# Example:
#   mstpd_configure_bridge lan
#
##########################################################################
mstpd_configure_bridge()
{
	local br_sysname="$1"
	local br_ifname=${br_sysname/br-/}

	# Check that interface is really bridge
	if [ ! -d "/sys/class/net/$br_sysname/bridge" ]; then
		${MSTPCTL_BIN} delbridge "$br_sysname"
		mstpd_log_info "lib: bridge '$br_sysname' deleted (not a bridge)"
		return 1
	fi

	local if_type="$(uci_get network.${br_ifname}.type)"
	if [ "$if_type" != "bridge" ]; then
		${MSTPCTL_BIN} delbridge "$br_sysname"
		mstpd_log_info "lib: bridge '$br_sysname' deleted (not a bridge)"
		return 1
	fi

	# Check that bridge has enabled STP
	local if_stp="$(uci_get network.${br_ifname}.stp)"
	if [ "$if_stp" != "1" ] && [ "$if_stp" != "on" ]; then
		${MSTPCTL_BIN} delbridge "$br_sysname"
		mstpd_log_info "lib: bridge '$br_sysname' deleted (has no enabled STP)"
		mstpd_bridge_stp_state $br_sysname
		if [ "$?" != "0" ]; then
			# Disable STP on bridge
			mstpd_log_info "lib: brctl: disable STP on bridge '$br_sysname'..."
			${BRCTL_BIN} stp ${bridge_current} off
		fi

		return 1
	fi

	config_load 'mstpd'
	config_get mstpctl_forcevers   "$br_ifname" 'forcevers'
	config_get mstpctl_maxwait     "$br_ifname" 'maxwait'
	config_get mstpctl_ageing      "$br_ifname" 'ageing'
	config_get mstpctl_maxage      "$br_ifname" 'maxage'
	config_get mstpctl_treeprio    "$br_ifname" 'treeprio'
	config_get mstpctl_fdelay      "$br_ifname" 'fdelay'
	config_get mstpctl_maxhops     "$br_ifname" 'maxhops'
	config_get mstpctl_txholdcount "$br_ifname" 'txholdcount'
	config_get mstpctl_hello       "$br_ifname" 'hello'

	mstpd_bridge_stp_state ${br_sysname}
	if [ "$?" != "2" ]; then
		# STP state is kernel STP or disabled
		mstpd_log_info "lib: brctl: switch STP on bridge '$br_sysname' to user mode..."
		${BRCTL_BIN} stp ${br_sysname} off
		${BRCTL_BIN} stp ${br_sysname} on
		return 0
	fi

	${MSTPCTL_BIN} addbridge "$br_sysname"
	mstpd_log_debug "lib: bridge '$br_sysname' added"

	current_fdelay=`${MSTPCTL_BIN} showbridge "$br_sysname" bridge-forward-delay`
	current_maxage=`${MSTPCTL_BIN} showbridge "$br_sysname" bridge-max-age`

	if ! [ "$mstpctl_maxage" ]; then
		mstpctl_maxage=$current_maxage
	fi

	if ! [ "$mstpctl_fdelay" ]; then
		mstpctl_fdelay=$current_fdelay
	fi

	new=$((2 * ($mstpctl_fdelay - 1)))

	if [ "$new" -ge "$current_maxage" ]; then
		# (2 * (new_forward_delay - 1)) >= current_maxage
		${MSTPCTL_BIN} setfdelay "$br_sysname" "$mstpctl_fdelay"
		${MSTPCTL_BIN} setmaxage "$br_sysname" "$mstpctl_maxage"
	else
		# (2 * (new_forward_delay - 1)) < current_maxage
		${MSTPCTL_BIN} setmaxage "$br_sysname" "$mstpctl_maxage"
		${MSTPCTL_BIN} setfdelay "$br_sysname" "$mstpctl_fdelay"
	fi

	mstpd_log_debug "lib: bridge '$br_sysname': setmaxage $mstpctl_maxage"
	mstpd_log_debug "lib: bridge '$br_sysname': setfdelay $mstpctl_fdelay"

	if [ "$mstpctl_maxhops" ]; then
		${MSTPCTL_BIN} setmaxhops "$br_sysname" "$mstpctl_maxhops"
		mstpd_log_debug "lib: bridge '$br_sysname': setmaxhops $mstpctl_maxhops"
	fi

	if [ "$mstpctl_txholdcount" ]; then
		${MSTPCTL_BIN} settxholdcount "$br_sysname" "$mstpctl_txholdcount"
		mstpd_log_debug "lib: bridge '$br_sysname': settxholdcount $mstpctl_txholdcount"
	fi

	if [ "$mstpctl_forcevers" ]; then
		${MSTPCTL_BIN} setforcevers "$br_sysname" "$mstpctl_forcevers"
		mstpd_log_debug "lib: bridge '$br_sysname': setforcevers $mstpctl_forcevers"
	fi

	if [ "$mstpctl_treeprio" ]; then
		${MSTPCTL_BIN} settreeprio "$br_sysname" 0 "$mstpctl_treeprio"
		mstpd_log_debug "lib: bridge '$br_sysname': settreeprio $mstpctl_treeprio"
	fi

	if [ "$mstpctl_hello" ]; then
		${MSTPCTL_BIN} sethello "$br_sysname" "$mstpctl_hello"
		mstpd_log_debug "lib: bridge '$br_sysname': sethello $mstpctl_hello"
	fi

	if [ "$mstpctl_ageing" ]; then
		${MSTPCTL_BIN} setageing "$br_sysname" "$mstpctl_ageing"
		mstpd_log_debug "lib: bridge '$br_sysname': setageing $mstpctl_ageing"
	fi

	# Configure ports
	bridge_ports=`ls /sys/class/net/$br_sysname/brif/`

	for br_port in $bridge_ports; do
		mstpd_configure_port "$br_ifname" "$br_sysname" "$br_port"
		mstpd_log_info "lib: port '$br_sysname:$br_port' configured"
	done

	mstpd_log_info "lib: bridge '$br_sysname' configured"

	return 0
}

##########################################################################
#
# Check that mstpd service is running
#
##########################################################################
mstpd_is_running()
{
	if ! pidof mstpd >/dev/null; then
		return 1
	else
		return 0
	fi
}

##########################################################################
#
# Configure all controlled by mstpd bridges
# by mstpctl tool
#
##########################################################################
mstpd_configure()
{
	local only_bridge
	local mstpd_enabled
	local bridge
	local bridges
	local bridges_json
	local loglevel

	only_bridge="$1"

	mstpd_enabled="$(uci_get mstpd.global.enabled)"

	if [ "${mstpd_enabled}" = "0" ]; then
		mstpd_log_error "lib: mstpd is disabled"
		exit 2
	fi

	# Read the mstpd config
	config_load 'mstpd'
	config_get bridges 'global' 'bridge'
	config_get loglevel 'global' 'loglevel' 2

	# Setup new log level
	${MSTPCTL_BIN} debuglevel "$loglevel"

	# Read currently added to mstpd bridges list
	bridges_json="{\"bridges\":`${MSTPCTL_BIN} -f json showbridge`}"

	if [ "$?" = "0" ]; then
		local key keys
		local bridge_current
		local bridges_current

		bridges_current=""

		json_init
		json_load "$bridges_json"

		json_select "bridges"
			json_get_keys keys
			for key in $keys; do
				json_select "$key"
					json_get_vars bridge
					append bridges_current "$bridge" " "
				json_select ..
			done
		json_select ..

		# Delete bridges that is not exists in mstpd config
		for bridge_current in $bridges_current; do
			# If bridge is not in mstpd config then
			# delete bridge from mstpd
			if ! list_contains bridges "$bridge_current"; then
				${MSTPCTL_BIN} delbridge "$bridge_current"
				mstpd_log_info "lib: bridge '$bridge_current' deleted (not controlled)"

				# Check bridge STP state
				mstpd_bridge_stp_state $bridge_current
				if [ "$?" = "2" ]; then
					local br_ifname=${bridge_current/br-/}
					local if_stp="$(uci_get network.${br_ifname}.stp)"

					if [ "$if_stp" != "1" ] && [ "$if_stp" != "on" ]; then
						# Disable STP on bridge
						mstpd_log_info "lib: brctl: disable STP on bridge '$bridge_current'..."
						${BRCTL_BIN} stp ${bridge_current} off
					else
						# Current STP state is user STP, switch to kernel STP
						mstpd_log_info "lib: brctl: switch STP on bridge '$bridge_current' from user to kernel mode..."
						${BRCTL_BIN} stp ${bridge_current} off
						${BRCTL_BIN} stp ${bridge_current} on
					fi
				fi
			fi
		done
	fi

	# Configure/add bridges
	if [ -z "$only_bridge" ]; then
		# Configure all bridges
		for bridge in $bridges; do
			mstpd_configure_bridge ${bridge}
		done
	else
		# Configure only specified bridge
		mstpd_configure_bridge ${only_bridge}
	fi
}

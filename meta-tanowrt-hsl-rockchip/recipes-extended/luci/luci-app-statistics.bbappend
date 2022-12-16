#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:boardcon-em356x = ".rk0"
PR:append:rock-pi-s = ".rk0"

inherit uci-config

do_uci_config:append:rockchip() {
${UCI} batch <<-EOF
	# Enable cpu
	set luci_statistics.collectd_cpu.enable='1'
	set luci_statistics.collectd_cpu.ReportByState='1'
	set luci_statistics.collectd_cpu.ReportByCpu='1'

	#
	# Enable thermal zones monitoring
	#   thermal_zone0 - soc-thermal
	#   thermal_zone1 - logic-thermal
	#
	set luci_statistics.collectd_thermal=statistics
	set luci_statistics.collectd_thermal.enable='1'
	set luci_statistics.collectd_thermal.IgnoreSelected='0'
	del luci_statistics.collectd_thermal.Device
	add_list luci_statistics.collectd_thermal.Device='thermal_zone0'
	add_list luci_statistics.collectd_thermal.Device='thermal_zone1'

	commit luci_statistics
EOF
}

do_uci_config:append:boardcon-em356x() {
# Tweak default UCI configuration
${UCI} batch <<-EOF
	# Enable interfaces
	set luci_statistics.collectd_interface=statistics
	set luci_statistics.collectd_interface.enable='1'
	set luci_statistics.collectd_interface.IgnoreSelected='0'
	del luci_statistics.collectd_interface.Interfaces
	add_list luci_statistics.collectd_interface.Interfaces='eth0'
	add_list luci_statistics.collectd_interface.Interfaces='usb0'

	commit luci_statistics
EOF
}

do_uci_config:append:rock-pi-s() {
# Tweak default UCI configuration
${UCI} batch <<-EOF
	# Enable interfaces
	set luci_statistics.collectd_interface=statistics
	set luci_statistics.collectd_interface.enable='1'
	set luci_statistics.collectd_interface.IgnoreSelected='0'
	del luci_statistics.collectd_interface.Interfaces
	add_list luci_statistics.collectd_interface.Interfaces='eth0'

	commit luci_statistics
EOF
}

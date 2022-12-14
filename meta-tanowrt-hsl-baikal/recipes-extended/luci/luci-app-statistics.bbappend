#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_mitx = ".baikal0"

inherit uci-config

do_uci_config_append_mitx() {
# Tweak default UCI configuration
${UCI} batch <<-EOF
	# Enable cpufreq
	set luci_statistics.collectd_cpufreq=statistics
	set luci_statistics.collectd_cpufreq.enable='1'
	set luci_statistics.collectd_cpufreq.ExtraItems='1'

	# Enable cpu
	set luci_statistics.collectd_cpu.enable='1'
	set luci_statistics.collectd_cpu.ReportByState='1'
	set luci_statistics.collectd_cpu.ReportByCpu='1'

	# Enable sensors
	set luci_statistics.collectd_sensors=statistics
	set luci_statistics.collectd_sensors.enable='1'
	del luci_statistics.collectd_sensors.Sensor
	add_list luci_statistics.collectd_sensors.Sensor='pvt-baikal-isa-0000/voltage-in1'
	add_list luci_statistics.collectd_sensors.Sensor='pvt-baikal-isa-0000/temperature-temp1'

	commit luci_statistics
EOF
}

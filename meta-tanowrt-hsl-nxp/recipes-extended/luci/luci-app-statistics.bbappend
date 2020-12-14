#
# This file Copyright (c) 2020, Tano Systems LLC. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR_append_ls1028ardb = ".nxp2"

inherit uci-config

do_uci_config_append_ls1028ardb() {
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

	# Enable interfaces
	set luci_statistics.collectd_interface=statistics
	set luci_statistics.collectd_interface.enable='1'
	set luci_statistics.collectd_interface.IgnoreSelected='0'
	del luci_statistics.collectd_interface.Interfaces
	add_list luci_statistics.collectd_interface.Interfaces='eno0'
	add_list luci_statistics.collectd_interface.Interfaces='br-lan'

	# Enable sensors
	set luci_statistics.collectd_sensors=statistics
	set luci_statistics.collectd_sensors.enable='1'
	del luci_statistics.collectd_sensors.Sensor
	add_list luci_statistics.collectd_sensors.Sensor='ina220-i2c-4-40/voltage-in1'
	add_list luci_statistics.collectd_sensors.Sensor='ina220-i2c-4-40/current-curr1'
	add_list luci_statistics.collectd_sensors.Sensor='ina220-i2c-4-40/power-power1'
	add_list luci_statistics.collectd_sensors.Sensor='sa56004-i2c-5-4c/temperature-temp1'
	add_list luci_statistics.collectd_sensors.Sensor='sa56004-i2c-5-4c/temperature-temp2'

	commit luci_statistics
EOF
}

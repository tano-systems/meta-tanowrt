# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Native VXLAN Kernel support"
LICENSE = "GPLv2"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-iptunnel \
	kmod-udptunnel4 \
	${@bb.utils.contains('COMBINED_FEATURES', 'ipv6', 'kmod-udptunnel6', '', d)} \
"

#
# kmod-vxlan
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_VXLAN, \
	required            = y, \
}"

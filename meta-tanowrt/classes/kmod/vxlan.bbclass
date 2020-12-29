#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/iptunnel
inherit kmod/udptunnel4
inherit ${@bb.utils.contains('COMBINED_FEATURES', 'ipv6', 'kmod/udptunnel6', '', d)}

#
# kmod-vxlan
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_VXLAN, \
	required            = y, \
}"

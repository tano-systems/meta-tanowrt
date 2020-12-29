#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/udptunnel4
inherit ${@bb.utils.contains('COMBINED_FEATURES', 'ipv6', 'kmod/udptunnel6', '', d)}

DEPENDS += "wireguard-module"
RDEPENDS_${PN} += "kernel-module-wireguard"

#
# kmod-wireguard
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_NET, \
	required            = y, \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_CRYPTO_BLKCIPHER, \
	required            = y, \
}"

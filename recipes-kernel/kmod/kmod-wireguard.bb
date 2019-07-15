# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "WireGuard kernel module"
LICENSE = "GPLv2"

inherit kernel-kmod

RDEPENDS_${PN} += "\
	kmod-udptunnel4 \
	${@bb.utils.contains('COMBINED_FEATURES', 'ipv6', 'kmod-udptunnel6', '', d)} \
	wireguard-module \
"

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

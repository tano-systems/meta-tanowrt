#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018-2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

inherit kmod/ipt-core
inherit kmod/nfnetlink

#	CONFIG_IP_SET \
#	CONFIG_IP_SET_MAX=256 \
#	CONFIG_NETFILTER_XT_SET \
#	CONFIG_IP_SET_BITMAP_IP \
#	CONFIG_IP_SET_BITMAP_IPMAC \
#	CONFIG_IP_SET_BITMAP_PORT \
#	CONFIG_IP_SET_HASH_IP \
#	CONFIG_IP_SET_HASH_IPMARK \
#	CONFIG_IP_SET_HASH_IPPORT \
#	CONFIG_IP_SET_HASH_IPPORTIP \
#	CONFIG_IP_SET_HASH_IPPORTNET \
#	CONFIG_IP_SET_HASH_MAC \
#	CONFIG_IP_SET_HASH_NET \
#	CONFIG_IP_SET_HASH_NETNET \
#	CONFIG_IP_SET_HASH_NETIFACE \
#	CONFIG_IP_SET_HASH_NETPORT \
#	CONFIG_IP_SET_HASH_NETPORTNET \
#	CONFIG_IP_SET_LIST_SET \
#	CONFIG_NET_EMATCH_IPSET=n

#
# kmod-ipt-ipset
# ##############
#
KERNEL_CONFIG_DEPENDS += "{\
	option              = CONFIG_IP_SET, \
	required            = y|m, \
	m_rdepends          = kernel-module-ip-set, \
	m_autoload          = ip_set, \
	m_autoload_priority = 49, \
	m_autoload_script   = ipt-ipset \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option   = CONFIG_IP_SET_MAX, \
	required = 256 \
}"

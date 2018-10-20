# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Traffic schedulers"
LICENSE = "MIT"

inherit kernel-config-depends

#	CONFIG_NET_SCHED=y \
#	CONFIG_NET_SCH_HFSC \
#	CONFIG_NET_SCH_HTB \
#	CONFIG_NET_SCH_TBF \
#	CONFIG_NET_SCH_INGRESS \
#	CONFIG_NET_SCH_FQ_CODEL \
#	CONFIG_NET_CLS=y \
#	CONFIG_NET_CLS_ACT=y \
#	CONFIG_NET_CLS_FLOW \
#	CONFIG_NET_CLS_FW \
#	CONFIG_NET_CLS_ROUTE4 \
#	CONFIG_NET_CLS_TCINDEX \
#	CONFIG_NET_CLS_U32 \
#	CONFIG_NET_ACT_MIRRED \
#	CONFIG_NET_ACT_SKBEDIT \
#	CONFIG_NET_EMATCH=y \
#	CONFIG_NET_EMATCH_U32

#
# kmod-sched-core
# ###############
#
KERNEL_CONFIG_DEPENDS += "{\
	option = CONFIG_NET_SCHED, required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option = CONFIG_NET_CLS, required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option = CONFIG_NET_CLS_ACT, required = y \
}"

KERNEL_CONFIG_DEPENDS += "{\
	option = CONFIG_NET_EMATCH, required = y \
}"

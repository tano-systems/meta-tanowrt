# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"
SUMMARY = "Traffic schedulers"
LICENSE = "MIT"

inherit kernel-kmod

#
# kmod-sched-core
# ###############
#
KERNEL_CONFIG_DEPENDS += "\
	{ option = CONFIG_NET_SCHED,        required = y   } \
	{ option = CONFIG_NET_SCH_HFSC,     required = y|m } \
	{ option = CONFIG_NET_SCH_HTB,      required = y|m } \
	{ option = CONFIG_NET_SCH_TBF,      required = y|m } \
	{ option = CONFIG_NET_SCH_INGRESS,  required = y|m } \
	{ option = CONFIG_NET_SCH_FQ_CODEL, required = y|m } \
	{ option = CONFIG_NET_CLS,          required = y   } \
	{ option = CONFIG_NET_CLS_ACT,      required = y   } \
	{ option = CONFIG_NET_CLS_BASIC,    required = y|m } \
	{ option = CONFIG_NET_CLS_FLOW,     required = y|m } \
	{ option = CONFIG_NET_CLS_FW,       required = y|m } \
	{ option = CONFIG_NET_CLS_ROUTE4,   required = y|m } \
	{ option = CONFIG_NET_CLS_TCINDEX,  required = y|m } \
	{ option = CONFIG_NET_CLS_U32,      required = y|m } \
	{ option = CONFIG_NET_ACT_MIRRED,   required = y|m } \
	{ option = CONFIG_NET_ACT_SKBEDIT,  required = y|m } \
	{ option = CONFIG_NET_CLS_MATCHALL, required = y|m } \
	{ option = CONFIG_NET_EMATCH,       required = y   } \
	{ option = CONFIG_NET_EMATCH_U32,   required = y|m } \
"

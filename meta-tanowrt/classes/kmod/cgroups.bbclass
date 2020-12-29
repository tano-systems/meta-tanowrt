#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018, 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit kernel-kmod

#
# kmod-cgroups
# ############
#
KERNEL_CONFIG_DEPENDS += "\
	{ option = CONFIG_CGROUPS,            required = y }\
	{ option = CONFIG_CGROUP_FREEZER,     required = y }\
	{ option = CONFIG_CGROUP_DEVICE,      required = y }\
	{ option = CONFIG_CGROUP_CPUACCT,     required = y }\
	{ option = CONFIG_CGROUP_PERF,        required = y }\
	{ option = CONFIG_CGROUP_SCHED,       required = y }\
	{ option = CONFIG_CGROUP_NET_PRIO,    required = y }\
	{ option = CONFIG_CGROUP_PIDS,        required = y }\
	{ option = CONFIG_MEMCG,              required = y }\
	{ option = CONFIG_MEMCG_SWAP,         required = y }\
	{ option = CONFIG_MEMCG_SWAP_ENABLED, required = y }\
	{ option = CONFIG_BLK_CGROUP,         required = y }\
	{ option = CONFIG_CPUSETS,            required = y }\
	{ option = CONFIG_PROC_PID_CPUSET,    required = y }\
	{ option = CONFIG_RT_GROUP_SCHED,     required = y }\
	{ option = CONFIG_FAIR_GROUP_SCHED,   required = y }\
	{ option = CONFIG_NAMESPACES,         required = y }\
	{ option = CONFIG_UTS_NS,             required = y }\
	{ option = CONFIG_IPC_NS,             required = y }\
	{ option = CONFIG_USER_NS,            required = y }\
	{ option = CONFIG_PID_NS,             required = y }\
	{ option = CONFIG_NET_NS,             required = y }\

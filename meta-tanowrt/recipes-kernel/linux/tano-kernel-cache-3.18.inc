#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#

PR_append = ".5"

SRC_URI_append = "\
	file://600-netfilter_conntrack_flush.patch \
	file://610-netfilter_match_bypass_default_checks.patch \
	file://611-netfilter_match_bypass_default_table.patch \
	file://612-netfilter_match_reduce_memory_access.patch \
	file://613-netfilter_optional_tcp_window_check.patch \
	file://630-packet_socket_type.patch \
	file://640-bridge_no_eap_forward.patch \
	file://655-increase_skb_pad.patch \
	file://660-fq_codel_defaults.patch \
	file://661-fq_codel_keep_dropped_stats.patch \
	file://662-use_fq_codel_by_default.patch \
	file://663-remove_pfifo_fast.patch \
	file://681-NET-add-of_get_mac_address_mtd.patch \
"

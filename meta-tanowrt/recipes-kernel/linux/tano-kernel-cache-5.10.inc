#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".3"

KERNEL_FEATURES_append = "\
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/kernel-4.14+/usb-leds/ledtrig_usbport.scc", "" ,d)} \
	features/kernel-4.14+/fs/overlayfs-extra.scc \
	features/kernel-4.1+/vdso.scc \
	features/kernel-5.6+/wireguard/wireguard.scc \
"

KERNEL_FEATURES_append_qemuall = " features/drm-bochs/drm-bochs.scc "

#
# Disable CONFIG_SERIAL_OF_PLATFORM for x86 machines
# See https://lists.yoctoproject.org/g/linux-yocto/message/9207 and
# https://lists.yoctoproject.org/g/linux-yocto/message/9183
#
KERNEL_FEATURES_append_qemux86 = " features/general/serial/disable-CONFIG_SERIAL_OF_PLATFORM.scc "
KERNEL_FEATURES_append_qemux86-64 = " features/general/serial/disable-CONFIG_SERIAL_OF_PLATFORM.scc "

# OpenWrt patches
SRC_URI_append = "\
	file://600-netfilter_conntrack_flush.patch \
	file://610-netfilter_match_bypass_default_checks.patch \
	file://611-netfilter_match_bypass_default_table.patch \
	file://612-netfilter_match_reduce_memory_access.patch \
	file://613-netfilter_optional_tcp_window_check.patch \
	file://620-net_sched-codel-do-not-defer-queue-length-update.patch \
	file://630-packet_socket_type.patch \
	file://640-bridge-only-accept-EAP-locally.patch \
	file://655-increase_skb_pad.patch \
	file://660-fq_codel_defaults.patch \
	file://661-use_fq_codel_by_default.patch \
	file://662-remove_pfifo_fast.patch \
	file://681-NET-add-of_get_mac_address_mtd.patch \
"

# Tano patches
SRC_URI_append = "\
	file://0001-squashfs-generate-fake-UUID.patch \
"

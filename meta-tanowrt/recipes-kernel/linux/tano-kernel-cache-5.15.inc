#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

PR:append = ".1"

KERNEL_FEATURES:append = "\
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/kernel-4.14+/usb-leds/ledtrig_usbport.scc", "" ,d)} \
	features/kernel-4.14+/fs/overlayfs-extra.scc \
	features/kernel-4.1+/vdso.scc \
	features/kernel-5.6+/wireguard/wireguard.scc \
	features/kernel-5.3+/nf_tables/nf_tables.scc \
	${@bb.utils.contains("MACHINE_FEATURES", "psample", " features/kernel-4.11+/psample.scc", "" ,d)} \
"

KERNEL_FEATURES:append:qemuall = " features/drm-bochs/drm-bochs.scc "

#
# Disable CONFIG_SERIAL_OF_PLATFORM for x86 machines
# See https://lists.yoctoproject.org/g/linux-yocto/message/9207 and
# https://lists.yoctoproject.org/g/linux-yocto/message/9183
#
KERNEL_FEATURES:append:qemux86 = " features/general/serial/disable-CONFIG_SERIAL_OF_PLATFORM.scc "
KERNEL_FEATURES:append:qemux86-64 = " features/general/serial/disable-CONFIG_SERIAL_OF_PLATFORM.scc "

# OpenWrt patches
SRC_URI:append = "\
	file://600-netfilter_conntrack_flush.patch \
	file://610-netfilter_match_bypass_default_checks.patch \
	file://611-netfilter_match_bypass_default_table.patch \
	file://612-netfilter_match_reduce_memory_access.patch \
	file://613-netfilter_optional_tcp_window_check.patch \
	file://620-net_sched-codel-do-not-defer-queue-length-update.patch \
	file://630-packet_socket_type.patch \
	file://640-bridge-only-accept-EAP-locally.patch \
	file://655-increase_skb_pad.patch \
	file://910-kobject_uevent.patch \
	file://911-kobject_add_broadcast_uevent.patch \
"

# Tano patches
SRC_URI:append = "\
	file://0001-squashfs-generate-fake-UUID.patch \
"

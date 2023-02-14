#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020-2023 Tano Systems LLC. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".tano1.${INC_PR}"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:${THISDIR}/${PN}/fragments:"

# Do not apply some conflicted OE patches
SRC_URI:remove = "\
	file://busybox-udhcpc-no_deconfig.patch \
"

# Patches
SRC_URI:append = "\
	file://120-lto-jobserver.patch \
	file://200-udhcpc_reduce_msgs.patch \
	file://201-udhcpc_changed_ifindex.patch \
	file://210-add_netmsg_util.patch \
	file://220-add_lock_util.patch \
	file://270-libbb_make_unicode_printable.patch \
	file://301-ip-link-fix-netlink-msg-size.patch \
	file://500-move-traceroute-applets-to-bin.patch \
	file://510-move-passwd-applet-to-bin.patch \
	file://520-loginutils-handle-crypt-failures.patch \
	file://530-nslookup-ensure-unique-transaction-IDs-for-the-DNS-queries.patch \
"

# Own patches
SRC_URI:append = "\
	file://0002-hwclock-Fix-read_rtc-with-glibc-2.31-and-enabled-SHO.patch \
	file://0003-depmod-Fix-ignoring-.debug-directories.patch \
"

require busybox-openwrt.inc

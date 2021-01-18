#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020-2021 Tano Systems LLC. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".tano3.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:${THISDIR}/${PN}/fragments:"

# Patches
SRC_URI_append = "\
	file://100-trylink_bash.patch \
	file://101-gen_build_files_bash.patch \
	file://110-no_static_libgcc.patch \
	file://120-lto-jobserver.patch \
	file://130-mconf_missing_sigwinch.patch \
	file://200-udhcpc_reduce_msgs.patch \
	file://201-udhcpc_changed_ifindex.patch \
	file://210-add_netmsg_util.patch \
	file://220-add_lock_util.patch \
	file://230-add_nslookup_lede.patch \
	file://240-telnetd_intr.patch \
	file://250-date-k-flag.patch \
	file://270-libbb_make_unicode_printable.patch \
	file://301-ip-link-fix-netlink-msg-size.patch \
	file://500-move-traceroute-applets-to-bin.patch \
	file://510-move-passwd-applet-to-bin.patch \
	file://520-loginutils-handle-crypt-failures.patch \
	file://600-allow-ntpd-non-root.patch \
"

# Backports from 1.32
SRC_URI_append = "\
	file://0001-modprobe-Add-support-for-modprobe.blacklist-module1-.patch \
"

# Own patches
SRC_URI_append = "\
	file://0002-hwclock-Fix-read_rtc-with-glibc-2.31-and-enabled-SHO.patch \
"

require busybox-openwrt.inc

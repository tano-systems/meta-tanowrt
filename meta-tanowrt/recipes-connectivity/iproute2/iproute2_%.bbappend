#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2021 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano3"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# Files
SRC_URI += "\
	file://15-teql \
"

# Patches
SRC_URI += "\
	file://100-configure.patch \
	file://110-darwin_fixes.patch \
	file://115-add-config-xtlibdir.patch \
	file://120-no_arpd.patch \
	file://130-no_netem.patch \
	file://140-allow_pfifo_fast.patch \
	file://140-keep_libmnl_optional.patch \
	file://145-keep_libelf_optional.patch \
	file://150-keep_libcap_optional.patch \
	file://160-libnetlink-pic.patch \
	file://170-ip_tiny.patch \
	file://175-reduce-dynamic-syms.patch \
	file://180-drop_FAILED_POLICY.patch \
	file://200-drop_libbsd_dependency.patch \
	file://300-selinux-configurable.patch \
"

do_install_append() {
	# These files provided by base-files package
	rm -f ${D}${sysconfdir}/iproute2/ematch_map
	rm -f ${D}${sysconfdir}/iproute2/rt_protos
	rm -f ${D}${sysconfdir}/iproute2/rt_tables

	# Install hotplug script
	install -dm 0755 ${D}${sysconfdir}/hotplug.d/iface
	install -m 0755 ${WORKDIR}/15-teql ${D}${sysconfdir}/hotplug.d/iface/15-teql
}

do_configure[depends] += "virtual/kernel:do_shared_workdir"

# ${PN}-tc
inherit kmod/sched-core

FILES_${PN}-tc += "${sysconfdir}/hotplug.d/iface"


# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".tano13"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-openwrt/patches:${THISDIR}/${PN}-openwrt:"

PACKAGECONFIG ??= ""
PACKAGECONFIG[oeoveropenwrt] = ""

SRC_URI += "\
    file://fragment-lock.cfg \
    ${@bb.utils.contains('PACKAGECONFIG', 'oeoveropenwrt', '', 'file://fragment-noifupdown.cfg', d)} \
    file://220-add_lock_util.patch \
    file://z300-fix_off_t_misdetection_triggered_without_LFS.patch \
"

# Patches
SRC_URI_append = "\
	file://200-udhcpc_reduce_msgs.patch \
	file://201-udhcpc_changed_ifindex.patch \
	file://210-add_netmsg_util.patch \
	file://230-add_nslookup_lede.patch \
	file://250-date-k-flag.patch \
	file://301-ip-link-fix-netlink-msg-size.patch \
"

# Files
SRC_URI_append = "\
	file://cron \
	file://sysntpd \
	file://ntpd-hotplug \
	file://systohc.hotplug \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "busybox"

OPENWRT_SERVICE_SCRIPTS_busybox += "cron sysntpd"
OPENWRT_SERVICE_STATE_busybox-cron ?= "enabled"
OPENWRT_SERVICE_STATE_busybox-sysntpd ?= "enabled"

do_install_append() {
    rm -f ${D}/usr/share/udhcpc/default.script
    install -d -m 0755 ${D}${sysconfdir}/init.d
    install -d -m 0755 ${D}${sysconfdir}/hotplug.d/ntp
    install -d -m 0755 ${D}${sbindir}
    install -m 0755 ${WORKDIR}/cron ${D}${sysconfdir}/init.d/cron
    install -m 0755 ${WORKDIR}/sysntpd ${D}${sysconfdir}/init.d/sysntpd
    install -m 0755 ${WORKDIR}/ntpd-hotplug ${D}${sbindir}/ntpd-hotplug
    install -m 0755 ${WORKDIR}/systohc.hotplug ${D}${sysconfdir}/hotplug.d/ntp/00-systohc
}

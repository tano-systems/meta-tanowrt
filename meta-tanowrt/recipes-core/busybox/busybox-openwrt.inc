# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

INC_PR = "24"

SRC_URI_remove = "\
	${@bb.utils.contains('DISTRO_FEATURES', 'procd', 'file://syslog.cfg', '', d)} \
"

# Configuration fragments
SRC_URI_append = "\
	file://fragment-arp.cfg \
	file://fragment-chpasswd.cfg \
	file://fragment-chrt.cfg \
	file://fragment-no-fsck.cfg \
	file://fragment-lock.cfg \
	file://fragment-mpstat.cfg \
	file://fragment-pstree.cfg \
	file://fragment-no-ubirename.cfg \
	file://fragment-wall.cfg \
	file://fragment-noifupdown.cfg \
"

# Files
SRC_URI_append = "\
	file://cron \
	file://sysntpd \
	file://ntpd-hotplug \
	file://systohc.hotplug \
	file://watchdog.init \
	file://watchdog.config \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "busybox"

TANOWRT_SERVICE_SCRIPTS_busybox += "\
	cron \
	sysntpd \
	${@bb.utils.contains('COMBINED_FEATURES', 'watchdog', 'watchdog', '', d)} \
"
TANOWRT_SERVICE_STATE_busybox-cron ?= "enabled"
TANOWRT_SERVICE_STATE_busybox-sysntpd ?= "enabled"
TANOWRT_SERVICE_STATE_busybox-watchdog ?= "enabled"

do_install_append() {
	if grep "CONFIG_UDHCPC=y" ${B}/.config; then
		rm -f ${D}/usr/share/udhcpc/default.script
	fi

	install -d -m 0755 ${D}${sysconfdir}/config
	install -d -m 0755 ${D}${sysconfdir}/init.d
	install -d -m 0755 ${D}${sysconfdir}/hotplug.d/ntp
	install -d -m 0755 ${D}${sbindir}

	install -m 0755 ${WORKDIR}/sysntpd ${D}${sysconfdir}/init.d/sysntpd
	install -m 0755 ${WORKDIR}/ntpd-hotplug ${D}${sbindir}/ntpd-hotplug

	if grep "CONFIG_HWCLOCK=y" ${B}/.config; then
		install -m 0755 ${WORKDIR}/systohc.hotplug ${D}${sysconfdir}/hotplug.d/ntp/00-systohc
	fi

	if [ "${@bb.utils.contains('COMBINED_FEATURES', 'watchdog', '1', '0', d)}" == "1" ]; then
		install -m 0755 ${WORKDIR}/watchdog.init ${D}${sysconfdir}/init.d/watchdog
		install -m 0644 ${WORKDIR}/watchdog.config ${D}${sysconfdir}/config/watchdog
	fi

	if grep "CONFIG_CROND=y" ${B}/.config; then
		install -m 0755 ${WORKDIR}/cron ${D}${sysconfdir}/init.d/cron
		rm -f ${D}${sysconfdir}/init.d/busybox-cron
	fi
}

CONFFILES_${PN} += "${sysconfdir}/config/watchdog"

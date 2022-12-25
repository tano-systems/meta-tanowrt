#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2022 Anton Kikin <a.kikin@tano-systems.com>
#

INC_PR = "0"

SRC_URI:remove = "\
	${@bb.utils.contains('DISTRO_FEATURES', 'procd', 'file://syslog.cfg', '', d)} \
"

RDEPENDS:${PN} += "jsonpath"

# Configuration fragments
SRC_URI:append = "\
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
SRC_URI:append = "\
	file://sysntpd \
	file://ntpd-hotplug \
	file://systohc.hotplug \
	file://watchdog.init \
	file://watchdog.config \
	file://ntpd.capabilities \
	file://ntpd_acl.json \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "busybox"

TANOWRT_SERVICE_SCRIPTS_busybox += "\
	sysntpd \
	${@bb.utils.contains('COMBINED_FEATURES', 'watchdog', 'watchdog', '', d)} \
"
TANOWRT_SERVICE_STATE_busybox-sysntpd ?= "enabled"
TANOWRT_SERVICE_STATE_busybox-watchdog ?= "enabled"

do_install:append() {
	if grep "CONFIG_UDHCPC=y" ${B}/.config; then
		rm -f ${D}/usr/share/udhcpc/default.script
	fi

	install -d -m 0755 ${D}${sysconfdir}/config
	install -d -m 0755 ${D}${sysconfdir}/init.d
	install -d -m 0755 ${D}${sysconfdir}/hotplug.d/ntp
	install -d -m 0755 ${D}${sysconfdir}/capabilities
	install -d -m 0755 ${D}${datadir}/acl.d
	install -d -m 0755 ${D}${sbindir}

	install -m 0755 ${WORKDIR}/sysntpd ${D}${sysconfdir}/init.d/sysntpd
	install -m 0755 ${WORKDIR}/ntpd-hotplug ${D}${sbindir}/ntpd-hotplug

	install -m 0644 ${WORKDIR}/ntpd_acl.json ${D}${datadir}/acl.d/ntpd.json
	install -m 0644 ${WORKDIR}/ntpd.capabilities ${D}${sysconfdir}/capabilities/ntpd.json

	if grep "CONFIG_HWCLOCK=y" ${B}/.config; then
		install -m 0755 ${WORKDIR}/systohc.hotplug ${D}${sysconfdir}/hotplug.d/ntp/00-systohc
	fi

	if [ "${@bb.utils.contains('COMBINED_FEATURES', 'watchdog', '1', '0', d)}" == "1" ]; then
		install -m 0755 ${WORKDIR}/watchdog.init ${D}${sysconfdir}/init.d/watchdog
		install -m 0644 ${WORKDIR}/watchdog.config ${D}${sysconfdir}/config/watchdog
	fi
}

FILES:${PN} += "${datadir}/acl.d"

CONFFILES:${PN} += "${sysconfdir}/config/watchdog"

inherit useradd

USERADD_PACKAGES = "${PN}"

USERADD_PARAM:${PN} = "\
	--system \
	--home-dir /var/run/ntp \
	--no-create-home \
	--shell /bin/false \
	--gid ntp \
	--groups rtc \
	  ntp \
"

GROUPADD_PARAM:${PN} = "--system ntp; --system rtc"

#
# SPDX-License-Identifier: MIT
#
# This file Copyright (c) 2020 Tano Systems LLC. All rights reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI_append_class-target = "\
	file://dbus-launch \
	file://dbus.init \
"

EXTRA_OECONF += "\
	--disable-maintainer-mode \
	--disable-developer \
	--enable-debug=no \
	--enable-shared \
	--disable-static \
	--disable-verbose-mode \
	--disable-asserts \
	--disable-xml-docs \
	--disable-doxygen-docs \
	--disable-ducktype-docs \
	--disable-selinux \
	--disable-apparmor \
	--disable-libaudit \
	--enable-inotify \
	--disable-kqueue \
	--disable-console-owner-file \
	--disable-systemd \
	--disable-tests \
	--disable-code-coverage \
	--disable-x11-autolaunch \
	--with-session-socket-dir=/tmp \
	--with-system-socket=/var/run/dbus/system_bus_socket \
	--with-system-pid-file=/var/run/dbus.pid \
	--with-dbus-user=root \
	--without-x \
"

do_install_append_class-target() {
	# OpenWrt: rm -rf /usr/bin/dbus-monitor -> utils
	# OpenWrt: rm -rf /usr/bin/dbus-run-session -> utils
	# OpenWrt: rm -rf /usr/bin/dbus-send -> utils
	# OpenWrt: rm -rf /usr/bin/dbus-update-activation-environment -> utils

	install -d ${D}${bindir}
	install -m 0755 ${WORKDIR}/dbus-launch ${D}${bindir}/

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/dbus.init ${D}${sysconfdir}/init.d/dbus
}

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES_class-target ?= "dbus"
TANOWRT_SERVICE_SCRIPTS_dbus_class-target += "dbus"
TANOWRT_SERVICE_STATE_dbus-dbus_class-target ?= "enabled"

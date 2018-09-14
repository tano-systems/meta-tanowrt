# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano6"

DESCRIPTION = "OpenWrt filesystem utilities"
HOMEPAGE = "https://git.openwrt.org/?p=project/fstools.git;a=summary"
# libubi is under GPL
# libblkid-tiny is PD
# libfstools is LGPL
LICENSE = "LGPL-2.1 & GPL-2.0 & PD"
LIC_FILES_CHKSUM = "file://ubi.c;beginline=1;endline=17;md5=8ccc371d64f0b3a8d91065b678dc7095"
SECTION = "base"
DEPENDS += "util-linux ubus"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.openwrt.org/project/fstools.git;branch=master \
	file://0001-Define-GLOB_ONLYDIR-if-not-available.patch \
	file://0002-block-Validate-libubi_open-return-value.patch \
	file://0003-ubi-Add-forgotten-newlines-to-error-messages.patch \
	file://fstab.init \
	file://fstab.default \
	file://snapshot \
	file://mount.hotplug \
	file://blockd.init \
"

# 24.05.2018
# fstools: allow the mounting with full access time accounting
SRCREV = "dd02dad332958575cc52e60c78ef10ef2b5aaced"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "fstools"
OPENWRT_SERVICE_SCRIPTS_fstools += "fstab blockd"
OPENWRT_SERVICE_STATE_fstools-fstab ?= "enabled"
OPENWRT_SERVICE_STATE_fstools-blockd ?= "enabled"

EXTRA_OECMAKE += "${EXTRA_OECONF}"

# avoids build breaks when using no-static-libs.inc
DISABLE_STATIC = ""

PACKAGECONFIG ??= "extroot"

PACKAGECONFIG[extroot] = "-DCMAKE_UBIFS_EXTROOT=ON,,libubox uci,"

do_install_append() {
	install -dm 0755 ${D}/sbin
	ln -s /usr/sbin/mount_root ${D}/sbin/mount_root
	ln -s /usr/sbin/jffs2reset ${D}/sbin/jffs2reset

	install -dm 0755 ${D}/usr/sbin
	install -dm 0755 ${D}${sysconfdir}/init.d
	install -dm 0755 ${D}${sysconfdir}/uci-defaults
	install -dm 0755 ${D}${sysconfdir}/hotplug.d/block

	ln -s /usr/sbin/block ${D}/sbin/block
	ln -s /usr/sbin/snapshot_tool ${D}/sbin/snapshot_tool
	ln -s /usr/sbin/ubi ${D}/sbin/ubi
	ln -s /sbin/block ${D}/usr/sbin/swapon
	ln -s /sbin/block ${D}/usr/sbin/swapoff

	install -m 0755 ${WORKDIR}/fstab.init ${D}${sysconfdir}/init.d/fstab
	install -m 0755 ${WORKDIR}/fstab.default ${D}${sysconfdir}/uci-defaults/10-fstab
	install -m 0755 ${WORKDIR}/blockd.init ${D}${sysconfdir}/init.d/blockd
	install -m 0755 ${WORKDIR}/snapshot ${D}/sbin/snapshot
	install -m 0644 ${WORKDIR}/mount.hotplug ${D}${sysconfdir}/hotplug.d/block/10-mount
}

FILES_${PN} += "${libdir}/*"

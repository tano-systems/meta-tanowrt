# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano27"
SUMMARY = "procd is the new OpenWrt process management daemon written in C"
DESCRIPTION = "procd is VIRTUAL-RUNTIME-init_manager"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/procd.git;branch=master \
	file://reload_config \
	file://procd.sh \
"

# Patches
SRC_URI += "\
	file://0001-service-allow-to-change-service-s-current-directory.patch \
	file://0002-service-Add-initial-cgroup-support.patch \
	file://0003-service-Allow-to-configure-scheduler-attributes.patch \
	file://0004-hotplug-Remove-dev-prefix-from-DEVNAME-variable.patch \
	file://0005-hotplug-Completely-remove-hotplug-functionality.patch \
	file://0006-service-Add-SCHED_IDLE-scheduler-policy-support.patch \
	file://0007-rcS-Add-psplash-support.patch \
	file://0101-procd-Add-cached-and-available-to-memory-table.patch \
	file://0102-procd-Add-shared-and-slab-to-memory-table.patch \
"

PACKAGECONFIG ??= "\
	${@bb.utils.contains('COMBINED_FEATURES', 'cgroup', 'cgroup', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'psplash psplash-script-msg', '', d)} \
"

PACKAGECONFIG[cgroup] = "-DCGROUP_SUPPORT=1,,libcgroup"
PACKAGECONFIG[psplash] = "-DPSPLASH_SUPPORT=1,,"
PACKAGECONFIG[psplash-script-msg] = "-DPSPLASH_SCRIPT_MSG=1,,"

# 17.04.2019
# procd: copy the respawn property of new instance
SRCREV = "a30a8fdced45b79bae12662caf68ffb8597858b3"

S = "${WORKDIR}/git"

REQUIRED_DISTRO_FEATURES = "procd"
CONFLICT_DISTRO_FEATURES = "sysvinit systemd"
inherit distro_features_check

inherit cmake openwrt pkgconfig

SRCREV_openwrt = "${OPENWRT_SRCREV}"

do_install_append() {
	install -d ${D}${base_sbindir}
	install -m 0755 ${WORKDIR}/reload_config ${D}${base_sbindir}/

	install -d ${D}${sysconfdir}

	install -d ${D}${base_libdir}/functions
	install -m 0755 ${WORKDIR}/procd.sh ${D}${base_libdir}/functions/

	# Make sure things are where they are expected to be
	install -dm 0755 ${D}/sbin ${D}/lib
	ln -s /usr/sbin/procd ${D}/sbin/procd
	ln -s /usr/sbin/init ${D}/sbin/init
	ln -s /usr/sbin/askfirst ${D}/sbin/askfirst
	ln -s /usr/sbin/udevtrigger ${D}/sbin/udevtrigger
	ln -s /usr/sbin/upgraded ${D}/sbin/upgraded
	mv ${D}${libdir}/libsetlbf.so ${D}${base_libdir}/libsetlbf.so
	rmdir ${D}/usr/lib
}

RDEPENDS_${PN} += "\
	udev \
	fstools \
	base-files-scripts-openwrt \
	${PN}-inittab \
"

FILES_${PN} = "/"

ALTERNATIVE_${PN} = "init"

ALTERNATIVE_PRIORITY = "40"
ALTERNATIVE_TARGET[init] = "${base_sbindir}/init"

CONFFILES_${PN}_append = "\
	${sysconfdir}/hotplug.json \
"

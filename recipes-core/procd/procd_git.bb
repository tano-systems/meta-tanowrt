# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano17"
SUMMARY = "procd is the new OpenWrt process management daemon written in C"
DESCRIPTION = "procd is both VIRTUAL-RUNTIME-init_manager and\
               VIRTUAL_RUNTIME-dev_manager (like systemd/systemd-udev)\
              "
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	${GIT_OPENWRT_ORG_URL}/project/procd.git;branch=master \
	file://reload_config \
	file://hotplug.json \
	file://hotplug-preinit.json \
	file://procd.sh \
"

# Patches
SRC_URI += "\
	file://0001-service-allow-to-change-service-s-current-directory.patch \
	file://0002-service-Add-initial-cgroup-support.patch \
"

PACKAGECONFIG ??= "${@bb.utils.contains('COMBINED_FEATURES', 'cgroup', 'cgroup', '', d)}"
PACKAGECONFIG[cgroup] = "-DCGROUP_SUPPORT=1,,libcgroup"

# 23.11.2018
# early: set /tmp permissions explicitly
SRCREV = "94944ab099a071a82eb830a6ded4426319dc2c78"

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

	# Dev manager / hotplug / coldplug
	install -m 0644 ${WORKDIR}/hotplug.json ${D}${sysconfdir}/
	
	# Early init + dev manager / coldplug
	install -m 0644 ${WORKDIR}/hotplug-preinit.json ${D}${sysconfdir}/

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

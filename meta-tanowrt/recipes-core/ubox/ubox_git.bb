# Copyright (C) 2015, MentorGraphics
# Copyright (C) 2015, Fabio Berton <fabio.berton@ossystems.com.br>
# Copyright (C) 2017, Theodore A. Roth <theodore_roth@trimble.com>
# Copyright (C) 2018-2020, Anton Kikin <a.kikin@tano-systems.com>

PR = "tano13"
DESCRIPTION = "OpenWrt system helper toolbox"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubox"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://kmodloader.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SRCREV = "16f7e16181e2f3e9cf3e2ce56a7e291844900d09"
SECTION = "base"

DEPENDS = "ubus libubox uci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/ubox.git \
	file://log.init \
"

# 19.10.2020
# logd: self-degrade to 'logd' user after opening pipes
SRCREV = "9ef886819dd48303d8ced4cdbc9afbf32682535c"

S = "${WORKDIR}/git"

inherit cmake tanowrt-services

FILES_SOLIBSDEV = ""

TANOWRT_SERVICE_PACKAGES = "ubox"
TANOWRT_SERVICE_SCRIPTS_ubox += "log"
TANOWRT_SERVICE_STATE_ubox-log ?= "enabled"

do_install_append () {
	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', '1', '0', d)}" = "1" ]; then
		install -Dm 0755 ${WORKDIR}/log.init ${D}${sysconfdir}/init.d/log

		install -dm 0755 ${D}/sbin
		ln -s /usr/sbin/logd ${D}/sbin/logd
		ln -s /usr/sbin/logread ${D}/sbin/logread
		ln -s /usr/sbin/validate_data ${D}/sbin/validate_data
	fi

	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', '1', '0', d)}" = "1" ]; then
		install -dm 0755 ${D}/sbin
		install -dm 0755 ${D}/usr/sbin

		ln -s /sbin/rmmod    ${D}/usr/sbin/rmmod
		ln -s /sbin/insmod   ${D}/usr/sbin/insmod
		ln -s /sbin/lsmod    ${D}/usr/sbin/lsmod
		ln -s /sbin/modinfo  ${D}/usr/sbin/modinfo
		ln -s /sbin/modprobe ${D}/usr/sbin/modprobe

		ln -s /usr/sbin/kmodloader ${D}/sbin/kmodloader
		ln -s /sbin/kmodloader ${D}/sbin/rmmod
		ln -s /sbin/kmodloader ${D}/sbin/insmod
		ln -s /sbin/kmodloader ${D}/sbin/lsmod
#		ln -s /sbin/kmodloader ${D}/sbin/modinfo
#		ln -s /sbin/kmodloader ${D}/sbin/modprobe
	fi
}

RDEPENDS_${PN} += "\
	ubus \
	libubox \
"

FILES_${PN} = "\
	${libdir} \
	${sbindir} \
	${base_sbindir} \
	${sysconfdir} \
"

PACKAGES += "getrandom"
SUMMARY_getrandom = "OpenWrt getrandom system helper"
SECTION_getrandom = "base"
FILES_getrandom = "${bindir}/getrandom"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "\
	--system \
	-d /var/run/logd \
	--no-create-home \
	--shell /bin/false \
	-g logd \
	  logd \
"

GROUPADD_PARAM_${PN} = "--system logd"

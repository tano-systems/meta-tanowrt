# Copyright (C) 2015, MentorGraphics
# Copyright (C) 2015, Fabio Berton <fabio.berton@ossystems.com.br>
# Copyright (C) 2017, Theodore A. Roth <theodore_roth@trimble.com>
# Copyright (C) 2018-2022, Anton Kikin <a.kikin@tano-systems.com>

PR = "tano18"
DESCRIPTION = "OpenWrt system helper toolbox"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubox"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://kmodloader.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SRCREV = "16f7e16181e2f3e9cf3e2ce56a7e291844900d09"
SECTION = "base"

DEPENDS = "ubus libubox uci"

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}/patches:${THISDIR}/${BPN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/ubox.git;branch=master \
	file://log.init \
"

# Patches
SRC_URI += "\
	file://0001-kmodloader-Add-support-for-modprobe.blacklist-in-ker.patch \
"

# 27.07.2021
# logread: fix erroneous message "Logread connected to" with udp
SRCREV = "205defb597295a4a8966db3e618cfe41a29bed99"

S = "${WORKDIR}/git"

inherit cmake tanowrt-services

FILES_SOLIBSDEV = ""

TANOWRT_SERVICE_PACKAGES = "${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'ubox', '', d)}"
TANOWRT_SERVICE_SCRIPTS_ubox += "log"
TANOWRT_SERVICE_STATE_ubox-log ?= "enabled"

inherit update-alternatives

ALTERNATIVE:${PN} = "logread"
ALTERNATIVE_PRIORITY = "10"
ALTERNATIVE_LINK_NAME[logread] = "${sbindir}/logread"

do_configure:prepend () {
	if [ -e "${S}/CMakeLists.txt" ] ; then
		sed -i -e "s:ARCHIVE DESTINATION lib:ARCHIVE DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       -e "s:LIBRARY DESTINATION lib:LIBRARY DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       ${S}/CMakeLists.txt
	fi
}

do_install:append () {
	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', '1', '0', d)}" = "1" ]; then
		install -Dm 0755 ${WORKDIR}/log.init ${D}${sysconfdir}/init.d/log
		install -dm 0755 ${D}/${base_sbindir}
		ln -s ${sbindir}/logd ${D}${base_sbindir}/logd
		ln -s ${sbindir}/logread ${D}${base_sbindir}/logread
	fi

	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', '1', '0', d)}" = "1" ]; then
		install -dm 0755 ${D}${base_sbindir}
		install -dm 0755 ${D}${sbindir}

		ln -s ${base_sbindir}/rmmod    ${D}${sbindir}/rmmod
		ln -s ${base_sbindir}/insmod   ${D}${sbindir}/insmod
		ln -s ${base_sbindir}/lsmod    ${D}${sbindir}/lsmod
		ln -s ${base_sbindir}/modinfo  ${D}${sbindir}/modinfo
		ln -s ${base_sbindir}/modprobe ${D}${sbindir}/modprobe

		ln -s ${sbindir}/kmodloader ${D}${base_sbindir}/kmodloader
		ln -s ${base_sbindir}/kmodloader ${D}${base_sbindir}/rmmod
		ln -s ${base_sbindir}/kmodloader ${D}${base_sbindir}/insmod
		ln -s ${base_sbindir}/kmodloader ${D}${base_sbindir}/lsmod
	fi

	ln -s ${sbindir}/validate_data ${D}${base_sbindir}/validate_data
}

RDEPENDS:${PN} += "\
	ubus \
	libubox \
"

FILES:${PN} = "\
	${libdir} \
	${sbindir} \
	${base_sbindir} \
	${sysconfdir} \
"

PACKAGES += "getrandom"
SUMMARY:getrandom = "OpenWrt getrandom system helper"
SECTION:getrandom = "base"
FILES:getrandom = "${bindir}/getrandom"

inherit useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM:${PN} = "\
	--system \
	-d /var/run/logd \
	--no-create-home \
	--shell /bin/false \
	-g logd \
	  logd \
"

GROUPADD_PARAM:${PN} = "--system logd"

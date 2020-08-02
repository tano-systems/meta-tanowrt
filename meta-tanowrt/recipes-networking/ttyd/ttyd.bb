#
# Share your terminal over the web
# ttyd is a simple command-line tool for sharing terminal over the web, inspired by GoTTY.
# https://github.com/tsl0922/ttyd
#
# This file Copyright (c) 2018, 2020 Tano Systems LLC. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"
PV = "1.6.1+git${SRCPV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a9b415801f3f426a95d1da1f527882d"

inherit cmake

DEPENDS += "openssl json-c libwebsockets vim-xxd-native libcap libuv"
RDEPENDS_${PN} += "openssl-bin"

GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tsl0922/ttyd.git;protocol=${GIT_PROTOCOL}"
SRCREV = "079aa6864921a0aff31686012125b0ebbc41d5a8"

# Files
SRC_URI += "\
	file://ttyd.init \
	file://ttyd.config \
"

# Patches
SRC_URI += "\
	file://100-log-to-syslog.patch \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "ttyd"
TANOWRT_SERVICE_SCRIPTS_ttyd += "ttyd"
TANOWRT_SERVICE_STATE_ttyd-ttyd ?= "enabled"

do_install_append() {
	install -d -m 0755 ${D}${sysconfdir}/config
	install -d -m 0755 ${D}${sysconfdir}/init.d
	install -d -m 0755 ${D}${sysconfdir}/ttyd

	install -m 0755 ${WORKDIR}/ttyd.init ${D}${sysconfdir}/init.d/ttyd
	install -m 0644 ${WORKDIR}/ttyd.config ${D}${sysconfdir}/config/ttyd
}

S = "${WORKDIR}/git"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/ttyd \
	${sysconfdir}/ttyd/ttyd.crt \
	${sysconfdir}/ttyd/ttyd.key \
"

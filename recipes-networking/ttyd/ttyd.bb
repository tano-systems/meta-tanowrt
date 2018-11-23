#
# Share your terminal over the web
# ttyd is a simple command-line tool for sharing terminal over the web, inspired by GoTTY.
# https://github.com/tsl0922/ttyd
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano4"
PV = "1.4.2+git${SRCPV}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a9b415801f3f426a95d1da1f527882d"

inherit cmake

DEPENDS += "openssl json-c libwebsockets vim-xxd-native libcap libuv"

GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tsl0922/ttyd.git;protocol=${GIT_PROTOCOL}"
SRCREV = "53624a44fa92a4fc216eef9ed34b1c2073f537e2"

# Files
SRC_URI += "\
	file://ttyd.init \
	file://ttyd.config \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "ttyd"
OPENWRT_SERVICE_SCRIPTS_ttyd += "ttyd"
OPENWRT_SERVICE_STATE_ttyd-ttyd ?= "enabled"

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

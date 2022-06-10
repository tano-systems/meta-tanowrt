#
# SPDX-License-Identifier: MIT
#
# Based on original psplash_git.bb from OE core layer.
#
# This file Copyright (c) 2019-2020 Tano Systems. All Rights Reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
SUMMARY = "Userspace framebuffer boot logo based on usplash"
DESCRIPTION = "PSplash is a userspace graphical boot splash screen for mainly \
embedded Linux devices supporting a 16bpp or 32bpp framebuffer. It has few dependencies \
(just libc), supports basic images and text and handles rotation. Its visual look is \
configurable by basic source changes. Also included is a 'client' command utility for \
sending information to psplash such as boot progress information."
HOMEPAGE = "https://github.com/tano-systems/psplash-tn"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://psplash.h;beginline=1;endline=16;md5=8228f7969c37755b722da0419276f343"

DEPENDS += "libuci libubox"

PACKAGECONFIG ??= "png jpeg alive-gif"

PACKAGECONFIG[png] = "-DENABLE_PNG=ON,,libpng,"
PACKAGECONFIG[jpeg] = "-DENABLE_JPEG=ON,,jpeg,"
PACKAGECONFIG[alive-gif] = "-DENABLE_ALIVE_GIF=ON,,,"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RPROVIDES_${PN} = "psplash"
RREPLACES_${PN} = "psplash"
RCONFLICTS_${PN} = "psplash"

PSPLASH_TN_GIT_URI      ?= "gitsm://github.com/tano-systems/psplash-tn"
PSPLASH_TN_GIT_BRANCH   ?= "tano/master"
PSPLASH_TN_GIT_PROTOCOL ?= "https"
PSPLASH_TN_GIT_SRCREV   ?= "a2aeb2ece1eda22b395ccdf7f9ad6ca07b4875be"

SRCREV = "${PSPLASH_TN_GIT_SRCREV}"
PV = "0.1+git${SRCPV}"
PR = "tano13"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "${PSPLASH_TN_GIT_URI};branch=${PSPLASH_TN_GIT_BRANCH};protocol=${PSPLASH_TN_GIT_PROTOCOL} \
           file://psplash.init \
           file://psplash.config \
           file://psplash-tano.png \
           file://psplash-tano-alive.gif \
"

UPSTREAM_CHECK_COMMITS = "1"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "psplash-tn"
TANOWRT_SERVICE_SCRIPTS_psplash-tn ?= "psplash"
TANOWRT_SERVICE_STATE_psplash-tn-psplash ?= "enabled"

S = "${WORKDIR}/git"

inherit cmake pkgconfig update-alternatives

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_LINK_NAME[psplash] = "${bindir}/psplash"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/psplash.init ${D}${sysconfdir}/init.d/psplash

	install -d ${D}${sysconfdir}/config/
	install -m 0644 ${WORKDIR}/psplash.config ${D}${sysconfdir}/config/psplash

	install -d ${D}${datadir}/psplash
	install -m 0644 ${WORKDIR}/psplash-tano.png ${D}${datadir}/psplash/
	install -m 0644 ${WORKDIR}/psplash-tano-alive.gif ${D}${datadir}/psplash/
}

################################################################################

FILES_${PN} += "${sysconfdir}/psplash-init.d ${datadir}/psplash"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/psplash \
"

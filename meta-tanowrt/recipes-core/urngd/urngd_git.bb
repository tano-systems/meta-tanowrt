# SPDX-License-Identifier: MIT
# This file Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
PV = "1.0.2+git${SRCPV}"

SUMMARY = "OpenWrt non-physical true random number generator based on timing jitter"
DESCRIPTION = "urngd is OpenWrt's micro non-physical true random number generator based on \
timing jitter. \
\
Using the Jitter RNG core, the rngd provides an entropy source that feeds into \
the Linux /dev/random device if its entropy runs low. It updates the \
/dev/random entropy estimator such that the newly provided entropy unblocks \
/dev/random. \
\
The seeding of /dev/random also ensures that /dev/urandom benefits from \
entropy. Especially during boot time, when the entropy of Linux is low, the \
Jitter RNGd provides a source of sufficient entropy."

HOMEPAGE = "https://github.com/ynezz/openwrt-urngd"
SECTION = "base"
DEPENDS = "libubox"

LICENSE = "GPL-2.0 & BSD-3-Clause"
LIC_FILES_CHKSUM = "file://urngd.c;beginline=1;endline=38;md5=be8a7966f07b41d470e7d30065c98465"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	gitsm://${GIT_OPENWRT_ORG}/project/urngd.git;branch=master \
	file://urngd.init \
"

SRCREV = "c7f7b6b65b82eda4675b42d8cd28d76ea7aebf1a"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
  -DCMAKE_INSTALL_SBINDIR:PATH=${base_sbindir} \
"

inherit cmake tanowrt-services

TANOWRT_SERVICE_PACKAGES = "urngd"
TANOWRT_SERVICE_SCRIPTS_urngd += "urngd"
TANOWRT_SERVICE_STATE_urngd-urngd ?= "enabled"

do_install:append() {
	install -dm 0755 ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/urngd.init ${D}${sysconfdir}/init.d/urngd
}

FILES:${PN} = "\
	${sysconfdir} \
	${base_sbindir} \
"

RDEPENDS:${PN} += "libubox"

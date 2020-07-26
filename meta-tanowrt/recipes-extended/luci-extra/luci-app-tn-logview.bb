#
# Log viewer LuCI application
#
# Copyright (c) 2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "0.9.0+git${SRCPV}"
PR = "tano8"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SUMMARY = "Log viewer LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=35d2a22ecae06415d0af96aa3af51793"

RDEPENDS_${PN} += "syslog-fc"

GIT_URI      = "git://github.com/tano-systems/luci-app-tn-logview.git"
GIT_BRANCH   = "master"
GIT_PROTOCOL = "https"
GIT_SRCREV   = "96e615cb201225b1776733c20d400856c5a57abf"

SRC_URI = "${GIT_URI};branch=${GIT_BRANCH};protocol=${GIT_PROTOCOL}"
SRCREV = "${GIT_SRCREV}"

S = "${WORKDIR}/git"

LUCI_PKG_EXECUTABLE += "${D}/usr/libexec/luci-logview/*"

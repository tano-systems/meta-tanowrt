#
# Tano Systems LuCI Theme bitbake recipe
# Copyright (c) 2019-2020, Tano Systems. All Rights Reserved.
#
PR = "tano14"
PV = "0.0.9+git${SRCPV}"

SUMMARY = "LuCI Theme by Tano Systems"
HOMEPAGE = "https://github.com/tano-systems/luci-theme-tano"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=572ff0d89ff068a9a5f7b4bc844b09cc"

DEPENDS += "nodejs-native"

inherit openwrt-luci-theme

LUCI_THEME_NAME = "tano"

LUCI_THEME_TANO_GIT_URI      ?= "git://github.com/tano-systems/luci-theme-tano.git"
LUCI_THEME_TANO_GIT_BRANCH   ?= "master"
LUCI_THEME_TANO_GIT_PROTOCOL ?= "https"
LUCI_THEME_TANO_GIT_SRCREV   ?= "a109732f984001298cc7e3f323ef3d9eb461d75e"

SRC_URI = "${LUCI_THEME_TANO_GIT_URI};name=theme;branch=${LUCI_THEME_TANO_GIT_BRANCH};protocol=${LUCI_THEME_TANO_GIT_PROTOCOL}"
SRCREV_theme = "${LUCI_THEME_TANO_GIT_SRCREV}"
SRCREV_FORMAT = "theme"

S = "${WORKDIR}/git"
LUCI_PKG_SRC = "${S}/bundle/build"

do_npm_configure() {
	cd ${B}
	npm cache clean --force
	npm install
}

do_npm_compile() {
	cd ${B}
	npm --verbose run bundle
}

addtask npm_configure after do_patch before do_npm_compile
addtask npm_compile after do_npm_configure before do_install

# Make sure we have native npm ready when we start building
do_npm_configure[depends] += "nodejs-native:do_populate_sysroot"

#
# SPDX-License-Identifier: MIT
#
# LuCI theme class
#
# Copyright (c) 2018 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
inherit tanowrt-luci-pkg
inherit allarch

DEPENDS += "coreutils-native gawk-native"

SRC_URI_append = "\
	${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};name=lucigit;destsuffix=lucigit \
"
SRCREV_lucigit = "${LUCI_GIT_SRCREV}"

LUCI_THEME_NAME ?= ""
LUCI_THEME_STATIC_PATH ?= "${D}${LUCI_INSTALL_HTDOCS_PATH}/luci-static/${LUCI_THEME_NAME}"
LUCI_THEME_VIEW_PATH   ?= "${D}${LUCI_INSTALL_LUASRC_PATH}/view/themes/${LUCI_THEME_NAME}"

LUCI_SRC = "${WORKDIR}/lucigit"
LUCI_SRC_RESOURCES = "${LUCI_SRC}/modules/luci-base/htdocs/luci-static/resources"

do_install_append() {
	if [ -z "${LUCI_THEME_NAME}" ]; then
		bbfatal "LUCI_THEME_NAME variable value must be set"
	fi
}

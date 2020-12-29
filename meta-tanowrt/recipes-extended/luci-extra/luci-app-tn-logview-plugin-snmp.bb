#
# SPDX-License-Identifier: MIT
#
# SNMP plugin for Log viewer LuCI application
#
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "0.9.0"
PR = "tano0"

inherit allarch
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

inherit externalsrc

SUMMARY = "SNMP plugin for Log viewer LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=35d2a22ecae06415d0af96aa3af51793"

EXTERNALSRC = "${THISDIR}/luci-app-tn-logview-plugins/snmp"

RDEPENDS_${PN} += "luci-app-tn-logview"

LUCI_PKG_EXECUTABLE += "${D}/usr/libexec/luci-logview/*"

#
# SPDX-License-Identifier: MIT
#
# SNMP plugin for Log viewer LuCI application
#
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
require luci-app-tn-logview.inc

PR = "tano0"

SUMMARY = "SNMP plugin for Log viewer LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://plugins/snmp/LICENSE;md5=35d2a22ecae06415d0af96aa3af51793"

LUCI_PKG_SRC = "${S}/plugins/snmp"

RDEPENDS_${PN} += "luci-app-tn-logview"

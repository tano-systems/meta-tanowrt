#
# SPDX-License-Identifier: MIT
#
# VRRP plugin for Log viewer LuCI application
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
require luci-app-tn-logview.inc

PR = "tano0"

SUMMARY = "VRRP plugin for Log viewer LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://plugins/keepalived/LICENSE;md5=db0fccee3def547e17585ac94447d2fb"

LUCI_PKG_SRC = "${S}/plugins/keepalived"

RDEPENDS:${PN} += "luci-app-tn-logview"

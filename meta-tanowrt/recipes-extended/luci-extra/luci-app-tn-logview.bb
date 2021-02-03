#
# SPDX-License-Identifier: MIT
#
# Log viewer LuCI application
#
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
require luci-app-tn-logview.inc

RDEPENDS_${PN} += "syslog-fc"

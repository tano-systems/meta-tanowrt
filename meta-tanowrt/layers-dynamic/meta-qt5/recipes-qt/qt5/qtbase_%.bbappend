#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PACKAGECONFIG_append = " accessibility fontconfig tslib "
PACKAGECONFIG_append_qemuall = " examples linuxfb no-opengl "
PACKAGECONFIG_remove_qemuall = "tests"

RDEPENDS_${PN} += "qt5-env"

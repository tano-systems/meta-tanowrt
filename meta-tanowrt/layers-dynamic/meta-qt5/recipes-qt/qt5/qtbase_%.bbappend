#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PACKAGECONFIG:append = " accessibility fontconfig tslib "
PACKAGECONFIG:append:qemuall = " examples linuxfb no-opengl "
PACKAGECONFIG:remove:qemuall = "tests"

RDEPENDS:${PN} += "qt5-env"

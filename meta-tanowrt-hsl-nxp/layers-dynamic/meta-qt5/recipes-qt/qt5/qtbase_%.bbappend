#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append:ls1028ardb = ".nxp0"
PACKAGECONFIG:append:ls1028ardb = " examples linuxfb no-opengl "
PACKAGECONFIG:remove:ls1028ardb = "tests"

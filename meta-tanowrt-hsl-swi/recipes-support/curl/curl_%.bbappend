#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

PR:append = ".swi0"
PACKAGECONFIG:remove = "ssl"
PACKAGECONFIG:append = " openssl"

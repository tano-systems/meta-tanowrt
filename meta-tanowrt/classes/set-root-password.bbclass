#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2018-2019, 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
inherit extrausers

# Default root password (root)
ROOT_PASSWORD_SETUP ?= "1"
ROOT_PASSWORD_HASH ?= "\\$5\\$7S3jAYk0zcIXUBvy\\$lKb8brB8iYYNAr7pFG6GxhcqzeTCxEo0S5mNuMsOxD6"

# Setup root password
EXTRA_USERS_PARAMS:append = ' ${@oe.utils.conditional("ROOT_PASSWORD_SETUP", "1", "usermod -p '${ROOT_PASSWORD_HASH}' root", "", d)};'

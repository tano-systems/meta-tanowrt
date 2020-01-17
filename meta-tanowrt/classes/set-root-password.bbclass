#
# Copyright (c) 2018-2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
inherit extrausers

# Default root password
ROOT_PASSWORD_SETUP ?= "1"
ROOT_PASSWORD ?= "root"

# Setup root password
EXTRA_USERS_PARAMS_append = ' ${@oe.utils.conditional("ROOT_PASSWORD_SETUP", "1", "usermod -P \"${ROOT_PASSWORD}\" root", "", d)};'

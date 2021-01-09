#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
#
LUCI_GIT_URI      ?= "git://github.com/tano-systems/luci.git"
LUCI_GIT_BRANCH   ?= "tano/master"
LUCI_GIT_PROTOCOL ?= "git"

# 09.01.2021
LUCI_GIT_SRCREV ?= "a237e7acd0aca273a844c3b097c0dc5a36bf96e8"

PR_append = ".103"

libdir = "/usr/lib"

# Default distributive and LuCI name and version
LUCI_DISTNAME     ?= "Host System"
LUCI_DISTVERSION  ?= "SDK"
LUCI_NAME         ?= "LuCI"

# Languages to install
LUCI_LANGUAGES ?= "en"

# Initial startup language
LUCI_INITIAL_LANG ?= "auto"

# Initial mediaurlbase setting
LUCI_INITIAL_MEDIAURLBASE ?= "/luci-static/bootstrap"

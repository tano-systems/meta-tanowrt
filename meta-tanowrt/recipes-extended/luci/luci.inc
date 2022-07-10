#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2022 Tano Systems LLC. All rights reserved.
#
LUCI_GIT_URI      ?= "git://github.com/tano-systems/luci.git"
LUCI_GIT_BRANCH   ?= "tano/master"
LUCI_GIT_PROTOCOL ?= "git"

# 11.07.2022
LUCI_GIT_SRCREV ?= "07eb337c2b7503712c3661dc0e14619f51da12b7"

PR_append = ".133"

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

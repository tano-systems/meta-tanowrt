#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2022 Tano Systems LLC. All rights reserved.
#
LUCI_GIT_URI      ?= "git://github.com/tano-systems/luci.git"
LUCI_GIT_BRANCH   ?= "tano/master"
LUCI_GIT_PROTOCOL ?= "git"

# 01.04.2022
LUCI_GIT_SRCREV ?= "2a9b4e23b7a103ec3d45f1504d09d7af2b18953c"

PR_append = ".126"

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


LUCI_GIT_URI      ?= "git://github.com/tano-systems/luci.git"
LUCI_GIT_BRANCH   ?= "tano/master"
LUCI_GIT_PROTOCOL ?= "git"

# 06.08.2020
LUCI_GIT_SRCREV ?= "64e0e75c6205ac1a77ceb76e6d0130e49312442a"

PR_append = ".81"

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

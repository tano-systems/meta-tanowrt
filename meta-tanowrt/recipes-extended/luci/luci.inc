
LUCI_GIT_URI      ?= "git://github.com/tano-systems/luci.git"
LUCI_GIT_BRANCH   ?= "tano/master"
LUCI_GIT_PROTOCOL ?= "git"

# 17.06.2020
LUCI_GIT_SRCREV ?= "48859ab7d47fc0a969f634e77c4c34176815597f"

PR_append = ".73"

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

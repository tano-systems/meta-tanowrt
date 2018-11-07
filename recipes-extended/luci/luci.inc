
LUCI_GIT_URI      = "git://github.com/openwrt/luci.git"
LUCI_GIT_BRANCH   = "master"
LUCI_GIT_PROTOCOL = "git"

# 07.11.2018
# i18n: sync translations, add location annotatations
LUCI_GIT_SRCREV = "40de466475a617f42d1f5cfa03d0342676e84c9b"

PR_append = ".12"

libdir = "/usr/lib"

# Default distributive and LuCI name and version
LUCI_DISTNAME     ?= "Host System"
LUCI_DISTVERSION  ?= "SDK"
LUCI_NAME         ?= "LuCI"
LUCI_VERSION      ?= "SVN"

# Languages to install
LUCI_LANGUAGES ?= "en"

# Initial startup language
LUCI_INITIAL_LANG ?= "auto"

# Initial mediaurlbase setting
LUCI_INITIAL_MEDIAURLBASE ?= "/luci-static/bootstrap"

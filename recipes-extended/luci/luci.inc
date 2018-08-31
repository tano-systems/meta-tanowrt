
LUCI_GIT_URI      = "git://github.com/openwrt/luci.git"
LUCI_GIT_BRANCH   = "openwrt-18.06"
LUCI_GIT_PROTOCOL = "git"

# 23.08.2018
# luci-app-adblock: fix chinese translation
LUCI_GIT_SRCREV = "650375611f5fa6e53327cdfdba8f087c6c601db0"

PR_append = ".7"

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

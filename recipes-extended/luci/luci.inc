
LUCI_GIT_URI      = "git://github.com/openwrt/luci.git"
LUCI_GIT_BRANCH   = "lede-17.01"
LUCI_GIT_PROTOCOL = "git"

# 28.04.2018 8:48:39 (branch lede-17.01)
LUCI_GIT_SRCREV = "cfba791c81b58212111de8df5f627b4ec8e1c636"

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

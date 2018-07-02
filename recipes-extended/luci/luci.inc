
LUCI_GIT_URI      = "git://github.com/openwrt/luci.git"
LUCI_GIT_BRANCH   = "lede-17.01"
LUCI_GIT_PROTOCOL = "git"

# 29.06.2018 luci-app-transmission: fix typo
LUCI_GIT_SRCREV = "b78664c2cd4046a1e77285834b71f9523c91afe7"

PR_append = ".1"

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


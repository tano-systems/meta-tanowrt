
LUCI_GIT_URI      = "git://github.com/openwrt/luci.git"
LUCI_GIT_BRANCH   = "openwrt-18.06"
LUCI_GIT_PROTOCOL = "git"

# Latest
LUCI_GIT_SRCREV = "4ba85e3d82b684262c570e38a72d2dc3bb712a13"

PR_append = ".2"

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


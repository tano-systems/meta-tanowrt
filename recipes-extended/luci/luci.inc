FILESEXTRAPATHS_prepend := "${THISDIR}/patches:${THISDIR}/files:"

SRC_URI += "\
	git://github.com/openwrt/luci.git;branch=lede-17.01 \
	file://cmake.patch \
	file://0001-Fixed-luci.sys.user.setpasswd.patch \
	file://0002-Use-etc-localtime-with-zoneinfo-instead-of-etc-TZ.patch \
	file://0003-Invoke-reload_config-on-apply-at-uci-changes-page.patch \
"

# 28.04.2018 8:48:39 (branch lede-17.01)
SRCREV = "cfba791c81b58212111de8df5f627b4ec8e1c636"

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

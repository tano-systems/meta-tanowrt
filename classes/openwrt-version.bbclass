#
# OpenWrt base configuration
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE, universal_newlines=True).communicate()[0].rstrip()
    except OSError:
        return None

#
# These options allow to override the version information embedded in
# the /etc/openwrt_version, /etc/openwrt_release, /etc/banner,
# /etc/opkg.conf, and /etc/os-release files. Usually there is no need
# to set these, but they're useful for release builds or custom OpenWrt
# redistributions that should carry custom version tags.
#

#
# Release distribution
#
# This is the name of the release distribution.
#
OPENWRT_VERSION_DIST ?= "OpenWrt"

#
# Release version number
#
# This is the release version number embedded in the image.
# If unspecified, it defaults to SNAPSHOT for the master branch
# or to ##.##-SNAPSHOT on release branches.
#
OPENWRT_VERSION_NUMBER ?= "18.06-SNAPSHOT"

#
# Release version code
#
# This is the release version code embedded in the image.
# If unspecified, it defaults to a revision number describing the
# repository version of the source, e.g. the number of commits
# since a branch point or a short Git commit ID.
#
OPENWRT_VERSION_SRCREV ?= "${@get_git_revision('${TANO_OPENWRT_BASE}')}"
OPENWRT_VERSION_CODE ?= "${OPENWRT_VERSION_SRCREV}"

#
# Release repository
#
# This is the repository address embedded in the image, it defaults
# to the trunk snapshot repo; the url may contain the following placeholders:
#
OPENWRT_VERSION_REPO ?= "https://github.com/tano-systems/meta-tano-openwrt"

#
# Release Homepage"
#
# This is the release version homepage
#
OPENWRT_VERSION_HOME_URL ?= "https://github.com/tano-systems/meta-tano-openwrt"

#
# Manufacturer name
#
# This is the manufacturer name embedded in /etc/device_info
# Useful for OEMs building OpenWrt based firmware
#
OPENWRT_VERSION_MANUFACTURER ?= "OpenWrt"

#
# Manufacturer URL
#
# This is an URL to the manufacturer's website embedded in /etc/device_info
# Useful for OEMs building OpenWrt based firmware
#
OPENWRT_VERSION_MANUFACTURER_URL ?= "http://openwrt.org/"

#
# Bug reporting URL
#
# This is an URL to provide users for providing bug reports
#
OPENWRT_VERSION_BUG_URL ?= "https://github.com/tano-systems/meta-tano-openwrt/issues"

#
# Support URL
#
# This an URL to provide users seeking support
#
OPENWRT_VERSION_SUPPORT_URL ?= ""

#
# Product name
#
# This is the product name embedded in /etc/device_info
# Useful for OEMs building OpenWrt based firmware
#
OPENWRT_VERSION_PRODUCT ?= "Generic"

#
# Hardware revision
#
# This is the hardware revision string embedded in /etc/device_info
# Useful for OEMs building OpenWrt based firmware
#
OPENWRT_VERSION_HWREV ?= "v0"

#
# Lowered-underscored versions for certain variables
#   "LEDE 17.01-SNAPSHOT" => "lede_17.01-snapshot"
#
OPENWRT_VERSION_NUMBER_LU="${@d.getVar('OPENWRT_VERSION_NUMBER', True).replace(' ', '_').lower()}"
OPENWRT_VERSION_CODE_LU="${@d.getVar('OPENWRT_VERSION_CODE', True).replace(' ', '_').lower()}"
OPENWRT_VERSION_DIST_LU="${@d.getVar('OPENWRT_VERSION_DIST', True).replace(' ', '_').lower()}"

#
# VERSION_SED script
#
# %R .. Repository revision ID
# %V .. Configured release version number or "SNAPSHOT", uppercase
# %v .. Configured release version number or "snapshot", lowercase
# %C .. Configured release revision code or value of %R, uppercase
# %c .. Configured release revision code or value of %R, lowercase
# %D .. Distribution name or "Lede", uppercase
# %d .. Distribution name or "lede", lowercase
# %T .. Target name
# %S .. Target/Subtarget name
# %A .. Package architecture
# %t .. Build taint flags, e.g. "no-all busybox"
# %M .. Manufacturer name or "Lede"
# %P .. Product name or "Generic"
# %h .. Hardware revision or "v0"
# %u    Home URL
#
OPENWRT_VERSION_SED = "sed -i \
	-e 's,%U,${OPENWRT_VERSION_REPO},g' \
	-e 's,%V,${OPENWRT_VERSION_NUMBER},g' \
	-e 's,%v,${OPENWRT_VERSION_NUMBER_LU},g' \
	-e 's,%C,${OPENWRT_VERSION_CODE},g' \
	-e 's,%c,${OPENWRT_VERSION_CODE_LU},g' \
	-e 's,%D,${OPENWRT_VERSION_DIST},g' \
	-e 's,%d,${OPENWRT_VERSION_DIST_LU},g' \
	-e 's,%R,${OPENWRT_VERSION_SRCREV},g' \
	-e 's,%T,${MACHINE},g' \
	-e 's,%S,${MACHINE},g' \
	-e 's,%A,${TARGET_ARCH},g' \
	-e 's,%t,,g' \
	-e 's,%M,${OPENWRT_VERSION_MANUFACTURER},g' \
	-e 's,%m,${OPENWRT_VERSION_MANUFACTURER_URL},g' \
	-e 's,%b,${OPENWRT_VERSION_BUG_URL},g' \
	-e 's,%u,${OPENWRT_VERSION_HOME_URL},g' \
	-e 's,%s,${OPENWRT_VERSION_SUPPORT_URL},g' \
	-e 's,%P,${OPENWRT_VERSION_PRODUCT},g' \
	-e 's,%h,${OPENWRT_VERSION_HWREV},g'\
"

do_install[vardeps] += "\
	OPENWRT_VERSION_REPO \
	OPENWRT_VERSION_NUMBER \
	OPENWRT_VERSION_CODE \
	OPENWRT_VERSION_DIST \
	OPENWRT_VERSION_SRCREV \
	OPENWRT_VERSION_MANUFACTURER \
	OPENWRT_VERSION_MANUFACTURER_URL \
	OPENWRT_VERSION_BUG_URL \
	OPENWRT_VERSION_HOME_URL \
	OPENWRT_VERSION_SUPPORT_URL \
	OPENWRT_VERSION_PRODUCT \
	OPENWRT_VERSION_HWREV \
"

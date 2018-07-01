#
# LuCI Support for Dynamic DNS Client (ddns-scripts)
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano1"

LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

require recipes-extended/luci/luci.inc

inherit openwrt-luci-app
inherit openwrt-luci-i18n

# +luci-mod-admin-full
RDEPENDS_${PN} += "ddns-scripts"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-ddns;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

SRC_URI_append = "\
	file://0900-luci-app-ddns-Fix-russian-translation.patch \
"

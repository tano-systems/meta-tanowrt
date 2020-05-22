#
# LuCI Support for Dynamic DNS Client (ddns-scripts)
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano9"

SUMMARY = "LuCI Support for Dynamic DNS Client (ddns-scripts)"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

# +luci-mod-admin-full
RDEPENDS_${PN} += "ddns-scripts luci-lib-ipkg luci-mod-admin-full"

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-ddns;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

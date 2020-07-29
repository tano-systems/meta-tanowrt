#
# LuCI Support for ledtrigger usbport
#
# This file Copyright (c) 2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano2"

SUMMARY = "LuCI Support for ledtrigger usbport"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

inherit kernel-config

# Wait for kernel shared work directory ready
do_package[depends] += "virtual/kernel:do_shared_workdir"
python populate_packages_prepend() {
    result = kernel_version_compare('4.14', d)
    if result < 0:
        bb.fatal('Package %s requires kernel version >= 4.14' % d.getVar('PN', True))
}

inherit kmod/usb-ledtrig-usbport

inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

SRC_URI = "${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};subpath=applications/luci-app-ledtrig-usbport;destsuffix=git/"
SRCREV = "${LUCI_GIT_SRCREV}"
S = "${WORKDIR}/git"

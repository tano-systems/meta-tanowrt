#
# This file Copyright (c) 2018, Tano Systems
# Anton Kikin <a.kikin@tano-systems.com>
#
DESCRIPTION = "Kommand Line Interface Shell"
SECTION = "console/utils"
PR = "tano0"

LICENSE = "klish"
LIC_FILES_CHKSUM = "file://LICENCE;md5=763c2d89173e7008fab9b7ecf2605ab5"

PACKAGECONFIG ??= "libxml2"

PACKAGECONFIG[libexpat] = "--with-libexpat,--without-libexpat,expat"
PACKAGECONFIG[libxml2] = "--with-libxml2,--without-libxml2,libxml2"

SRC_URI = "git://src.libcode.org/pkun/klish.git;tag=${PV};protocol=https"

SRC_URI[md5sum] = "57a0b39b6e0a450007ee9e0b2ff50f03"
SRC_URI[sha256sum] = "a89dd1027dce713407b6d68e836c8fdead56406dcfc650da84da8e0b92c9b2e5"

S = "${WORKDIR}/git"

FILES_${PN} += "/usr/lib/*.so"

inherit autotools

#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
SRC_URI_remove = "https://github.com/zeux/pugixml/archive/v${PV}.zip;name=pugixml-v1.8.zip"
SRC_URI_append = "git://github.com/zeux/pugixml.git"
SRCREV = "91bf70231aae8eddaccd5a6f5b0833712c471cb3"
S = "${WORKDIR}/git"

rm_makefile() {
    rm -f ${S}/Makefile
}

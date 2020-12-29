#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
SRC_URI_remove = "https://github.com/zeux/pugixml/archive/v${PV}.zip;name=pugixml-v1.4.zip"
SRC_URI_append = "git://github.com/zeux/pugixml.git"
SRCREV = "9f92eeba44cdf9df5faae0b03cc5bfb445793348"
S = "${WORKDIR}/git"

rm_makefile() {
    rm -f ${S}/Makefile
}

#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#

DESCRIPTION = "schedtool-dl (scheduler test tool) for deadline scheduler"
SECTION = "devel"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=dc1f51f7ca94aebffb9b3663d82873ec"

SRC_URI = "git://github.com/jlelli/schedtool-dl.git;protocol=https;branch=master \
           file://0001-schedtool-dl-add-flags-to-parameters-of-sched_setattr.patch \
          "
SRCREV = "3ffb479929c31cbae09de08f94f58b8f0f061d91"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "'CC=${CC}'"

TARGET_CC_ARCH += "${LDFLAGS}"

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}${bindir}
	install -m 0755 schedtool ${D}${bindir}
}

FILES:{PN} = "${bindir}/schedtool" 

PARALLEL_MAKE = ""

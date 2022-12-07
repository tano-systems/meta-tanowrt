#
# SPDX-License-Identifier: MIT
#
# qperf - Measure socket and RDMA performance
# This file Copyright (c) 2019, Anton Kikin <a.kikin@tano-systems.com>
#
DESCRIPTION = "Measure socket and RDMA performance"
HOMEPAGE = "https://github.com/linux-rdma/qperf"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"
SECTION = "console/network"

SRC_URI = "git://github.com/linux-rdma/qperf.git;branch=master;protocol=https"
SRCREV = "118a571efb3ef5305fdacccf596ad5eeefd99d92"

PR = "tano0"
PV = "0.4.11-git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools-brokensep

do_configure() {
	./cleanup
	./autogen.sh
	oe_runconf
}

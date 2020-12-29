#
# SPDX-License-Identifier: MIT
#
# sockperf - network benchmarking utility over socket API that was designed
# for testing performance (latency and throughput) of high-performance systems.
#
# This file Copyright (c) 2019, Anton Kikin <a.kikin@tano-systems.com>
#

DESCRIPTION = "Tool for network performance measurement written in C++"
HOMEPAGE = "https://github.com/Mellanox/sockperf"
LICENSE = "Mellanox-sockperf"
LIC_FILES_CHKSUM = "file://copying;md5=eacf3cc30a6506d311e96d2d44197d3e"
SECTION = "console/network"

SRC_URI = "git://github.com/Mellanox/sockperf;branch=sockperf_v2"
SRCREV = "13ced4c1eb0dccf434aa0fde44994c0dbfbca1f9"

PR = "tano1"
PV = "3.6-git${SRCPV}"

S = "${WORKDIR}/git"

inherit autotools

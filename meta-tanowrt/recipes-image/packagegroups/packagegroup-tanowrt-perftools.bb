#
# SPDX-License-Identifier: MIT
# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano1"
SUMMARY = "Performance testing utilities"
DESCRIPTION = "The set of packages required for performance tests"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
	iperf2 \
	iftop \
	tinymembench \
"

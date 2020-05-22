# Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Performance testing utilities"
DESCRIPTION = "The set of packages required for performance tests"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup tanowrt

# packagegroup-tanowrt-perftools
RDEPENDS_${PN} = "\
	iperf2 \
	iftop \
	tinymembench \
"

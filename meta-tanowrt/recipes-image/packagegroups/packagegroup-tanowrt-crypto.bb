#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PR = "tano0"
SUMMARY = "Crypto support packages"
DESCRIPTION = "The set of packages required for a crypto support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS:${PN} = "\
	cryptodev-module \
	openssl \
	openssl-bin \
	openssl-conf \
	openssl-engines \
"

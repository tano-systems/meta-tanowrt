# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"
SUMMARY = "Crypto support packages"
DESCRIPTION = "The set of packages required for a crypto support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
	cryptodev-module \
	openssl \
	openssl-bin \
	openssl-conf \
	openssl-engines \
"

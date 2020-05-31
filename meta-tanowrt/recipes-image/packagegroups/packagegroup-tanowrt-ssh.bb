# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"
SUMMARY = "SSH support packages"
DESCRIPTION = "The set of packages required for a SSH support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
	dropbear \
	openssh-sftp-server \
"

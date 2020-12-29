#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019 Tano Systems LLC. All rights reserved.
#

PR = "tano2"
SUMMARY = "Cgroup support packages"
DESCRIPTION = "The set of packages required for a cgroups support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

inherit kmod/cgroups

RDEPENDS_${PN} = "\
	libcgroup \
	libcgroup-procd \
"

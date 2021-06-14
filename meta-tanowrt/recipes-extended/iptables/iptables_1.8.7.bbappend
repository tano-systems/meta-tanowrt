#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}/patches:${THISDIR}/${PN}_${PV}/files:"

PR_append = ".tano0"

SRC_URI += "\
	file://010-add-set-dscpmark-support.patch \
	file://101-remove-check-already.patch \
	file://102-iptables-disable-modprobe.patch \
	file://103-optional-xml.patch \
	file://200-configurable_builtin.patch \
	file://600-shared-libext.patch \
	file://700-disable-legacy-revisions.patch \
	file://800-flowoffload_target.patch \
"

require iptables.inc

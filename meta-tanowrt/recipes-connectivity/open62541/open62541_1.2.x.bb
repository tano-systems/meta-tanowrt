#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
# open62541_git.bb
#
# open62541 is an open source and free implementation of OPC UA
# (OPC Unified Architecture) written in the common subset of the
# C99 and C++98 languages
#
require open62541.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}_1.2.x:"

SRC_URI += "file://0002-examples-don-t-build-some-examples-when-BUILD_SHARED_LIBS-is-ON.patch"

BRANCH = "1.2"
SRCREV = "ecf5a703785877a8719a0cda863a98455f7d5d12"

SRCREV_ua-nodeset = "9ba5c136eee04e962444f9bd751c21c6454cbae8"
SRCREV_mdnsd = "4bd993e0fdd06d54c8fd0b8f416cda6a8db18585"

PV = "1.2.2+git${SRCPV}"
PR = "tano0.${INC_PR}"

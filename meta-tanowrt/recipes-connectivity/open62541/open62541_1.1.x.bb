#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
# open62541_git.bb
#
# open62541 is an open source and free implementation of OPC UA
# (OPC Unified Architecture) written in the common subset of the
# C99 and C++98 languages
#
require open62541.inc

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}_1.1.x:"

SRC_URI += "file://0002-examples-don-t-build-some-examples-when-BUILD_SHARED_LIBS-is-ON.patch"

BRANCH = "1.1"
SRCREV = "0e8505832555a32354a6ecdd8d858596228be743"

SRCREV_ua-nodeset = "0777abd1bc407b4dbd79abc515864f8c3ce6812b"
SRCREV_mdnsd = "4bd993e0fdd06d54c8fd0b8f416cda6a8db18585"

PV = "1.1.3+git${SRCPV}"
PR = "tano0.${INC_PR}"

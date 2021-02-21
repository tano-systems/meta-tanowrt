#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano0.${INCPR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/files/default:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:"

# Prefer this version
DEFAULT_PREFERENCE = "1"

inherit features_check
REQUIRED_MACHINE_FEATURES = "swupdate"

COMPATIBLE_MACHINE = "qemupc"

require swupdate.inc

SWUPDATE_GIT_URI      ?= "git://github.com/tano-systems/swupdate.git"
SWUPDATE_GIT_BRANCH   ?= "tano/master"
SWUPDATE_GIT_PROTOCOL ?= "https"
SWUPDATE_GIT_SRCREV   ?= "d731756b656536e3f1632265670af5dd3d0f3b7f"

SRC_URI_remove = "git://github.com/sbabic/swupdate.git;protocol=https"
SRC_URI_append = " ${SWUPDATE_GIT_URI};branch=${SWUPDATE_GIT_BRANCH};protocol=${SWUPDATE_GIT_PROTOCOL} "
SRCREV = "${SWUPDATE_GIT_SRCREV}"
PV = "2020.11+git${SRCPV}"

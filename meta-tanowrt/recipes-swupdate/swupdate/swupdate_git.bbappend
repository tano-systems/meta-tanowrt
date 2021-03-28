#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano1.${INCPR}"

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
SWUPDATE_GIT_SRCREV   ?= "97c263482bb0d918bad571d1b13dd2daa0d926fe"

SRC_URI_remove = "git://github.com/sbabic/swupdate.git;protocol=https"
SRC_URI_append = " ${SWUPDATE_GIT_URI};branch=${SWUPDATE_GIT_BRANCH};protocol=${SWUPDATE_GIT_PROTOCOL} "
SRCREV = "${SWUPDATE_GIT_SRCREV}"
PV = "2020.11+git${SRCPV}"

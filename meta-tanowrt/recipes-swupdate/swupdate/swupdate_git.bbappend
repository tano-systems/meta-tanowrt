#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano0.${INCPR}"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/files/default:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:"

# Prefer this version
DEFAULT_PREFERENCE = "1"

inherit features_check
REQUIRED_MACHINE_FEATURES = "swupdate"

COMPATIBLE_MACHINE = "qemupc"

require swupdate.inc

SWUPDATE_GIT_URI      ?= "git://github.com/tano-systems/swupdate.git"
SWUPDATE_GIT_BRANCH   ?= "tano/master"
SWUPDATE_GIT_PROTOCOL ?= "https"
SWUPDATE_GIT_SRCREV   ?= "49e958fe46c224b47334a91266e018e64b1b5e47"

SRC_URI:remove = "git://github.com/sbabic/swupdate.git;protocol=https;branch=master"
SRC_URI:append = " ${SWUPDATE_GIT_URI};branch=${SWUPDATE_GIT_BRANCH};protocol=${SWUPDATE_GIT_PROTOCOL} "
SRCREV = "${SWUPDATE_GIT_SRCREV}"
PV = "2021.04+git${SRCPV}"

LIC_FILES_CHKSUM = "file://LICENSES/GPL-2.0-only.txt;md5=4ee23c52855c222cba72583d301d2338 \
                    file://LICENSES/LGPL-2.1-or-later.txt;md5=4fbd65380cdd255951079008b364516c \
                    file://LICENSES/MIT.txt;md5=838c366f69b72c5df05c96dff79b35f2 \
                    file://LICENSES/BSD-2-Clause.txt;md5=6a31f076f5773aabd8ff86191ad6fdd5 \
                    file://LICENSES/BSD-3-Clause.txt;md5=4a1190eac56a9db675d58ebe86eaf50c"

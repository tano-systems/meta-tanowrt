#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

PR = "tano2"

DEPENDS += "bison-native flex-native"

BRANCH = "processor-sdk-linux-rt-4.19.y"
SRCREV = "a242ccf3f13f03d41d521411ce2cc09775c873a2"

KERNEL_GIT_URI = "git://git.ti.com/git/processor-sdk/processor-sdk-linux.git"
KERNEL_GIT_PROTOCOL = "https"
SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

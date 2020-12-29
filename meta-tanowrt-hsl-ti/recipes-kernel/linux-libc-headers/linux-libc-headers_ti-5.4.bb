#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

PR = "tano0"

DEPENDS += "bison-native flex-native rsync-native"

BRANCH = "ti-linux-5.4.y"
SRCREV = "eb0f4e6c05153446b62028ac84452101b8280253"

KERNEL_GIT_URI = "git://git.ti.com/ti-linux-kernel/ti-linux-kernel.git"
KERNEL_GIT_PROTOCOL = "git"
SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL};branch=${BRANCH}"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

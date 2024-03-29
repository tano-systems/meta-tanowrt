#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#
require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

PR = "tano0"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}-${PV}:"

SRC_URI:append:libc-musl = "\
    file://0001-libc-compat.h-fix-some-issues-arising-from-in6.h.patch \
    file://0002-libc-compat.h-prevent-redefinition-of-struct-ethhdr.patch \
    file://0003-remove-inclusion-of-sysinfo.h-in-kernel.h.patch \
    file://0001-libc-compat.h-musl-_does_-define-IFF_LOWER_UP-DORMAN.patch \
    file://0001-if_ether-move-muslc-ethhdr-protection-to-uapi-file.patch \
    file://0001-include-linux-stddef.h-in-swab.h-uapi-header.patch \
   "

SRC_URI:append = "\
    file://0001-net-Use-__kernel_clockid_t-in-uapi-net_stamp.h.patch \
    file://0001-scripts-Use-fixed-input-and-output-files-instead-of-.patch \
    file://0001-kbuild-install_headers.sh-Strip-_UAPI-from-if-define.patch \
    file://0002-arm64-sve-ptrace-Fix-SVE_PT_REGS_OFFSET-definition.patch \
    file://0003-arm64-sve-Disentangle-uapi-asm-ptrace.h-from-uapi-as.patch \
"

SRC_URI[md5sum] = "740a90cf810c2105df8ee12e5d0bb900"
SRC_URI[sha256sum] = "0c68f5655528aed4f99dae71a5b259edc93239fa899e2df79c055275c21749a1"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
SUMMARY = "TanoWrt full featured SWU image with Qt5"
PACKAGE_ARCH = "${MACHINE_ARCH}"
PR = "tano0"

COMPATIBLE_MACHINE = "qemupc"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit tanowrt-image-swu

# Use sw-description file from base tanowrt-image-full-swu.bb recipe
FILESEXTRAPATHS_prepend := "${TANOWRT_BASE}/recipes-image/images/tanowrt-image-full-swu:"
SRC_URI = "file://sw-description"

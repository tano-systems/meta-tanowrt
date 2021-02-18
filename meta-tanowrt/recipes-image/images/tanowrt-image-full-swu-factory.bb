#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
SUMMARY = "TanoWrt full featured SWU factory installation image"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PV = "1.0.0"
PR = "tano0"

COMPATIBLE_MACHINE = "qemupc"

inherit tanowrt-image-swu-factory

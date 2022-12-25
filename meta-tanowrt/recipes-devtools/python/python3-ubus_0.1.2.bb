#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2022 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
# Mikhail Pankin <pankinams@gmail.com>
#

DESCRIPTION = "Enables a subset of libubus functions to be used directly from python"
HOMEPAGE = "https://pypi.org/project/ubus/"

LICENSE = "LGPL-2.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4fbd65380cdd255951079008b364516c"

SRC_URI[sha256sum] = "4dc4ef0fbcc8abb7a2354691475a58ff3eb015f1bab3150750729f7f657dd440"

PYPI_PACKAGE = "ubus"

PR = "tano0"

inherit pypi setuptools3

DEPENDS += "${PYTHON_PN}-pip-native ${PYTHON_PN}-wheel-native libubus libubox"

BBCLASSEXTEND = "native nativesdk"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/patches:"

SRC_URI += "\
	file://0001-ubus_loop-Add-timeout-callback.patch \
	file://0002-test_policies-Fix-RPC-authorization.patch \
"

#
# SPDX-License-Identifier: MIT
# Copyright (C) 2018-2022, Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano8"

DESCRIPTION = "LuCI HTTP utility library"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=a6b7565d075eb26cd08b6ac739db35e3"
DEPENDS = "lua5.1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit cmake tanowrt-lua pkgconfig

SRC_URI = "\
	git://github.com/jow-/lucihttp.git;protocol=https \
"

SRCREV = "cc8518386cd37f25e574a66be63f1d62d2bd5a0a"

PACKAGECONFIG ??= "build-lua build-ucode"
PACKAGECONFIG[build-lua] = "-DBUILD_LUA=ON,-DBUILD_LUA=OFF,"
PACKAGECONFIG[build-ucode] = "-DBUILD_UCODE=ON,-DBUILD_UCODE=OFF,ucode"

EXTRA_OECMAKE += "-DBUILD_TESTS=OFF -DLUAPATH=/usr/lib/lua/5.1"
OECMAKE_C_FLAGS += "${@bb.utils.contains('TOOLCHAIN', 'clang', '-Wno-unknown-warning-option', '', d)}"

S = "${WORKDIR}/git"

PRIVATE_LIBS = "\
	lucihttp.so \
"

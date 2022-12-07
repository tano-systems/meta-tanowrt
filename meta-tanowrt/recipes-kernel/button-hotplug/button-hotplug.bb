#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
HOMEPAGE = "http://openwrt.org/"
SUMMARY = "OpenWrt Button Hotplug driver"
DESCRIPTION = "Kernel module to generate button uevent-s from input subsystem events. \
If your device uses GPIO buttons, see gpio-button-hotplug."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://button-hotplug.c;beginline=1;endline=14;md5=9d49062ff157cf40a36da911cc3de4f5"

PR = "tano1"

inherit module

SRC_URI = "\
	file://button-hotplug.c \
	file://Makefile \
"

S = "${WORKDIR}"

RPROVIDES:${PN} += "kernel-module-button-hotplug"

KERNEL_MODULE_AUTOLOAD += "button-hotplug"

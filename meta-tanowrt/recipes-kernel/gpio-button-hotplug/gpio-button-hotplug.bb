#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
HOMEPAGE = "http://openwrt.org/"
SUMMARY = "Simple GPIO Button Hotplug driver"
DESCRIPTION = "This is a replacement for the following in-kernel drivers: \
1) gpio_keys (KEYBOARD_GPIO) \
2) gpio_keys_polled (KEYBOARD_GPIO_POLLED) \
\
Instead of generating input events (like in-kernel drivers do) it generates \
uevent-s and broadcasts them. This allows disabling input subsystem which is \
an overkill for OpenWrt simple needs."
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://gpio-button-hotplug.c;beginline=1;endline=15;md5=708871d134fc952ce42b3a8d8e4e128e"

PR = "tano1"

inherit module

SRC_URI = "\
	file://gpio-button-hotplug.c \
	file://Makefile \
"

S = "${WORKDIR}"

RPROVIDES:${PN} += "kernel-module-gpio-button-hotplug"

KERNEL_MODULE_AUTOLOAD += "gpio-button-hotplug"

#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR:append:boardcon-em356x = ".rk0"
PR:append:rock-pi-s = ".rk0"

SRC_URI:remove = "file://swu_install.json"
SRC_URI:append = " file://swu_install.json.in"

inherit vars-expander

EXPAND_VARIABLES += "\
	${TANOWRT_PARTUUID_VARS} \
"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}/:"

COMPATIBLE_MACHINE:append = "|boardcon-em3566-emmc|boardcon-em3568-emmc|rock-pi-s-sdnand"

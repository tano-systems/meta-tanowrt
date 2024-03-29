#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".9"

KCONFIG_MODE = "--alldefconfig"

# Config
KBUILD_DEFCONFIG = ""
SRC_URI:append = " file://defconfig"

KERNEL_DEVICETREE_EXTRA = "\
	${@d.getVar('KERNEL_DEVICETREE').replace('rockchip/', '').replace('.dtb', '.dts')} \
	boardcon-em356x.dtsi \
"

python () {
    files = d.getVar('KERNEL_DEVICETREE_EXTRA') or ''
    bb.parse.mark_dependency(d, files)

    for file in files.split():
        if file.endswith("dtsi"):
            d.appendVar('SRC_URI', ' file://' + file)
            d.appendVar('KERNEL_DEVICETREE_COPY', ' ' + file)
            bb.debug(1, 'Added dtsi to SRC_URI and KERNEL_DEVICETREE_COPY: ' + file)
}

# Devicetree files
SRC_URI:append = "\
	file://${@d.getVar('KERNEL_DEVICETREE').replace('rockchip/', '').replace('.dtb', '.dts')} \
"

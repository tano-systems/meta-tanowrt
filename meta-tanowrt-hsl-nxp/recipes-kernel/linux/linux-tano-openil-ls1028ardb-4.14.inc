#
# Copyright (C) 2020, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#
PR_append = ".2"

COMPATIBLE_MACHINE =. "ls1028ardb|"

# Config
SRC_URI_append_ls1028ardb = " file://defconfig "

# Fix building with GCC8
SRC_URI_append_ls1028ardb = "\
	file://0001-Makefile-add-cflags.patch \
	file://0002-qman_config-Fix-build-with-GCC8.patch \
"

# Devicetree
SRC_URI_append_ls1028ardb = "\
	file://fsl-ls1028a-rdb.dts \
	file://fsl-ls1028a.dtsi \
"

do_configure_append_ls1028ardb() {
	install -d ${S}/arch/arm64/boot/dts/freescale
	cp -f ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a-rdb.dts ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a-rdb.dts.orig
	cp -f ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a.dtsi ${S}/arch/arm64/boot/dts/freescale/fsl-ls1028a.dtsi.orig
	install -m 0644 ${WORKDIR}/fsl-ls1028a-rdb.dts ${S}/arch/arm64/boot/dts/freescale/
	install -m 0644 ${WORKDIR}/fsl-ls1028a.dtsi ${S}/arch/arm64/boot/dts/freescale/
}

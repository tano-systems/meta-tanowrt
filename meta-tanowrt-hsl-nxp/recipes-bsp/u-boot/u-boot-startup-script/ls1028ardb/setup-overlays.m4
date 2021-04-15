#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# NXP LS1028A RDB DTB/DTBO configuration setup
#
test -n ${mkb004lw} || setenv mkb004lw 0;

setenv bootconfigs "#conf@freescale_fsl-ls1028a-rdb.dtb";

if test x${mkb004lw} = x1; then
	setenv bootconfigs "${bootconfigs}#conf@freescale_overlays_tano-mkb004lw.dtbo";
	echo "Enabled MKB004LW overlay";
fi

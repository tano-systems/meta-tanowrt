#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# AM574x IDK DTB/DTBO configuration setup
#
setenv bootconfigs "#conf-am57xx-evm.dtb";

if test $board_name = beagle_x15; then
	setenv bootconfigs "#conf-am57xx-beagle-x15.dtb";
fi;
if test $board_name = beagle_x15_revb1; then
	setenv bootconfigs "#conf-am57xx-beagle-x15-revb1.dtb";
fi;
if test $board_name = beagle_x15_revc; then
	setenv bootconfigs "#conf-am57xx-beagle-x15-revc.dtb";
fi;
if test $board_name = am572x_idk && test $idk_lcd = no; then
	setenv bootconfigs "#conf-am572x-idk.dtb";
fi;
if test $board_name = am574x_idk && test $idk_lcd = no; then
	setenv bootconfigs "#conf-am574x-idk.dtb";
fi;
if test $board_name = am57xx_evm; then
	setenv bootconfigs "#conf-am57xx-evm.dtb";
fi;
if test $board_name = am571x_idk && test $idk_lcd = no; then
	setenv bootconfigs "#conf-am571x-idk.dtb";
fi;

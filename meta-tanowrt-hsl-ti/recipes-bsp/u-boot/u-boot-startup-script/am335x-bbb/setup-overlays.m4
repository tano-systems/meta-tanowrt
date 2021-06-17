#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# BeagleBone Black DTB/DTBO configuration setup
#
setenv bootconfigs "#conf-am335x-boneblack.dtb";

if test $board_name = A335BONE; then
	setenv bootconfigs "#conf-am335x-bone.dtb";
fi;
if test $board_name = A335BNLT; then
	setenv bootconfigs "#conf-am335x-boneblack.dtb";
fi;

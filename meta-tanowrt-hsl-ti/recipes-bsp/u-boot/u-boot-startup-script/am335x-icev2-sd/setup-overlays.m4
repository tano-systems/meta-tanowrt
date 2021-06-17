#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# AM335x ICEv2 DTB/DTBO configuration setup
#
setenv bootconfigs "#conf-am335x-icev2.dtb";

if test $ti_uio = 1; then
	setenv bootconfigs "#conf-am335x-icev2-pru-excl-uio.dtb";
	setenv optargs "$optargs modprobe.blacklist=pruss,pru_rproc,ti_prueth"
else
	if test $board_name = A335_ICE && test $ice_mii = rmii; then
		setenv bootconfigs "#conf-am335x-icev2.dtb";
	fi;
	if test $board_name = A335_ICE && test $ice_mii = mii; then
		setenv bootconfigs "#conf-am335x-icev2-prueth.dtb";
	fi;
fi

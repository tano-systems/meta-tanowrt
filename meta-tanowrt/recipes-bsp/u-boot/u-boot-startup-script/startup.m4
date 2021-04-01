#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# U-Boot startup script for normal NAND boot
#
include(`defs.m4')
include(`setup-common.m4')
include(`setup-env.m4')
include(`setup-overlays.m4')

# SWUPDATE env defaults
test -n ${swu_clear_overlay} || setenv swu_clear_overlay 0;
test -n ${swu_clear_overlay_force} || setenv swu_clear_overlay_force 0;
test -n ${swu_active} || setenv swu_active 0;
test -n ${swu_state} || setenv swu_state ok;
test -n ${swu_ustate} || setenv swu_ustate 0;
test -n ${swu_bootcount} || setenv swu_bootcount 0;
test -n ${swu_bootcount_limit} || setenv swu_bootcount_limit 1;
test -n ${swu_upgrade_available} || setenv swu_upgrade_available 0;

NORMAL_INIT

# Handle bootcount
if test ${swu_upgrade_available} = 1; then
	setexpr swu_bootcount ${swu_bootcount} + 1;

	echo "Upgrade available detected";

	if test ${swu_bootcount} > ${swu_bootcount_limit}; then
		echo "Detected firmware upgrade failure";
		echo "Switching back to standby system...";

		setenv swu_bootcount 0;
		setenv swu_upgrade_available 0;
		setenv swu_state failed;
		setenv swu_ustate 3;
		setenv swu_clear_overlay 0;
		setenv swu_clear_overlay_force 0;

		# Swap active system
		if test ${swu_active} = 0; then
			setenv swu_active 1;
		else
			setenv swu_active 0;
		fi;
	fi;

	saveenv;
fi;

# Setup active system
SWU_SETUP_ACTIVE_SYSTEM_BOTH
if test ${swu_active} = 1; then
	SWU_SETUP_ACTIVE_SYSTEM_B
	echo "Active system B";
else
	SWU_SETUP_ACTIVE_SYSTEM_A
	echo "Active system A";
fi;

NORMAL_LOAD

# Load kernel from NAND
echo "Loading kernel fitImage..."
NORMAL_LOAD_KERNEL

include(`setup-console.m4')

rootargs="NORMAL_KERNEL_ROOT_ARGS";
setenv bootargs ${consoleargs} ${rootargs} ${optargs} ${kernelargs};

echo "Booting kernel ${loadaddr}${bootconfigs}...";
bootm ${loadaddr}${bootconfigs};

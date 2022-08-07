#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2022 Tano Systems LLC
#
# U-Boot startup script definitions
#

# Console parameters
define(CONSOLE_DEV, `ttyS0')
define(CONSOLE_BAUDRATE, `${baudrate}')

# Init commands
define(NORMAL_INIT, `
	echo "Board name: $board_name";')

# Normal boot settings
define(NORMAL_LOAD, `')
define(NORMAL_LOAD_KERNEL, `
	ubi part ${system_part} 2048 || exit;
	ubi read ${loadaddr} ${kernel_volume} || exit;')
define(NORMAL_KERNEL_ROOT_ARGS, `\
	ubi.mtd=${system_part},2048\
	ubi.mtd=${rootfs_data_part},2048\
	ubi.block=0,${rootfs_volume}\
	root=/dev/ubiblock0_${rootfs_volume_id} ro\
	rootfstype=squashfs\
	rootwait\
	rootfs_partition=${system_part}\
	rootfs_volume=${rootfs_volume}')
define(SWU_SETUP_ACTIVE_SYSTEM_A, `system_part=system_a;')
define(SWU_SETUP_ACTIVE_SYSTEM_B, `system_part=system_b;')
define(SWU_SETUP_ACTIVE_SYSTEM_BOTH, `
	kernel_volume=kernel;
	rootfs_volume=rootfs;
	rootfs_volume_id=1;
	rootfs_data_part=rootfs_data;')

# Settings for factory installation media startup script
define(FACTORY_LOAD, `')
define(FACTORY_INIT, NORMAL_INIT)
define(FACTORY_LOAD_KERNEL, `
	mmc dev 0 || exit;
	fatload mmc 0:1 ${loadaddr} fitImage || exit;')
define(FACTORY_KERNEL_ROOT_ARGS, `\
	root=/dev/ram\
	rootfstype=squashfs\
	rootwait=1')

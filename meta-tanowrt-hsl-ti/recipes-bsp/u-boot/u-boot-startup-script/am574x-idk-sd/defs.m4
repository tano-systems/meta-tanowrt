#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# U-Boot startup script definitions
#

# Console parameters
define(CONSOLE_DEV, `ttyS2')
define(CONSOLE_BAUDRATE, `${baudrate}')

# Init commands
define(NORMAL_INIT, `
	echo "Board name: $board_name";
	echo "Board revision: $board_rev";
	echo "Board serial: $board_serial";')

# Normal boot settings
define(NORMAL_LOAD, `')
define(NORMAL_LOAD_KERNEL, `
	mmc dev 0;
	ext4load mmc 0:${kernel_part} ${loadaddr} fitImage')
define(NORMAL_KERNEL_ROOT_ARGS, `\
	root=/dev/mmcblk0p${rootfs_part} ro\
	rootfstype=squashfs\
	rootwait\
	rootfs_partition=${rootfs_part}\
	rootfs_volume=${rootfs_part}')
define(SWU_SETUP_ACTIVE_SYSTEM_A, `kernel_part=1; rootfs_part=2;')
define(SWU_SETUP_ACTIVE_SYSTEM_B, `kernel_part=3; rootfs_part=4;')
define(SWU_SETUP_ACTIVE_SYSTEM_BOTH, `')

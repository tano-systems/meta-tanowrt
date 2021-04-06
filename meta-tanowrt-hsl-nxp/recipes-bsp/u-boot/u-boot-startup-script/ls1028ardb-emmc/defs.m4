#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# U-Boot startup script definitions
#

# Console parameters
define(CONSOLE_DEV, `ttyS0')
define(CONSOLE_BAUDRATE, `${baudrate}')

# Init commands
define(NORMAL_INIT, `
	echo "Board name: $board_name";
	echo "Booted from ${boot_device}";')

# Load ls1028a-dp-fw.bin
define(NORMAL_LOAD, `
	mmc dev 1 || exit;
	mmc read 0x98000000 0x1800 0x200 || exit;
	hdp load 0x98000000 0x2000 || exit;')
define(NORMAL_LOAD_KERNEL, `
	mmc dev 1 || exit;
	ext4load mmc 1:${kernel_part} ${loadaddr} fitImage || exit;')
define(NORMAL_KERNEL_ROOT_ARGS, `\
	root=/dev/mmcblk1p${rootfs_part} ro\
	rootfstype=squashfs\
	rootwait\
	rootfs_partition=${rootfs_part}\
	rootfs_volume=${rootfs_part}')
define(SWU_SETUP_ACTIVE_SYSTEM_A, `kernel_part=1; rootfs_part=2;')
define(SWU_SETUP_ACTIVE_SYSTEM_B, `kernel_part=3; rootfs_part=4;')
define(SWU_SETUP_ACTIVE_SYSTEM_BOTH, `')

# Settings for factory installation media startup script
define(FACTORY_INIT, NORMAL_INIT)
define(FACTORY_LOAD, `
	mmc dev 0 || exit;
	mmc read 0x98000000 0x1800 0x200 || exit;
	hdp load 0x98000000 0x2000 || exit;')
define(FACTORY_BOOT_DEVICE, `SD')
define(FACTORY_LOAD_KERNEL, `
	mmc dev 0 || exit;
	ext4load mmc 0:2 ${loadaddr} fitImage || exit;')
define(FACTORY_KERNEL_ROOT_ARGS, `\
	root=/dev/ram\
	rootfstype=squashfs\
	rootwait=1')

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
define(NORMAL_INIT, `')

# Normal boot settings
define(NORMAL_BOOT_DEVICE, `')
define(NORMAL_LOAD, `')
define(NORMAL_LOAD_KERNEL, `')
define(NORMAL_KERNEL_ROOT_ARGS, `')
define(SWU_SETUP_ACTIVE_SYSTEM_A, `')
define(SWU_SETUP_ACTIVE_SYSTEM_B, `')
define(SWU_SETUP_ACTIVE_SYSTEM_BOTH, `')

# Settings for factory installation media startup script
define(FACTORY_INIT, NORMAL_INIT)
define(FACTORY_BOOT_DEVICE, `')
define(FACTORY_LOAD, NORMAL_LOAD)
define(FACTORY_LOAD_KERNEL, `')
define(FACTORY_KERNEL_ROOT_ARGS, `\
	root=/dev/ram\
	rootfstype=squashfs\
	rootwait=1')

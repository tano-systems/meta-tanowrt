#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# U-Boot startup script for factory installation media
#
include(`defs.m4')
include(`setup-common.m4')
include(`setup-env.m4')
include(`setup-overlays.m4')

FACTORY_INIT

FACTORY_LOAD

# Load kernel from factory installation media
echo "Loading kernel fitImage..."
FACTORY_LOAD_KERNEL

# Enable console
echo "Force console to be enabled";
setenv dbg_console 1;

include(`setup-console.m4')

rootargs="FACTORY_KERNEL_ROOT_ARGS";
setenv bootargs ${consoleargs} ${rootargs} ${optargs} ${kernelargs};

echo "Booting kernel ${loadaddr}${bootconfigs}...";
bootm ${loadaddr}${bootconfigs};

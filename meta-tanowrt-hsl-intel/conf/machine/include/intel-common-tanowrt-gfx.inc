#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

#
# Machine/distro features
#
MACHINE_FEATURES:append = "\
	screen \
	opengl \
"

#
# Psplash
#
IMAGE_FEATURES += "splash"
SPLASH = "psplash"

#
# QEMU options (runqemu)
#
QB_OPT_APPEND:append = "\
	-device usb-tablet \
	-serial mon:stdio \
	-serial null \
"

QB_OPT_APPEND:append = " -vga std "

#
# Enable SDL display in QEMU
#
PACKAGECONFIG_append_pn-qemu-system-native = " sdl"
PACKAGECONFIG_append_pn-nativesdk-qemu = " sdl"

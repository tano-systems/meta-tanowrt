#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#

#
# Machine/distro features
#

#
# Psplash
#
IMAGE_FEATURES += "splash"
SPLASH = "psplash"

XSERVER_QEMU_ALLX86 = "\
	xf86-video-cirrus \
	xf86-video-vmware \
	xf86-video-modesetting \
	xserver-xorg-module-libint10 \
"

XSERVER:append:qemux86 = " ${XSERVER_QEMU_ALLX86}"
XSERVER:append:qemux86-64 = " ${XSERVER_QEMU_ALLX86}"

#
# Kernel modules
#

#
# QEMU options (runqemu)
#
QB_OPT_APPEND:remove = "-nographic"
QB_OPT_APPEND:append = "\
	-device usb-tablet \
	-serial mon:stdio \
	-serial null \
"

QB_OPT_APPEND:append:qemux86 = " -vga std "
QB_OPT_APPEND:append:qemux86-64 = " -vga std "
QB_OPT_APPEND:append:qemuarm = " -device VGA,edid=on "
QB_OPT_APPEND:append:qemuarm64 = " -device VGA,edid=on "

#
# Enable SDL display in QEMU
#
PACKAGECONFIG:append:pn-qemu-system-native = " sdl"
PACKAGECONFIG:append:pn-nativesdk-qemu = " sdl"

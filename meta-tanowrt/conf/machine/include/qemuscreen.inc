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

XSERVER_append_qemux86 = " ${XSERVER_QEMU_ALLX86}"
XSERVER_append_qemux86-64 = " ${XSERVER_QEMU_ALLX86}"

#
# Kernel modules
#

#
# QEMU options (runqemu)
#
QB_OPT_APPEND_remove = "-nographic"
QB_OPT_APPEND_append = "\
	-device usb-tablet \
	-serial mon:stdio \
	-serial null \
"

QB_OPT_APPEND_append_qemux86 = " -vga std "
QB_OPT_APPEND_append_qemux86-64 = " -vga std "
QB_OPT_APPEND_append_qemuarm = " -device VGA,edid=on "
QB_OPT_APPEND_append_qemuarm64 = " -device VGA,edid=on "

#
# Enable SDL display in QEMU
#
PACKAGECONFIG_append_pn-qemu-system-native = " sdl"
PACKAGECONFIG_append_pn-nativesdk-qemu = " sdl"

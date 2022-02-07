#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

# For runqemu
IMAGE_CLASSES += "qemuboot"

QB_DEFAULT_FSTYPE = "wic"

QB_SYSTEM_NAME:intel-x86-64-corei7 = "qemu-system-x86_64"
QB_CPU:intel-x86-64-corei7 = "-cpu Nehalem"
QB_CPU_KVM:intel-x86-64-corei7 = "-cpu kvm64"

QB_SYSTEM_NAME:intel-x86-64-skylake = "qemu-system-x86_64"
QB_CPU:intel-x86-64-skylake = "-cpu Skylake-Client"
QB_CPU_KVM:intel-x86-64-skylake = "-cpu Skylake-Client"

QB_AUDIO_DRV = "alsa"
QB_AUDIO_OPT = "-soundhw ac97,es1370"
QB_KERNEL_CMDLINE_APPEND = " oprofile.timer=1"
# Add the 'virtio-rng-pci' device otherwise the guest may run out of entropy
QB_OPT_APPEND = " -usb -usbdevice tablet -device virtio-rng-pci"

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
PACKAGECONFIG:append:pn-qemu-system-native = " sdl"
PACKAGECONFIG:append:pn-nativesdk-qemu = " sdl"

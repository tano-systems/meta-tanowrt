#
# Machine/distro features
#
IMAGE_FEATURES += "splash"

#
# Psplash
#
SPLASH = "psplash-tano"

#
# XServer
#
PREFERRED_PROVIDER_virtual/xserver ?= "xserver-xorg"
PREFERRED_PROVIDER_virtual/libgl ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles1 ?= "mesa"
PREFERRED_PROVIDER_virtual/libgles2 ?= "mesa"

XSERVER = "xserver-xorg \
           ${@bb.utils.contains('DISTRO_FEATURES', 'opengl', 'mesa-driver-swrast xserver-xorg-extension-glx', '', d)} \
           xf86-video-cirrus \
           xf86-video-fbdev \
           xf86-video-vmware \
           xf86-video-modesetting \
           xserver-xorg-module-libint10 \
           "

#
# Kernel modules
#
UVESA_MODE = "1280x720-32"

KERNEL_MODULE_AUTOLOAD += "uvesafb"
KERNEL_MODULE_AUTOLOAD_EARLY += "uvesafb"
KERNEL_MODULE_PROBECONF += "uvesafb"
module_conf_uvesafb = "options uvesafb mode_option=${UVESA_MODE}"

#
# QEMU options (runqemu)
#
QB_OPT_APPEND  = "-show-cursor -usb -device usb-tablet -device virtio-rng-pci -smp 2"
QB_OPT_APPEND += "-vga vmware -serial mon:stdio -serial null"

# Enable SDL display in QEMU
PACKAGECONFIG_append_pn-qemu-system-native = " sdl"
PACKAGECONFIG_append_pn-nativesdk-qemu = " sdl"

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
UVESA_MODE_ALLX86 = "1280x720-32"
UVESA_MODE_qemux86 = "${UVESA_MODE_ALLX86}"
UVESA_MODE_qemux86-64 = "${UVESA_MODE_ALLX86}"

KERNEL_MODULE_AUTOLOAD_append_qemux86          = " uvesafb"
KERNEL_MODULE_AUTOLOAD_append_qemux86-64       = " uvesafb"
KERNEL_MODULE_AUTOLOAD_EARLY_append_qemux86    = " uvesafb"
KERNEL_MODULE_AUTOLOAD_EARLY_append_qemux86-64 = " uvesafb"
KERNEL_MODULE_PROBECONF_append_qemux86         = " uvesafb"
KERNEL_MODULE_PROBECONF_append_qemux86-64      = " uvesafb"

module_conf_uvesafb = "options uvesafb mode_option=${UVESA_MODE}"

#
# QEMU options (runqemu)
#
QB_OPT_APPEND_remove = "-nographic"
QB_OPT_APPEND_append = "\
	-show-cursor \
	-device usb-tablet \
	-serial mon:stdio \
	-serial null \
"

QB_OPT_APPEND_ALLX86 = "\
	-vga vmware \
"

QB_OPT_APPEND_append_qemux86 = " ${QB_OPT_APPEND_ALLX86}"
QB_OPT_APPEND_append_qemux86-64 = " ${QB_OPT_APPEND_ALLX86}"

#
# Enable SDL display in QEMU
#
PACKAGECONFIG_append_pn-qemu-system-native = " sdl"
PACKAGECONFIG_append_pn-nativesdk-qemu = " sdl"

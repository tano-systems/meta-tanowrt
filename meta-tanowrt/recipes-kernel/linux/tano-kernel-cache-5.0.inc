PR_append = ".1"

KERNEL_CACHE_SRC_BRANCH ?= "kernel-5.0"
KERNEL_CACHE_SRC_SRCREV ?= "a4b544a0a0a32f2a9b380c075a49136b15ba046c"

KERNEL_FEATURES_append = "\
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/usb-leds/ledtrig_usbport.scc", "" ,d)} \
"

#	patches/openwrt/generic/630.scc \
#

KERNEL_FEATURES_append = "\
	patches/openwrt/generic/550.scc \
	patches/openwrt/generic/600.scc \
	patches/openwrt/generic/610.scc \
	patches/openwrt/generic/611.scc \
	patches/openwrt/generic/612.scc \
	patches/openwrt/generic/613.scc \
	patches/openwrt/generic/616.scc \
	patches/openwrt/generic/620.scc \
	patches/openwrt/generic/640.scc \
	patches/openwrt/generic/655.scc \
	patches/openwrt/generic/660.scc \
	patches/openwrt/generic/661.scc \
	patches/openwrt/generic/662.scc \
	patches/openwrt/generic/681.scc \
"

require recipes-kernel/linux/tano-kernel-cache.inc

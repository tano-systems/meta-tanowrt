PR_append = ".8"

KERNEL_CACHE_DIR = "kmeta-tano"

KERNEL_FEATURES_append = "\
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/kernel-4.14+/usb-leds/ledtrig_usbport.scc", "" ,d)} \
"

#	patches/openwrt/generic-${LINUX_VERSION_SHORT}/550.scc \
#	patches/openwrt/generic-${LINUX_VERSION_SHORT}/640.scc \
#	patches/openwrt/generic-${LINUX_VERSION_SHORT}/660.scc \
#	patches/openwrt/generic-${LINUX_VERSION_SHORT}/662.scc \
#	patches/openwrt/generic-${LINUX_VERSION_SHORT}/663.scc \
#

KERNEL_FEATURES_append = "\
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/600.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/610.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/611.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/612.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/613.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/620.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/630.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/655.scc \
	patches/openwrt/generic-${LINUX_VERSION_SHORT}/681.scc \
"

require recipes-kernel/linux/tano-kernel-cache.inc

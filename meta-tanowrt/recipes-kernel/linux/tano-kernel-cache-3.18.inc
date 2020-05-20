PR_append = ".4"

KERNEL_CACHE_DIR = "kmeta-tano"

KERNEL_FEATURES_append = "\
	patches/openwrt/generic-${LINUX_VERSION_FULL}/600.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/610.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/611.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/612.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/613.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/630.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/640.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/655.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/660.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/661.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/662.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/663.scc \
	patches/openwrt/generic-${LINUX_VERSION_FULL}/681.scc \
"

require recipes-kernel/linux/tano-kernel-cache.inc

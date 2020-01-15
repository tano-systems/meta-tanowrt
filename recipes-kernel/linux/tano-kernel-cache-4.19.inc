PR_append = ".8"

KERNEL_CACHE_SRC_BRANCH ?= "kernel-4.19"
KERNEL_CACHE_SRC_SRCREV ?= "a342135d051412d395a1a4644bb26f4def98104e"

KERNEL_FEATURES_append = "\
	patches/openwrt/generic/550.scc \
	patches/openwrt/generic/600.scc \
	patches/openwrt/generic/610.scc \
	patches/openwrt/generic/611.scc \
	patches/openwrt/generic/612.scc \
	patches/openwrt/generic/613.scc \
	patches/openwrt/generic/616.scc \
	patches/openwrt/generic/620.scc \
	patches/openwrt/generic/630.scc \
	patches/openwrt/generic/640.scc \
	patches/openwrt/generic/655.scc \
	patches/openwrt/generic/660.scc \
	patches/openwrt/generic/661.scc \
	patches/openwrt/generic/662.scc \
	patches/openwrt/generic/681.scc \
"

require recipes-kernel/linux/tano-kernel-cache.inc

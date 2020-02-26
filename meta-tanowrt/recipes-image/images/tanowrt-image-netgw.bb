# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".0"

SUMMARY = "TanoWrt Image intended for network routers"
LICENSE = "MIT"

require recipes-image/images/tanowrt-image-base.bb

CORE_IMAGE_EXTRA_INSTALL += "\
	packagegroup-tanowrt-full \
	luci-app-nlbwmon \
	luci-app-vnstat2 \
"

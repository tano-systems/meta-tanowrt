# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano0"

SUMMARY = "OpenWrt Base Image Without Web-Interface"
LICENSE = "MIT"

BAD_RECOMMENDATIONS += "busybox-syslog"
BAD_RECOMMENDATIONS += "busybox-udhcpc"

require recipes-image/images/openwrt-image-noweb-base.bb

CORE_IMAGE_EXTRA_INSTALL += "packagegroup-openwrt-noweb-full"

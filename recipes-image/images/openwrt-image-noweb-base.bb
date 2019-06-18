# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".0"

SUMMARY = "OpenWrt Base Image Without Web-Interface"
LICENSE = "MIT"

require recipes-image/images/openwrt-image-minimal.bb

CORE_IMAGE_EXTRA_INSTALL += "packagegroup-openwrt-noweb-base"

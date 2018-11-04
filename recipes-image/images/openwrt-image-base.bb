# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano1"

SUMMARY = "OpenWrt Base Image"
LICENSE = "MIT"

require recipes-image/images/openwrt-image-minimal.bb

CORE_IMAGE_EXTRA_INSTALL += "packagegroup-openwrt-base"

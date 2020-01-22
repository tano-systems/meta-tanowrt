# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".1"

SUMMARY = "TanoWrt Base Image"
LICENSE = "MIT"

require recipes-image/images/tanowrt-image-minimal.bb

CORE_IMAGE_EXTRA_INSTALL += "packagegroup-tanowrt-base"

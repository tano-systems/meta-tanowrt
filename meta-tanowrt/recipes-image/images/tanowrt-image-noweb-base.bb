# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".0"

SUMMARY = "TanoWrt Base Image Without Web-Interface"
LICENSE = "MIT"

require recipes-image/images/tanowrt-image-minimal.bb

CORE_IMAGE_EXTRA_INSTALL += "packagegroup-tanowrt-noweb-base"

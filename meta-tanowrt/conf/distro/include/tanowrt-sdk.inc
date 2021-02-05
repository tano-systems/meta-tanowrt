#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
#

# SDK paths and names
SDK_NAME_PREFIX = "tanowrt"
SDK_VERSION = "${DISTRO_VERSION}"
SDK_VENDOR = "-tano"
SDK_NAME = "${SDK_NAME_PREFIX}-${SDKMACHINE}-${IMAGE_BASENAME}-${TUNE_PKGARCH}-${MACHINE}"
SDK_INCLUDE_NATIVESDK = "1"
SDKPATH_REAL ?= "/usr/local/${SDK_NAME_PREFIX}-${SDK_VERSION}"
SDKPATH ?= "${@"/tmp/"+"x"*96+"/"+"y"*96}"

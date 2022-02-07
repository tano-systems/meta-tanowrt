#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

require conf/machine/include/meta-intel.inc
require conf/machine/include/intel-corei7-64-common.inc
require conf/machine/include/intel-common-pkgarch.inc

require conf/machine/include/intel-common-tanowrt.inc
require conf/machine/include/intel-common-tanowrt-swu.inc
require conf/machine/include/intel-common-tanowrt-gfx.inc
require conf/machine/include/intel-common-tanowrt-qemu.inc

#
# Kernel
#
PREFERRED_VERSION_linux-tano-intel = "5.10%"
PREFERRED_VERSION_linux-tano-intel-rt = "5.10%"

#
# Other
#
MACHINE_FEATURES += "intel-ucode"
MACHINE_HWCODECS ?= "${@bb.utils.contains('TUNE_FEATURES', 'mx32', '', 'intel-media-driver intel-mediasdk', d)} gstreamer1.0-vaapi"

XSERVER ?= "${XSERVER_X86_BASE} \
            ${XSERVER_X86_EXT} \
            ${XSERVER_X86_FBDEV} \
            ${XSERVER_X86_I915} \
            ${XSERVER_X86_I965} \
            ${XSERVER_X86_MODESETTING} \
            ${XSERVER_X86_VESA} \
            ${XSERVER_X86_ASPEED_AST} \
           "


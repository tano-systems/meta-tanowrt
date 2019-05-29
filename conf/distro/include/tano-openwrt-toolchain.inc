
# Toolchain should be selected by setting TOOLCHAIN_BRAND in local.conf
# Set some sane defaults, in case someone forgets to set them in local.conf

# Use internal GCC toolchain for qemuall
TOOLCHAIN_TYPE_qemuall  ?= "internal"
TOOLCHAIN_BRAND_qemuall ?= "gcc"
SDKGCCVERSION_qemuall   ?= "7.3%"
GCCVERSION_qemuall      ?= "7.3%"

TOOLCHAIN_TYPE  ?= "internal"
TOOLCHAIN_BRAND ?= "linaro"

include conf/distro/include/tano-openwrt-hw-toolchain.inc

require conf/distro/include/toolchain-${TOOLCHAIN_TYPE}-${TOOLCHAIN_BRAND}.inc
require conf/distro/include/toolchain-${TOOLCHAIN_TYPE}.inc

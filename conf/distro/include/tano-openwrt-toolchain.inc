
# Toolchain should be selected by setting TOOLCHAIN_BRAND in local.conf
# Set some sane defaults, in case someone forgets to set them in local.conf

# Use internal GCC toolchain by default
TOOLCHAIN_TYPE  ?= "internal"
TOOLCHAIN_BRAND ?= "gcc"

include conf/distro/include/tano-openwrt-hw-toolchain.inc

require conf/distro/include/toolchain-${TOOLCHAIN_TYPE}-${TOOLCHAIN_BRAND}.inc
require conf/distro/include/toolchain-${TOOLCHAIN_TYPE}.inc

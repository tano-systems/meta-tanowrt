
# Toolchain should be selected by setting TOOLCHAIN_BRAND in local.conf
# Set some sane defaults, in case someone forgets to set them in local.conf

# Use internal GCC toolchain for qemuall
TOOLCHAIN_TYPE_qemuall  ?= "internal"
TOOLCHAIN_BRAND_qemuall ?= "gcc"
SDKGCCVERSION_qemuall   ?= "7.3%"
GCCVERSION_qemuall      ?= "7.3%"

TC_SANE_DEFAULT ?= "linaro"
TOOLCHAIN_TYPE  ?= "external"
TOOLCHAIN_BRAND ?= "${@[d.getVar('TC_SANE_DEFAULT', True),'gcc'][d.getVar('TOOLCHAIN_TYPE', True) == 'internal']}"

require conf/distro/include/toolchain-${TOOLCHAIN_BRAND}.inc
require conf/distro/include/toolchain-${TOOLCHAIN_TYPE}.inc

# This dependencies fixes QA Issue warnings for Linaro Toolchain
RDEPENDS_ldd    += "bash"
RDEPENDS_tzcode += "bash"

RDEPENDS_ldd_remove_qemuall = "bash"
RDEPENDS_tzcode_remove_qemuall = "bash"

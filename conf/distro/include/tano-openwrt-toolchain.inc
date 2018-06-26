# Use internal GCC toolchain for qemuarm
TOOLCHAIN_TYPE_qemuall  ?= "internal"
TOOLCHAIN_BRAND_qemuall ?= "gcc"
SDKGCCVERSION_qemuall   ?= "6.4%"
GCCVERSION_qemuall      ?= "6.4%"

# This dependencies fixes QA Issue warnings for Linaro Toolchain
RDEPENDS_ldd    += "bash"
RDEPENDS_tzcode += "bash"

RDEPENDS_ldd_remove_qemuall = "bash"
RDEPENDS_tzcode_remove_qemuall = "bash"

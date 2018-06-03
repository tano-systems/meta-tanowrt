# Use internal GCC toolchain for qemuarm
TOOLCHAIN_TYPE_qemuarm  ?= "internal"
TOOLCHAIN_BRAND_qemuarm ?= "gcc"
SDKGCCVERSION_qemuarm   ?= "6.4%"
GCCVERSION_qemuarm      ?= "6.4%"

# This dependencies fixes QA Issue warnings for Linaro Toolchain
RDEPENDS_ldd    += "bash"
RDEPENDS_tzcode += "bash"

RDEPENDS_ldd_remove_qemuarm = "bash"
RDEPENDS_tzcode_remove_qemuarm = "bash"

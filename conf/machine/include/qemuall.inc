#
# Disable hardfp warnings when compiling by internal GCC
# with meta-ti and meta-processor-sdk layers
#
BBMASK += "/meta-ti/recipes-ti/devtools/ti-cgt6x_8.2.2.bb"
BBMASK += "/meta-ti/recipes-graphics/libgal/ti-gc320-libs_5.0.11.p7.bb"
BBMASK += "/meta-ti/recipes-graphics/libgles/ti-sgx-ddk-um_1.14.3699939.bb"
BBMASK += "/meta-processor-sdk/recipes-graphics/libgles/ti-sgx-ddk-um_1.14.3699939.bbappend"

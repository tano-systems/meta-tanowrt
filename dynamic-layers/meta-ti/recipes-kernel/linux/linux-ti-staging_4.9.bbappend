FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-4.9:"

#
# QEMU
#

PR_append = ".tano0"

# ARM Versatile PB
KERNEL_DEVICETREE_qemuarm = "versatile-pb.dtb"

# openssl-native and bc-native needed for CONFIG_SYSTEM_TRUSTED_KEYRING
DEPENDS_qemuarm += "bc-native openssl-native util-linux-native"
DEPENDS_qemuarm += "gcc-cross-arm"
DEPENDS_qemuarm += "xz-native libgcc"
KERNEL_CC_append_qemuarm = " ${TOOLCHAIN_OPTIONS}"
KERNEL_LD_append_qemuarm = " ${TOOLCHAIN_OPTIONS}"

SRC_URI += "file://defconfig"

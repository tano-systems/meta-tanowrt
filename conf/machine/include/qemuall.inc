#
# Kernel
#
KERNEL_IMAGETYPE = "bzImage"
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-yocto"
PREFERRED_VERSION_linux-tano-yocto = "4.19%"
PREFERRED_VERSION_xtables-addons = "3.3"

LINUXLIBCVERSION = "4.19%"
SDKLINUXLIBCVERSION = "4.19%"

QEMU_X86_TARGETS = "i586 x86-64"

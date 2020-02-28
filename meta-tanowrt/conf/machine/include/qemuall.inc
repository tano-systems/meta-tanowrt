#
# Kernel
#
KERNEL_IMAGETYPE = "bzImage"
PREFERRED_PROVIDER_virtual/kernel = "linux-tano-yocto"
PREFERRED_VERSION_linux-tano-yocto = "5.0%"
PREFERRED_VERSION_xtables-addons = "3.3"

LINUXLIBCVERSION = "5.0%"
SDKLINUXLIBCVERSION = "5.0%"

QEMU_X86_TARGETS = "i586 x86-64"

# Enable tty1
PROCD_ENABLED_TTYS ?= "1"

# Original file from meta-arago-distro

# Select internal gcc toolchain to be built from sources
TCMODE = "internal-linaro"
TCLIBC = "glibc"

LIBCEXTENSION = "${@['', '-gnu'][(d.getVar('ABIEXTENSION', True) or '') != '']}"

ILT_TARGET_SYS_ARMV5 ?= "arm-linux-gnueabi"
ILT_TARGET_SYS_ARMV7 ?= "arm-linux-gnueabihf"
ILT_TARGET_SYS_ARMV8 ?= "aarch64-linux-gnu"

ILT_TARGET_SYS_arm = "${ILT_TARGET_SYS_ARMV5}"
ILT_TARGET_SYS_armv7a = "${ILT_TARGET_SYS_ARMV7}"
ILT_TARGET_SYS_aarch64 = "${ILT_TARGET_SYS_ARMV8}"

TOOLCHAIN_SYS := "${ILT_TARGET_SYS}"
TOOLCHAIN_PREFIX := "${TOOLCHAIN_SYS}-"

PREFERRED_PROVIDER_gcc-cross-canadian-${TRANSLATED_TARGET_ARCH} ?= "gcc-cross-canadian-${TRANSLATED_TARGET_ARCH}"
PREFERRED_PROVIDER_binutils-cross-canadian-${TRANSLATED_TARGET_ARCH} ?= "binutils-cross-canadian-${TRANSLATED_TARGET_ARCH}"
PREFERRED_PROVIDER_gdb-cross-canadian-${TRANSLATED_TARGET_ARCH} = "gdb-cross-canadian-${TRANSLATED_TARGET_ARCH}"

# Set Cross and SDK toolchain preferences
SDKGCCVERSION ?= "linaro-7.2%"
SDKBINUVERSION ?= "linaro-2.27%"
SDKGLIBCVERSION ?= "linaro-2.26%"
SDKLINUXLIBCVERSION ?= "4.14%"

PREFERRED_VERSION_gcc-crosssdk-${SDK_SYS} ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_gcc-crosssdk-initial-${SDK_SYS} ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_binutils-crosssdk-${SDK_SYS} ?= "${SDKBINUVERSION}"

PREFERRED_VERSION_nativesdk-gcc-runtime ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_nativesdk-libgcc ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_nativesdk-libgcc-initial ?= "${SDKGCCVERSION}"
PREFERRED_VERSION_nativesdk-linux-libc-headers ?= "${SDKLINUXLIBCVERSION}"
PREFERRED_VERSION_nativesdk-glibc ?= "${SDKGLIBCVERSION}"
PREFERRED_VERSION_nativesdk-glibc-initial ?= "${SDKGLIBCVERSION}"

# Set target toolchain preferences to match Cross/SDK by default
# Can be overwritten globally, if needed
GCCVERSION ?= "linaro-7.2%"
BINUVERSION ?= "linaro-2.27%"
GLIBCVERSION ?= "linaro-2.26%"
GLIBCINITVERSION ?= "linaro-2.26%"
LINUXLIBCVERSION ?= "4.14%"

PREFERRED_VERSION_gcc ?= "${GCCVERSION}"
PREFERRED_VERSION_binutils ?= "${BINUVERSION}"
PREFERRED_VERSION_linux-libc-headers ?= "${LINUXLIBCVERSION}"
PREFERRED_VERSION_glibc ?= "${GLIBCVERSION}"
PREFERRED_VERSION_glibc-locale ?= "${GLIBCVERSION}"
PREFERRED_VERSION_glibc-initial ?= "${GLIBCINITVERSION}"

require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":ti33x"
DEFAULTTUNE ?= "cortexa8thf-neon"

require conf/machine/include/tune-cortexa8.inc

# RTC hwclock parameters
OPENWRT_HWCLOCK_DEV       = "/dev/rtc0"
OPENWRT_HWCLOCK_LOCALTIME = "1"

PREFERRED_PROVIDER_virtual/egl = "ti-sgx-ddk-um"
PREFERRED_PROVIDER_virtual/libgles1 = "ti-sgx-ddk-um"
PREFERRED_PROVIDER_virtual/libgles2 = "ti-sgx-ddk-um"
PREFERRED_PROVIDER_libgbm = "libgbm"
PREFERRED_PROVIDER_libgbm-dev = "libgbm-dev"

PREFERRED_PROVIDER_virtual/libgl = "mesa-gl"

MACHINE_FEATURES_append = " hwrng "
MACHINE_FEATURES_append = " gpu "

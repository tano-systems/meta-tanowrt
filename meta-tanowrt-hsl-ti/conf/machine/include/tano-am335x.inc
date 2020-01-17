require conf/machine/include/ti-soc.inc
SOC_FAMILY_append = ":ti33x"
DEFAULTTUNE ?= "cortexa8thf-neon"

require conf/machine/include/tune-cortexa8.inc

# RTC hwclock parameters
OPENWRT_HWCLOCK_DEV       = "/dev/rtc0"
OPENWRT_HWCLOCK_LOCALTIME = "1"

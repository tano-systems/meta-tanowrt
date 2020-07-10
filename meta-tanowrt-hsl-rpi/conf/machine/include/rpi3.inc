MACHINEOVERRIDES =. "rpi3:raspberrypi3:"
require conf/machine/raspberrypi3.conf

PACKAGECONFIG_append_pn-fstools = " ovl-f2fs ovl-rootdisk-part "

IMAGE_CLASSES += "sdimage-rpi-helper"
IMAGE_FSTYPES = "wic"
WKS_FILE = "sdimage-rpi.wks"
WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"

PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rpi"
PREFERRED_VERSION_linux-tano-rpi = "4.19.%"

# Switch from linux-firmware-rpidistro to cypress-firmware
MACHINE_EXTRA_RRECOMMENDS_remove = "\
    linux-firmware-rpidistro-bcm43430 \
    linux-firmware-rpidistro-bcm43455 \
"

MACHINE_EXTRA_RRECOMMENDS_append = "\
    cypress-firmware-43430-sdio \
    cypress-firmware-43455-sdio \
    cypress-nvram-43430-sdio-rpi-3b \
    cypress-nvram-43455-sdio-rpi-3b-plus \
"

RPI_KERNEL_DEVICETREE_OVERLAYS = " \
    overlays/at86rf233.dtbo \
    overlays/dwc2.dtbo \
    overlays/gpio-key.dtbo \
    overlays/hifiberry-amp.dtbo \
    overlays/hifiberry-dac.dtbo \
    overlays/hifiberry-dacplus.dtbo \
    overlays/hifiberry-digi.dtbo \
    overlays/i2c-rtc.dtbo \
    overlays/iqaudio-dac.dtbo \
    overlays/iqaudio-dacplus.dtbo \
    overlays/pi3-disable-bt.dtbo \
    overlays/pi3-miniuart-bt.dtbo \
    overlays/pitft22.dtbo \
    overlays/pitft28-resistive.dtbo \
    overlays/pitft35-resistive.dtbo \
    overlays/pps-gpio.dtbo \
    overlays/rpi-ft5406.dtbo \
    overlays/rpi-poe.dtbo \
    overlays/vc4-kms-v3d.dtbo \
    overlays/w1-gpio-pullup.dtbo \
    overlays/w1-gpio.dtbo \
"

# Load vc4 module before psplash
KERNEL_MODULE_AUTOLOAD_append = " vc4"
KERNEL_MODULE_AUTOLOAD_EARLY_append = " vc4"

SERIAL_CONSOLES = ""
KERNEL_SERIAL = ""

SPLASH = "psplash"
IMAGE_FEATURES += "splash"

GPU_MEM ?= "256"
ENABLE_UART ?= "1"
ENABLE_SPI_BUS ?= "1"
ENABLE_I2C ?= "1"

MACHINE_FEATURES += "ethernet"
MACHINE_FEATURES += "wifi"
MACHINE_FEATURES += "cgroup"
MACHINE_FEATURES += "lldp"
MACHINE_FEATURES += "watchdog"
MACHINE_FEATURES += "opengl"
MACHINE_FEATURES += "crypto"

# No qemu support, yet
MACHINE_FEATURES_remove = "qemu-usermode"

MACHINE_EXTRA_RDEPENDS += "raspi-gpio"

#
# OpenWrt specific
#
OPENWRT_VERSION_PRODUCT = "Raspberry Pi 3 Model B+"
OPENWRT_VERSION_HWREV = "v1"

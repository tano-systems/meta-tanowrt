# Atmel SAMA5 defaults
require conf/machine/include/soc-family.inc
require conf/machine/include/arm/armv7a/tune-cortexa5.inc

# Add bootloaders to the images of every machine
EXTRA_IMAGEDEPENDS += "at91bootstrap virtual/bootloader"

SOC_FAMILY = "sama5"

PREFERRED_PROVIDER_virtual/kernel:sama5 = "linux-tano-at91"
PREFERRED_PROVIDER_virtual/bootloader:sama5 ?= "u-boot-tano-at91"
PREFERRED_PROVIDER_u-boot:sama5 ?= "u-boot-tano-at91"

PREFERRED_VERSION_linux-tano-at91 = "4.19%"

PREFERRED_PROVIDER_jpeg ?= "jpeg"
PREFERRED_PROVIDER_jpeg-native ?= "jpeg-native"

SERIAL_CONSOLES ?= "115200;ttyS0"
WIC_CREATE_EXTRA_ARGS ?= "--no-fstab-update"

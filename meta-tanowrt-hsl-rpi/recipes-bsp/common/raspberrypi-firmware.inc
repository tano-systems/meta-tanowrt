#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
RPIFW_DATE ?= "20201227"
SRCREV ?= "7ecd699b3f54f4985032d774fdab55f98d44ebf6"
RPIFW_SRC_URI ?= "https://github.com/raspberrypi/firmware/archive/${SRCREV}.tar.gz;downloadfilename=raspberrypi-firmware-${SRCREV}.tar.gz"
RPIFW_S ?= "${WORKDIR}/firmware-${SRCREV}"

SRC_URI = "${RPIFW_SRC_URI}"
SRC_URI[sha256sum] = "479a6bbb60f8ef9249fb65529e203b729605762432ca4684d9adc5f103b3c442"

PV = "${RPIFW_DATE}"

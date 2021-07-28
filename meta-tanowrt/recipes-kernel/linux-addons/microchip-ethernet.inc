#
# SPDX-License-Identifier: MIT
#
# Microchip DSA switch drivers
#
# This file Copyright (C) 2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PV_append = "+ksz2"
FILESEXTRAPATHS_prepend := "${THISDIR}/microchip-ethernet:"

MICROCHIP_ETHERNET_ENABLE ?= "1"
MICROCHIP_ETHERNET_CONFIGS ?= "ksz9477-spi"

MICROCHIP_ETHERNET_GIT_URI      ?= "git://github.com/tano-systems/microchip-ethernet.git"
MICROCHIP_ETHERNET_GIT_BRANCH   ?= "master"
MICROCHIP_ETHERNET_GIT_PROTOCOL ?= "https"
MICROCHIP_ETHERNET_GIT_SRCREV   ?= "c3d6b6fd730865e59c59222f0d9492e381f7e7eb"

MICROCHIP_ETHERNET_DESTDIR ?= "microchip-ethernet"
MICROCHIP_ETHERNET_SRCDIR ?= "${WORKDIR}/${MICROCHIP_ETHERNET_DESTDIR}"

MICROCHIP_ETHERNET_FILES = "\
	${MICROCHIP_ETHERNET_GIT_URI};branch=${MICROCHIP_ETHERNET_GIT_BRANCH};protocol=${MICROCHIP_ETHERNET_GIT_PROTOCOL};name=microchip-ethernet;destsuffix=${MICROCHIP_ETHERNET_DESTDIR} \
	file://0001-dsa-Add-DSA_TAG_PROTO_KSZ.patch \
	file://0002-net-bridge-Increase-maximum-hardware-header-length.patch \
"

SRCREV_microchip-ethernet = "${MICROCHIP_ETHERNET_GIT_SRCREV}"

do_microchip_ethernet_copy_src() {
	for f in $(find ${MICROCHIP_ETHERNET_SRCDIR}/drivers/ksz/ -type f); do
		DST="${f#${MICROCHIP_ETHERNET_SRCDIR}/drivers/ksz/}"
		mkdir -p $(dirname "${S}/${DST}")
		install -m 0644 "${f}" "${S}/${DST}"
	done
}

python __anonymous() {
    enable = d.getVar('MICROCHIP_ETHERNET_ENABLE', True) or "0"
    if enable != "1":
        return

    configs = (d.getVar('MICROCHIP_ETHERNET_CONFIGS', True) or "").split()
    files   = (d.getVar('MICROCHIP_ETHERNET_FILES', True) or "").split()

    for config in configs:
        d.appendVar('SRC_URI', ' file://%s.cfg ' % (config))

    d.appendVar('SRC_URI', ' '.join(files))
    bb.build.addtask("do_microchip_ethernet_copy_src", "do_kernel_configme", "do_patch", d)

    d.appendVarFlag('do_fetch', 'vardeps', ' MICROCHIP_ETHERNET_FILES')
    d.appendVarFlag('do_fetch', 'vardeps', ' MICROCHIP_ETHERNET_GIT_SRCREV')
}

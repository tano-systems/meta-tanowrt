#
# This file Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Partially taken from meta-openembedded layer
#
SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2"
SRC_URI[sha256sum] = "e796fda27ce06377f491ad91aa286962a68c2b54076aa77a29673d53204453da"

PV = "5.8.1"
PR = "tano0.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches_${PV}:"

# Patches (from meta-openembedded)
SRC_URI += "\
    file://0001-no-gcrypt-badpath.patch \
    file://0002-conditionally-check-libvirt.patch \
    file://0003-fix-to-build-with-glibc-2.25.patch \
    file://0004-configure-Check-for-Wno-error-format-truncation-comp.patch \
    file://0005-Disable-new-gcc8-warnings.patch \
    file://0006-libcollectdclient-Fix-string-overflow-errors.patch \
"

# Patches (from OpenWrt)
SRC_URI += "\
    file://1001-undefined-AM_PATH_LIBGCRYPT.patch \
    file://1050-backport-modbus-little-endian.patch \
    file://1100-rrdtool-add-rrasingle-option.patch \
    file://1140-fix-fqdnlookup.patch \
    file://1300-delay-first-read-cycle.patch \
    file://1400-fix-olsrd-get-all.patch \
    file://1600-fix-libmodbus-detection.patch \
    file://1700-disable-sys-capability-check.patch \
    file://1900-add-iwinfo-plugin.patch \
    file://1920-fix-ping-droprate.patch \
"

# Patches
SRC_URI += "\
    file://2001-irq-plugin-Fix-config-reading.patch \
"

require collectd.inc

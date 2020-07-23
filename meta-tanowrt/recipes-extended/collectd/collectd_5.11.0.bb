#
# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Partially taken from meta-openembedded layer
#
SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2"
SRC_URI[md5sum] = "13b1c946f6684abe453e24b5cd80ec45"
SRC_URI[sha256sum] = "37b10a806e34aa8570c1cafa6006c604796fae13cc2e1b3e630d33dcba9e5db2"

PV = "5.11.0"
PR = "tano0.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches_${PV}:"

# Patches (from OE)
SRC_URI += "\
	file://0006-libcollectdclient-Fix-string-overflow-errors.patch \
"

# Patches (from OpenWrt)
SRC_URI += "\
	file://1001-undefined-AM_PATH_LIBGCRYPT.patch \
	file://1100-rrdtool-add-rrasingle-option.patch \
	file://1140-fix-fqdnlookup.patch \
	file://1300-delay-first-read-cycle.patch \
	file://1320-reaction-to-ntp-time-change-at-boot.patch \
	file://1400-fix-olsrd-get-all.patch \
	file://1600-fix-libmodbus-detection.patch \
	file://1700-disable-sys-capability-check.patch \
	file://1900-add-iwinfo-plugin.patch \
	file://1910-add-cake-qdisc-types.patch \
	file://1920-fix-ubi-data-source-type.patch \
"

# Patches (Tano)
SRC_URI += "\
	file://2001-irq-plugin-Fix-config-reading.patch \
	file://2002-rrdtool-Fix-librrdtool-version-1.0.50-functions-call.patch \
"

require collectd.inc

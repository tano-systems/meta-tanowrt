#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Partially taken from meta-openembedded layer
#
SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2"
SRC_URI[md5sum] = "2b23a65960bc323d065234776a542e04"
SRC_URI[sha256sum] = "5bae043042c19c31f77eb8464e56a01a5454e0b39fa07cf7ad0f1bfc9c3a09d6"

PV = "5.12.0"
PR = "tano0.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches_${PV}:"

# Patches (from OE)
SRC_URI += "\
	file://0001-configure-Check-for-Wno-error-format-truncation-comp.patch \
	file://0001-fix-to-build-with-glibc-2.25.patch \
	file://0001-Remove-including-sys-sysctl.h-on-glibc-based-systems.patch \
	file://0005-Disable-new-gcc8-warnings.patch \
	file://0006-libcollectdclient-Fix-string-overflow-errors.patch \
	file://no-gcrypt-badpath.patch \
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
	file://1920-backport-netlink-reg-noerror.patch \
"

# Patches (Tano)
SRC_URI += "\
	file://2001-irq-plugin-Fix-config-reading.patch \
	file://2002-rrdtool-Fix-librrdtool-version-1.0.50-functions-call.patch \
"

require collectd.inc

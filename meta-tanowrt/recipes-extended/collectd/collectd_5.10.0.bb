#
# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Partially taken from meta-openembedded layer
#
SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2"
SRC_URI[sha256sum] = "a03359f563023e744c2dc743008a00a848f4cd506e072621d86b6d8313c0375b"

PV = "5.10.0"
PR = "tano1.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches_${PV}:"

PACKAGECONFIG[check_uptime] = "--enable-check_uptime,--disable-check_uptime,"
PACKAGECONFIG[pcie_errors] = "--enable-pcie_errors,--disable-pcie_errors,"
PACKAGECONFIG[write_syslog] = "--enable-write_syslog,--disable-write_syslog,"

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
"

# Patches
SRC_URI += "\
    file://2001-irq-plugin-Fix-config-reading.patch \
    file://2002-rrdtool-Fix-librrdtool-version-1.0.50-functions-call.patch \
"

require collectd.inc

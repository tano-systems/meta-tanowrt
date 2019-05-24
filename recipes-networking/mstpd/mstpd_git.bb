#
SUMMARY = "Multiple Spanning Tree Protocol Daemon"

PR = "tano15"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4325afd396febcb659c36b49533135d4 \
                    file://debian/copyright;md5=332234a99007d25da40f41ee96aa388f"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RDEPENDS_${PN} += "bridge-utils"

SRC_URI = "\
	git://github.com/mstpd/mstpd.git;protocol=https;branch=master \
	file://0001-ctl_main.c-Display-information-only-for-added-bridge.patch \
"

PV = "0.0.7+git${SRCPV}"

# 14.11.2018 Disable STP processing on a specific port
SRCREV = "1107ce8df252aab73d10abfed39ffe9ccbc0bd1c"

SRC_URI += "\
	file://bridge-stp \
	file://mstpd.config \
	file://mstpd.sh \
	file://mstpd.init \
"

S = "${WORKDIR}/git"

inherit autotools

EXTRA_OECONF = "--sbindir=/sbin"

do_install_append() {
	# Remove unused in OpenWrt files
	rm -rf ${D}${sysconfdir}/network
	rm -rf ${D}${sysconfdir}/bridge-stp.conf
	rm -rf ${D}/usr/libexec/mstpctl-utils/*

	# Install directories
	install -dm 0755 ${D}${sysconfdir}/config
	install -dm 0755 ${D}${sysconfdir}/init.d
	install -dm 0755 ${D}/lib/functions
	install -dm 0755 ${D}/sbin

	# Install files
	install -m 0755 ${WORKDIR}/bridge-stp ${D}/sbin/bridge-stp
	install -m 0644 ${WORKDIR}/mstpd.config ${D}${sysconfdir}/config/mstpd
	install -m 0755 ${WORKDIR}/mstpd.init ${D}${sysconfdir}/init.d/mstpd
	install -m 0755 ${WORKDIR}/mstpd.sh ${D}/lib/functions/mstpd.sh
}

FILES_${PN} += "/lib/functions/"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "mstpd"
OPENWRT_SERVICE_SCRIPTS_mstpd += "mstpd"
OPENWRT_SERVICE_STATE_mstpd-mstpd ?= "enabled"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/mstpd \
"

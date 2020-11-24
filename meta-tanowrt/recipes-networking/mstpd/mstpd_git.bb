#
SUMMARY = "Multiple Spanning Tree Protocol Daemon"

PR = "tano7"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4325afd396febcb659c36b49533135d4 \
                    file://debian/copyright;md5=332234a99007d25da40f41ee96aa388f"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RDEPENDS_${PN} += "bridge-utils"

SRC_URI = "\
	git://github.com/mstpd/mstpd.git;protocol=https;branch=master \
	file://0001-ctl_main.c-Display-information-only-for-added-bridge.patch \
"

PV = "0.0.8+git${SRCPV}"

# 10.10.2019 treewide: allow room for null-char in strncpy()
SRCREV = "d27d7e93485d881d8ff3a7f85309b545edbe1fc6"

SRC_URI += "\
	file://bridge-stp \
	file://mstpd.config \
	file://mstpd.sh \
	file://mstpd.init \
	file://mstpd-config-migrate.sh \
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

	# Install config migration script
	install -D -m 0755 ${WORKDIR}/mstpd-config-migrate.sh \
		${D}/usr/libexec/mstpctl-utils/mstpd-config-migrate.sh
}

FILES_${PN} += "/lib/functions/"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "mstpd"
TANOWRT_SERVICE_SCRIPTS_mstpd += "mstpd"
TANOWRT_SERVICE_STATE_mstpd-mstpd ?= "enabled"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/mstpd \
"

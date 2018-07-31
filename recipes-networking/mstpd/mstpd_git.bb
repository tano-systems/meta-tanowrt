#
SUMMARY = "Multiple Spanning Tree Protocol Daemon"

PR = "tano6"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4325afd396febcb659c36b49533135d4 \
                    file://debian/copyright;md5=332234a99007d25da40f41ee96aa388f"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	git://github.com/tano-systems/mstpd.git;protocol=https;branch=master \
	file://0001-Startup-delay-patch.patch \
"

PV = "0.0.6+git${SRCPV}"
SRCREV = "64521ef59a274ee1e012d7f5c4389244c408db65"

SRC_URI += "\
	file://openwrt-mstpd.config \
	file://openwrt-bridge-stp \
	file://openwrt-mstp_config_bridge \
"

S = "${WORKDIR}/git"

DEPENDS = "python"
RDEPENDS_${PN} = "python-core"

inherit autotools

EXTRA_OECONF = "--sbindir=/sbin"

do_install_append() {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'procd', 'true', 'false', d)}" = "true" ]; then
		rm -rf ${D}${sysconfdir}/network
		rm -rf ${D}${sysconfdir}/bridge-stp.conf
		rm -rf ${D}/usr/libexec/mstpctl-utils/*

		install -dm 0755 ${D}${sysconfdir}/config
		install -dm 0755 ${D}/usr/libexec/mstpctl-utils
		install -dm 0755 ${D}/sbin

		install -m 0644 ${WORKDIR}/openwrt-mstpd.config ${D}${sysconfdir}/config/mstpd
		install -m 0755 ${WORKDIR}/openwrt-bridge-stp ${D}/sbin/bridge-stp
		install -m 0755 ${WORKDIR}/openwrt-mstp_config_bridge ${D}/usr/libexec/mstpctl-utils/mstp_config_bridge
	fi
}

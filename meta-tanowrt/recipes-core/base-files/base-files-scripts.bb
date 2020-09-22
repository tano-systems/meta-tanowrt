# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license.  See COPYING.MIT for terms

require base-files.inc

PR = "tano7.${INC_PR}"

DESCRIPTION = "Subpackages from base-files from OpenWrt core"
HOMEPAGE = "http://wiki.openwrt.org/"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

REQUIRED_DISTRO_FEATURES = "procd"
CONFLICT_DISTRO_FEATURES = "sysvinit systemd"
inherit distro_features_check

SC = "${WORKDIR}/openwrt/package/base-files/files"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

PACKAGES = "\
	${PN}-openwrt \
	${PN}-sysupgrade \
"

# sysupgrade
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files-sysupgrade:"
SRC_URI += "\
	file://etc/sysupgrade.conf \
	file://lib/upgrade/common.sh \
	file://lib/upgrade/do_stage2 \
	file://lib/upgrade/fwtool.sh \
	file://lib/upgrade/keep.d \
	file://lib/upgrade/nand.sh \
	file://lib/upgrade/stage2 \
	file://lib/upgrade/keep.d/base-files-essential \
	file://sbin/firstboot \
	file://sbin/sysupgrade \
"

do_install_append () {
	install -dm 0755 ${D}${sysconfdir}/config

	install -Dm 0644 ${SC}/lib/functions.sh ${D}/lib/functions.sh
	install -Dm 0644 ${SC}/lib/functions/uci-defaults.sh ${D}/lib/functions/uci-defaults.sh
	install -Dm 0644 ${SC}/lib/functions/system.sh ${D}/lib/functions/system.sh
	install -Dm 0755 ${SC}/bin/ipcalc.sh ${D}/bin/ipcalc.sh

	# sysupgrade
	install -Dm 0644 ${WORKDIR}/etc/sysupgrade.conf ${D}/etc/sysupgrade.conf
	install -Dm 0755 ${WORKDIR}/sbin/sysupgrade ${D}/sbin/sysupgrade
	install -Dm 0755 ${WORKDIR}/sbin/firstboot ${D}/sbin/firstboot

	install -dm 0755 ${D}/lib/upgrade
	install -m 0755 ${WORKDIR}/lib/upgrade/do_stage2 ${D}/lib/upgrade
	install -m 0755 ${WORKDIR}/lib/upgrade/stage2 ${D}/lib/upgrade
	install -m 0644 ${WORKDIR}/lib/upgrade/common.sh ${D}/lib/upgrade
	install -m 0644 ${WORKDIR}/lib/upgrade/fwtool.sh ${D}/lib/upgrade
	install -m 0644 ${WORKDIR}/lib/upgrade/nand.sh ${D}/lib/upgrade

	install -dm 0755 ${D}/lib/upgrade/keep.d
	install -m 0644 ${WORKDIR}/lib/upgrade/keep.d/base-files-essential ${D}/lib/upgrade/keep.d
}

FILES_${PN}-openwrt = "\
                      /lib/functions.sh \
                      /lib/functions/uci-defaults.sh \
                      /lib/functions/system.sh \
                      /bin/ipcalc.sh \
                      ${sysconfdir}/config \
                      "

FILES_${PN}-sysupgrade = "\
                         /etc/sysupgrade.conf \
                         /sbin/sysupgrade \
                         /lib/upgrade/ \
                         /sbin/firstboot \
                         "

CONFFILES_${PN}-openwrt += "\
                           ${sysconfdir}/config \
                           "

CONFFILES_${PN}-sysupgrade += "\
                              ${sysconfdir}/sysupgrade.conf \
                              "

PACKAGE_ARCH = "${MACHINE_ARCH}"

BBCLASSEXTEND = "native"

#
# Hotplug scripts for the Network ports status LuCI application
#
# Copyright (c) 2019, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0"
PR = "tano0"

RDEPENDS_${PN} += "luci-app-netports"

SUMMARY = "Hotplug scripts for the Network ports status LuCI application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

do_compile[noexec] = "1"
do_configure[noexec] = "1"

SRC_URI = "file://luci-app-netports.hotplug"

do_install() {
	install -dm 0755 ${D}${sysconfdir}/hotplug.d/net
	install -m 0755 ${WORKDIR}/luci-app-netports.hotplug ${D}${sysconfdir}/hotplug.d/net/50-luci-app-netports
}

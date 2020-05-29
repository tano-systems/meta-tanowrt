# This file Copyright (C) 2018, 2020 Anton Kikin <a.kikin@tano-systems.com>
#
DESCRIPTION = "3G/GPRS datacard management utility"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://gpl.txt;md5=393a5ca445f6965873eca0259a17f833"

PR = "tano10"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RDEPENDS_${PN} += "ppp"

SRC_URI = "\
	https://sourceforge.net/projects/comgt/files/comgt/${PV}/comgt.${PV}.tgz \
	file://3g.chat \
	file://3g.sh \
	file://3g.usb \
	file://directip-stop.gcom \
	file://directip.gcom \
	file://directip.sh \
	file://evdo.chat \
	file://getcnum.gcom \
	file://getstrength.gcom \
	file://getimsi.gcom \
	file://getcarrier.gcom \
	file://getcardinfo.gcom \
	file://ncm.json \
	file://ncm.sh \
	file://runcommand.gcom \
	file://setpin.gcom \
	file://setmode.gcom \
"

SRC_URI[md5sum] = "db2452680c3d953631299e331daf49ef"
SRC_URI[sha256sum] = "0cedb2a5aa608510da66a99aab74df3db363df495032e57e791a2ff55f1d7913"

# Patches
SRC_URI_append = "\
	file://001-compile-fix.patch \
	file://003-termios.patch \
	file://004-compile-fix.patch \
	file://006-check_tty.patch \
	file://005-no_XCASE.patch \
	file://002-comgt-sms.patch \
"

S = "${WORKDIR}/comgt.${PV}"

FILES_${PN}-bin = "${bindir}/*"
FILES_${PN}-doc = "${mandir} ${docdir} ${infodir}"

do_install_append() {
    install -d ${D}${bindir}
    install -m 0755 ${B}/comgt ${D}${bindir}/comgt

    # gcom -> comgt
    ln -s ${bindir}/comgt ${D}/${bindir}/gcom

    install -d ${D}${sysconfdir}/chatscripts
    install -m 0644 ${WORKDIR}/3g.chat ${D}${sysconfdir}/chatscripts/3g.chat
    install -m 0644 ${WORKDIR}/evdo.chat ${D}${sysconfdir}/chatscripts/evdo.chat

    install -d ${D}${sysconfdir}/gcom
    install -m 0644 ${WORKDIR}/setpin.gcom ${D}${sysconfdir}/gcom/setpin.gcom
    install -m 0644 ${WORKDIR}/setmode.gcom ${D}${sysconfdir}/gcom/setmode.gcom
    install -m 0644 ${WORKDIR}/getcardinfo.gcom ${D}${sysconfdir}/gcom/getcardinfo.gcom
    install -m 0644 ${WORKDIR}/getstrength.gcom ${D}${sysconfdir}/gcom/getstrength.gcom
    install -m 0644 ${WORKDIR}/getcarrier.gcom ${D}${sysconfdir}/gcom/getcarrier.gcom
    install -m 0644 ${WORKDIR}/getcnum.gcom ${D}${sysconfdir}/gcom/getcnum.gcom
    install -m 0644 ${WORKDIR}/getimsi.gcom ${D}${sysconfdir}/gcom/getimsi.gcom

    # scripts
    install -m 0644 ${B}/scripts/command ${D}${sysconfdir}/gcom/command
    install -m 0644 ${B}/scripts/dump ${D}${sysconfdir}/gcom/dump
    install -m 0644 ${B}/scripts/info ${D}${sysconfdir}/gcom/info
    install -m 0644 ${B}/scripts/operator ${D}${sysconfdir}/gcom/operator
    install -m 0644 ${B}/scripts/read-sms ${D}${sysconfdir}/gcom/read-sms
    install -m 0644 ${B}/scripts/send-sms ${D}${sysconfdir}/gcom/send-sms
    install -m 0644 ${B}/scripts/sigmon ${D}${sysconfdir}/gcom/sigmon

    install -d ${D}${sysconfdir}/hotplug.d/tty
    install -m 0644 ${WORKDIR}/3g.usb ${D}${sysconfdir}/hotplug.d/tty/30-3g

    install -d ${D}/lib/netifd/proto
    install -m 0755 ${WORKDIR}/3g.sh ${D}/lib/netifd/proto/3g.sh

    ###### MANUAL

    install -d ${D}${mandir}/man1
    install -m 0644 ${B}/comgt.1 ${D}${mandir}/man1/comgt.1
    install -m 0644 ${B}/sigmon.1 ${D}${mandir}/man1/sigmon.1
}

FILES_${PN} = "\
	${bindir}/gcom \
	${bindir}/comgt \
	${sysconfdir}/chatscripts/3g.chat \
	${sysconfdir}/chatscripts/evdo.chat \
	${sysconfdir}/gcom/setpin.gcom \
	${sysconfdir}/gcom/setmode.gcom \
	${sysconfdir}/gcom/getcardinfo.gcom \
	${sysconfdir}/gcom/getstrength.gcom \
	${sysconfdir}/gcom/getcarrier.gcom \
	${sysconfdir}/gcom/getcnum.gcom \
	${sysconfdir}/gcom/getimsi.gcom \
	${sysconfdir}/gcom/command \
	${sysconfdir}/gcom/dump \
	${sysconfdir}/gcom/info \
	${sysconfdir}/gcom/operator \
	${sysconfdir}/gcom/read-sms \
	${sysconfdir}/gcom/send-sms \
	${sysconfdir}/gcom/sigmon \
	${sysconfdir}/hotplug.d \
	/lib/netifd/proto/3g.sh \
	${mandir}/man1/comgt.1 \
	${mandir}/man1/sigmon.1 \
"

PACKAGES += "${PN}-directip ${PN}-ncm"

##
## comgt-directip
##

do_install_append() {
    install -d ${D}${sysconfdir}/gcom
    install -m 0644 ${WORKDIR}/directip.gcom ${D}${sysconfdir}/gcom/directip.gcom
    install -m 0644 ${WORKDIR}/directip-stop.gcom ${D}${sysconfdir}/gcom/directip-stop.gcom
    install -d ${D}/lib/netifd/proto
    install -m 0755 ${WORKDIR}/directip.sh ${D}/lib/netifd/proto/directip.sh
}

FILES_${PN}-directip = "\
	${sysconfdir}/gcom/directip.gcom \
	${sysconfdir}/gcom/directip-stop.gcom \
	/lib/netifd/proto/directip.sh \
"

RDEPENDS_${PN}-directip += "${PN}"

# ${PN}-directip
inherit kmod/usb-serial
inherit kmod/usb-net

do_compile[depends] += "virtual/kernel:do_shared_workdir"

##
## comgt-ncm
##

do_install_append() {
    install -d ${D}${sysconfdir}/gcom
    install -m 0644 ${WORKDIR}/ncm.json ${D}${sysconfdir}/gcom/ncm.json
    install -m 0644 ${WORKDIR}/runcommand.gcom ${D}${sysconfdir}/gcom/runcommand.gcom
    install -d ${D}/lib/netifd/proto
    install -m 0755 ${WORKDIR}/ncm.sh ${D}/lib/netifd/proto/ncm.sh
}

FILES_${PN}-ncm = "\
	${sysconfdir}/gcom/ncm.json \
	${sysconfdir}/gcom/runcommand.gcom \
	/lib/netifd/proto/ncm.sh \
"

RDEPENDS_${PN}-ncm += "${PN} wwan"

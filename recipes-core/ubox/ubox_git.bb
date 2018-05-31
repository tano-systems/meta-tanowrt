# Copyright (C) 2015, MentorGraphics
# Copyright (C) 2015, Fabio Berton <fabio.berton@ossystems.com.br>
# Copyright (C) 2017, Theodore A. Roth <theodore_roth@trimble.com>
# Copyright (C) 2018, Anton Kikin <a.kikin@tano-systems.com>
PR = "tano1"
DESCRIPTION = "OpenWrt system helper toolbox"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/ubox"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://kmodloader.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SRCREV = "16f7e16181e2f3e9cf3e2ce56a7e291844900d09"

DEPENDS = "ubus libubox uci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}-patches:${THISDIR}/${PN}-openwrt:"

SRC_URI = "\
          git://git.openwrt.org/project/ubox.git \
          file://log.init \
          "

# 14.02.2018 11:08:54
SRCREV = "128bc35fa951ac3beff6e977bc3cced87c2e2600"

S = "${WORKDIR}/git"

inherit cmake openwrt-services openwrt

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "log"
OPENWRT_SERVICE_STATE_${PN}-log = "enabled"

do_install_append () {
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'install -Dm 0755 ${WORKDIR}/log.init ${D}/etc/init.d/log', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/rmmod', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/insmod', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/lsmod', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/modinfo', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/usr/sbin/modprobe', '', d)}
        install -dm 0755 ${D}/sbin
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /usr/sbin/kmodloader ${D}/sbin/kmodloader', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_kmod_manager', 'ubox', 'ln -s /sbin/kmodloader ${D}/sbin/modprobe', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'ln -s /usr/sbin/logd ${D}/sbin/logd', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'ln -s /usr/sbin/logread ${D}/sbin/logread', '', d)}
        ${@bb.utils.contains('VIRTUAL-RUNTIME_syslog', 'ubox', 'ln -s /usr/sbin/validate_data ${D}/sbin/validate_data', '', d)}
}

RDEPENDS_${PN} += "\
                  ubus \
                  libubox \
                  "

FILES_${PN} += "\
               ${libdir} \
               ${base_sbindir} \
               "

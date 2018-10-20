# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano9"

DESCRIPTION = "OpenWrt Network interface configuration daemon"
HOMEPAGE = "http://git.openwrt.org/?p=project/netifd.git;a=summary"
LICENSE = "GPL-2.0"
LIC_FILES_CHKSUM = "file://main.c;beginline=1;endline=13;md5=572cd47ba0e377b26331e67e9f3bc4b3"
SECTION = "base"
DEPENDS = "json-c libubox ubus libnl uci"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
          git://git.openwrt.org/project/netifd.git;name=netifd;branch=master \
          file://100-Fix-IFF_LOWER_UP-define.patch \
          file://300-replace-is_error-helper-with-NULL-check.patch \
          file://network.config \
          file://network.init \
          file://network.hotplug \
          "

# 17.10.2018
# system-linux: enable by default ignore encaplimit for grev6 tunnels
SRCREV_netifd = "841b5d158708ee89d9fa870c40404469cb8e871e"

S = "${WORKDIR}/git"

inherit cmake pkgconfig openwrt openwrt-services update-alternatives openwrt-base-files

OPENWRT_SERVICE_PACKAGES = "netifd"
OPENWRT_SERVICE_SCRIPTS_netifd += "network"
OPENWRT_SERVICE_STATE_netifd-network ?= "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

OECMAKE_C_FLAGS += "-I${STAGING_INCDIR}/libnl3 -Wno-error=cpp"

do_configure_prepend () {
    # replace hardcoded '/lib/' with '${base_libdir}/'
    grep -rnl "/lib/" ${S}/openwrt/package/network/config/netifd/ | xargs sed -i "s:/lib/:${base_libdir}/:g"
}

do_install_append() {
    install -d ${D}${base_libdir}/netifd/
    # cp because recursive
    cp -dR --preserve=mode,links ${S}/openwrt/package/network/config/netifd/files/* ${D}/
    cp -dR --preserve=mode,links ${S}/scripts/* ${D}${base_libdir}/netifd/

    install -Dm 0644 ${S}/openwrt/package/base-files/files/lib/functions/network.sh ${D}${base_libdir}/functions/network.sh
    install -Dm 0755 ${S}/openwrt/package/base-files/files/etc/uci-defaults/12_network-generate-ula ${D}${sysconfdir}/uci-defaults/12_network-generate-ula

    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'install -Dm 0755 ${S}/openwrt/package/base-files/files/sbin/wifi ${D}${base_sbindir}/wifi', '', d)}

    install -dm 0755 ${D}${sysconfdir}/config

    # If config_generate is not present we need a default network config
    ${@bb.utils.contains('IMAGE_INSTALL', 'base-files ', '', 'install -Dm 0644 ${WORKDIR}/network.config ${D}${sysconfdir}/config/network', d)}
    # FIXME: Handle wireless case without config_generate

    install -dm 0755 ${D}/sbin
    ln -sf /usr/sbin/netifd ${D}/sbin/netifd

    install -d ${D}${sysconfdir}/init.d
    install -m 755 ${WORKDIR}/network.init ${D}${sysconfdir}/init.d/network

    install -d ${D}${sysconfdir}/hotplug.d/iface
    install -m 755 ${WORKDIR}/network.hotplug ${D}${sysconfdir}/hotplug.d/iface/00-netstate
}

ALTERNATIVE_${PN} = "ifup ifdown default.script"

ALTERNATIVE_PRIORITY = "40"
ALTERNATIVE_PRIORITY_pkg[default.script] = "60"
ALTERNATIVE_LINK_NAME[ifup] = "${base_sbindir}/ifup"
ALTERNATIVE_LINK_NAME[ifdown] = "${base_sbindir}/ifdown"
ALTERNATIVE_LINK_NAME[default.script] = "/usr/share/udhcpc/default.script"

FILES_${PN} += "\
               /usr/share/udhcpc/default.script* \
               ${base_libdir}/netifd/dhcp.script \
               ${base_libdir}/netifd/utils.sh \
               ${base_libdir}/netifd/netifd-wireless.sh \
               ${base_libdir}/netifd/netifd-proto.sh \
               ${base_libdir}/netifd/proto/dhcp.sh \
               ${base_libdir}/network/config.sh \
               ${base_libdir}/functions/network.sh \
               ${@bb.utils.contains('IMAGE_INSTALL', 'base-files ', '', '${sysconfdir}/config/network', d)} \
               ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', '/sbin/wifi', '', d)} \
               "

CONFFILES_${PN}_append = "\
                         ${sysconfdir}/config/network \
                         $(sysconfdir)/config/wireless \
	                 "

RDEPENDS_${PN} += "\
                  bridge-utils \
                  base-files-scripts-openwrt \
                  kmod-bridge \
                  "

do_configure[depends] += "virtual/kernel:do_shared_workdir"

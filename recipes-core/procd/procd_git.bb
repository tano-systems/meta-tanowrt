# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano6"
SUMMARY = "procd is the new OpenWrt process management daemon written in C"
DESCRIPTION = "procd is both both VIRTUAL-RUNTIME-init_manager and \
              VIRTUAL_RUNTIME-dev_manager (like systemd/systemd-udev) \
              "
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://git.openwrt.org/project/procd.git;branch=master \
"

# 30.07.2018
# Allow disabling seccomp or changing the whitelist
SRCREV_pn-procd = "e29966f04cdf549a01f721f93634672055da8af4"

S = "${WORKDIR}/git"
PD = "${S}/openwrt/package/system/procd/files"
BF = "${S}/openwrt/package/base-files/files"

inherit cmake openwrt openwrt-services pkgconfig openwrt-base-files

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "boot done sysctl umount"
OPENWRT_SERVICE_STATE_${PN}-boot = "enabled"
OPENWRT_SERVICE_STATE_${PN}-done = "enabled"
OPENWRT_SERVICE_STATE_${PN}-sysctl = "enabled"
OPENWRT_SERVICE_STATE_${PN}-umount = "enabled"

SRCREV_openwrt = "${OPENWRT_SRCREV}"

OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_STDERR ?= "y"
OPENWRT_CONFIG_TARGET_PREINIT_TIMEOUT ?= "2"
OPENWRT_CONFIG_TARGET_PREINIT_INIT_PATH ?= "/usr/sbin:/usr/bin:/sbin:/bin"
OPENWRT_CONFIG_TARGET_INIT_ENV ?= ""
OPENWRT_CONFIG_TARGET_INIT_CMD ?= "/sbin/init"
OPENWRT_CONFIG_TARGET_INIT_SUPPRESS_STDERR ?= "y"
OPENWRT_CONFIG_TARGET_PREINIT_IFNAME ?= ""
OPENWRT_CONFIG_TARGET_PREINIT_IP ?= "192.168.1.1"
OPENWRT_CONFIG_TARGET_PREINIT_NETMASK ?= "255.255.255.0"
OPENWRT_CONFIG_TARGET_PREINIT_BROADCAST ?= "192.168.1.255"
OPENWRT_CONFIG_TARGET_PREINIT_SHOW_NETMSG ?= ""
OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_FAILSAFE_NETMSG ?= ""
OPENWRT_CONFIG_TARGET_PREINIT_DISABLE_FAILSAFE ?= ""

do_install_append() {
    # Early init
    install -Dm 0755 ${BF}/etc/preinit ${D}${sysconfdir}/preinit
    install -Dm 0755 ${BF}/etc/diag.sh ${D}${sysconfdir}/diag.sh
    install -Dm 0644 ${BF}/lib/functions/preinit.sh ${D}${base_libdir}/functions/preinit.sh
    install -Dm 0644 ${BF}/rom/note ${D}/rom/note

    install -d ${D}${base_libdir}
    cp --preserve=mode,timestamps -R ${BF}/lib/preinit ${D}${base_libdir}

    # Early init + dev manager / coldplug
    install -Dm 0644 ${PD}/hotplug-preinit.json ${D}${sysconfdir}/hotplug-preinit.json

    # Init
    install -Dm 0644 ${PD}/procd.sh ${D}${base_libdir}/functions/procd.sh
    install -Dm 0755 ${PD}/reload_config ${D}${base_sbindir}/reload_config
    install -Dm 0644 ${BF}/lib/functions/service.sh ${D}${base_libdir}/functions/service.sh
    install -Dm 0755 ${BF}/etc/rc.common ${D}${sysconfdir}/rc.common
    install -Dm 0755 ${BF}/etc/rc.local ${D}${sysconfdir}/rc.local
    install -Dm 0755 ${BF}/etc/init.d/done ${D}${sysconfdir}/init.d/done
    install -Dm 0755 ${BF}/etc/init.d/sysctl ${D}${sysconfdir}/init.d/sysctl
    install -Dm 0755 ${BF}/etc/init.d/umount ${D}${sysconfdir}/init.d/umount
    install -Dm 0755 ${BF}/usr/libexec/login.sh ${D}/usr/libexec/login.sh
    install -dm 0755 ${D}/etc/rc.d
    # FIXME: Need to split openwrt base-files 'boot' script so that
    # things that need to be done by procd even when not on a full
    # openwrt image get done without errors
    # and things which are openwrt-image specific stay in base-files
    install -Dm 0755 ${BF}/etc/init.d/boot ${D}${sysconfdir}/init.d/boot

    # Dev manager / hotplug / coldplug
    install -Dm 0644 ${PD}/hotplug.json ${D}${sysconfdir}/hotplug.json
    install -d ${D}${sysconfdir}
    cp --preserve=mode,timestamps -R ${BF}/etc/rc.button ${D}${sysconfdir}
    install -Dm 0755 ${BF}/sbin/hotplug-call ${D}${base_sbindir}/hotplug-call
    cp -dR --preserve=mode,links ${BF}/etc/hotplug.d ${D}${sysconfdir}

    # Common default PATH
    install -dm 0755 ${D}/lib/preinit
    PREINIT_CONF="${D}/lib/preinit/00_preinit.conf"
    echo "pi_suppress_stderr=\"${OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_STDERR}\"" >${PREINIT_CONF}
    echo "fs_failsafe_wait_timeout=${OPENWRT_CONFIG_TARGET_PREINIT_TIMEOUT}" >>${PREINIT_CONF}
    echo "pi_init_path=\"${OPENWRT_CONFIG_TARGET_PREINIT_INIT_PATH}\"" >>${PREINIT_CONF}
    echo "pi_init_env=\"${OPENWRT_CONFIG_TARGET_INIT_ENV}\"" >>${PREINIT_CONF}
    echo "pi_init_cmd=\"${OPENWRT_CONFIG_TARGET_INIT_CMD}\"" >>${PREINIT_CONF}
    echo "pi_init_suppress_stderr=\"${OPENWRT_CONFIG_TARGET_INIT_SUPPRESS_STDERR}\"" >>${PREINIT_CONF}
    echo "pi_ifname=\"${OPENWRT_CONFIG_TARGET_PREINIT_IFNAME}\"" >>${PREINIT_CONF}
    echo "pi_ip=\"${OPENWRT_CONFIG_TARGET_PREINIT_IP}\"" >>${PREINIT_CONF}
    echo "pi_netmask=\"${OPENWRT_CONFIG_TARGET_PREINIT_NETMASK}\"" >>${PREINIT_CONF}
    echo "pi_broadcast=\"${OPENWRT_CONFIG_TARGET_PREINIT_BROADCAST}\"" >>${PREINIT_CONF}
    echo "pi_preinit_net_messages=\"${OPENWRT_CONFIG_TARGET_PREINIT_SHOW_NETMSG}\"" >>${PREINIT_CONF}
    echo "pi_preinit_no_failsafe_netmsg=\"${OPENWRT_CONFIG_TARGET_PREINIT_SUPPRESS_FAILSAFE_NETMSG}\"" >>${PREINIT_CONF}
    echo "pi_preinit_no_failsafe=\"${OPENWRT_CONFIG_TARGET_PREINIT_DISABLE_FAILSAFE}\"" >>${PREINIT_CONF}

    sed -i "s#%PATH%#/usr/sbin:/usr/bin:/sbin:/bin#g" \
          ${D}${sysconfdir}/preinit \
          ${D}${base_sbindir}/hotplug-call

    # Make sure things are where they are expected to be
    install -dm 0755 ${D}/sbin ${D}/lib
    ln -s /usr/sbin/procd ${D}/sbin/procd
    ln -s /usr/sbin/init ${D}/sbin/init
    ln -s /usr/sbin/askfirst ${D}/sbin/askfirst
    ln -s /usr/sbin/udevtrigger ${D}/sbin/udevtrigger
    mv ${D}${libdir}/libsetlbf.so ${D}${base_libdir}/libsetlbf.so
    rmdir ${D}/usr/lib
}

RDEPENDS_${PN} += "\
                  fstools \
                  base-files-scripts-openwrt \
                  ${PN}-inittab \
                  "

FILES_${PN} = "/"

ALTERNATIVE_${PN} = "init"

ALTERNATIVE_PRIORITY = "40"
ALTERNATIVE_TARGET[init] = "${base_sbindir}/init"

python () {
    if not bb.utils.contains('DISTRO_FEATURES', 'procd', True, False, d):
        raise bb.parse.SkipPackage("'procd' not in DISTRO_FEATURES")
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/hotplug.json \
	${sysconfdir}/rc.local \
"

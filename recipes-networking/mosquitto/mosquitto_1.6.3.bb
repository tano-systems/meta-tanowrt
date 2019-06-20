#
SUMMARY = "Open source MQTT v3.1/3.1.1 implemention"
DESCRIPTION = "Mosquitto is an open source (Eclipse licensed) message broker that implements the MQ Telemetry Transport protocol version 3.1 and 3.1.1. MQTT provides a lightweight method of carrying out messaging using a publish/subscribe model. "
HOMEPAGE = "http://mosquitto.org/"
SECTION = "console/network"
PR = "tano0"

LICENSE = "EPL-1.0 | EDL-1.0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

S = "${WORKDIR}/mosquitto-${PV}"

LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=62ddc846179e908dc0c8efec4a42ef20 \
                    file://edl-v10;md5=c09f121939f063aeb5235972be8c722c \
                    file://epl-v10;md5=8d383c379e91d20ba18a52c3e7d3a979 \
                    file://notice.html;md5=a00d6f9ab542be7babc2d8b80d5d2a4c \
"

SRC_URI = "http://mosquitto.org/files/source/mosquitto-${PV}.tar.gz \
           file://mosquitto.init \
           file://mosquitto.config \
"

SRC_URI[md5sum] = "bd19cf124459ee26040912786ef177f7"
SRC_URI[sha256sum] = "9ef5cc75f4fe31d7bf50654ddf4728ad9e1ae2e5609a4b42ecbbcb4a209ed17e"

TARGET_CC_ARCH += "${LDFLAGS}"

inherit useradd

PACKAGECONFIG ??= "ssl uuid \
                  "

PACKAGECONFIG[dns-srv] = "WITH_SRV=yes,WITH_SRV=no,c-ares"
PACKAGECONFIG[ssl] = "WITH_TLS=yes WITH_TLS_PSK=yes,WITH_TLS=no WITH_TLS_PSK=no,openssl"
PACKAGECONFIG[uuid] = "WITH_UUID=yes,WITH_UUID=no,util-linux"
PACKAGECONFIG[websockets] = "WITH_WEBSOCKETS=yes,WITH_WEBSOCKETS=no,libwebsockets"

EXTRA_OEMAKE = " \
    prefix=${prefix} \
    mandir=${mandir} \
    localedir=${localedir} \
    ${PACKAGECONFIG_CONFARGS} \
    STRIP=/bin/true \
    WITH_DOCS=no \
    WITH_BUNDLED_DEPS=no \
"

export LIB_SUFFIX = "${@d.getVar('baselib').replace('lib', '')}"

do_install() {
    oe_runmake 'DESTDIR=${D}' install

#    install -d ${D}${systemd_unitdir}/system/
#    install -m 0644 ${S}/service/systemd/mosquitto.service.notify ${D}${systemd_unitdir}/system/mosquitto.service

    install -d ${D}${sysconfdir}/mosquitto
    install -m 0644 ${D}${sysconfdir}/mosquitto/mosquitto.conf.example \
                    ${D}${sysconfdir}/mosquitto/mosquitto.conf

    install -d ${D}${sysconfdir}/config/
    install -m 0644 ${WORKDIR}/mosquitto.config ${D}${sysconfdir}/config/mosquitto

    install -d ${D}${sysconfdir}/init.d/
    install -m 0755 ${WORKDIR}/mosquitto.init ${D}${sysconfdir}/init.d/mosquitto
    sed -i -e 's,@SBINDIR@,${sbindir},g' \
        -e 's,@BASE_SBINDIR@,${base_sbindir},g' \
        -e 's,@LOCALSTATEDIR@,${localstatedir},g' \
        -e 's,@SYSCONFDIR@,${sysconfdir},g' \
        ${D}${sysconfdir}/init.d/mosquitto
}

PACKAGES += "libmosquitto1 libmosquittopp1 ${PN}-clients"

PACKAGE_BEFORE_PN = "${PN}-examples"

FILES_${PN} = "${sbindir}/mosquitto \
               ${bindir}/mosquitto_passwd \
               ${sysconfdir}/mosquitto \
               ${sysconfdir}/init.d \
               ${sysconfdir}/config \
"

CONFFILES_${PN} += " \
	${sysconfdir}/mosquitto/mosquitto.conf \
	${sysconfdir}/config/mosquitto \
"

FILES_libmosquitto1 = "${libdir}/libmosquitto.so.1"

FILES_libmosquittopp1 = "${libdir}/libmosquittopp.so.1"

FILES_${PN}-clients = "${bindir}/mosquitto_pub \
                       ${bindir}/mosquitto_sub \
                       ${bindir}/mosquitto_rr \
"

FILES_${PN}-examples = "${sysconfdir}/mosquitto/*.example"

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system --no-create-home --shell /bin/false \
                       --user-group mosquitto"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "mosquitto"
OPENWRT_SERVICE_SCRIPTS_mosquitto += "mosquitto"
OPENWRT_SERVICE_STATE_mosquitto-mosquitto ?= "enabled"

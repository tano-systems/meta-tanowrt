#
PR = "tano2"

SUMMARY = "A 802.1ab implementation (LLDP) to help you locate neighbors of all your equipments"
SECTION = "net/misc"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/ISC;md5=f3b90e78ea0cffb20bf5cca7947a896d"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

DEPENDS = "libbsd libevent"

# RDEPENDS_${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'procd', 'procps', '', d)}"

SRC_URI = "\
    https://media.luffy.cx/files/lldpd/lldpd-1.0.1.tar.gz \
    file://0001-Fixed-strtonum-type-conversion-bug.patch \
"

SRC_URI[md5sum] = "91de961bfccfa8790e0514a5dc6eafb3"
SRC_URI[sha256sum] = "450b622aac7ae1758f1ef82f3b7b94ec47f2ff33abfb0e6ac82555b9ee55f151"

inherit autotools pkgconfig bash-completion useradd

USERADD_PACKAGES = "${PN}"
USERADD_PARAM_${PN} = "--system -g lldpd --shell /bin/false lldpd"
GROUPADD_PARAM_${PN} = "--system lldpd"

EXTRA_OECONF += "--without-embedded-libevent \
                 --disable-oldies \
                 --with-privsep-user=lldpd \
                 --with-privsep-group=lldpd \
                 --without-sysusersdir \
"

PACKAGECONFIG ??= "cdp fdp edp sonmp lldpmed dot1 dot3 snmp"
PACKAGECONFIG[xml] = "--with-xml,--without-xml,libxm2"
PACKAGECONFIG[snmp] = "--with-snmp,--without-snmp,net-snmp"
PACKAGECONFIG[readline] = "--with-readline,--without-readline,readline"
PACKAGECONFIG[seccomp] = "--with-seccomp,--without-seccomp,libseccomp"
PACKAGECONFIG[cdp] = "--enable-cdp,--disable-cdp"
PACKAGECONFIG[fdp] = "--enable-fdp,--disable-fdp"
PACKAGECONFIG[edp] = "--enable-edp,--disable-edp"
PACKAGECONFIG[sonmp] = "--enable-sonmp,--disable-sonmp"
PACKAGECONFIG[lldpmed] = "--enable-lldpmed,--disable-lldpmed"
PACKAGECONFIG[dot1] = "--enable-dot1,--disable-dot1"
PACKAGECONFIG[dot3] = "--enable-dot3,--disable-dot3"
PACKAGECONFIG[custom] = "--enable-custom,--disable-custom"

PACKAGES =+ "${PN}-zsh-completion"

FILES_${PN} += "${libdir}/sysusers.d"
#RDEPENDS_${PN} += "os-release"

FILES_${PN}-zsh-completion += "${datadir}/zsh/"
# FIXME: zsh is broken in meta-oe so this cannot be enabled for now
#RDEPENDS_${PN}-zsh-completion += "zsh"

SRC_URI_append = "\
    file://lldpd.config \
    file://lldpd.init \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "lldpd"
OPENWRT_SERVICE_SCRIPTS_lldpd += "lldpd"
OPENWRT_SERVICE_STATE_lldpd-lldpd ?= "enabled"

do_install_append() {
    if [ "${@bb.utils.contains('DISTRO_FEATURES', 'procd', 'true', 'false', d)}" = "true" ]; then
        install -Dm 0755 ${WORKDIR}/lldpd.init    ${D}${sysconfdir}/init.d/lldpd
        install -Dm 0644 ${WORKDIR}/lldpd.config  ${D}${sysconfdir}/config/lldpd
        #
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'cdp', 'true', 'false', d)}" = "false" ]; then
            sed -i -e '/cdp/d' ${D}${sysconfdir}/init.d/lldpd ${D}${sysconfdir}/config/lldpd
        fi
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'fdp', 'true', 'false', d)}" = "false" ]; then
            sed -i -e '/fdp/d' ${D}${sysconfdir}/init.d/lldpd ${D}${sysconfdir}/config/lldpd
        fi
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'edp', 'true', 'false', d)}" = "false" ]; then
            sed -i -e '/edp/d' ${D}${sysconfdir}/init.d/lldpd ${D}${sysconfdir}/config/lldpd
        fi
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'sonmp', 'true', 'false', d)}" = "false" ]; then
            sed -i -e '/sonmp/d' ${D}${sysconfdir}/init.d/lldpd ${D}${sysconfdir}/config/lldpd
        fi
        if [ "${@bb.utils.contains('PACKAGECONFIG', 'snmp', 'true', 'false', d)}" = "false" ]; then
            sed -i -e '/agentxsocket/d' ${D}${sysconfdir}/init.d/lldpd ${D}${sysconfdir}/config/lldpd
        fi
    fi
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/lldpd \
"

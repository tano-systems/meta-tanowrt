#
SUMMARY = "Lightweight, easy to configure DNS forwarder and DHCP server"
HOMEPAGE = "http://www.thekelleys.org.uk/dnsmasq/doc.html"
SECTION = "net"
# GPLv3 was added in version 2.41 as license option
LICENSE = "GPLv2 | GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=0636e73ff0215e8d672dc4c32c317bb3 \
                    file://COPYING-v3;md5=d32239bcb673463ab874e80d47fae504"

#at least versions 2.69 and prior are moved to the archive folder on the server
SRC_URI = "http://www.thekelleys.org.uk/dnsmasq/${PN}-${PV}.tar.xz;name=dnsmasq-${PV}"

SRC_URI += "\
    file://lua.patch \
"

SRC_URI[dnsmasq-2.80.md5sum] = "e040e72e6f377a784493c36f9e788502"
SRC_URI[dnsmasq-2.80.sha256sum] = "cdaba2785e92665cf090646cba6f94812760b9d7d8c8d0cfb07ac819377a63bb"

inherit pkgconfig

PR = "tano0"
DEPENDS += "libubus"

PACKAGECONFIG ?= "ubus"

PACKAGECONFIG[ubus] = ",,libubus"
PACKAGECONFIG[dbus] = ",,dbus"
PACKAGECONFIG[idn] = ",,libidn"
PACKAGECONFIG[conntrack] = ",,libnetfilter-conntrack"
PACKAGECONFIG[lua] = ",,lua"
PACKAGECONFIG[resolvconf] = ",,,resolvconf"
EXTRA_OEMAKE = "\
    'COPTS=${@bb.utils.contains('PACKAGECONFIG', 'dbus', '-DHAVE_DBUS', '', d)} \
           ${@bb.utils.contains('PACKAGECONFIG', 'ubus', '-DHAVE_UBUS', '', d)} \
           ${@bb.utils.contains('PACKAGECONFIG', 'idn', '-DHAVE_IDN', '', d)} \
           ${@bb.utils.contains('PACKAGECONFIG', 'conntrack', '-DHAVE_CONNTRACK', '', d)} \
           ${@bb.utils.contains('PACKAGECONFIG', 'lua', '-DHAVE_LUASCRIPT', '', d)}' \
    'CFLAGS=${CFLAGS}' \
    'LDFLAGS=${LDFLAGS}' \
"

SRC_URI += "${@bb.utils.contains('PACKAGECONFIG', 'resolvconf', 'file://dnsmasq.resolvconf file://99_dnsmasq file://dnsmasq-resolvconf-helper', '', d)}"

do_install () {
    oe_runmake "PREFIX=${D}${prefix}" \
               "BINDIR=${D}${bindir}" \
               "MANDIR=${D}${mandir}" \
               install
}

CONFFILES_${PN} = "${sysconfdir}/dnsmasq.conf"

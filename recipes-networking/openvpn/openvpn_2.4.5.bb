PR = "tano3"

SUMMARY = "A full-featured SSL VPN solution via tun device."
HOMEPAGE = "http://openvpn.sourceforge.net"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=7aee596ed2deefe3e8a861e24292abba"

SRC_URI = "http://swupdate.openvpn.org/community/releases/${BP}.tar.gz"

SRC_URI[md5sum] = "9300acfd08081dbf8096a165b2d79ecc"
SRC_URI[sha256sum] = "b1582ff7ebbb67196048a568911856470144acd420de82670148cbffaa0fbf33"

RRECOMMENDS_${PN} = "kernel-module-tun"

inherit autotools

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

require ${PN}-openwrt.inc

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/openvpn \
"

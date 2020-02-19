require wireguard.inc

SRC_URI = "https://git.zx2c4.com/wireguard-tools/snapshot/wireguard-tools-${PV}.tar.xz"
SRC_URI[md5sum] = "aa3d4b3bc9afef1f58f707a7b8c15329"
SRC_URI[sha256sum] = "f5207248c6a3c3e3bfc9ab30b91c1897b00802ed861e1f9faaed873366078c64"

S = "${WORKDIR}/wireguard-tools-${PV}/src"

PR = "tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit pkgconfig

DEPENDS = "wireguard-module libmnl"

SRC_URI += "\
	file://wireguard_watchdog \
	file://wireguard.sh \
"

do_install () {
    oe_runmake DESTDIR="${D}" PREFIX="${prefix}" SYSCONFDIR="${sysconfdir}" \
        WITH_SYSTEMDUNITS=no \
        WITH_BASHCOMPLETION=no \
        WITH_WGQUICK=no \
        install

    install -dm 0755 ${D}${bindir}
    install -m 0755 ${WORKDIR}/wireguard_watchdog ${D}${bindir}/

    install -dm 0755 ${D}${base_libdir}/netifd/proto
    install -m 0755 ${WORKDIR}/wireguard.sh ${D}${base_libdir}/netifd/proto/
}

FILES_${PN} = " \
    ${sysconfdir} \
    ${bindir} \
    ${base_libdir} \
"

RDEPENDS_${PN} = "wireguard-module ubus jsonpath"

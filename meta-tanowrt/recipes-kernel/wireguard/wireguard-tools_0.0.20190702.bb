require wireguard.inc

PR = "tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit pkgconfig

DEPENDS = "wireguard-module libmnl"

SRC_URI += "\
	file://wireguard_watchdog \
	file://wireguard.sh \
"

do_compile_prepend () {
    cd ${S}/tools
}

do_install () {
    cd ${S}/tools
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

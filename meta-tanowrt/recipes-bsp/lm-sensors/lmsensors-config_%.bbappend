PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://sensors.upgrade"

do_install_append() {
	install -d ${D}/lib/upgrade/keep.d
	install -m 0644 ${WORKDIR}/sensors.upgrade ${D}/lib/upgrade/keep.d/sensors
}

FILES_${PN}-libsensors += "/lib/upgrade/keep.d/sensors"
CONFFILES_${PN}-libsensors = "${sysconfdir}/sensors.d/"

PR_append = ".tano0"

do_install_append() {
	# Some applications (e.g. luci-app-statistics) expects sensors in /usr/sbin
	ln -s ${bindir}/sensors ${D}${sbindir}/sensors
}

FILES_${PN}-sensors += "${sbindir}/sensors"
CONFFILES_${PN}-libsensors = "${sysconfdir}/sensors3.conf"

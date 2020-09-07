PR_append = ".tano0"

INSANE_SKIP_${PN}_remove = "dev-deps"
INSANE_SKIP_${PN}_remove = "dev-so"

do_install () {
	oe_runmake install DESTDIR=${D} TARGET_PRODUCT=${TARGET_PRODUCT}
	rm -rf ${D}${includedir}/GL
	chown -R root:root ${D}
}

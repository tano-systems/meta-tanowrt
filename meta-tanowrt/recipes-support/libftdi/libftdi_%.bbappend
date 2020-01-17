PR_append = ".tano0"

EXTRA_OECMAKE += "-DEXAMPLES=on -DCMAKE_SKIP_RPATH=TRUE"

# EEPROM tool
PACKAGES =+ "ftdi-eeprom"
PACKAGECONFIG += "ftdi-eeprom"
PACKAGECONFIG[ftdi-eeprom] = "-DFTDI_EEPROM=on,-DFTDI_EEPROM=off,libconfuse"
FILES_ftdi-eeprom += "${bindir}/ftdi_eeprom"
RDEPENDS_ftdi-eeprom += "libftdi"

# Examples
PACKAGES =+ "ftdi-examples"
RDEPENDS_ftdi-examples += "libftdi"

do_install_append() {
	install -dm 0755 ${D}${bindir}
	install -m 0755 ${B}/examples/baud_test ${D}${bindir}/
	install -m 0755 ${B}/examples/bitbang ${D}${bindir}/
	install -m 0755 ${B}/examples/bitbang_cbus ${D}${bindir}/
	install -m 0755 ${B}/examples/bitbang_ft2232 ${D}${bindir}/
	install -m 0755 ${B}/examples/bitbang2 ${D}${bindir}/
	install -m 0755 ${B}/examples/eeprom ${D}${bindir}/
	install -m 0755 ${B}/examples/find_all ${D}${bindir}/
	install -m 0755 ${B}/examples/serial_test ${D}${bindir}/
	install -m 0755 ${B}/examples/simple ${D}${bindir}/
	install -m 0755 ${B}/examples/stream_test ${D}${bindir}/
}

FILES_ftdi-examples += "\
	${bindir}/baud_test \
	${bindir}/bitbang \
	${bindir}/bitbang_cbus \
	${bindir}/bitbang_ft2232 \
	${bindir}/bitbang2 \
	${bindir}/eeprom \
	${bindir}/find_all \
	${bindir}/serial_test \
	${bindir}/simple \
	${bindir}/stream_test \
"

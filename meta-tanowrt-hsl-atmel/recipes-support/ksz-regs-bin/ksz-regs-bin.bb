DESCRIPTION = "KSZ switch registers read/write utility"
LICENSE = "CLOSED"

PR = "tano0"
COMPATIBLE_MACHINE = "evb-ksz9477|evb-ksz9563"

SRC_URI = "\
	file://Makefile \
	file://regs_bin.c \
"

do_install() {
	install -d ${D}${sbindir}
	install -m 0755 ${B}/regs_bin ${D}${sbindir}/
}

S = "${WORKDIR}"

# Wireless Central Regulatory Domain Database
SUMMARY = "Wireless Central Regulatory Domain Database"
HOMEPAGE = "https://git.kernel.org/pub/scm/linux/kernel/git/sforshee/wireless-regdb.git/"
SECTION = "net"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

PR = "tano0"
PV = "2019.06.03"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/sforshee/wireless-regdb.git;protocol=https"
SRCREV = "49b8d9c09b145c2124184921640fa44f96630afc"

B = "${WORKDIR}/build"
S = "${WORKDIR}/git"

DEPENDS += "python-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:"

SRC_URI += "\
	file://010-regdb-fix-compatibility-with-python2.patch \
	file://500-world-regd-5GHz.patch \
"

do_compile() {
	python ${S}/db2fw.py ${B}/regulatory.db ${S}/db.txt
}

do_install() {
	install -d -m 0755 ${D}${base_libdir}/firmware
	install -m 0644 ${B}/regulatory.db ${D}${base_libdir}/firmware/
}

FILES_${PN} += "${base_libdir}/firmware"

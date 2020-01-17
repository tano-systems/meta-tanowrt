# Wireless Central Regulatory Domain Database
SUMMARY = "Wireless Central Regulatory Domain Database"
HOMEPAGE = "http://wireless.kernel.org/en/developers/Regulatory/CRDA"
SECTION = "net"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://LICENSE;md5=07c4f6dea3845b02a18dc00c8c87699c"

PR = "tano2"
PV = "2017.10.20"

SRC_URI = "git://git.kernel.org/pub/scm/linux/kernel/git/sforshee/wireless-regdb.git;protocol=https"
SRCREV = "4343d359ed5e7404de8803a74df186457b26ab79"

B = "${WORKDIR}/build"
S = "${WORKDIR}/git"

DEPENDS += "python-native"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:"

SRC_URI += "\
	file://100-regdb-write-firmware-file-format-version-code-20.patch \
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

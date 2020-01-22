PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}/patches:${THISDIR}/${PN}_${PV}/files:"

SRC_URI += "\
	file://CVE-2019-12900.patch \
"

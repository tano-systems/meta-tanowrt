#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

PR_append = ".tano0"
SRC_URI += "file://800-include_sockios.patch"

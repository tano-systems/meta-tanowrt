PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://CVE-2019-12450.patch \
           "

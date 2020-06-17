FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:"
PR_append = ".tano0"
SRC_URI += "file://500-world-regd-5GHz.patch"

#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PR_append = ".tano2"
SRC_URI += "file://100-fix_includes.patch"

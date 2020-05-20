#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
PR_append = ".tano1"
SRC_URI += "file://0001-mkfs.ubifs-Add-command-line-option-to-specify-UUID.patch"

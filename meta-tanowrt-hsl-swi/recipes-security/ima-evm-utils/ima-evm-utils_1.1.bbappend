PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

DEPENDS_remove = "attr"
SRC_URI += "file://0001-Remove-dependency-on-libattr.patch"

PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI_append = "\
	file://0001-autofs-move-inclusion-of-linux-limits.h-to-uapi.patch \
"

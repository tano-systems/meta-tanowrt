FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
PR_append = ".tano1"

require ${PN}-openwrt.inc

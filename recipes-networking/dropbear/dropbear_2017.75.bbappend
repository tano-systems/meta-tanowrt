FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
PR_append = ".tano0"

require ${PN}-openwrt.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
PR_append = ".tano2"

require ${PN}-openwrt.inc

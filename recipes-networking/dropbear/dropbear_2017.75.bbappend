FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
PR_append = ".tano3"

require ${PN}-openwrt.inc

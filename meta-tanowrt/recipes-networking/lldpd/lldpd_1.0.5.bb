#
require lldpd.inc

PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
    https://media.luffy.cx/files/lldpd/lldpd-${PV}.tar.gz \
"

SRC_URI[md5sum] = "6949ddf3634423ce3caaaf8a09fc0fa8"
SRC_URI[sha256sum] = "2dd3b212f4dbabfcbb2794c0010b245f9f8e74b387984e757be6243a74c6cb99"

#
require lldpd.inc

PR = "tano6.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
    https://media.luffy.cx/files/lldpd/lldpd-1.0.1.tar.gz \
    file://0001-Fixed-strtonum-type-conversion-bug.patch \
"

SRC_URI[md5sum] = "91de961bfccfa8790e0514a5dc6eafb3"
SRC_URI[sha256sum] = "450b622aac7ae1758f1ef82f3b7b94ec47f2ff33abfb0e6ac82555b9ee55f151"

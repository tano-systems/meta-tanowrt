#
require lldpd.inc

PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
    https://media.luffy.cx/files/lldpd/lldpd-1.0.4.tar.gz \
"

SRC_URI[md5sum] = "33e8d58623f99184e4e709cbbfe45db3"
SRC_URI[sha256sum] = "5319bc032fabf1008d5d91e280276aa7f1bbfbb70129d8526cd4526d7c22724f"

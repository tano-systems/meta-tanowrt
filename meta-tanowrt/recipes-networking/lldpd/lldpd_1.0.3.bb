#
require lldpd.inc

PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
    https://media.luffy.cx/files/lldpd/lldpd-1.0.3.tar.gz \
"

SRC_URI[md5sum] = "04844328f053ee343344d892efb8a00c"
SRC_URI[sha256sum] = "39fced395168015416bfe78b95414facf066f841f349024433aa20ab54e4c360"

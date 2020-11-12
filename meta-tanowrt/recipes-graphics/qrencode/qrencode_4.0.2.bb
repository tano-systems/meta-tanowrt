require qrencode.inc

PV = "4.0.2"
PR = "tano0.${INC_PR}"

SRC_URI = "git://github.com/fukuchi/libqrencode.git;branch=4.0"
SRCREV = "59ee597f913fcfda7a010a6e106fbee2595f68e4"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
SRC_URI += "file://001-add-inline-svg.patch"

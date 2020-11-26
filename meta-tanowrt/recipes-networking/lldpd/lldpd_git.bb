#
require lldpd.inc

PV = "1.0.7+git${SRCPV}"
PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

EXTRA_AUTORECONF_append = " --exclude=gnu-configize"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/vincentbernat/lldpd.git"
SRCREV = "e16e1dce259b2fed4b866a1ca0780c6ffe7453e5"

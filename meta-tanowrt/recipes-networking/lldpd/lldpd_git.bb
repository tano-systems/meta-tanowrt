#
require lldpd.inc

PV = "1.0.4+git${SRCPV}"
PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

EXTRA_AUTORECONF_append = " --exclude=gnu-configize"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/vincentbernat/lldpd.git"
SRCREV = "3aeae72b97716fddac290634fad02b952d981f17"

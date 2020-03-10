#
require lldpd.inc

PV = "1.0.5+git${SRCPV}"
PR = "tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

EXTRA_AUTORECONF_append = " --exclude=gnu-configize"

S = "${WORKDIR}/git"
SRC_URI = "git://github.com/vincentbernat/lldpd.git"
SRCREV = "657d89df25f4db1db2e4d5783127b200dc8f0d1e"

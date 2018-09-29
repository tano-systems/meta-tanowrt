FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

PR_append = ".tano2"

SRC_URI_append = "\
    file://ipset.cfg \
    file://bootchart.cfg \
"

KERNEL_MODULE_AUTOLOAD_qemux86 += "floppy parport_pc"
KERNEL_MODULE_AUTOLOAD_qemux86-64 += "floppy parport_pc"

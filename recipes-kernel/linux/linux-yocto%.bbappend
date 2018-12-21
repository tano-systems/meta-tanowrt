FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

PR_append = ".tano5"

SRC_URI_append = "\
    file://defconfig.cfg \
    file://ipset.cfg \
    file://ppp.cfg \
    file://usb-net.cfg \
    file://netfilter.cfg \
    file://bootchart.cfg \
"

KERNEL_MODULE_AUTOLOAD_qemux86 += "floppy parport_pc"
KERNEL_MODULE_AUTOLOAD_qemux86-64 += "floppy parport_pc"

KERNEL_FEATURES_append = " ${@bb.utils.contains("DISTRO_FEATURES", "cgroups", " features/cgroups/cgroups.scc", "" ,d)} "

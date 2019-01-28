FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

PR_append = ".tano6"

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

python __anonymous () {
    combined_features = set(d.getVar("COMBINED_FEATURES").split())

    if "wifi" in combined_features:
        if "usbhost" in combined_features:
            d.appendVar("KERNEL_FEATURES", " features/wifi/wifi-usb.scc ")
        if "pci" in combined_features:
            d.appendVar("KERNEL_FEATURES", " features/wifi/wifi-pci.scc ")
}

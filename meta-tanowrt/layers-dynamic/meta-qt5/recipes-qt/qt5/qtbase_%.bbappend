PACKAGECONFIG_append = " accessibility fontconfig tslib "
PACKAGECONFIG_append_qemuall = " examples linuxfb no-opengl "
PACKAGECONFIG_remove_qemuall = "tests"

RDEPENDS_${PN} += "qt5-env"

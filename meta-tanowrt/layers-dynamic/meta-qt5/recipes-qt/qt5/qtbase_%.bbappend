PACKAGECONFIG_append = " accessibility fontconfig tslib"
PACKAGECONFIG_append_qemuall = " linuxfb no-opengl "
PACKAGECONFIG_remove = "examples tests"

RDEPENDS_${PN} += "qt5-env"

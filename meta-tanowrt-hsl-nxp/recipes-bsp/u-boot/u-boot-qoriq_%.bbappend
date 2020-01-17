PR_append = ".nxp0"

FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += "\
	file://ls1028ardb_sdcard_defconfig \
"

do_configure_prepend() {
    install -m 0644 ${WORKDIR}/ls1028ardb_sdcard_defconfig ${S}/configs/
}

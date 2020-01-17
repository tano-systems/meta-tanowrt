PR_append = ".tano0"
require u-boot-ti-sdk-common.inc

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI = "\
	${UBOOT_GIT_URI};branch=${BRANCH} \
	file://fw_env.config \
"

do_compile () {
	oe_runmake sandbox_defconfig
	oe_runmake envtools
}

do_install_append() {
	install -m 0644 ${WORKDIR}/fw_env.config ${D}${sysconfdir}/fw_env.config
}

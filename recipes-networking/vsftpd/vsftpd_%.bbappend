#
PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

# Files
SRC_URI_append = "\
	file://vsftpd.conf \
	file://vsftpd.init \
	file://vsftpd.user_list \
	file://vsftpd.ftpusers \
"

# Patches
SRC_URI_append = "\
	file://004-disable-capabilities.patch \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "${PN}"
OPENWRT_SERVICE_SCRIPTS_${PN} = "vsftpd"
OPENWRT_SERVICE_STATE_${PN}-vsftpd = "enabled"

PACKAGECONFIG ??= ""

FILES_${PN} += "/var/"

do_install_append() {
	install -m 0644 ${WORKDIR}/vsftpd.conf ${D}${sysconfdir}/vsftpd.conf
	install -m 0644 ${WORKDIR}/vsftpd.user_list ${D}${sysconfdir}/vsftpd.user_list
	install -m 0644 ${WORKDIR}/vsftpd.ftpusers ${D}${sysconfdir}/vsftpd.ftpusers
	install -m 0755 ${WORKDIR}/vsftpd.init ${D}${sysconfdir}/init.d/vsftpd

	rm -rf ${D}${sysconfdir}/default
}

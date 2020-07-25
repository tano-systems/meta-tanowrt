#
PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

inherit tanowrt-services

#
# OE:
#  --enable-nistest=yes
#  --enable-olt=yes
#
EXTRA_OECONF = "\
	--enable-daemon=yes \
	--enable-threads=no \
	--enable-olt=no \
"

SRC_URI += "\
	file://haveged.init \
"

do_install_append() {
	rm -f ${D}${sysconfdir}/init.d/haveged

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/haveged.init ${D}${sysconfdir}/init.d/haveged
}

TANOWRT_SERVICE_PACKAGES = "haveged"
TANOWRT_SERVICE_SCRIPTS_haveged += "haveged"
TANOWRT_SERVICE_STATE_haveged-haveged ?= "enabled"

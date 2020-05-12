#
PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://netserver.init"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "netperf"
OPENWRT_SERVICE_SCRIPTS_netperf += "netserver"
OPENWRT_SERVICE_STATE_netperf-netserver ?= "disabled"

do_install_append() {
	# Remove sysvinit script
	rm -f ${D}${sysconfdir}/init.d/netperf

	# Install procd init script
	install -m 0755 ${WORKDIR}/netserver.init ${D}${sysconfdir}/init.d/netserver
}

PR_append = ".tano0"

do_install_append() {
	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_network_manager', 'netifd', '1', '0', d)}" = "1" ]; then
		rm -rf ${D}/${sysconfdir}/network/if-pre-up.d
		rm -rf ${D}/${sysconfdir}/network/if-post-down.d
	fi
}

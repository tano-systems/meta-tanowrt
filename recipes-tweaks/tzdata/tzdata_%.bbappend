PR_append = ".tano0"

pkg_postinst_${PN}_append_tano-openwrt() {
	if [ -L ${etc_lt} ] ; then
		rm -f "${etc_lt}"
	fi

	ln -sf /tmp/localtime ${etc_lt}
}

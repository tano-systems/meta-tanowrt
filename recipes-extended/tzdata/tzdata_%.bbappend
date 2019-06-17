PR_append = ".tano1"

pkg_postinst_${PN}_append() {
	if [ -L ${etc_lt} ] ; then
		rm -f "${etc_lt}"
	fi

	ln -sf /tmp/localtime ${etc_lt}
}

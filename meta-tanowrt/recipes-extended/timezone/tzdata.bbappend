PR_append = ".tano2"

RDEPENDS_${PN}_remove = "tzdata-misc"
RDEPENDS_${PN}_remove = "tzdata-right"
RDEPENDS_${PN}_remove = "tzdata-posix"

pkg_postinst_${PN}_append() {
	if [ -L ${etc_lt} ] ; then
		rm -f "${etc_lt}"
	fi

	ln -sf /tmp/localtime ${etc_lt}
}

PR_append = ".tano0"

do_install_append() {
	install -d ${D}${libexecdir}/mc/extfs.d
}

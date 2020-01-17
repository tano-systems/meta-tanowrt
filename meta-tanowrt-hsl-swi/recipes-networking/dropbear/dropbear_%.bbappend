PR_append = ".swi0"

do_install_append() {
	rm -rf ${D}${sysconfdir}/init.d/dropbear.wrapper
}

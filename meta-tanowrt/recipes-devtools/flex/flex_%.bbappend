PR_append = ".tano0"

do_install_append() {
	# symlink /usr/bin/lex to /usr/bin/flex
	ln -s flex ${D}/${bindir}/lex
}

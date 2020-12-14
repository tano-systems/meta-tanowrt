PR_append = ".tano0"

do_install_append() {
	install -d ${D}${datadir}/fonts/truetype
	for i in *.ttf; do
		ln -s ${prefix}/share/fonts/ttf/${i} ${D}${prefix}/share/fonts/truetype/${i}
	done
}

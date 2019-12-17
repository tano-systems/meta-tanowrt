PR_append = "${@oe.utils.version_less_or_equal('PV', '1.0.50', '', '.tano0', d)}"
RDEPENDS_${PN} += "${@oe.utils.version_less_or_equal('PV', '1.0.50', '', 'ttf-dejavu-sans-mono', d)}"

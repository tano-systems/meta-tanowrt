PR:append = ".ti1"

EXTRA_LIBS = ""

EXTRA_LIBS:append:am335x-evm = " \
	pru-icss-dev \
	pru-icss-staticdev \
"

RDEPENDS:${PN} += "${EXTRA_LIBS}"

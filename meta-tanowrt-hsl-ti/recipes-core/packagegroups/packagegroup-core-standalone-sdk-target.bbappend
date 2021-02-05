PR_append = ".ti0"

EXTRA_LIBS_append_am57xx-evm = " \
	osal-dev \
	osal-staticdev \
	pruss-lld-dev \
	pruss-lld-staticdev \
	icss-emac-lld-dev \
	icss-emac-lld-staticdev \
"

EXTRA_LIBS_append_am335x-evm = " \
	osal-dev \
	osal-staticdev \
	pruss-lld-dev \
	pruss-lld-staticdev \
	icss-emac-lld-dev \
	icss-emac-lld-staticdev \
	pru-icss-dev \
	pru-icss-staticdev \
"

RDEPENDS_${PN} += "${EXTRA_LIBS}"

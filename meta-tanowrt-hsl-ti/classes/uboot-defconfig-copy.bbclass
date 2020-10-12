#
UBOOT_DEFCONFIG_COPY ?= "1"

python __anonymous () {
    ubootmachine = d.getVar("UBOOT_MACHINE")
    ubootdefconfigcopy = d.getVar("UBOOT_DEFCONFIG_COPY")
    if ubootdefconfigcopy == '1':
        if ubootmachine:
            for config in ubootmachine.split():
                d.appendVar("SRC_URI", " file://%s " % config)
}

do_configure_prepend() {
	if [ "${UBOOT_DEFCONFIG_COPY}" = "1" ]; then
		for config in ${UBOOT_MACHINE}; do
			if [ -e "${WORKDIR}/${config}" ]; then
				cp ${WORKDIR}/${config} ${S}/configs/${config}
			fi
		done
	fi
}

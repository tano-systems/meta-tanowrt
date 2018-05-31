#
# LuCI application class
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

inherit openwrt-luci

do_configure[noexec] = "1"
do_compile[noexec]   = "1"

RDEPENDS_${PN} += "luci"

FILES_${PN} += "${LUCI_INSTALL_LUASRC_PATH}"
FILES_${PN} += "${LUCI_INSTALL_HTDOCS_PATH}"
FILES_${PN} += "${LUCI_INSTALL_ROOT_PATH}"

do_install_append() {
	# Install luasrc
	if [ -d "${S}/luasrc" ]; then
		( cd ${S}/luasrc; find * -type f -exec install -Dm 755 "{}" "${D}${LUCI_INSTALL_LUASRC_PATH}/{}" \; )
	fi

	# Install htdocs
	if [ -d "${S}/htdocs" ]; then
		( cd ${S}/htdocs; find * -type f -exec install -Dm 755 "{}" "${D}${LUCI_INSTALL_HTDOCS_PATH}/{}" \; )
	fi

	# Install root files
	if [ -d "${S}/root" ]; then
		( cd ${S}; find * -type f -exec install -Dm 755 "{}" "${D}${LUCI_INSTALL_ROOT_PATH}/{}" \; )
	fi
}

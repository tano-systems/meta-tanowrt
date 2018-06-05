#
# LuCI theme class
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

LUCI_THEME_SRC ?= "${S}"

do_install_append() {
	# Install luasrc
	if [ -d "${LUCI_THEME_SRC}/luasrc" ]; then
		( cd ${LUCI_THEME_SRC}/luasrc; find * -type f -exec install -Dm 755 "{}" "${D}${LUCI_INSTALL_LUASRC_PATH}/{}" \; )
	fi

	# Install htdocs
	if [ -d "${LUCI_THEME_SRC}/htdocs" ]; then
		( cd ${LUCI_THEME_SRC}/htdocs; find * -type f -exec install -Dm 755 "{}" "${D}${LUCI_INSTALL_HTDOCS_PATH}/{}" \; )
	fi

	# Install root files
	if [ -d "${LUCI_THEME_SRC}/root" ]; then
		( cd ${LUCI_THEME_SRC}; find * -type f -exec install -Dm 755 "{}" "${D}${LUCI_INSTALL_ROOT_PATH}/{}" \; )
	fi
}

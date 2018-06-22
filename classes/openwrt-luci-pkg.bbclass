#
# LuCI package class
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

LUCI_PKG_SRC ?= "${S}"

LUCI_PKG_EXECUTABLE ?= "\
	${D}${LUCI_INSTALL_ROOT_PATH}${sysconfdir}/uci-defaults/* \
	${D}${LUCI_INSTALL_ROOT_PATH}${sysconfdir}/init.d/* \
"

do_install_append() {
	# Install luasrc
	if [ -d "${LUCI_PKG_SRC}/luasrc" ]; then
		( cd ${LUCI_PKG_SRC}/luasrc; find * -type f -exec install -Dm 644 "{}" "${D}${LUCI_INSTALL_LUASRC_PATH}/{}" \; )
	fi

	# Install htdocs
	if [ -d "${LUCI_PKG_SRC}/htdocs" ]; then
		( cd ${LUCI_PKG_SRC}/htdocs; find * -type f -exec install -Dm 644 "{}" "${D}${LUCI_INSTALL_HTDOCS_PATH}/{}" \; )
	fi

	# Install root files
	if [ -d "${LUCI_PKG_SRC}/root" ]; then
		( cd ${LUCI_PKG_SRC}/root; find * -type f -exec install -Dm 644 "{}" "${D}${LUCI_INSTALL_ROOT_PATH}/{}" \; )
	fi

	# Executable files
	for p in "${LUCI_PKG_EXECUTABLE}"; do
		for f in ${p}; do
			if [ -e "$f" ]; then
				chmod 0755 "$f"
			fi
		done
	done
}

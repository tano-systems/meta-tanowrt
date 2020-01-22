#
# LuCI packages generation class
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

require recipes-extended/luci/luci.inc

DEPENDS += "jsmin-native luasrcdiet-native"

# By default make only english packages
LUCI_LANGUAGES ?= 'en'

# Default installation paths
LUCI_INSTALL_LUASRC_PATH ?= "/usr/lib/lua/5.1/luci"
LUCI_INSTALL_HTDOCS_PATH ?= "/www"
LUCI_INSTALL_ROOT_PATH   ?= "/"

LUCI_PKG_VERSION="${PV}-${PR}-${@d.getVar('LUCI_GIT_SRCREV', True)[:10]}"

LUCI_DO_POST_INSTALL_ACTIONS ?= "1"
LUCI_DO_SUBSTITUTE_VERSION ?= "1"
LUCI_DO_MINIFY_JS ?= "1"
LUCI_DO_MINIFY_LUA ?= "1"
LUCI_DO_RM_LUADOC ?= "1"

post_install_actions() {
	if [ "${LUCI_DO_POST_INSTALL_ACTIONS}" = "0" ]; then
		return
	fi

	ACTIONS_DIR="${D}"

	if [ "${LUCI_DO_RM_LUADOC}" = "1" ]; then
		find "${ACTIONS_DIR}" -type f -name '*.luadoc' | while read src; do
			rm -f "${src}"
		done
	fi

	if [ "${LUCI_DO_SUBSTITUTE_VERSION}" = "1" ]; then
		find "${ACTIONS_DIR}" -type f -name '*.htm' | while read src; do
			sed -i -e "s/<%# *\([^ ]*\)PKG_VERSION *%>/\1${LUCI_PKG_VERSION}/g" \
			       -e "s/\"\(<%= *\(media\|resource\) *%>[^\"]*\.\(js\|css\|gif\|ico\|png\|jpg\|svg\)\)\"/\"\1?v=${LUCI_PKG_VERSION}\"/g" \
			          "$src"
		done
	fi

	if [ "${LUCI_DO_MINIFY_JS}" = "1" ]; then
		find "${ACTIONS_DIR}" -type f -name '*.js' | while read src; do
			if jsmin < "$src" > "$src.o"; then
				mv "$src.o" "$src"
			fi
		done
	fi

	if [ "${LUCI_DO_MINIFY_LUA}" = "1" ]; then
		for p in "${ACTIONS_DIR}"; do
			find "$p" -type f -name '*.lua' | while read src; do
				if LUA_PATH=${STAGING_DIR_NATIVE}/usr/lib/lua/5.1/?.lua luasrcdiet --noopt-binequiv -o "$src.o" "$src"; then
					mv "$src.o" "$src"
				fi
			done
		done
	fi
}

do_install[postfuncs] += "post_install_actions"
do_install[vardeps] += "\
	LUCI_PKG_VERSION \
	LUCI_DO_POST_INSTALL_ACTIONS \
	LUCI_DO_SUBSTITUTE_VERSION \
	LUCI_DO_MINIFY_JS \
	LUCI_DO_MINIFY_LUA \
	LUCI_DO_RM_LUADOC \
"

do_compile[vardeps] += "\
	LUCI_LANGUAGES \
	LUCI_INSTALL_LUASRC_PATH \
	LUCI_INSTALL_HTDOCS_PATH \
	LUCI_INSTALL_ROOT_PATH \
"

EXTRA_OECMAKE += "\
  -DCMAKE_INSTALL_PREFIX:PATH= \
  -DCMAKE_INSTALL_BINDIR:PATH=/usr/bin \
  -DCMAKE_INSTALL_SBINDIR:PATH=sbin \
  -DCMAKE_INSTALL_LIBEXECDIR:PATH=libexec \
  -DCMAKE_INSTALL_SYSCONFDIR:PATH=/etc \
  -DCMAKE_INSTALL_LOCALSTATEDIR:PATH=/var \
  -DCMAKE_INSTALL_LIBDIR:PATH=/usr/lib \
  -DCMAKE_INSTALL_INCLUDEDIR:PATH=/usr/include \
  -DCMAKE_INSTALL_DATAROOTDIR:PATH=/usr/share \
"

OECMAKE_C_FLAGS += "-DDESTDIR=${D}"

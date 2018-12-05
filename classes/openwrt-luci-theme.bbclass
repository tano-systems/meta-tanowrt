#
# LuCI theme class
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
inherit openwrt-luci-pkg

DEPENDS += "coreutils-native gawk-native"

SRC_URI_append = "\
	${LUCI_GIT_URI};branch=${LUCI_GIT_BRANCH};protocol=${LUCI_GIT_PROTOCOL};name=lucigit;destsuffix=lucigit \
"
SRCREV_lucigit = "${LUCI_GIT_SRCREV}"

LUCI_THEME_NAME ?= ""
LUCI_THEME_STATIC_PATH ?= "${D}${LUCI_INSTALL_HTDOCS_PATH}/luci-static/${LUCI_THEME_NAME}"
LUCI_THEME_VIEW_PATH   ?= "${D}${LUCI_INSTALL_LUASRC_PATH}/view/themes/${LUCI_THEME_NAME}"

LUCI_SRC = "${WORKDIR}/lucigit"
LUCI_SRC_RESOURCES = "${LUCI_SRC}/modules/luci-base/htdocs/luci-static/resources"

do_install_append() {

	if [ -z "${LUCI_THEME_NAME}" ]; then
		bbfatal "LUCI_THEME_NAME variable value must be set"
	fi

	MD5SUM_CBI_JS=`md5sum ${LUCI_SRC_RESOURCES}/cbi.js | awk '{ print $1 }'`
	MD5SUM_XHR_JS=`md5sum ${LUCI_SRC_RESOURCES}/xhr.js | awk '{ print $1 }'`

	find "${LUCI_THEME_VIEW_PATH}" -type f -name '*.htm' | while read src; do
		sed -i -e "s,\(<%=\s*resource\s*%>/cbi.js\)\s*\",\1?v=${MD5SUM_CBI_JS}\",g" \
		       -e "s,\(<%=\s*resource\s*%>/xhr.js\)\s*\",\1?v=${MD5SUM_XHR_JS}\",g" \
		       $src
	done

	find "${LUCI_THEME_STATIC_PATH}" -type f -name '*' -printf "%P\n" | while read static; do
		STATIC_REL_PATH="${static}"
		STATIC_ABS_PATH="${LUCI_THEME_STATIC_PATH}/${static}"
		STATIC_MD5=`md5sum ${STATIC_ABS_PATH} | awk '{ print $1 }'`

		find "${LUCI_THEME_VIEW_PATH}" -type f -name '*.htm' | while read src; do
			sed -i -e "s,\(<%=\s*media\s*%>/${STATIC_REL_PATH}\)\s*\",\1?v=${STATIC_MD5}\",g" \
			       $src
		done
	done
}

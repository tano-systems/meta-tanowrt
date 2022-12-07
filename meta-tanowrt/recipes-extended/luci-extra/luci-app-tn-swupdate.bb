#
# SPDX-License-Identifier: MIT
#
# swupdate LuCI application
#
# Copyright (c) 2021 Tano Systems LLC. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PV = "1.0.0+git${SRCPV}"
PR = "tano1"

PACKAGES = "${PN} cgi-swupdate cgi-swupdate-dbg"

PACKAGE_ARCH = "${TUNE_PKGARCH}"

RDEPENDS:${PN} += "cgi-swupdate"

SUMMARY = "SWUpdate LuCI support application"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=db0fccee3def547e17585ac94447d2fb"

SRC_URI = "git://github.com/tano-systems/luci-app-tn-swupdate.git;protocol=https;branch=master;name=app"
SRCREV_app = "25fff2d1440ae7c1fb368dfb5d5a8f4d488731b8"
SRCREV_FORMAT = "app"

S = "${WORKDIR}/git"

LUCI_PKG_EXECUTABLE += "${D}/usr/libexec/rpcd/*"

inherit cmake
inherit tanowrt-luci-app
inherit tanowrt-luci-i18n

FILES:${PN} = "\
	${sysconfdir} \
	/www/luci-static \
	/usr/share/rpcd \
	/usr/share/luci \
"

# Unset do_configure[noexec] and do_compile[noexec]
python __anonymous() {
    d.delVarFlag('do_configure', 'noexec')
    d.delVarFlag('do_compile', 'noexec')
}

DESCRIPTION:cgi-swupdate = "CGI swupdate helper utility"
SUMMARY:cgi-swupdate = "CGI swupdate helper"
SECTION:cgi-swupdate = "net"
RDEPENDS:cgi-swupdate += "swupdate"

DEPENDS += "libubox libubus swupdate"
OECMAKE_SOURCEPATH = "${S}/src"

EXTRA_OECMAKE += "\
	-DCMAKE_INSTALL_SBINDIR:PATH=/usr/libexec \
"

FILES:cgi-swupdate += "\
	/usr/libexec/cgi-swupdate \
	/www/cgi-bin/cgi-swupdate \
"

FILES:cgi-swupdate-dbg += "\
	/usr/libexec/.debug \
"

do_configure:prepend () {
	if [ -e "${OECMAKE_SOURCEPATH}/CMakeLists.txt" ] ; then
		sed -i -e "s:RUNTIME DESTINATION sbin:RUNTIME DESTINATION \${CMAKE_INSTALL_SBINDIR}:g" \
		       ${OECMAKE_SOURCEPATH}/CMakeLists.txt
	fi
}

do_install:append() {
	# Create symlink to cgi-swupdate from /www/cgi-bin directory
	install -dm 0755 ${D}/www/cgi-bin
	ln -s ../../usr/libexec/cgi-swupdate ${D}/www/cgi-bin/cgi-swupdate
}

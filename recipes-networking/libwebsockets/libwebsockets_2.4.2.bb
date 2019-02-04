#
PR = "tano2"
SUMMARY = "Canonical libwebsockets.org websocket library"
HOMEPAGE = "https://libwebsockets.org/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=b415f60bd65e057120037bb15cad7139"

DEPENDS = "zlib libcap"

PV = "2.4.2+git${SRCPV}"

S = "${WORKDIR}/git"

# 11.10.2018
SRCREV = "b6d1375c2d02835ca16f7c81e7ce4b48e7b2e27f"
SRC_URI = "git://github.com/warmcat/libwebsockets.git;protocol=https;branch=v2.4-stable"

inherit cmake pkgconfig

PACKAGECONFIG ?= "\
	client \
	server \
	ssl \
	${@bb.utils.filter('ipv6', '${DISTRO_FEATURES}', d)} \
"

PACKAGECONFIG[client]   = "-DLWS_WITHOUT_CLIENT=OFF,-DLWS_WITHOUT_CLIENT=ON,"
PACKAGECONFIG[ipv6]     = "-DLWS_IPV6=ON,-DLWS_IPV6=OFF,"
PACKAGECONFIG[libev]    = "-DLWS_WITH_LIBEV=ON,-DLWS_WITH_LIBEV=OFF,libev"
PACKAGECONFIG[libuv]    = "-DLWS_WITH_LIBUV=ON,-DLWS_WITH_LIBUV=OFF,libuv"
PACKAGECONFIG[server]   = "-DLWS_WITHOUT_SERVER=OFF,-DLWS_WITHOUT_SERVER=ON,"
PACKAGECONFIG[ssl]      = "-DLWS_WITH_SSL=ON -DLWS_OPENSSL_SUPPORT=ON -DLWS_OPENSSL_CLIENT_CERTS=/etc/ssl/certs,-DLWS_WITH_SSL=OFF,openssl"
PACKAGECONFIG[testapps] = "-DLWS_WITHOUT_TESTAPPS=OFF,-DLWS_WITHOUT_TESTAPPS=ON,"

PACKAGES =+ "${PN}-testapps"

FILES_${PN}-dev += "${libdir}/cmake"
FILES_${PN}-testapps += "${datadir}/libwebsockets-test-server/*"


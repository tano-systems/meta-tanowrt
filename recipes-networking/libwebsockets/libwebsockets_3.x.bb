#
PR = "tano1"
SUMMARY = "Canonical libwebsockets.org websocket library"
HOMEPAGE = "https://libwebsockets.org/"
LICENSE = "LGPL-2.1"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ce87f3facb6f911c142c8bef9bfb380"

DEPENDS = "zlib libcap"

PV = "3.0.1+git${SRCPV}"

S = "${WORKDIR}/git"

# 07.09.2018
SRCREV = "fb31602ff9aeb88267fb8132d48df31195782ae5"
SRC_URI = "git://github.com/warmcat/libwebsockets.git;protocol=https;branch=v3.0-stable"

inherit cmake pkgconfig

PACKAGECONFIG ?= "\
	client \
	server \
	ssl \
	${@oe_filter('ipv6', '${DISTRO_FEATURES}', d)} \
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


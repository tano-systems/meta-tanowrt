#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#
PR = "tano0"
SUMMARY = "Canonical libwebsockets.org websocket library"
HOMEPAGE = "https://libwebsockets.org/"
LICENSE = "LGPL-2.1-only"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4ce87f3facb6f911c142c8bef9bfb380"

DEPENDS = "zlib libcap"

PV = "3.2.3+git${SRCPV}"

S = "${WORKDIR}/git"

# 06.05.2020
SRCREV = "c744c0934d69912e1cc50ff23d203ad60d535709"
SRC_URI = "git://github.com/warmcat/libwebsockets.git;protocol=https;branch=v3.2-stable"

inherit cmake pkgconfig

PACKAGECONFIG ?= "\
	client \
	server \
	ssl \
	libuv \
	plugins \
	server-status \
	access-log \
	cgi \
	unix-sock \
	${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)} \
"

PACKAGECONFIG[client]         = "-DLWS_WITHOUT_CLIENT=OFF,-DLWS_WITHOUT_CLIENT=ON,"
PACKAGECONFIG[ipv6]           = "-DLWS_IPV6=ON,-DLWS_IPV6=OFF,"
PACKAGECONFIG[libev]          = "-DLWS_WITH_LIBEV=ON,-DLWS_WITH_LIBEV=OFF,libev"
PACKAGECONFIG[libuv]          = "-DLWS_WITH_LIBUV=ON,-DLWS_WITH_LIBUV=OFF,libuv"
PACKAGECONFIG[server]         = "-DLWS_WITHOUT_SERVER=OFF,-DLWS_WITHOUT_SERVER=ON,"
PACKAGECONFIG[ssl]            = "-DLWS_WITH_SSL=ON -DLWS_OPENSSL_SUPPORT=ON -DLWS_OPENSSL_CLIENT_CERTS=/etc/ssl/certs,-DLWS_WITH_SSL=OFF,openssl"
PACKAGECONFIG[testapps]       = "-DLWS_WITHOUT_TESTAPPS=OFF,-DLWS_WITHOUT_TESTAPPS=ON,"
PACKAGECONFIG[plugins]        = "-DLWS_WITH_PLUGINS=ON,-DLWS_WITH_PLUGINS=OFF,"
PACKAGECONFIG[server-status]  = "-DLWS_WITH_SERVER_STATUS=ON,-DLWS_WITH_SERVER_STATUS=OFF,"
PACKAGECONFIG[access-log]     = "-DLWS_WITH_ACCESS_LOG=ON,-DLWS_WITH_ACCESS_LOG=OFF,"
PACKAGECONFIG[cgi]            = "-DLWS_WITH_CGI=ON,-DLWS_WITH_CGI=OFF,"
PACKAGECONFIG[unix-sock]      = "-DLWS_UNIX_SOCK=ON,-DLWS_UNIX_SOCK=OFF,"

PACKAGES =+ "${PN}-testapps"

FILES:${PN}-dev += "${libdir}/cmake"
FILES:${PN}-testapps += "${datadir}/libwebsockets-test-server/*"


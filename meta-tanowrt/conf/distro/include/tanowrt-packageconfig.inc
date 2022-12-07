#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#

PACKAGECONFIG:pn-libwebsockets = "\
	client \
	server \
	ssl \
	libuv \
	${@bb.utils.filter('DISTRO_FEATURES', 'ipv6', d)} \
"

# Remove ICU dependency for xerces-c
PACKAGECONFIG:remove:pn-xerces-c = "icu"

# Prefer OpenSSL over GnuTLS for curl due to TSU exemptions
PACKAGECONFIG:pn-curl = "${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'ipv6', '', d)} ssl zlib"

PACKAGECONFIG:pn-nano = "tiny"

# Enable UI and scripting for perf
PERF_FEATURES_ENABLE ?= "perf-scripting perf-tui"

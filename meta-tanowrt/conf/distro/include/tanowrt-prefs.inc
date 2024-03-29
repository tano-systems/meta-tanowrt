#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#

# Set preferred Qt5 versions. Different namespace from Qt4, so no conflict
include conf/distro/include/qt5-versions.inc

# Versions
PREFERRED_VERSION_iptables = "1.8.7"
PREFERRED_VERSION_usbutils = "007"
PREFERRED_VERSION_libwebsockets = "3.2%"
PREFERRED_VERSION_qrencode = "4.1.1"
PREFERRED_VERSION_rrdtool = "1.0.50"
PREFERRED_VERSION_collectd = "5.12.0"
PREFERRED_VERSION_ppp = "2.4.8"
PREFERRED_VERSION_rpm = "4.16.1.3"

PREFERRED_VERSION_linux-backports = "5.10%"

# Providers
PREFERRED_PROVIDER_psplash = "psplash-tn"
PREFERRED_PROVIDER_os-release = "tano-version"
PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"
PREFERRED_PROVIDER_lua = "lua5.1"
PREFERRED_PROVIDER_lua-native = "lua5.1-native"

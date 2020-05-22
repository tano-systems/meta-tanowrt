# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR = "tano3"
SUMMARY = "Normal TanoWrt system requirements"
DESCRIPTION = "The set of packages required for a more traditional full-featured Openwrt system"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup tanowrt

PACKAGES = "\
	packagegroup-tanowrt-base \
	packagegroup-tanowrt-base-luci \
	packagegroup-tanowrt-base-machine \
"

# packagegroup-tanowrt-base
RDEPENDS_${PN} = "\
	packagegroup-tanowrt-noweb-base \
	packagegroup-tanowrt-base-luci \
	packagegroup-tanowrt-base-machine \
"

SUMMARY_packagegroup-tanowrt-base-machine = "${MACHINE} extras"
SUMMARY_packagegroup-tanowrt-base-machine = "Extra packages required to fully support ${MACHINE} hardware"
RDEPENDS_packagegroup-tanowrt-base-machine = "${MACHINE_EXTRA_RDEPENDS}"
RRECOMMENDS_packagegroup-tanowrt-base-machine = "${MACHINE_EXTRA_RRECOMMENDS}"

# packagegroup-tanowrt-base-luci
RDEPENDS_${PN}-luci = "\
	lua5.1 \
	luci \
	luci-theme-tano \
	luci-app-firewall \
	uhttpd \
"

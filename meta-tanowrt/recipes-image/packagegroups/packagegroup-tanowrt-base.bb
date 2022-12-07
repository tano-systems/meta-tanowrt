#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano13"
SUMMARY = "Base TanoWrt system requirements"
DESCRIPTION = "The set of packages required for a more traditional full-featured TanoWrt system"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

do_package[vardeps] += "TANOWRT_LUCI_ENABLE"

PROVIDES = "${PACKAGES}"
PACKAGES = "\
	packagegroup-tanowrt-base \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'packagegroup-tanowrt-base-luci', '', d)} \
	packagegroup-tanowrt-base-alsa \
	packagegroup-tanowrt-base-core \
	packagegroup-tanowrt-base-tools \
	packagegroup-tanowrt-base-network \
	packagegroup-tanowrt-base-machine \
	packagegroup-tanowrt-base-distro \
"

# packagegroup-tanowrt-base
RDEPENDS:${PN} = "\
	packagegroup-tanowrt-base-core \
	packagegroup-tanowrt-base-network \
	packagegroup-tanowrt-base-tools \
	packagegroup-tanowrt-base-machine \
	packagegroup-tanowrt-base-distro \
	${@bb.utils.contains('MACHINE_FEATURES', 'alsa', 'packagegroup-tanowrt-base-alsa', '', d)} \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'packagegroup-tanowrt-base-luci ', '', d)} \
	make-ext4fs \
	eudev \
	mtd-utils \
	${@bb.utils.contains('MACHINE_FEATURES', 'ubifs', 'mtd-utils-ubifs', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'usbhost', 'libusb1', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'usbhost', 'usbutils', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'usbhost', 'usbreset', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'usbhost', 'usbmode', '',d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'usbgadget', 'usb-gadget-network', '',d)} \
"

# packagegroup-tanowrt-base-alsa
RDEPENDS:${PN}-alsa = "\
	alsa-utils-alsactl \
	alsa-utils-alsamixer \
	alsa-utils-aplay \
	${VIRTUAL-RUNTIME_alsa-state} \
"

RRECOMMENDS:${PN}-alsa = "\
	kernel-module-snd-mixer-oss \
	kernel-module-snd-pcm-oss \
"

# packagegroup-tanowrt-base-luci
RDEPENDS:${PN}-luci = "\
	lua5.1 \
	uhttpd \
	luci \
	luci-app-firewall \
	luci-app-uhttpd \
	luci-app-tn-netports \
	luci-app-tn-netports-hotplug \
	luci-app-tn-logview \
	luci-app-tn-logview-plugin-cron \
	luci-theme-tano \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'luci-proto-ipv6', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'usbhost', 'luci-app-ledtrig-usbport', '',d)} \
"

# packagegroup-tanowrt-base-tools
RDEPENDS:${PN}-tools = "\
	nano \
	cpulimit \
	schedtool-dl \
	picocom \
	${@bb.utils.contains('MACHINE_FEATURES', 'pci', 'pciutils', '',d)} \
"

# packagegroup-tanowrt-base-core
RDEPENDS:${PN}-core = "\
	packagegroup-core-boot \
	tzdata-europe \
	tzdata-asia \
	urandom-seed \
	${@bb.utils.contains('MACHINE_FEATURES', 'hwrng', 'rng-tools', 'haveged',d)} \
	rpcd \
	rpcd-mod-file \
	ubox \
	ubus \
	uci \
	libubox \
	libubox-lua \
	fstools \
	cronie \
	logrotate \
	${VIRTUAL-RUNTIME_syslog} \
"

# packagegroup-tanowrt-base-network
RDEPENDS:${PN}-network = "\
	${VIRTUAL-RUNTIME_network_manager} \
	dnsmasq \
	firewall3 \
	iptables \
	uclient \
	ustream-ssl \
	odhcpd \
	${@bb.utils.contains('DISTRO_FEATURES', 'ipv6', 'odhcp6c', '', d)} \
	ipset \
	tcpdump \
	ethtool \
	curl \
	drill \
	iproute2 \
	arp-scan \
"

#
# packages added by machine
#
SUMMARY:packagegroup-tanowrt-base-machine = "${MACHINE} extras"
SUMMARY:packagegroup-tanowrt-base-machine = "Extra packages required to fully support ${MACHINE} hardware"
RDEPENDS:packagegroup-tanowrt-base-machine = "${MACHINE_EXTRA_RDEPENDS}"
RRECOMMENDS:packagegroup-tanowrt-base-machine = "${MACHINE_EXTRA_RRECOMMENDS}"

#
# packages added by distribution
#
SUMMARY:packagegroup-tanowrt-base-distro = "${DISTRO} extras"
DEPENDS:packagegroup-tanowrt-base-distro = "${DISTRO_EXTRA_DEPENDS}"
RDEPENDS:packagegroup-tanowrt-base-distro = "${DISTRO_EXTRA_RDEPENDS}"
RRECOMMENDS:packagegroup-tanowrt-base-distro = "${DISTRO_EXTRA_RRECOMMENDS}"

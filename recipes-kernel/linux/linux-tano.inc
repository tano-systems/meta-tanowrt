#
# Copyright (C) 2019, Tano Systems, All rights reserved
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

python __anonymous() {
    if not d.getVar("KERNEL_SRC_URI") or not d.getVar("KERNEL_SRC_BRANCH") or not d.getVar("KERNEL_SRC_SRCREV"):
        bb.error("You need to set properly values for KERNEL_SRC_URI, KERNEL_SRC_BRANCH and KERNEL_SRC_SRCREV")

    if not d.getVar("KERNEL_CACHE_SRC_BRANCH") or not d.getVar("KERNEL_CACHE_SRC_SRCREV"):
        bb.error("You need to set properly values for KERNEL_CACHE_SRC_BRANCH and KERNEL_CACHE_SRC_SRCREV")
}

# LEVELS:
#   0: no reporting
#   1: report options that are specified, but not in the final config
#   2: report options that are not hardware related, but set by a BSP
KCONF_AUDIT_LEVEL ?= "1"
KCONF_BSP_AUDIT_LEVEL ?= "0"
KMETA_AUDIT ?= "yes"

KBRANCH ?= "${KERNEL_SRC_BRANCH}"
KMACHINE ?= "${MACHINE}"

KERNEL_SRC_PROTOCOL ?= "git"

FILESEXTRAPATHS_prepend := "${THISDIR}:"
SRC_URI += "\
	${KERNEL_SRC_URI};name=machine;protocol=${KERNEL_SRC_PROTOCOL};branch=${KERNEL_SRC_BRANCH} \
"

# kmeta
KERNEL_CACHE_ENABLE ?= "1"
KERNEL_CACHE_SRC_URI ?= "git://github.com/tano-systems/tano-kernel-cache.git"
KERNEL_CACHE_SRC_PROTOCOL ?= "git"

#
# Override all other kmeta data by specifying name starting with 'zz-' (destsuffix).
# All include paths will be sorted by spp with 'sort' tool.
#
SRC_URI += "\
	${KERNEL_CACHE_SRC_URI};type=kmeta;name=metatano;protocol=${KERNEL_CACHE_SRC_PROTOCOL};branch=${KERNEL_CACHE_SRC_BRANCH};destsuffix=zz-kernel-cache-tano \
"

SRCREV_machine = "${KERNEL_SRC_SRCREV}"
SRCREV_metatano = "${KERNEL_CACHE_SRC_SRCREV}"

SRCREV_FORMAT = "machine_metatano"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION_EXTENSION ??= "-tano-${LINUX_KERNEL_TYPE}"

KERNEL_FEATURES_append = " \
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/usb-net/usb-net.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/usb-serial/usb-serial.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "cgroup", " features/cgroups/cgroups.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "wifi usbhost", " features/wifi/wifi-usb.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "wifi pci", " features/wifi/wifi-pci.scc", "" ,d)} \
	features/net_sched/net_sched.scc \
	features/netfilter/netfilter.scc \
	features/nf_tables/nf_tables.scc \
	features/ebtables/ebtables.scc \
	features/net/routing.scc \
	features/net/l2tp.scc \
	features/net/ipset.scc \
	features/net/atm.scc \
	features/net/ifb.scc \
	features/net/ppp.scc \
	features/net/tun.scc \
	features/gre/gre-enable.scc \
"

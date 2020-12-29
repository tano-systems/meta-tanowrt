#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".0"

DEPENDS += "${@bb.utils.contains('ARCH', 'x86', 'elfutils-native', '', d)}"
DEPENDS += "openssl-native util-linux-native"

COMPATIBLE_MACHINE = "(qemux86|qemux86-64|qemuarm|qemuarm64)"
KERNEL_DEVICETREE_qemuarm ?= "versatile-pb.dtb"

# Functionality flags
KERNEL_EXTRA_FEATURES ?= "features/netfilter/netfilter.scc features/taskstats/taskstats.scc"
KERNEL_FEATURES_append = " ${KERNEL_EXTRA_FEATURES}"
KERNEL_FEATURES_append_qemuall = " cfg/virtio.scc"
KERNEL_FEATURES_append_qemux86 = " cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append_qemux86-64 = " cfg/sound.scc cfg/paravirt_kvm.scc"
KERNEL_FEATURES_append = " ${@bb.utils.contains('TUNE_FEATURES', 'mx32', ' cfg/x32.scc', '' ,d)}"

SRC_URI_append = " git://git.yoctoproject.org/yocto-kernel-cache;type=kmeta;name=metayocto;branch=${YOCTO_KERNEL_CACHE_BRANCH};destsuffix=10-kernel-cache-yocto "
SRCREV_metayocto ?= "${YOCTO_KERNEL_CACHE_SRCREV}"

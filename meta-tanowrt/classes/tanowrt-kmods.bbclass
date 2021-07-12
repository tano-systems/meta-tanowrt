#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2020-2021 Anton Kikin <a.kikin@tano-systems.com>
#

ROOTFS_POSTUNINSTALL_COMMAND_append = ' rootfs_flatten_modules_hook; '

KERNEL_VERSION="${@oe.utils.read_file('${STAGING_KERNEL_BUILDDIR}/kernel-abiversion')}"

MODULES_FLATTEN_PATHS ?= "\
	backports \
	updates \
	extra \
	kernel \
"

rootfs_flatten_modules_hook() {
    [ -d "${IMAGE_ROOTFS}/lib/modules" ] || return

    KERNEL_DIR="${KERNEL_VERSION}"

    if [ -n "${KERNEL_DIR}" ] && [ ! -d "${IMAGE_ROOTFS}/lib/modules/${KERNEL_DIR}" ]; then
        bbwarn "Could not find /lib/modules/${KERNEL_DIR} directory at IMAGE_ROOTFS"
        KERNEL_DIR="$(cd ${IMAGE_ROOTFS}/lib/modules && ls -1 | head -1)"
        [ -n "${KERNEL_DIR}" ] && bbwarn "Using /lib/modules/${KERNEL_DIR} instead"
    fi

    if [ -z "${KERNEL_DIR}" ]; then
        bbfatal "Can not detect kernel directory at /lib/modules"
    fi

    export KERNEL_DIR
    bbdebug 1 "Using /lib/modules/${KERNEL_DIR} for flattening modules tree"

    for p in ${MODULES_FLATTEN_PATHS}; do
        if [ -d "${IMAGE_ROOTFS}/lib/modules/${KERNEL_DIR}/$p" ]; then
            cd ${IMAGE_ROOTFS} && find lib/modules/${KERNEL_DIR}/$p -name '*.ko' -exec sh -c \
                'mod="{}"; \
                 target=${IMAGE_ROOTFS}/lib/modules/${KERNEL_DIR}/$(basename "$mod"); \
                 if [ ! -e "$target" ] || [ -L "$target" ]; then \
                     rm -f $target; \
                     mv -f ${IMAGE_ROOTFS}/$mod $target; \
                 fi' \
            \;

            rm -rf ${IMAGE_ROOTFS}/lib/modules/${KERNEL_DIR}/$p
        fi
    done
}

#
# Copyright (c) 2018 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
# Move modules autoloading config files from /etc/modules-load.d
# to /etc/modules.d as needed for OpenWrt kmodloader
# and remove /etc/modules-load.d folder from rootfs
#

ROOTFS_POSTPROCESS_COMMAND += "openwrt_rootfs_kmodloader; "

openwrt_rootfs_kmodloader() {
	if [ -d ${IMAGE_ROOTFS}/etc/modules-load.d ]
	then
		install -dm 0755 ${IMAGE_ROOTFS}/etc/modules.d

		mv ${IMAGE_ROOTFS}/etc/modules-load.d/* ${IMAGE_ROOTFS}/etc/modules.d
		bbnote "All files from /etc/modules-load.d moved to /etc/modules.d"

		rm -rf ${IMAGE_ROOTFS}/etc/modules-load.d
		bbnote "Removed /etc/modules-load.d from root filesystem"
	fi
}

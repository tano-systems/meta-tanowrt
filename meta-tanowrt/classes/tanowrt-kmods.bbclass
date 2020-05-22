#/ This file Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#
# It is released under the MIT license.  See COPYING.MIT
# for the terms.

ROOTFS_POSTUNINSTALL_COMMAND_append = ' openwrt_flatten_modules_hook; '

openwrt_flatten_modules_hook () {
    export KERNEL_VERSION="${@oe.utils.read_file('${STAGING_KERNEL_BUILDDIR}/kernel-abiversion')}"
    if [ -d "${IMAGE_ROOTFS}/lib/modules/${KERNEL_VERSION}" ]; then
        cd ${IMAGE_ROOTFS} && find lib/modules/${KERNEL_VERSION} -name '*.ko' -exec sh -c 'mod="{}"; ln -sf /$mod ${IMAGE_ROOTFS}/lib/modules/${KERNEL_VERSION}/$(basename "$mod")' \;
    fi
}

#
# Copyright (c) 2018, Tano Systems
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

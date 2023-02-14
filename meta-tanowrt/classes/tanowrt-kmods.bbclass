#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#           Copyright (C) 2020-2022 Anton Kikin <a.kikin@tano-systems.com>
#

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

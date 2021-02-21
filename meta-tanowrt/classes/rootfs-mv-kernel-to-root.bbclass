#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
# Move kernel files from /boot to /
#
ROOTFS_POSTPROCESS_COMMAND_prepend = "rootfs_mv_kernel_to_root; "

rootfs_mv_kernel_to_root() {
	if [ -d ${IMAGE_ROOTFS}/boot ]; then
		if [ -e "${IMAGE_ROOTFS}/boot/${KERNEL_IMAGETYPE}" ]; then
			mv "${IMAGE_ROOTFS}/boot/${KERNEL_IMAGETYPE}"* "${IMAGE_ROOTFS}/"
			bbnote "Moved kernel ${KERNEL_IMAGETYPE}* from /boot to /"
		fi
	fi
}

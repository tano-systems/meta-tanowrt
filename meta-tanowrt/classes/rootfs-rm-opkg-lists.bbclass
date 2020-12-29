#
# SPDX-License-Identifier: MIT
#
# Remove from rootfs files from /usr/lib/opkg/lists/
#
ROOTFS_POSTPROCESS_COMMAND += "rootfs_rm_opkg_lists; "

rootfs_rm_opkg_lists() {
	if [ -d ${IMAGE_ROOTFS}/usr/lib/opkg/lists ]; then
		rm -rf ${IMAGE_ROOTFS}/usr/lib/opkg/lists/*
	fi
}

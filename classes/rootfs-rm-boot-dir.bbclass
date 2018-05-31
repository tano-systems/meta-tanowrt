#
# Remove from rootfs /boot folder
#
ROOTFS_POSTPROCESS_COMMAND += "rootfs_rm_boot_dir; "

rootfs_rm_boot_dir() {
	if [ -d ${IMAGE_ROOTFS}/boot ]
	then
		rm -rf ${IMAGE_ROOTFS}/boot
		bbnote "Removed /boot from root filesystem"
	fi
}

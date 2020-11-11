#
ROOTFS_POSTPROCESS_COMMAND += "rootfs_misc_fixups; "

rootfs_misc_fixups() {
	rm -f ${IMAGE_ROOTFS}/${sysconfdir}/passwd-opkg
	rm -f ${IMAGE_ROOTFS}/${sysconfdir}/group-opkg
}

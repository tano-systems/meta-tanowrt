#
# Make /var in tmpfs
#
IMAGE_PREPROCESS_COMMAND += "rootfs_var_in_tmpfs; "

rootfs_var_in_tmpfs() {
	if [ -d ${IMAGE_ROOTFS}/var ]
	then
		# Remove /var and link /var to /tmp
		rm -rf ${IMAGE_ROOTFS}/var
		ln -s /tmp ${IMAGE_ROOTFS}/var
		bbnote "Removed /var directory and linked /var to /tmp"

		rm -rf ${IMAGE_ROOTFS}/run
		ln -s /var/run ${IMAGE_ROOTFS}/run
		bbnote "Linked /run to /var/run"
	fi
}

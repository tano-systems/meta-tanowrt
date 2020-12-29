#
# SPDX-License-Identifier: MIT
#
# Make symlink /var/lib/opkg -> /usr/lib/opkg
# It's only build time fix
#
ROOTFS_PREPROCESS_COMMAND += "rootfs_var_lib_opkg_symlink; "

rootfs_var_lib_opkg_symlink() {
	install -d ${IMAGE_ROOTFS}/var/lib
	rm -rf ${IMAGE_ROOTFS}/var/lib/opkg
	ln -s ../../usr/lib/opkg ${IMAGE_ROOTFS}/var/lib/opkg
}

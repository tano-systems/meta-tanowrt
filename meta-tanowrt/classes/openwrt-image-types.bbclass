#
# Copyright (C) 2018, Anton Kikin <a.kikin@tano-systems.com>
# Add support for squashfs root filesystem
#
# From poky distribution
#
inherit image_types

IMAGE_CMD_squashfs = "mksquashfs ${IMAGE_ROOTFS} ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.squashfs ${EXTRA_IMAGECMD} -noappend"
IMAGE_CMD_squashfs-xz = "mksquashfs ${IMAGE_ROOTFS} ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.squashfs-xz ${EXTRA_IMAGECMD} -noappend -comp xz"
IMAGE_CMD_squashfs-lzo = "mksquashfs ${IMAGE_ROOTFS} ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.squashfs-lzo ${EXTRA_IMAGECMD} -noappend -comp lzo"
IMAGE_CMD_squashfs-lz4 = "mksquashfs ${IMAGE_ROOTFS} ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.squashfs-lz4 ${EXTRA_IMAGECMD} -noappend -comp lz4"

do_image_squashfs[depends] += "squashfs-tools-native:do_populate_sysroot"
do_image_squashfs_xz[depends] += "squashfs-tools-native:do_populate_sysroot"
do_image_squashfs_lzo[depends] += "squashfs-tools-native:do_populate_sysroot"
do_image_squashfs_lz4[depends] += "squashfs-tools-native:do_populate_sysroot"

IMAGE_TYPES += "squashfs squashfs-xz squashfs-lzo squashfs-lz4"

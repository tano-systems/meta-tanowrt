#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

IMAGE_CLASSES = ""

inherit core-image

SWU_FACTORY_WKS_OUTPUT_EXT ?= "sdcard.img"

IMAGE_CMD_wic_append () {
	if [ -n "${SWU_FACTORY_WKS_OUTPUT_EXT}" ]; then
		mv "$out${IMAGE_NAME_SUFFIX}.wic" \
		   "$out${IMAGE_NAME_SUFFIX}.${SWU_FACTORY_WKS_OUTPUT_EXT}"
		ln -fs "${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${SWU_FACTORY_WKS_OUTPUT_EXT}" \
		       "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.${SWU_FACTORY_WKS_OUTPUT_EXT}"
	fi
}

IMAGE_FSTYPES = "wic"
IMAGE_INSTALL = ""
IMAGE_FEATURES = ""
EXTRA_IMAGE_FEATURES = ""
IMAGE_OVERHEAD_FACTOR = "1.0"
IMAGE_ROOTFS_SIZE = "8192"
IMAGE_ROOTFS_EXTRA_SPACE = "0"
IMAGE_NAME_SUFFIX = ""

IMAGE_PREPROCESS_COMMAND_remove_libc-glibc = "prelink_setup; prelink_image;"

python __anonymous () {
    if not d.getVar('SWU_FACTORY_WKS_FILE', True):
        bb.fatal('SWU_FACTORY_WKS_FILE have to be set')
}

# WIC
WKS_FILE = "${SWU_FACTORY_WKS_FILE}"
WIC_CREATE_EXTRA_ARGS = "--no-fstab-update"
WICVARS += "SWU_FACTORY_FILES"

SWU_FACTORY_WKS_FILE_DEPENDS ?= "\
	e2fsprogs-native \
	bmap-tools-native \
	dosfstools-native \
	mtools-native \
	squashfs-tools-native \
	cdrtools-native \
"

WKS_FILE_DEPENDS = "${SWU_FACTORY_WKS_FILE_DEPENDS}"

do_image_wic[depends] = "${@' '.join('%s-native:do_populate_sysroot' % r for r in ('parted', 'gptfdisk', 'dosfstools', 'mtools'))}"
do_image_wic[depends] += "${SWU_FACTORY_DEPENDS}"

# No need rootfs for factory installation image
fakeroot python do_rootfs () {
    path = d.getVar("IMAGE_ROOTFS", True)
    if not os.path.exists(path):
        os.makedirs(path)
}

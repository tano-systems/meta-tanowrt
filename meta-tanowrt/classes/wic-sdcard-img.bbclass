#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
# A simple class to rename the '.wic' extension to '.sdcard.img' for WIC images
#
IMAGE_CMD:wic:append () {
	mv "$out${IMAGE_NAME_SUFFIX}.wic" "$out${IMAGE_NAME_SUFFIX}.sdcard.img"
	ln -fs "${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.sdcard.img" "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.sdcard.img"
}

#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit features_check
REQUIRED_MACHINE_FEATURES = "swupdate swupdate-factory"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SWU_FACTORY_DESTINATION_NAME ?= "swu_install"
SWU_FACTORY_DESTINATION_NAME[doc] = "SWU image filename (without extension) on the installation media"

# SWU image recipe (for example, tanowrt-image-full-swu)
SWU_FACTORY_IMAGE ?= '${@PN.replace("-factory", "")}'
SWU_FACTORY_IMAGE_RECIPE ?= "${SWU_FACTORY_IMAGE}"

SWU_FACTORY_INITRAMFS_IMAGE ?= "${INITRAMFS_IMAGE}"
SWU_FACTORY_INITRAMFS_IMAGE_NAME ?= "${INITRAMFS_IMAGE_NAME}"

SWU_FACTORY_EXTRA_FILES ?= ""
SWU_FACTORY_FILES += "\
	${SWU_FACTORY_IMAGE}-${MACHINE}.swu;${SWU_FACTORY_DESTINATION_NAME}.swu \
	${SWU_FACTORY_EXTRA_FILES} \
"

SWU_FACTORY_EXTRA_DEPENDS ?= ""
SWU_FACTORY_DEPENDS ?= "\
	${SWU_FACTORY_IMAGE_RECIPE}:do_swuimage \
	${SWU_FACTORY_INITRAMFS_IMAGE}:do_image_complete \
	${SWU_FACTORY_EXTRA_DEPENDS} \
"

SWU_FACTORY_CLASSES ?= "tanowrt-image-swu-factory-wic"

python __anonymous () {
    if not d.getVar('SWU_FACTORY_DEPENDS', True):
        bb.fatal('SWU_FACTORY_DEPENDS have to be set')

    if not d.getVar('SWU_FACTORY_FILES', True):
        bb.fatal('SWU_FACTORY_FILES have to be set')
}

inherit ${SWU_FACTORY_CLASSES}

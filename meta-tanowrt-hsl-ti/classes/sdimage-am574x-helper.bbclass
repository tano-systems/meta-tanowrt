#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
def parse_dtbs(d):
    import re
    kdt=re.sub(r'\b[\w-]+\/', '', d.getVar('KERNEL_DEVICETREE', ''))
    dtbs=""
    dtbcount=1
    for DTB in kdt.split():
        if dtbcount == 1:
            dtbs += DTB+";oftree"
        dtbs += " "+DTB
        dtbcount += 1
    return dtbs

do_image_wic[depends] += "\
    dosfstools-native:do_populate_sysroot \
    mtools-native:do_populate_sysroot \
    virtual/kernel:do_deploy \
    virtual/bootloader:do_deploy \
"

IMAGE_CMD_wic_append () {
	mv "$out${IMAGE_NAME_SUFFIX}.wic" "$out${IMAGE_NAME_SUFFIX}.sdcard.img"
	ln -fs "${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.sdcard.img" "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.sdcard.img"
}

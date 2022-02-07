#
# SPDX-License-Identifier: MIT
# Copyright (c) 2022 Tano Systems LLC. All rights reserved.
#

#
# Machine/distro features
#
MACHINE_FEATURES:append = "\
	swupdate \
"

SWU_BOOTLOADER = "grub-efi"
SWU_OVERLAY_CONTAINER = "blkdev"
SWU_OVERLAY_FS = "ext4"

SWU_IMAGE_MEDIA_TYPE = "HDD"

SWU_IMAGE_EXTRA_ARTIFACTS += "bootloader"

SWU_IMAGE_ARTIFACT_rootfs[sw-desc-type] = "raw"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-a] = "/dev/swu-system-a"
SWU_IMAGE_ARTIFACT_rootfs[sw-desc-device-b] = "/dev/swu-system-b"

SWU_IMAGE_ARTIFACT_bootloader[name] = "grub"
SWU_IMAGE_ARTIFACT_bootloader[fname] = "grub-efi-${@os.path.splitext(d.getVar('EFI_BOOT_IMAGE', True))[0]}"
SWU_IMAGE_ARTIFACT_bootloader[ftype] = "efi"
SWU_IMAGE_ARTIFACT_bootloader[depends] = "grub-efi:do_deploy"
SWU_IMAGE_ARTIFACT_bootloader[verextract] = "1"
SWU_IMAGE_ARTIFACT_bootloader[verextract-cmd] = "strings"
SWU_IMAGE_ARTIFACT_bootloader[verextract-filter-cmd] = "sed -n '/GNU GRUB  version %s/{n;p;}'"
SWU_IMAGE_ARTIFACT_bootloader[sw-desc-type] = "rawfile"
SWU_IMAGE_ARTIFACT_bootloader[sw-desc-path] = "${EFI_FILES_PATH}/${EFI_BOOT_IMAGE}"

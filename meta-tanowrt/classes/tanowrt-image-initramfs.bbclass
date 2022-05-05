#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

# Do not generate locales
IMAGE_LINGUAS = ""
GLIBC_GENERATE_LOCALES = ""
ENABLE_BINARY_LOCALE_GENERATION = "0"

IMAGE_ROOTFS_EXTRA_SPACE = "0"
ROOTFS_RM_BOOT_DIR_DISABLE = "0"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

inherit tanowrt-image

# Disable runtime dependency on run-postinsts
ROOTFS_BOOTSTRAP_INSTALL = ""

TANOWRT_IMAGE_INITRAMFS_KEEP_FEATURES ?= ""

TANOWRT_IMAGE_INITRAMFS_FAILSAFE ?= "0"
TANOWRT_IMAGE_INITRAMFS_FAILSAFE_WAIT ?= "0"
TANOWRT_IMAGE_INITRAMFS_KEEP_IMAGE_FREATURES ?= ""
TANOWRT_IMAGE_INITRAMFS_BAD_RECOMMENDATIONS ?= "\
	kernel-modules \
	parted \
	tzdata \
	e2fsprogs-resize2fs \
	udev eudev \
"

TANOWRT_IMAGE_INITRAMFS_EXTRA_INSTALL ?= ""

TANOWRT_IMAGE_INITRAMFS_INSTALL ?= "\
	packagegroup-core-boot \
	ubox \
	ubus \
	uci \
	libubox \
	fstools \
"

#
# Cleanup all image features except listed in
# TANOWRT_IMAGE_INITRAMFS_KEEP_FEATURES
#
python __anonymous () {
    image_features = d.getVar('IMAGE_FEATURES', True)
    keep = d.getVar('TANOWRT_IMAGE_INITRAMFS_KEEP_FEATURES', True)

    if not image_features:
        res = ''
    else:
        val = set(image_features.split())
        if isinstance(keep, str):
            checkvalues = set(keep.split())
        else:
            checkvalues = set(keep)
        res = ' '.join(sorted(checkvalues & val))

    d.setVar('IMAGE_FEATURES', res);
}

EXTRA_IMAGE_FEATURES = ""

BAD_RECOMMENDATIONS += "${TANOWRT_IMAGE_INITRAMFS_BAD_RECOMMENDATIONS}"

# Packages added by machine
# Install MACHINE_EXTRA_RDEPENDS and ignore MACHINE_EXTRA_RRECOMMENDS
RDEPENDS_${PN} += "${MACHINE_EXTRA_RDEPENDS}"

# Packages added by distribution
# Install DISTRO_EXTRA_RDEPENDS and ignore DISTRO_EXTRA_RRECOMMENDS
RDEPENDS_${PN} += "${DISTRO_EXTRA_RDEPENDS}"

ROOTFS_POSTPROCESS_COMMAND += "rootfs_initramfs_fixups; "

rootfs_initramfs_fixups() {
	if [ "${TANOWRT_IMAGE_INITRAMFS_FAILSAFE_WAIT}" = "0" ]; then
		rm -f ${IMAGE_ROOTFS}${TANOWRT_PATH_PREINIT}/30_failsafe_wait
	else
		if [ "${TANOWRT_IMAGE_INITRAMFS_FAILSAFE}" = "0" ]; then
			# Disable failsafe mode (set pi_preinit_no_failsafe="y")
			sed -i -e "s/pi_preinit_no_failsafe=.*/pi_preinit_no_failsafe=\"y\"/" \
				${IMAGE_ROOTFS}${TANOWRT_PATH_PREINIT}/00_preinit.conf
		fi
	fi

	# Remove kernel from rootfs
	if [ -f "${IMAGE_ROOTFS}/${KERNEL_IMAGETYPE}" ]; then
		rm -f ${IMAGE_ROOTFS}/${KERNEL_IMAGETYPE}
	fi

	# Replace default init by special init for initramfs
	rm -f ${IMAGE_ROOTFS}/init
	install -m 0755 ${TANOWRT_FILES}/initramfs/init ${IMAGE_ROOTFS}/init
}

IMAGE_INSTALL = "\
	${TANOWRT_IMAGE_INITRAMFS_INSTALL} \
	${TANOWRT_IMAGE_INITRAMFS_EXTRA_INSTALL} \
"

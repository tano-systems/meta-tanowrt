#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

IMAGE_ROOTFS_EXTRA_SPACE = "0"
ROOTFS_RM_BOOT_DIR_DISABLE = "0"

IMAGE_FSTYPES = "${INITRAMFS_FSTYPES}"

inherit tanowrt-image

# Disable runtime dependency on run-postinsts
ROOTFS_BOOTSTRAP_INSTALL = ""

TANOWRT_IMAGE_INITRAMFS_GEN_LOCALES ?= "0"
TANOWRT_IMAGE_INITRAMFS_FAILSAFE ?= "0"
TANOWRT_IMAGE_INITRAMFS_FAILSAFE_WAIT ?= "0"
TANOWRT_IMAGE_INITRAMFS_CLEAN_IMAGE_FEATURES ?= "1"
TANOWRT_IMAGE_INITRAMFS_CLEAN_EXTRA_IMAGE_FEATURES ?= "1"
TANOWRT_IMAGE_INITRAMFS_KEEP_IMAGE_FEATURES ?= ""

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

python __anonymous () {
    if d.getVar('TANOWRT_IMAGE_INITRAMFS_CLEAN_EXTRA_IMAGE_FEATURES', True) == "1":
        d.setVar('EXTRA_IMAGE_FEATURES', '');

    if d.getVar('TANOWRT_IMAGE_INITRAMFS_CLEAN_IMAGE_FEATURES', True) == "1":
        #
        # Cleanup all image features except listed in
        # TANOWRT_IMAGE_INITRAMFS_KEEP_IMAGE_FEATURES
        #
        image_features = d.getVar('IMAGE_FEATURES', True)
        keep = d.getVar('TANOWRT_IMAGE_INITRAMFS_KEEP_IMAGE_FEATURES', True)

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

    if d.getVar('TANOWRT_IMAGE_INITRAMFS_GEN_LOCALES', True) != "1":
        # Do not generate locales
        d.setVar('IMAGE_LINGUAS', '');
        d.setVar('GLIBC_GENERATE_LOCALES', '');
        d.setVar('ENABLE_BINARY_LOCALE_GENERATION', '0');
}

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

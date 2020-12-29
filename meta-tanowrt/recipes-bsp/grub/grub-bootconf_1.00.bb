LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"
SUMMARY = "grub.cfg for use in EFI systems"

PR = "tano0"

RPROVIDES_${PN} += "virtual/grub-bootconf"
PACKAGE_ARCH = "${MACHINE_ARCH}"

require conf/image-uefi.conf

S = "${WORKDIR}"

SRC_URI += "\
	file://grub.cfg \
	file://grubenv \
"

do_install[depends] = "virtual/kernel:do_deploy"

do_install() {
	install -d ${D}${EFI_FILES_PATH}
	install -m 0644 ${WORKDIR}/grub.cfg ${D}${EFI_FILES_PATH}/grub.cfg
	install -m 0644 ${WORKDIR}/grubenv ${D}${EFI_FILES_PATH}/grubenv
	install ${DEPLOY_DIR_IMAGE}/${KERNEL_IMAGETYPE} ${D}/${GRUB_KERNEL_IMAGE}
}

FILES_${PN} = "\
	${EFI_FILES_PATH}/grub.cfg \
	${EFI_FILES_PATH}/grubenv \
	/${KERNEL_IMAGETYPE} \
"

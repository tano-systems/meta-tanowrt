#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
PKG_VERSION = "v5.4.18-2020_0402"
PV = "5.4.18"
PR = "tano0"

DESCRIPTION = "Cypress CYW43012/43340/43362/4339/43430/43455/4354/4356/43570/4359/4373/54591/89459 Firmware"
HOMEPAGE = "https://community.cypress.com/community/linux"
SECTION = "kernel/firmware"
LICENSE = "Cypress-Wireless-Connectivity-Devices"
LIC_FILES_CHKSUM = "file://firmware/LICENCE;md5=cbc5f665d04f741f1e006d2096236ba7"

inherit allarch

SRC_URI = "https://community.cypress.com/gfawx74859/attachments/gfawx74859/resourcelibrary/1016/1/cypress-fmac-v5.4.18-2020_0402.zip;unpack=0"
SRC_URI[sha256sum] = "b12b0570f462c2f3c26dde98b10235a845a7109037def1e7e51af728bcc1a958"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

S = "${WORKDIR}/${PN}-${PV}"

do_unpack() {
	unzip -q -p ${DL_DIR}/cypress-fmac-${PKG_VERSION}.zip cypress-firmware-${PKG_VERSION}.tar.gz \
		| gzip -dc \
		| tar -C ${S} -xf -
}

FWPATH = "/lib/firmware/brcm"

PACKAGES = "\
	${PN}-43012-sdio \
	${PN}-43340-sdio \
	${PN}-43362-sdio \
	${PN}-4339-sdio \
	${PN}-43430-sdio \
	${PN}-43455-sdio \
	${PN}-4354-sdio \
	${PN}-4356-pcie \
	${PN}-4356-sdio \
	${PN}-43570-pcie \
	${PN}-4359-pcie \
	${PN}-4359-sdio \
	${PN}-4373-sdio \
	${PN}-4373-usb \
	${PN}-54591-pcie \
	${PN}-89459-pcie \
"

PROVIDES = "${PACKAGES}"

do_install() {
	install -d ${D}${FWPATH}
	install -m 0644 ${S}/firmware/*.bin ${D}${FWPATH}/
	install -m 0644 ${S}/firmware/*.clm_blob ${D}${FWPATH}/
}

SUMMARY_${PN}-43012-sdio = "CYW43012 FullMac SDIO firmware"
FILES_${PN}-43012-sdio   = "${FWPATH}/brcmfmac43012-sdio.bin \
                            ${FWPATH}/brcmfmac43012-sdio.clm_blob"

SUMMARY_${PN}-43340-sdio = "CYW43340 FullMac SDIO firmware"
FILES_${PN}-43340-sdio   = "${FWPATH}/brcmfmac43340-sdio.bin \
                            ${FWPATH}/brcmfmac43340-sdio.clm_blob"

SUMMARY_${PN}-43362-sdio = "CYW4343362 FullMac SDIO firmware"
FILES_${PN}-43362-sdio   = "${FWPATH}/brcmfmac43362-sdio.bin"

SUMMARY_${PN}-4339-sdio  = "CYW4339 FullMac SDIO firmware"
FILES_${PN}-4339-sdio    = "${FWPATH}/brcmfmac4339-sdio.bin"

SUMMARY_${PN}-43430-sdio = "CYW43430 FullMac SDIO firmware"
FILES_${PN}-43430-sdio   = "${FWPATH}/brcmfmac43430-sdio.bin \
                            ${FWPATH}/brcmfmac43430-sdio.clm_blob"

SUMMARY_${PN}-43455-sdio = "CYW43455 FullMac SDIO firmware"
FILES_${PN}-43455-sdio   = "${FWPATH}/brcmfmac43455-sdio.bin \
                            ${FWPATH}/brcmfmac43455-sdio.clm_blob"

SUMMARY_${PN}-4354-sdio  = "CYW4354 FullMac SDIO firmware"
FILES_${PN}-4354-sdio    = "${FWPATH}/brcmfmac4354-sdio.bin \
                            ${FWPATH}/brcmfmac4354-sdio.clm_blob"

SUMMARY_${PN}-4356-pcie  = "CYW4356 FullMac PCIe firmware"
FILES_${PN}-4356-pcie    = "${FWPATH}/brcmfmac4356-pcie.bin \
                            ${FWPATH}/brcmfmac4356-pcie.clm_blob"

SUMMARY_${PN}-4356-sdio  = "CYW4356 FullMac SDIO firmware"
FILES_${PN}-4356-sdio    = "${FWPATH}/brcmfmac4356-sdio.bin \
                            ${FWPATH}/brcmfmac4356-sdio.clm_blob"

SUMMARY_${PN}-43570-pcie = "CYW43570 FullMac PCIe firmware"
FILES_${PN}-43570-pcie   = "${FWPATH}/brcmfmac43570-pcie.bin \
                            ${FWPATH}/brcmfmac43570-pcie.clm_blob"

SUMMARY_${PN}-4359-pcie  = "CYW4359 FullMac PCIe firmware"
FILES_${PN}-4359-pcie    = "${FWPATH}/brcmfmac4359-pcie.bin \
                            ${FWPATH}/brcmfmac4359-pcie.clm_blob"

SUMMARY_${PN}-4359-sdio  = "CYW4359 FullMac SDIO firmware"
FILES_${PN}-4359-sdio    = "${FWPATH}/brcmfmac4359-sdio.bin \
                            ${FWPATH}/brcmfmac4359-sdio.clm_blob"

SUMMARY_${PN}-4373-sdio  = "CYW4373 FullMac SDIO firmware"
FILES_${PN}-4373-sdio    = "${FWPATH}/brcmfmac4373-sdio.bin \
                            ${FWPATH}/brcmfmac4373-sdio.clm_blob"

SUMMARY_${PN}-4373-usb   = "CYW4373 FullMac USB firmware"
FILES_${PN}-4373-usb     = "${FWPATH}/brcmfmac4373-usb.bin \
                            ${FWPATH}/brcmfmac4373.clm_blob"

SUMMARY_${PN}-54591-pcie = "CYW54591 FullMac PCIe firmware"
FILES_${PN}-54591-pcie   = "${FWPATH}/brcmfmac54591-pcie.bin \
                            ${FWPATH}/brcmfmac54591-pcie.clm_blob"

SUMMARY_${PN}-89459-pcie = "CYW89459 FullMac PCIe firmware"
FILES_${PN}-89459-pcie   = "${FWPATH}/brcmfmac89459-pcie.bin \
                            ${FWPATH}/brcmfmac89459-pcie.clm_blob"

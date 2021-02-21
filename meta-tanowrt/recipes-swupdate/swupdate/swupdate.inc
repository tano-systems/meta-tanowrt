#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2021 Tano Systems LLC. All rights reserved.
#
INCPR = "0"

# Prefer this version
DEFAULT_PREFERENCE = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

RDEPENDS_${PN} += "factory-reset"

require swupdate-generate-conf.inc
require swupdate-ubus-deps.inc

inherit tanowrt-services
inherit vars-expander
inherit swupdate-sanity-check

EXPAND_VARIABLES += " \
	SWU_BOARD \
	SWU_REVISION \
	SWU_BOOTLOADER \
	SWU_OVERLAY_CONTAINER \
	SWU_OVERLAY_FS \
	SWU_CONF \
	SWU_SW_VERSIONS_FILE \
	SWU_SW_VERSIONS_FILE_TMP \
	SWU_HW_COMPATIBILITY_FILE \
	SWU_SYSTEM_A_PART \
	SWU_SYSTEM_A_SELECTION \
	SWU_SYSTEM_B_PART \
	SWU_SYSTEM_B_SELECTION \
	SWU_BOOTLOADER_TRANSACTION_MARKER \
	SWU_SURICATTA \
"

#
# Supported bootloaders:
# - "none" - No bootloader
# - "uboot" - U-Boot
# - "grub-efi"  - GRUB EFI
#
SWU_BOOTLOADER ?= "uboot"

SWU_BOARD ?= "${MACHINE}"
SWU_REVISION ?= "1.0"

SWU_DONE_INIT ?= "1"

TANOWRT_SERVICE_PACKAGES = "swupdate"
TANOWRT_SERVICE_SCRIPTS_swupdate += "\
	swupdate \
	${@oe.utils.conditional('SWU_DONE_INIT', '1', 'swupdate_done', '', d)} \
"

TANOWRT_SERVICE_STATE_swupdate-swupdate ?= "enabled"
TANOWRT_SERVICE_STATE_swupdate-swupdate_done ?= "enabled"

PACKAGECONFIG_CONFARGS = ""

# Files
SRC_URI += " \
	file://config/swupdate \
	file://init.d/swupdate.init \
	file://init.d/swupdate_done.init \
	file://conf.d/09_swupdate_args \
	file://preinit/51_swupdate_gen_sw_versions \
	file://preinit/55_swupdate_preinit_before_overlay \
	file://preinit/81_swupdate_preinit \
	file://swupdate.sh \
	file://swupdate_done.sh \
	file://swupdate_hook.sh \
	file://swupdate_config.in \
	file://swupdate_bootloader_${SWU_BOOTLOADER}.sh \
	file://defconfig-bootloader-${SWU_BOOTLOADER}.cfg \
	file://defconfig-board.cfg \
	${@oe.utils.conditional('SWU_SURICATTA', '1', 'file://defconfig-suricatta-hawkbit.cfg', '', d)} \
"

# Patches
SRC_URI += "\
	file://0001-Fix-lua-linking.patch \
	file://0002-Add-relax-delays.patch \
"

do_set_extraversion() {
	sed -i -e 's/\(EXTRAVERSION\s*=\).*/\1 -git${@d.getVar('SRCREV')[:12]}-${PR}/' ${S}/Makefile
}

addtask set_extraversion after do_unpack before do_configure

require conf/image-uefi.conf

SWU_GRUBENV ?= "/boot/EFI/BOOT/grubenv"
SWU_GRUBENV[doc] = "Path to the GRUB environment file"

SWU_GRUBBIN ?= "${EFI_FILES_PATH}/${EFI_BOOT_IMAGE}"
SWU_GRUBBIN[doc] = "Path to the GRUB binary image"

SWU_GRUBENV_RO ?= "1"
SWU_GRUBENV_RO[doc] = "Flag indicating that the GRUB environment variables file is mounted in read-only mode by default."

SWU_GRUBENV_MOUNTPOINT ?= "/boot"

SWU_HW_COMPATIBILITY_FILE = "${SWUPDATE_HW_COMPATIBILITY_FILE}"
SWU_SW_VERSIONS_FILE = "${SWUPDATE_SW_VERSIONS_FILE}"
SWU_HW_REVISION = "${SWUPDATE_HW_REVISION}"

# Modify and/or remove original recipe scripts
do_install_modify_original() {
	rm -f ${D}${libdir}/swupdate/conf.d/10-mongoose-args
	rm -f ${D}${libdir}/swupdate/conf.d/90-start-progress
	rm -f ${D}${libdir}/swupdate/swupdate.sh
	rm -f ${D}${sysconfdir}/init.d/swupdate

	# Move /www to /www-swupdate
	mv ${D}/www ${D}/www-swupdate
}

do_install_append() {
	# Modify and/or remove original recipe scripts
	do_install_modify_original

	# Generate and install SWUPDATE configuration
	SWU_CONF_NAME=$(basename ${SWU_CONF})
	do_generate_swupdate_conf "${WORKDIR}/${SWU_CONF_NAME}"
	install -Dm 644 ${WORKDIR}/${SWU_CONF_NAME} ${D}${SWU_CONF}

	# Install SWU_HW_COMPATIBILITY_FILE file
	install -d ${D}$(dirname ${SWU_HW_COMPATIBILITY_FILE})
	echo "${SWU_BOARD} ${SWU_REVISION}" \
		> ${D}${SWU_HW_COMPATIBILITY_FILE}

	# Install preinit scripts
	install -d ${D}${base_libdir}/preinit
	install -m 0644 ${WORKDIR}/preinit/51_swupdate_gen_sw_versions ${D}${base_libdir}/preinit/
	install -m 0644 ${WORKDIR}/preinit/55_swupdate_preinit_before_overlay ${D}${base_libdir}/preinit/
	install -m 0644 ${WORKDIR}/preinit/81_swupdate_preinit ${D}${base_libdir}/preinit/

	# Install configuration scripts
	install -d ${D}${libdir}/swupdate/conf.d
	install -m 0644 ${WORKDIR}/conf.d/09_swupdate_args ${D}${libdir}/swupdate/conf.d/

	# Install UCI config
	install -d ${D}${sysconfdir}/config
	install -m 0644 ${WORKDIR}/config/swupdate ${D}${sysconfdir}/config/

	# Install procd init scripts
	install -d ${D}${sysconfdir}/init.d

	if [ "${SWU_DONE_INIT}" = "1" ]; then
		install -m 0755 ${WORKDIR}/init.d/swupdate_done.init \
		                ${D}${sysconfdir}/init.d/swupdate_done
	fi

	install -m 0755 ${WORKDIR}/init.d/swupdate.init \
	                ${D}${sysconfdir}/init.d/swupdate

	# Install scripts
	install -d ${D}${libdir}/swupdate
	install -m 0644 ${WORKDIR}/swupdate.sh ${D}${libdir}/swupdate/
	install -m 0644 ${WORKDIR}/swupdate_config ${D}${libdir}/swupdate/
	install -m 0755 ${WORKDIR}/swupdate_done.sh ${D}${libdir}/swupdate/
	install -m 0755 ${WORKDIR}/swupdate_hook.sh ${D}${libdir}/swupdate/

	if [ "${SWU_BOOTLOADER}" = "grub-efi" ]; then
		if [ -n "${SWU_GRUBENV}" ]; then
			echo "SWU_GRUBENV=\"${SWU_GRUBENV}\"" \
				>> ${D}${libdir}/swupdate/swupdate_config
		fi
		if [ -n "${SWU_GRUBBIN}" ]; then
			echo "SWU_GRUBBIN=\"${SWU_GRUBBIN}\"" \
				>> ${D}${libdir}/swupdate/swupdate_config
		fi
		echo "SWU_GRUBENV_RO=\"${SWU_GRUBENV_RO}\"" \
			>> ${D}${libdir}/swupdate/swupdate_config
		echo "SWU_GRUBENV_MOUNTPOINT=\"${SWU_GRUBENV_MOUNTPOINT}\"" \
			>> ${D}${libdir}/swupdate/swupdate_config
	fi

	if [ ! -f "${WORKDIR}/swupdate_bootloader_${SWU_BOOTLOADER}.sh" ]; then
		bbfatal "Unsupported SWU_BOOTLOADER ${SWU_BOOTLOADER}"
	fi

	install -m 0644 ${WORKDIR}/swupdate_bootloader_${SWU_BOOTLOADER}.sh \
	                ${D}${libdir}/swupdate/

	# Create symlink to generated at runtime versions file
	ln -sf "${SWU_SW_VERSIONS_FILE_TMP}" \
	       "${D}${SWU_SW_VERSIONS_FILE}"
}

FILES_${PN}-www_remove = "/www/*"
FILES_${PN}-www = "/www-swupdate/*"

FILES_${PN} += "\
	${SWU_CONF} \
	${SWU_HW_REVISION} \
	${libdir}/swupdate \
	${base_libdir}/preinit \
"

CONFFILES_${PN} += "\
	${sysconfdir}/swupdate.conf \
"

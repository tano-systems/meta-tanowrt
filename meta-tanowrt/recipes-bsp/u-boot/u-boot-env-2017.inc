#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
# Initial environment script generation for old U-Boot 2017.x
# Some parts of this code are taken from recipes of more modern
# versions of the U-Boot (2019 and 2020)
#

PACKAGE_BEFORE_PN += "${PN}-env"

# Default name of u-boot initial env, but enable individual recipes to change
# this value.
UBOOT_INITIAL_ENV ?= "${PN}-initial-env"

RPROVIDES_${PN}-env += "u-boot-default-env"
ALLOW_EMPTY_${PN}-env = "1"
FILES_${PN}-env = " \
	${@ '${sysconfdir}/${UBOOT_INITIAL_ENV}*' if d.getVar('UBOOT_INITIAL_ENV') else ''} \
	${sysconfdir}/fw_env.config \
"

RDEPENDS_${PN} += "${PN}-env"

do_compile_append() {
	if [ -n "${UBOOT_INITIAL_ENV}" ]; then
		if [ -n "${UBOOT_CONFIG}" ]; then
			unset i j
			for config in ${UBOOT_MACHINE}; do
				i=$(expr $i + 1);
				for type in ${UBOOT_CONFIG}; do
					j=$(expr $j + 1);
					if [ $j -eq $i ]; then
						rm -f ${B}/${config}/scripts/get_default_envs.sh
						cp ${S}/scripts/get_default_envs.sh \
						   ${B}/${config}/scripts/get_default_envs.sh

						# Add support for cross-compilation to get_default_envs.sh script
						sed -i -e "s|objcopy|\$\{CROSS_COMPILE\}objcopy|" \
							${B}/${config}/scripts/get_default_envs.sh

						# Script must be called from build directory
						CROSS_COMPILE=${TARGET_PREFIX} ${B}/${config}/scripts/get_default_envs.sh \
							> ${B}/${config}/${UBOOT_INITIAL_ENV}-${type}
					fi
				done
				unset j
			done
			unset i
		else
			rm -f ${B}/scripts/get_default_envs.sh
			cp ${S}/scripts/get_default_envs.sh \
			   ${B}/scripts/get_default_envs.sh

			# Add support for cross-compilation to get_default_envs.sh script
			sed -i -e "s|objcopy|\$\{CROSS_COMPILE\}objcopy|" \
				${B}/scripts/get_default_envs.sh

			# Script must be called from build directory
			CROSS_COMPILE=${TARGET_PREFIX} ${B}/scripts/get_default_envs.sh \
				> ${B}/${UBOOT_INITIAL_ENV}
		fi
	fi
}

do_install_append() {
	if [ -n "${UBOOT_INITIAL_ENV}" ]; then
		if [ -n "${UBOOT_CONFIG}" ]; then
			unset i j
			for config in ${UBOOT_MACHINE}; do
				i=$(expr $i + 1);
				for type in ${UBOOT_CONFIG}; do
					j=$(expr $j + 1);
					if [ $j -eq $i ]; then
						install -d ${D}${sysconfdir}
						install -m 0644 ${B}/${UBOOT_INITIAL_ENV}-${type} \
						                ${D}${sysconfdir}/${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR}

						ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR} \
						       ${D}/${sysconfdir}/${UBOOT_INITIAL_ENV}-${MACHINE}-${type}
						ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR} \
						       ${D}/${sysconfdir}/${UBOOT_INITIAL_ENV}-${type}
						ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR} \
						       ${D}/${sysconfdir}/${UBOOT_INITIAL_ENV}
					fi
				done
				unset j
			done
			unset i
		else
			install -d ${D}${sysconfdir}
			install -m 0644 ${B}/${UBOOT_INITIAL_ENV} \
			                ${D}${sysconfdir}/${UBOOT_INITIAL_ENV}
		fi
	fi
}

do_deploy_append() {
	if [ -n "${UBOOT_INITIAL_ENV}" ]; then
		if [ -n "${UBOOT_CONFIG}" ]; then
			unset i j
			for config in ${UBOOT_MACHINE}; do
				i=$(expr $i + 1);
				for type in ${UBOOT_CONFIG}; do
					j=$(expr $j + 1);
					if [ $j -eq $i ]; then
						install -D -m 644 ${B}/${config}/${UBOOT_INITIAL_ENV}-${type} \
						                  ${DEPLOYDIR}/${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR}
						cd ${DEPLOYDIR}
						ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR} \
						       ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}
						ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR} \
						       ${UBOOT_INITIAL_ENV}-${type}
					fi
				done
				unset j
			done
			unset i
		else
			install -D -m 644 ${B}/${UBOOT_INITIAL_ENV} \
			                  ${DEPLOYDIR}/${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR}
			cd ${DEPLOYDIR}
			ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR} \
			       ${UBOOT_INITIAL_ENV}-${MACHINE}
			ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR} \
			       ${UBOOT_INITIAL_ENV}
		fi
	fi
}


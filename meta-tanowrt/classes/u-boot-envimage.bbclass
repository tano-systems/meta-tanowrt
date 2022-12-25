#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
# Binary initial environment image generation
#

DEPENDS:append = " u-boot-mkenvimage-native"

UBOOT_ENV_SIZE      ?= "131072"
UBOOT_ENV_REDUNDANT ?= "1"

do_compile:append() {
	if [ -n "${UBOOT_INITIAL_ENV}" ]; then
		if [ -n "${UBOOT_CONFIG}" ]; then
			unset i j
			for config in ${UBOOT_MACHINE}; do
				i=$(expr $i + 1);
				for type in ${UBOOT_CONFIG}; do
					j=$(expr $j + 1);
					if [ $j -eq $i ]; then
						MKENVIMAGE_ARGS="-s ${UBOOT_ENV_SIZE} -o ${B}/${config}/${UBOOT_INITIAL_ENV}-${type}.bin ${B}/${config}/${UBOOT_INITIAL_ENV}-${type}"
						if [ "${UBOOT_ENV_REDUNDANT}" = "1" ]; then
							MKENVIMAGE_ARGS="-r ${MKENVIMAGE_ARGS}"
						fi

						mkenvimage ${MKENVIMAGE_ARGS}
					fi
				done
				unset j
			done
			unset i
		else
			MKENVIMAGE_ARGS="-s ${UBOOT_ENV_SIZE} -o ${B}/${UBOOT_INITIAL_ENV}.bin ${B}/${UBOOT_INITIAL_ENV}"
			if [ "${UBOOT_ENV_REDUNDANT}" = "1" ]; then
				MKENVIMAGE_ARGS="-r ${MKENVIMAGE_ARGS}"
			fi

			mkenvimage ${MKENVIMAGE_ARGS}
		fi
	fi
}

do_deploy:append() {
	if [ -n "${UBOOT_INITIAL_ENV}" ]; then
		if [ -n "${UBOOT_CONFIG}" ]; then
			unset i j
			for config in ${UBOOT_MACHINE}; do
				i=$(expr $i + 1);
				for type in ${UBOOT_CONFIG}; do
					j=$(expr $j + 1);
					if [ $j -eq $i ]; then
						install -D -m 644 ${B}/${config}/${UBOOT_INITIAL_ENV}-${type}.bin \
						                  ${DEPLOYDIR}/${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR}.bin
						cd ${DEPLOYDIR}
						ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR}.bin \
						       ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}.bin
						ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${type}-${PV}-${PR}.bin \
						       ${UBOOT_INITIAL_ENV}-${type}.bin
					fi
				done
				unset j
			done
			unset i
		else
			install -D -m 644 ${B}/${UBOOT_INITIAL_ENV}.bin \
			                  ${DEPLOYDIR}/${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR}.bin
			cd ${DEPLOYDIR}
			ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR}.bin \
			       ${UBOOT_INITIAL_ENV}-${MACHINE}.bin
			ln -sf ${UBOOT_INITIAL_ENV}-${MACHINE}-${PV}-${PR}.bin \
			       ${UBOOT_INITIAL_ENV}.bin
		fi
	fi
}

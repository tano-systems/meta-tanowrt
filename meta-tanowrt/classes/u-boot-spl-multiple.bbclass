#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Generation of the single image with multiple SPL images inside
#
UBOOT_SPL_MULTIPLE ?= "0"
UBOOT_SPL_MULTIPLE_OUTPUT ?= "${SPL_BINARY}-${MACHINE}"
UBOOT_SPL_MULTIPLE_OUTPUT_LINK ?= "${SPL_BINARY}"
UBOOT_SPL_MULTIPLE_INPUT ?= "${SPL_BINARY}"
UBOOT_SPL_MULTIPLE_COUNT ?= "4"
UBOOT_SPL_MULTIPLE_BLOCK_SIZE ?= "131072"
UBOOT_SPL_MULTIPLE_PADDING_BYTE ?= "\377"

do_deploy[depends] += "bc-native:do_populate_sysroot"

build_spl_multiple() {
	local input="${1}"
	local output="${2}"
	local output_link="${3}"

	rm -f ${DEPLOYDIR}/${output}

	if [ ! -e ${DEPLOYDIR}/${input} ]; then
		bbfatal "Could not find input SPL binary at ${DEPLOYDIR}/${input}"
	fi

	local INPUT_SPL_SIZE=$(stat -L -c %s ${DEPLOYDIR}/${input} 2>/dev/null)

	if [ ${INPUT_SPL_SIZE} -gt ${UBOOT_SPL_MULTIPLE_BLOCK_SIZE} ]; then
		bbfatal "Input SPL size is greater than multiple block size (${INPUT_SPL_SIZE} > ${UBOOT_SPL_MULTIPLE_SIZE})"
	fi

	local SPL_COUNT
	for SPL_COUNT in ${UBOOT_SPL_MULTIPLE_COUNT}; do
		local TOTAL_SIZE=$(echo "${UBOOT_SPL_MULTIPLE_BLOCK_SIZE} * ${SPL_COUNT}" | bc)
		dd if=/dev/zero \
			ibs=1 \
			count=${TOTAL_SIZE} \
			| tr "\000" "${UBOOT_SPL_MULTIPLE_PADDING_BYTE}" \
				> ${DEPLOYDIR}/${output}-x${SPL_COUNT}

		local N=0
		while [ ${N} -lt ${SPL_COUNT} ]; do
			local SEEK=$(echo "${UBOOT_SPL_MULTIPLE_BLOCK_SIZE} * ${N}" | bc)
			dd if=${DEPLOYDIR}/${input} \
			   of=${DEPLOYDIR}/${output}-x${SPL_COUNT} \
			   ibs=${INPUT_SPL_SIZE} \
			   obs=1 \
			   count=1 \
			   conv=notrunc \
			   seek=${SEEK}

			N=$(echo "${N} + 1" | bc)
		done

		ln -sf ${output}-x${SPL_COUNT} \
		       ${DEPLOYDIR}/${output_link}-x${SPL_COUNT}
	done
}

do_deploy:append() {
	if [ "${UBOOT_SPL_MULTIPLE}" = "1" ]; then
		if [ -n "${UBOOT_CONFIG}" ]; then
			unset i j
			for config in ${UBOOT_MACHINE}; do
				i=$(expr $i + 1);
				for type in ${UBOOT_CONFIG}; do
					j=$(expr $j + 1);
					if [ $j -eq $i ]; then
						build_spl_multiple \
							"${UBOOT_SPL_MULTIPLE_INPUT}-${type}" \
							"${UBOOT_SPL_MULTIPLE_OUTPUT}-${type}" \
							"${UBOOT_SPL_MULTIPLE_OUTPUT_LINK}-${type}"
					fi
				done
				unset j
			done
			unset i
		else
			build_spl_multiple \
				"${UBOOT_SPL_MULTIPLE_INPUT}" \
				"${UBOOT_SPL_MULTIPLE_OUTPUT}" \
				"${UBOOT_SPL_MULTIPLE_OUTPUT_LINK}"
		fi
	fi
}

# Copyright (C) 2019, Fuzhou Rockchip Electronics Co., Ltd
# Copyright (C) 2022, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

require recipes-bsp/u-boot/u-boot-common.inc
require u-boot-tano-rockchip.inc

PROVIDES += "u-boot"

PV = "2017.09+git${SRCPV}"
PR = "tano10"

LIC_FILES_CHKSUM = "file://Licenses/README;md5=a2c678cfd4a4d97135585cad908541c6"

DEPENDS += "bc-native dtc-native python3-setuptools-native"
DEPENDS += "${PYTHON_PN}-native"

# Needed for packing BSP u-boot
DEPENDS += "coreutils-native ${PYTHON_PN}-pyelftools-native"

inherit python3-dir

SRC_URI = "\
	git://github.com/JeffyCN/mirrors.git;protocol=https;branch=u-boot; \
	git://github.com/tano-systems/rkbin.git;protocol=https;branch=tano/master;name=rkbin;destsuffix=rkbin; \
	file://${MACHINE}_rkboot.ini \
	file://${MACHINE}_rktrust.ini \
"

S = "${WORKDIR}/git"

SRCREV = "e3ca3c3805cc60cc9e2fe2a4d78694907b49ee46"
SRCREV_rkbin = "fe66a9be19f2f72a96dc2413676665acf7e1b446"

SRCREV_FORMAT = "default_rkbin"

# Patches
SRC_URI += "\
	file://1000-Add-support-for-images-created-by-newer-versions-of-.patch \
	file://1001-Allow-to-define-some-options-in-defconfig.patch \
	file://1002-rockchip-scripts-A-few-fixes-and-updates-to-Rockchip.patch \
	file://1003-rockchip-Fix-build-with-disabled-CONFIG_ROCKCHIP_RES.patch \
	file://1004-rockchip-Remove-ROCKCHIP_FIT_IMAGE_PACK-dependency-o.patch \
	file://1005-rockchip-Do-not-parse-bootargs-from-FDT.patch \
	file://1006-rockchip-rk3308-Fix-default-configs.patch \
	file://1007-rockchip-rk3568-Fix-default-configs.patch \
	file://1008-rockchip-rk3308-Add-UART3-over-GPIO0_C1-C2-pins-supp.patch \
	file://1009-rk3568-Debug-to-UART4.patch \
	file://1010-rockchip-add-rock-pi-s-u-boot.patch \
	file://1011-rockchip-rk3308-rk3568-set-first-boot-to-same-as-spl.patch \
	file://1012-clk_rk3308-Add-support-for-RK3308B-specific-PWM-cloc.patch \
	file://1013-rk3568-evb-Add-DTS-with-boot-order-selection.patch \
	file://1014-rk3568-Add-DTS-for-Boardcon-EM3568-board.patch \
	file://1015-dwc_eth_qos-Fix-DT-parameters-names.patch \
"

# Use UART2 for debug on EM3568
SRC_URI:remove:boardcon_em3568 = "file://1009-rk3568-Debug-to-UART4.patch"

do_configure:prepend() {
	# Make sure we use /usr/bin/env ${PYTHON_PN} for scripts
	for s in `grep -rIl python ${S}`; do
		sed -i -e '1s|^#!.*python[23]*|#!/usr/bin/env ${PYTHON_PN}|' $s
	done

	# Support python3
	sed -i -e 's/\(open(.*[^"]\))/\1, "rb")/' -e 's/,$//' \
		-e 's/print >> \([^,]*\), *\(.*\)$/print(\2, file=\1)/' \
		-e 's/print \(.*\)$/print(\1)/' \
		${S}/arch/arm/mach-rockchip/make_fit_atf.py

	# Remove unneeded stages from make.sh
	sed -i -e '/^select_tool/d' -e '/^clean/d' -e '/^\t*make/d' ${S}/make.sh

	[ -e "${S}/.config" ] && make -C ${S} mrproper
}

RKBIN_DIR ?= "${WORKDIR}/rkbin"

# Generate Rockchip style loader binaries
RK_IMAGE_IDBLOCK ?= "idblock.img"
RK_IMAGE_TRUST   ?= "trust.img"
RK_IMAGE_LOADER  ?= "loader.bin"

RKBOOT_HEAD      ?= "FlashHead"
RKBOOT_LOADER1   ?= "FlashData"
RKBOOT_LOADER2   ?= "FlashBoot"

CMD_MAKE_RKBOOT = "rk_make_rkboot"

RKBIN_DDR_UART_UPDATE ?= "0"
RKBIN_DDR_UART_ID ?= "2"
RKBIN_DDR_UART_IOMUX ?= "0"
RKBIN_DDR_UART_BAUDRATE ?= "1500000"

do_unpack[vardeps] += "\
	RKBIN_DDR_UART_UPDATE RKBIN_DDR_UART_ID RKBIN_DDR_UART_IOMUX RKBIN_DDR_UART_BAUDRATE"

rk_update_rkbin_ddr() {

	DDR_BIN_PATH=$(sed -n "/FlashData=/s/FlashData=//p" ${BUILD_DIR}/${MACHINE}_rkboot.ini | tr -d '\r')
	bbdebug 1 "Detected ddrbin ${DDR_BIN_PATH} from ${BUILD_DIR}/${MACHINE}_rkboot.ini"

	DDR_BIN=$(basename ${DDR_BIN_PATH})
	cp -vf ${DDR_BIN_PATH} ${BUILD_DIR}/${DDR_BIN}

	bbdebug 1 "Reading parameters from ${DDR_BIN} to ${BUILD_DIR}/ddrbin_param.txt..."
	rm -f ddrbin_param.txt
	${RKBIN_DIR}/tools/ddrbin_tool -g ${BUILD_DIR}/ddrbin_param.txt ${BUILD_DIR}/${DDR_BIN}

	# Update parameters and add mandatory start tag parameter
	bbdebug 1 "Updating parameters in ${BUILD_DIR}/ddrbin_param.txt..."
	sed -i -e "s,\(uart id\)=.*,\1=${RKBIN_DDR_UART_ID},g" \
	       -e "s,\(uart iomux\)=.*,\1=${RKBIN_DDR_UART_IOMUX},g" \
	       -e "s,\(uart baudrate\)=.*,\1=${RKBIN_DDR_UART_BAUDRATE},g" \
	       -e "1 a\start tag=0x12345678" \
		${BUILD_DIR}/ddrbin_param.txt

	bbdebug 1 "Updating parameters in ${DDR_BIN} binary..."
	${RKBIN_DIR}/tools/ddrbin_tool ${BUILD_DIR}/ddrbin_param.txt ${BUILD_DIR}/${DDR_BIN}

	# Update paths to ddr binary in ${MACHINE}_rkboot.ini to updated binary
	sed -i -e "s,\(.*\)=${DDR_BIN_PATH},\1=${BUILD_DIR}/${DDR_BIN},g" \
		${BUILD_DIR}/${MACHINE}_rkboot.ini
}

rk_make_rkboot() {
	if [ "${RKBIN_DDR_UART_UPDATE}" != "0" ]; then
		rk_update_rkbin_ddr
	fi

	# Pack rockchip loader image
	RKBIN_DIR=${WORKDIR}/rkbin ./make.sh ${BUILD_DIR}/${MACHINE}_rkboot.ini \
	                                     ${BUILD_DIR}/${MACHINE}_rktrust.ini

	# Generate idblock image
	bbnote "${PN}: Generating ${RK_IMAGE_IDBLOCK} from ${RK_IMAGE_LOADER}"

	LOADER_UNPACK_DIR="unpack"

	[ -d "${LOADER_UNPACK_DIR}" ] && rm -rf ${LOADER_UNPACK_DIR}/*
	mkdir -p ${LOADER_UNPACK_DIR}

	pushd ${LOADER_UNPACK_DIR}
	${BUILD_DIR}/tools/boot_merger --unpack "${BUILD_DIR}/${RK_IMAGE_LOADER}"
	popd

	IDBLOCK_DATA="${LOADER_UNPACK_DIR}/${RKBOOT_LOADER1}:${LOADER_UNPACK_DIR}/${RKBOOT_LOADER2}"

	# Generate idblock image
	if [ -f FlashHead ]; then
		IDBLOCK_DATA="${LOADER_UNPACK_DIR}/${RKBOOT_HEAD}:${IDBLOCK_DATA}"
	fi

	${BUILD_DIR}/tools/mkimage -n "${SOC_FAMILY}" \
		-T rksd -d ${IDBLOCK_DATA} \
		"${RK_IMAGE_IDBLOCK}"
}

rk_uboot_compile() {
	BUILD_DIR=$1
	CONFIG=$2
	TYPE=$3

	cd ${BUILD_DIR}

	# Prepare needed files
	for d in make.sh scripts configs arch/arm/mach-rockchip; do
		cp -rT ${S}/${d} ${d}
	done

	# Substitute some vars in ${MACHINE}_rkboot.ini
	cp -vf ${WORKDIR}/${MACHINE}_rkboot.ini ${BUILD_DIR}/
	cp -vf ${WORKDIR}/${MACHINE}_rktrust.ini ${BUILD_DIR}/
	sed -i -e "s,@@RKBIN_DIR@@,${RKBIN_DIR},g" \
	       -e "s,@@BUILD_DIR@@,${BUILD_DIR},g" \
	       -e "s,@@RK_IMAGE_IDBLOCK@@,${RK_IMAGE_IDBLOCK},g" \
	       -e "s,@@RK_IMAGE_LOADER@@,${RK_IMAGE_LOADER},g" \
	       -e "s,@@RK_IMAGE_TRUST@@,${RK_IMAGE_TRUST},g" \
	       -e "s,@@RKBOOT_HEAD@@,${RKBOOT_HEAD},g" \
	       -e "s,@@RKBOOT_LOADER1@@,${RKBOOT_LOADER1},g" \
	       -e "s,@@RKBOOT_LOADER2@@,${RKBOOT_LOADER2},g" \
	       ${BUILD_DIR}/${MACHINE}_rkboot.ini \
	       ${BUILD_DIR}/${MACHINE}_rktrust.ini

	${CMD_MAKE_RKBOOT} ${BUILD_DIR} ${CONFIG} ${TYPE}
}

#
# Same as original do_compile from `recipes-bsp/u-boot/u-boot.inc`
# except added rk_uboot_compile call after default oe_runmake
#
do_compile () {
    if [ "${@bb.utils.filter('DISTRO_FEATURES', 'ld-is-gold', d)}" ]; then
        sed -i 's/$(CROSS_COMPILE)ld$/$(CROSS_COMPILE)ld.bfd/g' ${S}/config.mk
    fi

    unset LDFLAGS
    unset CFLAGS
    unset CPPFLAGS

    if [ ! -e ${B}/.scmversion -a ! -e ${S}/.scmversion ]
    then
        echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
        echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
    fi

    if [ -n "${UBOOT_CONFIG}" -o -n "${UBOOT_DELTA_CONFIG}" ]
    then
        unset i j k
        for config in ${UBOOT_MACHINE}; do
            i=$(expr $i + 1);
            for type in ${UBOOT_CONFIG}; do
                j=$(expr $j + 1);
                if [ $j -eq $i ]
                then
                    oe_runmake -C ${S} O=${B}/${config} ${UBOOT_MAKE_TARGET}
                    rk_uboot_compile "${B}/${config}" "${config}" "${type}"
                    for binary in ${UBOOT_BINARIES}; do
                        k=$(expr $k + 1);
                        if [ $k -eq $i ]; then
                            cp ${B}/${config}/${binary} ${B}/${config}/u-boot-${type}.${UBOOT_SUFFIX}
                        fi
                    done

                    # Generate the uboot-initial-env
                    if [ -n "${UBOOT_INITIAL_ENV}" ]; then
                        oe_runmake -C ${S} O=${B}/${config} u-boot-initial-env
                        cp ${B}/${config}/u-boot-initial-env ${B}/${config}/u-boot-initial-env-${type}
                    fi

                    unset k
                fi
            done
            unset j
        done
        unset i
    else
        oe_runmake -C ${S} O=${B} ${UBOOT_MAKE_TARGET}
        rk_uboot_compile "${B}"

        # Generate the uboot-initial-env
        if [ -n "${UBOOT_INITIAL_ENV}" ]; then
            oe_runmake -C ${S} O=${B} u-boot-initial-env
        fi
    fi
}

rk_uboot_deploy() {
	BUILD_DIR=$1
	CONFIG=$2
	TYPE=$3

	cd ${BUILD_DIR}

	for binary in "${RK_IMAGE_IDBLOCK}" "${RK_IMAGE_LOADER}";do
		[ -f "${binary}" ] || continue
		if [ -n "${TYPE}" ]; then
			install "${binary}" "${DEPLOYDIR}/${binary}-${MACHINE}-${TYPE}-${PV}"
			ln -sf "${binary}-${MACHINE}-${TYPE}-${PV}" "${DEPLOYDIR}/${binary}-${MACHINE}-${TYPE}"
		else
			install "${binary}" "${DEPLOYDIR}/${binary}-${PV}"
			ln -sf "${binary}-${PV}" "${DEPLOYDIR}/${binary}"
		fi
	done
}

do_deploy:append() {
	if [ -n "${UBOOT_CONFIG}" ]; then
		unset i j
		for config in ${UBOOT_MACHINE}; do
			i=$(expr $i + 1);
			for type in ${UBOOT_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]; then
					rk_uboot_deploy "${B}/${config}" "${config}" "${type}"
				fi
			done
			unset j
		done
		unset i
	else
		rk_uboot_deploy "${B}"
	fi
}

FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}/files:"
FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}/patches:"

inherit u-boot-defconfig-copy
inherit u-boot-envimage

UBOOT_LOCALVERSION = "-git${SRCPV}-${PR}"

# Always write localversion to .scmversion
do_compile:prepend() {
	echo ${UBOOT_LOCALVERSION} > ${B}/.scmversion
	echo ${UBOOT_LOCALVERSION} > ${S}/.scmversion
}

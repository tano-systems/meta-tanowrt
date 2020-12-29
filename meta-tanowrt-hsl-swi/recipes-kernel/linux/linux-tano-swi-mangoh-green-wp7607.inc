#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019-2020 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR_append = ".1"

DT_DIR = "${MACHINE}"
DT_DIR_mangoh-green-wp7607 = "wp7xxx"
FILESEXTRAPATHS_prepend := "${TANOWRT_HSL_BASE}/devicetree/${LINUX_VERSION_SHORT}/${DT_DIR}:"

# Config
SRC_URI_append_mangoh-green-wp7607 = "\
	file://defconfig \
	file://0001-i2c-msm-v2-Hide-I2C_MSM_ERR_NACK-errors-for-0x3a-sla.patch \
	file://0002-spidev-Add-spidev-to-compatible-list.patch \
"

# Devicetree
DT_SUBDIR = "dts"

SRC_URI_append_mangoh-green-wp7607 = "\
	file://mdm9607-wp76xx-mangoh-green.dts;subdir=${DT_SUBDIR} \
	file://mdm9206-wp77xx-mangoh-green.dts;subdir=${DT_SUBDIR} \
	file://mdm9206.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9206-wp77xx.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-bus.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-coresight.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-ion.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-pinctrl.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-pm.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-regulator.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-rpm-regulator.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-sierra-monitor.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-smp2p.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-swi.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-swimcu.dtsi;subdir=${DT_SUBDIR} \
	file://mdm9607-wp76xx.dtsi;subdir=${DT_SUBDIR} \
	file://msm-pm8019.dtsi;subdir=${DT_SUBDIR} \
"

do_unpack[cleandirs] += "${WORKDIR}/${DT_SUBDIR}"

do_configure_append_mangoh-green-wp7607() {
	install -d ${S}/arch/arm/boot/dts/qcom

	for f in ${WORKDIR}/${DT_SUBDIR}/*; do
		install -m 0644 $f ${S}/arch/arm/boot/dts/qcom/
	done
}

#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2019-2021 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

PR:append = ".6"

# Config
SRC_URI:append:mangoh-green-wp7607 = "\
	file://defconfig \
	file://0001-i2c-msm-v2-Hide-I2C_MSM_ERR_NACK-errors-for-0x3a-sla.patch \
	file://0002-spidev-Add-spidev-to-compatible-list.patch \
	file://0003-u_ether-Disable-error-messages-for-SIOCGMIIPHY-and-S.patch \
	file://0004-msm_rmnet_bam-Disable-error-messages-for-SIOCGIWNAME.patch \
"

# Devicetree
DT_DIR = "dts"

SRC_URI:append:mangoh-green-wp7607 = "\
	file://${DT_DIR}/mdm9607-wp76xx-mangoh-green.dts \
	file://${DT_DIR}/mdm9206-wp77xx-mangoh-green.dts \
	file://${DT_DIR}/mdm9206.dtsi \
	file://${DT_DIR}/mdm9206-wp77xx.dtsi \
	file://${DT_DIR}/mdm9607.dtsi \
	file://${DT_DIR}/mdm9607-bus.dtsi \
	file://${DT_DIR}/mdm9607-coresight.dtsi \
	file://${DT_DIR}/mdm9607-ion.dtsi \
	file://${DT_DIR}/mdm9607-pinctrl.dtsi \
	file://${DT_DIR}/mdm9607-pm.dtsi \
	file://${DT_DIR}/mdm9607-regulator.dtsi \
	file://${DT_DIR}/mdm9607-rpm-regulator.dtsi \
	file://${DT_DIR}/mdm9607-sierra-monitor.dtsi \
	file://${DT_DIR}/mdm9607-smp2p.dtsi \
	file://${DT_DIR}/mdm9607-swi.dtsi \
	file://${DT_DIR}/mdm9607-swimcu.dtsi \
	file://${DT_DIR}/mdm9607-wp76xx.dtsi \
	file://${DT_DIR}/msm-pm8019.dtsi \
"

do_unpack[cleandirs] += "${WORKDIR}/${DT_DIR}"

do_configure:append:mangoh-green-wp7607() {
	install -d ${S}/arch/arm/boot/dts/qcom

	for f in ${WORKDIR}/${DT_DIR}/*; do
		install -m 0644 $f ${S}/arch/arm/boot/dts/qcom/
	done
}

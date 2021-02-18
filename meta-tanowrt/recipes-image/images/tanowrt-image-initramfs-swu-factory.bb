#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"
SUMMARY = "TanoWrt SWUPDATE factory installation initramfs image"

inherit features_check
REQUIRED_MACHINE_FEATURES = "swupdate-factory"

COMPATIBLE_MACHINE = "qemupc"

inherit tanowrt-image-initramfs
inherit uci-config

do_uci_config() {
	:
}

UCI_ROOT = "${IMAGE_ROOTFS}"

SWU_FACTORY_INITRAMFS_NETWORKING ?= "0"
SWU_FACTORY_INITRAMFS_NETWORKING[doc] = \
	"Install and enable networking support in final image"

SWU_FACTORY_INITRAMFS_LAN_DHCP_CLIENT ?= "1"
SWU_FACTORY_INITRAMFS_LNA_DHCP_CLIENT[doc] = \
	"Configure lan network interface to use DHCP client"

SWU_FACTORY_INITRAMFS_SSH ?= "0"
SWU_FACTORY_INITRAMFS_SSH[doc] = \
	"Install and enable SSH server in final image"

#
# Do not run swupdate serivce automatically
# swupdate must be run by the installation script
# (see swupdate-factory-install package)
#
# Disable other services not needed for SWU
# factory installation script.
#
SWU_FACTORY_INITRAMFS_DISABLE_SERVICES ?= "\
	swupdate \
	swupdate_done \
	fstab \
	sysntpd \
	psplash \
"
SWU_FACTORY_INITRAMFS_DISABLE_SERVICES[doc] = \
	"List of procd services that must be disabled in the final image"

TANOWRT_IMAGE_INITRAMFS_KEEP_FEATURES += "\
	splash \
	${@oe.utils.conditional('SWU_FACTORY_INITRAMFS_SSH', '1', \
		'ssh-server-dropbear ssh-server-openssh', '', d)} \
"

TANOWRT_IMAGE_INITRAMFS_INSTALL += "\
	swupdate \
	swupdate-factory-install \
	${@oe.utils.conditional('SWU_FACTORY_INITRAMFS_NETWORKING', '1', \
		'${VIRTUAL-RUNTIME_network_manager} odhcpd', '', d)} \
	${@oe.utils.conditional('SWU_FACTORY_INITRAMFS_SSH', '1', \
		'openssh-sftp-server', '', d)} \
"

# Disable unneeded services
swu_install_initramfs_modify() {
	for srv in ${SWU_FACTORY_INITRAMFS_DISABLE_SERVICES}; do
		if [ -e "${IMAGE_ROOTFS}${sysconfdir}/init.d/${srv}" ]; then
			IPKG_INSTROOT=${IMAGE_ROOTFS} /bin/sh ${IMAGE_ROOTFS}${sysconfdir}/rc.common \
				${IMAGE_ROOTFS}${sysconfdir}/init.d/${srv} disable
		fi
	done

	if [ "${SWU_FACTORY_INITRAMFS_NETWORKING}" = "1" ] && \
	   [ "${SWU_FACTORY_INITRAMFS_LAN_DHCP_CLIENT}" = "1" ]; then
		${UCI} set network.lan.proto=dhcp
		${UCI} commit network
	fi
}

ROOTFS_POSTPROCESS_COMMAND += "swu_install_initramfs_modify;"

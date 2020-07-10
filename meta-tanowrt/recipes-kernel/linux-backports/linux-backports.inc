#
# This file Copyright (C) 2020 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
DESCRIPTION = "Linux Backports"
HOMEPAGE = "https://backports.wiki.kernel.org"
SECTION = "kernel/modules"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

INC_PR = "0"

FILESEXTRAPATHS_prepend = "${THISDIR}/${PN}/files:"
FILESEXTRAPATHS_prepend = "${THISDIR}/${PN}/configs:"

DEPENDS = "virtual/kernel coreutils-native flex-native bison-native"

# Base configuration
SRC_URI += "\
	file://config-base \
"

# Configurations
SRC_URI += "\
	file://config-ath10k \
	file://config-ath5k \
	file://config-ath6kl \
	file://config-ath9k \
	file://config-hwsim \
	file://config-iwlwifi \
	file://config-mt76 \
	file://config-ralink \
	file://config-rtlwifi \
	file://config-b43 \
	file://config-brcmfmac \
	file://config-brcmsmac \
"

inherit module-base

EXTRA_OEMAKE = "V=1 \
                KLIB_BUILD=${STAGING_KERNEL_BUILDDIR} \
                KLIB=${B} \
"

EXCLUDE_FROM_WORLD = "1"

# TODO: b43 (build failed)
LINUX_BACKPORTS_CONFIGS ?= "iwlwifi mt76 ralink rtlwifi brcmfmac brcmsmac"

do_configure() {
	rm -f ${S}/kconf/conf ${S}/kconf/*.o
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake -C kconf CC=${BUILD_CC} conf

	rm -f ${S}/.config
	cp ${WORKDIR}/config-base ${S}/.config
	for cfg in ${LINUX_BACKPORTS_CONFIGS}; do
		if [ -f ${WORKDIR}/config-$cfg ]; then
			cat ${WORKDIR}/config-$cfg >> ${S}/.config
		else
			bbwarn "Invalid config '${cfg}' (not exists) in LINUX_BACKPORTS_CONFIGS"
		fi
	done

	cp ${S}/.config ${S}/.config.orig

	oe_runmake oldconfig
}

do_compile() {
	oe_runmake
}

do_install() {
	install -d ${D}/lib/modules/${KERNEL_VERSION}/backports
	for ko in $(find ${S} -type f -name '*.ko')
	do
		install -m 0644 $ko ${D}/lib/modules/${KERNEL_VERSION}/backports/
	done
}

FILES_${PN} = " \
	/lib/modules/${KERNEL_VERSION}/backports/ \
"

do_configure[depends] += "\
	flex-native:do_populate_sysroot \
	bison-native:do_populate_sysroot \
	virtual/kernel:do_shared_workdir \
"

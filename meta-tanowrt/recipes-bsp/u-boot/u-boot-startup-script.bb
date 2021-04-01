#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
DEPENDS = "u-boot-mkimage-native m4-native"

PV = "1.0.0"
PR = "tano1"

do_compile[noexec] = "1"
do_install[noexec] = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "\
	file://defs.m4 \
	file://setup-common.m4 \
	file://setup-env.m4 \
	file://setup-overlays.m4 \
	file://setup-console.m4 \
	file://startup.m4 \
	file://startup-factory.m4 \
"

S = "${WORKDIR}"
B = "${WORKDIR}/build"

inherit deploy

BUILD_SCRIPTS ?= "\
	startup \
	${@bb.utils.contains('MACHINE_FEATURES', 'swupdate-factory', 'startup-factory', '', d)} \
"

do_mkimage[cleandirs] += "${B}"
do_mkimage () {
	install -m 0644 -t ${B} ${WORKDIR}/*.m4

	for script in ${BUILD_SCRIPTS}; do
		m4 -I${B} ${B}/${script}.m4 > ${B}/${script}.scr

		# Remove leading spaces
		# Remove trailing spaces
		# Remove blank lines
		# Remove comments
		sed -i -e 's/^[ \t]*//;s/[ \t]*$//;/^$/d;/^[\t\ ]*#/d;/\.*#.*/ {/[\x22\x27].*#.*[\x22\x27]/ !{:regular_loop s/\(.*\)*[^\]#.*/\1/;t regular_loop}; /[\x22\x27].*#.*[\x22\x27]/ {:special_loop s/\([\x22\x27].*#.*[^\x22\x27]\)#.*/\1/;t special_loop}; /\\#/ {:second_special_loop s/\(.*\\#.*[^\]\)#.*/\1/;t second_special_loop}}' \
			${B}/${script}.scr

		# DO NOT REMOVE version information from description!
		# This information is used for extracting startup script version
		# at system startup to prevent unnecessary updates
		sed -i '1s/^/# '${script}' version: ${PV}-${PR}\n/' \
			${B}/${script}.scr

		uboot-mkimage -A arm -O linux -T script -C none -a 0 -e 0 \
			-n "U-Boot startup script for ${MACHINE}" \
			-d ${B}/${script}.scr \
			${B}/${script}.img

		echo "${PV}-${PR}" > ${B}/${script}.img.version
	done
}

addtask mkimage after do_compile before do_install

do_deploy[cleandirs] += "${DEPLOYDIR}"
do_deploy () {
	install -d ${DEPLOYDIR}
	install -m 0644 -t ${DEPLOYDIR} ${B}/*.img
	install -m 0644 -t ${DEPLOYDIR} ${B}/*.img.version

	for script in ${BUILD_SCRIPTS}; do
		ln -sf ${script}.img ${DEPLOYDIR}/${script}-${MACHINE}.img
		ln -sf ${script}.img.version ${DEPLOYDIR}/${script}-${MACHINE}.img.version
	done
}

addtask deploy after do_install before do_build

#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021-2023 Tano Systems LLC. All Rights Reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
SUMMARY = "Baikal-M (BE-M1000) SCP communication driver"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=db0fccee3def547e17585ac94447d2fb"

inherit cmake module-base

BAIKAL_SCP_GIT_URI      ?= "git://github.com/tano-systems/baikal-scp-tool.git"
BAIKAL_SCP_GIT_BRANCH   ?= "master"
BAIKAL_SCP_GIT_PROTOCOL ?= "https"
BAIKAL_SCP_GIT_SRCREV   ?= "3db02d60abe55632d094dc0be09ba71aa23ef0af"

PV = "1.1.0+git${SRCPV}"
PR = "tano1"

SRC_URI = "${BAIKAL_SCP_GIT_URI};branch=${BAIKAL_SCP_GIT_BRANCH};protocol=${BAIKAL_SCP_GIT_PROTOCOL}"
SRCREV = "${BAIKAL_SCP_GIT_SRCREV}"
S = "${WORKDIR}/git"

PACKAGECONFIG ??= "libcurl"
PACKAGECONFIG[libcurl] = "-DUSE_LIBCURL=ON,-DUSE_LIBCURL=OFF,curl"

MODULES_INSTALL_TARGET ?= "modules_install"
MODULES_MODULE_SYMVERS_LOCATION = "../git/kernel"
MODULE_NAME = "baikal_scp"

do_configure[depends] += "virtual/kernel:do_shared_workdir"

EXTRA_OEMAKE += "KERNEL_SRC=${STAGING_KERNEL_DIR}"

do_compile_prepend() {
	# Compile kernel part
	cd ${S}/kernel
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake KERNEL_PATH=${STAGING_KERNEL_DIR}   \
		   KERNEL_VERSION=${KERNEL_VERSION}    \
		   CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
		   AR="${KERNEL_AR}" \
		   O=${STAGING_KERNEL_BUILDDIR} \
		   KBUILD_EXTRA_SYMBOLS="${KBUILD_EXTRA_SYMBOLS}" \
		   ${MAKE_TARGETS}
}

do_compile_append() {
	# Compile userspace part
	cd ${B} && cmake_runcmake_build --target ${OECMAKE_TARGET_COMPILE}
}

do_install_prepend() {
	cd ${S}/kernel
}

do_install_append() {
	# Install kernel module
	unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
	oe_runmake DEPMOD=echo MODLIB="${D}${nonarch_base_libdir}/modules/${KERNEL_VERSION}" \
	           INSTALL_FW_PATH="${D}${nonarch_base_libdir}/firmware" \
	           CC="${KERNEL_CC}" LD="${KERNEL_LD}" \
	           O=${STAGING_KERNEL_BUILDDIR} \
	           ${MODULES_INSTALL_TARGET}

	if [ ! -e "${B}/${MODULES_MODULE_SYMVERS_LOCATION}/Module.symvers" ] ; then
		bbwarn "Module.symvers not found in ${B}/${MODULES_MODULE_SYMVERS_LOCATION}"
		bbwarn "Please consider setting MODULES_MODULE_SYMVERS_LOCATION to a"
		bbwarn "directory below B to get correct inter-module dependencies"
	else
		install -Dm0644 "${B}/${MODULES_MODULE_SYMVERS_LOCATION}"/Module.symvers ${D}${includedir}/${BPN}/Module.symvers
		# Module.symvers contains absolute path to the build directory.
		# While it doesn't actually seem to matter which path is specified,
		# clear them out to avoid confusion
		sed -e 's:${B}/::g' -i ${D}${includedir}/${BPN}/Module.symvers
	fi

	# Install userspace part
	cd ${B} && DESTDIR='${D}' cmake_runcmake_build --target ${OECMAKE_TARGET_INSTALL}
}

PACKAGES += "${PN}-lib ${PN}-lib-dev"
PACKAGE_BEFORE_PN = "${PN}-tool"

FILES_SOLIBSDEV = ""
FILES_${PN} += "${base_libdir}/modules/${KERNEL_VERSION}/"

RRECOMMENDS_${PN} += "${PN}-lib"

SUMMARY_${PN}-lib = "Baikal-M (BE-M1000) SCP communication userspace library"
FILES_${PN}-lib += "${libdir}/*.so"
RDEPENDS_${PN}-lib += "${PN}"
RRECOMMENDS_${PN}-lib += "${PN}-tool"

SUMMARY_${PN}-lib-dev = "Baikal-M (BE-M1000) SCP communication userspace library dev headers"
FILES_${PN}-lib-dev += "${includedir}/"

SUMMARY_${PN}-tool = "Baikal-M (BE-M1000) SCP communication userspace utility"
FILES_${PN}-tool += "${sbindir}/"
RDEPENDS_${PN}-tool += "${PN}-lib"

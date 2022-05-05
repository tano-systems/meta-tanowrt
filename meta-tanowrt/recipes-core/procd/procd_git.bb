#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2018-2021 Anton Kikin <a.kikin@tano-systems.com>
#

PR = "tano57"
SUMMARY = "procd is the new OpenWrt process management daemon written in C"
DESCRIPTION = "procd is VIRTUAL-RUNTIME-init_manager"
HOMEPAGE = "http://wiki.openwrt.org/doc/techref/procd"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://procd.c;beginline=1;endline=13;md5=61e3657604f131a859b57a40f27a9d8e"
SECTION = "base"
DEPENDS = "libubox ubus json-c"
TOOLCHAIN = "gcc"

PACKAGE_ARCH = "${MACHINE_ARCH}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${BPN}/patches:${THISDIR}/${BPN}/files:"

SRC_URI = "\
	git://${GIT_OPENWRT_ORG}/project/procd.git;branch=master \
	file://reload_config \
	file://procd.sh \
	file://uxc.init \
"

# Patches
SRC_URI += "\
	file://0001-service-allow-to-change-service-s-current-directory.patch \
	file://0002-service-Allow-to-configure-scheduler-attributes.patch \
	file://0003-hotplug-Remove-dev-prefix-from-DEVNAME-variable.patch \
	file://0004-hotplug-Completely-remove-hotplug-functionality.patch \
	file://0005-service-Add-SCHED_IDLE-scheduler-policy-support.patch \
	file://0006-rcS-Add-psplash-support.patch \
	file://0007-early-Use-devtmpfs-instead-of-tmpfs-for-dev.patch \
	file://0008-instance-Improve-error-messages.patch \
	file://0009-inittab-Handle-multiple-consoles-in-proc-cmdline.patch \
	file://0010-instance-Fix-64-bit-compilation-issues.patch \
	file://0011-system-Add-tm_gmtoff-to-system.info-ubus-call.patch \
	file://0012-system-Handle-DISTRIB_TIMESTAMP-in-etc-openwrt_relea.patch \
	file://0013-uxc-Fix-compiler-warnings.patch \
	file://0014-build-Fix-building-in-OE-environment.patch \
	file://0015-jail-Fixes-for-64-bit-systems.patch \
	file://0016-jail-Add-libnss_-libs-to-mounts-when-using-glibc.patch \
	file://0017-jail-load_ldso_conf-Log-glob-failure-as-info-instead.patch \
	file://0018-early-Mount-CGv1-to-tmp-cgroup-v1.patch \
	file://0019-rcS-Display-only-script-name-in-psplash-instead-of-f.patch \
	file://0020-ujail-Fix-loading-ELF-with-multiple-LOAD-segments.patch \
	file://0021-jail-Early-call-to-get_jail_user.patch \
	file://0022-system-Add-optional-delay-parameter-to-system.reboot.patch \
	file://0023-procd-Add-shared-and-slab-to-memory-table.patch \
	file://0024-Fix-compilation-with-GCC-10.patch \
"

PACKAGECONFIG ??= "\
	${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'psplash psplash-script-msg', '', d)} \
	${@bb.utils.contains('MACHINE_FEATURES', 'seccomp', 'seccomp', '' , d)} \
	ujail \
"

PACKAGECONFIG[psplash] = "-DPSPLASH_SUPPORT=1,,"
PACKAGECONFIG[psplash-script-msg] = "-DPSPLASH_SCRIPT_MSG=1,,"
PACKAGECONFIG[uxc] = ",,"
PACKAGECONFIG[ujail] = "-DJAIL_SUPPORT=1,,"
PACKAGECONFIG[seccomp] = "-DSECCOMP_SUPPORT=1 -DUTRACE_SUPPORT=1,,"

# 05.03.2021
# inittab: detect active console from kernel if no console= specified
SRCREV = "2cfc26f8456a4d5ba3836c914a742f3d00bad781"

S = "${WORKDIR}/git"

REQUIRED_DISTRO_FEATURES = "procd"
CONFLICT_DISTRO_FEATURES = "sysvinit systemd"
inherit features_check

inherit cmake pkgconfig

SRCREV_openwrt = "${OPENWRT_SRCREV}"

do_unpack[vardeps] += "libdir"
do_unpack[vardeps] += "base_libdir"

do_configure_prepend () {
	if [ -e "${S}/CMakeLists.txt" ] ; then
		sed -i -e "s:ARCHIVE DESTINATION lib:ARCHIVE DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       -e "s:LIBRARY DESTINATION lib:LIBRARY DESTINATION \${CMAKE_INSTALL_LIBDIR}:g" \
		       ${S}/CMakeLists.txt
	fi

	if [ "${libdir}" != "/usr/lib" ]; then
		if [ -e "${S}/jail/elf.c" ] ; then
			sed -i -e "s:alloc_library_path(\"/usr/lib\");:alloc_library_path(\"${libdir}\");:g" \
			       ${S}/jail/elf.c
		fi
	fi

	if [ "${base_libdir}" != "/lib" ]; then
		if [ -e "${S}/service/instance.c" ] ; then
			sed -i -e "s:/lib/libsetlbf.so:${base_libdir}/libsetlbf.so:g" \
			       ${S}/service/instance.c
		fi

		if [ -e "${S}/jail/jail.c" ] ; then
			sed -i -e "s:/lib/libnss_:${base_libdir}/libnss_:g" \
			       ${S}/jail/jail.c
		fi

		if [ -e "${S}/trace/trace.c" ] ; then
			sed -i -e "s:/lib/libpreload:${base_libdir}/libpreload:g" \
			       ${S}/trace/trace.c
		fi
	fi
}

do_install_append() {
	install -d ${D}${base_sbindir}
	install -m 0755 ${WORKDIR}/reload_config ${D}${base_sbindir}/

	install -d ${D}${sysconfdir}

	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/uxc.init ${D}${sysconfdir}/init.d/uxc

	install -d ${D}${nonarch_base_libdir}/functions
	install -m 0755 ${WORKDIR}/procd.sh ${D}${nonarch_base_libdir}/functions/

	# Make sure things are where they are expected to be
	install -dm 0755 ${D}/sbin ${D}/lib
	ln -s /usr/sbin/procd ${D}/sbin/procd
	ln -s /usr/sbin/init ${D}/sbin/init
	ln -s /usr/sbin/askfirst ${D}/sbin/askfirst
	ln -s /usr/sbin/udevtrigger ${D}/sbin/udevtrigger
	ln -s /usr/sbin/upgraded ${D}/sbin/upgraded

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'ujail', '1', '0', d)}" = "1" ]; then
		ln -s /usr/sbin/ujail ${D}/sbin/ujail
		ln -s /usr/sbin/ujail-console ${D}/sbin/ujail-console
	fi

	install -d ${D}${base_libdir}

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'seccomp', '1', '0', d)}" = "1" ]; then
		mv ${D}${libdir}/libpreload-seccomp.so ${D}${base_libdir}/libpreload-seccomp.so
		mv ${D}${libdir}/libpreload-trace.so ${D}${base_libdir}/libpreload-trace.so
		ln -s /usr/sbin/utrace ${D}/sbin/utrace
		ln -s utrace ${D}/sbin/seccomp-trace
	fi

	mv ${D}${libdir}/libsetlbf.so ${D}${base_libdir}/libsetlbf.so
	rmdir ${D}${libdir}

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'uxc', '1', '0', d)}" = "0" ]; then
		rm -f ${D}${sbindir}/uxc
		rm -f ${D}${sysconfdir}/init.d/uxc
	fi

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'ujail', '1', '0', d)}" = "0" ]; then
		rm -f ${D}${sbindir}/ujail
		rm -f ${D}/sbin/ujail

		rm -f ${D}${sbindir}/ujail-console
		rm -f ${D}/sbin/ujail-console
	fi
}

RDEPENDS_${PN} += "\
	fstools \
	base-files-scripts-openwrt \
	${PN}-inittab \
"

RRECOMMENDS_${PN} += "\
	udev \
"

FILES_${PN} = "/"
FILES_SOLIBSDEV = ""

CONFFILES_${PN}_append = "\
	/etc/uxc \
"

BBCLASSEXTEND = "native nativesdk"

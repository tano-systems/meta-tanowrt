PR_append = ".tano1"

#
# Fix for warning:
# WARNING: external-linaro-toolchain-2017.11-r0.arago36.tano1 do_populate_lic:
#          ${COREBASE}/LICENSE is not a valid license file, please use
#          '${COMMON_LICENSE_DIR}/MIT' for a MIT License file in LIC_FILES_CHKSUM.
#          This will become an error in the future
#
LIC_FILES_CHKSUM_remove = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"
LIC_FILES_CHKSUM += "\
	file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302\
"

INHIBIT_PACKAGE_STRIP = "0"

INSANE_SKIP_libstdc++-dbg += "ldflags"
INSANE_SKIP_libstdc++-dbg += "staticdev"
INSANE_SKIP_libgcc-dev += "staticdev"

PROVIDES += "\
	libcilkrts \
	libcilkrts-dev \
	libcilkrts-staticdev \
"

PACKAGES += "\
	libcilkrts \
	libcilkrts-dev \
	libcilkrts-staticdev \
"

INSANE_SKIP_libcilkrts += "ldflags"
PKGV_libcilkrts = "${ELT_VER_GCC}"
PKGV_libcilkrts-dev = "${ELT_VER_GCC}"
PKGV_libcilkrts-staticdev = "${ELT_VER_GCC}"

FILES_libcilkrts += "${base_libdir}/libcilkrts*.so.*"
FILES_libcilkrts-dev += "${base_libdir}/libcilkrts*.so ${base_libdir}/libcilkrts*.spec"
FILES_libcilkrts-staticdev = "${base_libdir}/libcilkrts*.a"

do_install_append() {
	rm -f ${D}${base_libdir}/libcilkrts.so
}

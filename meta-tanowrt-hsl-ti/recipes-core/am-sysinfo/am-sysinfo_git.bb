SUMMARY = "AM SysInfo"
HOMEPAGE = "https://gforge.ti.com/gf/project/am_sysinfo/"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://mem_util/mem_util.c;beginline=1;endline=37;md5=8aa8e714ab729cfe8177298af8a5a25d"

SECTION = "system"

PR = "r10"

BRANCH ?= "master"
SRCREV = "5df7da69a50d27b7f594db0918c5e6793c3a6237"

SRC_URI = "git://git.ti.com/apps/am_sysinfo.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

do_compile() {
	export CROSS_COMPILE=${TARGET_PREFIX}
	export CFLAGS='${TARGET_CC_ARCH}'
	# build the release version
	oe_runmake release CC="${CC}"
}

do_install() {
	install -d ${D}/${bindir}
	install -m 0755 ${S}/mem_util/Release/mem_util ${D}/${bindir}
}

INSANE_SKIP_${PN} = "ldflags"

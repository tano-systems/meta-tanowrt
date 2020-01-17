DESCRIPTION = "Ethernet Switch configuration management"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://switch-config.c;beginline=1;endline=14;md5=659ff9658cbaba3110b81804af60de75"

PV = "2.0"
PR = "r4"

BRANCH ?= "v4.1"
BRANCH_aarch64 ?= "am65x-v1.0"

SRCREV = "7efabd92177199205808ebd993267b04e8c2fb6a"
SRCREV_aarch64 = "3b31ce5fe8bd5a3560b89f2a709fb3e3b877326d"

SRC_URI = "git://git.ti.com/switch-config/switch-config.git;protocol=git;branch=${BRANCH}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "CROSS_COMPILE=${TARGET_PREFIX} CC="${CC}""

do_configure() {
	sed 's|-I$(KBUILD_OUTPUT)/usr/include|${TOOLCHAIN_OPTIONS} -I.|' -i ${S}/Makefile
}

do_install() {
	install -d ${D}${bindir}/
	install -c -m 755 ${S}/switch-config ${D}${bindir}/switch-config
}

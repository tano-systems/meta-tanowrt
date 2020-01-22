#
# modpoll is a command line based Modbus master simulator and test utility.
# modpoll is using the FieldTalk Modbus driver.
#
# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

SUMMARY = "FieldTalk Modbus Master Simulator"
HOMEPAGE = "https://www.modbusdriver.com/modpoll.html"
LICENSE = "proconX-LICENSE-FREE"
LIC_FILES_CHKSUM = "file://LICENSE-FREE.txt;md5=05018d6237f3f611b1b4be0f65fb2a59"

COMPATIBLE_HOST = "(i.86.*|x86_64.*|arm.*)-linux.*"

SRC_URI = "https://www.modbusdriver.com/downloads/modpoll.tgz"
SRC_URI[md5sum] = "9f2678f74dd4976a1fb58895f5144a79"
SRC_URI[sha256sum] = "9742d87f30a7ab59a391b9afd15d3257efcf6118679bd15bbeee2ecf8d6bb642"

S = "${WORKDIR}/modpoll"

INSANE_SKIP_${PN}_append = "already-stripped"

ARCH_DIR_x86-64 = "linux_x86-64"
ARCH_DIR_i586 = "linux_i386"
ARCH_DIR_i686 = "linux_i386"
ARCH_DIR_arm = "linux_arm-eabihf"

do_install () {
	install -m 0755 -d ${D}${bindir}
	install -m 0755 ${S}/${ARCH_DIR}/modpoll ${D}${bindir}/modpoll
}

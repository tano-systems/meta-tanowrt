#
# diagslave is a simple command line based Modbus slave
# simulator and test utility. diagslave is using the FieldTalk Modbus driver.
#
# This file Copyright (C) 2019 Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

SUMMARY = "FieldTalk Modbus Diagnostic Slave Simulator"
HOMEPAGE = "https://www.modbusdriver.com/diagslave.html"
LICENSE = "proconX-LICENSE-FREE"
LIC_FILES_CHKSUM = "file://LICENSE-FREE.txt;md5=05018d6237f3f611b1b4be0f65fb2a59"

COMPATIBLE_HOST = "(i.86.*|x86_64.*|arm.*)-linux.*"

SRC_URI = "https://www.modbusdriver.com/downloads/diagslave.tgz"
SRC_URI[md5sum] = "155c10ac9c4b35c6f9061a722b9e5aa1"
SRC_URI[sha256sum] = "a8ba4b09055e92dc7307d43a2f885c0ded90b81ba843ead5e530fd1911ee3c45"

S = "${WORKDIR}/diagslave"

INSANE_SKIP_${PN}_append = "already-stripped"

ARCH_DIR_x86-64 = "linux_x86-64"
ARCH_DIR_i586 = "linux_i386"
ARCH_DIR_i686 = "linux_i386"
ARCH_DIR_arm = "linux_arm-eabihf"

do_install () {
	install -m 0755 -d ${D}${bindir}
	install -m 0755 ${S}/${ARCH_DIR}/diagslave ${D}${bindir}/diagslave
}

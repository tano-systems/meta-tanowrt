require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

PR = "tano0"

DEPENDS += "bison-native flex-native"

KERNEL_GIT_URI = "git://github.com/openil/linux.git;nobranch=1"
KERNEL_GIT_PROTOCOL = "git"
SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL}"
SRCREV = "OpenIL-v1.7-linux-202001"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

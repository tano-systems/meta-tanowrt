# Copyright (C) 2016 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "Standalone fork of Android's make_ext4fs utility"
HOMEPAGE = "https://git.openwrt.org/?p=project/make_ext4fs.git;a=summary"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://NOTICE;md5=bb2810bf31da2f6bb39e0bfa86091da3"
SECTION = "tools"
DEPENDS = "zlib"
PR = "tano2"

SRC_URI = "git://${GIT_OPENWRT_ORG}/project/make_ext4fs.git \
          "

# 29.05.2017
# make_ext4: Add strict prototypes. 
SRCREV = "eebda1d55d9701ace2700d7ae461697fadf52d1f"

S = "${WORKDIR}/git"
B = "${S}"

CFLAGS += "-I${S}/include -I${S}/libsparse/include"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	install -Dm 0755 ${B}/make_ext4fs ${D}${bindir}/make_ext4fs
}

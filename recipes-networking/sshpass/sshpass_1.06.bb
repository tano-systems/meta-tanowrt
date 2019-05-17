# Recipe taken from meta-odroid layer

DESCRIPTION = "Non-interactive ssh password auth"
HOMEPAGE = "http://sshpass.sourceforge.net/"
SECTION = "console/network"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=94d55d512a9ba36caa9b7df079bae19f"

SRC_URI = "${SOURCEFORGE_MIRROR}/sshpass/sshpass-${PV}.tar.gz"

SRC_URI[md5sum] = "f59695e3b9761fb51be7d795819421f9"
SRC_URI[sha256sum] = "c6324fcee608b99a58f9870157dfa754837f8c48be3df0f5e2f3accf145dee60"

inherit autotools

BBCLASSEXTEND += "native nativesdk"

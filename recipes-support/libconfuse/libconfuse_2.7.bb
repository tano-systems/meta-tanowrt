DESCRIPTION = "Library for parsing configuration files."
HOMEPAGE = "http://www.nongnu.org/confuse/"
SECTION = "libs"
LICENSE = "ISC"
LIC_FILES_CHKSUM = "file://NEWS;md5=18e885b42b86362019e0cab8dc2a393b"

SRC_URI = "http://download.savannah.gnu.org/releases/confuse/confuse-${PV}.tar.gz \
          "
SRC_URI[md5sum] = "45932fdeeccbb9ef4228f1c1a25e9c8f"
SRC_URI[sha256sum] = "e32574fd837e950778dac7ade40787dd2259ef8e28acd6ede6847ca895c88778"

inherit autotools binconfig pkgconfig lib_package gettext

EXTRA_OECONF = "--enable-shared"

BBCLASSEXTEND = "native"

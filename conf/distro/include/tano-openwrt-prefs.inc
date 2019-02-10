# Original file from meta-arago-distro

# Set preferred Qt5 versions. Different namespace from Qt4, so no conflict
include conf/distro/include/qt5-versions.inc

PREFERRED_VERSION_iptables = "1.6.2"
PREFERRED_VERSION_usbutils = "007"

PREFERRED_PROVIDER_os-release = "tano-version"

PREFERRED_PROVIDER_virtual/kernel = "linux-yocto-rt"

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"

# Setting PREFERRED_VERSIONS due to selecting a specific version of a library or
# application that does not have a GPLv3 license
PREFERRED_VERSION_gdbm = "1.13"
PREFERRED_VERSION_libnl = "3.2.29"
PREFERRED_VERSION_crda = "3.18"
PREFERRED_VERSION_obex = "0.34"
PREFERRED_VERSION_bash = "4.4"
PREFERRED_VERSION_gettext = "0.19.8.1"
PREFERRED_VERSION_elfutils = "0.170"

PREFERRED_VERSION_libwebsockets = "2.4.2%"


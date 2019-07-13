# Set preferred Qt5 versions. Different namespace from Qt4, so no conflict
include conf/distro/include/qt5-versions.inc

# Versions
PREFERRED_VERSION_iptables = "1.6.2"
PREFERRED_VERSION_usbutils = "007"
PREFERRED_VERSION_libwebsockets = "2.4.2%"

# Providers
PREFERRED_PROVIDER_psplash = "psplash-tn"
PREFERRED_PROVIDER_os-release = "tano-version"
PREFERRED_PROVIDER_virtual/kernel = "linux-yocto-rt"
PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"

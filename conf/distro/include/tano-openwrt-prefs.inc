# Original file from meta-arago-distro

# Set preferred Qt5 versions. Different namespace from Qt4, so no conflict
include conf/distro/include/qt5-versions.inc

PREFERRED_VERSION_iptables = "1.6.2"
PREFERRED_VERSION_usbutils = "007"

# Use the latest version of ltp-ddt for kernel versions 3.8+
PREFERRED_PROVIDER_ltp-ddt = "ltp-ddt"

PREFERRED_PROVIDER_virtual/gettext = "gettext"

PREFERRED_PROVIDER_virtual/egl = "ti-sgx-ddk-um"
PREFERRED_PROVIDER_virtual/libgles1 = "ti-sgx-ddk-um"
PREFERRED_PROVIDER_virtual/libgles2 = "ti-sgx-ddk-um"
PREFERRED_PROVIDER_libgbm = "libgbm"
PREFERRED_PROVIDER_libgbm-dev = "libgbm-dev"

PREFERRED_PROVIDER_virtual/libgl = "mesa-gl"

# This is required when using keystone devices with graphical support
# such as Qt5
PREFERRED_PROVIDER_virtual/egl_keystone = "mesa"
PREFERRED_PROVIDER_virtual/libgles1_keystone = "mesa"
PREFERRED_PROVIDER_virtual/libgles2_keystone = "mesa"
PREFERRED_PROVIDER_virtual/libgl_keystone = "mesa"
PREFERRED_PROVIDER_virtual/mesa_keystone = "mesa"

PREFERRED_PROVIDER_virtual/kernel = "linux-ti-staging${ARAGO_KERNEL_SUFFIX}"

# For TI devices with only baremetal/TI RTOS support
PREFERRED_PROVIDER_virtual/kernel_c66x = "linux-dummy"
PREFERRED_PROVIDER_virtual/kernel_omapl137 = "linux-dummy"

PREFERRED_PROVIDER_jpeg = "libjpeg-turbo"

# Setting PREFERRED_VERSIONS due to selecting a specific version of a library or
# application that does not have a GPLv3 license
PREFERRED_VERSION_gdbm = "1.8.3"
PREFERRED_VERSION_libnl = "3.2.28"
PREFERRED_VERSION_crda = "3.18"
PREFERRED_VERSION_obex = "0.34"
PREFERRED_VERSION_bash = "3.2.57"
PREFERRED_VERSION_gettext = "0.16.1"
PREFERRED_VERSION_elfutils = "0.148"

PREFERRED_PROVIDER_libnl-xfrm = "libnl"

PREFERRED_PROVIDER_wpa-supplicant = "wpa-supplicant"
PREFERRED_PROVIDER_wpa-supplicant_ti33x = "wpa-supplicant-wl18xx"
PREFERRED_PROVIDER_wpa-supplicant_ti43x = "wpa-supplicant-wl18xx"
PREFERRED_PROVIDER_wpa-supplicant_omap-a15 = "wpa-supplicant-wl18xx"

PREFERRED_VERSION_gstreamer1.0 = "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-ugly = "1.8.3"
PREFERRED_VERSION_gstreamer1.0-libav = "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-good = "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-bad = "1.8.3"
PREFERRED_VERSION_gstreamer1.0-plugins-base = "1.8.3"

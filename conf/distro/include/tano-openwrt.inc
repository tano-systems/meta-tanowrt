require conf/distro/include/tano-openwrt-toolchain.inc
require conf/distro/include/tano-openwrt-sdk.inc

IMAGE_CLASSES += "openwrt-image-types"

# Virtual runtimes
VIRTUAL-RUNTIME_dev_manager = "procd"
VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "procd"
VIRTUAL-RUNTIME_kmod_manager = "ubox"
VIRTUAL-RUNTIME_syslog = "ubox"
VIRTUAL-RUNTIME_base-utils = "busybox"
VIRTUAL-RUNTIME_network_manager = "netifd"

# Start with default set of distro features
DISTRO_FEATURES = "${DISTRO_FEATURES_DEFAULT} ${DISTRO_FEATURES_LIBC}"

# Set procd as init manager
DISTRO_FEATURES_append = " procd"

# Remove sysvinit and systemd from DISTRO_FEATURES
DISTRO_FEATURES_BACKFILL_CONSIDERED_append = " sysvinit systemd"

# Clean DISTRO_FEATURES
DISTRO_FEATURES_remove = "\
    wayland \
    wifi \
    pci \
    3g \
    nfc \
    pulseaudio \
    bluetooth \
    irda \
    pcmcia \
    bluez5 \
    largefile \
    sysvinit \
    systemd \
    pam \
    x11 \
    opengl \
"

# Rootfs customization
ROOTFS_RO_UNNEEDED = "update-rc.d ${ROOTFS_BOOTSTRAP_INSTALL}"
FORCE_RO_REMOVE = "1"

# Licenses
LICENSE_FLAGS_WHITELIST = "commercial_gst-ffmpeg commercial_gstreamer1.0-libav"

# Disable polkit's systemd configuration from meta-oe
POLKITAUTH = ""

# Set ENTERPRISE_DISTRO flag to ensure that recipes that have been flagged
# do not attempt to enable features that have potential commercial
# licensing issues.
ENTERPRISE_DISTRO = "1"

# Image
IMAGE_FSTYPES_remove = "tar.xz.md5"

# Root home path
ROOT_HOME ??= "/root"

# Set preferred Qt version
QT_PROVIDER ?= "qt5"

require conf/distro/include/tano-openwrt-packageconfig.inc
require conf/distro/include/tano-openwrt-prefs.inc
require conf/distro/include/tano-openwrt-srcipk.inc
require conf/distro/include/tano-openwrt-repairs.inc
require conf/distro/include/tano-openwrt-arago.inc

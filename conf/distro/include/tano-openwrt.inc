INHERIT += " openwrt-kmods "

PREFERRED_VERSION_iptables = "1.6.2"
PREFERRED_VERSION_usbutils = "007"

# Virtual runtimes
VIRTUAL-RUNTIME_dev_manager = "procd"
VIRTUAL-RUNTIME_login_manager = "busybox"
VIRTUAL-RUNTIME_init_manager = "procd"
VIRTUAL-RUNTIME_kmod_manager = "ubox"
VIRTUAL-RUNTIME_syslog = "ubox"
VIRTUAL-RUNTIME_base-utils = "busybox"
VIRTUAL-RUNTIME_network_manager = "netifd"

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
"
# Rootfs customization
ROOTFS_RO_UNNEEDED = "update-rc.d ${ROOTFS_BOOTSTRAP_INSTALL}"
FORCE_RO_REMOVE = "1"

# Licenses
LICENSE_FLAGS_WHITELIST = "commercial_gst-ffmpeg commercial_gstreamer1.0-libav"

# Image
IMAGE_FSTYPES_remove = "tar.xz.md5"

require conf/distro/include/tano-openwrt-repairs.inc


PACKAGECONFIG_pn-libwebsockets = "\
	client \
	server \
	ssl \
	libuv \
	${@bb.utils.filter('ipv6', '${DISTRO_FEATURES}', d)} \
"

# Distro-specific package configuration
PACKAGECONFIG_pn-weston = "egl kms launch clients"
PACKAGECONFIG_append_pn-systemd = " coredump"

# Configure Qt5 flags (GLES is still required for qtdeclarative and qtwebkit)
PACKAGECONFIG_GL_pn-qtbase = "gles2 linuxfb"
PACKAGECONFIG_DISTRO_pn-qtbase = "icu examples accessibility"
PACKAGECONFIG_FONTS_pn-qtbase = "fontconfig"

# Add gstreamer in package configuration to enable video
PACKAGECONFIG_append_pn-qtmultimedia  = " gstreamer"

# Prefer OpenSSL over GnuTLS for curl due to TSU exemptions
PACKAGECONFIG_pn-curl = "${@bb.utils.contains("DISTRO_FEATURES", "ipv6", "ipv6", "", d)} ssl zlib"

# No GFX on Keystone
PACKAGECONFIG_remove_pn-gstreamer1.0-plugins-bad_keystone = "wayland egl gles2"

PACKAGECONFIG_pn-glmark2 = "drm-gles2 wayland-gles2"

# Enable UI and scripting for perf
PERF_FEATURES_ENABLE ?= "perf-scripting perf-tui"

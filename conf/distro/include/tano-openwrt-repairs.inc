#
# Repairs for "No recipes available for" warnings
#

# Current version is 1.12.2
BBMASK += "gstreamer1.0-plugins-base_1.8.3.bbappend"

# Current version is 3.5.3
BBMASK += "python3-native_3.5.2.bbappend"

# Current version is 2.0.0
BBMASK += "weston_1..0.bbappend"

# Current version is 4.9%, 4.14 is from rocko
BBMASK += "linux-ti-staging_4.14.bbappend"

BBMASK += "gnome-settings-daemon_2.32.1.bbappend"

# Fixes
RDEPENDS_tzcode += "bash"
RDEPENDS_ldd += "bash"

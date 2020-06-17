inherit kernel-config

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR_append = "${@'.rpi0' if d.getVar('WIREGUARD_PATCH_RPI_4_19') == '1' else ''}"
SRC_URI_append = "${@' file://0001-Fix-compilation-for-RPi-kernel-4.19.patch;pnum=2 ' if d.getVar('WIREGUARD_PATCH_RPI_4_19') == '1' else ''}"

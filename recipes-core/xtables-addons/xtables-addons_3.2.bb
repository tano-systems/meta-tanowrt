PV = "3.2"
PR = "tano1.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "\
	https://fossies.org/linux/privat/${BPN}-${PV}.tar.xz \
	file://100-add-rtsp-conntrack.patch \
	file://200-add-lua-packetscript.patch \
	file://201-fix-lua-packetscript.patch \
	file://202-add-lua-autoconf.patch \
	file://400-fix-IFF_LOWER_UP-musl.patch \
	file://fix-to-build-linux-v4.15-and-later.patch \
	file://0001-Unset-LDFLAGS-for-kernel-modules.patch \
"

SRC_URI[md5sum] = "80ea89ba8d5a001a8d71c7f05b2f0141"
SRC_URI[sha256sum] = "006f4e38bbf4b9a9069b90ca78c93b65800e9ebfd17332b713f1f80292420aaa"

S = "${WORKDIR}/xtables-addons-${PV}"

require xtables-addons.inc

# Copyright (C) 2015 Khem Raj <raj.khem@gmail.com>
# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".tano10"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "file://udev.init file://local.rules file://openwrt-hotplug.rules"

inherit openwrt-services
inherit useradd

RRECOMMENDS_${PN}_remove = "eudev-hwdb"

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM_${PN} = "\
    -f -r tty; \
    -f -r dialout; \
    -f -r kmem; \
    -f -r input; \
    -f -r video; \
    -f -r lp; \
    -f -r disk; \
    -f -r cdrom; \
    -f -r tape; \
    -f -r floppy; \
    -f -r kvm;"

OPENWRT_SERVICE_PACKAGES = "eudev"
OPENWRT_SERVICE_SCRIPTS_eudev += "udev"
OPENWRT_SERVICE_STATE_eudev-udev ?= "enabled"

PACKAGE_ARCH = "${MACHINE_ARCH}"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d
	install -m 0755 ${WORKDIR}/udev.init ${D}${sysconfdir}/init.d/udev

	install -d ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/local.rules ${D}${sysconfdir}/udev/rules.d
	install -m 0644 ${WORKDIR}/openwrt-hotplug.rules ${D}${sysconfdir}/udev/rules.d

	# Remove rules for joystick
	rm -rf ${D}${base_libdir}/udev/rules.d/70-joystick.rules

	# Remove rules for CDROM
	rm -rf ${D}${base_libdir}/udev/rules.d/60-cdrom_id.rules
	rm -rf ${D}${base_libdir}/udev/cdrom_id

	if [ "${@bb.utils.contains('MACHINE_FEATURES', 'screen', 'yes', 'no', d)}" = "no" ]; then
		# Remove video rules
		rm -rf ${D}${base_libdir}/udev/rules.d/60-persistent-v4l.rules
		rm -rf ${D}${base_libdir}/udev/v4l_id
	fi

	# Remove sound rules
	if [ "${@bb.utils.contains('MACHINE_FEATURES', 'sound', 'yes', 'no', d)}" = "no" ]; then
		rm -rf ${D}${base_libdir}/udev/rules.d/60-persistent-alsa.rules
		rm -rf ${D}${base_libdir}/udev/rules.d/78-sound-card.rules
	fi
}

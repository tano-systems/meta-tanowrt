# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

PR = "tano1"
SUMMARY = "MQTT support packages"
DESCRIPTION = "The set of packages required for a MQTT support"
LICENSE = "MIT"

PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit packagegroup

RDEPENDS_${PN} = "\
	mosquitto \
"

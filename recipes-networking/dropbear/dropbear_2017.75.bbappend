# This file Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".tano3.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}_${PV}/files:${THISDIR}/${PN}_${PV}/patches:"

require ${PN}-openwrt.inc

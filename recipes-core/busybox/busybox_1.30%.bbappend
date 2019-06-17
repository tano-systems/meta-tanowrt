# This file Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".tano0.${INC_PR}"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:"

require busybox-openwrt.inc

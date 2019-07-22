#
# Copyright (C) 2015 Wind River Systems, Inc.
# Copyright (C) 2019 Tano Systems, Ltd.
#

# commit tagged as version 1.6.0
#
SRCREV = "b47eea84cbb93f533b0cba2f1aaf9ca4da8706b9"
PV = "1.6.0"
PR = "tano0.${INC_PR}"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI = "git://github.com/Irqbalance/irqbalance"

S = "${WORKDIR}/git"

require irqbalance.inc

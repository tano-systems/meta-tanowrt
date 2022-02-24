#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020-2022, Tano Systems LLC. All rights reserved.
#
PR_append = ".swi2"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/files:${THISDIR}/${PN}/patches:"

SRC_URI += "\
	file://0001-Revert-system-linux-expose-hw-tc-offload-ethtool-fea.patch \
	file://0002-Revert-system-linux-add-wrapper-function-for-creatin.patch \
	file://0003-Revert-system-linux-delete-bridge-devices-using-netl.patch \
	file://0004-Revert-system-linux-create-bridge-devices-using-netl.patch \
	file://0005-Revert-iprule-add-support-for-uidrange.patch \
"

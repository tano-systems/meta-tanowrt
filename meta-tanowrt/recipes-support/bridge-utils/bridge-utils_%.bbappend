#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano1"

do_install:append() {
	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_network_manager', 'netifd', '1', '0', d)}" = "1" ]; then
		rm -rf ${D}/${sysconfdir}/network
	fi
}

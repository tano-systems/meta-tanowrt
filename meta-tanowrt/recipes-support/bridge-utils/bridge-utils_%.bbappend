#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2021, 2023 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano2"

SRC_URI_remove =  "git://git.kernel.org/pub/scm/linux/kernel/git/shemminger/bridge-utils.git"
SRC_URI_append = " git://git.kernel.org/pub/scm/linux/kernel/git/shemminger/bridge-utils.git;branch=main"

do_install_append() {
	if [ "${@bb.utils.contains('VIRTUAL-RUNTIME_network_manager', 'netifd', '1', '0', d)}" = "1" ]; then
		rm -rf ${D}/${sysconfdir}/network
	fi
}

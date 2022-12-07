#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#

# Note that VIRTUAL-RUNTIME_network_manager nor VIRTUAL-RUNTIME_syslog
# are essential for booting a standalone system so not included here.

PR:append = ".tano2"

RDEPENDS:${PN}:append = "\
	${VIRTUAL-RUNTIME_kmod_manager} \
"

RDEPENDS:${PN}:remove = "\
	${VIRTUAL-RUNTIME_dev_manager} \
	${EFI_PROVIDER} \
"

RRECOMMENDS:${PN}:append = "\
	kernel-modules \
	${VIRTUAL-RUNTIME_dev_manager} \
"

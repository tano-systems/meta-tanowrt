#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#

# Note that VIRTUAL-RUNTIME_network_manager nor VIRTUAL-RUNTIME_syslog
# are essential for booting a standalone system so not included here.

PR_append = ".tano1"

RDEPENDS_${PN}_append = "\
	${VIRTUAL-RUNTIME_kmod_manager} \
"

RDEPENDS_${PN}_remove = "\
	${VIRTUAL-RUNTIME_dev_manager} \
"

RRECOMMENDS_${PN}_append = "\
	kernel-modules \
	${VIRTUAL-RUNTIME_dev_manager} \
"

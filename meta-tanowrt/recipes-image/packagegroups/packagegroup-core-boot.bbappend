#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2018 Daniel Dickinson <cshored@thecshore.com>
#

# Note that VIRTUAL-RUNTIME_network_manager nor VIRTUAL-RUNTIME_syslog
# are essential for booting a standalone system so not included here.

PR_append = ".tano0"

RDEPENDS_${PN}_append = "\
	kernel-modules \
	${VIRTUAL-RUNTIME_kmod_manager} \
"

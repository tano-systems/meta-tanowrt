#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".swi0"

RDEPENDS:${PN}:remove = "\
	net-snmp-client \
	net-snmp-mibs \
"

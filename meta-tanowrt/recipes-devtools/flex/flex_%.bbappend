#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PR:append = ".tano0"

do_install:append() {
	# symlink /usr/bin/lex to /usr/bin/flex
	ln -s flex ${D}/${bindir}/lex
}

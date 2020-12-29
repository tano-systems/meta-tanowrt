#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PR_append = ".tano0"

do_install_append() {
	# symlink /usr/bin/lex to /usr/bin/flex
	ln -s flex ${D}/${bindir}/lex
}

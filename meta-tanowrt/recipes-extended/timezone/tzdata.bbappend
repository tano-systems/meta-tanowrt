#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#
PR:append = ".tano2"

RDEPENDS:${PN}:remove = "tzdata-misc"
RDEPENDS:${PN}:remove = "tzdata-right"
RDEPENDS:${PN}:remove = "tzdata-posix"

pkg_postinst:${PN}:append() {
	if [ -L ${etc_lt} ] ; then
		rm -f "${etc_lt}"
	fi

	ln -sf /tmp/localtime ${etc_lt}
}

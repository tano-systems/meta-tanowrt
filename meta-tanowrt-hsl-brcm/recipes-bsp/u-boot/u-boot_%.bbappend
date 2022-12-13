#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
PR:append = ".rpi0"

do_install:append:rpi() {
	rm -f ${D}${sysconfdir}/fw_env.config
}

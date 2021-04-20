#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
PR_append = ".rpi0"

do_install_append_rpi() {
	rm -f ${D}${sysconfdir}/fw_env.config
}

#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

PR:append = ".nxp0"

do_deploy:append() {
    for fw in ${DEPLOYDIR}/dp/*.bin; do
        ln -sf dp/$(basename "$fw") ${DEPLOYDIR}/$(basename "$fw")
    done
}

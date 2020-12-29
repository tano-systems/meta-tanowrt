#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#

PR_append = ".swi0"

copy_sources() {
    mv ${WORKDIR}/i2cgpioctl.c  ${S}
    # Include gpioexp for MDM9X15, MDM9X28, MDM9X28_FX30 and MDM9X28_WP
    mv ${WORKDIR}/gpioexp.c  ${S}
    mv ${WORKDIR}/tools      ${S}
}

do_compile() {
    cd ${S}
    ${CC} ${CFLAGS} ${LDFLAGS} -g -o i2cgpioctl -I tools i2cgpioctl.c tools/util.c tools/i2cbusses.c
    # Include gpioexp for MDM9X15, MDM9X28, MDM9X28_FX30 and MDM9X28_WP
    ${CC} ${CFLAGS} ${LDFLAGS} -g -o gpioexp -I tools gpioexp.c
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/i2cgpioctl ${D}${bindir}
    # Include gpioexp for MDM9X15, MDM9X28, MDM9X28_FX30 and MDM9X28_WP
    install -m 0755 ${S}/gpioexp ${D}${bindir}
}

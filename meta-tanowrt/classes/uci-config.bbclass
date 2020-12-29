#
# SPDX-License-Identifier: MIT
#
# UCI configuration
#
# Copyright (c) 2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

DEPENDS_append = " uci-native "

UCI = "uci -c ${D}${sysconfdir}/config"

do_uci_config() {
	bbnote "Performing UCI configuration"
}

do_install[postfuncs] += "do_uci_config"

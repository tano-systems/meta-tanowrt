#
# SPDX-License-Identifier: MIT
#
# UCI configuration
#
# Copyright (c) 2020 Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

DEPENDS:append = " uci-native "

UCI_ROOT ?= "${D}"
UCI = "uci -c ${UCI_ROOT}${sysconfdir}/config"

do_uci_config() {
	bbnote "Performing UCI configuration"
}

do_install[postfuncs] += "do_uci_config"

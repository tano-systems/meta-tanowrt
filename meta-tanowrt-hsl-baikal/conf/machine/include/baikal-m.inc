#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021-2022 Tano Systems LLC. All rights reserved.
#

MACHINEOVERRIDES =. "baikal-m:"

MACHINE_EXTRA_RRECOMMENDS_append = "\
	baikal-scp \
"

require conf/machine/include/baikal-common.inc
require conf/machine/include/tune-cortexa57.inc

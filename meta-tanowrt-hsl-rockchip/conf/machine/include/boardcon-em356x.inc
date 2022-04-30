# Copyright (c) 2022, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

MACHINE_EXTRA_RRECOMMENDS_append = " \
	drm-cursor \
"

MACHINE_EXTRA_RDEPENDS_append = " \
	i2c-tools \
"

# Enable multilib support
require conf/multilib.conf
MULTILIBS = "multilib:lib32"
DEFAULTTUNE_virtclass-multilib-lib32 = "armv7vethf-neon"

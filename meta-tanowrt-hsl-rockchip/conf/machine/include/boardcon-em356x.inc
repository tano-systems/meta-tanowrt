# Copyright (c) 2022, Tano Systems LLC
# Released under the MIT license (see COPYING.MIT for the terms)

MACHINE_EXTRA_RRECOMMENDS:append = " \
	drm-cursor \
"

MACHINE_EXTRA_RDEPENDS:append = " \
	i2c-tools \
"

# Enable multilib support
require conf/multilib.conf
MULTILIBS = "multilib:lib32"
DEFAULTTUNE:virtclass-multilib-lib32 = "armv7vethf-neon"

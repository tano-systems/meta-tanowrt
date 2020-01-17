# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)
PR_append = ".swi0"
inherit tanowrt-swi-image

IMAGE_INSTALL_append = "mbusd"

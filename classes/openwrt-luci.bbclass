#
# LuCI packages generation class
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

# By default make only english packages
LUCI_LANGUAGES ?= 'en'

# Default installation paths
LUCI_INSTALL_LUASRC_PATH ?= "/usr/lib/lua/5.1/luci"
LUCI_INSTALL_HTDOCS_PATH ?= "/www"
LUCI_INSTALL_ROOT_PATH   ?= "/"

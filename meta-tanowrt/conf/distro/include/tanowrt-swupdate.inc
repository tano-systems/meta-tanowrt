#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#
# SWUPDATE configuration defaults
#

SWU_VERBOSE ?= "1"
SWU_SYSLOG ?= "0"
SWU_UBI_MTD_BLACKLIST ?= ""
SWU_LOGLEVEL ?= "3"
SWU_HOOKCMD ?= "/usr/lib/swupdate/swupdate_hook.sh"

SWU_SW_VERSIONS_FILE_TMP ?= "/tmp/sw-versions"

SWU_HW_COMPATIBILITY ?= ""${SWU_REVISION}""

SWU_SYSTEM_A_PART ?= "system_a"
SWU_SYSTEM_A_SELECTION ?= "tanowrt,system-a"

SWU_SYSTEM_B_PART ?= "system_b"
SWU_SYSTEM_B_SELECTION ?= "tanowrt,system-b"

SWU_BOOTLOADER_TRANSACTION_MARKER ?= "0"

SWU_SURICATTA ?= "0"

SWU_CONF ?= "${sysconfdir}/swupdate.conf"

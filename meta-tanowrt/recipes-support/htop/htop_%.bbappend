#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano0"

PACKAGECONFIG_remove = "${@bb.utils.contains('COMBINED_FEATURES', 'cgroup', '', 'cgroup', d)}"
PACKAGECONFIG_remove = "delayacct"

#
# SPDX-License-Identifier: MIT
# Copyright (c) 2019-2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".tano0"

#
# Set priority less than busybox's awk
#
# Setting gawk as alternative for /bin/awk will breaks
# /bin/ipcalc.sh script from base-files package
#
ALTERNATIVE_PRIORITY = "10"

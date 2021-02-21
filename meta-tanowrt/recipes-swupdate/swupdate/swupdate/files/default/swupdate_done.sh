#!/bin/sh
# Copyright (C) 2021 Tano Systems LLC. All Rights Reserved.
# SPDX-License-Identifier: MIT

[ -f /usr/lib/swupdate/swupdate.sh ] || exit 1

. /usr/lib/swupdate/swupdate.sh

swupdate_init_boot_done

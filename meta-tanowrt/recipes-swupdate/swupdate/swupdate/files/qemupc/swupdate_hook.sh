#!/bin/sh
# Copyright (C) 2021 Tano Systems LLC. All Rights Reserved.
# SPDX-License-Identifier: MIT

[ -f /usr/lib/swupdate/swupdate.sh ] || exit 1

. /usr/lib/swupdate/swupdate.sh

case "$1" in
	preupdate)
		# Remount /boot with read/write permissions
		mount /boot -o remount,rw
		;;

	postupdate-success|postupdate-failed)
		# Remount /boot with read-only permissions
		mount /boot -o remount,ro
		;;

	*)
		;;
esac

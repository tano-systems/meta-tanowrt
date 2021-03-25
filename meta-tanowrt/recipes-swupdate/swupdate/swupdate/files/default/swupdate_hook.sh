#!/bin/sh
# Copyright (C) 2021 Tano Systems LLC. All Rights Reserved.
# SPDX-License-Identifier: MIT

[ "${SWU_FACTORY_INSTALL}" = "1" ] && exit 0

[ -f /usr/lib/swupdate/swupdate.sh ] || exit 1

. /usr/lib/swupdate/swupdate.sh

case "$1" in
	preupdate|postupdate-success|postupdate-failed)
		cd /usr/lib/swupdate/hook.d 2>/dev/null || return 0
		scripts="$(ls)"
		[ -z "$scripts" ] && exit 0
		for script in $scripts; do
			if [ -x "$script" ]; then
				./${script} "${1}"
				[ "$?" = "0" ] || exit 0
			fi
		done
		;;

	*)
		;;
esac

exit 0

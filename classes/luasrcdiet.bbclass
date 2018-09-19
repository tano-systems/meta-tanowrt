# This file Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>

DEPENDS += "luasrcdiet-native"

LUASRCDIET_PATHS ?= "${D}/usr/lib/lua/5.1"

do_install_append() {
	for p in "${LUASRCDIET_PATHS}"; do
		find "$p" -type f -name '*.lua' | while read src; do
			if luasrcdiet --noopt-binequiv -o "$src.o" "$src"; then
				mv "$src.o" "$src"
			fi
		done
	done
}

# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC

test -n ${console_tty1} || setenv console_tty1 1;

consoleargs=""

if test $console_tty1 = 1; then
	consoleargs="${consoleargs} console=tty1"
fi

consoleargs_enabled="console="CONSOLE_DEV","CONSOLE_BAUDRATE"n8";
consoleargs="${consoleargs} ${consoleargs_enabled}";


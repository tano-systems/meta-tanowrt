# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2022 Tano Systems LLC

consoleargs=""

consoleargs_enabled="console="CONSOLE_DEV","CONSOLE_BAUDRATE"n8 earlycon=uart8250,mmio32,0xfe680000";
consoleargs="${consoleargs} ${consoleargs_enabled}";


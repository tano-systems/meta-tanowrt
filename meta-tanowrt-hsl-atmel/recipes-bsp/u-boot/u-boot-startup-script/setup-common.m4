# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC

test -n ${loadaddr} || setenv loadaddr 0x24000000;
test -n ${baudrate} || setenv baudrate 115200;
test -n ${optargs} || setenv optargs "earlyprintk panic=15";

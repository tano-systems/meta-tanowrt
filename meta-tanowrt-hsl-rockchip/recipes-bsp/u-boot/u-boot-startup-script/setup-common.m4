# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2022 Tano Systems LLC

test -n ${loadaddr} || setenv loadaddr 0x04080000;
test -n ${baudrate} || setenv baudrate 1500000;
test -n ${optargs} || setenv optargs "earlyprintk panic=15";

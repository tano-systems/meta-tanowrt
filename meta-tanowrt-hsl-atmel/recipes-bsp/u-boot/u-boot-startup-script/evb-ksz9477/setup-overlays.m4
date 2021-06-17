#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC
#
# EVB-KSZ9477 DTB/DTBO configuration setup
#
test -n ${sfp} || setenv sfp 0;

setenv bootconfigs "#conf-at91-evb-ksz9477.dtb";

if test $sfp = 1; then
	setenv bootconfigs "#conf-at91-evb-ksz9477-sfp.dtb";
fi;

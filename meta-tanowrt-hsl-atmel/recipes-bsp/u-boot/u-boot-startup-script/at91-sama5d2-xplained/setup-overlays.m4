#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2022 Tano Systems LLC
#
# SAMA5D2 Xplained DTB/DTBO configuration setup
#
test -n ${sfp} || setenv sfp 0;

setenv bootconfigs "#conf-at91-sama5d2_xplained.dtb";

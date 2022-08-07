#
# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2022 Tano Systems LLC
#
# SAMA5D3 Xplained DTB/DTBO configuration setup
#
test -n ${sfp} || setenv sfp 0;

setenv bootconfigs "#conf-at91-sama5d3_xplained.dtb";

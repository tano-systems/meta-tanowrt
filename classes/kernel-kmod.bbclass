#
# Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>
#
inherit kernel-config-depends

do_configure[noexec] = "1"
do_compile[noexec] = "1"

FILES_${PN}-dev = ""
FILES_SOLIBSDEV = ""
ALLOW_EMPTY_${PN} = "1"

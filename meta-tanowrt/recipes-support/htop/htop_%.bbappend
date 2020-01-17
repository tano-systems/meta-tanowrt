# This file Copyright (C) 2018-2019 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".tano0"

PACKAGECONFIG_remove = "${@bb.utils.contains('COMBINED_FEATURES', 'cgroup', '', 'cgroup', d)}"
PACKAGECONFIG_remove = "delayacct"

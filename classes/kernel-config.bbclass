#
# Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
#

def kernel_get_config(config, d):
    import re

    staging = d.getVar('STAGING_KERNEL_BUILDDIR', True)
    with open(staging + '/.config') as f:
        lines = f.readlines()

    for line in lines:
        match = re.search(r'^' + config + '=(.*)$', line)
        if match:
            return match.group(1)

    return "n"

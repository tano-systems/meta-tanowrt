#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

#
# Handle CONFIG_UBUS config option
#
# The underlying code of this function are taken from file
# "recipes-support/swupdate/swupdate.inc" of the meta-swupdate layer.
#
python() {
    import re

    try:
        defconfig = bb.fetch2.localpath('file://defconfig', d)
    except bb.fetch2.FetchError:
        return

    try:
        configfile = open(defconfig)
    except IOError:
        return

    features = configfile.read()
    configfile.close()

    for current_fragment in find_cfgs(d):
        try:
            fragment_fd = open(current_fragment)
        except IOError:
            continue

        fragment = fragment_fd.read()
        fragment_fd.close()

        fragment_search = re.findall('^(?:# )?(CONFIG_[a-zA-Z0-9_]*)[= ].*\n?', fragment, re.MULTILINE)

        for feature in fragment_search:
            features = re.sub("^(?:# )?" + feature + "[= ].*\n?", "", features, flags=re.MULTILINE)

        features = features + fragment

    features = features.splitlines(True)

    depends = d.getVar('DEPENDS', False)

    if 'CONFIG_UBUS=y\n' in features:
        depends += ' libubus libubox'
    else:
        bb.error('CONFIG_UBUS is not enabled in configuration')

    if 'CONFIG_SW_VERSIONS_FILE="/etc/sw-versions"\n' not in features:
        bb.error('CONFIG_SW_VERSIONS_FILE is set to value other than "/etc/sw-versions"')

    if 'CONFIG_HW_COMPATIBILITY=y\n' not in features:
        bb.error('CONFIG_HW_COMPATIBILITY is not enabled in configuration')
    else:
        if 'CONFIG_HW_COMPATIBILITY_FILE="/etc/hw-revision"\n' not in features:
            bb.error('CONFIG_HW_COMPATIBILITY_FILE is set to value other than "/etc/hw-revision"')

    d.setVar('DEPENDS', depends)
}

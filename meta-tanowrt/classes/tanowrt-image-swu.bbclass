#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#

inherit swupdate

# For UEFI images
require conf/image-uefi.conf

inherit features_check
REQUIRED_MACHINE_FEATURES = "swupdate"

DEPENDS += "sed-native binutils-native"

SWU_IMAGE_CLASSES ?= ""
inherit ${SWU_IMAGE_CLASSES}

SWU_IMAGE_VARS = "SWU_IMAGE_ARTIFACTS SWU_IMAGE_MEDIA_TYPE SWU_IMAGE_BOARD_NAME \
                  SWU_IMAGE_BOARD_ID SWU_IMAGE_HW_COMPATIBILITY SWU_IMAGE_FIRMWARE_VERSION \
                  SWU_IMAGE_FIRMWARE_DESCRIPTION"

SWU_IMAGE_MEDIA_TYPE ?= "SD Card"
SWU_IMAGE_BOARD_NAME ?= "${OPENWRT_VERSION_PRODUCT}"
SWU_IMAGE_BOARD_ID ?= "${SWU_BOARD}"
SWU_IMAGE_HW_COMPATIBILITY ?= "${SWU_HW_COMPATIBILITY}"

SWU_IMAGE_FIRMWARE_VERSION ?= "${@d.getVarFlag('SWU_IMAGE_ARTIFACT_rootfs', 'version', True)}"
SWU_IMAGE_FIRMWARE_DESCRIPTION ?= "Firmware upgrade for ${SWU_IMAGE_BOARD_NAME}"

SWU_IMAGE_EXTRA_ARTIFACTS ?= ""
SWU_IMAGE_ARTIFACTS ?= "rootfs ${SWU_IMAGE_EXTRA_ARTIFACTS}"

SWU_IMAGE_ARTIFACT_rootfs[fname] ?= '${@PN.replace("-swu", "")}'
SWU_IMAGE_ARTIFACT_rootfs[ftype] ?= "${ROOTFS_IMAGE_FSTYPE}"
SWU_IMAGE_ARTIFACT_rootfs[verextract] ?= "1"
SWU_IMAGE_ARTIFACT_rootfs[verextract-input] ?= "${DEPLOY_DIR_IMAGE}/${@d.getVarFlag('SWU_IMAGE_ARTIFACT_rootfs', 'fname', True)}-${MACHINE}.version"
SWU_IMAGE_ARTIFACT_rootfs[verextract-var] ?= "SWU_IMAGE_ARTIFACT_rootfs[version]"
SWU_IMAGE_ARTIFACT_rootfs[depends] ?= "${@d.getVarFlag('SWU_IMAGE_ARTIFACT_rootfs', 'fname', True)}:do_image_complete"

python __anonymous () {
    arts = (d.getVar('SWU_IMAGE_ARTIFACTS', True) or "").split()
    for art in arts:
        artvar = 'SWU_IMAGE_ARTIFACT_%s' % art
        d.appendVarFlag("do_swuimage", "vardeps", " %s" % artvar)

        depends = d.getVarFlag(artvar, 'depends', True) or ""
        if depends:
            for dep in depends.split():
                d.appendVarFlag("do_swuimage", "depends", " %s" % dep)
}

def do_prepare_swu_image_data(d):
    def file_search(dir, files):
        for f in files:
            path = '%s/%s' % (dir, f)
            if os.path.isfile(path):
                return f

        return None

    arts = (d.getVar('SWU_IMAGE_ARTIFACTS', True) or "").split()
    deploy_dir = d.getVar('DEPLOY_DIR_IMAGE', True)
    machine = d.getVar('MACHINE', True)
    for art in arts:
        artvar = 'SWU_IMAGE_ARTIFACT_%s' % art

        fname = d.getVarFlag(artvar, 'fname', True)
        if not fname:
            bb.fatal('%s[fname] have to be set' % artvar)

        ftype = d.getVarFlag(artvar, 'ftype', True) or ""
        no_append_machine = d.getVarFlag(artvar, 'no-append-machine', True)

        sw_desc_extra = d.getVarFlag(artvar, 'sw-desc-extra', True)
        if sw_desc_extra is None:
            sw_desc_extra = ""
            d.setVarFlag(artvar, 'sw-desc-extra', sw_desc_extra)

        name = d.getVarFlag(artvar, 'name', True)
        if not name:
            name = art
            d.setVarFlag(artvar, 'name', name)

        fext = ('.%s' % ftype) if ftype else ""

        if no_append_machine == False:
            filename = '%s-%s%s' % (fname, machine, fext)
            d.setVarFlag('SWUPDATE_IMAGES_NOAPPEND_MACHINE', fname, "0")
        elif no_append_machine == True:
            filename = '%s%s' % (fname, fext)
            d.setVarFlag('SWUPDATE_IMAGES_NOAPPEND_MACHINE', fname, "1")
        else:
            filename = file_search(deploy_dir, \
                [ '%s-%s%s' % (fname, machine, fext), '%s%s' % (fname, fext) ])

        if not filename:
            bb.fatal("Cannot find required file for artifact '%s' in deploy directory" % (art))

        d.setVarFlag(artvar, 'file', filename)
        d.appendVar('SWUPDATE_IMAGES', ' %s' % fname)
        d.setVarFlag('SWUPDATE_IMAGES_FSTYPES', fname, fext)

        verextract = d.getVarFlag(artvar, 'verextract', True) or "0"
        if verextract not in [ "1", "True" ]:
            continue

        d.appendVar('EXTRACT_DATA_ARTIFACTS', ' %s' % art)

        verextract_input = d.getVarFlag(artvar, 'verextract-input', True) or ""
        if verextract_input:
            d.setVarFlag('EXTRACT_DATA_INPUT', art, verextract_input)
        else:
            d.setVarFlag('EXTRACT_DATA_INPUT', art, '%s/%s' % (deploy_dir, filename))

        verextract_cmd = d.getVarFlag(artvar, 'verextract-cmd', True) or ""
        if verextract_cmd:
            d.setVarFlag('EXTRACT_DATA_CMD', art, verextract_cmd)

        verextract_filter_cmd = d.getVarFlag(artvar, 'verextract-filter-cmd', True) or ""
        if verextract_filter_cmd:
            d.setVarFlag('EXTRACT_DATA_FILTER_CMD', art, verextract_filter_cmd)

        verextract_var = d.getVarFlag(artvar, 'verextract-var', True) or ""
        if verextract_var:
            d.setVarFlag('EXTRACT_DATA_VAR', art, verextract_var)
        else:
            d.setVarFlag('EXTRACT_DATA_VAR', art, '%s[version]' % artvar)

do_swuimage[depends] += "\
	virtual/kernel:do_deploy \
"

do_swuimage[vardeps] += "${SWU_IMAGE_VARS}"

#
# Exactly the same as the original, only the lines has been changed:
#
#   -m = re.match(r"^(?P<before_placeholder>.+)@@(?P<bitbake_variable_name>\w+)@@(?P<after_placeholder>.+)$", line)
#   +m = re.match(r"^(?P<before_placeholder>.*)@@(?P<bitbake_variable_name>\w+)@@(?P<after_placeholder>.*)$", line)
#
#   -m = re.match(r"^(?P<before_placeholder>.+)@@(?P<bitbake_variable_name>.+)\[(?P<flag_var_name>.+)\]@@(?P<after_placeholder>.+)$", line)
#   +m = re.match(r"^(?P<before_placeholder>.*)@@(?P<bitbake_variable_name>.+)\[(?P<flag_var_name>.+)\]@@(?P<after_placeholder>.*)$", line)
#
# This allows using variables @@VAR@@ separately on lines without anything else.
#
def swupdate_expand_bitbake_variables(d, s):
    write_lines = []

    with open(os.path.join(s, "sw-description"), 'r') as f:
        import re
        for line in f:
            found = False
            while True:
                m = re.match(r"^(?P<before_placeholder>.*)@@(?P<bitbake_variable_name>\w+)@@(?P<after_placeholder>.*)$", line)
                if m:
                    bitbake_variable_value = d.getVar(m.group('bitbake_variable_name'), True)
                    if bitbake_variable_value is None:
                        bitbake_variable_value = ""
                        bb.warn("BitBake variable %s not set" % (m.group('bitbake_variable_name')))
                    line = m.group('before_placeholder') + bitbake_variable_value + m.group('after_placeholder')
                    found = True
                    continue
                else:
                    m = re.match(r"^(?P<before_placeholder>.*)@@(?P<bitbake_variable_name>.+)\[(?P<flag_var_name>.+)\]@@(?P<after_placeholder>.*)$", line)
                    if m:
                       bitbake_variable_value = d.getVarFlag(m.group('bitbake_variable_name'), m.group('flag_var_name'), True)
                       if bitbake_variable_value is None:
                           bitbake_variable_value = ""
                           bb.warn("BitBake variable %s[%s] not set" % (m.group('bitbake_variable_name'), m.group('flag_var_name')))
                       line = m.group('before_placeholder') + bitbake_variable_value + m.group('after_placeholder')
                       found = True
                       continue

                    if found:
                       line = line + "\n"
                    break

            write_lines.append(line)

    with open(os.path.join(s, "sw-description"), 'w+') as f:
        for line in write_lines:
            f.write(line)


#
# Extract actual versions from deployed artifacts
#
inherit extract-data

EXTRACT_DATA_DISPLAY_TABLE = "1"
EXTRACT_DATA_TITLE = "Version"
EXTRACT_DATA_STRIP = "1"
EXTRACT_DATA_REPLACE_SPACES = "-"

EXTRACT_DATA_ARTIFACTS ?= ""

python do_swuimage:prepend() {
    do_prepare_swu_image_data(d)
    # Extract versions data
    do_extract_data(d)
}

#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

EXTRACT_DATA_DISPLAY_TABLE ?= "1"
EXTRACT_DATA_TITLE ?= "Data"
EXTRACT_DATA_STRIP ?= "1"
EXTRACT_DATA_REPLACE_SPACES ?= "-"

EXTRACT_DATA_ARTIFACTS ?= ""

do_extract_data[vardeps] += "\
	EXTRACT_DATA_DISPLAY_TABLE \
	EXTRACT_DATA_TITLE \
	EXTRACT_DATA_STRIP \
	EXTRACT_DATA_REPLACE_SPACES \
	EXTRACT_DATA_ARTIFACTS \
	EXTRACT_DATA_VAR \
	EXTRACT_DATA_CMD \
	EXTRACT_DATA_INPUT \
	EXTRACT_DATA_FILTER_CMD \
"

def do_extract_data(d):
    artifacts = d.getVar('EXTRACT_DATA_ARTIFACTS')
    if not artifacts:
        return

    data_strip = d.getVar('EXTRACT_DATA_STRIP', True)
    data_replace_spaces = d.getVar('EXTRACT_DATA_REPLACE_SPACES', True)

    def data_process(data, d):
        ret = data
        if data_strip == "1":
            ret = ret.strip()
        if data_replace_spaces and data_replace_spaces != "":
            ret = ret.replace(' ', data_replace_spaces)
        return ret

    artifacts = artifacts.split()
    maxwidth = 8 # len('Artifact')

    for artifact in artifacts:
        var = d.getVarFlag('EXTRACT_DATA_VAR', artifact)
        cmd = d.getVarFlag('EXTRACT_DATA_CMD', artifact)
        input = d.getVarFlag('EXTRACT_DATA_INPUT', artifact)
        filter_cmd = d.getVarFlag('EXTRACT_DATA_FILTER_CMD', artifact)

        #
        # Calculate maximum width for the artifacts names.
        # Used only for pretty formatting extracted data table.
        #
        width = len(artifact)
        if width > maxwidth:
            maxwidth = width

        if not var or not input:
            bb.warn("Skipping data extraction for %s. "
                    "EXTRACT_DATA_VAR[%s] and EXTRACT_DATA_INPUT[%s] have to be set." \
                    % (artifact, artifact, artifact))
            continue

        if not cmd:
            cmd = "cat"

        fullcmd = '%s %s' % (cmd, input)

        if filter_cmd:
            fullcmd = fullcmd + ('|%s' % filter_cmd)

        bb.debug(1, "Extracting data for %s with command '%s'..." % (artifact, fullcmd))
        fd = os.popen(fullcmd)
        data = fd.readline()
        fd.close()

        if not data:
            bb.warn("Cannot extract data for %s" % artifact)
            d.setVarFlag(var, 'extracted', '0')
        else:
            data = data_process(data, d)
            bb.debug(1, "Extracted data for %s from %s is '%s'" % (artifact, input, data))
            d.setVarFlag(var, 'extracted', '1')
            d.setVar(var, data)
            bb.debug(1, "Saved '%s' to variable %s" % (data, var))

    if d.getVar('EXTRACT_DATA_DISPLAY_TABLE', True) == "1" and len(artifacts):
        hor_line = ('-' * (maxwidth + 1)) + '-+-'  + ('-' * 60)

        data_title = d.getVar('EXTRACT_DATA_TITLE', True)
        if not data_title:
            data_title = "Data"

        data_table = '\n'
        data_table = data_table + '* Machine: ' + d.getVar('MACHINE', True) + '\n'
        data_table = data_table + '* Recipe: ' + d.getVar('BPN', True) + '\n'
        data_table = data_table + '* Version: ' + d.getVar('PV', True) + '\n'
        data_table = data_table + '* Revision: ' + d.getVar('PR', True) + '\n'
        data_table = data_table + '\n'
        data_table = data_table + hor_line + '\n'
        data_table = data_table + " %*s | %s\n" % (maxwidth, 'Artifact', data_title)
        data_table = data_table + hor_line + '\n'

        for artifact in artifacts:
            var = d.getVarFlag('EXTRACT_DATA_VAR', artifact)
            extracted = d.getVarFlag(var, 'extracted')

            if extracted == '1':
                data = d.getVar(var, True)
                data_table = data_table + \
                    (" %*s | %s\n" % (maxwidth, artifact, data))
            else:
                data_table = data_table + \
                    (" %*s | \033[1;31munknown / not extracted\033[0m\n" % (maxwidth, artifact))

        data_table = data_table + hor_line + '\n'
        bb.plain(data_table)

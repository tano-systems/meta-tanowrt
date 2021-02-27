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
    import re

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
        cmd = d.getVarFlag('EXTRACT_DATA_CMD', artifact)
        input = d.getVarFlag('EXTRACT_DATA_INPUT', artifact)
        filter_cmd = d.getVarFlag('EXTRACT_DATA_FILTER_CMD', artifact)

        var = d.getVarFlag('EXTRACT_DATA_VAR', artifact)
        varflag = None
        result = re.match(r'(.+)\[(.+)\]', var)
        if result:
            var = result.group(1)
            varflag = result.group(2)

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

        if not os.path.isfile(input):
            bb.error("Cannot extract data for artifact '%s': file '%s' is not exists" % (artifact, input))
            d.setVarFlag('EXTRACT_DATA_EXTRACTED', artifact, '0')
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
            bb.error("Cannot extract data for artifact '%s'" % artifact)
            d.setVarFlag('EXTRACT_DATA_EXTRACTED', artifact, '0')
            continue

        data = data_process(data, d)
        bb.debug(1, "Extracted data for %s from %s is '%s'" % (artifact, input, data))
        d.setVarFlag('EXTRACT_DATA_EXTRACTED', artifact, '1')
        if varflag:
            d.setVarFlag(var, varflag, data)
            bb.debug(1, "Saved '%s' to variable %s[%s]" % (data, var, varflag))
        else:
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
            extracted = d.getVarFlag('EXTRACT_DATA_EXTRACTED', artifact, True)
            var = d.getVarFlag('EXTRACT_DATA_VAR', artifact, True)
            varflag = None
            result = re.match(r'(.+)\[(.+)\]', var)
            if result:
                var = result.group(1)
                varflag = result.group(2)

            if extracted == '1':
                if varflag:
                    data = d.getVarFlag(var, varflag, True)
                else:
                    data = d.getVar(var, True)

                data_table = data_table + \
                    (" %*s | %s\n" % (maxwidth, artifact, data))
            else:
                data_table = data_table + \
                    (" %*s | \033[1;31munknown / not extracted\033[0m\n" % (maxwidth, artifact))

        data_table = data_table + hor_line + '\n'
        bb.plain(data_table)

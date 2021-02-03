#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#
# Class for expanding bitbake variables in files
#
# Some parts of code are taken from meta-swupdate
# (meta-swupdate/classes/swupdate-common.bbclass)
#

EXPAND_IN_FILES_WITH_EXTENSION ?= ".in"
EXPAND_VARIABLES ?= ""

def expand_bitbake_variables(d, fileinput, fileoutput):
    write_lines = []

    vars = d.getVar('EXPAND_VARIABLES', True)
    if vars:
        vars = vars.split()
    else:
        vars = []

    with open(fileinput, 'r') as f:
        import re
        for line in f:
            found = False
            while True:
                m = re.match(r"^(?P<before_placeholder>.*)@@(?P<bitbake_variable_name>\w+)@@(?P<after_placeholder>.*)$", line)
                if m:
                    varname = m.group('bitbake_variable_name')
                    if varname not in vars:
                        bb.warn("BitBake variable %s is not in EXPAND_VARIABLES list. Add variable to this list, so that if value is changed, the re-expansion will be done automatically." % (varname))
                    bitbake_variable_value = d.getVar(varname, True)
                    if bitbake_variable_value is None:
                        bitbake_variable_value = ""
                        bb.warn("BitBake variable %s not set" % (varname))
                    line = m.group('before_placeholder') + bitbake_variable_value + m.group('after_placeholder')
                    found = True
                    continue
                else:
                    m = re.match(r"^(?P<before_placeholder>.+)@@(?P<bitbake_variable_name>.+)\[(?P<flag_var_name>.+)\]@@(?P<after_placeholder>.+)$", line)
                    if m:
                        varname = m.group('bitbake_variable_name')
                        if varname not in vars:
                            bb.warn("BitBake variable %s is not in EXPAND_VARIABLES list. Add variable to this list, so that if value is changed, the re-expansion will be done automatically." % (varname))
                        varflag = m.group('flag_var_name')
                        bitbake_variable_value = (d.getVarFlag(varname, varflag, True) or "")
                        if bitbake_variable_value is None:
                            bitbake_variable_value = ""
                        line = m.group('before_placeholder') + bitbake_variable_value + m.group('after_placeholder')
                        continue

                    if found:
                        line = line + "\n"
                    break

            write_lines.append(line)

    with open(fileoutput, 'w+') as f:
        for line in write_lines:
            f.write(line)


do_expandvars[vardeps] += "${EXPAND_VARIABLES}"

python do_expandvars() {
    import re

    ext = d.getVar('EXPAND_IN_FILES_WITH_EXTENSION', True)
    workdir = d.getVar('WORKDIR', True)

    fetch = bb.fetch2.Fetch([], d)

    for url in fetch.urls:
        local = fetch.localpath(url)
        basename = os.path.basename(local)
        if os.path.isfile(local) and re.match(r".+" + re.escape(ext) + r"$", basename):
            fileinput = local
            fileoutput = os.path.join(workdir, \
                re.sub(re.escape(ext) + r"$", "", basename))

            expand_bitbake_variables(d, fileinput, fileoutput)
            bb.debug(1, "Expanded bitbake variables in '%s' and writed output to '%s'" % \
                (fileinput, fileoutput))
}

addtask expandvars after do_unpack before do_patch

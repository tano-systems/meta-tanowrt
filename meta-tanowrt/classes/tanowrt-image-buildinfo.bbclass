#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2020 Tano Systems LLC
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
# Writes build information to target filesystem
# on /etc/build.json in JSON format
#
# Based on original image-buildinfo.bbclass from OpenEmbedded
# core layer.
#
# Changes:
#   - changed /etc/build output format to JSON
#     (output to /etc/build.json)
#   - added variables to default list
#   - moved from 'base-files' package generation of files:
#       - /etc/openwrt_version
#       - /etc/openwrt_release
#       - /etc/device_info
#

# Desired variables to display
TANOWRT_IMAGE_BUILDINFO_VARS ?= "\
	DISTRO \
	DISTRO_VERSION \
	MACHINE \
	TARGET_SYS \
	TARGET_ARCH \
	TANOWRT_VERSION \
	TANOWRT_ROOTFS_GEN_TIMESTAMP \
	TANOWRT_DEVICE_MANUFACTURER \
	TANOWRT_DEVICE_MANUFACTURER_URL \
	TANOWRT_DEVICE_PRODUCT \
	TANOWRT_DEVICE_REVISION \
	TANOWRT_RELEASE_DISTRIB_ID \
	TANOWRT_RELEASE_DISTRIB_RELEASE \
	TANOWRT_RELEASE_DISTRIB_REVISION \
	TANOWRT_RELEASE_DISTRIB_TARGET \
	TANOWRT_RELEASE_DISTRIB_ARCH \
	TANOWRT_RELEASE_DISTRIB_DESCRIPTION \
	TANOWRT_RELEASE_DISTRIB_TAINTS \
	TANOWRT_RELEASE_DISTRIB_TIMESTAMP \
"

# Desired location of the output file in the image
TANOWRT_IMAGE_BUILDINFO_FILE ??= "${sysconfdir}/build.json"

# Gets git branch's status (clean or dirty)
def tanowrt_get_layer_git_modified(path):
    import subprocess
    try:
        subprocess.check_output("""cd %s; export PSEUDO_UNLOAD=1; set -e;
                                git diff --quiet --no-ext-diff
                                git diff --quiet --no-ext-diff --cached""" % path,
                                shell=True,
                                stderr=subprocess.STDOUT)
        return False
    except subprocess.CalledProcessError as ex:
        # Silently treat errors as "modified", without checking for the
        # (expected) return code 1 in a modified git repo. For example, we get
        # output and a 129 return code when a layer isn't a git repo at all.
        return True

# Returns layer revisions along with their respective status
def tanowrt_get_layers_info(d):
    layers = (d.getVar("BBLAYERS") or "").split()
    ret = {}

    for layer in layers:
        layer_name = os.path.basename(layer)
        layer_branch = base_get_metadata_git_branch(layer, None).strip()
        layer_rev = base_get_metadata_git_revision(layer, None)
        layer_modified = tanowrt_get_layer_git_modified(layer)

        ret[layer_name] = {
            "branch":   layer_branch,
            "revision": layer_rev,
            "modified": layer_modified
        }

    return ret

def tanowrt_output_vars(vars, d):
    vars = vars.split()
    ret = {}

    for var in vars:
        value = d.getVar(var) or ""
        if (d.getVarFlag(var, 'type') == "list"):
            ret[var] = value.split()
        else:
            ret[var] = value

    return ret

def tanowrt_get_vars_info(d):
    # Get context
    if d.getVar('BB_WORKERCONTEXT') != '1':
        return {}
    # Single and list variables to be read
    vars = (d.getVar("TANOWRT_IMAGE_BUILDINFO_VARS") or "")
    return tanowrt_output_vars(vars, d)

# Write build information to target filesystem
python tanowrt_buildinfo() {
    import json

    buildinfo = {
        "build":  tanowrt_get_vars_info(d),
        "layers": tanowrt_get_layers_info(d)
    }

    if not d.getVar('TANOWRT_IMAGE_BUILDINFO_FILE'):
        return
    with open(d.expand('${IMAGE_ROOTFS}${TANOWRT_IMAGE_BUILDINFO_FILE}'), 'w') as f:
        f.write(json.dumps(buildinfo, sort_keys = False, indent = "\t"))
        f.write("\n")
}

IMAGE_PREPROCESS_COMMAND += "tanowrt_buildinfo;"

#
# Calculate some version dependent variables
#
TANOWRT_SCM_BASE = "${@os.path.normpath(os.path.join(d.getVar('TANOWRT_BASE'), ".."))}"
TANOWRT_SCM_BRANCH = "${@base_get_metadata_git_branch(d.getVar('TANOWRT_SCM_BASE'), None).strip()}"
TANOWRT_SCM_REVISION = "${@base_get_metadata_git_revision(d.getVar('TANOWRT_SCM_BASE'), None)}"
TANOWRT_SCM_REVISION_SHORT = "${@d.getVar('TANOWRT_SCM_REVISION')[:12]}"

TANOWRT_ROOTFS_GEN_TIMESTAMP ?= "${@time.strftime('%Y-%m-%d %H:%M:%S', time.strptime(d.getVar('DATETIME', d), '%Y%m%d%H%M%S'))} UTC"
TANOWRT_ROOTFS_GEN_TIMESTAMP[vardepsexclude] += "DATETIME"

#
# Files generation:
#   - /etc/openwrt_version
#   - /etc/openwrt_release
#   - /etc/device_info
#
inherit tanowrt-version

TANOWRT_DEVICE_MANUFACTURER ?= "${OPENWRT_VERSION_MANUFACTURER}"
TANOWRT_DEVICE_MANUFACTURER_URL ?= "${OPENWRT_VERSION_MANUFACTURER_URL}"
TANOWRT_DEVICE_PRODUCT ?= "${OPENWRT_VERSION_PRODUCT}"
TANOWRT_DEVICE_REVISION ?= "${OPENWRT_VERSION_HWREV}"

TANOWRT_VERSION ?= "${MACHINE} git-${TANOWRT_SCM_REVISION_SHORT} ${TANOWRT_ROOTFS_GEN_TIMESTAMP}"

TANOWRT_RELEASE_DISTRIB_ID ?= "${OPENWRT_VERSION_DIST}"
TANOWRT_RELEASE_DISTRIB_RELEASE ?= "${OPENWRT_VERSION_NUMBER}"
TANOWRT_RELEASE_DISTRIB_REVISION ?= "${TANOWRT_SCM_REVISION}"
TANOWRT_RELEASE_DISTRIB_TARGET ?= "${MACHINE}"
TANOWRT_RELEASE_DISTRIB_ARCH ?= "${TARGET_ARCH}"
TANOWRT_RELEASE_DISTRIB_DESCRIPTION ?= "${TANOWRT_RELEASE_DISTRIB_ID} ${TANOWRT_RELEASE_DISTRIB_RELEASE} ${TANOWRT_VERSION}"
TANOWRT_RELEASE_DISTRIB_TAINTS ?= ""
TANOWRT_RELEASE_DISTRIB_TIMESTAMP ?= "${TANOWRT_ROOTFS_GEN_TIMESTAMP}"

tanowrt_generate_openwrt_files() {
	# /etc/device_info
	{
		echo "DEVICE_MANUFACTURER='${TANOWRT_DEVICE_MANUFACTURER}'"
		echo "DEVICE_MANUFACTURER_URL='${TANOWRT_DEVICE_MANUFACTURER_URL}'"
		echo "DEVICE_PRODUCT='${TANOWRT_DEVICE_PRODUCT}'"
		echo "DEVICE_REVISION='${TANOWRT_DEVICE_REVISION}'"

	} > ${IMAGE_ROOTFS}${sysconfdir}/device_info

	# /etc/openwrt_version
	{
		echo "${TANOWRT_VERSION}"

	} > ${IMAGE_ROOTFS}${sysconfdir}/openwrt_version

	# /etc/openwrt_release
	{
		echo "DISTRIB_ID='${TANOWRT_RELEASE_DISTRIB_ID}'"
		echo "DISTRIB_RELEASE='${TANOWRT_RELEASE_DISTRIB_RELEASE}'"
		echo "DISTRIB_REVISION='${TANOWRT_RELEASE_DISTRIB_REVISION}'"
		echo "DISTRIB_TARGET='${TANOWRT_RELEASE_DISTRIB_TARGET}'"
		echo "DISTRIB_ARCH='${TANOWRT_RELEASE_DISTRIB_ARCH}'"
		echo "DISTRIB_DESCRIPTION='${TANOWRT_RELEASE_DISTRIB_DESCRIPTION}'"
		echo "DISTRIB_TAINTS='${TANOWRT_RELEASE_DISTRIB_TAINTS}'"
		echo "DISTRIB_TIMESTAMP='${TANOWRT_RELEASE_DISTRIB_TIMESTAMP}'"
	
	} > ${IMAGE_ROOTFS}${sysconfdir}/openwrt_release
}

IMAGE_PREPROCESS_COMMAND += "tanowrt_generate_openwrt_files; "

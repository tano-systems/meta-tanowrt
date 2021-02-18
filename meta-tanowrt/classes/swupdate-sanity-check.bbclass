#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021 Anton Kikin <a.kikin@tano-systems.com>
#
# Class for sanity check swupdate related variables
#

inherit features_check
REQUIRED_MACHINE_FEATURES = "swupdate"

python __anonymous() {
    def check_vars(varlist):
        errors = 0
        for varname in varlist:
            val = d.getVar(varname, True)
            if not val:
                bb.error("%s have to be set" % varname)
                errors = errors + 1

        return errors

    def check_var_list(varname, vallist):
        val = d.getVar(varname, True)
        if not val:
            bb.error("%s have to be set" % varname)
            return 1
        elif val not in vallist:
            bb.error("Unsupported %s value '%s'" % (varname, val))
            return 1

        return 0

    errors = 0

    errors += check_vars([
        "SWU_BOARD",
        "SWU_REVISION",
        "SWU_SW_VERSIONS_FILE_TMP",
        "SWU_SYSTEM_A_PART",
        "SWU_SYSTEM_B_PART",
        "SWU_SYSTEM_A_SELECTION",
        "SWU_SYSTEM_B_SELECTION"
    ])

    errors += check_var_list("SWU_BOOTLOADER", [ "none", "uboot", "grub-efi" ])
    errors += check_var_list("SWU_OVERLAY_CONTAINER", [ "ubivol", "blkdev" ])
    errors += check_var_list("SWU_OVERLAY_FS", [ "ubifs", "ext4" ])

    if d.getVar("SWU_BOOTLOADER", True) == "grub-efi":
        errors += check_vars([
            "SWU_GRUBENV",
            "SWU_GRUBENV_RO",
            "SWU_GRUBENV_MOUNTPOINT",
            "SWU_GRUBBIN",
        ])

    if d.getVar("SWU_SURICATTA", True) == "1":
        errors += check_vars([
            "SWU_SURICATTA_TENANT",
            "SWU_SURICATTA_ID",
            "SWU_SURICATTA_URL"
        ])

    if errors > 0:
        bb.fatal("SWUPDATE sanity checks (%d) failed" % errors)
}

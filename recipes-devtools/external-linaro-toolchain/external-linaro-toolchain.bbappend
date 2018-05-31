PR_append = ".tano0"

### LIC_FILES_CHKSUM_remove = "file://${COREBASE}/LICENSE;md5=4d92cd373abda3937c2bc47fbc49d690"

INHIBIT_PACKAGE_STRIP = "0"

INSANE_SKIP_libstdc++-dbg += "ldflags"
INSANE_SKIP_libstdc++-dbg += "staticdev"
INSANE_SKIP_libgcc-dev += "staticdev"

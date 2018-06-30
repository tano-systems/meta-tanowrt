FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:${THISDIR}/files:"

PR_append = ".tano0"

SRC_URI_append = "\
    file://ipset.cfg \
"

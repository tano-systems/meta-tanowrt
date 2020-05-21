FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

PR_append = ".tano0"
EXTRA_OECMAKE_append = " -DCMAKE_BUILD_TYPE=Release "

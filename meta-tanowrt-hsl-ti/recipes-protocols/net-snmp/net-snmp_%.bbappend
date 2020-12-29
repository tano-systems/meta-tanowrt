#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
PR_append = ".ti0"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI_append = " \
    file://0001-IEC-62439-3-add-initial-support-for-hsr-prp-mib-supp.patch \
    file://0002-IEC-62439-3-add-a-dummy-lreInterfaceConfigTable-for-.patch \
    file://0003-IEC-62439-3-MIB-update-lreInterfaceConfigTable-for-M.patch \
    file://0004-IEC-62439-3-MIB-update-lreInterfaceTable-to-use-real.patch \
    file://0005-IEC-62439-3-MIB-refractor-code-for-re-use.patch \
    file://0006-IEC-62439-3-MIB-update-to-get-real-slave-admin-and-l.patch \
    file://0007-IEC-62439-3-MIB-add-functions-to-get-set-real-values.patch \
    file://0008-IEC-62439-3-MIB-Move-existing-lre-access-functions-t.patch \
    file://0009-IEC-62439-3-MIB-add-initial-version-of-stats-and-nod.patch \
    file://0010-IEC-62439-3-MIB-Add-support-for-lreNodesTable.patch \
    file://0011-IEC-62439-3-MIB-bug-fixes.patch \
    file://0012-IEC-62439-3-MIB-Fix-index-issue-in-table-implementat.patch \
    file://0013-IEC-62439-3-MIB-Add-command-to-set-HSR-mode.patch \
    file://0014-IEC-62439-3-MIB-add-license-header-to-sources.patch \
    file://0001-IEC-62439-3-MIB-fix-lreManufacturerName-displaying-d.patch \
    file://0001-IEC-62439-3-MIB-fix-duplicate-discard-mode-setting-f.patch \
    file://0001-hsr-prp-update-to-retrieve-mibs-from-main-interface.patch \
"

PACKAGECONFIG[iec-62439-3-mib] = "--with-mib-modules=iec-62439-3-mib,,,"
PACKAGECONFIG_append_ti33x = " iec-62439-3-mib"

RDEPENDS_${PN}-client += "perl-module-getopt-std \
                          perl-module-term-readline \
"

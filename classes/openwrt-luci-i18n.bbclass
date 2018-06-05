#
# LuCI languages (i18n) packages generation class
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

DEPENDS += "luci-po2lmo-native"

inherit openwrt-luci

LUCI_I18N_META_PACKAGE ?= "${PN}-i18n"
FILES_${LUCI_I18N_META_PACKAGE} = ""
ALLOW_EMPTY_${LUCI_I18N_META_PACKAGE} = "1"

PACKAGES =+ "${LUCI_I18N_META_PACKAGE}"
RDEPENDS_${PN} += "${LUCI_I18N_META_PACKAGE}"

PACKAGESPLITFUNCS_prepend = "split_i18n_packages "

# Language code titles
LUCI_LANG_ca="Català (Catalan)"
LUCI_LANG_cs="Čeština (Czech)"
LUCI_LANG_de="Deutsch (German)"
LUCI_LANG_el="Ελληνικά (Greek)"
LUCI_LANG_en="English"
LUCI_LANG_es="Español (Spanish)"
LUCI_LANG_fr="Français (French)"
LUCI_LANG_he="עִבְרִית (Hebrew)"
LUCI_LANG_hu="Magyar (Hungarian)"
LUCI_LANG_it="Italiano (Italian)"
LUCI_LANG_ja="日本語 (Japanese)"
LUCI_LANG_ko="한국어 (Korean)"
LUCI_LANG_ms="Bahasa Melayu (Malay)"
LUCI_LANG_no="Norsk (Norwegian)"
LUCI_LANG_pl="Polski (Polish)"
LUCI_LANG_pt_br="Português do Brasil (Brazialian Portuguese)"
LUCI_LANG_pt="Português (Portuguese)"
LUCI_LANG_ro="Română (Romanian)"
LUCI_LANG_ru="Русский (Russian)"
LUCI_LANG_sk="Slovenčina (Slovak)"
LUCI_LANG_sv="Svenska (Swedish)"
LUCI_LANG_tr="Türkçe (Turkish)"
LUCI_LANG_uk="украї́нська (Ukrainian)"
LUCI_LANG_vi="Tiếng Việt (Vietnamese)"
LUCI_LANG_zh_cn="中文 (Chinese)"
LUCI_LANG_zh_tw="臺灣華語 (Taiwanese)"

LUCI_PKG_SRC ?= "${S}"

def build_i18n_packages(d):
    import os
    import fnmatch
    import subprocess

    split_packages = set()

    source_dir = d.expand("${LUCI_PKG_SRC}")
    pkg        = d.expand("${PN}")

    # Generate LMO files and uci-defaults to package dir (not install dir)
    dest_dir = d.expand("${PKGD}")

    # Make LMO root directory
    lmo_root = os.path.join(d.getVar('LUCI_INSTALL_LUASRC_PATH', True), 'i18n')
    if not os.path.isdir(dest_dir + lmo_root):
        os.makedirs(dest_dir + lmo_root)

    # Make /etc/uci-defaults directory at destination
    uci_defaults = os.path.join(dest_dir +  d.expand("${sysconfdir}"), 'uci-defaults')
    if not os.path.isdir(uci_defaults):
        os.makedirs(uci_defaults)

    langs = d.getVar("LUCI_LANGUAGES", True)

    bb.debug(1, 'Making i18n pacakges for package %s' % pkg)

    def process_po_file(luci, lang, po_file):
        i18n_pkg = 'luci-i18n-%s-%s' % (luci['remain'], lang)

        bb.debug(1, '  >>> %s: started' % i18n_pkg)

        # Ignore languages not in LUCI_LANGUAGES variable
        if not lang in d.getVar('LUCI_LANGUAGES', True):
            bb.debug(1, '    > %s: skipping, not in LUCI_LANGUAGES list' % i18n_pkg)
            return

        lang_underscore = lang.replace('-', '_')
        lang_name       = d.getVar('LUCI_LANG_%s' % lang_underscore, True) or ''

        if not lang_name:
            return

        # Add i18n package
        if not i18n_pkg in d.getVar('PACKAGES', True):
            split_packages.add(i18n_pkg)

            description = "LuCI package '%s' translation (%s)" % (pkg, lang)

            d.prependVar('PACKAGES', '%s ' % i18n_pkg)
            d.setVar('RDEPENDS_%s' % i18n_pkg, pkg)
            d.setVar('DESCRIPTION_%s' % i18n_pkg, description)

            bb.debug(1, '    > %s: building' % i18n_pkg)

        bb.debug(1, '    > %s: %s' % (i18n_pkg, lang_name))

        # Write "uci set luci.languages.<lang_underscore>='<lang_name>'; uci commit luci"
        # to file "/etc/uci-defaults/luci-i18n-<luci['remain']-<lang>"
        f_name = os.path.join(uci_defaults, i18n_pkg)

        f = open(f_name, 'w')
        f.write("#!/bin/sh\n")
        f.write("uci set luci.languages.%s='%s'; uci commit luci\n" % (lang_underscore, lang_name))
        f.close()

        # Set 0755 mode to files in uci_defaults
        os.chmod(f_name, 0o755)

        bb.debug(1, '    > %s: writed /etc/uci-defaults' % i18n_pkg)

        # Add file to package
        d.prependVar('FILES_%s' % i18n_pkg, "${sysconfdir}/uci-defaults/%s " % i18n_pkg)

        # Source PO file
        po_full_file = os.path.join(luci['po_dir'], lang, po_file)

        # Destination LMO file
        lmo_file      = po_file.replace('.po', '.%s.lmo' % lang)
        lmo_full_file = os.path.join(dest_dir + lmo_root, lmo_file)

        # Compile LMO file
        po2lmo_cmd = [ 'po2lmo', po_full_file, lmo_full_file ]

        ret = subprocess.call(po2lmo_cmd)
        if ret != 0:
            bb.error("Can't execute po2lmo utility (%d)" % ret)

        # Add generated LMO to package
        if os.path.exists(lmo_full_file):
            bb.debug(1, '    > %s: compiled LMO' % i18n_pkg)
            d.prependVar('FILES_%s' % i18n_pkg, "%s " % os.path.join(lmo_root, lmo_file))
        else:
            bb.debug(1, '    > %s: LMO is empty' % i18n_pkg)

    def process_po_dir(root):
        luci = dict()
        if root == source_dir:
            luci['name'] = pkg
            luci['root'] = source_dir
        else:
            (luci['root'], luci['name']) = os.path.split(root)

        arr = luci['name'].split('-')
        if len(arr) < 2:
            return

        # Extract type (app, mod, lib, base, proto, theme, ...)
        luci['type'] = arr[1]

        # Make remaining part of package name (without luci-<type>-)
        if luci['type'] == 'base':
            luci['remain'] = luci['type']
        else:
            arr.pop(0)
            arr.pop(0)
            luci['remain'] = "-".join(arr)

        if root == source_dir:
            # po folder in root
            luci['po_dir'] = os.path.join(luci['root'], 'po')
        else:
            luci['po_dir'] = os.path.join(luci['root'], luci['name'], 'po')

        bb.debug(1, '> NAME   : %s' % luci['name'])
        bb.debug(1, '  ROOT   : %s' % luci['root'])
        bb.debug(1, '  PO_DIR : %s' % luci['po_dir'])
        bb.debug(1, '  TYPE   : %s' % luci['type'])
        bb.debug(1, '  REMAIN : %s' % luci['remain'])

        for po_root, po_dirs, po_files in os.walk(luci['po_dir']):
            for po_file in po_files:
                if fnmatch.fnmatch(po_file, "*.po"):
                    lang = os.path.basename(po_root)
                    process_po_file(luci, lang, po_file)

    for root, dirs, files in os.walk(source_dir):
        for basename in dirs:
            if fnmatch.fnmatch(basename, "po"):
                if not os.path.isdir(os.path.join(root, basename)):
                    continue

                process_po_dir(root)

    return list(split_packages)

python split_i18n_packages() {
    packages = build_i18n_packages(d)
    if packages:
        pkg = d.getVar('LUCI_I18N_META_PACKAGE', True)
        d.appendVar('RDEPENDS_' + pkg, ' '+' '.join(packages))
}

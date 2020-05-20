#

do_install_append() {
	install -d ${D}${sysconfdir}/modules-boot.d
}

#
# Override split_kernel_module_packages from
# kernel-module-split.bbclass of opemembedded-core layer.
#
# Modified handling of KERNEL_MODULE_AUTOLOAD
# and KERNEL_MODULE_PROBECONF for procd and ubox's modloader.
#
python split_kernel_module_packages () {
    import re

    modinfoexp = re.compile("([^=]+)=(.*)")

    def extract_modinfo(file):
        import tempfile, subprocess
        tempfile.tempdir = d.getVar("WORKDIR")
        tf = tempfile.mkstemp()
        tmpfile = tf[1]
        cmd = "%sobjcopy -j .modinfo -O binary %s %s" % (d.getVar("HOST_PREFIX") or "", file, tmpfile)
        subprocess.check_call(cmd, shell=True)
        f = open(tmpfile)
        l = f.read().split("\000")
        f.close()
        os.close(tf[0])
        os.unlink(tmpfile)
        vals = {}
        for i in l:
            m = modinfoexp.match(i)
            if not m:
                continue
            vals[m.group(1)] = m.group(2)
        return vals

    def get_module_conf(basename, modconflist):
        modconf = d.getVar('module_conf_%s' % basename)
        if modconf and basename in modconflist:
            # Remove "options <basename>" at beginning from modconf if it's present
            modconf = re.sub("^\s*options\s*%s\s*" % basename, "", modconf)
            return modconf
        elif modconf:
            bb.error("Please ensure module %s is listed in KERNEL_MODULE_PROBECONF since module_conf_%s is set" % (basename, basename))
        return None

    def frob_metadata(file, pkg, pattern, format, basename):
        vals = extract_modinfo(file)

        dvar = d.getVar('PKGD')

        # If autoloading is requested, output /etc/modules-load.d/<name>.conf and append
        # appropriate modprobe commands to the postinst
        autoloadlist = (d.getVar("KERNEL_MODULE_AUTOLOAD") or "").split()
        autoloadearlylist = (d.getVar("KERNEL_MODULE_AUTOLOAD_EARLY") or "").split()
        autoload = d.getVar('module_autoload_%s' % basename)
        if autoload and autoload == basename:
            bb.warn("module_autoload_%s was replaced by KERNEL_MODULE_AUTOLOAD for cases where basename == module name, please drop it" % basename)
        if autoload and basename not in autoloadlist:
            bb.warn("module_autoload_%s is defined but '%s' isn't included in KERNEL_MODULE_AUTOLOAD, please add it there" % (basename, basename))
        if basename in autoloadlist:
            modconflist = (d.getVar("KERNEL_MODULE_PROBECONF") or "").split()
            if basename in autoloadearlylist:
                name = '%s/etc/modules-boot.d/%s.conf' % (dvar, basename)
            else:
                name = '%s/etc/modules.d/%s.conf' % (dvar, basename)
            f = open(name, 'w')
            if autoload:
                for m in autoload.split():
                    # find configuration options for module
                    modconf = get_module_conf(m, modconflist)
                    if modconf is not None:
                        f.write('%s %s\n' % (m, modconf))
                    else:
                        f.write('%s\n' % m)
            else:
                # find configuration options for module
                modconf = get_module_conf(basename, modconflist)
                if modconf is not None:
                    f.write('%s %s\n' % (basename, modconf))
                else:
                    f.write('%s\n' % basename)
            f.close()
            postinst = d.getVar('pkg_postinst_%s' % pkg)
            if not postinst:
                bb.fatal("pkg_postinst_%s not defined" % pkg)
            postinst += d.getVar('autoload_postinst_fragment') % (autoload or basename)
            d.setVar('pkg_postinst_%s' % pkg, postinst)

        files = d.getVar('FILES_%s' % pkg)
        files = "%s /etc/modules-boot.d/%s.conf /etc/modules-load.d/%s.conf /etc/modprobe.d/%s.conf" % (files, basename, basename, basename)
        d.setVar('FILES_%s' % pkg, files)

        if "description" in vals:
            old_desc = d.getVar('DESCRIPTION_' + pkg) or ""
            d.setVar('DESCRIPTION_' + pkg, old_desc + "; " + vals["description"])

        rdepends = bb.utils.explode_dep_versions2(d.getVar('RDEPENDS_' + pkg) or "")
        modinfo_deps = []
        if "depends" in vals and vals["depends"] != "":
            for dep in vals["depends"].split(","):
                on = legitimize_package_name(dep)
                dependency_pkg = format % on
                modinfo_deps.append(dependency_pkg)
        for dep in modinfo_deps:
            if not dep in rdepends:
                rdepends[dep] = []
        d.setVar('RDEPENDS_' + pkg, bb.utils.join_deps(rdepends, commasep=False))

        # Avoid automatic -dev recommendations for modules ending with -dev.
        d.setVarFlag('RRECOMMENDS_' + pkg, 'nodeprrecs', 1)

        # Provide virtual package without postfix
        providevirt = d.getVar('KERNEL_MODULE_PROVIDE_VIRTUAL')
        if providevirt == "1":
           postfix = format.split('%s')[1]
           d.setVar('RPROVIDES_' + pkg, pkg.replace(postfix, ''))

    module_regex = '^(.*)\.k?o$'

    module_pattern_prefix = d.getVar('KERNEL_MODULE_PACKAGE_PREFIX')
    module_pattern_suffix = d.getVar('KERNEL_MODULE_PACKAGE_SUFFIX')
    module_pattern = module_pattern_prefix + 'kernel-module-%s' + module_pattern_suffix

    postinst = d.getVar('pkg_postinst_modules')
    postrm = d.getVar('pkg_postrm_modules')

    modules = do_split_packages(d, root='${nonarch_base_libdir}/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='kernel-%s' % (d.getVar("KERNEL_VERSION")))
    if modules:
        metapkg = d.getVar('KERNEL_MODULES_META_PACKAGE')
        d.appendVar('RDEPENDS_' + metapkg, ' '+' '.join(modules))

    # If modules-load.d, modules-boot.d and modprobe.d are empty at this point,
    # remove them to avoid warnings. removedirs only raises an OSError
    # if an empty directory cannot be removed.
    dvar = d.getVar('PKGD')
    for dir in ["%s/etc/modules-boot.d" % (dvar), "%s/etc/modprobe.d" % (dvar), "%s/etc/modules-load.d" % (dvar), "%s/etc" % (dvar)]:
        if len(os.listdir(dir)) == 0:
            os.rmdir(dir)
}

def kernel_full_version(version):
    parts = version.split("-", 1)
    return parts[0]

#
# SPDX-License-Identifier: MIT
#

do_install_append() {
	install -d ${D}${sysconfdir}/modules-boot.d
	install -d ${D}${sysconfdir}/modules.d
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
        compressed = re.match( r'.*\.([xg])z$', file)
        tf = tempfile.mkstemp()
        tmpfile = tf[1]
        if compressed:
            tmpkofile = tmpfile + ".ko"
            if compressed.group(1) == 'g':
                cmd = "gunzip -dc %s > %s" % (file, tmpkofile)
                subprocess.check_call(cmd, shell=True)
            elif compressed.group(1) == 'x':
                cmd = "xz -dc %s > %s" % (file, tmpkofile)
                subprocess.check_call(cmd, shell=True)
            else:
                msg = "Cannot decompress '%s'" % file
                raise msg
            cmd = "%sobjcopy -j .modinfo -O binary %s %s" % (d.getVar("HOST_PREFIX") or "", tmpkofile, tmpfile)
        else:
            cmd = "%sobjcopy -j .modinfo -O binary %s %s" % (d.getVar("HOST_PREFIX") or "", file, tmpfile)
        subprocess.check_call(cmd, shell=True)
        # errors='replace': Some old kernel versions contain invalid utf-8 characters in mod descriptions (like 0xf6, 'ö')
        f = open(tmpfile, errors='replace')
        l = f.read().split("\000")
        f.close()
        os.close(tf[0])
        os.unlink(tmpfile)
        if compressed:
            os.unlink(tmpkofile)
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
        files = "%s /etc/modules-boot.d/%s.conf /etc/modules.d/%s.conf /etc/modules-load.d/%s.conf /etc/modprobe.d/%s.conf" % (files, basename, basename, basename, basename)
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

    kernel_package_name = d.getVar("KERNEL_PACKAGE_NAME") or "kernel"
    kernel_version = d.getVar("KERNEL_VERSION")

    module_regex = r'^(.*)\.k?o(?:\.[xg]z)?$'

    module_pattern_prefix = d.getVar('KERNEL_MODULE_PACKAGE_PREFIX')
    module_pattern_suffix = d.getVar('KERNEL_MODULE_PACKAGE_SUFFIX')
    module_pattern = module_pattern_prefix + kernel_package_name + '-module-%s' + module_pattern_suffix

    postinst = d.getVar('pkg_postinst_modules')
    postrm = d.getVar('pkg_postrm_modules')

    modules = do_split_packages(d, root='${nonarch_base_libdir}/modules', file_regex=module_regex, output_pattern=module_pattern, description='%s kernel module', postinst=postinst, postrm=postrm, recursive=True, hook=frob_metadata, extra_depends='%s-%s' % (kernel_package_name, kernel_version))
    if modules:
        metapkg = d.getVar('KERNEL_MODULES_META_PACKAGE')
        d.appendVar('RDEPENDS_' + metapkg, ' '+' '.join(modules))

    # If modules-load.d, modules-boot.d and modprobe.d are empty at this point,
    # remove them to avoid warnings. removedirs only raises an OSError
    # if an empty directory cannot be removed.
    dvar = d.getVar('PKGD')
    for dir in ["%s/etc/modules-boot.d" % (dvar), "%s/etc/modules.d" % (dvar), "%s/etc/modprobe.d" % (dvar), "%s/etc/modules-load.d" % (dvar), "%s/etc" % (dvar)]:
        if len(os.listdir(dir)) == 0:
            os.rmdir(dir)
}

def kernel_full_version(version):
    parts = version.split("-", 1)
    return parts[0]

def kernel_dtb2dts(var, d):
    value = d.getVar(var, True) or ''
    return value.replace('.dtb', '.dts')

KERNEL_DEVICETREE_COPY ?= ""
KERNEL_DEVICETREE_COPY_DST_mipsarch ?= "${S}/arch/mips/boot/dts"
KERNEL_DEVICETREE_COPY_DST_microblaze ?= "${S}/arch/microblaze/boot/dts"
KERNEL_DEVICETREE_COPY_DST_aarch64 ?= "${S}/arch/arm64/boot/dts"
KERNEL_DEVICETREE_COPY_DST_arm ?= "${S}/arch/arm/boot/dts"

do_configure_append() {
	if [ ! -z "${KERNEL_DEVICETREE_COPY_DST}" ]; then
		for f in ${KERNEL_DEVICETREE_COPY}; do
			bbdebug 1 "${f} (copy ${f} to ${KERNEL_DEVICETREE_COPY_DST}/${f})"
			if [ -e "${KERNEL_DEVICETREE_COPY_DST}/${f}" ]; then
				rm -f ${KERNEL_DEVICETREE_COPY_DST}/${f}.orig
				mv ${KERNEL_DEVICETREE_COPY_DST}/${f} ${KERNEL_DEVICETREE_COPY_DST}/${f}.orig
			fi
			install -D -m 0644 ${WORKDIR}/${f} \
			                   ${KERNEL_DEVICETREE_COPY_DST}/${f}
		done
	fi
}

# LINUX_LOCALVERSION can be set to add a tag to the end of the
# kernel version string. such as the commit id
def get_localversion(d):
    pr = d.getVar("PR")
    srcrev = d.getVar("KERNEL_SRC_SRCREV")
    localversion = ""
    if srcrev and srcrev != "":
        localversion += "-g%s" % (srcrev[:10])
    if pr and pr != "":
        localversion += "-%s" % (pr)

    return localversion

LINUX_LOCALVERSION ?= "${@get_localversion(d)}"

do_configure_append() {
	echo ${LINUX_LOCALVERSION} > ${B}/.scmversion
	echo ${LINUX_LOCALVERSION} > ${S}/.scmversion
}

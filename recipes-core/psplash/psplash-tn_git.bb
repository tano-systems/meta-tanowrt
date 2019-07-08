#
# Based on original psplash_git.bb from OE core layer.
#
# This file Copyright (c) 2019 Tano Systems. All Rights Reserved.
# Author: Anton Kikin <a.kikin@tano-systems.com>
#
SUMMARY = "Userspace framebuffer boot logo based on usplash"
DESCRIPTION = "PSplash is a userspace graphical boot splash screen for mainly \
embedded Linux devices supporting a 16bpp or 32bpp framebuffer. It has few dependencies \
(just libc), supports basic images and text and handles rotation. Its visual look is \
configurable by basic source changes. Also included is a 'client' command utility for \
sending information to psplash such as boot progress information."
HOMEPAGE = "https://github.com/tano-systems/psplash-tn"
SECTION = "base"
LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://psplash.h;beginline=1;endline=16;md5=8228f7969c37755b722da0419276f343"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

RPROVIDES_${PN} = "psplash"
RREPLACES_${PN} = "psplash"
RCONFLICTS_${PN} = "psplash"

SRCREV = "243dba3c734811e803657e4d7dc8efb81f1a5fa2"
PV = "0.1+git${SRCPV}"
PR = "tano2"

PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "git://github.com/tano-systems/psplash-tn;branch=tano/master \
           file://psplash.init \
           file://psplash.config \
           ${SPLASH_IMAGES}"

UPSTREAM_CHECK_COMMITS = "1"

SPLASH_IMAGES = "\
	file://psplash-tano-black-h.png;name=tano-h;outsuffix=tano-h;alt_priority=101 \
	file://psplash-tano-black-v.png;name=tano-v;outsuffix=tano-v;alt_priority=102 \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "psplash-tn"
OPENWRT_SERVICE_SCRIPTS_psplash-tn ?= "psplash"
OPENWRT_SERVICE_STATE_psplash-tn-psplash ?= "enabled"

python __anonymous() {
    oldpkgs = d.getVar("PACKAGES").split()
    splashfiles = d.getVar('SPLASH_IMAGES').split()
    pkgs = []
    localpaths = []
    names = []
    haspng = False
    for uri in splashfiles:
        fetcher = bb.fetch2.Fetch([uri], d)
        flocal = os.path.basename(fetcher.localpath(uri))
        fbase = os.path.splitext(flocal)[0]
        outsuffix = fetcher.ud[uri].parm.get("outsuffix")
        if not outsuffix:
            if fbase.startswith("psplash-"):
                outsuffix = fbase[8:]
            else:
                outsuffix = fbase
            if outsuffix.endswith('-img'):
                outsuffix = outsuffix[:-4]
        outname = "psplash-%s" % outsuffix
        if outname == '' or outname in oldpkgs:
            bb.fatal("The output name '%s' derived from the URI %s is not valid, please specify the outsuffix parameter" % (outname, uri))
        else:
            pkgs.append(outname)
        if flocal.endswith(".png"):
            haspng = True
        alt_priority = fetcher.ud[uri].parm.get("alt_priority")
        if alt_priority:
            d.setVar("ALTERNATIVE_PRIORITY_%s" % outname, alt_priority)
        localpaths.append(flocal)
        name = fetcher.ud[uri].parm.get("name")
        if not name:
            name = None
        names.append(name)

    # Set these so that we have less work to do in do_compile and do_install_append
    d.setVar("SPLASH_INSTALL", " ".join(pkgs))
    d.setVar("SPLASH_LOCALPATHS", " ".join(localpaths))
    d.setVar("SPLASH_NAMES", " ".join(names))

    if haspng:
        d.appendVar("DEPENDS", " gdk-pixbuf-native")

    d.prependVar("PACKAGES", "%s " % (" ".join(pkgs)))
    mlprefix = d.getVar('MLPREFIX') or ''
    pn = d.getVar('PN') or ''
    for p in pkgs:
        ep = '%s%s' % (mlprefix, p)
        epsplash = '%s%s' % (mlprefix, 'psplash')
        d.setVar("FILES_%s" % ep, "${bindir}/%s" % p)
        d.setVar("ALTERNATIVE_%s" % ep, 'psplash')
        d.setVarFlag("ALTERNATIVE_TARGET_%s" % ep, 'psplash', '${bindir}/%s' % p)
        d.appendVar("RDEPENDS_%s" % ep, " %s" % pn)
        if p == "psplash-default":
            d.appendVar("RRECOMMENDS_%s" % pn, " %s" % ep)
}

S = "${WORKDIR}/git"

inherit autotools pkgconfig update-alternatives

ALTERNATIVE_PRIORITY = "100"
ALTERNATIVE_LINK_NAME[psplash] = "${bindir}/psplash"

def psplash_apply_config(d, name, filename):
    import subprocess
    colors = [
        "PSPLASH_BACKGROUND_COLOR",
        "PSPLASH_TEXT_COLOR",
        "PSPLASH_BAR_COLOR",
        "PSPLASH_BAR_BACKGROUND_COLOR",
        "PSPLASH_BAR_BORDER_COLOR",
        "PSPLASH_BAR_BORDER_SPACE_COLOR",
    ]
    options = [
        "PSPLASH_STARTUP_MSG",
        "PSPLASH_IMG_FULLSCREEN",
        "PSPLASH_IMG_SPLIT_NUMERATOR",
        "PSPLASH_IMG_SPLIT_DENOMINATOR",
        "PSPLASH_MSG_PADDING",
        "PSPLASH_BAR_WIDTH",
        "PSPLASH_BAR_HEIGHT",
        "PSPLASH_BAR_BORDER_WIDTH",
        "PSPLASH_BAR_BORDER_SPACE",
    ]

    bb.debug(1, "Configuration for [%s]:" % name)
    sed_cmd_colors = [ 'sed', '-i', '-E' ]
    sed_cmd_options = [ 'sed', '-i', '-E' ]
    for opt in colors + options:
        value = d.getVar(opt, True)
        varflags = d.getVarFlags(opt) or {}
        if varflags:
            if name and name in varflags.keys():
                value = d.expand(varflags[name])
            else:
                if filename in varflags.keys():
                    value = d.expand(varflags[filename])
                else:
                    searchname = os.path.splitext(filename)[0]
                    if searchname in varflags.keys():
                        value = d.expand(varflags[searchname])
        bb.debug(1, "  %-32s = \"%s\"" % (opt, value))
        regexp = "s/(\#define\s+%s)\s+(\\\")?.*/\\1 \\2%s\\2/" % (opt, value)
        if opt in colors:
            sed_cmd_colors.append('-e')
            sed_cmd_colors.append(regexp)
        else:
            sed_cmd_options.append('-e')
            sed_cmd_options.append(regexp)

    sed_cmd_colors.append("%s/psplash-colors.h" % d.getVar('S'))
    sed_cmd_options.append("%s/psplash-config.h" % d.getVar('S'))
    ret = subprocess.call(sed_cmd_colors)
    if ret != 0:
        bb.error("Can't apply configuration to psplash-colors.h (%d)" % ret)
    ret = subprocess.call(sed_cmd_options)
    if ret != 0:
        bb.error("Can't apply configuration to psplash-config.h (%d)" % ret)

python do_compile () {
    import shutil
    import subprocess
    import shlex

    # Build a separate executable for each splash image
    workdir = d.getVar('WORKDIR')
    convertscript = "%s/make-image-header.sh" % d.getVar('S')
    destfile = "%s/psplash-poky-img.h" % d.getVar('S')
    localfiles = d.getVar('SPLASH_LOCALPATHS').split()
    outputfiles = d.getVar('SPLASH_INSTALL').split()
    names = d.getVar('SPLASH_NAMES').split()
    for localfile, outputfile, name in zip(localfiles, outputfiles, names):
        if localfile.endswith(".png"):
            subprocess.call(shlex.split('%s %s POKY' % (convertscript, os.path.join(workdir, localfile))))
            fbase = os.path.splitext(localfile)[0]
            shutil.copyfile("%s-img.h" % fbase, destfile)
        else:
            shutil.copyfile(os.path.join(workdir, localfile), destfile)
        # Apply configuration
        psplash_apply_config(d, name, localfile)
        # For some reason just updating the header is not enough, we have to touch the .c
        # file in order to get it to rebuild
        os.utime("%s/psplash.c" % d.getVar('S'), None)
        bb.build.exec_func("oe_runmake", d)
        shutil.copyfile("psplash", outputfile)
}

do_install_append() {
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/psplash.init ${D}${sysconfdir}/init.d/psplash

	install -d ${D}${sysconfdir}/config/
	install -m 0755 ${WORKDIR}/psplash.config ${D}${sysconfdir}/config/psplash

	install -d ${D}${bindir}
	for i in ${SPLASH_INSTALL} ; do
		install -m 0755 $i ${D}${bindir}/$i
	done
	rm -f ${D}${bindir}/psplash
}

################################################################################
#
# Color variables:
#
# - PSPLASH_BACKGROUND_COLOR:
#   This is the overall background color
#
# - PSPLASH_TEXT_COLOR:
#   This is the color of any text output
#
# - PSPLASH_BAR_COLOR:
#   This is the color of the progress bar indicator
#
# - PSPLASH_BAR_BACKGROUND_COLOR:
#   This is the color of the progress bar background
#
# - PSPLASH_BAR_BORDER_COLOR:
#   This is the color of the progress bar border
#
# - PSPLASH_BAR_BORDER_SPACE_COLOR:
#   This is the color of the space between progress bar and its border
#
# Configuration variables:
#
# - PSPLASH_STARTUP_MSG:
#   Text to output on program start; if undefined, output nothing
#
# - PSPLASH_IMG_FULLSCREEN:
#   Bool indicating if the image is fullscreen, as opposed to split screen
#
# - PSPLASH_IMG_SPLIT_NUMERATOR:
#   Position of the image split from top edge, numerator of fraction
#
# - PSPLASH_IMG_SPLIT_DENOMINATOR:
#   Position of the image split from top edge, denominator of fraction
#
# - PSPLASH_MSG_PADDING:
#   Padding for message (space in pixels between message and progress bar)
#
# - PSPLASH_BAR_WIDTH:
#   Progress bar width (in pixels)
#
# - PSPLASH_BAR_HEIGHT:
#   Progress bar height (in pixels)
#
# - PSPLASH_BAR_BORDER_WIDTH:
#   Progress bar border thickness (in pixels)
#
# - PSPLASH_BAR_BORDER_SPACE:
#   Size in pixels between progress bar and its border
#
# You can customize these variables for each splash image separately.
# To do this, use the following form:
#
#   CONFIG_VAR[splash-image-filename] = <value>
#
# or
#
#   CONFIG_VAR[name-specified-in-SPLASH_IMAGES] = <value>
#
# For example:
#
#   SPLASH_IMAGES = "\
#       file://for-example-h.png;outsuffix=example-h;alt_priority=101 \
#       file://for-example-v.png;name=exv;outsuffix=example-v;alt_priority=102 \
#   "
#
#   PSPLASH_STARTUP_MSG[for-example-h.png] = "Booting example-h system..."
#   PSPLASH_STARTUP_MSG[exv] = "Booting example-v system..."
#
################################################################################

PSPLASH_BACKGROUND_COLOR       ?= "0x00,0x00,0x00"
PSPLASH_TEXT_COLOR             ?= "0xbb,0xbb,0xbb"
PSPLASH_BAR_COLOR              ?= "0x88,0x88,0x88"
PSPLASH_BAR_BACKGROUND_COLOR   ?= "0x44,0x44,0x44"
PSPLASH_BAR_BORDER_COLOR       ?= "0xbb,0xbb,0xbb"
PSPLASH_BAR_BORDER_SPACE_COLOR ?= "0x00,0x00,0x00"
PSPLASH_STARTUP_MSG            ?= "Booting system, please wait..."
PSPLASH_IMG_FULLSCREEN         ?= "0"
PSPLASH_IMG_SPLIT_NUMERATOR    ?= "5"
PSPLASH_IMG_SPLIT_DENOMINATOR  ?= "6"
PSPLASH_MSG_PADDING            ?= "4"
PSPLASH_BAR_WIDTH              ?= "340"
PSPLASH_BAR_HEIGHT             ?= "30"
PSPLASH_BAR_BORDER_WIDTH       ?= "2"
PSPLASH_BAR_BORDER_SPACE       ?= "2"

do_compile[vardeps] += "\
	PSPLASH_BACKGROUND_COLOR \
	PSPLASH_TEXT_COLOR \
	PSPLASH_BAR_COLOR \
	PSPLASH_BAR_BACKGROUND_COLOR \
	PSPLASH_BAR_BORDER_COLOR \
	PSPLASH_BAR_BORDER_SPACE_COLOR \
	PSPLASH_STARTUP_MSG \
	PSPLASH_IMG_FULLSCREEN \
	PSPLASH_IMG_SPLIT_NUMERATOR \
	PSPLASH_IMG_SPLIT_DENOMINATOR \
	PSPLASH_MSG_PADDING \
	PSPLASH_BAR_WIDTH \
	PSPLASH_BAR_HEIGHT \
	PSPLASH_BAR_BORDER_WIDTH \
	PSPLASH_BAR_BORDER_SPACE \
"

################################################################################

FILES_${PN} += "${sysconfdir}/psplash-init.d"

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/psplash \
"

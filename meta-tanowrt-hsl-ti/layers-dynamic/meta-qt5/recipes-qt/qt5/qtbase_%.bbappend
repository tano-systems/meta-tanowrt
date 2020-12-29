#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

GLES_EXTRA_DEPS = "libdrm wayland"

PACKAGECONFIG[gles2] = "-opengl es2 -eglfs,,virtual/libgles2 virtual/egl ${GLES_EXTRA_DEPS}"

PR_append = ".ti0"

QT_CONFIG_FLAGS += "-qpa ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland', 'eglfs', d)}"

QT_EGLFS_PATCHES = "\
    file://0001-calculator-Add-exit-button-for-non-window-environmen.patch \
    file://0002-animatedtiles-Add-exit-button-for-non-window-environ.patch \
    file://quit.png \
"

SRC_URI += "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', '', "${QT_EGLFS_PATCHES}", d)}\
    file://0001-deform-Fix-how-controls-are-shown.patch \
    file://0001-qtbase-plugins-platforms-eglfs_kms-fix-compiler-erro.patch \
"

python do_patch_append() {
    import shutil

    work_dir = d.getVar("WORKDIR")
    s = d.getVar("S")

    if not bb.utils.contains('DISTRO_FEATURES','wayland',True,False,d):
        shutil.copy(os.path.join(work_dir,"quit.png"),os.path.join(s,"examples/widgets/animation/animatedtiles/images/"))
}

# Add symbolic link qt5/examples for backward compatibility
do_install_append () {
    install -d ${D}${datadir}/qt5
    ln -sf ../examples ${D}${datadir}/qt5/examples
}

FILES_${PN}-examples +=  "${datadir}/qt5/*"

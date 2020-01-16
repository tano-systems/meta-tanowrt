#
# Copyright (c) 2018-2019, Anton Kikin <a.kikin@tano-systems.com>
#
# Originally based on the code from Angstrom Distribution
# (https://github.com/Angstrom-distribution/meta-angstrom.git)
#
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

PV = "${DISTRO_VERSION}"
PR = "tano1"

SRC_URI = "file://os-release"

PACKAGES = "${PN}"
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit tano-scmrevision
inherit openwrt-version

def get_layers(bb, d):
    layers = (d.getVar("BBLAYERS", 1) or "").split()
    layers_branch_rev = ["  %-26s = %s:%s" % (os.path.basename(i), \
        base_get_metadata_git_branch(i, None).strip().strip('()'), \
        base_get_metadata_git_revision(i, None)) \
        for i in layers]
    i = len(layers_branch_rev)-1
    p1 = layers_branch_rev[i].find("=")
    s1 = layers_branch_rev[i][p1:]
    while i > 0:
        p2 = layers_branch_rev[i-1].find("=")
        s2 = layers_branch_rev[i-1][p2:]
        if s1 == s2:
            layers_branch_rev[i-1] = layers_branch_rev[i-1][0:p2]
            i -= 1
        else:
            i -= 1
            p1 = layers_branch_rev[i].find("=")
            s1 = layers_branch_rev[i][p1:]

    layertext = "Configured OpenEmbedded layers:\n%s\n" % '\n'.join(layers_branch_rev)
    layertext = layertext.replace('<','')
    layertext = layertext.replace('>','')
    return layertext

do_install() {
	install -d ${D}${libdir}
	install -d ${D}${sysconfdir}

	install -m 0644 ${WORKDIR}/os-release ${D}${libdir}/os-release
	ln -s ${libdir}/os-release ${D}${sysconfdir}/os-release

	# Run VERSION_SED script
	${OPENWRT_VERSION_SED} \
		${D}/usr/lib/os-release \

	echo "TanoWrt Distribution ${DISTRO_VERSION}" > ${D}${sysconfdir}/tano-version
	echo "Built from branch: ${TANOWRT_SCM_BRANCH}" >> ${D}${sysconfdir}/tano-version
	echo "Revision: ${TANOWRT_SCM_REVISION}" >> ${D}${sysconfdir}/tano-version
	echo "Target system: ${TARGET_SYS}" >> ${D}${sysconfdir}/tano-version

	echo "${@get_layers(bb, d)}" > ${D}${sysconfdir}/tano-build-info
}

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

INSANE_SKIP_${PN} += "build-deps"

RPROVIDES_${PN} = "os-release"
RREPLACES_${PN} = "os-release"
RCONFLICTS_${PN} = "os-release"

FILES_${PN} += "${libdir}"

#
# SPDX-License-Identifier: MIT
#
# Copyright (C) 2021-2022 Tano Systems LLC. All rights reserved.
# Authors: Anton Kikin <a.kikin@tano-systems.com>
#

#
# This recipe allows to use the format to specify in the machine:
#
# AT91BOOTSTRAP_BUILD_CONFIG ??= "config1 config2"
# AT91BOOTSTRAP_BUILD_CONFIG[config1] = "config1_defconfig"
# AT91BOOTSTRAP_BUILD_CONFIG[config2] = "config2_defconfig"
#

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

COMPATIBLE_MACHINE:append = "|evb-ksz9477|evb-ksz9563|at91-sama5d2-xplained|at91-sama5d3-xplained"

DEPENDS += "python3-native"

PR:append = ".tano2"
AT91BOOTSTRAP_LOCALVERSION = "-git${SRCPV}-${PR}"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"
do_configure[cleandirs] = "${B}"

python () {
    at91machine = d.getVar('AT91BOOTSTRAP_MACHINE')
    at91buildconfig = (d.getVar('AT91BOOTSTRAP_BUILD_CONFIG') or "").split()
    at91buildconfigflags = d.getVarFlags('AT91BOOTSTRAP_BUILD_CONFIG')

    if not at91machine and not at91buildconfig:
        raise bb.parse.SkipRecipe("Either AT91BOOTSTRAP_MACHINE or AT91BOOTSTRAP_BUILD_CONFIG must be set in the %s machine configuration." % d.getVar("MACHINE"))

    if at91machine and at91buildconfig:
        raise bb.parse.SkipRecipe("You cannot use AT91BOOTSTRAP_MACHINE and AT91BOOTSTRAP_BUILD_CONFIG at the same time.")

    if len(at91buildconfig) > 0:
        for config in at91buildconfig:
            for f, v in at91buildconfigflags.items():
                if config == f: 
                    items = v.split(',')
                    if items[0] and len(items) > 1:
                        raise bb.parse.SkipRecipe('Only config can be specified!')
                    d.appendVar('AT91BOOTSTRAP_MACHINE', ' ' + items[0])
                    break

    at91machine = d.getVar('AT91BOOTSTRAP_MACHINE')
    if at91machine:
        for config in at91machine.split():
            d.appendVar("SRC_URI", " file://%s " % config)
}

do_configure() {
	if [ -n "${AT91BOOTSTRAP_BUILD_CONFIG}" ]; then
		unset i j
		for config in ${AT91BOOTSTRAP_MACHINE}; do
			i=$(expr $i + 1)
			for type in ${AT91BOOTSTRAP_BUILD_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]; then
					mkdir -p ${B}/${config}

					rm -f "${S}/.config"
					if [ -f "${WORKDIR}/${config}" ]; then
						cp "${WORKDIR}/${config}" "${B}/${config}/.config"
					fi

					if [ ! -f "${B}/${config}/.config" ]; then
						bbfatal "No config files found"
					fi
				fi
			done
			unset j
		done
		unset i
	else
		# Copy board defconfig to .config if .config does not exist. This
		# allows recipes to manage the .config themselves in
		# do_configure:prepend().
		if [ -f "${S}/board/${AT91BOOTSTRAP_MACHINE}/${AT91BOOTSTRAP_TARGET}" ] && [ ! -f "${B}/.config" ]; then
			cp "${S}/board/${AT91BOOTSTRAP_MACHINE}/${AT91BOOTSTRAP_TARGET}" "${B}/.config"
		fi

		# Copy defconfig to .config if .config does not exist. This allows
		# recipes to manage the .config themselves in do_configure:prepend()
		# and to override default settings with a custom file.
		if [ -f "${WORKDIR}/defconfig" ] && [ ! -f "${B}/.config" ]; then
			cp "${WORKDIR}/defconfig" "${B}/.config"
		fi

		if [ ! -f "${S}/.config" ]; then
			bbfatal "No config files found"
		fi

		cml1_do_configure
	fi
}

do_compile() {
	if [ "${@bb.utils.contains('DISTRO_FEATURES', 'ld-is-gold', 'ld-is-gold', '', d)}" = "ld-is-gold" ] ; then
		sed -i 's/$(CROSS_COMPILE)ld$/$(CROSS_COMPILE)ld.bfd/g' ${S}/Makefile
	fi

	# Always write localversion to .scmversion
	echo ${AT91BOOTSTRAP_LOCALVERSION} > ${B}/.scmversion
	echo ${AT91BOOTSTRAP_LOCALVERSION} > ${S}/.scmversion

	if [ -n "${AT91BOOTSTRAP_BUILD_CONFIG}" ]; then
		unset i j
		for config in ${AT91BOOTSTRAP_MACHINE}; do
			i=$(expr $i + 1)
			for type in ${AT91BOOTSTRAP_BUILD_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]; then
					unset CFLAGS CPPFLAGS LDFLAGS
					oe_runmake -C ${S} mrproper
					cp "${B}/${config}/.config" "${S}/.config"
					unset CFLAGS CPPFLAGS CXXFLAGS LDFLAGS
					yes '' | oe_runmake -C ${S} oldconfig
					oe_runmake -C ${S} BINDIR=${B}/${config}
				fi
			done
			unset j
		done
		unset i
	else
		unset CFLAGS CPPFLAGS LDFLAGS
		oe_runmake
	fi
}

AT91BOOTSTRAP_PMECC_HEADER ?= "0"

do_deploy() {
	install -d ${DEPLOYDIR}
	cd ${DEPLOYDIR}

	if [ -n "${AT91BOOTSTRAP_BUILD_CONFIG}" ]; then
		unset i j
		for config in ${AT91BOOTSTRAP_MACHINE}; do
			i=$(expr $i + 1)
			for type in ${AT91BOOTSTRAP_BUILD_CONFIG}; do
				j=$(expr $j + 1);
				if [ $j -eq $i ]; then
					install ${B}/${config}/${AT91BOOTSTRAP_BINARY} \
					        ${DEPLOYDIR}/${AT91BOOTSTRAP_BINARY}-${type}
					rm -f boot.bin-${type} BOOT.BIN-${type}
					ln -sf ${AT91BOOTSTRAP_BINARY}-${type} boot.bin-${type}

					if [ "${AT91BOOTSTRAP_PMECC_HEADER}" = "1" ]; then
						# Generate binary with PMECC header
						cd ${S}/scripts && python3 addpmecchead.py \
							"${B}/${config}/.config" "${DEPLOYDIR}/"

						rm -f boot.bin-${type}-pmecc BOOT.BIN-${type}-pmecc
						rm -f "${DEPLOYDIR}/${AT91BOOTSTRAP_BINARY}-${type}-pmecc"

						if [ -f "${DEPLOYDIR}/pmecc.tmp" ]; then
							cat "${DEPLOYDIR}/pmecc.tmp" \
								"${B}/${config}/${AT91BOOTSTRAP_BINARY}" \
								> "${DEPLOYDIR}/${AT91BOOTSTRAP_BINARY}-${type}-pmecc"
							rm -f "${DEPLOYDIR}/pmecc.tmp"
						else
							cp -vf "${B}/${config}/${AT91BOOTSTRAP_BINARY}" \
							       "${DEPLOYDIR}/${AT91BOOTSTRAP_BINARY}-${type}-pmecc"
						fi

						ln -sf ${AT91BOOTSTRAP_BINARY}-${type}-pmecc boot.bin-${type}-pmecc
					fi
				fi
			done
			unset j
		done
		unset i
	else
		install ${S}/${AT91BOOTSTRAP_BIN_PATH}/${AT91BOOTSTRAP_BINARY} ${DEPLOYDIR}/${AT91BOOTSTRAP_IMAGE}

		rm -f ${AT91BOOTSTRAP_BINARY} ${AT91BOOTSTRAP_SYMLINK}
		ln -sf ${AT91BOOTSTRAP_IMAGE} ${AT91BOOTSTRAP_SYMLINK}
		ln -sf ${AT91BOOTSTRAP_IMAGE} ${AT91BOOTSTRAP_BINARY}

		# Create a symlink ready for file copy on SD card
		rm -f boot.bin BOOT.BIN
		ln -sf ${AT91BOOTSTRAP_IMAGE} BOOT.BIN
	fi
}

#
# Generate /usr/share/locale/locale.alias
#
ROOTFS_POSTPROCESS_COMMAND += "rootfs_gen_locale_alias; "

rootfs_gen_locale_alias() {
	#
	# Generate locale aliases in /usr/share/locale/locale.alias.
	# For example, for ru_RU.UTF-8 will be generated these aliases:
	#   ru_RU -> ru_RU.UTF-8
	#   ru    -> ru_RU.UTF-8
	#
	for LOCALE in ${GLIBC_GENERATE_LOCALES}; do
		TRUNCATED=${LOCALE%.*}
		if [ "${TRUNCATED}" != "${LOCALE}" ]; then
			echo "${TRUNCATED} ${LOCALE}" >> ${IMAGE_ROOTFS}${datadir}/locale/locale.alias
			XTRUNCATED=${TRUNCATED%_*}
			if [ "${XTRUNCATED}" != "${TRUNCATED}" ]; then
				echo "${XTRUNCATED} ${LOCALE}" >> ${IMAGE_ROOTFS}${datadir}/locale/locale.alias
			fi
		fi
	done
}

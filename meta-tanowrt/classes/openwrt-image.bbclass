#
inherit core-image
inherit openwrt
inherit tanowrt-kmods

IMAGE_OVERLAY_NAME_SUFFIX = ".overlay"

IMAGE_OVERHEAD_FACTOR = "1"

#
# Available IMAGE_FEATURES:
#
# - wifi
# - cgroup
# - perftools
#
FEATURE_PACKAGES_cgroup = "packagegroup-tanowrt-cgroup"
FEATURE_PACKAGES_wifi = "packagegroup-tanowrt-wifi"
FEATURE_PACKAGES_perftools = "packagegroup-tanowrt-perftools"

#
# Create empty overlay image
#
IMAGE_CMD_ubifs_append() {
	# Do not remove this comment. Original IMAGE_CMD_ubifs has no linebreak at end
	mkfs.ubifs -o ${IMGDEPLOYDIR}/${IMAGE_NAME}${IMAGE_OVERLAY_NAME_SUFFIX}.ubifs ${MKUBIFS_ARGS_OVERLAY}
	bbplain "Created empty UBIFS overlay image"
}

#
# Create symlink to the newly created overlay image
#
python create_symlinks_append() {
    type = "ubifs"
    imgsuffix = d.getVarFlag("do_" + taskname, 'imgsuffix', True) or d.expand("${IMAGE_OVERLAY_NAME_SUFFIX}.")
    dst = os.path.join(deploy_dir, link_name + d.expand("${IMAGE_OVERLAY_NAME_SUFFIX}") + "." + type)
    src = img_name + imgsuffix + type
    if os.path.exists(os.path.join(deploy_dir, src)):
        bb.note("Creating symlink: %s -> %s" % (dst, src))
        if os.path.islink(dst):
            os.remove(dst)
        os.symlink(src, dst)
    else:
        bb.note("Skipping symlink, source does not exist: %s -> %s" % (dst, src))
}

ROOTFS_GEN_TIMESTAMP = "${@time.strftime('%Y-%m-%d %H:%M:%S', time.strptime(d.getVar('DATETIME', d), '%Y%m%d%H%M%S'))} UTC"
ROOTFS_GEN_TIMESTAMP[vardepsexclude] += "DATETIME"

# Substitutes '#ROOTFS_GEN_TIMESTAMP#' in some rootfs files with
# actual timestamp value
do_substitute_timestamp() {
	FILES_TO_SUBSTITUTE="\
		${sysconfdir}/issue \
		${sysconfdir}/tano-version \
		${sysconfdir}/openwrt_version \
		${sysconfdir}/openwrt_release \
		${libdir}/os-release \
	"

	for file in ${FILES_TO_SUBSTITUTE}; do
		if [ -f "${IMAGE_ROOTFS}${file}" ]; then
			sed -i \
			    -e "s,#ROOTFS_GEN_TIMESTAMP#,${ROOTFS_GEN_TIMESTAMP},g" \
			    ${IMAGE_ROOTFS}/${file}
		fi
	done
}

# Inject the version file in the rootfs directory before it
# is being packaged into an image.
IMAGE_PREPROCESS_COMMAND += "do_substitute_timestamp; "

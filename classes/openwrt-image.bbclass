#
inherit core-image
inherit openwrt
inherit openwrt-kmods

IMAGE_OVERLAY_NAME_SUFFIX = ".overlay"

#
# Available IMAGE_FEATURES:
#
# - wifi
# - cgroup
#
FEATURE_PACKAGES_cgroup = "packagegroup-openwrt-cgroup"
FEATURE_PACKAGES_wifi = "packagegroup-openwrt-wifi"

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

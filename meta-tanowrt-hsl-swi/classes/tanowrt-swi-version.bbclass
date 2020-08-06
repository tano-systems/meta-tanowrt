#
do_create_rootfs_version() {
    EXT_VERSION=`cat "${IMAGE_ROOTFS}${sysconfdir}/openwrt_version"`
    echo "${DISTRO_NAME} ${DISTRO_VERSION} ${EXT_VERSION}" > ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.version
    ln -sf ${IMAGE_NAME}.rootfs.version ${DEPLOY_DIR_IMAGE}/${IMAGE_LINK_NAME}.rootfs.version
    ln -sf ${IMAGE_NAME}.rootfs.version ${DEPLOY_DIR_IMAGE}/rootfs.version
}

addtask create_rootfs_version after do_image_complete before do_build

IMAGE_CMD_wic_append () {
	mv "$out${IMAGE_NAME_SUFFIX}.wic" "$out${IMAGE_NAME_SUFFIX}.sdcard.img"
	ln -fs "${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.sdcard.img" "${IMGDEPLOYDIR}/${IMAGE_LINK_NAME}.sdcard.img"
}

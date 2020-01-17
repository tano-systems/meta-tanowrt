#
PR_append = ".swi0"

inherit uci-config

do_uci_config_append() {
	# Prohibit unmounts of some SWI related mount points
	${UCI} set luci.mounts=internal
	${UCI} add_list luci.mounts.prohibit_umount='/firmware'
	${UCI} add_list luci.mounts.prohibit_umount='/mnt/legato'
	${UCI} commit luci
}

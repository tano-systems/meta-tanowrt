PR_append = ".13"

KERNEL_FEATURES_append = "\
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/kernel-4.14+/usb-leds/ledtrig_usbport.scc", "" ,d)} \
	features/kernel-4.14+/fs/overlayfs-extra.scc \
	features/kernel-4.1+/vdso.scc \
"

SRC_URI_append = "\
	file://600-netfilter_conntrack_flush.patch \
	file://610-netfilter_match_bypass_default_checks.patch \
	file://611-netfilter_match_bypass_default_table.patch \
	file://612-netfilter_match_reduce_memory_access.patch \
	file://613-netfilter_optional_tcp_window_check.patch \
	file://620-net_sched-codel-do-not-defer-queue-length-update.patch \
	file://630-packet_socket_type.patch \
	file://655-increase_skb_pad.patch \
	file://681-NET-add-of_get_mac_address_mtd.patch \
"

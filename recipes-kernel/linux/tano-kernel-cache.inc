PR_append = ".3"

KERNEL_FEATURES_append = " \
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/usb-net/usb-net.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/usb-serial/usb-serial.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "cgroup", " features/cgroups/cgroups.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "wifi usbhost", " features/wifi/wifi-usb.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "wifi pci", " features/wifi/wifi-pci.scc", "" ,d)} \
	features/general/ikconfig.scc \
	features/net_sched/net_sched.scc \
	features/netfilter/netfilter.scc \
	features/nf_tables/nf_tables.scc \
	features/ebtables/ebtables.scc \
	features/net/routing.scc \
	features/net/l2tp.scc \
	features/net/ipset.scc \
	features/net/atm.scc \
	features/net/ifb.scc \
	features/net/ppp.scc \
	features/net/tun.scc \
	features/net/vxlan.scc \
	features/gre/gre-enable.scc \
	features/net/phylib.scc \
	features/fs/squashfs.scc \
	features/fs/f2fs.scc \
	features/fs/overlayfs.scc \
	features/nls/utf8.scc \
	features/nls/codepage_437.scc \
	features/nls/codepage_866.scc \
	features/nls/codepage_1251.scc \
	features/nls/iso8859_1.scc \
	features/leds/ledtrig_netdev.scc \
	features/leds/ledtrig_mtd.scc \
	features/leds/ledtrig_heartbeat.scc \
	features/leds/ledtrig_timer.scc \
"

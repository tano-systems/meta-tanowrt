PR_append = ".12"

KERNEL_FEATURES_append = " \
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/general/usb-net/usb-net.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "usbhost", " features/general/usb-serial/usb-serial.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "cgroup", " features/general/cgroups/cgroups.scc", "" ,d)} \
	${@bb.utils.contains("COMBINED_FEATURES", "seccomp", " features/general/secutiry/seccomp.scc", "" ,d)} \
	features/general/general/ikconfig.scc \
	features/general/net_sched/net_sched.scc \
	features/general/netfilter/netfilter.scc \
	features/general/nf_tables/nf_tables.scc \
	features/general/ebtables/ebtables.scc \
	features/general/net/routing.scc \
	features/general/net/l2tp.scc \
	features/general/net/ipset.scc \
	features/general/net/atm.scc \
	features/general/net/ifb.scc \
	features/general/net/ppp.scc \
	features/general/net/tun.scc \
	features/general/net/vxlan.scc \
	features/general/gre/gre-enable.scc \
	features/general/net/phylib.scc \
	features/general/fs/squashfs.scc \
	features/general/fs/f2fs.scc \
	features/general/fs/overlayfs.scc \
	features/general/fs/autofs4_fs.scc \
	features/general/nls/utf8.scc \
	features/general/nls/codepage_437.scc \
	features/general/nls/codepage_866.scc \
	features/general/nls/codepage_1251.scc \
	features/general/nls/iso8859_1.scc \
	features/general/leds/ledtrig_netdev.scc \
	features/general/leds/ledtrig_mtd.scc \
	features/general/leds/ledtrig_heartbeat.scc \
	features/general/leds/ledtrig_timer.scc \
"

KERNEL_FEATURES_append_qemuall = " features/general/virtio/virtio.scc"

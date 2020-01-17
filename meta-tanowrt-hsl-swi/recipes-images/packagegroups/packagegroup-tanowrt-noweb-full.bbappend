#
PR_append = ".swi0"

# packagegroup-tanowrt-noweb-full-base
RDEPENDS_${PN}-base_remove = "\
	apcupsd \
	cpulimit \
"

# packagegroup-tanowrt-noweb-full-console
RDEPENDS_${PN}-console_remove = "\
	ncurses \
	ncurses-terminfo \
	htop \
"

# packagegroup-tanowrt-noweb-full-network
RDEPENDS_${PN}-network_remove = "\
	lldpd \
	mstpd \
	umbim \
	net-snmp-client \
	net-snmp-mibs \
	openvpn \
	openvpn-easy-rsa \
"

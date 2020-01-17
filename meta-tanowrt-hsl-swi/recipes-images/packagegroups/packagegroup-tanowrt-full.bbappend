# Copyright (C) 2019-2020 Anton Kikin <a.kikin@tano-systems.com>
# Released under the MIT license (see COPYING.MIT for the terms)

PR_append = ".swi0"

# packagegroup-tanowrt-full-luci
RDEPENDS_${PN}-luci_remove = "\
	luci-app-tn-lldpd \
	luci-app-tn-mstpd \
	luci-app-ledtrig-usbport \
	luci-app-openvpn \
	luci-app-ddns \
	luci-theme-bootstrap \
	luci-proto-qmi \
	luci-proto-ncm \
	luci-proto-ppp \
"

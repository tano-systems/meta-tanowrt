# Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>

PR_append = ".swi0"

RDEPENDS_${PN}_remove = "\
	comgt-ncm \
	uqmi \
	umbim \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-ncm', '',d)} \
	${@oe.utils.conditional('TANOWRT_LUCI_ENABLE', '1', 'luci-proto-qmi', '',d)} \
"

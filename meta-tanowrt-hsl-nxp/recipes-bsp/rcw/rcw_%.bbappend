PR_append = ".nxp2"
M_ls1028ardb = "ls1028ardb"

SRC_URI = "git://source.codeaurora.org/external/qoriq/qoriq-components/rcw;nobranch=1"
SRCREV = "LSDK-19.09"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"

SRC_URI += "\
	file://0001-ls1028ardb-Enable-IIC5_PMUX-for-GPIO-function.patch \
	file://0002-ls1028ardb-Enable-CLK_OUT_PMUX-for-GPIO-function.patch \
	file://0003-ls1028ardb-Add-commands-to-copy-SPL-to-OCRAM.patch \
"

do_deploy_append() {
	ln -sf ${RCW_BINARY} ${DEPLOYDIR}/rcw.bin
}

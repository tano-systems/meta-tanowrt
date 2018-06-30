# Original file from meta-arago-distro

# Locate the toolchain automatically from PATH
TOOLCHAIN_SYSPATH := "${@os.path.abspath(os.path.dirname(bb.utils.which(d.getVar('PATH', d, 1), d.getVar('TOOLCHAIN_PREFIX', d, 1)+'cpp'))+'/../'+d.getVar('TOOLCHAIN_SYS', d, 1))}"
TOOLCHAIN_PATH := "${@bool(d.getVar('TOOLCHAIN_SYSPATH', d, 1)) and (os.path.exists(d.getVar('TOOLCHAIN_SYSPATH', d, 1)) and os.path.dirname(d.getVar('TOOLCHAIN_SYSPATH', d, 1)) or bb.fatal('No valid toolchain in PATH')) or ''}"
EXTERNAL_TOOLCHAIN = "${TOOLCHAIN_PATH}"

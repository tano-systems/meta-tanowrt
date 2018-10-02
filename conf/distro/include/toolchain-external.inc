# Original file from meta-arago-distro

# Locate the toolchain automatically from PATH
TOOLCHAIN_PATH ??= "/usr/local/sdk"
TOOLCHAIN_SYSPATH = "${TOOLCHAIN_PATH}/${TOOLCHAIN_SYS}"
EXTERNAL_TOOLCHAIN = "${TOOLCHAIN_PATH}"

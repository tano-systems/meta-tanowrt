#===============================================================================
# BOM Configuration file
#
# Syntax:
#   Select one of available providers (recipes) for a package.
#   Usually used for virtual/xyz style packages. i.e virtual/kernel
#       PREFERRED_PROVIDER_<pkg> = "recipe"
#       PREFERRED_PROVIDER_<pkg>_<mach> = "recipe"
#   i.e.
#       PREFERRED_PROVIDER_virtual/kernel = "linux-davinci"
#       PREFERRED_PROVIDER_virtual/kenrel_omap3evm = "linux-omap3"
#
#   Select one of the available versions for a package
#       PREFERRED_VERSION_<pkg> = "X.Y.Z"
#       PREFERRED_VERSION_<pkg>_<mach> = "X.Y.Z"
#   i.e.
#       PREFERRED_VERSION_udev = "141"
#       PREFERRED_VERSION_udev_omap3evm = "124"
#
#   Enable source ipk creation for a package
#       CREATE_SRCIPK_pn-<pkg> = "1"
#   i.e.
#       CREATE_SRCIPK_pn-u-boot = "1"
#
#   Change install diretory of source ipk for a package
#       SRCIPK_INSTALL_DIR_pn-<pkg> = "<install dir>"
#   i.e.
#       SRCIPK_INSTALL_DIR_pn-u-boot = "board-support/u-boot-source"
#
#   Change source ipk architecture for a package
#       SRCIPK_PACKAGE_ARCH_pn-<pkg> = "<arch>"
#   i.e.
#       SRCIPK_PACKAGE_ARCH_pn-u-boot = "${MACHINE_ARCH}"
#===============================================================================

# Enable the creation of source ipks
INHERIT += "sourceipk"

# Disable including README and recipe files in sourceipks for SDK builds.
SRCIPK_INCLUDE_EXTRAFILES = "0"

# Set source ipk packages and install directories
CREATE_SRCIPK_pn-ti-tisdk-licenses = "1"
SRCIPK_INSTALL_DIR_pn-ti-tisdk-licenses = "docs/licenses"

CREATE_SRCIPK_pn-matrix-gui = "1"
SRCIPK_INSTALL_DIR_pn-matrix-gui = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-ti-crypto-examples = "1"
SRCIPK_INSTALL_DIR_pn-ti-crypto-examples = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-qml-carinfo = "1"
SRCIPK_INSTALL_DIR_pn-qml-carinfo = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-quick-playground = "1"
SRCIPK_INSTALL_DIR_pn-quick-playground = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-qt-catalog = "1"
SRCIPK_INSTALL_DIR_pn-qt-catalog = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-qt-patientcare = "1"
SRCIPK_INSTALL_DIR_pn-qt-patientcare = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-qt-tstat = "1"
SRCIPK_INSTALL_DIR_pn-qt-tstat = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-dual-camera-demo = "1"
SRCIPK_INSTALL_DIR_pn-dual-camera-demo = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-evse-hmi = "1"
SRCIPK_INSTALL_DIR_pn-evse-hmi = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-protection-relays-hmi = "1"
SRCIPK_INSTALL_DIR_pn-protection-relays-hmi = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-image-gallery = "1"
SRCIPK_INSTALL_DIR_pn-image-gallery = "example-applications/${PN}"

CREATE_SRCIPK_pn-matrix-gui-browser = "1"
SRCIPK_INSTALL_DIR_pn-matrix-gui-browser = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-refresh-screen = "1"
SRCIPK_INSTALL_DIR_pn-refresh-screen = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-am-sysinfo = "1"
SRCIPK_INSTALL_DIR_pn-am-sysinfo = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-oprofile-example = "1"
SRCIPK_INSTALL_DIR_pn-oprofile-example = "example-applications/${PN}"

CREATE_SRCIPK_pn-arm-benchmarks = "1"
SRCIPK_INSTALL_DIR_pn-arm-benchmarks = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-omapconf = "1"
SRCIPK_INSTALL_DIR_pn-omapconf = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-ti-pru-sw-examples = "1"
SRCIPK_INSTALL_DIR_pn-ti-pru-sw-examples = "example-applications/pru-${PV}"

CREATE_SRCIPK_pn-ti-ocf-crypto-module = "1"
SRCIPK_INSTALL_DIR_pn-ti-ocf-crypto-module = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-omap3-sgx-modules = "1"
SRCIPK_INSTALL_DIR_pn-omap3-sgx-modules = "board-support/extra-drivers/sgx-modules-${PV}"

CREATE_SRCIPK_pn-ti-compat-wireless-wl18xx = "1"
SRCIPK_INSTALL_DIR_pn-ti-compat-wireless-wl18xx = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-wl18xx-fw = "1"
SRCIPK_INSTALL_DIR_pn-wl18xx-fw = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-wl1271-wlan-cli = "1"
SRCIPK_INSTALL_DIR_pn-wl1271-wlan-cli = "example-applications/wlan"

CREATE_SRCIPK_pn-av-examples = "1"
SRCIPK_INSTALL_DIR_pn-av-examples = "example-applications/av-examples-${PV}"

CREATE_SRCIPK_pn-u-boot = "1"
SRCIPK_INSTALL_DIR_pn-u-boot = "board-support/u-boot-${PV}"
# For beagleboard we use the git version which doesn't define UVER
SRCIPK_INSTALL_DIR_pn-u-boot_beagleboard = "board-support/${P}"

CREATE_SRCIPK_pn-x-load = "1"
SRCIPK_INSTALL_DIR_pn-x-load = "board-support/x-load-${XVER}-psp${PSPREL}"
# For beagleboard we use the git version which doesn't define XVER
SRCIPK_INSTALL_DIR_pn-x-load_beagleboard = "board-support/${P}"

CREATE_SRCIPK_pn-u-boot-ti-staging = "1"
SRCIPK_INSTALL_DIR_pn-u-boot-ti-staging = "board-support/u-boot-${PV}${UBOOT_LOCALVERSION}"
SRCIPK_PRESERVE_GIT_pn-u-boot-ti-staging = "true"

CREATE_SRCIPK_pn-u-boot-keystone = "1"
SRCIPK_INSTALL_DIR_pn-u-boot-keystone = "board-support/u-boot-${PV}"
SRCIPK_PRESERVE_GIT_pn-u-boot-keystone = "true"

CREATE_SRCIPK_pn-u-boot-dra7xx = "1"
SRCIPK_INSTALL_DIR_pn-u-boot-dra7xx = "board-support/u-boot-${PV}-${PR}"

CREATE_SRCIPK_pn-linux-ti-staging = "1"
SRCIPK_INSTALL_DIR_pn-linux-ti-staging = "board-support/linux-${PV}${KERNEL_LOCALVERSION}"
SRCIPK_PRESERVE_GIT_pn-linux-ti-staging = "true"
SRCIPK_SHALLOW_CLONE_pn-linux-ti-staging = "true"

CREATE_SRCIPK_pn-linux-ti-staging-rt = "1"
SRCIPK_INSTALL_DIR_pn-linux-ti-staging-rt = "board-support/linux-rt-${PV}${KERNEL_LOCALVERSION}"
SRCIPK_PRESERVE_GIT_pn-linux-ti-staging-rt = "true"
SRCIPK_SHALLOW_CLONE_pn-linux-ti-staging-rt = "true"

CREATE_SRCIPK_pn-linux-ti-staging-systest = "1"
SRCIPK_INSTALL_DIR_pn-linux-ti-staging-systest = "board-support/linux-systest-${PV}${KERNEL_LOCALVERSION}"
SRCIPK_PRESERVE_GIT_pn-linux-ti-staging-systest = "true"
SRCIPK_SHALLOW_CLONE_pn-linux-ti-staging-systest = "true"

CREATE_SRCIPK_pn-sitara-linux-ti-staging = "1"
SRCIPK_INSTALL_DIR_pn-sitara-linux-ti-staging = "board-support/linux-${PV}${KERNEL_LOCALVERSION}"
SRCIPK_PRESERVE_GIT_pn-sitara-linux-ti-staging = "true"
SRCIPK_SHALLOW_CLONE_pn-sitara-linux-ti-staging = "true"
SRCIPK_SHALLOW_DEPTH_pn-sitara-linux-ti-staging = "400"

CREATE_SRCIPK_pn-linux-keystone = "1"
SRCIPK_INSTALL_DIR_pn-linux-keystone = "board-support/linux-${PV}"
SRCIPK_PRESERVE_GIT_pn-linux-keystone = "true"

CREATE_SRCIPK_pn-linux-am335x-psp = "1"
SRCIPK_INSTALL_DIR_pn-linux-am335x-psp = "board-support/linux-${KVER}"

CREATE_SRCIPK_pn-linux-omap = "1"
SRCIPK_INSTALL_DIR_pn-linux-omap = "board-support/linux-${PV}-${PR}"

CREATE_SRCIPK_pn-linux = "1"
SRCIPK_INSTALL_DIR_pn-linux = "board-support/linux-${PV}"

CREATE_SRCIPK_pn-linux-omap4 = "1"
SRCIPK_INSTALL_DIR_pn-linux-omap4 = "board-support/linux-${PV}"

CREATE_SRCIPK_pn-cryptodev-module = "1"
SRCIPK_INSTALL_DIR_pn-cryptodev-module = "board-support/extra-drivers/cryptodev-module-${PV}"

CREATE_SRCIPK_pn-cmem-mod = "1"
SRCIPK_INSTALL_DIR_pn-cmem-mod = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-debugss-module-drv = "1"
SRCIPK_INSTALL_DIR_pn-debugss-module-drv = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-gdbserverproxy-module-drv = "1"
SRCIPK_INSTALL_DIR_pn-gdbserverproxy-module-drv = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-ti-sgx-ddk-km = "1"
SRCIPK_INSTALL_DIR_pn-ti-sgx-ddk-km = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-ti-gc320-driver = "1"
SRCIPK_INSTALL_DIR_pn-ti-gc320-driver = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-ti-linux-driver-examples = "1"
SRCIPK_INSTALL_DIR_pn-ti-linux-driver-examples = "example-applications/linux-driver-examples-${PV}"

CREATE_SRCIPK_pn-ti-linux-driver-examples-omapl = "1"
SRCIPK_INSTALL_DIR_pn-ti-linux-driver-examples-omapl = "example-applications/linux-driver-examples-${PV}"

CREATE_SRCIPK_pn-ti-linux-driver-examples-omap3 = "1"
SRCIPK_INSTALL_DIR_pn-ti-linux-driver-examples-omap3 = "example-applications/linux-driver-examples-${PV}"

CREATE_SRCIPK_pn-ti-framework-components = "1"
SRCIPK_INSTALL_DIR_pn-ti-framework-components = "component-sources/framework_components_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-codec-engine = "1"
SRCIPK_INSTALL_DIR_pn-ti-codec-engine = "component-sources/codec_engine_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-codecs-omap3530 = "1"
SRCIPK_INSTALL_DIR_pn-ti-codecs-omap3530 = "codecs-omap3530_${PV}"

CREATE_SRCIPK_pn-ti-linuxutils = "1"
SRCIPK_INSTALL_DIR_pn-ti-linuxutils = "component-sources/linuxutils_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-xdctools = "1"
SRCIPK_INSTALL_DIR_pn-ti-xdctools = "component-sources/xdctools_${PV}"

CREATE_SRCIPK_pn-ti-dmai = "1"
SRCIPK_INSTALL_DIR_pn-ti-dmai = "dmai_${PV}"

CREATE_SRCIPK_pn-ti-xdais = "1"
SRCIPK_INSTALL_DIR_pn-ti-xdais = "component-sources/xdais_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-local-power-manager = "1"
SRCIPK_INSTALL_DIR_pn-ti-local-power-manager = "local-power-manager_${PV}"

CREATE_SRCIPK_pn-ti-codecs-dm355 = "1"
SRCIPK_INSTALL_DIR_pn-ti-codecs-dm355 = "codecs-dm355_${PV}"

CREATE_SRCIPK_pn-ti-dvsdk-demos = "1"
SRCIPK_INSTALL_DIR_pn-ti-dvsdk-demos = "dvsdk-demos_${PV}"

CREATE_SRCIPK_pn-ti-dvtb = "1"
SRCIPK_INSTALL_DIR_pn-ti-dvtb = "dvtb_${PV}"

CREATE_SRCIPK_pn-ti-codecs-dm365 = "1"
SRCIPK_INSTALL_DIR_pn-ti-codecs-dm365 = "codecs-dm365_${PV}"

CREATE_SRCIPK_pn-ti-dm365mm-module = "1"
SRCIPK_INSTALL_DIR_pn-ti-dm365mm-module = "dm365mm-module_${PV}"

CREATE_SRCIPK_pn-ti-codecs-dm6446 = "1"
SRCIPK_INSTALL_DIR_pn-ti-codecs-dm6446 = "codecs-dm6446_${PV}"

CREATE_SRCIPK_pn-ti-codecs-dm6467 = "1"
SRCIPK_INSTALL_DIR_pn-ti-codecs-dm6467 = "codecs-dm6467_${PV}"

CREATE_SRCIPK_pn-ti-codecs-omapl137 = "1"
SRCIPK_INSTALL_DIR_pn-ti-codecs-omapl137 = "codecs-omapl137_${PV}"

CREATE_SRCIPK_pn-ti-codecs-omapl138 = "1"
SRCIPK_INSTALL_DIR_pn-ti-codecs-omapl138 = "codecs-omapl138_${PV}"

CREATE_SRCIPK_pn-ti-cgt6x = "1"
SRCIPK_INSTALL_DIR_pn-ti-cgt6x = "dsp-devkit/cgt6x_${PV}"

CREATE_SRCIPK_pn-ti-cgt470 = "1"
SRCIPK_INSTALL_DIR_pn-ti-cgt470 = "media-controller-devkit/cgt470_${PV}"

CREATE_SRCIPK_pn-ti-dspbios = "1"
SRCIPK_INSTALL_DIR_pn-ti-dspbios = "dspbios_${PV}"

CREATE_SRCIPK_pn-ti-sysbios = "1"
SRCIPK_INSTALL_DIR_pn-ti-sysbios = "component-sources/bios_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-biosutils = "1"
SRCIPK_INSTALL_DIR_pn-ti-biosutils = "biosutils_${PV}"

CREATE_SRCIPK_pn-ti-dsplink = "1"
SRCIPK_INSTALL_DIR_pn-ti-dsplink = "dsplink_${PV}"

CREATE_SRCIPK_pn-ti-syslink = "1"
SRCIPK_INSTALL_DIR_pn-ti-syslink = "component-sources/syslink_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-edma3lld = "1"
SRCIPK_INSTALL_DIR_pn-ti-edma3lld = "component-sources/edma3lld_${PV}"

CREATE_SRCIPK_pn-ti-c6accel = "1"
SRCIPK_INSTALL_DIR_pn-ti-c6accel = "component-sources/c6accel_${PV}"

CREATE_SRCIPK_pn-ti-biospsp = "1"
SRCIPK_INSTALL_DIR_pn-ti-biospsp = "biospsp_${PV}"

CREATE_SRCIPK_pn-ti-audio-soc-example = "1"
SRCIPK_INSTALL_DIR_pn-ti-audio-soc-example = "audio-soc-example_${PV}"

CREATE_SRCIPK_pn-ti-c6run = "1"
SRCIPK_INSTALL_DIR_pn-ti-c6run = "component-sources/c6run_${PV}"

CREATE_SRCIPK_pn-ti-osal = "1"
SRCIPK_INSTALL_DIR_pn-ti-osal = "component-sources/osal_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-ipc = "1"
SRCIPK_INSTALL_DIR_pn-ti-ipc = "component-sources/ipc_${PV}"

CREATE_SRCIPK_pn-ti-dsp-benchmark = "1"
SRCIPK_INSTALL_DIR_pn-ti-dsp-benchmark = "example-applications/dsp-benchmark_${PV}"

CREATE_SRCIPK_pn-gstreamer-ti = "1"
SRCIPK_INSTALL_DIR_pn-gstreamer-ti = "gstreamer-ti_${PV}"

CREATE_SRCIPK_pn-ti-omx = "1"
SRCIPK_INSTALL_DIR_pn-ti-omx = "component-sources/omx_${PV}"

CREATE_SRCIPK_pn-ti-uia = "1"
SRCIPK_INSTALL_DIR_pn-ti-uia = "component-sources/uia_${PV}${PVExtra}"

CREATE_SRCIPK_pn-ti-media-controller-utils = "1"
SRCIPK_INSTALL_DIR_pn-ti-media-controller-utils = "board-support/media-controller-utils_${PV}"

CREATE_SRCIPK_pn-ti-firmware-dm816x = "1"
SRCIPK_INSTALL_DIR_pn-ti-firmware-dm816x = "board-support/prebuilt-images/dm816x-firmware_${PV}"

CREATE_SRCIPK_pn-ti-firmware-dm814x = "1"
SRCIPK_INSTALL_DIR_pn-ti-firmware-dm814x = "board-support/prebuilt-images/dm814x-firmware_${PV}"

CREATE_SRCIPK_pn-ti-slog = "1"
SRCIPK_INSTALL_DIR_pn-ti-slog = "component-sources/slog_${PV}"

CREATE_SRCIPK_pn-ti-c674x-aaclcdec = "1"
SRCIPK_INSTALL_DIR_pn-ti-c674x-aaclcdec = "component-sources/c674x-aaclcdec_${PV}${PVExtra}"

CREATE_SRCIPK_pn-oe-layersetup = "1"
SRCIPK_INSTALL_DIR_pn-oe-layersetup = "bin/oe-layersetup"

CREATE_SRCIPK_pn-opencl-examples = "1"
SRCIPK_INSTALL_DIR_pn-opencl-examples = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-openmpacc-examples = "1"
SRCIPK_INSTALL_DIR_pn-openmpacc-examples = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-qt-opencv-opencl-opengl-multithreaded = "1"
SRCIPK_INSTALL_DIR_pn-qt-opencv-opencl-opengl-multithreaded = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-libarch-examples = "1"
SRCIPK_INSTALL_DIR_pn-libarch-examples = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-linalg-examples = "1"
SRCIPK_INSTALL_DIR_pn-linalg-examples = "example-applications/${PN}-${PV}"
SRCIPK_SRC_DIR_pn-linalg-examples = "${EXAMPLES_ROOT}"

CREATE_SRCIPK_pn-barcode-roi = "1"
SRCIPK_INSTALL_DIR_pn-barcode-roi = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-boot-monitor = "1"
SRCIPK_INSTALL_DIR_pn-boot-monitor = "board-support/${PN}-${PV}"

CREATE_SRCIPK_pn-hplib-mod = "1"
SRCIPK_INSTALL_DIR_pn-hplib-mod = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-uio-module-drv = "1"
SRCIPK_INSTALL_DIR_pn-uio-module-drv = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-ipsecmgr-mod = "1"
SRCIPK_INSTALL_DIR_pn-ipsecmgr-mod = "board-support/extra-drivers/${PN}-${PV}"

CREATE_SRCIPK_pn-pru-icss = "1"
SRCIPK_INSTALL_DIR_pn-pru-icss = "example-applications/${PN}-${PV}"

CREATE_SRCIPK_pn-dsplib-c66x = "1"
SRCIPK_INSTALL_DIR_pn-dsplib-c66x = "component-sources/dsplib_c66x_${PV}"

CREATE_SRCIPK_pn-mathlib-c66x = "1"
SRCIPK_INSTALL_DIR_pn-mathlib-c66x = "component-sources/mathlib_c66x_${PV}"

CREATE_SRCIPK_pn-imglib-c66x = "1"
SRCIPK_INSTALL_DIR_pn-imglib-c66x = "component-sources/imglib_c66x_${PV}"

CREATE_SRCIPK_pn-vlib-c66x = "1"
SRCIPK_INSTALL_DIR_pn-vlib-c66x = "component-sources/vlib_c66x_${PV}"

CREATE_SRCIPK_pn-big-data-ipc-demo-linux = "1"
SRCIPK_INSTALL_DIR_pn-big-data-ipc-demo-linux = "example-applications/${PN}-${PV}"

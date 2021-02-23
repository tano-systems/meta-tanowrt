#
# SPDX-License-Identifier: MIT
# This file Copyright (c) 2019-2021 Anton Kikin <a.kikin@tano-systems.com>
#

HOMEPAGE = "http://w1.fi/hostapd/"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://${B}/README;md5=e7d3dbb01f75f0b9799e192731d1e1ff"

INC_PR = "tano17"

inherit pkgconfig

FILESEXTRAPATHS_prepend := "${THISDIR}/hostapd:${THISDIR}/hostapd/patches:${THISDIR}/hostapd/files:"

SRC_URI = "git://w1.fi/hostap.git"

# 2020-06-08
SRCREV = "5a8b366233f5585e68a4ffbb604fbb4a848eb325"

PV = "2.9+git${SRCPV}"

# Files
SRC_URI += "\
	file://hostapd-basic.config \
	file://hostapd-full.config \
	file://hostapd-mini.config \
	file://wpa_supplicant-basic.config \
	file://wpa_supplicant-full.config \
	file://wpa_supplicant-mini.config \
	file://wpa_supplicant-p2p.config \
	file://hostapd.sh \
	file://wps-hotplug.sh \
	file://multicall.c \
	file://wpad.init \
	file://wpad.json \
	file://wpad_acl.json \
"

# Sources
SRC_URI += "\
	file://src/utils/build_features.h \
	file://src/ap/ubus.c \
	file://src/ap/ubus.h \
	file://wpa_supplicant/ubus.c \
	file://wpa_supplicant/ubus.h \
"

# Patches
SRC_URI += "\
	file://001-HE-VHT-fix-frequency-setup-with-HE-enabled.patch \
	file://002-mesh-fix-channel-init-order-disable-pri-sec-channel-.patch \
	file://003-wpa_supplicant-handle-HT40-and-mode-downgrade-in-AP-.patch \
	file://004-wpa_supplicant-fix-frequency-config-for-non-p2p-vht-.patch \
	file://005-wpa_supplicant-enable-vht-and-he-in-default-config-p.patch \
	file://006-hw_features-better-debug-messages-for-some-error-cas.patch \
	file://007-dfs-use-helper-functions-for-vht-he-parameters.patch \
	file://008-mesh-use-setup-completion-callback-to-complete-mesh-.patch \
	file://009-mesh-update-ssid-frequency-as-pri-sec-channel-switch.patch \
	file://010-mesh-Allow-DFS-channels-to-be-selected-if-dfs-is-ena.patch \
	file://011-mesh-do-not-set-offchanok-on-DFS-channels-in-non-ETS.patch \
	file://012-mesh-fix-channel-switch-error-during-CAC.patch \
	file://013-mesh-inform-kernel-driver-DFS-handler-in-userspace.patch \
	file://014-mesh-fixes-for-mesh-init-deinit.patch \
	file://015-mesh-fix-DFS-deinit-init.patch \
	file://016-tests-DFS-test-for-wpa_supplicant-mesh.patch \
	file://017-mesh-fix-mesh_oom-test.patch \
	file://018-mesh-move-mesh-freq-setting-to-own-function.patch \
	file://019-mesh-use-deterministic-channel-on-channel-switch.patch \
	file://020-ignore-4addr-mode-enabling-error.patch \
	file://050-mesh-make-forwarding-configurable.patch \
	file://100-daemonize_fix.patch \
	file://110-notify-mgmt-frames.patch \
	file://110-wolfssl-compile-fix.patch \
	file://120-reconfigure-wps-credentials.patch \
	file://200-multicall.patch \
	file://300-noscan.patch \
	file://301-mesh-noscan.patch \
	file://310-rescan_immediately.patch \
	file://320-optional_rfkill.patch \
	file://330-nl80211_fix_set_freq.patch \
	file://340-reload_freq_change.patch \
	file://341-mesh-ctrl-iface-channel-switch.patch \
	file://350-nl80211_del_beacon_bss.patch \
	file://360-ctrl_iface_reload.patch \
	file://370-ap_sta_support.patch \
	file://380-disable_ctrl_iface_mib.patch \
	file://381-hostapd_cli_UNKNOWN-COMMAND.patch \
	file://390-wpa_ie_cap_workaround.patch \
	file://400-wps_single_auth_enc_type.patch \
	file://410-limit_debug_messages.patch \
	file://420-indicate-features.patch \
	file://430-hostapd_cli_ifdef.patch \
	file://431-wpa_cli_ifdef.patch \
	file://432-missing-typedef.patch \
	file://450-scan_wait.patch \
	file://460-wpa_supplicant-add-new-config-params-to-be-used-with.patch \
	file://461-driver_nl80211-use-new-parameters-during-ibss-join.patch \
	file://463-add-mcast_rate-to-11s.patch \
	file://464-fix-mesh-obss-check.patch \
	file://470-survey_data_fallback.patch \
	file://500-lto-jobserver-support.patch \
	file://599-wpa_supplicant-fix-warnings.patch \
	file://600-ubus_support.patch \
	file://700-wifi-reload.patch \
	file://800-dfs-enter-DFS-state-if-no-available-channel-is-found.patch \
	file://801-P2P-Fix-copying-of-secondary-device-types-for-P2P-gr.patch \
"

S = "${WORKDIR}/git"
B = "${S}"

TARGET_CFLAGS += "\
	-MMD -O2 -g -Wall \
	-I${STAGING_INCDIR}/libnl3 \
	-I${S}/src/crypto \
	-DCONFIG_LIBNL20 \
	-D_GNU_SOURCE \
	-ffunction-sections \
	-fdata-sections \
	-Wno-pointer-sign \
	-Wno-unused-function \
	-Wno-unused-variable \
	-Wno-unused-const-variable \
	-Wno-incompatible-pointer-types \
"

TARGET_LDFLAGS += "\
	-lm \
	-lnl-3 \
	-lnl-genl-3 \
	-lnl-route-3 \
	-lubox \
	-lubus \
	-lssl \
	-lcrypto \
"

EXTRA_OEMAKE = "\
	CONFIG_ACS=y \
	CONFIG_AP=y \
	CONFIG_SAE=y \
	CONFIG_MESH=y \
	CONFIG_DRIVER_NL80211=y \
	CONFIG_DRIVER_WEXT=y \
	CONFIG_IEEE80211N=y \
	CONFIG_IEEE80211AC=y \
	CONFIG_IEEE80211AX=y \
	CONFIG_LIBNL32=y \
	CONFIG_TLS=openssl \
	CONFIG_OWE=y \
	CONFIG_SUITEB192=y \
	NEED_RFKILL=y \
"

PARALLEL_MAKE = ""

do_configure() {
	oe_runmake clean -C ${B}/hostapd
	oe_runmake clean -C ${B}/wpa_supplicant

	install -d ${S}/src/utils
	install -d ${S}/src/ap
	install -m 0644 ${WORKDIR}/src/utils/build_features.h ${S}/src/utils
	install -m 0644 ${WORKDIR}/src/ap/ubus.c ${S}/src/ap
	install -m 0644 ${WORKDIR}/src/ap/ubus.h ${S}/src/ap
	install -m 0644 ${WORKDIR}/wpa_supplicant/ubus.c ${S}/wpa_supplicant
	install -m 0644 ${WORKDIR}/wpa_supplicant/ubus.h ${S}/wpa_supplicant

	install -m 0644 ${WORKDIR}/hostapd-full.config ${B}/hostapd/.config
	install -m 0644 ${WORKDIR}/wpa_supplicant-full.config ${B}/wpa_supplicant/.config
}

do_configure[depends] += "virtual/kernel:do_shared_workdir"

inherit tanowrt-services
TANOWRT_SERVICE_PACKAGES = "hostapd"
TANOWRT_SERVICE_SCRIPTS_hostapd += "wpad"
TANOWRT_SERVICE_STATE_hostapd-wpad ?= "enabled"

inherit useradd

USERADD_PACKAGES = "${PN}"

USERADD_PARAM_${PN} = "\
	--system \
	--home-dir /var/run/network \
	--no-create-home \
	--shell /bin/false \
	--gid network \
	  network \
"

GROUPADD_PARAM_${PN} = "--system network"

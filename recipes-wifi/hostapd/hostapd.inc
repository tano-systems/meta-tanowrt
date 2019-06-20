# This file Copyright (c) 2019 Anton Kikin <a.kikin@tano-systems.com>

HOMEPAGE = "http://w1.fi/hostapd/"
LICENSE = "GPLv2 | BSD"
LIC_FILES_CHKSUM = "file://${B}/README;md5=495cbce6008253de4b4d8f4cdfae9f4f"

INC_PR = "tano3"

inherit pkgconfig

FILESEXTRAPATHS_prepend := "${THISDIR}/hostapd:${THISDIR}/hostapd/patches:${THISDIR}/hostapd/files:"

SRC_URI = "git://w1.fi/hostap.git"

# 2018-12-02
SRCREV = "c2c6c01bb8b6fafc2074b46a53c4eab2c145ac6f"

PV = "2.7+git${SRCPV}"

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
	file://001-mesh-factor-out-mesh-join-function.patch \
	file://002-mesh-factor-out-rsn-initialization.patch \
	file://003-mesh-relocate-RSN-init-function.patch \
	file://004-mesh-use-setup-completion-callback-to-complete-mesh-.patch \
	file://005-mesh-reflect-country-setting-to-mesh-configuration.patch \
	file://006-mesh-inform-kernel-driver-DFS-handler-in-userspace.patch \
	file://007-mesh-apply-channel-attributes-before-running-Mesh.patch \
	file://008-mesh-set-interface-type-to-mesh-before-setting-inter.patch \
	file://009-mesh-set-mesh-center-frequency.patch \
	file://010-mesh-consider-mesh-interface-on-dfs-event-handler.patch \
	file://011-mesh-Allow-DFS-channels-to-be-selected-if-dfs-is-ena.patch \
	file://013-mesh-do-not-allow-pri-sec-channel-switch.patch \
	file://014-mesh-do-not-allow-scan-result-to-swap-pri-sec.patch \
	file://015-mesh-do-not-use-offchan-mgmt-tx-on-DFS.patch \
	file://016-mesh-fix-channel-switch-error-during-CAC.patch \
	file://017-mesh-use-right-interface-context-to-send-DFS-event-m.patch \
	file://018-mesh-make-forwarding-configurable.patch \
	file://031-mesh-add-VHT_CHANWIDTH_USE_HT-to-max_oper_chwidth.patch \
	file://032-mesh-implement-use-of-VHT20-config-in-mesh-mode.patch \
	file://040-FT-Fix-CONFIG_IEEE80211X-y-build-without-CONFIG_FILS.patch \
	file://051-wpa_supplicant-fix-race-condition-in-mesh-mpm-new-pe.patch \
	file://061-0001-OpenSSL-Use-constant-time-operations-for-private-big.patch \
	file://061-0002-Add-helper-functions-for-constant-time-operations.patch \
	file://061-0003-OpenSSL-Use-constant-time-selection-for-crypto_bignu.patch \
	file://061-0005-SAE-Minimize-timing-differences-in-PWE-derivation.patch \
	file://061-0006-SAE-Avoid-branches-in-is_quadratic_residue_blind.patch \
	file://061-0007-SAE-Mask-timing-of-MODP-groups-22-23-24.patch \
	file://061-0008-SAE-Use-const_time-selection-for-PWE-in-FFC.patch \
	file://061-0009-SAE-Use-constant-time-operations-in-sae_test_pwd_see.patch \
	file://062-0004-EAP-pwd-Use-constant-time-and-memory-access-for-find.patch \
	file://063-0010-SAE-Fix-confirm-message-validation-in-error-cases.patch \
	file://064-0011-EAP-pwd-server-Verify-received-scalar-and-element.patch \
	file://064-0012-EAP-pwd-server-Detect-reflection-attacks.patch \
	file://064-0013-EAP-pwd-client-Verify-received-scalar-and-element.patch \
	file://064-0014-EAP-pwd-Check-element-x-y-coordinates-explicitly.patch \
	file://100-daemonize_fix.patch \
	file://110-no_eapol_fix.patch \
	file://120-disable_bridge_packet_workaround.patch \
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
	file://550-hostapd-Add-Multi-AP-protocol-support.patch \
	file://551-wpa_supplicant-Add-Multi-AP-protocol-support-to-supp.patch \
	file://552-tests-Multi-AP-association.patch \
	file://553-tests-refactor-test_multi_ap.patch \
	file://554-multi_ap-don-t-reject-backhaul-STA-on-fronhaul-BSS.patch \
	file://555-WPS-wps_build_wfa_ext-add-multi_ap_subelem-parameter.patch \
	file://556-wpa_supplicant-support-Multi-AP-backhaul-STA-onboard.patch \
	file://557-hostapd-support-Multi-AP-backhaul-STA-onboarding.patch \
	file://558-hostapd-add-README-MULTI-AP.patch \
	file://559-tests-add-WPS-tests-to-multi_ap-hwsim-tests.patch \
	file://600-ubus_support.patch \
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
	CONFIG_DRIVER_NL80211=y \
	CONFIG_DRIVER_WEXT=y \
	CONFIG_IEEE80211N=y \
	CONFIG_IEEE80211W=y \
	CONFIG_IEEE80211AC=y \
	CONFIG_LIBNL32=y \
	CONFIG_TLS=openssl \
	CONFIG_OWE=y \
	CONFIG_SUITEB192=y \
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

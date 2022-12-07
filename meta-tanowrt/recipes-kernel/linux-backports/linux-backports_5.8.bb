#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
require linux-backports.inc

PV = "5.8-1"
PR = "tano3.${INC_PR}"

FILESEXTRAPATHS:prepend = "${THISDIR}/${PN}_5.8/files:"
FILESEXTRAPATHS:prepend = "${THISDIR}/${PN}_5.8/configs:"
FILESEXTRAPATHS:prepend = "${THISDIR}/${PN}_5.8/patches:"

SRC_URI += "http://www.kernel.org/pub/linux/kernel/projects/backports/stable/v5.8/backports-${PV}.tar.gz"
SRC_URI[md5sum] = "00e9f98162c48e362236995406d4fd20"
SRC_URI[sha256sum] = "44ae3025d76f6747a0dfe559948dc5f0f0d84a5238fd3bf64e934cb43fcd63ff"

S = "${WORKDIR}/backports-${PV}"

#
# Disabled OpenWrt patches:
#   file://patches-openwrt/build/001-fix_build.patch \
#   file://patches-openwrt/build/012-kernel_build_check.patch \
#

# Patches from OpenWrt (build)
SRC_URI += "\
	file://patches-openwrt/build/000-fix_kconfig.patch \
	file://patches-openwrt/build/002-change_allconfig.patch \
	file://patches-openwrt/build/003-remove_bogus_modparams.patch \
	file://patches-openwrt/build/004-kconfig_backport_fix.patch \
	file://patches-openwrt/build/010-disable_rfkill.patch \
	file://patches-openwrt/build/015-ipw200-mtu.patch \
	file://patches-openwrt/build/050-lib80211_option.patch \
	file://patches-openwrt/build/060-no_local_ssb_bcma.patch \
"

# Patches from OpenWrt (subsystem)
SRC_URI += "\
	file://patches-openwrt/subsys/070-backports-add-netif_receive_skb_list.patch \
	file://patches-openwrt/subsys/071-backports-add-skb_list_del_init.patch \
	file://patches-openwrt/subsys/072-backports-add-sched_set_fifo_low.patch \
	file://patches-openwrt/subsys/100-remove-cryptoapi-dependencies.patch \
	file://patches-openwrt/subsys/110-mac80211_keep_keys_on_stop_ap.patch \
	file://patches-openwrt/subsys/120-cfg80211_allow_perm_addr_change.patch \
	file://patches-openwrt/subsys/130-disable-fils.patch \
	file://patches-openwrt/subsys/131-Revert-mac80211-aes-cmac-switch-to-shash-CMAC-driver.patch \
	file://patches-openwrt/subsys/132-mac80211-remove-cmac-dependency.patch \
	file://patches-openwrt/subsys/150-disable_addr_notifier.patch \
	file://patches-openwrt/subsys/210-ap_scan.patch \
	file://patches-openwrt/subsys/300-mac80211-optimize-skb-resizing.patch \
	file://patches-openwrt/subsys/304-mac80211-sta-randomize-BA-session-dialog-token-alloc.patch \
	file://patches-openwrt/subsys/305-mac80211-improve-AQL-tx-airtime-estimation.patch \
	file://patches-openwrt/subsys/307-mac80211-add-a-function-for-running-rx-without-passi.patch \
	file://patches-openwrt/subsys/308-net-fq_impl-use-skb_get_hash-instead-of-skb_get_hash.patch \
	file://patches-openwrt/subsys/309-mac80211-calculcate-skb-hash-early-when-using-itxq.patch \
	file://patches-openwrt/subsys/310-mac80211-reduce-packet-loss-event-false-positives.patch \
	file://patches-openwrt/subsys/311-mac80211-use-rate-provided-via-status-rate-on-ieee80.patch \
	file://patches-openwrt/subsys/312-mac80211-factor-out-code-to-look-up-the-average-pack.patch \
	file://patches-openwrt/subsys/313-mac80211-improve-AQL-aggregation-estimation-for-low-.patch \
	file://patches-openwrt/subsys/314-mac80211-add-missing-queue-hash-initialization-to-80.patch \
	file://patches-openwrt/subsys/315-mac80211-check-and-refresh-aggregation-session-in-en.patch \
	file://patches-openwrt/subsys/316-mac80211-skip-encap-offload-for-tx-multicast-control.patch \
	file://patches-openwrt/subsys/317-mac80211-set-info-control.hw_key-for-encap-offload-p.patch \
	file://patches-openwrt/subsys/318-mac80211-rework-tx-encapsulation-offload-API.patch \
	file://patches-openwrt/subsys/319-mac80211-reduce-duplication-in-tx-status-functions.patch \
	file://patches-openwrt/subsys/320-mac80211-remove-tx-status-call-to-ieee80211_sta_regi.patch \
	file://patches-openwrt/subsys/321-mac80211-optimize-station-connection-monitor.patch \
	file://patches-openwrt/subsys/322-mac80211-swap-NEED_TXPROCESSING-and-HW_80211_ENCAP-t.patch \
	file://patches-openwrt/subsys/323-mac80211-unify-802.3-offload-and-802.11-tx-status-co.patch \
	file://patches-openwrt/subsys/324-mac80211-support-using-ieee80211_tx_status_ext-to-fr.patch \
	file://patches-openwrt/subsys/325-mac80211-extend-ieee80211_tx_status_ext-to-support-b.patch \
	file://patches-openwrt/subsys/326-mac80211-notify-the-driver-when-a-sta-uses-4-address.patch \
	file://patches-openwrt/subsys/327-mac80211-reorganize-code-to-remove-a-forward-declara.patch \
	file://patches-openwrt/subsys/328-mac80211-extend-AQL-aggregation-estimation-to-HE-and.patch \
	file://patches-openwrt/subsys/329-mac80211-add-AQL-support-for-VHT160-tx-rates.patch \
	file://patches-openwrt/subsys/330-mac80211-allow-bigger-A-MSDU-sizes-in-VHT-even-if-HT.patch \
	file://patches-openwrt/subsys/331-mac80211-do-not-allow-bigger-VHT-MPDUs-than-the-hard.patch \
	file://patches-openwrt/subsys/332-mac80211-fix-regression-in-sta-connection-monitor.patch \
	file://patches-openwrt/subsys/333-mac80211-fix-memory-leak-on-filtered-powersave-frame.patch \
	file://patches-openwrt/subsys/370-mac80211-fix-misplaced-while-instead-of-if.patch \
	file://patches-openwrt/subsys/400-allow-ibss-mixed.patch \
	file://patches-openwrt/subsys/500-mac80211_configure_antenna_gain.patch \
"

# Patches from OpenWrt (ath)
SRC_URI += "\
	file://patches-openwrt/ath/070-ath_common_config.patch \
	file://patches-openwrt/ath/080-ath10k_thermal_config.patch \
	file://patches-openwrt/ath/120-owl-loader-compat.patch \
	file://patches-openwrt/ath/201-ath5k-WAR-for-AR71xx-PCI-bug.patch \
	file://patches-openwrt/ath/350-ath9k_hw-reset-AHB-WMAC-interface-on-AR91xx.patch \
	file://patches-openwrt/ath/351-ath9k_hw-issue-external-reset-for-QCA955x.patch \
	file://patches-openwrt/ath/354-ath9k-force-rx_clear-when-disabling-rx.patch \
	file://patches-openwrt/ath/356-Revert-ath9k-interpret-requested-txpower-in-EIRP-dom.patch \
	file://patches-openwrt/ath/365-ath9k-adjust-tx-power-reduction-for-US-regulatory-do.patch \
	file://patches-openwrt/ath/400-ath_move_debug_code.patch \
	file://patches-openwrt/ath/401-ath9k_blink_default.patch \
	file://patches-openwrt/ath/402-ath_regd_optional.patch \
	file://patches-openwrt/ath/403-world_regd_fixup.patch \
	file://patches-openwrt/ath/404-regd_no_assoc_hints.patch \
	file://patches-openwrt/ath/405-ath_regd_us.patch \
	file://patches-openwrt/ath/406-ath_relax_default_regd.patch \
	file://patches-openwrt/ath/410-ath9k_allow_adhoc_and_ap.patch \
	file://patches-openwrt/ath/411-ath5k_allow_adhoc_and_ap.patch \
	file://patches-openwrt/ath/420-ath5k_disable_fast_cc.patch \
	file://patches-openwrt/ath/430-add_ath5k_platform.patch \
	file://patches-openwrt/ath/431-add_platform_eeprom_support_to_ath5k.patch \
	file://patches-openwrt/ath/432-ath5k_add_pciids.patch \
	file://patches-openwrt/ath/440-ath5k_channel_bw_debugfs.patch \
	file://patches-openwrt/ath/450-ath9k-enabled-MFP-capability-unconditionally.patch \
	file://patches-openwrt/ath/500-ath9k_eeprom_debugfs.patch \
	file://patches-openwrt/ath/501-ath9k_ahb_init.patch \
	file://patches-openwrt/ath/510-ath9k_intr_mitigation_tweak.patch \
	file://patches-openwrt/ath/511-ath9k_reduce_rxbuf.patch \
	file://patches-openwrt/ath/512-ath9k_channelbw_debugfs.patch \
	file://patches-openwrt/ath/513-ath9k_add_pci_ids.patch \
	file://patches-openwrt/ath/530-ath9k_extra_leds.patch \
	file://patches-openwrt/ath/531-ath9k_extra_platform_leds.patch \
	file://patches-openwrt/ath/540-ath9k_reduce_ani_interval.patch \
	file://patches-openwrt/ath/542-ath9k_debugfs_diag.patch \
	file://patches-openwrt/ath/543-ath9k_entropy_from_adc.patch \
	file://patches-openwrt/ath/544-ath9k-ar933x-usb-hang-workaround.patch \
	file://patches-openwrt/ath/545-ath9k_ani_ws_detect.patch \
	file://patches-openwrt/ath/547-ath9k_led_defstate_fix.patch \
	file://patches-openwrt/ath/548-ath9k_enable_gpio_chip.patch \
	file://patches-openwrt/ath/549-ath9k_enable_gpio_buttons.patch \
	file://patches-openwrt/ath/550-ath9k-disable-bands-via-dt.patch \
	file://patches-openwrt/ath/551-ath9k_ubnt_uap_plus_hsr.patch \
	file://patches-openwrt/ath/552-ahb_of.patch \
	file://patches-openwrt/ath/553-ath9k_of_gpio_mask.patch \
	file://patches-openwrt/ath/921-ath10k_init_devices_synchronously.patch \
	file://patches-openwrt/ath/922-ath10k-increase-rx-buffer-size-to-2048.patch \
	file://patches-openwrt/ath/930-ath10k_add_tpt_led_trigger.patch \
	file://patches-openwrt/ath/974-ath10k_add-LED-and-GPIO-controlling-support-for-various-chipsets.patch \
	file://patches-openwrt/ath/975-ath10k-use-tpt-trigger-by-default.patch \
	file://patches-openwrt/ath/980-ath10k-fix-max-antenna-gain-unit.patch \
	file://patches-openwrt/ath/981-ath10k-adjust-tx-power-reduction-for-US-regulatory-d.patch \
"

# Patches from OpenWrt (brcm)
SRC_URI += "\
	file://patches-openwrt/brcm/040-brcmutil_option.patch \
	file://patches-openwrt/brcm/810-b43-gpio-mask-module-option.patch \
	file://patches-openwrt/brcm/811-b43_no_pio.patch \
	file://patches-openwrt/brcm/812-b43-add-antenna-control.patch \
	file://patches-openwrt/brcm/813-b43-reduce-number-of-RX-slots.patch \
	file://patches-openwrt/brcm/814-b43-only-use-gpio-0-1-for-led.patch \
	file://patches-openwrt/brcm/815-b43-always-take-overlapping-devs.patch \
	file://patches-openwrt/brcm/850-brcmsmac-remove-extra-regulation-restriction.patch \
	file://patches-openwrt/brcm/860-brcmfmac-register-wiphy-s-during-module_init.patch \
	file://patches-openwrt/brcm/861-brcmfmac-workaround-bug-with-some-inconsistent-BSSes.patch \
	file://patches-openwrt/brcm/862-brcmfmac-Disable-power-management.patch \
	file://patches-openwrt/brcm/863-brcmfmac-add-in-driver-tables-with-country-codes.patch \
	file://patches-openwrt/brcm/864-brcmfmac-do-not-use-internal-roaming-engine-by-default.patch \
	file://patches-openwrt/brcm/998-survey.patch \
"

# Patches from OpenWrt (mwl)
SRC_URI += "\
	file://patches-openwrt/mwl/700-mwl8k-missing-pci-id-for-WNR854T.patch \
	file://patches-openwrt/mwl/801-libertas-configure-sysfs-links.patch \
	file://patches-openwrt/mwl/802-libertas-set-wireless-macaddr.patch \
	file://patches-openwrt/mwl/940-mwl8k_init_devices_synchronously.patch \
"

# Patches from OpenWrt (rt2x00)
SRC_URI += "\
	file://patches-openwrt/rt2x00/002-rt2x00-define-RF5592-in-init_eeprom-routine.patch \
	file://patches-openwrt/rt2x00/100-rt2x00_options.patch \
	file://patches-openwrt/rt2x00/501-rt2x00-allow-to-build-rt2800soc-module-for-RT3883.patch \
	file://patches-openwrt/rt2x00/601-rt2x00-introduce-rt2x00_platform_h.patch \
	file://patches-openwrt/rt2x00/602-rt2x00-introduce-rt2x00eeprom.patch \
	file://patches-openwrt/rt2x00/603-rt2x00-of_load_eeprom_filename.patch \
	file://patches-openwrt/rt2x00/604-rt2x00-load-eeprom-on-SoC-from-a-mtd-device-defines-.patch \
	file://patches-openwrt/rt2x00/606-rt2x00-allow_disabling_bands_through_platform_data.patch \
	file://patches-openwrt/rt2x00/607-rt2x00-add_platform_data_mac_addr.patch \
	file://patches-openwrt/rt2x00/608-rt2x00-allow_disabling_bands_through_dts.patch \
	file://patches-openwrt/rt2x00/609-rt2x00-make-wmac-loadable-via-OF-on-rt288x-305x-SoC.patch \
	file://patches-openwrt/rt2x00/610-rt2x00-change-led-polarity-from-OF.patch \
	file://patches-openwrt/rt2x00/611-rt2x00-add-AP+STA-support.patch \
	file://patches-openwrt/rt2x00/612-rt2x00-led-tpt-trigger-support.patch \
	file://patches-openwrt/rt2x00/650-rt2x00-add-support-for-external-PA-on-MT7620.patch \
	file://patches-openwrt/rt2x00/982-rt2x00-add-rf-self-txdc-calibration.patch \
	file://patches-openwrt/rt2x00/983-rt2x00-add-r-calibration.patch \
	file://patches-openwrt/rt2x00/984-rt2x00-add-rxdcoc-calibration.patch \
	file://patches-openwrt/rt2x00/985-rt2x00-add-rxiq-calibration.patch \
	file://patches-openwrt/rt2x00/986-rt2x00-add-TX-LOFT-calibration.patch \
	file://patches-openwrt/rt2x00/990-rt2x00-mt7620-introduce-accessors-for-CHIP_VER-register.patch \
	file://patches-openwrt/rt2x00/991-rt2x00-mt7620-differentiate-based-on-SoC-CHIP_VER.patch \
	file://patches-openwrt/rt2x00/992-rt2x00-save-survey-for-every-channel-visited.patch \
"

# Patches from Tano
SRC_URI += "\
	file://patches-tano/0001-rtl8821ce-Add-driver-for-Realtek-RTL8821CE.patch \
	file://patches-tano/0002-Add-debug-output.patch \
	file://patches-tano/0003-Use-kfree_sensitive-instead-of-kzfree-for-kernels-5..patch \
	file://patches-tano/0004-nl80211-Fix-compilation-with-kernel-5.10.patch \
"

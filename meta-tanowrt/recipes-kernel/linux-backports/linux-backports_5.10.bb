#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2020-2021 Tano Systems LLC
# Anton Kikin <a.kikin@tano-systems.com>
#
require linux-backports.inc

PV = "5.10.16-1"
PR = "tano3.${INC_PR}"

FILESEXTRAPATHS:prepend = "${THISDIR}/${PN}_5.10/files:"
FILESEXTRAPATHS:prepend = "${THISDIR}/${PN}_5.10/configs:"
FILESEXTRAPATHS:prepend = "${THISDIR}/${PN}_5.10/patches:"

SRC_URI += "http://www.kernel.org/pub/linux/kernel/projects/backports/stable/v5.10.16/backports-${PV}.tar.gz"
SRC_URI[sha256sum] = "90005f3598b4b1fac4b0088f0b345ef2e8312df9f9f80c50aeb28497453888f5"

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
	file://patches-openwrt/subsys/100-remove-cryptoapi-dependencies.patch \
	file://patches-openwrt/subsys/110-mac80211_keep_keys_on_stop_ap.patch \
	file://patches-openwrt/subsys/120-cfg80211_allow_perm_addr_change.patch \
	file://patches-openwrt/subsys/130-disable-fils.patch \
	file://patches-openwrt/subsys/131-Revert-mac80211-aes-cmac-switch-to-shash-CMAC-driver.patch \
	file://patches-openwrt/subsys/132-mac80211-remove-cmac-dependency.patch \
	file://patches-openwrt/subsys/150-disable_addr_notifier.patch \
	file://patches-openwrt/subsys/210-ap_scan.patch \
	file://patches-openwrt/subsys/304-mac80211-sta-randomize-BA-session-dialog-token-alloc.patch \
	file://patches-openwrt/subsys/310-net-fq_impl-bulk-free-packets-from-a-flow-on-overmem.patch \
	file://patches-openwrt/subsys/311-net-fq_impl-drop-get_default_func-move-default-flow-.patch \
	file://patches-openwrt/subsys/312-net-fq_impl-do-not-maintain-a-backlog-sorted-list-of.patch \
	file://patches-openwrt/subsys/315-mac80211-add-rx-decapsulation-offload-support.patch \
	file://patches-openwrt/subsys/337-mac80211-minstrel_ht-clean-up-CCK-code.patch \
	file://patches-openwrt/subsys/338-mac80211-minstrel_ht-add-support-for-OFDM-rates-on-n.patch \
	file://patches-openwrt/subsys/339-mac80211-remove-legacy-minstrel-rate-control.patch \
	file://patches-openwrt/subsys/340-mac80211-minstrel_ht-remove-old-ewma-based-rate-aver.patch \
	file://patches-openwrt/subsys/341-mac80211-minstrel_ht-improve-ampdu-length-estimation.patch \
	file://patches-openwrt/subsys/342-mac80211-minstrel_ht-improve-sample-rate-selection.patch \
	file://patches-openwrt/subsys/343-mac80211-minstrel_ht-fix-max-probability-rate-select.patch \
	file://patches-openwrt/subsys/344-mac80211-minstrel_ht-increase-stats-update-interval.patch \
	file://patches-openwrt/subsys/345-mac80211-minstrel_ht-fix-rounding-error-in-throughpu.patch \
	file://patches-openwrt/subsys/346-mac80211-minstrel_ht-use-bitfields-to-encode-rate-in.patch \
	file://patches-openwrt/subsys/347-mac80211-minstrel_ht-update-total-packets-counter-in.patch \
	file://patches-openwrt/subsys/348-mac80211-minstrel_ht-reduce-the-need-to-sample-slowe.patch \
	file://patches-openwrt/subsys/349-mac80211-minstrel_ht-significantly-redesign-the-rate.patch \
	file://patches-openwrt/subsys/350-mac80211-minstrel_ht-show-sampling-rates-in-debugfs.patch \
	file://patches-openwrt/subsys/351-mac80211-minstrel_ht-remove-sample-rate-switching-co.patch \
	file://patches-openwrt/subsys/352-mac80211-minstrel_ht-fix-regression-in-the-max_prob_.patch \
	file://patches-openwrt/subsys/354-mac80211-minstrel_ht-reduce-fluctuations-in-rate-pro.patch \
	file://patches-openwrt/subsys/355-mac80211-minstrel_ht-rework-rate-downgrade-code-and-.patch \
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
	file://patches-openwrt/ath/560-ath9k-fix-transmitting-to-stations-in-dynamic-SMPS-m.patch \
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

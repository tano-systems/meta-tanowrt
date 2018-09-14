SRC_URI_append = "\
	file://dropbear.config \
	file://dropbear.init \
"

# OpenWrt configuration
EXTRA_OECONF_append += "\
	--disable-pam \
	--enable-openpty \
	--enable-syslog \
	--disable-lastlog \
	--disable-utmpx \
	--disable-utmp \
	--disable-wtmp \
	--disable-wtmpx \
	--disable-loginfunc \
	--disable-pututline \
	--disable-pututxline \
	--disable-zlib \
	--enable-bundled-libtom \
"

inherit openwrt-services

OPENWRT_SERVICE_PACKAGES = "dropbear"
OPENWRT_SERVICE_SCRIPTS_dropbear += "dropbear"
OPENWRT_SERVICE_STATE_dropbear-dropbear ?= "enabled"

# OpenWrt patches
SRC_URI_append = "\
	file://010-runtime-maxauthtries.patch \
	file://100-pubkey_path.patch \
	file://110-change_user.patch \
	file://120-openwrt_options.patch \
	file://130-ssh_ignore_x_args.patch \
	file://140-disable_assert.patch \
	file://150-dbconvert_standalone.patch \
	file://610-skip-default-keys-in-custom-runs.patch \
	file://600-allow-blank-root-password.patch \
"

FILES_${PN} += "/root"

do_install_append() {
    install -Dm 0755 ${WORKDIR}/dropbear.init    ${D}${sysconfdir}/init.d/dropbear
    install -Dm 0644 ${WORKDIR}/dropbear.config  ${D}${sysconfdir}/config/dropbear

    touch ${D}${sysconfdir}/dropbear/dropbear_rsa_host_key
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/dropbear \
	${sysconfdir}/dropbear/dropbear_rsa_host_key \
"

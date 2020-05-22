# Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>

INC_PR = "3"

# OpenWrt files
SRC_URI_append = "\
	file://dropbear.config \
	file://dropbear.init \
	file://dropbear.failsafe \
"

# OpenWrt configuration
EXTRA_OECONF_append += "\
	--disable-pam \
	--enable-openpty \
	--enable-syslog \
	--disable-lastlog \
	--disable-utmpx \
	--disable-wtmp \
	--disable-wtmpx \
	--disable-loginfunc \
	--disable-pututxline \
	--disable-zlib \
	--enable-bundled-libtom \
"

inherit tanowrt-services

TANOWRT_SERVICE_PACKAGES = "dropbear"
TANOWRT_SERVICE_SCRIPTS_dropbear += "dropbear"
TANOWRT_SERVICE_STATE_dropbear-dropbear ?= "enabled"

# OpenWrt patches
SRC_URI_append = "\
	file://100-pubkey_path.patch \
	file://110-change_user.patch \
	file://130-ssh_ignore_x_args.patch \
	file://140-disable_assert.patch \
	file://160-lto-jobserver.patch \
	file://600-allow-blank-root-password.patch \
	file://900-configure-hardening.patch \
	file://901-bundled-libs-cflags.patch \
"

FILES_${PN} += "/root /lib/preinit"

do_configure_append() {
	: > ${B}/localoptions.h
	
	echo '#define DROPBEAR_CURVE25519 1' >> ${B}/localoptions.h
	echo '#define DROPBEAR_ECDSA 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_ECDH 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_ECDSA 0' >> ${B}/localoptions.h

	echo '#define INETD_MODE 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_CLI_NETCAT 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_3DES 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_DSS 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_ENABLE_CBC_MODE 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_SHA1_96_HMAC 0' >> ${B}/localoptions.h
	echo '#define DROPBEAR_USE_PASSWORD_ENV 0' >> ${B}/localoptions.h

	# Remove protocol idented software version number
	sed -E -i -e 's,^(#define LOCAL_IDENT) .*,\1 "SSH-2.0-dropbear",g' \
		${S}/sysoptions.h
}

do_install_append() {
    install -Dm 0755 ${WORKDIR}/dropbear.init    ${D}${sysconfdir}/init.d/dropbear
    install -Dm 0644 ${WORKDIR}/dropbear.config  ${D}${sysconfdir}/config/dropbear

    touch ${D}${sysconfdir}/dropbear/dropbear_rsa_host_key

    rm -rf ${D}${sysconfdir}/default

    # Make link to dropbearkey in /usr/bin
    ln -s ${sbindir}/dropbear ${D}${bindir}/dropbearkey

    install -d ${D}/lib/preinit
    install -m 0644 ${WORKDIR}/dropbear.failsafe ${D}/lib/preinit/99_10_failsafe_dropbear
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/dropbear \
	${sysconfdir}/dropbear/dropbear_rsa_host_key \
"

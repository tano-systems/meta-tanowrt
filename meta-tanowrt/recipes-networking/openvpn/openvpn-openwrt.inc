#
# Open source VPN solution using SSL
# OpenWrt recipe
#
# Copyright (c) 2018-2020, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

INC_PR = "9"

inherit tanowrt-services

SRC_URI += " \
    file://openvpn.config \
    file://openvpn.init \
    file://openvpn.options \
    file://openvpn.upgrade \
    file://etc/openvpn.user \
    file://etc/hotplug.d/openvpn/01-user \
    file://usr/libexec/openvpn-hotplug \
    file://lib/functions/openvpn.sh \
"

# Patches
SRC_URI += " \
    file://100-mbedtls-disable-runtime-version-check.patch \
    file://001-reproducible-remove_DATE.patch \
    file://220-disable_des.patch \
    file://210-build_always_use_internal_lz4.patch"

TANOWRT_SERVICE_PACKAGES = "openvpn"
TANOWRT_SERVICE_SCRIPTS_openvpn += "openvpn"
TANOWRT_SERVICE_STATE_openvpn-openvpn ?= "disabled"

CFLAGS += "-ffunction-sections -fdata-sections"

PACKAGECONFIG ??= "\
	ssl \
	lzo \
	lz4 \
	server \
	iproute2 \
	fragment \
	multihome \
	def-auth \
	pf \
	${@bb.utils.contains('DISTRO_FEATURES', 'pam', '', '', d)} \
"

PACKAGECONFIG[ssl]                   = "--enable-crypto,--disable-crypto,openssl"
PACKAGECONFIG[small]                 = "--enable-small,,"
PACKAGECONFIG[lzo]                   = "--enable-lzo,--disable-lzo,lzo"
PACKAGECONFIG[lz4]                   = "--enable-lz4,--disable-lz4,lz4"
PACKAGECONFIG[pam]                   = "--enable-plugin-auth-pam,--disable-plugin-auth-pam,libpam"
PACKAGECONFIG[server]                = "--enable-server,--disable-server,"
PACKAGECONFIG[mgmt]                  = "--enable-management,--disable-management,"
PACKAGECONFIG[fragment]              = "--enable-fragment,--disable-fragment,"
PACKAGECONFIG[multihome]             = "--enable-multihome,--disable-multihome,"
PACKAGECONFIG[iproute2]              = "--enable-iproute2,--disable-iproute2,iproute2"
PACKAGECONFIG[def-auth]              = "--enable-def-auth,--disable-def-auth,"
PACKAGECONFIG[pf]                    = "--enable-pf,--disable-pf,"
PACKAGECONFIG[x509-alt-username-ssl] = "--enable-x509-alt-username-ssl,,openssl"
PACKAGECONFIG[sample]                = ",,"

EXTRA_OE_CONF += "\
	--disable-selinux \
	--disable-systemd \
	--disable-plugins \
	--disable-debug \
	--disable-pkcs11 \
	"${@bb.utils.contains('PACKAGECONFIG', 'ssl', '--with-crypto-library=openssl', '', d)}" \
"

inherit kmod/tun
do_configure[depends] += "virtual/kernel:do_shared_workdir"

FILES_${PN} += "/lib/upgrade/keep.d /lib/functions/openvpn.sh"

do_install_append() {
	# OpenWrt files

	install -dm 0755 ${D}${sysconfdir}/openvpn

	if [ "${@bb.utils.contains('PACKAGECONFIG', 'sample', 'true', 'false', d)}" = "false" ]; then
		# Remove samples
		rm -rf ${D}${sysconfdir}/openvpn/sample
	fi

	# Install procd init script
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${WORKDIR}/openvpn.init ${D}${sysconfdir}/init.d/openvpn

	# Install service UCI config
	install -d ${D}${sysconfdir}/config
	install -m 644 ${WORKDIR}/openvpn.config ${D}${sysconfdir}/config/openvpn

	# Install upgrade
	install -d ${D}${base_libdir}/upgrade/keep.d
	install -m 644 ${WORKDIR}/openvpn.upgrade ${D}${base_libdir}/upgrade/keep.d/openvpn

	# Install OpenVPN options
	install -d ${D}${datadir}/openvpn
	install -m 644 ${WORKDIR}/openvpn.options ${D}${datadir}/openvpn/openvpn.options

	# Install OpenVPN functions lib
	install -d ${D}/lib/functions
	install -m 644 ${WORKDIR}/lib/functions/openvpn.sh ${D}/lib/functions/openvpn.sh

	# Hotplug functionality
	install -d ${D}/usr/libexec
	install -d ${D}${sysconfdir}/hotplug.d/openvpn
	install -m 755 ${WORKDIR}/usr/libexec/openvpn-hotplug ${D}/usr/libexec/
	install -m 644 ${WORKDIR}/etc/hotplug.d/openvpn/01-user ${D}${sysconfdir}/hotplug.d/openvpn/
	install -m 644 ${WORKDIR}/etc/openvpn.user ${D}${sysconfdir}/
}

CONFFILES_${PN}_append = "\
	${sysconfdir}/config/openvpn \
"

PACKAGES_remove = "${PN}-sample"

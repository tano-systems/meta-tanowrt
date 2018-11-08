#
# This file Copyright (C) 2018 Anton Kikin <a.kikin@tano-systems.com>
# Partially taken from meta-openembedded layer
#
SUMMARY = "Collects and summarises system performance statistics"
DESCRIPTION = "collectd is a daemon which collects system performance statistics periodically and provides mechanisms to store the values in a variety of ways, for example in RRD files."
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1bd21f19f7f0c61a7be8ecacb0e28854"

PV = "5.8.1"
PR = "tano1"

FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/patches:${THISDIR}/${PN}/files:"
DEPENDS = "rrdtool libgcrypt libtool flex"
RDEPENDS_${PN} += "iwinfo"

SRC_URI = "http://collectd.org/files/collectd-${PV}.tar.bz2"
SRC_URI[sha256sum] = "e796fda27ce06377f491ad91aa286962a68c2b54076aa77a29673d53204453da"

# Files (OpenWrt)
SRC_URI += "\
	file://collectd.conf \
	file://collectd.init \
"

# Patches (from meta-openembedded)
SRC_URI += "\
    file://0001-no-gcrypt-badpath.patch \
    file://0002-conditionally-check-libvirt.patch \
    file://0003-fix-to-build-with-glibc-2.25.patch \
    file://0004-configure-Check-for-Wno-error-format-truncation-comp.patch \
    file://0005-Disable-new-gcc8-warnings.patch \
"

# Patches (from OpenWrt)
SRC_URI += "\
    file://1001-undefined-AM_PATH_LIBGCRYPT.patch \
    file://1050-backport-modbus-little-endian.patch \
    file://1100-rrdtool-add-rrasingle-option.patch \
    file://1140-fix-fqdnlookup.patch \
    file://1300-delay-first-read-cycle.patch \
    file://1400-fix-olsrd-get-all.patch \
    file://1600-fix-libmodbus-detection.patch \
    file://1700-disable-sys-capability-check.patch \
    file://1900-add-iwinfo-plugin.patch \
    file://1920-fix-ping-droprate.patch \
"

# update-rc.d systemd pythonnative 
inherit autotools pkgconfig openwrt-services

OPENWRT_SERVICE_PACKAGES = "collectd"
OPENWRT_SERVICE_SCRIPTS_collectd += "collectd"
OPENWRT_SERVICE_STATE_collectd-collectd ?= "enabled"

# Floatingpoint layout, architecture dependent
# 'nothing', 'endianflip' or 'intswap'
FPLAYOUT ?= "--with-fp-layout=nothing"

PACKAGECONFIG ??= "\
    conntrack \
    contextswitch \
    cpu \
    entropy \
    exec \
    filecount \
    network \
    disk \
    interface \
    iptables \
    irq \
    iwinfo \
    load \
    memory \
    ping \
    processes \
    rrdtool \
    tcpconns \
    uptime \
"

PACKAGECONFIG[aggregation] = "--enable-aggregation,--disable-aggregation,"
PACKAGECONFIG[apache] = "--enable-apache,--disable-apache,libcurl"
PACKAGECONFIG[apcups] = "--enable-apcups,--disable-apcups,"
PACKAGECONFIG[ascent] = "--enable-ascent,--disable-ascent,libcurl libxml2"
PACKAGECONFIG[bind]   = "--enable-bind,--disable-bind,libcurl libxml2"
PACKAGECONFIG[chrony] = "--enable-chrony,--disable-chrony,"
PACKAGECONFIG[conntrack] = "--enable-conntrack,--disable-conntrack,"
PACKAGECONFIG[contextswitch] = "--enable-contextswitch,--disable-contextswitch,"
PACKAGECONFIG[cpu] = "--enable-cpu,--disable-cpu,"
PACKAGECONFIG[csv] = "--enable-csv,--disable-csv,"
PACKAGECONFIG[curl] = "--enable-curl,--disable-curl,libcurl"
PACKAGECONFIG[df] = "--enable-df,--disable-df,"
PACKAGECONFIG[disk] = "--enable-disk,--disable-disk,"
PACKAGECONFIG[dns] = "--enable-dns,--disable-dns,libpcap"
PACKAGECONFIG[email] = "--enable-email,--disable-email,"
PACKAGECONFIG[entropy] = "--enable-entropy,--disable-entropy,"
PACKAGECONFIG[exec] = "--enable-exec,--disable-exec,"
PACKAGECONFIG[filecount] = "--enable-filecount,--disable-filecount,"
PACKAGECONFIG[fscache] = "--enable-fscache,--disable-fscache,"
PACKAGECONFIG[interface] = "--enable-interface,--disable-interface,"
PACKAGECONFIG[iptables] = "--enable-iptables,--disable-iptables,iptables"
PACKAGECONFIG[irq] = "--enable-irq,--disable-irq,"
PACKAGECONFIG[iwinfo] = "--enable-iwinfo,--disable-iwinfo,iwinfo"
PACKAGECONFIG[load] = "--enable-load,--disable-load,"
PACKAGECONFIG[logfile] = "--enable-logfile,--disable-logfile,"
PACKAGECONFIG[match_empty_counter] = "--enable-match_empty_counter,--disable-match_empty_counter,"
PACKAGECONFIG[match_hashed] = "--enable-match_hashed,--disable-match_hashed,"
PACKAGECONFIG[match_regex] = "--enable-match_regex,--disable-match_regex,"
PACKAGECONFIG[match_timediff] = "--enable-match_timediff,--disable-match_timediff,"
PACKAGECONFIG[match_value] = "--enable-match_value,--disable-match_value,"
PACKAGECONFIG[memory] = "--enable-memory,--disable-memory,"
PACKAGECONFIG[modbus] = "--enable-modbus,--disable-modbus,libmodbus"
PACKAGECONFIG[mqtt] = "--enable-mqtt,--disable-mqtt,libmosquitto"
PACKAGECONFIG[mysql]      = "--enable-mysql --with-libmysql=yes, --disable-mysql --with-libmysql=no,mysql5"
PACKAGECONFIG[netlink] = "--enable-netlink,--disable-netlink,libmnl"
PACKAGECONFIG[network] = "--enable-network,--disable-network,libgcrypt"
PACKAGECONFIG[nginx] = "--enable-nginx,--disable-nginx,libcurl"
PACKAGECONFIG[ntpd] = "--enable-ntpd,--disable-ntpd,"
PACKAGECONFIG[olsrd] = "--enable-olsrd,--disable-olsrd,"
PACKAGECONFIG[openvpn] = "--enable-openvpn,--disable-openvpn,"
PACKAGECONFIG[ping] = "--enable-ping,--disable-ping,liboping"
PACKAGECONFIG[postgresql] = "--enable-postgresql --with-libpq=yes, --disable-postgresql --with-libpq=no,postgresql"
PACKAGECONFIG[powerdns] = "--enable-powerdns,--disable-powerdns,"
PACKAGECONFIG[processes] = "--enable-processes,--disable-processes,"
PACKAGECONFIG[protocols] = "--enable-protocols,--disable-protocols,"
PACKAGECONFIG[rrdtool] = "--enable-rrdtool,--disable-rrdtool,rrdtool"
PACKAGECONFIG[sensors] = "--enable-sensors --with-libsensors=yes, --disable-sensors --with-libsensors=no,lmsensors"
PACKAGECONFIG[snmp] = "--enable-snmp,--disable-snmp --with-libnetsnmp=no,net-snmp"
PACKAGECONFIG[syslog] = "--enable-syslog,--disable-syslog,"
PACKAGECONFIG[table] = "--enable-table,--disable-table,"
PACKAGECONFIG[tail] = "--enable-tail,--disable-tail,"
PACKAGECONFIG[tail_csv] = "--enable-tail_csv,--disable-tail_csv,"
PACKAGECONFIG[target_notification] = "--enable-target_notification,--disable-target_notification,"
PACKAGECONFIG[target_replace] = "--enable-target_replace,--disable-target_replace,"
PACKAGECONFIG[target_scale] = "--enable-target_scale,--disable-target_scale,"
PACKAGECONFIG[target_set] = "--enable-target_set,--disable-target_set,"
PACKAGECONFIG[target_v5upgrade] = "--enable-target_v5upgrade,--disable-target_v5upgrade,"
PACKAGECONFIG[tcpconns] = "--enable-tcpconns,--disable-tcpconns,"
PACKAGECONFIG[teamspeak2] = "--enable-teamspeak2,--disable-teamspeak2,"
PACKAGECONFIG[thermal] = "--enable-thermal,--disable-thermal,"
PACKAGECONFIG[threshold] = "--enable-threshold,--disable-threshold,"
PACKAGECONFIG[unixsock] = "--enable-unixsock,--disable-unixsock,"
PACKAGECONFIG[uptime] = "--enable-uptime,--disable-uptime,"
PACKAGECONFIG[users] = "--enable-users,--disable-users,"
PACKAGECONFIG[vmem] = "--enable-vmem,--disable-vmem,"
PACKAGECONFIG[wireless] = "--enable-wireless,--disable-wireless,"
PACKAGECONFIG[write_graphite] = "--enable-write_graphite,--disable-write_graphite,"
PACKAGECONFIG[write_http] = "--enable-write_http,--disable-write_http,libcurl"

EXTRA_OECONF = " \
    ${FPLAYOUT} \
    --disable-werror \
    --disable-debug \
    --enable-daemon \
    --with-libperl=no \
    --with-perl-bindings=no \
    --with-libgcrypt=${STAGING_BINDIR_CROSS}/libgcrypt-config \
    --with-nan-emulation \
    --with-libyajl=no \
    --without-perl-bindings \
    --without-libudev \
    --without-libpython \
    --without-librabbitmq \
    --without-java \
    --without-libmemcached \
    --without-libesmtp \
    --without-libatasmart \
    --without-libowcapi \
    --without-libldap \
    --disable-openldap \
    --disable-amqp \
    --disable-apple_sensors \
    --disable-aquaero \
    --disable-barometer \
    --disable-battery \
    --disable-ceph \
    --disable-cgroups \
    --disable-cpusleep \
    --disable-curl_json \
    --disable-curl_xml \
    --disable-dbi \
    --disable-dpdkevents \
    --disable-dpdkstat \
    --disable-drbd \
    --disable-ethstat \
    --disable-fhcount \
    --disable-gmond \
    --disable-gps \
    --disable-grpc \
    --disable-hddtemp \
    --disable-hugepages \
    --disable-intel_pmu \
    --disable-intel_rdt \
    --disable-ipc \
    --disable-ipmi \
    --disable-ipvs \
    --disable-java \
    --disable-log_logstash \
    --disable-lua \
    --disable-lvm \
    --disable-lpar \
    --disable-madwifi \
    --disable-mbmon \
    --disable-mcelog \
    --disable-md \
    --disable-memcachec \
    --disable-memcached \
    --disable-mic \
    --disable-multimeter \
    --disable-netapp \
    --disable-nfs \
    --disable-notify_desktop \
    --disable-notify_email \
    --disable-notify_nagios \
    --disable-numa \
    --disable-openldap \
    --disable-oracle \
    --disable-ovs_events \
    --disable-ovs_stats \
    --disable-perl \
    --disable-pf \
    --disable-pinba \
    --disable-python \
    --disable-redis \
    --disable-routeros \
    --disable-rrdcached \
    --disable-serial \
    --disable-sigrok \
    --disable-smart \
    --disable-snmp_agent \
    --disable-statsd \
    --disable-swap \
    --disable-synproxy \
    --disable-tape \
    --disable-tokyotyrant \
    --disable-turbostat \
    --disable-uuid \
    --disable-varnish \
    --disable-virt \
    --disable-vserver \
    --disable-write_kafka \
    --disable-write_log \
    --disable-write_mongodb \
    --disable-write_prometheus \
    --disable-write_redis \
    --disable-write_riemann \
    --disable-write_sensu \
    --disable-write_tsdb \
    --disable-xencpu \
    --disable-xmms \
    --disable-zfs_arc \
    --disable-zone \
    --disable-zookeeper \
    --disable-cpufreq \
    --disable-nut \
    --disable-onewire \
    --disable-ted \
"

do_install_append() {
    # Install configuration files
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/collectd/conf.d
    install -m 0644 ${WORKDIR}/collectd.conf ${D}${sysconfdir}/collectd.conf

    # Install init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/collectd.init ${D}${sysconfdir}/init.d/collectd

    rmdir "${D}${localstatedir}/run"
    rmdir --ignore-fail-on-non-empty "${D}${localstatedir}"
}

CONFFILES_${PN} = "${sysconfdir}/collectd.conf"

# threshold.so load.so are also provided by gegl
# disk.so is also provided by libgphoto2-camlibs
PRIVATE_LIBS = "threshold.so load.so disk.so"

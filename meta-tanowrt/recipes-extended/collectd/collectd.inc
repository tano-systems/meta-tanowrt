#
# SPDX-License-Identifier: MIT
#
# This file Copyright (C) 2018-2020 Anton Kikin <a.kikin@tano-systems.com>
# Partially taken from meta-openembedded layer
#
INC_PR = "7"

SUMMARY = "Collects and summarises system performance statistics"
DESCRIPTION = "collectd is a daemon which collects system performance statistics periodically and provides mechanisms to store the values in a variety of ways, for example in RRD files."
LICENSE = "GPLv2 & MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=1bd21f19f7f0c61a7be8ecacb0e28854"

DEPENDS = "rrdtool libgcrypt libtool flex"
RDEPENDS:${PN} += "${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iwinfo', '', d)}"

# Files (OpenWrt)
SRC_URI += "\
	file://collectd.uci \
	file://collectd.init \
	file://usr/share/collectd/plugin/apcups.json \
	file://usr/share/collectd/plugin/conntrack.json \
	file://usr/share/collectd/plugin/contextswitch.json \
	file://usr/share/collectd/plugin/cpu.json \
	file://usr/share/collectd/plugin/cpufreq.json \
	file://usr/share/collectd/plugin/csv.json \
	file://usr/share/collectd/plugin/curl.json \
	file://usr/share/collectd/plugin/df.json \
	file://usr/share/collectd/plugin/disk.json \
	file://usr/share/collectd/plugin/dns.json \
	file://usr/share/collectd/plugin/email.json \
	file://usr/share/collectd/plugin/entropy.json \
	file://usr/share/collectd/plugin/exec.json \
	file://usr/share/collectd/plugin/interface.json \
	file://usr/share/collectd/plugin/iptables.json \
	file://usr/share/collectd/plugin/irq.json \
	file://usr/share/collectd/plugin/iwinfo.json \
	file://usr/share/collectd/plugin/load.json \
	file://usr/share/collectd/plugin/logfile.json \
	file://usr/share/collectd/plugin/lua.json \
	file://usr/share/collectd/plugin/memory.json \
	file://usr/share/collectd/plugin/netlink.json \
	file://usr/share/collectd/plugin/network.json \
	file://usr/share/collectd/plugin/nut.json \
	file://usr/share/collectd/plugin/olsrd.json \
	file://usr/share/collectd/plugin/openvpn.json \
	file://usr/share/collectd/plugin/ping.json \
	file://usr/share/collectd/plugin/processes.json \
	file://usr/share/collectd/plugin/rrdtool.json \
	file://usr/share/collectd/plugin/sensors.json \
	file://usr/share/collectd/plugin/syslog.json \
	file://usr/share/collectd/plugin/tcpconns.json \
	file://usr/share/collectd/plugin/thermal.json \
	file://usr/share/collectd/plugin/ubi.json \
	file://usr/share/collectd/plugin/unixsock.json \
	file://usr/share/collectd/plugin/uptime.json \
	file://usr/share/collectd/plugin/vmem.json \
	file://exec-scripts/sqm_collectd.sh \
"

inherit autotools pkgconfig tanowrt-services

TANOWRT_SERVICE_PACKAGES = "collectd"
TANOWRT_SERVICE_SCRIPTS_collectd += "collectd"
TANOWRT_SERVICE_STATE_collectd-collectd ?= "enabled"

# Floatingpoint layout, architecture dependent
# 'nothing', 'endianflip' or 'intswap'
FPLAYOUT ?= "--with-fp-layout=nothing"

PACKAGECONFIG ??= "\
    conntrack \
    contextswitch \
    cpu \
    entropy \
    exec \
    network \
    disk \
    interface \
    iptables \
    irq \
    ${@bb.utils.contains('COMBINED_FEATURES', 'wifi', 'iwinfo', '', d)} \
    load \
    memory \
    ping \
    processes \
    rrdtool \
    tcpconns \
    uptime \
    syslog \
"

PACKAGECONFIG[aggregation] = "--enable-aggregation,--disable-aggregation,"
PACKAGECONFIG[apache] = "--enable-apache,--disable-apache,libcurl"
PACKAGECONFIG[apcups] = "--enable-apcups,--disable-apcups,"
PACKAGECONFIG[ascent] = "--enable-ascent,--disable-ascent,libcurl libxml2"
PACKAGECONFIG[bind]   = "--enable-bind,--disable-bind,libcurl libxml2"
PACKAGECONFIG[buddyinfo] = "--enable-buddyinfo,--disable-buddyinfo,"
PACKAGECONFIG[capabilities] = "--enable-capabilities,--disable-capabilities,"
PACKAGECONFIG[check_uptime] = "--enable-check_uptime,--disable-check_uptime,"
PACKAGECONFIG[chrony] = "--enable-chrony,--disable-chrony,"
PACKAGECONFIG[conntrack] = "--enable-conntrack,--disable-conntrack,"
PACKAGECONFIG[contextswitch] = "--enable-contextswitch,--disable-contextswitch,"
PACKAGECONFIG[cpu] = "--enable-cpu,--disable-cpu,"
PACKAGECONFIG[cpufreq] = "--enable-cpufreq,--disable-cpufreq,"
PACKAGECONFIG[csv] = "--enable-csv,--disable-csv,"
PACKAGECONFIG[curl] = "--enable-curl,--disable-curl,libcurl"
PACKAGECONFIG[dcpmm] = "--enable-dcpmm,--disable-dcpmm,"
PACKAGECONFIG[df] = "--enable-df,--disable-df,"
PACKAGECONFIG[disk] = "--enable-disk,--disable-disk,"
PACKAGECONFIG[dns] = "--enable-dns,--disable-dns,libpcap"
PACKAGECONFIG[dpdk_telemetry] = "--enable-dpdk_telemetry,--disable-dpdk_telemetry,"
PACKAGECONFIG[email] = "--enable-email,--disable-email,"
PACKAGECONFIG[entropy] = "--enable-entropy,--disable-entropy,"
PACKAGECONFIG[exec] = "--enable-exec,--disable-exec,"
PACKAGECONFIG[filecount] = "--enable-filecount,--disable-filecount,"
PACKAGECONFIG[fhcount] = "--enable-fhcount,--disable-fhcount,"
PACKAGECONFIG[fscache] = "--enable-fscache,--disable-fscache,"
PACKAGECONFIG[interface] = "--enable-interface,--disable-interface,"
PACKAGECONFIG[infiniband] = "--enable-infiniband,--disable-infiniband,"
PACKAGECONFIG[ipstats] = "--enable-ipstats,--disable-ipstats,"
PACKAGECONFIG[iptables] = "--enable-iptables,--disable-iptables,iptables"
PACKAGECONFIG[irq] = "--enable-irq,--disable-irq,"
PACKAGECONFIG[iwinfo] = "--enable-iwinfo,--disable-iwinfo,iwinfo"
PACKAGECONFIG[load] = "--enable-load,--disable-load,"
PACKAGECONFIG[logfile] = "--enable-logfile,--disable-logfile,"
PACKAGECONFIG[logparser] = "--enable-logparser,--disable-logparser,"
PACKAGECONFIG[match_empty_counter] = "--enable-match_empty_counter,--disable-match_empty_counter,"
PACKAGECONFIG[match_hashed] = "--enable-match_hashed,--disable-match_hashed,"
PACKAGECONFIG[match_regex] = "--enable-match_regex,--disable-match_regex,"
PACKAGECONFIG[match_timediff] = "--enable-match_timediff,--disable-match_timediff,"
PACKAGECONFIG[match_value] = "--enable-match_value,--disable-match_value,"
PACKAGECONFIG[mdevents] = "--enable-mdevents,--disable-mdevents,"
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
PACKAGECONFIG[pcie_errors] = "--enable-pcie_errors,--disable-pcie_errors,"
PACKAGECONFIG[ping] = "--enable-ping,--disable-ping,liboping"
PACKAGECONFIG[postgresql] = "--enable-postgresql --with-libpq=yes, --disable-postgresql --with-libpq=no,postgresql"
PACKAGECONFIG[powerdns] = "--enable-powerdns,--disable-powerdns,"
PACKAGECONFIG[processes] = "--enable-processes,--disable-processes,"
PACKAGECONFIG[protocols] = "--enable-protocols,--disable-protocols,"
PACKAGECONFIG[redfish] = "--enable-redfish,--disable-redfish,"
PACKAGECONFIG[rrdtool] = "--enable-rrdtool,--disable-rrdtool,rrdtool"
PACKAGECONFIG[sensors] = "--enable-sensors --with-libsensors=yes, --disable-sensors --with-libsensors=no,lmsensors,lmsensors-sensors"
PACKAGECONFIG[slurm] = "--enable-slurm,--disable-slurm,"
PACKAGECONFIG[snmp] = "--enable-snmp,--disable-snmp --with-libnetsnmp=no,net-snmp"
PACKAGECONFIG[sqm] = ",,"
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
PACKAGECONFIG[ubi] = "--enable-ubi,--disable-ubi,"
PACKAGECONFIG[unixsock] = "--enable-unixsock,--disable-unixsock,"
PACKAGECONFIG[uptime] = "--enable-uptime,--disable-uptime,"
PACKAGECONFIG[users] = "--enable-users,--disable-users,"
PACKAGECONFIG[vmem] = "--enable-vmem,--disable-vmem,"
PACKAGECONFIG[wireless] = "--enable-wireless,--disable-wireless,"
PACKAGECONFIG[write_graphite] = "--enable-write_graphite,--disable-write_graphite,"
PACKAGECONFIG[write_http] = "--enable-write_http,--disable-write_http,libcurl"
PACKAGECONFIG[write_influxdb_udp] = "--enable-write_influxdb_udp,--disable-write_influxdb_udp,"
PACKAGECONFIG[write_syslog] = "--enable-write_syslog,--disable-write_syslog,"

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
    --disable-nut \
    --disable-onewire \
    --disable-ted \
"

do_install:append() {
    # Install configuration files
    install -d ${D}${sysconfdir}
    install -d ${D}${sysconfdir}/collectd/conf.d

    # Install init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/collectd.init ${D}${sysconfdir}/init.d/collectd

    sed -i -e "s:LIBDIR=\"/usr/lib\":LIBDIR=\"${libdir}\":g" \
              ${D}${sysconfdir}/init.d/collectd

    # Install UCI config
    install -d ${D}${sysconfdir}/config
    install -m 0644 ${WORKDIR}/collectd.uci ${D}${sysconfdir}/config/collectd

    sed -i -e "s:PluginDir '/usr/lib/collectd':PluginDir '${libdir}/collectd':g" \
              ${D}${sysconfdir}/config/collectd

    # Install plugin JSONs
    install -d ${D}/usr/share/collectd/plugin
    for plugin in $(ls ${D}${libdir}/collectd/*.so); do
        PLUGIN_FILE=$(basename ${plugin})
        PLUGIN_NAME=${PLUGIN_FILE%.*}
        if [ -e ${WORKDIR}/usr/share/collectd/plugin/${PLUGIN_NAME}.json ]; then
            install -m 0644 ${WORKDIR}/usr/share/collectd/plugin/${PLUGIN_NAME}.json \
                                  ${D}/usr/share/collectd/plugin/${PLUGIN_NAME}.json
        else
            bbwarn "No JSON configuration template for plugin ${PLUGIN_FILE}"
        fi
    done

    if [ "${@bb.utils.contains('PACKAGECONFIG', 'sqm', '1', '0', d)}" = "1" ]; then
        # Install SQM script
        install -d ${D}/usr/libexec/collectd
        install -m 0755 ${WORKDIR}/exec-scripts/sqm_collectd.sh ${D}/usr/libexec/collectd/
    fi

    rm -rf "${D}${localstatedir}"
}

CONFFILES:${PN} += "\
	${sysconfdir}/collectd.conf \
	${sysconfdir}/config/collectd \
"

FILES:${PN} = "${sysconfdir}/ /usr/"

# threshold.so load.so are also provided by gegl
# disk.so is also provided by libgphoto2-camlibs
PRIVATE_LIBS = "threshold.so load.so disk.so"

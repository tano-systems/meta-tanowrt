SUMMARY = "Utility for IPMI control"
DESCRIPTION = "This package contains a utility for interfacing with devices that support \
the Intelligent Platform Management Interface specification. IPMI is \
an open standard for machine health, inventory, and remote power control. \
\
This utility can communicate with IPMI-enabled devices through either a \
kernel driver such as OpenIPMI or over the RMCP LAN protocol defined in \
the IPMI specification. IPMIv2 adds support for encrypted LAN \
communications and remote Serial-over-LAN functionality. \
\
It provides commands for reading the Sensor Data Repository (SDR) and \
displaying sensor values, displaying the contents of the System Event \
Log (SEL), printing Field Replaceable Unit (FRU) information, reading and \
setting LAN configuration, and chassis power control. \
"
HOMEPAGE = "https://github.com/ipmitool/ipmitool"
SECTION = "kernel/userland"

PV = "1.8.19+git${SRCPV}"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=9aa91e13d644326bf281924212862184"

DEPENDS = "openssl readline ncurses"

SRC_URI = "git://github.com/ipmitool/ipmitool.git;branch=master;protocol=https"
SRCREV = "be11d948f89b10be094e28d8a0a5e8fb532c7b60"

inherit pkgconfig
inherit autotools

S = "${WORKDIR}/git"

# --disable-dependency-tracking speeds up the build
# --enable-file-security adds some security checks
# --disable-intf-free disables FreeIPMI support - we don't want to depend on
#   FreeIPMI libraries, FreeIPMI has its own ipmitoool-like utility.
# --disable-registry-download disables download/install the IANA PEN registry
#
EXTRA_OECONF = "\
	--disable-dependency-tracking \
	--enable-file-security \
	--disable-intf-free \
	--disable-registry-download \
"

#
# Open source library for IEC 61850
# http://libiec61850.com/libiec61850/
# https://github.com/mz-automation/libiec61850
#
# This file Copyright (C) 2020 Anton Kikin <a.kikin@tano-systems.com>
#

DESCRIPTION = "Open source library for IEC 61850"
HOMEPAGE = "http://libiec61850.com/libiec61850/"
SECTION = "libs"
LICENSE = "GPL-3"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504"
MAINTAINER = "Anton Kikin <a.kikin@tano-systems.com>"

PV = "1.4.2.1"
PR = "tano0"

SRC_URI = "git://github.com/mz-automation/libiec61850;protocol=https;branch=v1.4"
SRCREV = "d798814fb463115a835da597535a625b68a39cff"

S = "${WORKDIR}/git"

inherit cmake pkgconfig lib_package

PACKAGECONFIG ??= "examples goose"

# Build examples
PACKAGECONFIG[examples] = "-DBUILD_EXAMPLES=ON,-DBUILD_EXAMPLES=OFF,"

# Enable debugging (disabled by default)
PACKAGECONFIG[debug] = "-DDEBUG=ON,-DDEBUG=OFF,"

# Build with GOOSE support
PACKAGECONFIG[goose] = "-DCONFIG_INCLUDE_GOOSE_SUPPORT=ON,-DCONFIG_INCLUDE_GOOSE_SUPPORT=OFF,"

LIBIEC61850_EXAMPLES_DIR="${datadir}/libiec61850-examples"
LIBIEC61850_EXAMPLES_FILES="\
	goose_publisher/goose_publisher_example \
	goose_subscriber/goose_subscriber_example \
	iec61850_9_2_LE_example/sv_9_2_LE_example \
	iec61850_client_example_array/iec61850_client_example_array \
	iec61850_client_example_async/iec61850_client_async \
	iec61850_client_example_control/client_example_control \
	iec61850_client_example_files/file-tool \
	iec61850_client_example_log/iec61850_client_example_log \
	iec61850_client_example_reporting/iec61850_client_example_reporting \
	iec61850_client_example1/iec61850_client_example1 \
	iec61850_client_example2/iec61850_client_example2 \
	iec61850_client_example4/iec61850_client_example4 \
	iec61850_client_example5/iec61850_client_example5 \
	iec61850_client_file_async/iec61850_client_file_async \
	iec61850_sv_client_example/iec61850_sv_client_example \
	mms_utility/mms_utility \
	server_example_61400_25/server_example_61400_25 \
	server_example_basic_io/server_example_basic_io \
	server_example_complex_array/server_example_ca \
	server_example_config_file/server_example_config_file \
	server_example_config_file/model.cfg \
	server_example_control/server_example_control \
	server_example_dynamic/server_example_dynamic \
	server_example_files/server_example_files \
	server_example_goose/server_example_goose \
	server_example_password_auth/server_example_password_auth \
	server_example_setting_groups/server_example_sg \
	server_example_simple/server_example_simple \
	server_example_substitution/server_example_substitution \
	server_example_threadless/server_example_threadless \
	server_example_write_handler/server_example_write_handler \
	sv_publisher/sv_publisher_example \
	sv_subscriber/sv_subscrieber_example \
	tls_client_example/tls_client_example \
	tls_client_example/client1.cer \
	tls_client_example/root.cer \
	tls_client_example/client1-key.pem \
	tls_server_example/tls_server_example \
	tls_server_example/client1.cer \
	tls_server_example/client2.cer \
	tls_server_example/root.cer \
	tls_server_example/server.cer \
	tls_server_example/server-key.pem \
"

do_install_append() {
	if [ "${@bb.utils.contains('PACKAGECONFIG', 'examples', '1', '0', d)}" = "1" ]; then
		# Install available examples
		install -d ${D}${LIBIEC61850_EXAMPLES_DIR}

		for f in ${LIBIEC61850_EXAMPLES_FILES}; do
			DIRNAME="${LIBIEC61850_EXAMPLES_DIR}/$(dirname $f)"
			if [ -x ${B}/examples/${f} ]; then
				install -d ${D}${DIRNAME}
				install -m 0755 ${B}/examples/${f} ${D}${DIRNAME}/
			fi
			if [ -f ${S}/examples/${f} -a -d ${D}${DIRNAME} ]; then
				install -m 0644 ${S}/examples/${f} ${D}${DIRNAME}/
			fi
		done
	fi
}

PACKAGES += "${PN}-examples"
FILES_${PN}-examples = "${LIBIEC61850_EXAMPLES_DIR}"
DESCRIPTION_${PN} = "Open source library for IEC 61850 examples"

LEAD_SONAME = "libiec61850.so"
BBCLASSEXTEND = "native"

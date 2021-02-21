#
# SPDX-License-Identifier: MIT
#
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#

def boolean_string(varname, d):
    value = d.getVar(varname, True)
    if not value:
        bb.warn("Variable '%s' is not defined" % varname)

    if value == "1" or value.lower() == "true":
        return "true"
    else:
        return "false"

do_generate_swupdate_conf() {
	local destination=${1}
	{
		echo "globals: {"
		echo "	verbose = ${@boolean_string('SWU_VERBOSE', d)};"
		echo "	loglevel = ${SWU_LOGLEVEL};"
		echo "	syslog = ${@boolean_string('SWU_SYSLOG', d)};"

		if [ -n "${SWU_HOOKCMD}" ]; then
			echo "	hookcmd = \"${SWU_HOOKCMD}\";"
		fi

		if [ -n "${SWU_UBI_MTD_BLACKLIST}" ]; then
			echo "	mtd-blacklist = \"${SWU_UBI_MTD_BLACKLIST}\";"
		fi

		echo "};"

		if [ "${SWU_SURICATTA}" = "1" ]; then
			echo ""
			echo "identify: ("
			echo "	{ name = \"board\"; value = \"${SWU_BOARD}\"; },"
			echo "	{ name = \"revision\"; value = \"${SWU_REVISION}\"; }"
			echo ");"
			echo ""

			#
			# suricatta section: setup for backend
			#
			# Currently, they refer to the Hawkbit agent.
			#
			# tenant		: string
			#				  Hawkbit tenant
			# id			: string
			#				  target identifier
			# url			: string
			#				  URL of the Hawkbit server
			# polldelay		: integer
			#				  polling cycle (seconds)
			# retry			: integer
			#				  number of retries to connect to server
			# retrywait		: integer
			# userid		: integer
			#				  userID for Webserver process
			# groupid		: integer
			#				  groupId for Webserver process
			# enable		: bool
			# 				  default=true
			# 				  If set to false, suricatta do not try to connect to the server
			# 				  Enable can be done then via IPC
			# cafile		: string
			# 				  File with Public Certificate Authority
			# sslkey		: string
			#				  path of the file containing the key for ssl connection
			# sslcert		: string
			#				  path of the file containing the certificate for SSL connection
			# targettoken	: string
			#				  hawkBit target security token
			# gatewaytoken	: string
			#				  hawkBit gateway security token
			# usetokentodwl : bool
			# 				  send authentication token also to download the artefacts
			# 				  Hawkbit server checks for the token, but if a SWU is stored on a different server
			# 				  the token can be rejected if a different authentication mechanism is used. Default on.
			# proxy			: string
			#				  in case the server is reached via a proxy
			# ciphers		: string in the format used by CURL to set the allowed ciphers suite
			#				  This allows to disable some ciphers, for example
			#				  ciphers = "!eNULL:!aNULL:!EXP:!LOW:!MEDIUM:!ADH:!AECDH:!IDEA:!SEED:!MD5:!SHA:!RC4:HIGH+EECDH:HIGH+EDH";
			# interface		: string
			#				  interface name (e.g. "eth0") or IP address to bind communication channel to.
			#				  This allows to select source interface/address for outgoing traffic, if needed.

			echo "suricatta: {"
			echo "	tenant = \"${SWU_SURICATTA_TENANT}\";"
			echo "	id = \"${SWU_SURICATTA_ID}\";"
			echo "	url = \"${SWU_SURICATTA_URL}\";"
			echo "};"
		fi

	} > ${destination}
}

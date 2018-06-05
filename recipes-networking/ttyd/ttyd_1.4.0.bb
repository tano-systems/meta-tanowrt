#
# Share your terminal over the web
# ttyd is a simple command-line tool for sharing terminal over the web, inspired by GoTTY.
# https://github.com/tsl0922/ttyd
#
# This file Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
PR = "tano0"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=4a9b415801f3f426a95d1da1f527882d"

inherit cmake

DEPENDS += "openssl json-c libwebsockets vim-xxd-native"

GIT_TAG      = "${PV}"
GIT_PROTOCOL = "https"

SRC_URI = "git://github.com/tsl0922/ttyd.git;tag=${GIT_TAG};protocol=${GIT_PROTOCOL}"

S = "${WORKDIR}/git"

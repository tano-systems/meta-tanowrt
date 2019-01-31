SUMMARY = "Cross locale generation tool for glibc"
HOMEPAGE = "http://www.gnu.org/software/libc/libc.html"
SECTION = "libs"
LICENSE = "LGPL-2.1"

LIC_FILES_CHKSUM = "file://LICENSES;md5=e9a558e243b36d3209f380deb394b213 \
      file://COPYING;md5=b234ee4d69f5fce4486a80fdaf4a4263 \
      file://posix/rxspencer/COPYRIGHT;md5=dc5485bb394a13b2332ec1c785f5d83a \
      file://COPYING.LIB;md5=4fbd65380cdd255951079008b364516c"

# Tell autotools that we're working in the localedef directory
#
AUTOTOOLS_SCRIPT_PATH = "${S}/localedef"

inherit native
inherit autotools

FILESEXTRAPATHS =. "${FILE_DIRNAME}/${PN}:${FILE_DIRNAME}/glibc:${FILE_DIRNAME}/glibc-linaro-2.25:"

SRCBRANCH ?= "release/2.25/master"
GLIBC_GIT_URI ?= "git://git.linaro.org/toolchain/glibc.git"
UPSTREAM_CHECK_GITTAGREGEX = "(?P<pver>\d+\.\d+(\.\d+)*)"

SRCREV_glibc ?= "46acbd0582ce0c1b661e1b43f8e783daeea6ed9a"
SRCREV_localedef ?= "dfb4afe551c6c6e94f9cc85417bd1f582168c843"

SRC_URI = "${GLIBC_GIT_URI};branch=${SRCBRANCH};name=glibc \
           git://github.com/kraj/localedef;branch=master;name=localedef;destsuffix=git/localedef \
"
# Makes for a rather long rev (22 characters), but...
#
SRCREV_FORMAT = "glibc_localedef"

S = "${WORKDIR}/git"

EXTRA_OECONF = "--with-glibc=${S}"
CFLAGS += "-fgnu89-inline -std=gnu99 -DIS_IN\(x\)='0'"

do_install() {
	install -d ${D}${bindir}
	install -m 0755 ${B}/localedef ${D}${bindir}/cross-localedef
}

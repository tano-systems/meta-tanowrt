#
# SPDX-License-Identifier: MIT
# Copyright (c) 2020 Tano Systems LLC. All rights reserved.
#
require recipes-kernel/linux-libc-headers/linux-libc-headers.inc

PR = "tano1"

DEPENDS += "bison-native flex-native"

KERNEL_GIT_URI = "git://github.com/openil/linux.git;nobranch=1"
KERNEL_GIT_PROTOCOL = "git"
SRC_URI = "${KERNEL_GIT_URI};protocol=${KERNEL_GIT_PROTOCOL}"
SRCREV = "OpenIL-v1.8.2-linux-202009"

LIC_FILES_CHKSUM = "file://COPYING;md5=bbea815ee2795b2f4230826c0c6b8814"

S = "${WORKDIR}/git"

do_install_armmultilib() {
	# removed asm/bpf_perf_event.h
	oe_multilib_header asm/auxvec.h asm/bitsperlong.h asm/byteorder.h asm/fcntl.h asm/hwcap.h asm/ioctls.h asm/kvm.h asm/kvm_para.h asm/mman.h asm/param.h asm/perf_regs.h
	oe_multilib_header asm/posix_types.h asm/ptrace.h asm/setup.h asm/sigcontext.h asm/siginfo.h asm/signal.h asm/stat.h asm/statfs.h asm/swab.h asm/types.h asm/unistd.h
}

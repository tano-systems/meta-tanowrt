# SPDX-License-Identifier: MIT
# SPDX-FileCopyrightText: 2021 Tano Systems LLC

test -n ${loadaddr}       || setenv loadaddr 0xa0000000;
test -n ${fdtaddr}        || setenv fdtaddr 0xb0000000;
test -n ${eth2addr}       || setenv eth2addr 00:1F:7B:63:35:E9;
test -n ${ethact}         || setenv ethact FM1@DTSEC3;
test -n ${ethprime}       || setenv ethprime FM1@DTSEC3;
test -n ${fdt_high}       || setenv fdt_high 0xffffffffffffffff;
test -n ${fdtcontroladdr} || setenv fdtcontroladdr ffc01550;
test -n ${fman_ucode}     || setenv fman_ucode ffc12090;
test -n ${hwconfig}       || setenv hwconfig fsl_ddr:bank_intlv=auto;
test -n ${initrd_high}    || setenv initrd_high 0xffffffffffffffff;
test -n ${loadaddr}       || setenv loadaddr 0xa0000000;
test -n ${stderr}         || setenv stderr serial;
test -n ${stdin}          || setenv stdin serial;
test -n ${stdout}         || setenv stdout serial;

test -n ${optargs} || setenv optargs "panic=15 earlycon=uart8250,mmio,0x21c0500 ramdisk_size=0x2000000 default_hugepagesz=2m hugepagesz=2m hugepages=256 video=1920x1080-32@60 cma=256M";

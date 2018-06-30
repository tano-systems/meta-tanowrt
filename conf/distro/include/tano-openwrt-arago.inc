
BUILD_VARIANT = "${@['prod','rt','systest'][int(d.getVar('ARAGO_RT_ENABLE', True))+int(d.getVar('ARAGO_SYSTEST_ENABLE', True))*2]}"
FEEDURIPREFIX ?= "packages/${DISTRO}-morty/${BUILD_VARIANT}/"

OPKG_ARGS_remove = "--prefer-arch-to-version"

TI_MIRROR = "http://software-dl.ti.com/processor-sdk-mirror/sources/"
MIRRORS += " \
ftp://.*/.*       http://arago-project.org/files/sources/ \n \
https?$://.*/.*   http://arago-project.org/files/sources/ \n \
bzr://.*/.*      ${TI_MIRROR} \n \
cvs://.*/.*      ${TI_MIRROR} \n \
git://.*/.*      ${TI_MIRROR} \n \
gitsm://.*/.*    ${TI_MIRROR} \n \
hg://.*/.*       ${TI_MIRROR} \n \
osc://.*/.*      ${TI_MIRROR} \n \
p4://.*/.*       ${TI_MIRROR} \n \
npm://.*/.*      ${TI_MIRROR} \n \
ftp://.*/.*      ${TI_MIRROR} \n \
https?$://.*/.*  ${TI_MIRROR} \n \
svn://.*/.*      ${TI_MIRROR} \n \
"

#VIRTUAL-RUNTIME_initscripts = "${@base_conditional("ARAGO_SYSVINIT", "1", "initscripts", "systemd-compat-units", d)}"
#VIRTUAL-RUNTIME_initramfs = "${@base_conditional("ARAGO_SYSVINIT", "1", "sysvinit-initramfs", "systemd-initramfs", d)}"

ARAGO_RT_ENABLE ?= "0"
ARAGO_KERNEL_SUFFIX_append = "${@base_conditional("ARAGO_RT_ENABLE", "1", "-rt", "", d)}"

# Set systest kernel suffix
ARAGO_SYSTEST_ENABLE ?= "0"


PR_append = ".tano0"
FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}/:"

GRUB_BUILDIN = "boot linux fat squash4 ext2 serial part_msdos part_gpt normal \
                efi_gop iso9660 configfile search loadenv"

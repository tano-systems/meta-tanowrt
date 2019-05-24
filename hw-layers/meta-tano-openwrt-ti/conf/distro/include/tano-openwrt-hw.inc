#
# Dynamic includes
#
include ${@bb.utils.contains('BBFILE_COLLECTIONS', 'meta-arago-distro', 'conf/distro/include/dynamic/meta-arago-distro.inc', '__not_existsing__.inc', d)}

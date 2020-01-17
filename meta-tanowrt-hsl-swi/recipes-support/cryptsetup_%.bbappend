FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

python() {
  import re

  pv = d.getVar('PV', True)
  srcuri = d.getVar('SRC_URI', True)

  # Handle version 1.7.4
  if re.match('1.7', pv):
    d.setVar('SRC_URI', srcuri + \
             ' file://file_read_only_open_1.7.4.patch' )
  else:
    d.setVar('SRC_URI', srcuri + \
             ' file://file_read_only_open.patch' )
}

SRC_URI_append_class-native = " file://Retry-device_open-without-direct-io.patch"

#
# SPDX-License-Identifier: GPL-2.0-only
#

import logging

from wic.pluginbase import SourcePlugin

logger = logging.getLogger('wic')

class EmptyPlugin(SourcePlugin):
    """
    Populate empty partition content.
    """

    name = 'empty'

    @classmethod
    def do_prepare_partition(cls, part, source_params, cr, cr_workdir,
                             oe_builddir, bootimg_dir, kernel_dir,
                             rootfs_dir, native_sysroot):
        logger.info('Empty partition with size: %d kB', part.size)

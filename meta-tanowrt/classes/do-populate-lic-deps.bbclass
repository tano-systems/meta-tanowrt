#
# SPDX-License-Identifier: MIT
# Copyright (c) 2021 Tano Systems LLC. All rights reserved.
#

#
# When we build an image for the qemux86-64 (for example) machine,
# the do_populate_lic task populates the 'tmp/deploy/licenses/qemux86-64'
# folder with the necessary files. If we then try to build for another
# machine, for example qemuarm, the do_populate_lic task for recipes that
# are not machine dependent (allarch for example) will not be executed, and
# the required licenses will be missing in the 'tmp/deploy/licenses/qemuarm'
# folder. This, as a result, will cause warnings to be generated when
# building the rootfs image (do_rootfs task) like this:
#
# |
# | <image-recipe> do_rootfs: The license listed <license> was not \
# |     in the licenses collected for recipe <recipe>
# |
#
# By setting the do_populate_lic task to depend on the MACHINE variable,
# this issue can be solved.
#
do_populate_lic[vardeps] += "MACHINE"

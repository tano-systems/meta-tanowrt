.. SPDX-License-Identifier: MIT

============
Common Tasks
============

.. rubric:: Contents
.. contents::
   :local:


.. _sec-writing-sd-or-usb:

Writing Images to SD Card or USB Flash Drive
============================================

For many :ref:`supported boards <sec-supported-targets>` are implemented build
TanoWrt images, aimed to run from SD card or USB flash drive.
In this section you can find description of how to write these
images to SD card or USB flash drive respectively.

Using Linux
-----------

Use the :command:`dd` utility to write the binary images
to the SD card or USB flash drive. This can be an bootable
SD card or USB flash images intended to boot and run
TanoWrt or initial factory installation images.

Usually TanoWrt images intended for writing to
SD cards have the extension :file:`.sdcard.img`.
And images intended to write to USB flash drive have
the extension :file:`.wic`.

For example, writing bootable image for
:ref:`Boardcon EM3568 <machine-boardcon-em3568>` board to SD card:

.. code-block:: console

   $ sudo dd if=~/tanowrt/build/tanowrt-glibc/deploy/images/boardcon-em3568-sd/tanowrt-image-full-boardcon-em3568-sd.sdcard.img \
             of=/dev/mmcblk1 \
             bs=1k

Where :file:`/dev/mmcblk1` is the device name of the SD card.

Another example, write bootable image for
:ref:`MBM 2.0 <machine-mbm20>` board to USB flash drive:

.. code-block:: console

   $ sudo dd if=~/tanowrt/build/tanowrt-glibc/deploy/images/mbm20/tanowrt-image-full-mbm20.wic \
             of=/dev/sdc \
             bs=1k

Where :file:`/dev/sdc` is the device name of the USB flash drive.

.. tip:: Before you can disconnect the media (SD card or USB flash drive)
         from the PC, you must force immediate writing of all cached
         data to media (:command:`sync` command) or unmount it.

         Writing of all cached data to disks:

         .. code-block:: console

            $ sync

         Unmounting drive (:file:`/mnt/mmcblk1` in this example):

         .. code-block:: console

            $ sudo umount /mnt/mmcblk1

         In the case of unmounting, it is not necessary to use the
         :command:`sync` command. When unmounting, the all cached data
         are automatically flushed to the drive.


Using Windows
-------------

For writing SD card or USB flash drive under Windows
we recommend to use `PassMark imageUSB utility <https://www.osforensics.com/tools/write-usb-images.html>`__.

.. todo:: Add content


Setting Serial Console Connection to Device
===========================================

.. todo:: Add content

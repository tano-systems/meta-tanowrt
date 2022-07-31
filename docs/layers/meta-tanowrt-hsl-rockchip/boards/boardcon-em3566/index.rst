.. SPDX-License-Identifier: MIT

.. _machine-boardcon-em3566:

*******************
Boardcon EM3566 SBC
*******************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-boardcon-em3566-board:

Board Overview
==============

Boardcon EM3566 single board computer (:term:`SBC`) features 2 GiB memory
(up to 8 GiB), ﻿comes with MIPI/LVDS, HDMI and eDP video out, and offers
Gigabit Ethernet port, as well as M.2 socket which enables expansion with
a large hard drive and plus a miniPCIe, a Nano SIM card socket for
4G cellular connectivity. Display and camera capabilities with graphics
and video hardware acceleration make it suitable for machine vision
applications. And built-in independent NPU, computing power of 0.8 TOPS
for AI accelerator extend the reach of AI solutions. EM3566 supports
microphone array input, with high dynamic noise reduction algorithm,
it can accurately recognize human voices even in noisy environments,
and achieve precise voice control of the devices.

Photos
------

.. _fig-boardcon-em3566-angle:
.. figure:: images/boardcon-em3566-angle.jpeg
    :width: 500
    :class: with-border

    Boardcon EM3566 front with an angle view

.. _fig-boardcon-em3566-top:
.. figure:: images/boardcon-em3566-top.jpeg
    :width: 500
    :class: with-border

    Boardcon EM3566 top view

.. _fig-boardcon-em3566-back:
.. figure:: images/boardcon-em3566-back.jpeg
    :width: 500
    :class: with-border

    Boardcon EM3566 back view


Specification
-------------

.. table:: Boardcon EM3566 Specification

   +--------------+------------------------------------------------------------------------------+
   | Model        | Boardcon EM3566                                                              |
   +==============+==============================================================================+
   | Processor    || SoC Rockchip RK3566                                                         |
   |              || Quad Cortex-A55 ARM 64 bits processor                                       |
   |              || frequency up to 1.8 GHz                                                     |
   +--------------+------------------------------------------------------------------------------+
   | GPU          | ARM Mali-G52 2EE GPU with support for                                        |
   |              | OpenGL ES 1.1/2.0/3.2, OpenCL 2.0, Vulkan 1.1,                               |
   |              | 4Kp60 H.265/H.264/VP9 video decoder                                          |
   +--------------+------------------------------------------------------------------------------+
   | NPU          | 0.8 TOPS                                                                     |
   +--------------+------------------------------------------------------------------------------+
   | Memory       | 2 GiB, 4 GiB or 8 GiB LPDDR4                                                 |
   +--------------+------------------------------------------------------------------------------+
   | Storage      || microSD(TF)                                                                 |
   |              || 8 GiB eMMC flash (up to 128 GiB)                                            |
   |              || M.2 PCIe 2.0 socket NVMe SSD                                                |
   |              || SATA 3.0 (optional SATA 3.0 and USB 3.0 share the signal, switch with SW1)  |
   +--------------+------------------------------------------------------------------------------+
   | Wireless     || 802.11 b/g/n WiFi                                                           |
   |              || Bluetooth 4.2                                                               |
   |              || External antenna                                                            |
   +--------------+------------------------------------------------------------------------------+
   | Connectivity | PCIe socket with Nano SIM card port to support 4G/GPS module                 |
   +--------------+------------------------------------------------------------------------------+
   | USB          || 1 |times| USB OTG 2.0                                                       |
   |              || 4 |times| USB Host 2.0 (USB-AF or 4-pin connector)                          |
   |              || 1 |times| USB 3.0                                                           |
   +--------------+------------------------------------------------------------------------------+
   | Ethernet     || 1 |times| Gigabit Ethernet RJ45 ports                                       |
   |              || (|RTL8211F-CG|)                                                             |
   +--------------+------------------------------------------------------------------------------+
   | Serial       || 1 |times| Serial port for debug (3-pin connector)                           |
   |              || 2 |times| UART (4-pin connectors)                                           |
   |              || 1 |times| RS485 (2-pin header or 3-pin connector)                           |
   +--------------+------------------------------------------------------------------------------+
   | Video        || HDMI 2.0, 4Kp60                                                             |
   |              || MIPI DSI/LVDS, 1080p60 (40-pin header)                                      |
   |              || eDP 1.3, 2560x1600\@60Hz (30-pin header)                                    |
   +--------------+------------------------------------------------------------------------------+
   | Audio        || 3.5 mm audio I/O jack, ES8388 audio codec                                   |
   |              || 8-channel audio via HDMI                                                    |
   +--------------+------------------------------------------------------------------------------+
   | Camera       | 2 |times| Cameras via MIPI CSI (24-pin FPC connector)                        |
   | (optional)   |                                                                              |
   +--------------+------------------------------------------------------------------------------+
   | Keys         || 1 |times| Recovery                                                          |
   |              || 1 |times| Switch for switch between SATA 3.0 and USB 3.0                    |
   +--------------+------------------------------------------------------------------------------+
   | Other        || RTC with battery connector                                                  |
   | Features     || GPIO                                                                        |
   |              || IR Receiver                                                                 |
   |              || ADC                                                                         |
   +--------------+------------------------------------------------------------------------------+
   | Power        | 12V/3A DC input jack                                                         |
   +--------------+------------------------------------------------------------------------------+
   | Dimensions   || Base board: 135 mm |times| 95 mm                                            |
   |              || CPU module: 40 mm |times| 47 mm                                             |
   +--------------+------------------------------------------------------------------------------+

.. |RTL8211F-CG| replace:: :download:`Realtek RTL8211F-CG controller <http://www.armdesigner.com/download/RTL8211F-CG-Realtek.pdf>`


.. _sec-boardcon-em3566-targets:

Build Targets
=============

.. _sec-boardcon-em3566-machines:

Machines
--------

.. _table-boardcon-em3566-machines:
.. table:: Supported Machines

   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_     | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +=================+==============================+==========================+====================================+========================+==========================+
   | Boardcon EM3566 | ``boardcon-em3566-sd.yml``   | ``boardcon-em3566-sd``   | ``tanowrt-image-full-swu``         | microSD card           | |ndash|                  |
   |                 +------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   |                 | ``boardcon-em3566-emmc.yml`` | ``boardcon-em3566-emmc`` | ``tanowrt-image-full-swu-factory`` | internal eMMC          | microSD card             |
   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-boardcon-em3566-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-boardcon-em3566-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-boardcon-em3566-images:

Images
------

.. _table-boardcon-em3566-images:
.. table:: Supported Images
   :widths: 15, 15, 15, 55

   +---------------------------+------------------------------------+------------------------------+-----------------------------------------------------+
   | Read-Only Root Filesystem | Recipe\ [#]_                       | Supported by Target(s)       | Description                                         |
   | Image                     |                                    |                              |                                                     |
   +===========================+====================================+==============================+=====================================================+
   | ``tanowrt-image-full``    | ``tanowrt-image-full``             | *All*                        | Standard TanoWrt image.                             |
   |                           +------------------------------------+------------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu``         | *All*                        | Standard TanoWrt image                              |
   |                           |                                    |                              | and :ref:`firmware upgrade <sec-firmware-upgrade>`  |
   |                           |                                    |                              | image. When building this image,                    |
   |                           |                                    |                              | ``tanowrt-image-full`` will also be built           |
   |                           |                                    |                              | as dependency.                                      |
   |                           +------------------------------------+------------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu-factory`` | ``boardcon-em3566-emmc.yml`` | Factory installation image for standard TanoWrt     |
   |                           |                                    |                              | image. When building this image,                    |
   |                           |                                    |                              | ``tanowrt-image-full``                              |
   |                           |                                    |                              | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                              | as dependencies.                                    |
   +---------------------------+------------------------------------+------------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-boardcon-em3566-build` section).


.. _sec-boardcon-em3566-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-boardcon-em3566-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-boardcon-em3566-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-boardcon-em3566-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for Boardcon EM3566 Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For microSD Card

.. code-block:: console

   $ kas build targets/kas/boardcon-em3566-sd.yml

Default images will be produced to boot and run from the microSD
card on the Boardcon EM3566 target board.

.. rubric:: For Internal eMMC Flash

.. code-block:: console

   $ kas build targets/kas/boardcon-em3566-emmc.yml

An initial factory installation image will be generated,
intended to run from the microSD card. The installer image
will install the default image to the internal eMMC flash
memory and further the Boardcon EM3566 board will boot
and run from the eMMC flash memory.


.. _sec-boardcon-em3566-partitioning:

Partitioning Layouts
====================

Images for Boardcon EM3566 board using default partitions layouts described :ref:`here <sec-hsl-rockchip-partitioning>`.


.. _sec-boardcon-em3566-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-boardcon-em3566-artifacts` for a description of some common (not all) build artifacts.

.. _table-boardcon-em3566-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | Artifact                                                | Target(s)                     | Description                                                   |
   +=========================================================+===============================+===============================================================+
   | .. centered:: Bootloader                                                                                                                                |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`idblock.img-<MACHINE>-sdcard`                    | *All*                         | Rockchip IDBLOCK image for booting from SD card.              |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`idblock.img-<MACHINE>-emmc`                      | ``boardcon-em3566-emmc.yml``  | Rockchip IDBLOCK image for booting from internal eMMC.        |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | .. centered:: Bootloader (U-Boot)                                                                                                                       |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`startup-<MACHINE>.img`                           | *All*                         | U-Boot startup script.                                        |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`startup-factory-<MACHINE>.img`                   | ``boardcon-em3566-emmc.yml``  | U-Boot startup script for factory installation image.         |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdcard`             | *All*                         | U-Boot initial environment image for SD card image.           |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-emmc`               | ``boardcon-em3566-emmc.yml``  | U-Boot initial environment image for internal eMMC flash.     |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdcard`                     | *All*                         | U-Boot binary image for booting from SD card.                 |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-emmc`                       | ``boardcon-em3566-emmc.yml``  | U-Boot binary image for booting from internal eMMC flash.     |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                      |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.bin`                          | *All*                         | Flattened Image Tree (FIT) image with Linux kernel            |
   |                                                         |                               | and Device Tree Blobs (DTB).                                  |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.ext4`                         | *All*                         | FIT image packed into an ext4 file system image.              |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`boardcon-em3566-<MACHINE>.dtb`                   | *All*                         | Target Device Tree Blob (DTB).                                |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                    |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`             | ``boardcon-em3566-sd.yml``    | SD card image including all required partitions for booting   |
   |                                                         |                               | and running the system. This image is ready to be written     |
   |                                                         |                               | to the SD card using the :command:`dd` utility or similar     |
   |                                                         |                               | (see :ref:`sec-boardcon-em3566-flash`).                       |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img` | ``boardcon-em3566-emmc.yml``  | SD card factory installation image. This image is ready       |
   |                                                         |                               | to be written to the SD card using the :command:`dd` utility  |
   |                                                         |                               | or similar (see :ref:`sec-boardcon-em3566-flash`).            |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`           | *All*                         | Root filesystem image (squashfs with LZO compression).        |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                | *All*                         | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.         |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-boardcon-em3566-targets_>`__. ``<rootfs-image>`` is replaced
          by the actual read-only root filesystem `image <sec-boardcon-em3566-images_>`__ name.

For example, below is the complete list of artifacts produced by the
``boardcon-em3566-sd.yml`` target build.

.. code-block:: console

   [~/tanowrt/build/tanowrt-glibc/deploy/images/boardcon-em3566-sd]$ ls -gGh
   total 311M
   -rw-r--r-- 2 105K Jul 20 06:00 boardcon-em3566--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.dtb
   lrwxrwxrwx 2   97 Jul 20 06:00 boardcon-em3566-boardcon-em3566-sd.dtb -> boardcon-em3566--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.dtb
   lrwxrwxrwx 2   97 Jul 20 06:00 boardcon-em3566.dtb -> boardcon-em3566--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.dtb
   lrwxrwxrwx 2   90 Jul 20 06:00 fitImage -> fitImage--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.bin
   -rw-r--r-- 2 9.0M Jul 20 06:00 fitImage--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.bin
   -rw-r--r-- 2  12M Jul 20 06:00 fitImage-4.19.219+gitAUTOINC+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd.ext4
   lrwxrwxrwx 2   90 Jul 20 06:00 fitImage-boardcon-em3566-sd.bin -> fitImage--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.bin
   -rw-r--r-- 2  12M Jul 20 06:00 fitImage-boardcon-em3566-sd.ext4
   -rw-r--r-- 2 1.6K Jul 20 06:00 fitImage-its--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.its
   lrwxrwxrwx 2   94 Jul 20 06:00 fitImage-its-boardcon-em3566-sd -> fitImage-its--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.its
   -rw-r--r-- 2 8.9M Jul 20 06:00 fitImage-linux.bin--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.bin
   lrwxrwxrwx 2  100 Jul 20 06:00 fitImage-linux.bin-boardcon-em3566-sd -> fitImage-linux.bin--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.bin
   lrwxrwxrwx 2   78 Jul 20 05:56 idblock.img-boardcon-em3566-sd-sdcard -> idblock.img-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   -rwxr-xr-x 2 294K Jul 20 05:56 idblock.img-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   lrwxrwxrwx 2   77 Jul 20 05:56 loader.bin-boardcon-em3566-sd-sdcard -> loader.bin-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   -rwxr-xr-x 2 453K Jul 20 05:56 loader.bin-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   -rw-r--r-- 2  50M Jul 20 06:00 modules--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.tgz
   lrwxrwxrwx 2   89 Jul 20 06:00 modules-boardcon-em3566-sd.tgz -> modules--4.19.219+git0+40b753b965-tano2.2.20.20.1.8-boardcon-em3566-sd-20220720025543.tgz
   lrwxrwxrwx 2   11 Jun  8 03:04 startup-boardcon-em3566-sd.img -> startup.img
   lrwxrwxrwx 2   19 Jun  8 03:04 startup-boardcon-em3566-sd.img.version -> startup.img.version
   -rw-r--r-- 2 2.2K Jun  8 03:04 startup.img
   -rw-r--r-- 2   16 Jun  8 03:04 startup.img.version
   -rw-r--r-- 2  55K Jul 20 10:44 tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.manifest
   -rw-r--r-- 2 945M Jul 20 10:45 tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.sdcard.img
   -rw-r--r-- 2  47M Jul 20 10:44 tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.squashfs-lzo
   -rw-r--r-- 2   24 Jul 20 10:45 tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.version
   -rw-r--r-- 2 393K Jul 20 10:44 tanowrt-image-full-boardcon-em3566-sd-20220720074256.testdata.json
   lrwxrwxrwx 2   68 Jul 20 10:44 tanowrt-image-full-boardcon-em3566-sd.manifest -> tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.manifest
   lrwxrwxrwx 2   70 Jul 20 10:45 tanowrt-image-full-boardcon-em3566-sd.sdcard.img -> tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.sdcard.img
   lrwxrwxrwx 2   72 Jul 20 10:44 tanowrt-image-full-boardcon-em3566-sd.squashfs-lzo -> tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.squashfs-lzo
   lrwxrwxrwx 2   66 Jul 20 10:44 tanowrt-image-full-boardcon-em3566-sd.testdata.json -> tanowrt-image-full-boardcon-em3566-sd-20220720074256.testdata.json
   lrwxrwxrwx 2   67 Jul 20 10:45 tanowrt-image-full-boardcon-em3566-sd.version -> tanowrt-image-full-boardcon-em3566-sd-20220720074256.rootfs.version
   -rw-r--r-- 2 5.3K Jul 20 10:44 tanowrt-image-full.env
   -rw-r--r-- 2 2.3K Jul 20 10:44 tanowrt-image-full-sdimage-rockchip-swu-a-b.wks
   -rw-r--r-- 2  61M Jul 20 10:45 tanowrt-image-full-swu-boardcon-em3566-sd-20220720074256.swu
   lrwxrwxrwx 2   60 Jul 20 10:45 tanowrt-image-full-swu-boardcon-em3566-sd.swu -> tanowrt-image-full-swu-boardcon-em3566-sd-20220720074256.swu
   lrwxrwxrwx 2   64 Jul 20 05:56 u-boot-boardcon-em3566-sd.bin -> u-boot-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8.bin
   lrwxrwxrwx 2   64 Jul 20 05:56 u-boot-boardcon-em3566-sd.bin-sdcard -> u-boot-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8.bin
   lrwxrwxrwx 2   91 Jul 20 05:56 u-boot-initial-env-boardcon-em3566-sd-sdcard -> u-boot-initial-env-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8
   -rw-r--r-- 2  468 Jul 20 05:56 u-boot-initial-env-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8
   -rw-r--r-- 2  32K Jul 20 05:56 u-boot-initial-env-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8.bin
   lrwxrwxrwx 2   95 Jul 20 05:56 u-boot-initial-env-boardcon-em3566-sd-sdcard.bin -> u-boot-initial-env-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8.bin
   lrwxrwxrwx 2   91 Jul 20 05:56 u-boot-initial-env-sdcard -> u-boot-initial-env-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8
   lrwxrwxrwx 2   95 Jul 20 05:56 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-boardcon-em3566-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8.bin
   -rw-r--r-- 2 2.0M Jul 20 05:56 u-boot-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano8.bin


.. _sec-boardcon-em3566-flash:

Writing Images
==============


.. _sec-boardcon-em3566-flash-sd:

Writing Image to microSD Card
-----------------------------

No special information about writing images to microSD card
for Boardcon EM3566 board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing factory installation image for the ``boardcon-em3566-emmc.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-swu-factory-boardcon-em3566-emmc.sdcard.img of=/dev/mmcblk1

Writing bootable image for the ``boardcon-em3566-sd.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-boardcon-em3566-sd.sdcard.img of=/dev/mmcblk1


.. _sec-boardcon-em3566-flash-emmc:

Writing Image to eMMC Flash
---------------------------

For the initial flashing of the internal eMMC memory it is recommended to use
the special image of the initial factory installation. If you choose a build target
(see :ref:`sec-boardcon-em3566-targets` for details) that assumes using the
factory installation image for the initial flashing of the
device, a factory installation image (:file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`)
will be automatically generated during the build process
(see :ref:`sec-boardcon-em3566-build`).
To write the factory installation image to a SD card, follow the instructions
from :ref:`sec-writing-sd-or-usb` section.

When you boot device from the prepaired SD card with factory installation image the installation
of TanoWrt to the internal eMMC flash memory will be done automatically. The detailed
installation log is available on the :ref:`debug UART <sec-boardcon-em3566-serial>`. After the installation is complete,
the board will reboots automatically. When the device is rebooted, the installed system
will be booted from the internal eMMC flash memory.

.. caution:: Be aware that during the installation all existing data on the internal eMMC
             flash memory will be permanently lost.


.. _sec-boardcon-em3566-booting:

Booting and Running
===================

Booting from SD Card
--------------------

1. Insert the micro SD card to the board.
2. Connect the power cable to the DC input connector.
3. Boardcon EM3566 will boot.
4. (Optional) Use a USB to TTL serial cable to make a connection between
   your PC and Boardcon EM3566. See :ref:`sec-boardcon-em3566-serial` section for details.
5. Log in to system using default :ref:`credentials <sec-access-creds>`.

.. attention:: The internal eMMC flash memory must be empty in order to perform
               a boot from the SD card. See :ref:`sec-boardcon-em3566-emmc-erase`
               section for details about erasing internal eMMC flash.


Booting from Internal eMMC Flash
-----------------------------------

1. Connect the power cable to the DC input connector.
2. Boardcon EM3566 will boot.
3. (Optional) Use a USB to TTL serial cable to make a connection between
   your PC and Boardcon EM3566. See :ref:`sec-boardcon-em3566-serial` section for details.
4. Log in to system using default :ref:`credentials <sec-access-creds>`.


.. _sec-boardcon-em3566-emmc-erase:

Erasing Internal eMMC Flash
------------------------------

You can erase the internal eMMC flash memory in the following ways:

- Linux command line;
- U-Boot command line;
- special utilities and USB connection with PC.

.. tabs::

   .. tab:: Linux CLI

      .. rubric:: Erasing eMMC Using Linux Command Line

      If the device has a bootable Linux system, you can erase the eMMC
      by the following command entered in the Linux terminal:

      .. code-block:: console

         [root@tanowrt ~]# dd if=/dev/zero of=/dev/mmcblk0 bs=1k count=32768

      Note that if the Linux distribution flashed on the eMMC flash memory
      is not TanoWrt, the device name of the eMMC flash memory may be different.
      You can usually find out the real device name by analysing the
      system boot log by running the command dmesg. For example:

      .. code-block:: console
         :emphasize-lines: 4,5,6,7

         [root@tanowrt ~]# dmesg | grep mmcblk
         [    2.861965] mmcblk1: mmc1:aaaa SL16G 14.8 GiB
         [    2.871858]  mmcblk1: p1 p2 p3 p4 p5 p6
         [    2.890560] mmcblk0: mmc0:0001 8GTF4R 7.28 GiB
         [    2.891462] mmcblk0boot0: mmc0:0001 8GTF4R partition 1 4.00 MiB
         [    2.892322] mmcblk0boot1: mmc0:0001 8GTF4R partition 2 4.00 MiB
         [    2.892741] mmcblk0rpmb: mmc0:0001 8GTF4R partition 3 512 KiB, chardev (238:0)
         [    7.724257] overlay-resize: Root device mmcblk1
         [    7.820211] overlay-resize: Overlay partition /dev/mmcblk1p6 (disk /dev/mmcblk1)
         [    8.747050] overlay-resize: Partition /dev/mmcblk1p6 already resized
         [    8.747189] overlay-resize: Resizing filesystem on partition /dev/mmcblk1p6...
         [    8.786542] SWUPDATE: Failed to extract the idblock version from '/dev/mmcblk1'
         [    8.788867] SWUPDATE: Failed to extract the U-Boot version from '/dev/mmcblk1p1'
         [    8.791161] SWUPDATE: Failed to extract the U-Boot startup version from '/dev/mmcblk1'
         [    8.855794] mount_root: /dev/mmcblk1p1: p1, rw, start 8388608, size 8388608
         [    8.860902] mount_root: /dev/mmcblk1p2: p2, rw, start 16777216, size 67108864
         [    8.861109] mount_root: /dev/mmcblk1p3: p3, rw, start 83886080, size 402653184 [rootfs]
         [    8.865791] mount_root: /dev/mmcblk1p4: p4, rw, start 486539264, size 67108864
         [    8.867224] mount_root: /dev/mmcblk1p5: p5, rw, start 553648128, size 402653184
         [    8.871402] mount_root: /dev/mmcblk1p6: p6, rw, start 956301312, size 14975221248 [overlay]
         [    8.871508] mount_root: root filesystem on the /dev/mmcblk1p3 partition of /dev/mmcblk1 (rw) device
         [    8.887955] mount_root: founded suitable overlay partition /dev/mmcblk1p6
         [    9.057146] EXT4-fs (mmcblk1p6): mounted filesystem with ordered data mode. Opts: (null)
         [    9.170150] EXT4-fs (mmcblk1p6): mounted filesystem with ordered data mode. Opts: (null)

      This output shows that device :file:`/dev/mmcblk0` is eMMC flash
      memory and device :file:`/dev/mmcblk1` is SD card.

   .. tab:: U-Boot CLI

      .. rubric:: Erasing eMMC Using U-Boot Command Line

      If the device has a functional U-Boot bootloader, you can erase
      eMMC with the following commands entered at the U-Boot command line:

      .. code-block:: console

         => mmc dev 0
         switch to partitions #0, OK
         mmc0 is current device
         => mmc erase 0 10000

         MMC erase: dev # 0, block # 0, count 65536 ... 65536 blocks erased: OK
         =>

      Please note that if the U-Boot bootloader on the eMMC flash memory
      is different from the one built as part of the TanoWrt distribution,
      the device number of the eMMC flash memory may be different from
      the one shown above. Use the :command:`mmc dev`, :command:`mmc list` and :command:`mmc info`
      commands to identify the valid eMMC flash memory device number:

      .. code-block:: console
         :emphasize-lines: 3,5,8

         => mmc dev 0
         switch to partitions #0, OK
         mmc0(part 0) is current device
         => mmc info
         Device: sdhci@fe310000
         Manufacturer ID: 15
         OEM: 100
         Name: 8GTF4
         Timing Interface: HS200
         Tran Speed: 200000000
         Rd Block Len: 512
         MMC version 5.1
         High Capacity: Yes
         Capacity: 7.3 GiB
         Bus Width: 8-bit
         Erase Group Size: 512 KiB
         HC WP Group Size: 8 MiB
         User Capacity: 7.3 GiB WRREL
         Boot Capacity: 4 MiB ENH
         RPMB Capacity: 512 KiB ENH
         =>

   .. tab:: USB Connection with PC

      .. rubric:: Erasing eMMC Using Special Utilities and USB Connection with PC

      If the device fails to boot, e.g. due to a corrupted boot loader,
      the only way to erase the eMMC flash memory is to use a USB
      connection with a PC and use special utilities.

      .. todo:: Add content


.. _sec-boardcon-em3566-serial:

Serial Console
==============

.. note:: The default baudrate of Boardcon EM3566 with original firmware is 1500000 (1.5 Mbps),
          please check if your USB to TTL cable support 1.5 Mbps baudrate. Some model
          of CP210X and PL2303x have baudrate limitation, please check the specified model.

.. rubric:: Connection

Connect the USB to TTL serial cable as described below. Don't connect the VCC wire,
connect only TX, RX and GND wires.

.. attention:: The original firmware for the debug output uses UART2
               (3-pin connector J10) at 1500000 baudrate. In contrast,
               TanoWrt firmware by default uses UART4 (4-pin connector)
               for debug output.

+--------+-------------------------------------------+
| Signal | Boardcon EM3566 Board                     |
|        +---------------------+---------------------+
|        | J10 3-pin connector | J11 4-pin connector |
|        | (UART2)             | (UART4)             |
+========+=====================+=====================+
| RX     | Pin 1 (UART2DBG_RX) | Pin 3 (UART4_RX_M0) |
+--------+---------------------+---------------------+
| TX     | Pin 2 (UART2DBG_TX) | Pin 2 (UART4_TX_M0) |
+--------+---------------------+---------------------+
| GND    | Pin 3 (GND)         | Pin 1 (GND)         |
+--------+---------------------+---------------------+
|        | *Used by original   | *Used by TanoWrt*   |
|        | firmware*           |                     |
+--------+---------------------+---------------------+

See :ref:`fig-boardcon-em3566-usb-to-ttl` figure for example
connection USB to TTL converter with Boardcon EM3566.

.. _fig-boardcon-em3566-usb-to-ttl:
.. figure:: images/boardcon-em3566-usb-to-ttl.jpeg
    :class: with-border
    :width: 600px

    USB to TTL Converter Connection to Boardcon EM3566


.. rubric:: Serial Setting on Host PC

The default serial console settings for Boardcon EM3566 for U-Boot and kernel are
described in table below.

+-----------------+-------------------+
| Parameter       | Value             |
+=================+===================+
| Baudrate        | 1500000           |
+-----------------+-------------------+
| Data bits       | 8                 |
+-----------------+-------------------+
| Stop bits       | 1                 |
+-----------------+-------------------+
| Parity          | none              |
+-----------------+-------------------+
| Flow control    | none              |
+-----------------+-------------------+


.. _sec-boardcon-em3566-network-config:

Default Network Configuration
=============================

By default Ethernet port (``eth0`` interface) are joined into a bridge (``br-lan`` interface).
Bridge (``br-lan``) configured to obtain IP configuration via :term:`DHCP` client.
To see obtained IP configuration use the following command:

.. code-block:: console

   [root@tanowrt ~]# ifstatus lan | jsonfilter -e '@["ipv4-address"][0].address'
   192.168.0.39

In this example, the device got the IP address 192.168.0.39 via :term:`DHCP`.

Also you can connect to the board using USB network connection (``usb0`` interface).
USB network interface configured with static IP address 192.168.128.1 with enabled
:term:`DHCP` server with pool with single IP address for client (your PC) 192.168.128.100.

WiFi module and 3G/GPRS modules currently are not supported in TanoWrt.


.. _sec-boardcon-em3566-webui:

Web User Interface
==================

The WebUI can be accessed via Ethernet port or USB network connection through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-boardcon-em3566-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-boardcon-em3566-luci-status:
.. figure:: images/boardcon-em3566-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-boardcon-em3566-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-boardcon-em3566-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


Additional Information
======================

Here are sections with various additional information about the
Boardcon EM3566  board and the operation of TanoWrt on it.

.. toctree::

   bootlog-u-boot.rst
   bootlog-kernel.rst
   factory-installation-to-emmc.rst


References
==========

1. http://www.armdesigner.com/EM3566_SBC/

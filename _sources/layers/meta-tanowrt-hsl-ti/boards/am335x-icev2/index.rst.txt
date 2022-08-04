.. SPDX-License-Identifier: MIT

.. _machine-am335x-icev2:

******************************
AM3359 ICEv2 EVM (TMDSICE3359)
******************************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-am335x-icev2-board:

Board Overview
==============

The AM3359 Industrial Communications Engine (ICE) is a development platform targeted
for systems that specifically focus on the industrial communications capabilities
of the Sitara AM335x Arm® Cortex™-A8 processors.

The AM335x Arm Cortex-A8 processors integrate the Programmable Real-time Unit (PRU)
that has been architected to implement the real-time communication technologies used
in a broad range of industrial automation equipment. It enables low foot print
designs with minimal external components and with best in class low power performance.

.. _fig-am335x-icev2:
.. figure:: images/am335x-icev2.jpg
   :width: 1000

   AM3359 ICEv2 EVM Interfaces and Connectors


Photos
------

.. container:: flex

   .. _fig-am335x-icev2-angled:
   .. figure:: images/am335x-icev2-angled.jpg
      :width: 300
      :class: with-border

      AM3359 ICEv2 EVM Angled View

   .. _fig-am335x-icev2-top:
   .. figure:: images/am335x-icev2-top.jpg
      :width: 300
      :class: with-border

      AM3359 ICEv2 EVM Top View

   .. _fig-am335x-icev2-bottom:
   .. figure:: images/am335x-icev2-bottom.jpg
      :width: 300
      :class: with-border

      AM3359 ICEv2 EVM Bottom View

   .. _fig-am335x-icev2-box:
   .. figure:: images/am335x-icev2-box.png
      :width: 300
      :class: with-border

      AM3359 ICEv2 EVM Box Contents



Specification
-------------

.. table:: AM3359 ICEv2 EVM Specification

   +--------------+------------------------------------------------------------------------------+
   | Model        | AM3359 ICEv2 EVM                                                             |
   +==============+==============================================================================+
   | Processor    || Texas Instruments AM3359 (Sitara AM3359ZCZ)                                 |
   |              || 1 |times| ARM Cortex-A8                                                     |
   |              |  frequency up to 1.0 GHz                                                     |
   |              || NEON floating-point accelerator                                             |
   +--------------+------------------------------------------------------------------------------+
   | PMIC         || TPS65910A3 power management IC                                              |
   +--------------+------------------------------------------------------------------------------+
   | Memory       || 256 MiB DDR3 SDRAM (128M |times| 16)                                        |
   +--------------+------------------------------------------------------------------------------+
   | Storage      || microSD card                                                                |
   |              || 64 Mbit (8 MiB) SPI Flash (W25Q64)                                          |
   |              || 16 Mbit (2 MiB) NOR Flash (M29W160EB)                                       |
   |              || 256 KiB EEPROM (CAT24C256WI-GT3)                                            |
   +--------------+------------------------------------------------------------------------------+
   | Debug        || Optional Onboard 20-pin CTI JTAG, Serial Header                             |
   |              || Embedded XDS100V2 USB emulation through the Micro USB AB connector          |
   +--------------+------------------------------------------------------------------------------+
   | USB          || 1 |times| Micro USB AB (USB to JTAG and USB to UART)                        |
   +--------------+------------------------------------------------------------------------------+
   | Ethernet     || 2 |times| 10/100 Ethernet transceivers (TLK110)                             |
   |              || (connected to gigabit switch or PRU-ICSS units through muxing)              |
   +--------------+------------------------------------------------------------------------------+
   | CAN/Profibus || 1 |times| DB9 female connector                                              |
   +--------------+------------------------------------------------------------------------------+
   | Expansion    || 24V Power                                                                   |
   | Connectors   || 5V Power                                                                    |
   |              || 8 |times| Industrial 24V digital inputs                                     |
   |              || 8 |times| Industrial 24V digital outputs                                    |
   |              || 6 |times| Analog inputs                                                     |
   |              || 3 |times| GPIO                                                              |
   |              || 2 |times| Latch inputs                                                      |
   |              || 1 |times| LCD connector                                                     |
   |              || 1 |times| SPI                                                               |
   |              || 1 |times| I2C                                                               |
   |              || 1 |times| JTAG EMU4                                                         |
   |              || 1 |times| UART                                                              |
   |              || 1 |times| GPMC                                                              |
   +--------------+------------------------------------------------------------------------------+
   | Power Source | DC Jack                                                                      |
   +--------------+------------------------------------------------------------------------------+
   | Power        | 24V/0.75A DC input jack                                                      |
   +--------------+------------------------------------------------------------------------------+


.. _sec-am335x-icev2-targets:

Build Targets
=============

.. _sec-am335x-icev2-machines:

Machines
--------

.. _table-am335x-icev2-machines:
.. table:: Supported Machines

   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_      | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +==================+==============================+==========================+====================================+========================+==========================+
   | AM3359 ICEv2 EVM | ``am335x-icev2-sd.yml``      | ``am335x-icev2-sd``      | ``tanowrt-image-full-swu``         | microSD card           | |ndash|                  |
   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-am335x-icev2-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-am335x-icev2-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-am335x-icev2-images:

Images
------

.. _table-am335x-icev2-images:
.. table:: Supported Images
   :widths: 15, 15, 15, 55

   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+
   | Read-Only Root Filesystem | Recipe\ [#]_                       | Supported by Target(s)     | Description                                         |
   | Image                     |                                    |                            |                                                     |
   +===========================+====================================+============================+=====================================================+
   | ``tanowrt-image-full``    | ``tanowrt-image-full``             | *All*                      | Standard TanoWrt image.                             |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu``         | *All*                      | Standard TanoWrt image                              |
   |                           |                                    |                            | and :ref:`firmware upgrade <sec-firmware-upgrade>`  |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-full`` will also be built           |
   |                           |                                    |                            | as dependency.                                      |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-am335x-icev2-build` section).


.. _sec-am335x-icev2-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-am335x-icev2-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-am335x-icev2-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-am335x-icev2-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for AM3359 ICEv2 EVM Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For microSD Card

.. code-block:: console

   $ kas build targets/kas/am335x-icev2-sd.yml

Default images will be produced to boot and run from the microSD
card on the AM3359 ICEv2 EVM target board.


.. _sec-am335x-icev2-partitioning:

Partitioning Layouts
====================

microSD Card
------------

The partitioning and data layout of the microSD card image for the AM3359 ICEv2 EVM board
are shown in the figure below.

.. _fig-am335x-icev2-layout-sd:
.. figure:: images/am335x-icev2-layout-sd.svg
   :width: 1000

   AM3359 ICEv2 EVM Partitions Layout for microSD Card


.. _sec-am335x-icev2-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-am335x-icev2-artifacts` for a description of some common (not all) build artifacts.

.. _table-am335x-icev2-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | Artifact                                                            | Target(s)                     | Description                                                          |
   +=====================================================================+===============================+======================================================================+
   | .. centered:: Bootloader (SPL)                                                                                                                                             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`MLO-sdcard-defconfig`                                        | *All*                         | MLO binary blob for microSD card images.                             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Bootloader (U-Boot)                                                                                                                                          |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`startup-<MACHINE>.img`                                       | *All*                         | U-Boot startup script.                                               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdcard-defconfig`               | *All*                         | U-Boot initial environment image for microSD card image.             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdcard-defconfig`                       | *All*                         | U-Boot binary image for booting from microSD card.                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.bin`                                      | *All*                         | Flattened Image Tree (FIT) image with Linux kernel                   |
   |                                                                     |                               | and Device Tree Blobs (DTB).                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.ext4`                                     | *All*                         | FIT image packed into an ext4 file system image.                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`am335x-icev2.dtb`                                            | *All*                         | Target Device Tree Blob (DTB) for CPSW                               |
   |                                                                     |                               | :ref:`ethernet ports mode <sec-am335x-icev2-ethernet-mode>`.         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`am335x-icev2-prueth.dtb`                                     | *All*                         | Target Device Tree Blob (DTB) for PRU-ICSS                           |
   |                                                                     |                               | :ref:`ethernet ports mode <sec-am335x-icev2-ethernet-mode>`.         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`                         | *All*                         | SD card image including all required partitions for booting          |
   |                                                                     |                               | and running the system. This image is ready to be written            |
   |                                                                     |                               | to the SD card using the :command:`dd` utility or similar            |
   |                                                                     |                               | (see :ref:`sec-am335x-icev2-flash`).                                 |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`                       | *All*                         | Root filesystem image (squashfs with LZO compression).               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                            | *All*                         | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-am335x-icev2-targets_>`__. ``<rootfs-image>`` is replaced
          by the actual read-only root filesystem `image <sec-am335x-icev2-images_>`__ name.

For example, below is the lists of artifacts produced by the ``am335x-icev2-sd.yml``
target build. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/am335x-icev2-sd]$ ls -gGh | grep -v -e "^l"
         total 154M
         -rw-r--r-- 2  61K Jul 25 14:07 am335x-icev2--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         -rw-r--r-- 2  62K Jul 25 14:07 am335x-icev2-prueth--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         -rw-r--r-- 2 4.5M Jul 25 14:07 fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.bin
         -rw-r--r-- 2 6.4M Jul 25 14:07 fitImage-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd.ext4
         -rw-r--r-- 2 6.4M Jul 25 14:07 fitImage-am335x-icev2-sd.ext4
         -rw-r--r-- 2 2.4K Jul 25 14:07 fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.its
         -rw-r--r-- 2 4.4M Jul 25 14:07 fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.bin
         -rw-r--r-- 2  42K Jul 25 14:04 MLO-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 384K Jul 25 14:04 MLO-am335x-icev2-sd-sdcard-defconfig-x3
         -rw-r--r-- 2 4.8M Jul 25 14:07 modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.tgz
         -rw-r--r-- 2 2.5K Jul 25 14:04 startup.img
         -rw-r--r-- 2   16 Jul 25 14:04 startup.img.version
         -rw-r--r-- 2  91K Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.manifest
         -rw-r--r-- 2 932M Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.sdcard.img
         -rw-r--r-- 2  28M Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.version
         -rw-r--r-- 2 357K Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.testdata.json
         -rw-r--r-- 2 5.5K Jul 25 14:09 tanowrt-image-full.env
         -rw-r--r-- 2 3.1K Jul 25 14:09 tanowrt-image-full-sdimage-ti-swu-a-b.wks
         -rw-r--r-- 2  35M Jul 25 14:09 tanowrt-image-full-swu-am335x-icev2-sd-20220725110331.swu
         -rw-r--r-- 2  411 Jul 25 14:04 u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 14:04 u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         -rw-r--r-- 2 675K Jul 25 14:04 u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         -rwxr-xr-x 2  41K Jul 25 14:04 u-boot-spl.bin-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/am335x-icev2-sd]$ ls -gGh
         total 154M
         -rw-r--r-- 2  61K Jul 25 14:07 am335x-icev2--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         lrwxrwxrwx 2   91 Jul 25 14:07 am335x-icev2-am335x-icev2-sd.dtb -> am335x-icev2--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         lrwxrwxrwx 2   91 Jul 25 14:07 am335x-icev2.dtb -> am335x-icev2--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         -rw-r--r-- 2  62K Jul 25 14:07 am335x-icev2-prueth--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         lrwxrwxrwx 2   98 Jul 25 14:07 am335x-icev2-prueth-am335x-icev2-sd.dtb -> am335x-icev2-prueth--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         lrwxrwxrwx 2   98 Jul 25 14:07 am335x-icev2-prueth.dtb -> am335x-icev2-prueth--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.dtb
         lrwxrwxrwx 2   87 Jul 25 14:07 fitImage -> fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.bin
         -rw-r--r-- 2 4.5M Jul 25 14:07 fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.bin
         -rw-r--r-- 2 6.4M Jul 25 14:07 fitImage-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd.ext4
         lrwxrwxrwx 2   87 Jul 25 14:07 fitImage-am335x-icev2-sd.bin -> fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.bin
         -rw-r--r-- 2 6.4M Jul 25 14:07 fitImage-am335x-icev2-sd.ext4
         -rw-r--r-- 2 2.4K Jul 25 14:07 fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.its
         lrwxrwxrwx 2   91 Jul 25 14:07 fitImage-its-am335x-icev2-sd -> fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.its
         -rw-r--r-- 2 4.4M Jul 25 14:07 fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.bin
         lrwxrwxrwx 2   97 Jul 25 14:07 fitImage-linux.bin-am335x-icev2-sd -> fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.bin
         lrwxrwxrwx 2  116 Jul 25 14:04 MLO -> MLO-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2  116 Jul 25 14:04 MLO-am335x-icev2-sd -> MLO-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  42K Jul 25 14:04 MLO-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 384K Jul 25 14:04 MLO-am335x-icev2-sd-sdcard-defconfig-x3
         lrwxrwxrwx 2  116 Jul 25 14:04 MLO-sdcard-defconfig -> MLO-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   39 Jul 25 14:04 MLO-sdcard-defconfig-x3 -> MLO-am335x-icev2-sd-sdcard-defconfig-x3
         -rw-r--r-- 2 4.8M Jul 25 14:07 modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.tgz
         lrwxrwxrwx 2   86 Jul 25 14:07 modules-am335x-icev2-sd.tgz -> modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.12-am335x-icev2-sd-20220725110331.tgz
         lrwxrwxrwx 2   11 Jul 25 14:04 startup-am335x-icev2-sd.img -> startup.img
         lrwxrwxrwx 2   19 Jul 25 14:04 startup-am335x-icev2-sd.img.version -> startup.img.version
         -rw-r--r-- 2 2.5K Jul 25 14:04 startup.img
         -rw-r--r-- 2   16 Jul 25 14:04 startup.img.version
         -rw-r--r-- 2  91K Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.manifest
         -rw-r--r-- 2 932M Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.sdcard.img
         -rw-r--r-- 2  28M Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.version
         -rw-r--r-- 2 357K Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd-20220725110331.testdata.json
         lrwxrwxrwx 2   65 Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd.manifest -> tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.manifest
         lrwxrwxrwx 2   67 Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd.sdcard.img -> tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.sdcard.img
         lrwxrwxrwx 2   69 Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd.squashfs-lzo -> tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.squashfs-lzo
         lrwxrwxrwx 2   63 Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd.testdata.json -> tanowrt-image-full-am335x-icev2-sd-20220725110331.testdata.json
         lrwxrwxrwx 2   64 Jul 25 14:09 tanowrt-image-full-am335x-icev2-sd.version -> tanowrt-image-full-am335x-icev2-sd-20220725110331.rootfs.version
         -rw-r--r-- 2 5.5K Jul 25 14:09 tanowrt-image-full.env
         -rw-r--r-- 2 3.1K Jul 25 14:09 tanowrt-image-full-sdimage-ti-swu-a-b.wks
         -rw-r--r-- 2  35M Jul 25 14:09 tanowrt-image-full-swu-am335x-icev2-sd-20220725110331.swu
         lrwxrwxrwx 2   57 Jul 25 14:09 tanowrt-image-full-swu-am335x-icev2-sd.swu -> tanowrt-image-full-swu-am335x-icev2-sd-20220725110331.swu
         lrwxrwxrwx 2   67 Jul 25 14:04 u-boot-am335x-icev2-sd.img -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 14:04 u-boot-am335x-icev2-sd.img-sdcard-defconfig -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 14:04 u-boot.img -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 14:04 u-boot.img-sdcard-defconfig -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   91 Jul 25 14:04 u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig -> u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  411 Jul 25 14:04 u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 14:04 u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   95 Jul 25 14:04 u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig.bin -> u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   91 Jul 25 14:04 u-boot-initial-env-sdcard-defconfig -> u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   95 Jul 25 14:04 u-boot-initial-env-sdcard-defconfig.bin -> u-boot-initial-env-am335x-icev2-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         -rw-r--r-- 2 675K Jul 25 14:04 u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   87 Jul 25 14:04 u-boot-spl.bin -> u-boot-spl.bin-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   87 Jul 25 14:04 u-boot-spl.bin-am335x-icev2-sd -> u-boot-spl.bin-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         -rwxr-xr-x 2  41K Jul 25 14:04 u-boot-spl.bin-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   87 Jul 25 14:04 u-boot-spl.bin-am335x-icev2-sd-sdcard-defconfig -> u-boot-spl.bin-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   87 Jul 25 14:04 u-boot-spl.bin-sdcard-defconfig -> u-boot-spl.bin-am335x-icev2-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig


.. _sec-am335x-icev2-flash:

Writing Image to microSD Card
=============================

No special information about writing images to microSD card
for AM3359 ICEv2 EVM board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing bootable card image for the ``am335x-icev2-sd.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-am335x-icev2-sd.sdcard.img of=/dev/mmcblk1


.. _sec-am335x-icev2-booting:

Booting and Running
===================

.. _sec-am335x-icev2-boot-mode:

Choosing Boot Mode
------------------

Short pin 2 and 3 on J5 (sysboot) for choosing MMC/SD boot mode.


.. _sec-am335x-icev2-ethernet-mode:

Choosing Ethernet Mode
----------------------

Jumpers J18 and J19 must be set to control the Ethernet ports using CPSW
(gigabit switch) or PRU-ICSS mode. For PRU-ICSS mode, short pins 2 and 3.
For CPSW mode, short pins 1 and 2.


Booting from microSD Card
-------------------------

1. Choose proper :ref:`boot mode <sec-am335x-icev2-boot-mode>` and
   :ref:`Ethernet mode <sec-am335x-icev2-ethernet-mode>`.
2. Insert the microSD card into the slot on the AM3359 ICEv2 EVM board (power is off).
3. Power on board.
4. TanoWrt will be booting from microSD card.
5. Log in to system using default :ref:`credentials <sec-access-creds>`.

TI AM3359 ICEv2 EVM board has SPI NOR flash. By default this storage contains
a demonstration bootloader. Booting from an microSD card is only possible when
there is no bootloader in the SPI NOR flash. If you have an bootloader in the SPI NOR
flash you must erase SPI NOR flash contents. Follow instructions in
section :ref:`sec-am335x-icev2-erasing-nor` to erase SPI NOR flash contents.


.. _sec-am335x-icev2-serial:

Serial Console
==============

The debug serial console on the AM3359 ICEv2 EVM board is accessible
via the Micro USB connector on board.
A Micro USB-AB connector (J13) is connected to the upstream port of the
USB to UART converter IC (FT2232L). This is used for USB to JTAG and USB to UART
conversion applications. This USB port can also be used for XDS100V2 JTAG emulation.

The default serial console settings for AM3359 ICEv2 EVM for U-Boot and kernel
are described in the table below.

+-----------------+-------------------+
| Parameter       | Value             |
+=================+===================+
| Baudrate        | 115200            |
+-----------------+-------------------+
| Data bits       | 8                 |
+-----------------+-------------------+
| Stop bits       | 1                 |
+-----------------+-------------------+
| Parity          | none              |
+-----------------+-------------------+
| Flow control    | none              |
+-----------------+-------------------+


.. _sec-am335x-icev2-network-config:

Default Network Configuration
=============================

By default Ethernet 1 and 2 ports (``eth0`` and ``eth1`` interfaces) are joined into
a bridge (``br-lan`` interface) with enabled :term:`RSTP` protocol.
Bridge (``br-lan``) configured with static IP address 192.168.0.1/24 with enabled
:term:`DHCP` server.

Ethernet ports 1 and 2 (``eth0`` and ``eth1``) have :term:`LLDP` enabled by default.


.. _sec-am335x-icev2-webui:

Web User Interface
==================

The WebUI can be accessed via Ethernet ports through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-am335x-icev2-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-am335x-icev2-luci-status:
.. figure:: images/am335x-icev2-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-am335x-icev2-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-am335x-icev2-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


Additional Information
======================

Here are sections with various additional information about the
AM3359 ICEv2 EVM board and the operation of TanoWrt on it.

.. toctree::
   :maxdepth: 1

   extra/erasing-nor.rst
   extra/ppp-over-uart4.rst
   extra/ti-uio.rst


References
==========

1. https://www.ti.com/tool/TMDSICE3359

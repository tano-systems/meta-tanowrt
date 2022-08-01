.. SPDX-License-Identifier: MIT

.. _machine-am335x-bbb:

****************
BeagleBone Black
****************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-am335x-bbb-board:

Board Overview
==============

BeagleBone Black is a low-cost, community-supported development platform for developers
and hobbyists.

.. _fig-am335x-bbb:
.. figure:: images/am335x-bbb.jpg
   :width: 800
   :class: with-border

   BeagleBone Black Top and Bottom Views


Block Diagram
-------------

.. _fig-am335x-bbb-block-diagram:
.. figure:: images/am335x-bbb-block-diagram.jpg
    :width: 300
    :class: with-border

    BeagleBone Black Block Diagram


Specification
-------------

.. table:: BeagleBone Black Specification

   +--------------+------------------------------------------------------------------------------+
   | Model        | BeagleBone Black                                                             |
   +==============+==============================================================================+
   | Processor    || Texas Instruments AM3358 (Sitara AM3358BZCZ100)                             |
   |              || 1 x ARM Cortex-A8                                                           |
   |              || frequency up to 1.0 GHz                                                     |
   |              || 3D graphics accelerator                                                     |
   |              || NEON floating-point accelerator                                             |
   |              || 2 |times| PRU 32-bit microcontrollers                                       |
   +--------------+------------------------------------------------------------------------------+
   | GPU          || SGX530 3D, 20M Polygons/s                                                   |
   +--------------+------------------------------------------------------------------------------+
   | PMIC         || TPS65217C PMIC regulator and one additional LDO                             |
   +--------------+------------------------------------------------------------------------------+
   | Memory       || 512 MiB DDR3L SDRAM                                                         |
   +--------------+------------------------------------------------------------------------------+
   | Storage      || 4 GB, 8-bit Embedded MMC (eMMC)                                             |
   |              || microSD card                                                                |
   +--------------+------------------------------------------------------------------------------+
   | Debug        || Optional Onboard 20-pin CTI JTAG, Serial Header                             |
   +--------------+------------------------------------------------------------------------------+
   | USB          || 1 |times| Type A USB 2.0 Host LS/FS/HS                                      |
   |              || 1 |times| miniUSB (client mode)                                             |
   +--------------+------------------------------------------------------------------------------+
   | Video        || 1 |times| HDMI interface (microHDMI)                                        |
   |              || 16b HDMI, 1280x1024 (MAX)                                                   |
   |              || 1024x768, 1280x720, 1440x900, 1920x1080\@24Hz w/EDID Support                |
   +--------------+------------------------------------------------------------------------------+
   | Audio        || Via HDMI Interface, Stereo                                                  |
   +--------------+------------------------------------------------------------------------------+
   | Ethernet     || 1 |times| 10/100 RJ45 Ethernet (SMSC PHY)                                   |
   +--------------+------------------------------------------------------------------------------+
   | Expansion    || 5V, 3.3V, VDD_ADC(1.8V)                                                     |
   | Connectors   || 1 |times| McASP                                                             |
   | (3.3V I/O on || 1 |times| SPI                                                               |
   | all          || 1 |times| I2C                                                               |
   | signals)     || 69 |times| GPIO                                                             |
   |              || 1 |times| LCD                                                               |
   |              || 1 |times| GPMC                                                              |
   |              || 2 |times| MMC                                                               |
   |              || 7 |times| AIN (1.8V max),                                                   |
   |              || 4 |times| Timers                                                            |
   |              || 4 |times| Serial Ports                                                      |
   |              || 1 |times| CAN                                                               |
   |              || 2 |times| EHRPWM                                                            |
   |              || DMA Interrupt                                                               |
   |              || Power button                                                                |
   |              || Expansion Board ID (up to 4 can be stacked)                                 |
   +--------------+------------------------------------------------------------------------------+
   | Power Source | miniUSB USB or DC Jack                                                       |
   +--------------+------------------------------------------------------------------------------+
   | Power        | 5V/1A DC input jack                                                          |
   +--------------+------------------------------------------------------------------------------+


.. _sec-am335x-bbb-targets:

Build Targets
=============

.. _sec-am335x-bbb-machines:

Machines
--------

.. _table-am335x-bbb-machines:
.. table:: Supported Machines

   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_      | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +==================+==============================+==========================+====================================+========================+==========================+
   | BeagleBone Black | ``am335x-bbb-sd.yml``        | ``am335x-bbb-sd``        | ``tanowrt-image-full-swu``         | microSD card           | |ndash|                  |
   |                  +------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   |                  | ``am335x-bbb-emmc.yml``      | ``am335x-bbb-emmc``      | ``tanowrt-image-full-swu-factory`` | internal eMMC          | microSD card             |
   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-am335x-bbb-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-am335x-bbb-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-am335x-bbb-images:

Images
------

.. _table-am335x-bbb-images:
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
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu-factory`` | ``am335x-bbb-emmc.yml``    | Factory installation image for standard TanoWrt     |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-full``                              |
   |                           |                                    |                            | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                            | as dependencies.                                    |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-am335x-bbb-build` section).


.. _sec-am335x-bbb-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-am335x-bbb-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-am335x-bbb-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-am335x-bbb-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for BeagleBone Black Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For SD Card

.. code-block:: console

   $ kas build targets/kas/am335x-bbb-sd.yml

Default images will be produced to boot and run from the SD
card on the BeagleBone Black target board.

.. rubric:: For Internal eMMC Flash

.. code-block:: console

   $ kas build targets/kas/am335x-bbb-emmc.yml

An initial factory installation image will be generated,
intended to run from the SD card. The installer image
will install the default image to the internal eMMC flash
memory and further the BeagleBone Black board will boot
and run from the eMMC flash memory.


.. _sec-am335x-bbb-partitioning:

Partitioning Layouts
====================

microSD
-------

The partitioning and data layout of the microSD card image for the BeagleBone Black board
are shown in the figure below.

.. _fig-am335x-bbb-layout-sd:
.. figure:: images/am335x-bbb-layout-sd.svg
   :width: 1000

   BeagleBone Black Partitions Layout for microSD Card

eMMC
----

The partitioning and data layout of the eMMC image for the BeagleBone Black board
are shown in the figure below.

.. _fig-am335x-bbb-layout-emmc:
.. figure:: images/am335x-bbb-layout-emmc.svg
   :width: 1000

   BeagleBone Black Partitions Layout for eMMC

The ROM2 (Boot Partition 1, :file:`/dev/mmcblk1boot0`) and ROM3 (Boot Partition 2, :file:`/dev/mmcblk1boot1`)
eMMC hardware partitions are currently not used. TI AM335x devices does not support booting
from eMMC boot partitions. Only boot from user area is supported.

In the future, it is planned to use SPL (MLO) to select the partition depending on
the Boot Partition [179] register value from the EXTCSD area. On boot partitions
it is planned to store redundant U-Boot and startup script images.


.. _sec-am335x-bbb-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-am335x-bbb-artifacts` for a description of some common (not all) build artifacts.

.. _table-am335x-bbb-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | Artifact                                                            | Target(s)                     | Description                                                          |
   +=====================================================================+===============================+======================================================================+
   | .. centered:: Bootloader (SPL)                                                                                                                                             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`MLO-sdcard-defconfig`                                        | *All*                         | MLO binary blob for microSD card images.                             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`MLO-emmc-defconfig`                                          | ``am335x-bbb-emmc.yml``       | MLO binary blob for eMMC images.                                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Bootloader (U-Boot)                                                                                                                                          |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`startup-<MACHINE>.img`                                       | *All*                         | U-Boot startup script.                                               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`startup-factory-<MACHINE>.img`                               | ``am335x-bbb-emmc.yml``       | U-Boot startup script for factory installation image.                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdcard-defconfig`               | *All*                         | U-Boot initial environment image for microSD card image.             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-emmc-defconfig`                 | ``am335x-bbb-emmc.yml``       | U-Boot initial environment image for internal eMMC flash.            |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdcard-defconfig`                       | *All*                         | U-Boot binary image for booting from microSD card.                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-emmc-defconfig`                         | ``am335x-bbb-emmc.yml``       | U-Boot binary image for booting from internal eMMC flash.            |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.bin`                                      | *All*                         | Flattened Image Tree (FIT) image with Linux kernel                   |
   |                                                                     |                               | and Device Tree Blobs (DTB).                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.ext4`                                     | *All*                         | FIT image packed into an ext4 file system image.                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`am335x-boneblack.dtb`                                        | *All*                         | Target Device Tree Blob (DTB).                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>.ext4` | ``am335x-bbb-emmc.yml``       | FIT image for SWU factory installation image with                    |
   |                                                                     |                               | initramfs image.                                                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`                         | ``am335x-bbb-sd.yml``         | SD card image including all required partitions for booting          |
   |                                                                     |                               | and running the system. This image is ready to be written            |
   |                                                                     |                               | to the SD card using the :command:`dd` utility or similar            |
   |                                                                     |                               | (see :ref:`sec-am335x-bbb-flash`).                                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`             | ``am335x-bbb-emmc.yml``       | SD card factory installation image. This image is ready              |
   |                                                                     |                               | to be written to the SD card using the :command:`dd` utility         |
   |                                                                     |                               | or similar (see :ref:`sec-am335x-bbb-flash`).                        |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`tanowrt-image-initramfs-swu-factory-<MACHINE>.cpio.gz`       | ``am335x-bbb-emmc.yml``       | Root filesystem initramfs image for factory installtion              |
   |                                                                     |                               | image. This image is included in                                     |
   |                                                                     |                               | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>.ext4`. |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`                       | *All*                         | Root filesystem image (squashfs with LZO compression).               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                            | *All*                         | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-am335x-bbb-targets_>`__. ``<rootfs-image>`` is replaced
          by the actual read-only root filesystem `image <sec-am335x-bbb-images_>`__ name.

For example, below is the lists of artifacts produced by the ``am335x-bbb-emmc.yml``
and ``am335x-bbb-sd.yml`` target builds. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

Build Artifacts Listings for ``am335x-bbb-sd.yml`` Target
---------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/am335x-bbb-sd]$ ls -gGh | grep -v -e "^l"
         total 164M
         -rw-r--r-- 2  60K Jul 25 04:22 am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.dtb
         -rw-r--r-- 2 4.5M Jul 25 04:22 fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.bin
         -rw-r--r-- 2 6.4M Jul 25 04:22 fitImage-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd.ext4
         -rw-r--r-- 2 6.4M Jul 25 04:22 fitImage-am335x-bbb-sd.ext4
         -rw-r--r-- 2 1.6K Jul 25 04:22 fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.its
         -rw-r--r-- 2 4.4M Jul 25 04:22 fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.bin
         -rw-r--r-- 2  42K Jul 25 04:19 MLO-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 384K Jul 25 04:19 MLO-am335x-bbb-sd-sdcard-defconfig-x3
         -rw-r--r-- 2 4.7M Jul 25 04:22 modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.tgz
         -rw-r--r-- 2 2.4K Jul 25 04:19 startup.img
         -rw-r--r-- 2   16 Jul 25 04:19 startup.img.version
         -rw-r--r-- 2  92K Jul 25 04:23 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.manifest
         -rw-r--r-- 2 932M Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.sdcard.img
         -rw-r--r-- 2  30M Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.version
         -rw-r--r-- 2 356K Jul 25 04:23 tanowrt-image-full-am335x-bbb-sd-20220725011807.testdata.json
         -rw-r--r-- 2 5.4K Jul 25 04:24 tanowrt-image-full.env
         -rw-r--r-- 2 3.0K Jul 25 04:24 tanowrt-image-full-sdimage-ti-swu-a-b.wks
         -rw-r--r-- 2  38M Jul 25 04:24 tanowrt-image-full-swu-am335x-bbb-sd-20220725011807.swu
         -rw-r--r-- 2  411 Jul 25 04:19 u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 04:19 u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         -rw-r--r-- 2 675K Jul 25 04:19 u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         -rwxr-xr-x 2  41K Jul 25 04:19 u-boot-spl.bin-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/am335x-bbb-sd]$ ls -gGh
         total 164M
         -rw-r--r-- 2  60K Jul 25 04:22 am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.dtb
         lrwxrwxrwx 2   92 Jul 25 04:22 am335x-boneblack-am335x-bbb-sd.dtb -> am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.dtb
         lrwxrwxrwx 2   92 Jul 25 04:22 am335x-boneblack.dtb -> am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.dtb
         lrwxrwxrwx 2   84 Jul 25 04:22 fitImage -> fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.bin
         -rw-r--r-- 2 4.5M Jul 25 04:22 fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.bin
         -rw-r--r-- 2 6.4M Jul 25 04:22 fitImage-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd.ext4
         lrwxrwxrwx 2   84 Jul 25 04:22 fitImage-am335x-bbb-sd.bin -> fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.bin
         -rw-r--r-- 2 6.4M Jul 25 04:22 fitImage-am335x-bbb-sd.ext4
         -rw-r--r-- 2 1.6K Jul 25 04:22 fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.its
         lrwxrwxrwx 2   88 Jul 25 04:22 fitImage-its-am335x-bbb-sd -> fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.its
         -rw-r--r-- 2 4.4M Jul 25 04:22 fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.bin
         lrwxrwxrwx 2   94 Jul 25 04:22 fitImage-linux.bin-am335x-bbb-sd -> fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.bin
         lrwxrwxrwx 2  114 Jul 25 04:19 MLO -> MLO-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2  114 Jul 25 04:19 MLO-am335x-bbb-sd -> MLO-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  42K Jul 25 04:19 MLO-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 384K Jul 25 04:19 MLO-am335x-bbb-sd-sdcard-defconfig-x3
         lrwxrwxrwx 2  114 Jul 25 04:19 MLO-sdcard-defconfig -> MLO-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   37 Jul 25 04:19 MLO-sdcard-defconfig-x3 -> MLO-am335x-bbb-sd-sdcard-defconfig-x3
         -rw-r--r-- 2 4.7M Jul 25 04:22 modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.tgz
         lrwxrwxrwx 2   83 Jul 25 04:22 modules-am335x-bbb-sd.tgz -> modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-sd-20220725011807.tgz
         lrwxrwxrwx 2   11 Jul 25 04:19 startup-am335x-bbb-sd.img -> startup.img
         lrwxrwxrwx 2   19 Jul 25 04:19 startup-am335x-bbb-sd.img.version -> startup.img.version
         -rw-r--r-- 2 2.4K Jul 25 04:19 startup.img
         -rw-r--r-- 2   16 Jul 25 04:19 startup.img.version
         -rw-r--r-- 2  92K Jul 25 04:23 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.manifest
         -rw-r--r-- 2 932M Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.sdcard.img
         -rw-r--r-- 2  30M Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.version
         -rw-r--r-- 2 356K Jul 25 04:23 tanowrt-image-full-am335x-bbb-sd-20220725011807.testdata.json
         lrwxrwxrwx 2   63 Jul 25 04:23 tanowrt-image-full-am335x-bbb-sd.manifest -> tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.manifest
         lrwxrwxrwx 2   65 Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd.sdcard.img -> tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.sdcard.img
         lrwxrwxrwx 2   67 Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd.squashfs-lzo -> tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.squashfs-lzo
         lrwxrwxrwx 2   61 Jul 25 04:23 tanowrt-image-full-am335x-bbb-sd.testdata.json -> tanowrt-image-full-am335x-bbb-sd-20220725011807.testdata.json
         lrwxrwxrwx 2   62 Jul 25 04:24 tanowrt-image-full-am335x-bbb-sd.version -> tanowrt-image-full-am335x-bbb-sd-20220725011807.rootfs.version
         -rw-r--r-- 2 5.4K Jul 25 04:24 tanowrt-image-full.env
         -rw-r--r-- 2 3.0K Jul 25 04:24 tanowrt-image-full-sdimage-ti-swu-a-b.wks
         -rw-r--r-- 2  38M Jul 25 04:24 tanowrt-image-full-swu-am335x-bbb-sd-20220725011807.swu
         lrwxrwxrwx 2   55 Jul 25 04:24 tanowrt-image-full-swu-am335x-bbb-sd.swu -> tanowrt-image-full-swu-am335x-bbb-sd-20220725011807.swu
         lrwxrwxrwx 2   67 Jul 25 04:19 u-boot-am335x-bbb-sd.img -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 04:19 u-boot-am335x-bbb-sd.img-sdcard-defconfig -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 04:19 u-boot.img -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 04:19 u-boot.img-sdcard-defconfig -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   89 Jul 25 04:19 u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig -> u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  411 Jul 25 04:19 u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 04:19 u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   93 Jul 25 04:19 u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig.bin -> u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   89 Jul 25 04:19 u-boot-initial-env-sdcard-defconfig -> u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   93 Jul 25 04:19 u-boot-initial-env-sdcard-defconfig.bin -> u-boot-initial-env-am335x-bbb-sd-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         -rw-r--r-- 2 675K Jul 25 04:19 u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   85 Jul 25 04:19 u-boot-spl.bin -> u-boot-spl.bin-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   85 Jul 25 04:19 u-boot-spl.bin-am335x-bbb-sd -> u-boot-spl.bin-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         -rwxr-xr-x 2  41K Jul 25 04:19 u-boot-spl.bin-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   85 Jul 25 04:19 u-boot-spl.bin-am335x-bbb-sd-sdcard-defconfig -> u-boot-spl.bin-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   85 Jul 25 04:19 u-boot-spl.bin-sdcard-defconfig -> u-boot-spl.bin-am335x-bbb-sd-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig


Build Artifacts Listings for ``am335x-bbb-emmc.yml`` Target
-----------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/am335x-bbb-emmc]$ ls -gGh | grep -v -e "^l"
         total 186M
         -rw-r--r-- 2  60K Jul 25 04:13 am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.dtb
         -rw-r--r-- 2 4.5M Jul 25 04:13 fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2 6.4M Jul 25 04:13 fitImage-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc.ext4
         -rw-r--r-- 2 6.4M Jul 25 04:13 fitImage-am335x-bbb-emmc.ext4
         -rw-r--r-- 2 1.6K Jul 25 04:13 fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.its
         -rw-r--r-- 2 2.3K Jul 25 04:13 fitImage-its-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.its
         -rw-r--r-- 2 4.4M Jul 25 04:13 fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2  15M Jul 25 04:13 fitImage-tanowrt-image-initramfs-swu-factory-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc.ext4
         -rw-r--r-- 2  12M Jul 25 04:13 fitImage-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2  15M Jul 25 04:13 fitImage-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc.ext4
         -rw-r--r-- 2  47K Jul 25 04:13 MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  42K Jul 25 04:13 MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 384K Jul 25 04:13 MLO-am335x-bbb-emmc-emmc-defconfig-x3
         -rw-r--r-- 2 384K Jul 25 04:13 MLO-am335x-bbb-emmc-sdcard-defconfig-x3
         -rw-r--r-- 2 4.7M Jul 25 04:13 modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.tgz
         -rw-r--r-- 2 1.3K Jul 25 04:14 startup-factory.img
         -rw-r--r-- 2   16 Jul 25 04:14 startup-factory.img.version
         -rw-r--r-- 2 2.4K Jul 25 04:14 startup.img
         -rw-r--r-- 2   16 Jul 25 04:14 startup.img.version
         -rw-r--r-- 2  93K Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.manifest
         -rw-r--r-- 2  30M Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.version
         -rw-r--r-- 2 356K Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.testdata.json
         -rw-r--r-- 2  37M Jul 25 04:17 tanowrt-image-full-swu-am335x-bbb-emmc-20220725005407.swu
         -rw-r--r-- 2  56M Jul 25 04:17 tanowrt-image-full-swu-factory-am335x-bbb-emmc-20220725005407.sdcard.img
         -rw-r--r-- 2 6.0K Jul 25 04:14 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2 2.2K Jul 25 04:14 tanowrt-image-full-swu-factory-sdimage-ti-swu-factory.wks
         -rw-r--r-- 2 7.1M Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.cpio.gz
         -rw-r--r-- 2 3.6K Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.manifest
         -rw-r--r-- 2   24 Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.version
         -rw-r--r-- 2 362K Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.testdata.json
         -rw-r--r-- 2 675K Jul 25 04:13 u-boot-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         -rw-r--r-- 2  411 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         -rw-r--r-- 2  411 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         -rw-r--r-- 2 675K Jul 25 04:13 u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         -rwxr-xr-x 2  46K Jul 25 04:13 u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig
         -rwxr-xr-x 2  41K Jul 25 04:13 u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/am335x-bbb-emmc]$ ls -gGh
         total 186M
         -rw-r--r-- 2  60K Jul 25 04:13 am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.dtb
         lrwxrwxrwx 2   94 Jul 25 04:13 am335x-boneblack-am335x-bbb-emmc.dtb -> am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.dtb
         lrwxrwxrwx 2   94 Jul 25 04:13 am335x-boneblack.dtb -> am335x-boneblack--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.dtb
         lrwxrwxrwx 2   86 Jul 25 04:13 fitImage -> fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2 4.5M Jul 25 04:13 fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2 6.4M Jul 25 04:13 fitImage-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc.ext4
         lrwxrwxrwx 2   86 Jul 25 04:13 fitImage-am335x-bbb-emmc.bin -> fitImage--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2 6.4M Jul 25 04:13 fitImage-am335x-bbb-emmc.ext4
         -rw-r--r-- 2 1.6K Jul 25 04:13 fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.its
         lrwxrwxrwx 2   90 Jul 25 04:13 fitImage-its-am335x-bbb-emmc -> fitImage-its--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.its
         -rw-r--r-- 2 2.3K Jul 25 04:13 fitImage-its-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.its
         lrwxrwxrwx 2  142 Jul 25 04:13 fitImage-its-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-am335x-bbb-emmc -> fitImage-its-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.its
         -rw-r--r-- 2 4.4M Jul 25 04:13 fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         lrwxrwxrwx 2   96 Jul 25 04:13 fitImage-linux.bin-am335x-bbb-emmc -> fitImage-linux.bin--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2  15M Jul 25 04:13 fitImage-tanowrt-image-initramfs-swu-factory-5.4.106+gitAUTOINC+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc.ext4
         -rw-r--r-- 2  12M Jul 25 04:13 fitImage-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         lrwxrwxrwx 2  138 Jul 25 04:13 fitImage-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-am335x-bbb-emmc -> fitImage-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.bin
         -rw-r--r-- 2  15M Jul 25 04:13 fitImage-tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc.ext4
         lrwxrwxrwx 2  114 Jul 25 04:13 MLO -> MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2  114 Jul 25 04:13 MLO-am335x-bbb-emmc -> MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  47K Jul 25 04:13 MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  42K Jul 25 04:13 MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 384K Jul 25 04:13 MLO-am335x-bbb-emmc-emmc-defconfig-x3
         -rw-r--r-- 2 384K Jul 25 04:13 MLO-am335x-bbb-emmc-sdcard-defconfig-x3
         lrwxrwxrwx 2  114 Jul 25 04:13 MLO-emmc-defconfig -> MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   37 Jul 25 04:13 MLO-emmc-defconfig-x3 -> MLO-am335x-bbb-emmc-emmc-defconfig-x3
         lrwxrwxrwx 2  116 Jul 25 04:13 MLO-sdcard-defconfig -> MLO-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   39 Jul 25 04:13 MLO-sdcard-defconfig-x3 -> MLO-am335x-bbb-emmc-sdcard-defconfig-x3
         -rw-r--r-- 2 4.7M Jul 25 04:13 modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.tgz
         lrwxrwxrwx 2   85 Jul 25 04:13 modules-am335x-bbb-emmc.tgz -> modules--5.4.106+git0+519667b0d8-tano0.2.12.20.8.2-am335x-bbb-emmc-20220725005407.tgz
         lrwxrwxrwx 2   11 Jul 25 04:14 startup-am335x-bbb-emmc.img -> startup.img
         lrwxrwxrwx 2   19 Jul 25 04:14 startup-am335x-bbb-emmc.img.version -> startup.img.version
         lrwxrwxrwx 2   19 Jul 25 04:14 startup-factory-am335x-bbb-emmc.img -> startup-factory.img
         lrwxrwxrwx 2   27 Jul 25 04:14 startup-factory-am335x-bbb-emmc.img.version -> startup-factory.img.version
         -rw-r--r-- 2 1.3K Jul 25 04:14 startup-factory.img
         -rw-r--r-- 2   16 Jul 25 04:14 startup-factory.img.version
         -rw-r--r-- 2 2.4K Jul 25 04:14 startup.img
         -rw-r--r-- 2   16 Jul 25 04:14 startup.img.version
         -rw-r--r-- 2  93K Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.manifest
         -rw-r--r-- 2  30M Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.version
         -rw-r--r-- 2 356K Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc-20220725005407.testdata.json
         lrwxrwxrwx 2   65 Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc.manifest -> tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.manifest
         lrwxrwxrwx 2   69 Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc.squashfs-lzo -> tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.squashfs-lzo
         lrwxrwxrwx 2   63 Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc.testdata.json -> tanowrt-image-full-am335x-bbb-emmc-20220725005407.testdata.json
         lrwxrwxrwx 2   64 Jul 25 04:17 tanowrt-image-full-am335x-bbb-emmc.version -> tanowrt-image-full-am335x-bbb-emmc-20220725005407.rootfs.version
         -rw-r--r-- 2  37M Jul 25 04:17 tanowrt-image-full-swu-am335x-bbb-emmc-20220725005407.swu
         lrwxrwxrwx 2   57 Jul 25 04:17 tanowrt-image-full-swu-am335x-bbb-emmc.swu -> tanowrt-image-full-swu-am335x-bbb-emmc-20220725005407.swu
         -rw-r--r-- 2  56M Jul 25 04:17 tanowrt-image-full-swu-factory-am335x-bbb-emmc-20220725005407.sdcard.img
         lrwxrwxrwx 2   72 Jul 25 04:17 tanowrt-image-full-swu-factory-am335x-bbb-emmc.sdcard.img -> tanowrt-image-full-swu-factory-am335x-bbb-emmc-20220725005407.sdcard.img
         -rw-r--r-- 2 6.0K Jul 25 04:14 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2 2.2K Jul 25 04:14 tanowrt-image-full-swu-factory-sdimage-ti-swu-factory.wks
         -rw-r--r-- 2 7.1M Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.cpio.gz
         -rw-r--r-- 2 3.6K Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.manifest
         -rw-r--r-- 2   24 Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.version
         -rw-r--r-- 2 362K Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.testdata.json
         lrwxrwxrwx 2   81 Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc.cpio.gz -> tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.cpio.gz
         lrwxrwxrwx 2   82 Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc.manifest -> tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.manifest
         lrwxrwxrwx 2   80 Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc.testdata.json -> tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.testdata.json
         lrwxrwxrwx 2   81 Jul 25 04:13 tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc.version -> tanowrt-image-initramfs-swu-factory-am335x-bbb-emmc-20220725005407.rootfs.version
         lrwxrwxrwx 2   65 Jul 25 04:13 u-boot-am335x-bbb-emmc.img -> u-boot-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   65 Jul 25 04:13 u-boot-am335x-bbb-emmc.img-emmc-defconfig -> u-boot-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 04:13 u-boot-am335x-bbb-emmc.img-sdcard-defconfig -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         -rw-r--r-- 2 675K Jul 25 04:13 u-boot-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   65 Jul 25 04:13 u-boot.img -> u-boot-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   65 Jul 25 04:13 u-boot.img-emmc-defconfig -> u-boot-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   67 Jul 25 04:13 u-boot.img-sdcard-defconfig -> u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   89 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig -> u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  411 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   93 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig.bin -> u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   91 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig -> u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2  411 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         -rw-r--r-- 2 128K Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   95 Jul 25 04:13 u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig.bin -> u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   89 Jul 25 04:13 u-boot-initial-env-emmc-defconfig -> u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   93 Jul 25 04:13 u-boot-initial-env-emmc-defconfig.bin -> u-boot-initial-env-am335x-bbb-emmc-emmc-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         lrwxrwxrwx 2   91 Jul 25 04:13 u-boot-initial-env-sdcard-defconfig -> u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2
         lrwxrwxrwx 2   95 Jul 25 04:13 u-boot-initial-env-sdcard-defconfig.bin -> u-boot-initial-env-am335x-bbb-emmc-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.bin
         -rw-r--r-- 2 675K Jul 25 04:13 u-boot-sdcard-defconfig-2020.01+gitAUTOINC+6b5b982e98-r36.tano2.img
         lrwxrwxrwx 2   85 Jul 25 04:13 u-boot-spl.bin -> u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig
         lrwxrwxrwx 2   85 Jul 25 04:13 u-boot-spl.bin-am335x-bbb-emmc -> u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig
         -rwxr-xr-x 2  46K Jul 25 04:13 u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig
         -rwxr-xr-x 2  41K Jul 25 04:13 u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   85 Jul 25 04:13 u-boot-spl.bin-am335x-bbb-emmc-emmc-defconfig -> u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig
         lrwxrwxrwx 2   87 Jul 25 04:13 u-boot-spl.bin-am335x-bbb-emmc-sdcard-defconfig -> u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig
         lrwxrwxrwx 2   85 Jul 25 04:13 u-boot-spl.bin-emmc-defconfig -> u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-emmc-defconfig
         lrwxrwxrwx 2   87 Jul 25 04:13 u-boot-spl.bin-sdcard-defconfig -> u-boot-spl.bin-am335x-bbb-emmc-2020.01+gitAUTOINC+6b5b982e98-r36.tano2-sdcard-defconfig


.. _sec-am335x-bbb-flash:

Writing Images
==============


.. _sec-am335x-bbb-flash-sd:

Writing Image to SD Card
-----------------------------

No special information about writing images to microSD card
for BeagleBone Black board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing factory installation image for the ``am335x-bbb-emmc.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-swu-factory-am335x-bbb-emmc.sdcard.img of=/dev/mmcblk1

Writing bootable card image for the ``am335x-bbb-sd.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-am335x-bbb-sd.sdcard.img of=/dev/mmcblk1


.. _sec-am335x-bbb-flash-emmc:

Writing Image to eMMC Flash
---------------------------

For the initial flashing of the internal eMMC memory it is recommended to use
the special image of the initial factory installation. If you choose a build target
(see :ref:`sec-am335x-bbb-targets` for details) that assumes using the
factory installation image for the initial flashing of the
device, a factory installation image (:file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`)
will be automatically generated during the build process
(see :ref:`sec-am335x-bbb-build`).
To write the factory installation image to a SD card, follow the instructions
from :ref:`sec-writing-sd-or-usb` section.

When you boot device from the prepaired SD card with factory installation image the installation
of TanoWrt to the internal eMMC flash memory will be done automatically. The detailed
installation log is available on the :ref:`debug UART <sec-am335x-bbb-serial>`.
After the installation is complete, the board will reboots automatically. When the
device is rebooted, the installed system will be booted from the internal eMMC flash memory.

.. caution:: Be aware that during the installation all existing data on the internal eMMC
             flash memory will be permanently lost.


.. _sec-am335x-bbb-booting:

Booting and Running
===================

Booting from microSD Card
-------------------------

1. Insert the microSD card into the slot on the BeagleBone Black board (power is off).
2. Press and hold the S2 button.
3. Power on board.
4. Wait 1–3 seconds and release S2 button.
5. TanoWrt will be booting from microSD card.
6. Log in to system using default :ref:`credentials <sec-access-creds>`.

The procedure described above is only required if the internal eMMC drive contains
a working firmware, because when the board is powered on, it first attempts to boot
from the internal eMMC. If you want to always boot from the microSD card, you need to
erase the eMMC flash boot area. For example, this can be done by using the U-Boot command:

.. code-block:: console

   => mmc dev 1; mmc erase 0 400

or with a command in the Linux terminal:

.. code-block:: console

   $ dd if=/dev/zero of=/dev/mmcblk1 bs=1k count=512

.. caution:: Be aware that these commands will make the existing
             firmware on the eMMC flash memory **INOPERABLE**.



Booting from Internal eMMC Flash
-----------------------------------

The board by default tries to boot from the internal eMMC flash memory first.
Therefore, if the internal eMMC flash memory has a working firmware, you
don't need to perform any additional actions to boot from it.


.. _sec-am335x-bbb-serial:

Serial Console
==============


Establishing a serial communication between host computer and
BeagleBone Black board requires a USB-to-TTL serial cable.
Connect the USB-to-TTL serial cable as described below.
Don't connect the VCC wire, connect only TX, RX and GND wires.

+--------+-----------------------+
| Signal | BeagleBone Black      |
|        +-----------------------+
|        | J1 6-pin connector    |
+========+=======================+
| GND    | Pin 1                 |
+--------+-----------------------+
| TX     | Pin 4                 |
+--------+-----------------------+
| RX     | Pin 5                 |
+--------+-----------------------+

The default serial console settings for BeagleBone Black for U-Boot and kernel
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


.. _sec-am335x-bbb-network-config:

Default Network Configuration
=============================

By default Ethernet port (``eth0`` interface) are joined into a bridge
(``br-lan`` interface). Bridge (``br-lan``) configured with static IP address 192.168.0.1/24 with
enabled :term:`DHCP` server.

Also you can connect to the board using USB RNDIS connection (``usb0`` interface).
RNDIS interface configured with static IP address 172.16.0.1/24 with
enabled :term:`DHCP` server.

The Ethernet port (``eth0``) has enabled :term:`LLDP` by default.


.. _sec-am335x-bbb-webui:

Web User Interface
=============================

The WebUI can be accessed via Ethernet port or USB network connection through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-am335x-bbb-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-am335x-bbb-luci-status:
.. figure:: images/am335x-bbb-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-am335x-bbb-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-am335x-bbb-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


References
==========

1. https://beagleboard.org/black

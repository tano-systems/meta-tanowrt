.. SPDX-License-Identifier: MIT

.. _machine-ls1028ardb:

***************
NXP LS1028A RDB
***************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-ls1028ardb-board:

Board Overview
==============

The LS1028A reference design board (RDB) is a computing, evaluation,
and development platform that supports industrial IoT applications,
human machine interface solutions, and industrial networking.

.. _fig-ls1028ardb-front:
.. figure:: images/ls1028ardb-front.jpg
   :width: 1000
   :class: with-border

   NXP LS1028A RDB Front View

.. _fig-ls1028ardb-back:
.. figure:: images/ls1028ardb-back.jpg
   :width: 1000
   :class: with-border

   NXP LS1028A RDB Back View

Photos
------

.. _fig-ls1028ardb-pcb:
.. figure:: images/ls1028ardb-pcb.jpg
    :width: 500
    :class: with-border

    NXP LS1028A RDB PCB


Specification
-------------

.. table:: NXP LS1028A RDB Specification

   +--------------+------------------------------------------------------------------------------+
   | Model        | NXP LS1028A RDB                                                              |
   +==============+==============================================================================+
   | Processor    || Layerscape LS1028A                                                          |
   |              || Dual-core processor based on Cortex-A72 64 bits                             |
   |              || frequency up to 1.5 GHz                                                     |
   |              || ECC on internal L1, L2 caches for high-reliability applications             |
   +--------------+------------------------------------------------------------------------------+
   | Memory       || 4 GiB DDR4 SDRAM w/ECC                                                      |
   |              || 32-bit DDR4 bus at data rates up to 1600 MT/s                               |
   +--------------+------------------------------------------------------------------------------+
   | Storage      || 256 MB QSPI NOR flash                                                       |
   |              || 512 MB QSPI NAND flash                                                      |
   |              || 8 GB eMMC                                                                   |
   |              || SDHC port connected to full-size SD card slot                               |
   +--------------+------------------------------------------------------------------------------+
   | USB          || 1 |times| Type A USB 3.0 super-speed port                                   |
   |              || 1 |times| Type C USB 3.0 super-speed port                                   |
   +--------------+------------------------------------------------------------------------------+
   | Video        || 1 |times| DisplayPort interface                                             |
   +--------------+------------------------------------------------------------------------------+
   | Ethernet     || 1 |times| Gigabit Ethernet support w/TSN, 1588                              |
   |              || 4 |times| Gigabit Ethernet switch support w/TSN, 1588 (QSGMII)              |
   +--------------+------------------------------------------------------------------------------+
   | Basic        || 2 |times| M.2 Type E slots with PCIe Gen 3.0 x1                             |
   | Peripherals  || 1 |times| M.2 Type B slot with SATA 3.0 (resistor mux with 1 Type E slot)   |
   | and          || 2 |times| DB9 RS232 serial ports                                            |
   | Interconnect || 2 |times| DB9 CAN interfaces                                                |
   |              || 1 |times| 3.5 mm audio output                                               |
   |              || 2 |times| MikroBUS sockets                                                  |
   +--------------+------------------------------------------------------------------------------+
   | Power        | 12V/5A DC input jack                                                         |
   +--------------+------------------------------------------------------------------------------+


.. _sec-ls1028ardb-targets:

Build Targets
=============

.. _sec-ls1028ardb-machines:

Machines
--------

.. _table-ls1028ardb-machines:
.. table:: Supported Machines

   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_     | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +=================+==============================+==========================+====================================+========================+==========================+
   | NXP LS1028A RDB | ``ls1028ardb-sd.yml``        | ``ls1028ardb-sd``        | ``tanowrt-image-full-swu``         | SD card                | |ndash|                  |
   |                 +------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   |                 | ``ls1028ardb-emmc.yml``      | ``ls1028ardb-emmc``      | ``tanowrt-image-full-swu-factory`` | internal eMMC          | SD card                  |
   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-ls1028ardb-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-ls1028ardb-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-ls1028ardb-images:

Images
------

.. _table-ls1028ardb-images:
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
   |                           | ``tanowrt-image-full-swu-factory`` | ``ls1028ardb-emmc.yml``    | Factory installation image for standard TanoWrt  .  |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-full``                              |
   |                           |                                    |                            | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                            | as dependencies.                                    |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+
   | ``tanowrt-image-qt5``     | ``tanowrt-image-qt5``              | *All*                      | Standard TanoWrt image with Qt5 support.            |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-qt5-swu``          | *All*                      | Standard TanoWrt image with Qt5 support             |
   |                           |                                    |                            | and :ref:`firmware upgrade <sec-firmware-upgrade>`  |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-qt5`` will also be built            |
   |                           |                                    |                            | as dependency.                                      |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-qt5-swu-factory``  | ``ls1028ardb-emmc.yml``    | Factory installation image for standard TanoWrt     |
   |                           |                                    |                            | image with Qt5 support. When building this image,   |
   |                           |                                    |                            | ``tanowrt-image-qt5``                               |
   |                           |                                    |                            | and ``tanowrt-image-qt5-swu`` will also be built    |
   |                           |                                    |                            | as dependencies.                                    |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-ls1028ardb-build` section).


.. _sec-ls1028ardb-boot-sequence-selection:

Boot Sequence Selection
=======================

DIP switches SW2[1:4] on the LS1028A RDB board are used to select the boot source.
The DIP switches settings for all supported boot sources are shown in the
figure and table below.

.. _fig-ls1028ardb-boot-source-selcetion:
.. figure:: images/ls1028ardb-dip-switches.svg
   :width: 600

   NXP LS1028A RDB Boot Source Selection

Also you can switch the boot source in runtime from the U-Boot command
line using the command :command:`qixis_reset`:

- ``qixis_reset sd`` |mdash| switch to SDHC1 (SD Card);
- ``qixis_reset emmc`` |mdash| switch to SDHC2 (eMMC);
- ``qixis_reset`` |mdash| switch to XSPI (NOR).


.. _sec-ls1028ardb-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-ls1028ardb-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-ls1028ardb-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-ls1028ardb-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for NXP LS1028A RDB Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For SD Card

.. code-block:: console

   $ kas build targets/kas/ls1028ardb-sd.yml

Default images will be produced to boot and run from the SD
card on the NXP LS1028A RDB target board.

.. rubric:: For Internal eMMC Flash

.. code-block:: console

   $ kas build targets/kas/ls1028ardb-emmc.yml

An initial factory installation image will be generated,
intended to run from the SD card. The installer image
will install the default image to the internal eMMC flash
memory and further the NXP LS1028A RDB board will boot
and run from the eMMC flash memory.

Build Image with Qt5 Support for NXP LS1028A RDB Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For Internal eMMC Flash

.. code-block:: console

   $ kas build --target tanowrt-image-qt5-swu-factory targets/kas/ls1028ardb-emmc.yml

An initial factory installation image will be generated,
intended to run from the SD card. The installer image
will install the default image to the internal eMMC flash
memory and further the NXP LS1028A RDB board will boot
and run from the eMMC flash memory.


.. _sec-ls1028ardb-partitioning:

Partitioning Layouts
====================

SD Card
-------

The partitioning and data layout of the SD card image for the LS1028A RDB board
are shown in the figure below.

.. _fig-ls1028ardb-layout-sd:
.. figure:: images/ls1028ardb-layout-sd.svg
   :width: 1000

   NXP LS1028A RDB Partitions Layout for SD Card

eMMC
----

The partitioning and data layout of the eMMC image for the LS1028A RDB board
are shown in the figure below.

.. _fig-ls1028ardb-layout-emmc:
.. figure:: images/ls1028ardb-layout-emmc.svg
   :width: 1000

   NXP LS1028A RDB Partitions Layout for eMMC


.. _sec-ls1028ardb-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-ls1028ardb-artifacts` for a description of some common (not all) build artifacts.

.. _table-ls1028ardb-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | Artifact                                                            | Target(s)                     | Description                                                          |
   +=====================================================================+===============================+======================================================================+
   | .. centered:: Firmware Blobs                                                                                                                                               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`ls1028a-dp-fw.bin`                                           | *All*                         | DisplayPort firmware binary blob                                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`rcw.bin`                                                     | *All*                         | RCW binary blob                                                      |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`ppa.itb`                                                     | *All*                         | PPA firmware binary blob                                             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Bootloader (U-Boot)                                                                                                                                          |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`startup-<MACHINE>.img`                                       | *All*                         | U-Boot startup script.                                               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`startup-factory-<MACHINE>.img`                               | ``ls1028ardb-emmc.yml``       | U-Boot startup script for factory installation image.                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdcard-defconfig`               | *All*                         | U-Boot initial environment image for SD card image.                  |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-emmc-defconfig`                 | ``ls1028ardb-emmc.yml``       | U-Boot initial environment image for internal eMMC flash.            |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdcard-defconfig`                       | *All*                         | U-Boot binary image for booting from SD card.                        |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-emmc-defconfig`                         | ``ls1028ardb-emmc.yml``       | U-Boot binary image for booting from internal eMMC flash.            |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`Image-<MACHINE>.bin`                                         | *All*                         | Linux kernel image                                                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.bin`                                      | *All*                         | Flattened Image Tree (FIT) image with Linux kernel                   |
   |                                                                     |                               | and Device Tree Blobs (DTB).                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.ext4`                                     | *All*                         | FIT image packed into an ext4 file system image.                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fsl-ls1028a-rdb.dtb`                                         | *All*                         | Target Device Tree Blob (DTB).                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`tano-mkb004lw.dtbo`                                          | *All*                         | Device Tree Blob (DTB) overlay for                                   |
   |                                                                     |                               | Tano Systems MKB004LW mikroBUS module.                               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>.ext4` | ``ls1028ardb-emmc.yml``       | FIT image for SWU factory installation image with                    |
   |                                                                     |                               | initramfs image.                                                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`                         | ``ls1028ardb-sd.yml``         | SD card image including all required partitions for booting          |
   |                                                                     |                               | and running the system. This image is ready to be written            |
   |                                                                     |                               | to the SD card using the :command:`dd` utility or similar            |
   |                                                                     |                               | (see :ref:`sec-ls1028ardb-flash`).                                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`             | ``ls1028ardb-emmc.yml``       | SD card factory installation image. This image is ready              |
   |                                                                     |                               | to be written to the SD card using the :command:`dd` utility         |
   |                                                                     |                               | or similar (see :ref:`sec-ls1028ardb-flash`).                        |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`tanowrt-image-initramfs-swu-factory-<MACHINE>.cpio.gz`       | ``ls1028ardb-emmc.yml``       | Root filesystem initramfs image for factory installtion              |
   |                                                                     |                               | image. This image is included in                                     |
   |                                                                     |                               | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>.ext4`. |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`                       | *All*                         | Root filesystem image (squashfs with LZO compression).               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                            | *All*                         | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-ls1028ardb-targets_>`__. ``<rootfs-image>`` is replaced
          by the actual read-only root filesystem `image <sec-ls1028ardb-images_>`__ name.

For example, below is the lists of artifacts produced by the ``ls1028ardb-emmc.yml``
and ``ls1028ardb-sd.yml`` target builds. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

.. rubric:: Build Artifacts Listings for ``ls1028ardb-sd.yml`` Target

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/ls1028ardb-sd]$ ls -gGh | grep -v -e "^l"
         total 1.1G
         drwxr-xr-x 2   60 Jul 24 19:32 dp
         -rw-r--r-- 2  14M Jul 24 19:32 fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  17M Jul 24 19:32 fitImage-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd.ext4
         -rw-r--r-- 2 2.4K Jul 24 19:32 fitImage-its--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.its
         -rw-r--r-- 2  14M Jul 24 19:32 fitImage-linux.bin--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  17M Jul 24 19:32 fitImage-ls1028ardb-sd.ext4
         -rw-r--r-- 2  26K Jul 24 19:32 fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtb
         -rw-r--r-- 2  35M Jul 24 19:32 Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  43M Jul 24 19:32 Image-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd.ext4
         -rw-r--r-- 2  43M Jul 24 19:32 Image-ls1028ardb-sd.ext4
         -rw-r--r-- 2 161M Jul 24 19:32 modules--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.tgz
         -rw-r--r-- 2  51K Jul 24 19:32 ppa.itb
         drwxr-xr-x 3   60 Jul 24 19:32 rcw
         -rw-r--r-- 2 3.1K Jul 24 19:32 startup.img
         -rw-r--r-- 2   17 Jul 24 19:32 startup.img.version
         -rw-r--r-- 2 1012 Jul 24 19:32 tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtbo
         -rw-r--r-- 2 5.1K Jul 24 19:35 tanowrt-image-full.env
         -rw-r--r-- 2 106M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.ext4.gz
         -rw-r--r-- 2 117K Jul 24 19:34 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.manifest
         -rw-r--r-- 2 939M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.sdcard.img
         -rw-r--r-- 2 116M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.squashfs-lzo
         -rw-r--r-- 2 107M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.tar.gz
         -rw-r--r-- 2   24 Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.version
         -rw-r--r-- 2 375K Jul 24 19:34 tanowrt-image-full-ls1028ardb-sd-20220724163125.testdata.json
         -rw-r--r-- 2 3.7K Jul 24 19:35 tanowrt-image-full-sdimage-ls1028ardb-swu-a-b.wks
         -rw-r--r-- 2 134M Jul 24 19:35 tanowrt-image-full-swu-ls1028ardb-sd-20220724163125.swu
         -rw-r--r-- 2  297 Jul 24 16:42 u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2 8.0K Jul 24 16:42 u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1.bin
         -rw-r--r-- 2 667K Jul 24 16:42 u-boot-sdcard-defconfig-2018.03-r0.nxp1.bin

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/ls1028ardb-sd]$ ls -gGh
         total 1.1G
         drwxr-xr-x 2   60 Jul 24 19:32 dp
         lrwxrwxrwx 2   70 Jul 24 19:32 fitImage -> fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  14M Jul 24 19:32 fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  17M Jul 24 19:32 fitImage-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd.ext4
         -rw-r--r-- 2 2.4K Jul 24 19:32 fitImage-its--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.its
         lrwxrwxrwx 2   74 Jul 24 19:32 fitImage-its-ls1028ardb-sd -> fitImage-its--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.its
         -rw-r--r-- 2  14M Jul 24 19:32 fitImage-linux.bin--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         lrwxrwxrwx 2   80 Jul 24 19:32 fitImage-linux.bin-ls1028ardb-sd -> fitImage-linux.bin--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         lrwxrwxrwx 2   70 Jul 24 19:32 fitImage-ls1028ardb-sd.bin -> fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  17M Jul 24 19:32 fitImage-ls1028ardb-sd.ext4
         -rw-r--r-- 2  26K Jul 24 19:32 fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtb
         lrwxrwxrwx 2   77 Jul 24 19:32 fsl-ls1028a-rdb.dtb -> fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtb
         lrwxrwxrwx 2   77 Jul 24 19:32 fsl-ls1028a-rdb-ls1028ardb-sd.dtb -> fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtb
         lrwxrwxrwx 2   67 Jul 24 19:32 Image -> Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  35M Jul 24 19:32 Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  43M Jul 24 19:32 Image-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd.ext4
         lrwxrwxrwx 2   67 Jul 24 19:32 Image-ls1028ardb-sd.bin -> Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.bin
         -rw-r--r-- 2  43M Jul 24 19:32 Image-ls1028ardb-sd.ext4
         lrwxrwxrwx 2   20 Jul 24 19:32 ls1028a-dp-fw.bin -> dp/ls1028a-dp-fw.bin
         -rw-r--r-- 2 161M Jul 24 19:32 modules--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.tgz
         lrwxrwxrwx 2   69 Jul 24 19:32 modules-ls1028ardb-sd.tgz -> modules--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.tgz
         -rw-r--r-- 2  51K Jul 24 19:32 ppa.itb
         drwxr-xr-x 3   60 Jul 24 19:32 rcw
         lrwxrwxrwx 2   48 Jul 24 19:32 rcw.bin -> rcw/ls1028ardb/R_SQPH_0x85be/rcw_1300_sdboot.bin
         -rw-r--r-- 2 3.1K Jul 24 19:32 startup.img
         -rw-r--r-- 2   17 Jul 24 19:32 startup.img.version
         lrwxrwxrwx 2   11 Jul 24 19:32 startup-ls1028ardb-sd.img -> startup.img
         lrwxrwxrwx 2   19 Jul 24 19:32 startup-ls1028ardb-sd.img.version -> startup.img.version
         -rw-r--r-- 2 1012 Jul 24 19:32 tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtbo
         lrwxrwxrwx 2   76 Jul 24 19:32 tano-mkb004lw.dtbo -> tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtbo
         lrwxrwxrwx 2   76 Jul 24 19:32 tano-mkb004lw-ls1028ardb-sd.dtbo -> tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-sd-20220724163125.dtbo
         -rw-r--r-- 2 5.1K Jul 24 19:35 tanowrt-image-full.env
         -rw-r--r-- 2 106M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.ext4.gz
         -rw-r--r-- 2 117K Jul 24 19:34 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.manifest
         -rw-r--r-- 2 939M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.sdcard.img
         -rw-r--r-- 2 116M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.squashfs-lzo
         -rw-r--r-- 2 107M Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.tar.gz
         -rw-r--r-- 2   24 Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.version
         -rw-r--r-- 2 375K Jul 24 19:34 tanowrt-image-full-ls1028ardb-sd-20220724163125.testdata.json
         lrwxrwxrwx 2   62 Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd.ext4.gz -> tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.ext4.gz
         lrwxrwxrwx 2   63 Jul 24 19:34 tanowrt-image-full-ls1028ardb-sd.manifest -> tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.manifest
         lrwxrwxrwx 2   65 Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd.sdcard.img -> tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.sdcard.img
         lrwxrwxrwx 2   67 Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd.squashfs-lzo -> tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.squashfs-lzo
         lrwxrwxrwx 2   61 Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd.tar.gz -> tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.tar.gz
         lrwxrwxrwx 2   61 Jul 24 19:34 tanowrt-image-full-ls1028ardb-sd.testdata.json -> tanowrt-image-full-ls1028ardb-sd-20220724163125.testdata.json
         lrwxrwxrwx 2   62 Jul 24 19:35 tanowrt-image-full-ls1028ardb-sd.version -> tanowrt-image-full-ls1028ardb-sd-20220724163125.rootfs.version
         -rw-r--r-- 2 3.7K Jul 24 19:35 tanowrt-image-full-sdimage-ls1028ardb-swu-a-b.wks
         -rw-r--r-- 2 134M Jul 24 19:35 tanowrt-image-full-swu-ls1028ardb-sd-20220724163125.swu
         lrwxrwxrwx 2   55 Jul 24 19:35 tanowrt-image-full-swu-ls1028ardb-sd.swu -> tanowrt-image-full-swu-ls1028ardb-sd-20220724163125.swu
         lrwxrwxrwx 2   65 Jul 24 16:42 u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig -> u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2  297 Jul 24 16:42 u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2 8.0K Jul 24 16:42 u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   69 Jul 24 16:42 u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig.bin -> u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   65 Jul 24 16:42 u-boot-initial-env-sdcard-defconfig -> u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1
         lrwxrwxrwx 2   69 Jul 24 16:42 u-boot-initial-env-sdcard-defconfig.bin -> u-boot-initial-env-ls1028ardb-sd-sdcard-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   43 Jul 24 16:42 u-boot-ls1028ardb-sd.bin -> u-boot-sdcard-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   43 Jul 24 16:42 u-boot-ls1028ardb-sd.bin-sdcard-defconfig -> u-boot-sdcard-defconfig-2018.03-r0.nxp1.bin
         -rw-r--r-- 2 667K Jul 24 16:42 u-boot-sdcard-defconfig-2018.03-r0.nxp1.bin


.. rubric:: Build Artifacts Listings for ``ls1028ardb-emmc.yml`` Target

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/ls1028ardb-emmc]$ ls -gGh | grep -v -e "^l"
         total 1010M
         drwxr-xr-x 2   60 Jul 24 16:56 dp
         -rw-r--r-- 2  14M Jul 24 17:04 fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  17M Jul 24 17:04 fitImage-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc.ext4
         -rw-r--r-- 2 2.4K Jul 24 17:04 fitImage-its--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.its
         -rw-r--r-- 2 3.1K Jul 24 17:04 fitImage-its-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.its
         -rw-r--r-- 2  14M Jul 24 17:04 fitImage-linux.bin--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  17M Jul 24 17:04 fitImage-ls1028ardb-emmc.ext4
         -rw-r--r-- 2  27M Jul 24 17:04 fitImage-tanowrt-image-initramfs-swu-factory-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc.ext4
         -rw-r--r-- 2  22M Jul 24 17:04 fitImage-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  27M Jul 24 17:04 fitImage-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc.ext4
         -rw-r--r-- 2  26K Jul 24 17:04 fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtb
         -rw-r--r-- 2  35M Jul 24 17:04 Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  43M Jul 24 17:04 Image-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc.ext4
         -rw-r--r-- 2  43M Jul 24 17:04 Image-ls1028ardb-emmc.ext4
         -rw-r--r-- 2 161M Jul 24 17:04 modules--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.tgz
         -rw-r--r-- 2  52K Jul 24 16:56 ppa.itb
         drwxr-xr-x 3   60 Jul 24 16:56 rcw
         -rw-r--r-- 2 2.0K Jul 24 16:58 startup-factory.img
         -rw-r--r-- 2   17 Jul 24 16:58 startup-factory.img.version
         -rw-r--r-- 2 3.1K Jul 24 16:58 startup.img
         -rw-r--r-- 2   17 Jul 24 16:58 startup.img.version
         -rw-r--r-- 2 1012 Jul 24 17:04 tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtbo
         -rw-r--r-- 2 106M Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.ext4.gz
         -rw-r--r-- 2 118K Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.manifest
         -rw-r--r-- 2 115M Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.squashfs-lzo
         -rw-r--r-- 2 106M Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.tar.gz
         -rw-r--r-- 2   24 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.version
         -rw-r--r-- 2 375K Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.testdata.json
         -rw-r--r-- 2 5.8K Jul 24 17:03 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2 169M Jul 24 17:06 tanowrt-image-full-swu-factory-ls1028ardb-emmc-20220724135405.sdcard.img
         -rw-r--r-- 2 1.4K Jul 24 17:03 tanowrt-image-full-swu-factory-sdimage-ls1028ardb-swu-factory.wks
         -rw-r--r-- 2 133M Jul 24 17:05 tanowrt-image-full-swu-ls1028ardb-emmc-20220724135405.swu
         -rw-r--r-- 2 8.3M Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.cpio.gz
         -rw-r--r-- 2 3.0K Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.manifest
         -rw-r--r-- 2   24 Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.version
         -rw-r--r-- 2 382K Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.testdata.json
         -rw-r--r-- 2 667K Jul 24 16:56 u-boot-emmc-defconfig-2018.03-r0.nxp1.bin
         -rw-r--r-- 2  297 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2 8.0K Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1.bin
         -rw-r--r-- 2  297 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2 8.0K Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1.bin
         -rw-r--r-- 2 667K Jul 24 16:56 u-boot-sdcard-defconfig-2018.03-r0.nxp1.bin

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/ls1028ardb-emmc]$ ls -gGh
         total 1010M
         drwxr-xr-x 2   60 Jul 24 16:56 dp
         lrwxrwxrwx 2   72 Jul 24 17:04 fitImage -> fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  14M Jul 24 17:04 fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  17M Jul 24 17:04 fitImage-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc.ext4
         -rw-r--r-- 2 2.4K Jul 24 17:04 fitImage-its--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.its
         lrwxrwxrwx 2   76 Jul 24 17:04 fitImage-its-ls1028ardb-emmc -> fitImage-its--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.its
         -rw-r--r-- 2 3.1K Jul 24 17:04 fitImage-its-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.its
         lrwxrwxrwx 2  128 Jul 24 17:04 fitImage-its-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-ls1028ardb-emmc -> fitImage-its-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.its
         -rw-r--r-- 2  14M Jul 24 17:04 fitImage-linux.bin--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         lrwxrwxrwx 2   82 Jul 24 17:04 fitImage-linux.bin-ls1028ardb-emmc -> fitImage-linux.bin--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         lrwxrwxrwx 2   72 Jul 24 17:04 fitImage-ls1028ardb-emmc.bin -> fitImage--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  17M Jul 24 17:04 fitImage-ls1028ardb-emmc.ext4
         -rw-r--r-- 2  27M Jul 24 17:04 fitImage-tanowrt-image-initramfs-swu-factory-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc.ext4
         -rw-r--r-- 2  22M Jul 24 17:04 fitImage-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  27M Jul 24 17:04 fitImage-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc.ext4
         lrwxrwxrwx 2  124 Jul 24 17:04 fitImage-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-ls1028ardb-emmc -> fitImage-tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  26K Jul 24 17:04 fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtb
         lrwxrwxrwx 2   79 Jul 24 17:04 fsl-ls1028a-rdb.dtb -> fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtb
         lrwxrwxrwx 2   79 Jul 24 17:04 fsl-ls1028a-rdb-ls1028ardb-emmc.dtb -> fsl-ls1028a-rdb--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtb
         lrwxrwxrwx 2   69 Jul 24 17:04 Image -> Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  35M Jul 24 17:04 Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  43M Jul 24 17:04 Image-5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc.ext4
         lrwxrwxrwx 2   69 Jul 24 17:04 Image-ls1028ardb-emmc.bin -> Image--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.bin
         -rw-r--r-- 2  43M Jul 24 17:04 Image-ls1028ardb-emmc.ext4
         lrwxrwxrwx 2   20 Jul 24 16:56 ls1028a-dp-fw.bin -> dp/ls1028a-dp-fw.bin
         -rw-r--r-- 2 161M Jul 24 17:04 modules--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.tgz
         lrwxrwxrwx 2   71 Jul 24 17:04 modules-ls1028ardb-emmc.tgz -> modules--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.tgz
         -rw-r--r-- 2  52K Jul 24 16:56 ppa.itb
         drwxr-xr-x 3   60 Jul 24 16:56 rcw
         lrwxrwxrwx 2   50 Jul 24 16:56 rcw.bin -> rcw/ls1028ardb/R_SQPH_0x85be/rcw_1300_emmcboot.bin
         -rw-r--r-- 2 2.0K Jul 24 16:58 startup-factory.img
         -rw-r--r-- 2   17 Jul 24 16:58 startup-factory.img.version
         lrwxrwxrwx 2   19 Jul 24 16:58 startup-factory-ls1028ardb-emmc.img -> startup-factory.img
         lrwxrwxrwx 2   27 Jul 24 16:58 startup-factory-ls1028ardb-emmc.img.version -> startup-factory.img.version
         -rw-r--r-- 2 3.1K Jul 24 16:58 startup.img
         -rw-r--r-- 2   17 Jul 24 16:58 startup.img.version
         lrwxrwxrwx 2   11 Jul 24 16:58 startup-ls1028ardb-emmc.img -> startup.img
         lrwxrwxrwx 2   19 Jul 24 16:58 startup-ls1028ardb-emmc.img.version -> startup.img.version
         -rw-r--r-- 2 1012 Jul 24 17:04 tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtbo
         lrwxrwxrwx 2   78 Jul 24 17:04 tano-mkb004lw.dtbo -> tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtbo
         lrwxrwxrwx 2   78 Jul 24 17:04 tano-mkb004lw-ls1028ardb-emmc.dtbo -> tano-mkb004lw--5.4.3-rt1-tano0.2.12.20.0.7-ls1028ardb-emmc-20220724135405.dtbo
         -rw-r--r-- 2 106M Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.ext4.gz
         -rw-r--r-- 2 118K Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.manifest
         -rw-r--r-- 2 115M Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.squashfs-lzo
         -rw-r--r-- 2 106M Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.tar.gz
         -rw-r--r-- 2   24 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.version
         -rw-r--r-- 2 375K Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc-20220724135405.testdata.json
         lrwxrwxrwx 2   64 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc.ext4.gz -> tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.ext4.gz
         lrwxrwxrwx 2   65 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc.manifest -> tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.manifest
         lrwxrwxrwx 2   69 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc.squashfs-lzo -> tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.squashfs-lzo
         lrwxrwxrwx 2   63 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc.tar.gz -> tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.tar.gz
         lrwxrwxrwx 2   63 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc.testdata.json -> tanowrt-image-full-ls1028ardb-emmc-20220724135405.testdata.json
         lrwxrwxrwx 2   64 Jul 24 17:05 tanowrt-image-full-ls1028ardb-emmc.version -> tanowrt-image-full-ls1028ardb-emmc-20220724135405.rootfs.version
         -rw-r--r-- 2 5.8K Jul 24 17:03 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2 169M Jul 24 17:06 tanowrt-image-full-swu-factory-ls1028ardb-emmc-20220724135405.sdcard.img
         lrwxrwxrwx 2   72 Jul 24 17:06 tanowrt-image-full-swu-factory-ls1028ardb-emmc.sdcard.img -> tanowrt-image-full-swu-factory-ls1028ardb-emmc-20220724135405.sdcard.img
         -rw-r--r-- 2 1.4K Jul 24 17:03 tanowrt-image-full-swu-factory-sdimage-ls1028ardb-swu-factory.wks
         -rw-r--r-- 2 133M Jul 24 17:05 tanowrt-image-full-swu-ls1028ardb-emmc-20220724135405.swu
         lrwxrwxrwx 2   57 Jul 24 17:05 tanowrt-image-full-swu-ls1028ardb-emmc.swu -> tanowrt-image-full-swu-ls1028ardb-emmc-20220724135405.swu
         -rw-r--r-- 2 8.3M Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.cpio.gz
         -rw-r--r-- 2 3.0K Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.manifest
         -rw-r--r-- 2   24 Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.version
         -rw-r--r-- 2 382K Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.testdata.json
         lrwxrwxrwx 2   81 Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc.cpio.gz -> tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.cpio.gz
         lrwxrwxrwx 2   82 Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc.manifest -> tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.manifest
         lrwxrwxrwx 2   80 Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc.testdata.json -> tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.testdata.json
         lrwxrwxrwx 2   81 Jul 24 17:04 tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc.version -> tanowrt-image-initramfs-swu-factory-ls1028ardb-emmc-20220724135405.rootfs.version
         -rw-r--r-- 2 667K Jul 24 16:56 u-boot-emmc-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   65 Jul 24 16:56 u-boot-initial-env-emmc-defconfig -> u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1
         lrwxrwxrwx 2   69 Jul 24 16:56 u-boot-initial-env-emmc-defconfig.bin -> u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   65 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig -> u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2  297 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2 8.0K Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   69 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig.bin -> u-boot-initial-env-ls1028ardb-emmc-emmc-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   67 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig -> u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2  297 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1
         -rw-r--r-- 2 8.0K Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   71 Jul 24 16:56 u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig.bin -> u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   67 Jul 24 16:56 u-boot-initial-env-sdcard-defconfig -> u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1
         lrwxrwxrwx 2   71 Jul 24 16:56 u-boot-initial-env-sdcard-defconfig.bin -> u-boot-initial-env-ls1028ardb-emmc-sdcard-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   41 Jul 24 16:56 u-boot-ls1028ardb-emmc.bin -> u-boot-emmc-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   41 Jul 24 16:56 u-boot-ls1028ardb-emmc.bin-emmc-defconfig -> u-boot-emmc-defconfig-2018.03-r0.nxp1.bin
         lrwxrwxrwx 2   43 Jul 24 16:56 u-boot-ls1028ardb-emmc.bin-sdcard-defconfig -> u-boot-sdcard-defconfig-2018.03-r0.nxp1.bin
         -rw-r--r-- 2 667K Jul 24 16:56 u-boot-sdcard-defconfig-2018.03-r0.nxp1.bin


.. _sec-ls1028ardb-flash:

Writing Images
==============

.. _sec-ls1028ardb-flash-sd:

Writing Image to SD Card
-----------------------------

No special information about writing images to microSD card
for NXP LS1028A RDB board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing factory installation image for the ``ls1028ardb-emmc.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-swu-factory-ls1028ardb-emmc.sdcard.img of=/dev/mmcblk1

Writing bootable card image for the ``ls1028ardb-sd.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-ls1028ardb-sd.sdcard.img of=/dev/mmcblk1


.. _sec-ls1028ardb-flash-emmc:

Writing Image to eMMC Flash
---------------------------

For the initial flashing of the internal eMMC memory it is recommended to use
the special image of the initial factory installation. If you choose a build target
(see :ref:`sec-ls1028ardb-targets` for details) that assumes using the
factory installation image for the initial flashing of the
device, a factory installation image (:file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`)
will be automatically generated during the build process
(see :ref:`sec-ls1028ardb-build`).
To write the factory installation image to a SD card, follow the instructions
from :ref:`sec-writing-sd-or-usb` section.

When you boot device from the prepaired SD card with factory installation image the installation
of TanoWrt to the internal eMMC flash memory will be done automatically. The detailed
installation log is available on the :ref:`debug UART <sec-ls1028ardb-serial>`.
After the installation is complete, the board will reboots automatically. When the
device is rebooted, the installed system will be booted from the internal eMMC flash memory.

.. caution:: Be aware that during the installation all existing data on the internal eMMC
             flash memory will be permanently lost.


.. _sec-ls1028ardb-booting:

Booting and Running
===================

Booting from SD Card
--------------------

1. Power off board
2. Change boot source to SDHC1 (SD Card). See :ref:`sec-ls1028ardb-boot-sequence-selection` for details.
3. Insert the prepared SD card into the slot on the LS1028A RDB board
4. Power on board.
5. TanoWrt will be booting from SD card.
6. Log in to system using default :ref:`credentials <sec-access-creds>`.


Booting from Internal eMMC Flash
-----------------------------------

1. Power off board
2. Change boot source to SDHC2 (eMMC). See :ref:`sec-ls1028ardb-boot-sequence-selection` for details.
3. Power on board.
4. TanoWrt will be booting from internal eMMC.
5. Log in to system using default :ref:`credentials <sec-access-creds>`.


.. _sec-ls1028ardb-serial:

Serial Console
==============

Connect one end of the DB9F-to-USB Type A cable to the UART1 DB9 connector
available on chassis front panel (see :ref:`fig-ls1028ardb-front` figure) and the
other end of the cable to the USB port of the host machine. This connection
allows you to make a console connection between the board and host computer
to see the console output.

The default serial console settings for NXP LS1028A RDB for U-Boot and kernel
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


.. _sec-ls1028ardb-network-config:

Default Network Configuration
=============================

.. _fig-ls1028ardb-network:
.. figure:: images/ls1028ardb-network.jpg
   :width: 1000
   :class: with-border

   NXP LS1028A RDB Default Network Configuration

By default, network ports SWP0, SWP1, SWP2 and SWP3 are joined
into a bridge (``br-lan`` interface) with the :term:`RSTP` protocol enabled.
Bridge ``br-lan`` is in the LAN firewall zone. By default, the IP address
on the ``br-lan`` bridge is configured using a :term:`DHCP` client.

To see obtained IP configuration for LAN network use the following command:

.. code-block:: console

   [root@tanowrt ~]# ifstatus lan | jsonfilter -e '@["ipv4-address"][0].address'
   192.168.10.132

In this example, the device got the IP address 192.168.10.132 via :term:`DHCP`
on LAN interface (bridge ``br-lan``).

The network port MAC0 (interface ``eno0``) is a separate network interface
included in the WAN firewall zone with enabled translation (NAT) from LAN zone.
The IP address of the ``eno0`` interface is also configured with a :term:`DHCP` client.
A firewall with blocking rules for incoming traffic is enabled on the ``eno0``
interface. Therefore, there is no access to the web configuration interface
through this interface.

To see obtained IP configuration for WAN network use the following command:

.. code-block:: console

   [root@tanowrt ~]# ifstatus wan | jsonfilter -e '@["ipv4-address"][0].address'
   10.10.0.168

In this example, the device got the IP address 10.10.0.168 via :term:`DHCP`
on the WAN interface (``eno0``).

Ethernet ports SWP0 (``swp0``), SWP1 (``swp1``), SWP2 (``swp2``), SWP3 (``swp3``)
and MAC0 (``eno0``) have enabled :term:`LLDP` by default.

.. _sec-ls1028ardb-webui:


Web User Interface
=============================

The WebUI can be accessed via any Ethernet port bridged to LAN network
through HTTP(s) protocol. You must see something like this in browser:

.. _fig-ls1028ardb-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-ls1028ardb-luci-status:
.. figure:: images/ls1028ardb-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-ls1028ardb-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-ls1028ardb-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


References
==========

1. https://www.nxp.com/design/qoriq-developer-resources/ls1028a-development-board:LS1028ARDB

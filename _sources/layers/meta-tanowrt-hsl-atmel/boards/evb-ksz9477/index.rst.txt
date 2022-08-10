.. SPDX-License-Identifier: MIT

.. _machine-evb-ksz9477:

*********************
Microchip EVB-KSZ9477
*********************

KSZ9477 Managed Switch Evaluation Kit with SAMA5D36 MPU (EVB-KSZ9477).

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-evb-ksz9477-board:

Board Overview
==============

The EVB-KSZ9477 evaluation board features the KSZ9477 seven-port managed gigabit
Ethernet switch. It has five 10BASE-Te/100BASE-TX/1000BASE-T physical layer
transceivers (PHYs) and associated MAC units, one SGMII and one RGMII interfaces.
Ports 1 through 5, with integrated MAC and PHY are connected to RJ45 Ethernet jacks
with integrated magnetics. The SGMII interface is connected to a small form-factor
pluggable (SFP) transceiver receptacle whereas, the RGMII interface is connected
to the SAMA5D36A embedded microprocessor (MPU).

The board provides 2 Gbit (256 MiB) DDR2 SDRAM and 2 Gbit (256 MiB) of NAND flash memory.

.. _fig-evb-ksz9477:
.. figure:: images/evb-ksz9477.jpg
   :width: 1000
   :class: with-border

   EVB-KSZ9477 Interfaces and Connectors


Block Diagram
-------------

.. _fig-evb-ksz9477-block-diagram:
.. figure:: images/evb-ksz9477-block-diagram.png
   :class: with-border
   :width: 400

   EVB-KSZ9477 Block Diagram


Specification
-------------

.. table:: EVB-KSZ9477 Specification

   +--------------+------------------------------------------------------------------------------------+
   | Model        | EVB-KSZ9477                                                                        |
   +==============+====================================================================================+
   | Processor    || Atmel SAMA5D36 (324-ball BGA package)                                             |
   |              || 1 |times| ARM Cortex-A5 with v7-A Thumb®-2 instruction set                        |
   |              || frequency up to 536 MHz                                                           |
   +--------------+------------------------------------------------------------------------------------+
   | Memory       || 256 MiB DDR2 SDRAM                                                                |
   +--------------+------------------------------------------------------------------------------------+
   | Storage      || Full-size SD card slot                                                            |
   |              || 256 MiB NAND flash                                                                |
   +--------------+------------------------------------------------------------------------------------+
   | Debug        || JTAG interface to program and debug the MPU (20-pin header)                       |
   |              || 1 |times| Serial communication (6-pin header)                                     |
   +--------------+------------------------------------------------------------------------------------+
   | USB          || 1 |times| USB 2.0 Micro-AB (Device mode)                                          |
   +--------------+------------------------------------------------------------------------------------+
   | Ethernet     || 5 |times| Gigabit Ethernet PHYs                                                   |
   |              || 1 |times| SGMII Gigabit Ethernet Port (Fiber SFP)                                 |
   +--------------+------------------------------------------------------------------------------------+
   | Other        || 1 |times| SPI/I2C Aardvark Header (10-pin)                                        |
   | Connectors   || 1 |times| KSZ9477 output probe points (8-pin header)                              |
   |              || 1 |times| I/O connector for the MPU (6-pin header)                                |
   |              || 1 |times| GPIO for the MPU (16-pin header)                                        |
   |              || 1 |times| LCD Connector for the MPU (50-pin FPC Connector)                        |
   |              || 8 |times| KSZ9477 Strap DIP-Switches                                              |
   |              || 1 |times| 3-pin board power source selection (5V input jack or USB)               |
   +--------------+------------------------------------------------------------------------------------+
   | Power Source | DC Jack or USB                                                                     |
   +--------------+------------------------------------------------------------------------------------+
   | Power        | 5V/2A DC input jack                                                                |
   +--------------+------------------------------------------------------------------------------------+


.. _sec-evb-ksz9477-targets:

Build Targets
=============

.. _sec-evb-ksz9477-machines:

Machines
--------

.. _table-evb-ksz9477-machines:
.. table:: Supported Machines

   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_      | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +==================+==============================+==========================+====================================+========================+==========================+
   | EVB-KSZ9477      | ``evb-ksz9477-sd.yml``       | ``evb-ksz9477-sd``       | ``tanowrt-image-full-swu``         | SD card                | |ndash|                  |
   |                  +------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   |                  | ``evb-ksz9477-nand.yml``     | ``evb-ksz9477-nand``     | ``tanowrt-image-full-swu-factory`` | internal NAND          | SD card                  |
   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-evb-ksz9477-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-evb-ksz9477-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-evb-ksz9477-images:

Images
------

.. _table-evb-ksz9477-images:
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
   |                           | ``tanowrt-image-full-swu-factory`` | ``evb-ksz9477-nand.yml``   | Factory installation image for standard TanoWrt     |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-full``                              |
   |                           |                                    |                            | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                            | as dependencies.                                    |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-evb-ksz9477-build` section).


.. _sec-evb-ksz9477-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-evb-ksz9477-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-evb-ksz9477-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-evb-ksz9477-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for EVB-KSZ9477 Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For SD Card

.. code-block:: console

   $ kas build targets/kas/evb-ksz9477-sd.yml

Default images will be produced to boot and run from the SD
card on the EVB-KSZ9477 target board.

.. rubric:: For Internal NAND Flash

.. code-block:: console

   $ kas build targets/kas/evb-ksz9477-nand.yml

An initial factory installation image will be generated,
intended to run from the SD card. The installer image
will install the default image to the internal NAND flash
memory and further the EVB-KSZ9477 board will boot
and run from the NAND flash memory.


.. _sec-evb-ksz9477-partitioning:

Partitioning Layouts
====================

SD Card
-------

The partitioning and data layout of the SD card image for the EVB-KSZ9477 board
are shown in the figure below.

.. _fig-evb-ksz9477-layout-sd:
.. figure:: images/evb-ksz9477-layout-sd.svg
   :width: 1000

   EVB-KSZ9477 Partitions Layout for SD Card

NAND
----

The partitioning and data layout of the NAND image for the EVB-KSZ9477 board
are shown in the figure below.

.. _fig-evb-ksz9477-layout-nand:
.. figure:: images/evb-ksz9477-layout-nand.svg
   :width: 1000

   EVB-KSZ9477 Partitions Layout for NAND


.. _sec-evb-ksz9477-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-evb-ksz9477-artifacts` for a description of some common (not all) build artifacts.

.. _table-evb-ksz9477-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | Artifact                                                            | Target(s)                     | Description                                                          |
   +=====================================================================+===============================+======================================================================+
   | .. centered:: Bootloader (AT91Bootstrap)                                                                                                                                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`at91bootstrap.bin-sdcard`                                    | *All*                         | AT91Bootstrap binary for SD card images.                             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`at91bootstrap.bin-nand`                                      | ``evb-ksz9477-nand.yml``      | AT91Bootstrap binary for NAND flash images.                          |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Bootloader (U-Boot)                                                                                                                                          |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`startup-<MACHINE>.img`                                       | *All*                         | U-Boot startup script.                                               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`startup-factory-<MACHINE>.img`                               | ``evb-ksz9477-nand.yml``      | U-Boot startup script for factory installation image.                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdcard`                         | *All*                         | U-Boot initial environment image for SD card image.                  |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-nand`                           | ``evb-ksz9477-nand.yml``      | U-Boot initial environment image for internal NAND flash.            |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdcard`                                 | *All*                         | U-Boot binary image for booting from SD card.                        |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-nand`                                   | ``evb-ksz9477-nand.yml``      | U-Boot binary image for booting from internal NAND flash.            |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.bin`                                      | *All*                         | Flattened Image Tree (FIT) image with Linux kernel                   |
   |                                                                     |                               | and Device Tree Blobs (DTB).                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.ext4`                                     | *All*                         | FIT image packed into an ext4 file system image.                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`at91-evb-ksz9477.dtb`                                        | *All*                         | Target Device Tree Blob (DTB).                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>.ext4` | ``evb-ksz9477-nand.yml``      | FIT image for SWU factory installation image with                    |
   |                                                                     |                               | initramfs image.                                                     |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`                         | ``evb-ksz9477-sd.yml``        | SD card image including all required partitions for booting          |
   |                                                                     |                               | and running the system. This image is ready to be written            |
   |                                                                     |                               | to the SD card using the :command:`dd` utility or similar            |
   |                                                                     |                               | (see :ref:`sec-evb-ksz9477-flash`).                                  |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`             | ``evb-ksz9477-nand.yml``      | SD card factory installation image. This image is ready              |
   |                                                                     |                               | to be written to the SD card using the :command:`dd` utility         |
   |                                                                     |                               | or similar (see :ref:`sec-evb-ksz9477-flash`).                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`tanowrt-image-initramfs-swu-factory-<MACHINE>.cpio.gz`       | ``evb-ksz9477-nand.yml``      | Root filesystem initramfs image for factory installtion              |
   |                                                                     |                               | image. This image is included in                                     |
   |                                                                     |                               | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>.ext4`. |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`                       | *All*                         | Root filesystem image (squashfs with LZO compression).               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                            | *All*                         | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-evb-ksz9477-targets_>`__. ``<rootfs-image>`` is replaced by
          the actual read-only root filesystem `image <sec-evb-ksz9477-images_>`__ name.

For example, below is the lists of artifacts produced by the ``evb-ksz9477-nand.yml``
and ``evb-ksz9477-sd.yml`` target builds. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

Build Artifacts Listings for ``evb-ksz9477-sd.yml`` Target
----------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9477-sd]$ ls -gGh | grep -v -e "^l"
         total 135M
         -rwxr-xr-x 2  20K Jul 27 17:35 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  50K Jul 27 17:37 at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         -rw-r--r-- 2  50K Jul 27 17:37 at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         -rw-r--r-- 2 4.6M Jul 27 17:37 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.bin
         -rw-r--r-- 2 6.5M Jul 27 17:37 fitImage-4.19.78+gitAUTOINC+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd.ext4
         -rw-r--r-- 2 6.5M Jul 27 17:37 fitImage-evb-ksz9477-sd.ext4
         -rw-r--r-- 2 2.4K Jul 27 17:37 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.its
         -rw-r--r-- 2 4.5M Jul 27 17:37 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.bin
         -rw-r--r-- 2 2.0M Jul 27 17:37 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         -rw-r--r-- 2  890 Jul 27 17:35 startup-factory.img
         -rw-r--r-- 2   19 Jul 27 17:35 startup-factory.img.version
         -rw-r--r-- 2 2.1K Jul 27 17:35 startup.img
         -rw-r--r-- 2   19 Jul 27 17:35 startup.img.version
         -rw-r--r-- 2 5.3K Jul 27 17:38 tanowrt-image-full.env
         -rw-r--r-- 2  63K Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.manifest
         -rw-r--r-- 2 940M Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.sdcard.img
         -rw-r--r-- 2  24M Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.version
         -rw-r--r-- 2 374K Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.testdata.json
         -rw-r--r-- 2 2.6K Jul 27 17:38 tanowrt-image-full-sdimage-at91-swu-a-b.wks
         -rw-r--r-- 2  31M Jul 27 17:38 tanowrt-image-full-swu-evb-ksz9477-sd-20220727143416.swu
         -rw-r--r-- 2  192 Jul 27 17:35 u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:35 u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 17:35 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9477-sd]$ ls -gGh
         total 135M
         -rwxr-xr-x 2  20K Jul 27 17:35 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  50K Jul 27 17:37 at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         lrwxrwxrwx 2   98 Jul 27 17:37 at91-evb-ksz9477.dtb -> at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         lrwxrwxrwx 2   98 Jul 27 17:37 at91-evb-ksz9477-evb-ksz9477-sd.dtb -> at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         -rw-r--r-- 2  50K Jul 27 17:37 at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         lrwxrwxrwx 2  102 Jul 27 17:37 at91-evb-ksz9477-sfp.dtb -> at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         lrwxrwxrwx 2  102 Jul 27 17:37 at91-evb-ksz9477-sfp-evb-ksz9477-sd.dtb -> at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.dtb
         lrwxrwxrwx 2   24 Jul 27 17:35 boot.bin-sdcard -> at91bootstrap.bin-sdcard
         lrwxrwxrwx 2   90 Jul 27 17:37 fitImage -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.bin
         -rw-r--r-- 2 4.6M Jul 27 17:37 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.bin
         -rw-r--r-- 2 6.5M Jul 27 17:37 fitImage-4.19.78+gitAUTOINC+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd.ext4
         lrwxrwxrwx 2   90 Jul 27 17:37 fitImage-evb-ksz9477-sd.bin -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.bin
         -rw-r--r-- 2 6.5M Jul 27 17:37 fitImage-evb-ksz9477-sd.ext4
         -rw-r--r-- 2 2.4K Jul 27 17:37 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.its
         lrwxrwxrwx 2   94 Jul 27 17:37 fitImage-its-evb-ksz9477-sd -> fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.its
         -rw-r--r-- 2 4.5M Jul 27 17:37 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.bin
         lrwxrwxrwx 2  100 Jul 27 17:37 fitImage-linux.bin-evb-ksz9477-sd -> fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.bin
         -rw-r--r-- 2 2.0M Jul 27 17:37 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.tgz
         lrwxrwxrwx 2   89 Jul 27 17:37 modules-evb-ksz9477-sd.tgz -> modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-sd-20220727143416.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         lrwxrwxrwx 2   11 Jul 27 17:35 startup-evb-ksz9477-sd.img -> startup.img
         lrwxrwxrwx 2   19 Jul 27 17:35 startup-evb-ksz9477-sd.img.version -> startup.img.version
         lrwxrwxrwx 2   19 Jul 27 17:35 startup-factory-evb-ksz9477-sd.img -> startup-factory.img
         lrwxrwxrwx 2   27 Jul 27 17:35 startup-factory-evb-ksz9477-sd.img.version -> startup-factory.img.version
         -rw-r--r-- 2  890 Jul 27 17:35 startup-factory.img
         -rw-r--r-- 2   19 Jul 27 17:35 startup-factory.img.version
         -rw-r--r-- 2 2.1K Jul 27 17:35 startup.img
         -rw-r--r-- 2   19 Jul 27 17:35 startup.img.version
         -rw-r--r-- 2 5.3K Jul 27 17:38 tanowrt-image-full.env
         -rw-r--r-- 2  63K Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.manifest
         -rw-r--r-- 2 940M Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.sdcard.img
         -rw-r--r-- 2  24M Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.version
         -rw-r--r-- 2 374K Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd-20220727143416.testdata.json
         lrwxrwxrwx 2   64 Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd.manifest -> tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.manifest
         lrwxrwxrwx 2   66 Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd.sdcard.img -> tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.sdcard.img
         lrwxrwxrwx 2   68 Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd.squashfs-lzo -> tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.squashfs-lzo
         lrwxrwxrwx 2   62 Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd.testdata.json -> tanowrt-image-full-evb-ksz9477-sd-20220727143416.testdata.json
         lrwxrwxrwx 2   63 Jul 27 17:38 tanowrt-image-full-evb-ksz9477-sd.version -> tanowrt-image-full-evb-ksz9477-sd-20220727143416.rootfs.version
         -rw-r--r-- 2 2.6K Jul 27 17:38 tanowrt-image-full-sdimage-at91-swu-a-b.wks
         -rw-r--r-- 2  31M Jul 27 17:38 tanowrt-image-full-swu-evb-ksz9477-sd-20220727143416.swu
         lrwxrwxrwx 2   56 Jul 27 17:38 tanowrt-image-full-swu-evb-ksz9477-sd.swu -> tanowrt-image-full-swu-evb-ksz9477-sd-20220727143416.swu
         lrwxrwxrwx 2   61 Jul 27 17:35 u-boot.bin -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:35 u-boot.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:35 u-boot-evb-ksz9477-sd.bin -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:35 u-boot-evb-ksz9477-sd.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 17:35 u-boot-initial-env-evb-ksz9477-sd-sdcard -> u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 17:35 u-boot-initial-env-evb-ksz9477-sd-sdcard.bin -> u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  192 Jul 27 17:35 u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:35 u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 17:35 u-boot-initial-env-sdcard -> u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 17:35 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-evb-ksz9477-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 17:35 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin


Build Artifacts Listings for ``evb-ksz9477-nand.yml`` Target
------------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9477-nand]$ ls -gGh | grep -v -e "^l"
         total 123M
         -rwxr-xr-x 2  15K Jul 27 16:41 at91bootstrap.bin-nand
         -rw-r--r-- 2  16K Jul 27 16:41 at91bootstrap.bin-nand-pmecc
         -rwxr-xr-x 2  20K Jul 27 16:41 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  20K Jul 27 16:41 at91bootstrap.bin-sdcard-pmecc
         -rw-r--r-- 2  50K Jul 27 16:45 at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         -rw-r--r-- 2  50K Jul 27 16:45 at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         -rw-r--r-- 2 4.6M Jul 27 16:45 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         -rw-r--r-- 2 2.4K Jul 27 16:45 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.its
         -rw-r--r-- 2 3.1K Jul 27 16:45 fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.its
         -rw-r--r-- 2 4.5M Jul 27 16:45 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         -rw-r--r-- 2  12M Jul 27 16:45 fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         -rw-r--r-- 2 2.0M Jul 27 16:45 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         -rw-r--r-- 2  965 Jul 27 16:42 startup-factory.img
         -rw-r--r-- 2   19 Jul 27 16:42 startup-factory.img.version
         -rw-r--r-- 2 2.3K Jul 27 16:42 startup.img
         -rw-r--r-- 2   19 Jul 27 16:42 startup.img.version
         -rw-r--r-- 2  64K Jul 27 16:45 tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.manifest
         -rw-r--r-- 2  23M Jul 27 16:46 tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 16:46 tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.version
         -rw-r--r-- 2 374K Jul 27 16:45 tanowrt-image-full-evb-ksz9477-nand-20220727132751.testdata.json
         -rw-r--r-- 2  29M Jul 27 16:46 tanowrt-image-full-swu-evb-ksz9477-nand-20220727132751.swu
         -rw-r--r-- 2 5.9K Jul 27 16:32 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  42M Jul 27 16:46 tanowrt-image-full-swu-factory-evb-ksz9477-nand-20220727132751.sdcard.img
         -rw-r--r-- 2  778 Jul 27 16:32 tanowrt-image-full-swu-factory-sdimage-at91-swu-factory.wks
         -rw-r--r-- 2 6.6M Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.cpio.gz
         -rw-r--r-- 2 3.3K Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.manifest
         -rw-r--r-- 2   24 Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.version
         -rw-r--r-- 2 381K Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.testdata.json
         -rw-r--r-- 2  187 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  192 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 727K Jul 27 16:41 u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 16:41 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9477-nand]$ ls -gGh
         total 123M
         -rwxr-xr-x 2  15K Jul 27 16:41 at91bootstrap.bin-nand
         -rw-r--r-- 2  16K Jul 27 16:41 at91bootstrap.bin-nand-pmecc
         -rwxr-xr-x 2  20K Jul 27 16:41 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  20K Jul 27 16:41 at91bootstrap.bin-sdcard-pmecc
         -rw-r--r-- 2  50K Jul 27 16:45 at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         lrwxrwxrwx 2  100 Jul 27 16:45 at91-evb-ksz9477.dtb -> at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         lrwxrwxrwx 2  100 Jul 27 16:45 at91-evb-ksz9477-evb-ksz9477-nand.dtb -> at91-evb-ksz9477--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         -rw-r--r-- 2  50K Jul 27 16:45 at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         lrwxrwxrwx 2  104 Jul 27 16:45 at91-evb-ksz9477-sfp.dtb -> at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         lrwxrwxrwx 2  104 Jul 27 16:45 at91-evb-ksz9477-sfp-evb-ksz9477-nand.dtb -> at91-evb-ksz9477-sfp--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.dtb
         lrwxrwxrwx 2   24 Jul 27 16:41 boot.bin-sdcard -> at91bootstrap.bin-sdcard
         lrwxrwxrwx 2   92 Jul 27 16:45 fitImage -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         -rw-r--r-- 2 4.6M Jul 27 16:45 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         lrwxrwxrwx 2   92 Jul 27 16:45 fitImage-evb-ksz9477-nand.bin -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         -rw-r--r-- 2 2.4K Jul 27 16:45 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.its
         lrwxrwxrwx 2   96 Jul 27 16:45 fitImage-its-evb-ksz9477-nand -> fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.its
         -rw-r--r-- 2 3.1K Jul 27 16:45 fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.its
         lrwxrwxrwx 2  149 Jul 27 16:45 fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-evb-ksz9477-nand -> fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.its
         -rw-r--r-- 2 4.5M Jul 27 16:45 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         lrwxrwxrwx 2  102 Jul 27 16:45 fitImage-linux.bin-evb-ksz9477-nand -> fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         -rw-r--r-- 2  12M Jul 27 16:45 fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         lrwxrwxrwx 2  145 Jul 27 16:45 fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-evb-ksz9477-nand -> fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.bin
         -rw-r--r-- 2 2.0M Jul 27 16:45 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.tgz
         lrwxrwxrwx 2   91 Jul 27 16:45 modules-evb-ksz9477-nand.tgz -> modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9477-nand-20220727132751.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         lrwxrwxrwx 2   11 Jul 27 16:42 startup-evb-ksz9477-nand.img -> startup.img
         lrwxrwxrwx 2   19 Jul 27 16:42 startup-evb-ksz9477-nand.img.version -> startup.img.version
         lrwxrwxrwx 2   19 Jul 27 16:42 startup-factory-evb-ksz9477-nand.img -> startup-factory.img
         lrwxrwxrwx 2   27 Jul 27 16:42 startup-factory-evb-ksz9477-nand.img.version -> startup-factory.img.version
         -rw-r--r-- 2  965 Jul 27 16:42 startup-factory.img
         -rw-r--r-- 2   19 Jul 27 16:42 startup-factory.img.version
         -rw-r--r-- 2 2.3K Jul 27 16:42 startup.img
         -rw-r--r-- 2   19 Jul 27 16:42 startup.img.version
         -rw-r--r-- 2  64K Jul 27 16:45 tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.manifest
         -rw-r--r-- 2  23M Jul 27 16:46 tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 16:46 tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.version
         -rw-r--r-- 2 374K Jul 27 16:45 tanowrt-image-full-evb-ksz9477-nand-20220727132751.testdata.json
         lrwxrwxrwx 2   66 Jul 27 16:45 tanowrt-image-full-evb-ksz9477-nand.manifest -> tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.manifest
         lrwxrwxrwx 2   70 Jul 27 16:46 tanowrt-image-full-evb-ksz9477-nand.squashfs-lzo -> tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.squashfs-lzo
         lrwxrwxrwx 2   64 Jul 27 16:45 tanowrt-image-full-evb-ksz9477-nand.testdata.json -> tanowrt-image-full-evb-ksz9477-nand-20220727132751.testdata.json
         lrwxrwxrwx 2   65 Jul 27 16:46 tanowrt-image-full-evb-ksz9477-nand.version -> tanowrt-image-full-evb-ksz9477-nand-20220727132751.rootfs.version
         -rw-r--r-- 2  29M Jul 27 16:46 tanowrt-image-full-swu-evb-ksz9477-nand-20220727132751.swu
         lrwxrwxrwx 2   58 Jul 27 16:46 tanowrt-image-full-swu-evb-ksz9477-nand.swu -> tanowrt-image-full-swu-evb-ksz9477-nand-20220727132751.swu
         -rw-r--r-- 2 5.9K Jul 27 16:32 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  42M Jul 27 16:46 tanowrt-image-full-swu-factory-evb-ksz9477-nand-20220727132751.sdcard.img
         lrwxrwxrwx 2   73 Jul 27 16:46 tanowrt-image-full-swu-factory-evb-ksz9477-nand.sdcard.img -> tanowrt-image-full-swu-factory-evb-ksz9477-nand-20220727132751.sdcard.img
         -rw-r--r-- 2  778 Jul 27 16:32 tanowrt-image-full-swu-factory-sdimage-at91-swu-factory.wks
         -rw-r--r-- 2 6.6M Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.cpio.gz
         -rw-r--r-- 2 3.3K Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.manifest
         -rw-r--r-- 2   24 Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.version
         -rw-r--r-- 2 381K Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.testdata.json
         lrwxrwxrwx 2   82 Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand.cpio.gz -> tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.cpio.gz
         lrwxrwxrwx 2   83 Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand.manifest -> tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.manifest
         lrwxrwxrwx 2   81 Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand.testdata.json -> tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.testdata.json
         lrwxrwxrwx 2   82 Jul 27 16:45 tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand.version -> tanowrt-image-initramfs-swu-factory-evb-ksz9477-nand-20220727132751.rootfs.version
         lrwxrwxrwx 2   59 Jul 27 16:41 u-boot.bin -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   59 Jul 27 16:41 u-boot.bin-nand -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 16:41 u-boot.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   59 Jul 27 16:41 u-boot-evb-ksz9477-nand.bin -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   59 Jul 27 16:41 u-boot-evb-ksz9477-nand.bin-nand -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 16:41 u-boot-evb-ksz9477-nand.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-nand -> u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-nand.bin -> u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  187 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   86 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-sdcard -> u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   90 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-sdcard.bin -> u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  192 Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 16:41 u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 16:41 u-boot-initial-env-nand -> u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 16:41 u-boot-initial-env-nand.bin -> u-boot-initial-env-evb-ksz9477-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   86 Jul 27 16:41 u-boot-initial-env-sdcard -> u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   90 Jul 27 16:41 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-evb-ksz9477-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 727K Jul 27 16:41 u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 16:41 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin


.. _sec-evb-ksz9477-flash:

Writing Images
==============

.. _sec-evb-ksz9477-flash-sd:

Writing Image to SD Card
-----------------------------

No special information about writing images to SD card
for EVB-KSZ9477 board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing factory installation image for the ``evb-ksz9477-nand.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-swu-factory-evb-ksz9477-nand.sdcard.img of=/dev/mmcblk1

Writing bootable card image for the ``evb-ksz9477-sd.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-evb-ksz9477-sd.sdcard.img of=/dev/mmcblk1


.. _sec-evb-ksz9477-flash-emmc:

Writing Image to NAND Flash
---------------------------

For the initial flashing of the internal NAND memory it is recommended to use
the special image of the initial factory installation. If you choose a build target
(see :ref:`sec-evb-ksz9477-targets` for details) that assumes using the
factory installation image for the initial flashing of the
device, a factory installation image (:file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`)
will be automatically generated during the build process
(see :ref:`sec-evb-ksz9477-build`).
To write the factory installation image to a SD card, follow the instructions
from :ref:`sec-writing-sd-or-usb` section.

When you boot device from the prepaired SD card with factory installation image the installation
of TanoWrt to the internal NAND flash memory will be done automatically. The detailed
installation log is available on the :ref:`debug UART <sec-evb-ksz9477-serial>`.
After the installation is complete, the board will power off automatically. You need
to remove SD card with the installation image and power on the board. When the
board boots up, the installed system will be booted from the internal NAND flash memory.

.. caution:: Be aware that during the installation all existing data on the internal NAND
             flash memory will be permanently lost.


.. _sec-evb-ksz9477-booting:

Booting and Running
===================

Configuring Boot Mode
---------------------

The EVB-KSZ9477 board does not have any switches or jumpers for selecting the boot source.
By default, booting is performed from the SD card. If booting from an SD card fails,
NAND flash memory is used for booting.

Hardware Configuration
----------------------

Ensure that the J13 (NAND Enable) jumper on EVB-KSZ9477 board is shorted (this is needed
for installing and running the TanoWrt from the internal NAND flash memory).

To avoid network loops while the bootloader is running and while the operating system
is booting (while the :term:`STP`/:term:`RSTP` support software is not yet running) it
is recommended to set the SW1:1 switch to the "ON" position. Switch SW1:1 is used for
automatic start of the switch with the default configuration when the power is turned on.

Booting from SD Card
-------------------------

1. Insert the SD card into the slot on the EVB-KSZ9477 board (power is off).
2. Power on board.
3. TanoWrt will be booting from SD card.
4. Log in to system using default :ref:`credentials <sec-access-creds>`.


.. _sec-evb-ksz9477-serial:

Serial Console
==============

Serial console on EVB-KSZ9477 board are available through 6-pin
serial communication header J10.
Connect the USB to TTL serial cable as described below.
Don't connect the VCC wire of the USB to TTL converted,
connect only TX, RX and GND wires.

+------------------------------+-----------------+
| EVB-KSZ9477 J10 6-pin Header | Signal          |
+==============================+=================+
| Pin 1                        | *Not used*      |
+------------------------------+-----------------+
| Pin 2                        | TX              |
+------------------------------+-----------------+
| Pin 3                        | RX              |
+------------------------------+-----------------+
| Pin 4                        | *Not connected* |
+------------------------------+-----------------+
| Pin 5                        | *Not used*      |
+------------------------------+-----------------+
| Pin 6                        | GND             |
+------------------------------+-----------------+

See :numref:`fig-evb-ksz9477` for example
connection USB to TTL converter with EVB-KSZ9477 board.

The default serial console settings for EVB-KSZ9477 for U-Boot and kernel
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


.. _sec-evb-ksz9477-network-config:

Default Network Configuration
=============================

.. _fig-evb-ksz9477-network:
.. figure:: images/evb-ksz9477-network.jpg

   EVB-KSZ9477 Default Network Configuration

By default, network ports 1, 2, 3, 4 and 6 are joined into a bridge
(``br-lan`` interface) with the :term:`RSTP` protocol enabled.
Bridge ``br-lan`` is in the LAN firewall zone. By default, the ``br-lan`` bridge
is configured with static IP address 192.168.0.1/24 with enabled :term:`DHCP` server.

The network port 5 (interface ``sw1p5``) is a separate network interface included
in the WAN firewall zone with enabled translation (NAT) from LAN zone. The IP address
of the ``sw1p5`` interface is also configured with a :term:`DHCP` client. A firewall
with blocking rules for incoming traffic is enabled on the ``sw1p5`` interface.
Therefore, there is no access to the web configuration interface through this interface.

To see obtained IP configuration for WAN network use the following command:

.. code-block:: console

   [root@tanowrt ~]# ifstatus wan | jsonfilter -e '@["ipv4-address"][0].address'
   10.0.1.65

Also you can connect to the board using USB RNDIS connection (``usb0`` interface).
RNDIS interface configured with static IP address 172.16.0.1/24 with
enabled :term:`DHCP` server.

All ethernet ports (from 1 to 6) have enabled :term:`LLDP` for Rx and Tx state by default.


.. _sec-evb-ksz9477-webui:

Web User Interface
==================

The WebUI can be accessed via any Ethernet port bridged to LAN network
(1, 2, 3, 4 or 6) or via USB RNDIS connection through HTTP(s) protocol.
You must see something like this in browser:

.. _fig-evb-ksz9477-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-evb-ksz9477-luci-status:
.. figure:: images/evb-ksz9477-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-evb-ksz9477-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-evb-ksz9477-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


Known Issues
============

+-------------+---------------------------+----------------------------------------------------------------------------------------------+
| Code        | Affected Target(s)        | Issue Description                                                                            |
+=============+===========================+==============================================================================================+
| |ndash|     || ``evb-ksz9477-sd.yml``   | SFP port (port 6) does not working with any kind of transceivers (``sw1p6`` interface).      |
|             || ``evb-ksz9477-nand.yml`` |                                                                                              |
+-------------+---------------------------+----------------------------------------------------------------------------------------------+

References
==========

1. https://www.microchip.com/DevelopmentTools/ProductDetails/PartNO/EVB-KSZ9477

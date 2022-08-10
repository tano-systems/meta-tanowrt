.. SPDX-License-Identifier: MIT

.. _machine-evb-ksz9563:

************************************
Microchip EVB-KSZ9563 on SAMA5D3 EDS
************************************

KSZ9563 Plug-in Evaluation Board (EVB-KSZ9563) on SAMA5D3 Ethernet Development System (DM320114)

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-evb-ksz9563-board:

Board Overview
==============

The EVB-KSZ9563 Evaluation Board is a plug-in card that interfaces directly with a mating
Microchip host processor or controller board, such as the SAMA5D3 Ethernet Development
System Board. It features the KSZ9563, a highly integrated networking device that incorporates
a layer-2+ managed Gigabit Ethernet switch, two 10BASE-T/100BASE-TX/1000BASE-T physical layer
transceivers (PHYs) and associated MAC units, and one MAC port configured as the RGMII interface.
The board's two PHY ports are connected to RJ45 Ethernet jacks with integrated magnetics,
and the RGMII MAC interface is brought out to a high-speed multi-pin (HS) connector.

Together, the EVB-KSZ9563 and SAMA5D3 Ethernet Development System (EDS) provide a highly-flexible
platform for evaluation of basic PHY/Switch features via static Control-Status Registers
(CSR's) and development of firmware for advanced MAC/Switch features that require interaction
with upper network layers (e.g., IEEE1588 PTP, AVB, and :term:`RSTP`/:term:`MSTP`).

The Microchip SAMA5D3 Ethernet Development System board (EDS) board is an MPU-based platform
for evaluating Ethernet switch and PHY products. Compatible switch and PHY evaluation boards
connect to the EDS board via either an RGMII connector or an RMII connector. These daughter
boards are available separately. The EDS board is not intended for stand-alone use and has
no Ethernet capabilities when no daughter board is connected.

.. _fig-evb-ksz9563:
.. figure:: images/evb-ksz9563.jpg
   :width: 1000
   :class: with-border

   EVB-KSZ9563 on SAMA5D3 EDS Interfaces and Connectors


Block Diagram
-------------

.. container:: flex

   .. _fig-evb-ksz9563-block-diagram:
   .. figure:: images/evb-ksz9563-block-diagram.png
      :class: with-border
      :width: 300

      EVB-KSZ9563 Block Diagram

   .. _fig-sama5d3-eds-block-diagram:
   .. figure:: images/sama5d3-eds-block-diagram.png
      :class: with-border
      :width: 300

      SAMA5D3 EDS Block Diagram

Photos
------

.. container:: flex

   .. _fig-evb-ksz9563-top:
   .. figure:: images/evb-ksz9563-top.jpg
      :class: with-border
      :width: 300

      EVB-KSZ9563 Top View

   .. _fig-evb-ksz9563-bottom:
   .. figure:: images/evb-ksz9563-bottom.jpg
      :class: with-border
      :width: 300

      EVB-KSZ9563 Bottom View

   .. _fig-sama5d3-eds-angled:
   .. figure:: images/sama5d3-eds-angled.jpg
      :class: with-border
      :width: 300

      SAMA5D3 EDS Angled View


Specification
-------------

.. table:: EVB-KSZ9563 on SAMA5D3 EDS Specification

   +---------------+------------------------------------------------------------------------------------+
   | Model         | EVB-KSZ9563 on SAMA5D3 EDS                                                         |
   +===============+====================================================================================+
   | Processor     || Atmel SAMA5D36 (324-ball BGA package)                                             |
   |               || 1 |times| ARM Cortex-A5 with v7-A Thumb®-2 instruction set                        |
   |               || frequency up to 536 MHz                                                           |
   +---------------+------------------------------------------------------------------------------------+
   | Memory        || 256 MiB DDR2 SDRAM                                                                |
   +---------------+------------------------------------------------------------------------------------+
   | Storage       || Full-size SD card slot                                                            |
   |               || 256 MiB NAND flash                                                                |
   +---------------+------------------------------------------------------------------------------------+
   | Debug         || JTAG interface to program and debug the MPU (20-pin header)                       |
   |               || 1 |times| Serial communication (6-pin header)                                     |
   +---------------+------------------------------------------------------------------------------------+
   | USB           || 2 |times| USB 2.0 Host                                                            |
   |               || 1 |times| Micro-AB USB Device                                                     |
   +---------------+------------------------------------------------------------------------------------+
   | Ethernet      || 1 |times| Gigabit MAC with RGMII interface                                        |
   | (SAMA5D3 EDS) || 1 |times| 10/100 Mbit MAC with RMII interface                                     |
   +---------------+------------------------------------------------------------------------------------+
   | Ethernet      || 2 |times| Gigabit Ethernet PHYs                                                   |
   | (EVB-KSZ9563) ||                                                                                   |
   +---------------+------------------------------------------------------------------------------------+
   | User          || 1 |times| Reset push button                                                       |
   | Interface     || LEDs for power, reset USB and NAND flash disable                                  |
   +---------------+------------------------------------------------------------------------------------+
   | Expansion     || 8 |times| 2 GPIO header with two-wire, SPI and audio                              |
   | Interface     || 1 |times| 6 GPIO header with USART                                                |
   +---------------+------------------------------------------------------------------------------------+
   | Power Source  | DC Jack                                                                            |
   +---------------+------------------------------------------------------------------------------------+
   | Power         | 12V/500mA DC input jack                                                            |
   +---------------+------------------------------------------------------------------------------------+


.. _sec-evb-ksz9563-targets:

Build Targets
=============

.. _sec-evb-ksz9563-machines:

Machines
--------

.. _table-evb-ksz9563-machines:
.. table:: Supported Machines

   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_      | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +==================+==============================+==========================+====================================+========================+==========================+
   | EVB-KSZ9563 on   | ``evb-ksz9563-sd.yml``       | ``evb-ksz9563-sd``       | ``tanowrt-image-full-swu``         | SD card                | |ndash|                  |
   | SAMA5D3 EDS      +------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   |                  | ``evb-ksz9563-nand.yml``     | ``evb-ksz9563-nand``     | ``tanowrt-image-full-swu-factory`` | internal NAND          | SD card                  |
   +------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-evb-ksz9563-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-evb-ksz9563-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-evb-ksz9563-images:

Images
------

.. _table-evb-ksz9563-images:
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
   |                           | ``tanowrt-image-full-swu-factory`` | ``evb-ksz9563-nand.yml``   | Factory installation image for standard TanoWrt     |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-full``                              |
   |                           |                                    |                            | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                            | as dependencies.                                    |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-evb-ksz9563-build` section).


.. _sec-evb-ksz9563-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-evb-ksz9563-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-evb-ksz9563-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-evb-ksz9563-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for EVB-KSZ9563 on SAMA5D3 EDS Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For SD Card

.. code-block:: console

   $ kas build targets/kas/evb-ksz9563-sd.yml

Default images will be produced to boot and run from the SD
card on the EVB-KSZ9563 on SAMA5D3 EDS target board.

.. rubric:: For Internal NAND Flash

.. code-block:: console

   $ kas build targets/kas/evb-ksz9563-nand.yml

An initial factory installation image will be generated,
intended to run from the SD card. The installer image
will install the default image to the internal NAND flash
memory and further the EVB-KSZ9563 on SAMA5D3 EDS board will boot
and run from the NAND flash memory.


.. _sec-evb-ksz9563-partitioning:

Partitioning Layouts
====================

SD Card
-------

The partitioning and data layout of the SD card image for the EVB-KSZ9563 on SAMA5D3 EDS board
are shown in the figure below.

.. _fig-evb-ksz9563-layout-sd:
.. figure:: images/evb-ksz9563-layout-sd.svg
   :width: 1000

   EVB-KSZ9563 on SAMA5D3 EDS Partitions Layout for SD Card

NAND
----

The partitioning and data layout of the NAND image for the EVB-KSZ9563 on SAMA5D3 EDS board
are shown in the figure below.

.. _fig-evb-ksz9563-layout-nand:
.. figure:: images/evb-ksz9563-layout-nand.svg
   :width: 1000

   EVB-KSZ9563 on SAMA5D3 EDS Partitions Layout for NAND


.. _sec-evb-ksz9563-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-evb-ksz9563-artifacts` for a description of some common (not all) build artifacts.

.. _table-evb-ksz9563-artifacts:
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
   | :file:`at91-evb-ksz9563.dtb`                                        | *All*                         | Target Device Tree Blob (DTB).                                       |
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
          `target <sec-evb-ksz9563-targets_>`__.  ``<rootfs-image>`` is replaced by
          the actual read-only root filesystem `image <sec-evb-ksz9563-images_>`__ name.

For example, below is the lists of artifacts produced by the ``evb-ksz9563-nand.yml``
and ``evb-ksz9563-sd.yml`` target builds. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

Build Artifacts Listings for ``evb-ksz9563-sd.yml`` Target
----------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9563-sd]$ ls -gGh | grep -v -e "^l"
         total 135M
         -rwxr-xr-x 2  20K Jul 27 17:54 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  50K Jul 27 17:55 at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.dtb
         -rw-r--r-- 2 4.5M Jul 27 17:55 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.bin
         -rw-r--r-- 2 6.4M Jul 27 17:55 fitImage-4.19.78+gitAUTOINC+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd.ext4
         -rw-r--r-- 2 6.4M Jul 27 17:55 fitImage-evb-ksz9563-sd.ext4
         -rw-r--r-- 2 1.6K Jul 27 17:55 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.its
         -rw-r--r-- 2 4.4M Jul 27 17:55 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.bin
         -rw-r--r-- 2 2.0M Jul 27 17:55 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         -rw-r--r-- 2  771 Jul 27 17:54 startup-factory.img
         -rw-r--r-- 2   12 Jul 27 17:54 startup-factory.img.version
         -rw-r--r-- 2 2.0K Jul 27 17:54 startup.img
         -rw-r--r-- 2   12 Jul 27 17:54 startup.img.version
         -rw-r--r-- 2 5.3K Jul 27 17:57 tanowrt-image-full.env
         -rw-r--r-- 2  63K Jul 27 17:56 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.manifest
         -rw-r--r-- 2 940M Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.sdcard.img
         -rw-r--r-- 2  24M Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.version
         -rw-r--r-- 2 374K Jul 27 17:56 tanowrt-image-full-evb-ksz9563-sd-20220727145300.testdata.json
         -rw-r--r-- 2 2.6K Jul 27 17:57 tanowrt-image-full-sdimage-at91-swu-a-b.wks
         -rw-r--r-- 2  31M Jul 27 17:57 tanowrt-image-full-swu-evb-ksz9563-sd-20220727145300.swu
         -rw-r--r-- 2  192 Jul 27 17:53 u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:53 u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 17:53 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9563-sd]$ ls -gGh
         total 135M
         -rwxr-xr-x 2  20K Jul 27 17:54 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  50K Jul 27 17:55 at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.dtb
         lrwxrwxrwx 2   98 Jul 27 17:55 at91-evb-ksz9563.dtb -> at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.dtb
         lrwxrwxrwx 2   98 Jul 27 17:55 at91-evb-ksz9563-evb-ksz9563-sd.dtb -> at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.dtb
         lrwxrwxrwx 2   24 Jul 27 17:54 boot.bin-sdcard -> at91bootstrap.bin-sdcard
         lrwxrwxrwx 2   90 Jul 27 17:55 fitImage -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.bin
         -rw-r--r-- 2 4.5M Jul 27 17:55 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.bin
         -rw-r--r-- 2 6.4M Jul 27 17:55 fitImage-4.19.78+gitAUTOINC+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd.ext4
         lrwxrwxrwx 2   90 Jul 27 17:55 fitImage-evb-ksz9563-sd.bin -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.bin
         -rw-r--r-- 2 6.4M Jul 27 17:55 fitImage-evb-ksz9563-sd.ext4
         -rw-r--r-- 2 1.6K Jul 27 17:55 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.its
         lrwxrwxrwx 2   94 Jul 27 17:55 fitImage-its-evb-ksz9563-sd -> fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.its
         -rw-r--r-- 2 4.4M Jul 27 17:55 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.bin
         lrwxrwxrwx 2  100 Jul 27 17:55 fitImage-linux.bin-evb-ksz9563-sd -> fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.bin
         -rw-r--r-- 2 2.0M Jul 27 17:55 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.tgz
         lrwxrwxrwx 2   89 Jul 27 17:55 modules-evb-ksz9563-sd.tgz -> modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-sd-20220727145300.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         lrwxrwxrwx 2   11 Jul 27 17:54 startup-evb-ksz9563-sd.img -> startup.img
         lrwxrwxrwx 2   19 Jul 27 17:54 startup-evb-ksz9563-sd.img.version -> startup.img.version
         lrwxrwxrwx 2   19 Jul 27 17:54 startup-factory-evb-ksz9563-sd.img -> startup-factory.img
         lrwxrwxrwx 2   27 Jul 27 17:54 startup-factory-evb-ksz9563-sd.img.version -> startup-factory.img.version
         -rw-r--r-- 2  771 Jul 27 17:54 startup-factory.img
         -rw-r--r-- 2   12 Jul 27 17:54 startup-factory.img.version
         -rw-r--r-- 2 2.0K Jul 27 17:54 startup.img
         -rw-r--r-- 2   12 Jul 27 17:54 startup.img.version
         -rw-r--r-- 2 5.3K Jul 27 17:57 tanowrt-image-full.env
         -rw-r--r-- 2  63K Jul 27 17:56 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.manifest
         -rw-r--r-- 2 940M Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.sdcard.img
         -rw-r--r-- 2  24M Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.version
         -rw-r--r-- 2 374K Jul 27 17:56 tanowrt-image-full-evb-ksz9563-sd-20220727145300.testdata.json
         lrwxrwxrwx 2   64 Jul 27 17:56 tanowrt-image-full-evb-ksz9563-sd.manifest -> tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.manifest
         lrwxrwxrwx 2   66 Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd.sdcard.img -> tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.sdcard.img
         lrwxrwxrwx 2   68 Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd.squashfs-lzo -> tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.squashfs-lzo
         lrwxrwxrwx 2   62 Jul 27 17:56 tanowrt-image-full-evb-ksz9563-sd.testdata.json -> tanowrt-image-full-evb-ksz9563-sd-20220727145300.testdata.json
         lrwxrwxrwx 2   63 Jul 27 17:57 tanowrt-image-full-evb-ksz9563-sd.version -> tanowrt-image-full-evb-ksz9563-sd-20220727145300.rootfs.version
         -rw-r--r-- 2 2.6K Jul 27 17:57 tanowrt-image-full-sdimage-at91-swu-a-b.wks
         -rw-r--r-- 2  31M Jul 27 17:57 tanowrt-image-full-swu-evb-ksz9563-sd-20220727145300.swu
         lrwxrwxrwx 2   56 Jul 27 17:57 tanowrt-image-full-swu-evb-ksz9563-sd.swu -> tanowrt-image-full-swu-evb-ksz9563-sd-20220727145300.swu
         lrwxrwxrwx 2   61 Jul 27 17:53 u-boot.bin -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:53 u-boot.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:53 u-boot-evb-ksz9563-sd.bin -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:53 u-boot-evb-ksz9563-sd.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 17:53 u-boot-initial-env-evb-ksz9563-sd-sdcard -> u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 17:53 u-boot-initial-env-evb-ksz9563-sd-sdcard.bin -> u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  192 Jul 27 17:53 u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:53 u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 17:53 u-boot-initial-env-sdcard -> u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 17:53 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-evb-ksz9563-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 17:53 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin


Build Artifacts Listings for ``evb-ksz9563-nand.yml`` Target
------------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9563-nand]$ ls -gGh | grep -v -e "^l"
         total 123M
         -rwxr-xr-x 2  15K Jul 27 17:47 at91bootstrap.bin-nand
         -rw-r--r-- 2  16K Jul 27 17:47 at91bootstrap.bin-nand-pmecc
         -rwxr-xr-x 2  20K Jul 27 17:47 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  20K Jul 27 17:47 at91bootstrap.bin-sdcard-pmecc
         -rw-r--r-- 2  50K Jul 27 17:50 at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.dtb
         -rw-r--r-- 2 4.5M Jul 27 17:50 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         -rw-r--r-- 2 1.6K Jul 27 17:50 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.its
         -rw-r--r-- 2 2.3K Jul 27 17:50 fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.its
         -rw-r--r-- 2 4.4M Jul 27 17:50 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         -rw-r--r-- 2  12M Jul 27 17:50 fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         -rw-r--r-- 2 2.0M Jul 27 17:50 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         -rw-r--r-- 2  846 Jul 27 17:47 startup-factory.img
         -rw-r--r-- 2   12 Jul 27 17:47 startup-factory.img.version
         -rw-r--r-- 2 2.2K Jul 27 17:47 startup.img
         -rw-r--r-- 2   12 Jul 27 17:47 startup.img.version
         -rw-r--r-- 2  64K Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.manifest
         -rw-r--r-- 2  23M Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 17:51 tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.version
         -rw-r--r-- 2 374K Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand-20220727144635.testdata.json
         -rw-r--r-- 2  29M Jul 27 17:51 tanowrt-image-full-swu-evb-ksz9563-nand-20220727144635.swu
         -rw-r--r-- 2 5.9K Jul 27 17:49 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  42M Jul 27 17:51 tanowrt-image-full-swu-factory-evb-ksz9563-nand-20220727144635.sdcard.img
         -rw-r--r-- 2  778 Jul 27 17:49 tanowrt-image-full-swu-factory-sdimage-at91-swu-factory.wks
         -rw-r--r-- 2 6.6M Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.cpio.gz
         -rw-r--r-- 2 3.3K Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.manifest
         -rw-r--r-- 2   24 Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.version
         -rw-r--r-- 2 381K Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.testdata.json
         -rw-r--r-- 2  187 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  192 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 727K Jul 27 17:47 u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 17:47 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/evb-ksz9563-nand]$ ls -gGh
         total 123M
         -rwxr-xr-x 2  15K Jul 27 17:47 at91bootstrap.bin-nand
         -rw-r--r-- 2  16K Jul 27 17:47 at91bootstrap.bin-nand-pmecc
         -rwxr-xr-x 2  20K Jul 27 17:47 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  20K Jul 27 17:47 at91bootstrap.bin-sdcard-pmecc
         -rw-r--r-- 2  50K Jul 27 17:50 at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.dtb
         lrwxrwxrwx 2  100 Jul 27 17:50 at91-evb-ksz9563.dtb -> at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.dtb
         lrwxrwxrwx 2  100 Jul 27 17:50 at91-evb-ksz9563-evb-ksz9563-nand.dtb -> at91-evb-ksz9563--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.dtb
         lrwxrwxrwx 2   24 Jul 27 17:47 boot.bin-sdcard -> at91bootstrap.bin-sdcard
         lrwxrwxrwx 2   92 Jul 27 17:50 fitImage -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         -rw-r--r-- 2 4.5M Jul 27 17:50 fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         lrwxrwxrwx 2   92 Jul 27 17:50 fitImage-evb-ksz9563-nand.bin -> fitImage--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         -rw-r--r-- 2 1.6K Jul 27 17:50 fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.its
         lrwxrwxrwx 2   96 Jul 27 17:50 fitImage-its-evb-ksz9563-nand -> fitImage-its--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.its
         -rw-r--r-- 2 2.3K Jul 27 17:50 fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.its
         lrwxrwxrwx 2  149 Jul 27 17:50 fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-evb-ksz9563-nand -> fitImage-its-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.its
         -rw-r--r-- 2 4.4M Jul 27 17:50 fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         lrwxrwxrwx 2  102 Jul 27 17:50 fitImage-linux.bin-evb-ksz9563-nand -> fitImage-linux.bin--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         -rw-r--r-- 2  12M Jul 27 17:50 fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         lrwxrwxrwx 2  145 Jul 27 17:50 fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-evb-ksz9563-nand -> fitImage-tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.bin
         -rw-r--r-- 2 2.0M Jul 27 17:50 modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.tgz
         lrwxrwxrwx 2   91 Jul 27 17:50 modules-evb-ksz9563-nand.tgz -> modules--4.19.78+git0+046113c438+ksz2-tano0.2.20.20.0.1-evb-ksz9563-nand-20220727144635.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         lrwxrwxrwx 2   11 Jul 27 17:47 startup-evb-ksz9563-nand.img -> startup.img
         lrwxrwxrwx 2   19 Jul 27 17:47 startup-evb-ksz9563-nand.img.version -> startup.img.version
         lrwxrwxrwx 2   19 Jul 27 17:47 startup-factory-evb-ksz9563-nand.img -> startup-factory.img
         lrwxrwxrwx 2   27 Jul 27 17:47 startup-factory-evb-ksz9563-nand.img.version -> startup-factory.img.version
         -rw-r--r-- 2  846 Jul 27 17:47 startup-factory.img
         -rw-r--r-- 2   12 Jul 27 17:47 startup-factory.img.version
         -rw-r--r-- 2 2.2K Jul 27 17:47 startup.img
         -rw-r--r-- 2   12 Jul 27 17:47 startup.img.version
         -rw-r--r-- 2  64K Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.manifest
         -rw-r--r-- 2  23M Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 27 17:51 tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.version
         -rw-r--r-- 2 374K Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand-20220727144635.testdata.json
         lrwxrwxrwx 2   66 Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand.manifest -> tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.manifest
         lrwxrwxrwx 2   70 Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand.squashfs-lzo -> tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.squashfs-lzo
         lrwxrwxrwx 2   64 Jul 27 17:50 tanowrt-image-full-evb-ksz9563-nand.testdata.json -> tanowrt-image-full-evb-ksz9563-nand-20220727144635.testdata.json
         lrwxrwxrwx 2   65 Jul 27 17:51 tanowrt-image-full-evb-ksz9563-nand.version -> tanowrt-image-full-evb-ksz9563-nand-20220727144635.rootfs.version
         -rw-r--r-- 2  29M Jul 27 17:51 tanowrt-image-full-swu-evb-ksz9563-nand-20220727144635.swu
         lrwxrwxrwx 2   58 Jul 27 17:51 tanowrt-image-full-swu-evb-ksz9563-nand.swu -> tanowrt-image-full-swu-evb-ksz9563-nand-20220727144635.swu
         -rw-r--r-- 2 5.9K Jul 27 17:49 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  42M Jul 27 17:51 tanowrt-image-full-swu-factory-evb-ksz9563-nand-20220727144635.sdcard.img
         lrwxrwxrwx 2   73 Jul 27 17:51 tanowrt-image-full-swu-factory-evb-ksz9563-nand.sdcard.img -> tanowrt-image-full-swu-factory-evb-ksz9563-nand-20220727144635.sdcard.img
         -rw-r--r-- 2  778 Jul 27 17:49 tanowrt-image-full-swu-factory-sdimage-at91-swu-factory.wks
         -rw-r--r-- 2 6.6M Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.cpio.gz
         -rw-r--r-- 2 3.3K Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.manifest
         -rw-r--r-- 2   24 Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.version
         -rw-r--r-- 2 381K Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.testdata.json
         lrwxrwxrwx 2   82 Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand.cpio.gz -> tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.cpio.gz
         lrwxrwxrwx 2   83 Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand.manifest -> tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.manifest
         lrwxrwxrwx 2   81 Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand.testdata.json -> tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.testdata.json
         lrwxrwxrwx 2   82 Jul 27 17:50 tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand.version -> tanowrt-image-initramfs-swu-factory-evb-ksz9563-nand-20220727144635.rootfs.version
         lrwxrwxrwx 2   59 Jul 27 17:47 u-boot.bin -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   59 Jul 27 17:47 u-boot.bin-nand -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:47 u-boot.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   59 Jul 27 17:47 u-boot-evb-ksz9563-nand.bin -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   59 Jul 27 17:47 u-boot-evb-ksz9563-nand.bin-nand -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   61 Jul 27 17:47 u-boot-evb-ksz9563-nand.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-nand -> u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-nand.bin -> u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  187 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   86 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-sdcard -> u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   90 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-sdcard.bin -> u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2  192 Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         -rw-r--r-- 2 128K Jul 27 17:47 u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   84 Jul 27 17:47 u-boot-initial-env-nand -> u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   88 Jul 27 17:47 u-boot-initial-env-nand.bin -> u-boot-initial-env-evb-ksz9563-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         lrwxrwxrwx 2   86 Jul 27 17:47 u-boot-initial-env-sdcard -> u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0
         lrwxrwxrwx 2   90 Jul 27 17:47 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-evb-ksz9563-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 727K Jul 27 17:47 u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin
         -rw-r--r-- 2 719K Jul 27 17:47 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.0.bin


.. _sec-evb-ksz9563-flash:

Writing Images
==============

.. _sec-evb-ksz9563-flash-sd:

Writing Image to SD Card
-----------------------------

No special information about writing images to SD card
for EVB-KSZ9563 on SAMA5D3 EDS board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing factory installation image for the ``evb-ksz9563-nand.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-swu-factory-evb-ksz9563-nand.sdcard.img of=/dev/mmcblk1

Writing bootable card image for the ``evb-ksz9563-sd.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-evb-ksz9563-sd.sdcard.img of=/dev/mmcblk1


.. _sec-evb-ksz9563-flash-emmc:

Writing Image to NAND Flash
---------------------------

For the initial flashing of the internal NAND memory it is recommended to use
the special image of the initial factory installation. If you choose a build target
(see :ref:`sec-evb-ksz9563-targets` for details) that assumes using the
factory installation image for the initial flashing of the
device, a factory installation image (:file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`)
will be automatically generated during the build process
(see :ref:`sec-evb-ksz9563-build`).
To write the factory installation image to a SD card, follow the instructions
from :ref:`sec-writing-sd-or-usb` section.

When you boot device from the prepaired SD card with factory installation image the installation
of TanoWrt to the internal NAND flash memory will be done automatically. The detailed
installation log is available on the :ref:`debug UART <sec-evb-ksz9563-serial>`.
After the installation is complete, the board will power off automatically. You need
to remove SD card with the installation image and power on the board. When the
board boots up, the installed system will be booted from the internal NAND flash memory.

.. caution:: Be aware that during the installation all existing data on the internal NAND
             flash memory will be permanently lost.


.. _sec-evb-ksz9563-booting:

Booting and Running
===================

Configuring Boot Mode
---------------------

The SAMA5D3 Ethernet Development System board does not have any switches or jumpers for
selecting the boot source. By default, booting is performed from the SD card. If booting
from an SD card fails, NAND flash memory is used for booting.

Hardware Configuration
----------------------

Ensure that the J20 (NAND Enable) jumper on SAMA5D3 EDS board is shorted (this is needed
for installing and running the TanoWrt from the internal NAND flash memory).

Booting from SD Card
-------------------------

1. Insert the SD card into the slot on the SAMA5D3 EDS board (power is off).
2. Power on board.
3. TanoWrt will be booting from SD card.
4. Log in to system using default :ref:`credentials <sec-access-creds>`.


.. _sec-evb-ksz9563-serial:

Serial Console
==============

Serial console on SAMA5D3 EDS board are available through 6-pin
serial communication header J10.
Connect the USB to TTL serial cable as described below.
Don't connect the VCC wire of the USB to TTL converted,
connect only TX, RX and GND wires.

+------------------------------+-----------------+
| SAMA5D3 EDS J10 6-pin Header | Signal          |
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

See :numref:`fig-evb-ksz9563` for example
connection USB to TTL converter with SAMA5D3 EDS board.

The default serial console settings for SAMA5D3 EDS for U-Boot and kernel
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


.. _sec-evb-ksz9563-network-config:

Default Network Configuration
=============================

By default, network ports 1 and 2 are joined into a bridge (``br-lan`` interface)
with the :term:`RSTP` protocol enabled. Bridge ``br-lan`` is in the LAN firewall zone.
By default, the ``br-lan`` bridge is configured with static IP address
192.168.0.1/24 with enabled :term:`DHCP` server.

Also you can connect to the board using USB RNDIS connection (``usb0`` interface).
RNDIS interface configured with static IP address 172.16.0.1/24 with
enabled :term:`DHCP` server.

Default network configuration for the EVB-KSZ9563 has no WAN interface.

Ethernet ports 1 and 2 have enabled :term:`LLDP` for Rx and Tx state by default.


.. _sec-evb-ksz9563-webui:

Web User Interface
==================

The WebUI can be accessed via any Ethernet port bridged to LAN network
(1 or 2) or via USB RNDIS connection through HTTP(s) protocol.
You must see something like this in browser:

.. _fig-evb-ksz9563-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-evb-ksz9563-luci-status:
.. figure:: images/evb-ksz9563-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-evb-ksz9563-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-evb-ksz9563-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


References
==========

1. `KSZ9563 Plug-in Evaluation Board (EVB-KSZ9563) <https://www.microchip.com/developmenttools/ProductDetails/PartNO/EVB-KSZ9563>`_
2. `SAMA5D3 Ethernet Development System (DM320114) <https://www.microchip.com/DevelopmentTools/ProductDetails/PartNO/DM320114>`_

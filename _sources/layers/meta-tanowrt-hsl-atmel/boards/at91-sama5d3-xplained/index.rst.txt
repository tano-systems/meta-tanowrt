.. SPDX-License-Identifier: MIT

.. _machine-at91-sama5d3-xplained:

**********************
Atmel SAMA5D3 Xplained
**********************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-at91-sama5d3-xplained-board:

Board Overview
==============

The SAMA5D3 Xplained is a fast prototyping and evaluation platform for microprocessor-based design.
The board comes with a rich set of ready to use connectivity and storage peripherals and expansion
headers for easy customization. A Linux distribution and software package gets you evaluating fast.
A USB device connector can be used to power the board as well as programming and debugging it.

See [ATSAMA5D3-XPLD]_ for details about SAMA5D3 Xplained board.

.. _fig-at91-sama5d3-xplained:
.. figure:: images/at91-sama5d3-xplained.jpg
   :width: 1000
   :class: with-border

   SAMA5D3 Xplained Board Interfaces and Connectors


Block Diagram
-------------

.. _fig-at91-sama5d3-xplained-block-diagram:
.. figure:: images/at91-sama5d3-xplained-block-diagram.png
   :class: with-border
   :width: 300

   SAMA5D3 Xplained Board Block Diagram


Photos
------

.. container:: flex

   .. _fig-at91-sama5d3-xplained-angled:
   .. figure:: images/at91-sama5d3-xplained-angled.jpg
      :class: with-border
      :width: 300

      SAMA5D3 Xplained Angled View

   .. _fig-at91-sama5d3-xplained-top:
   .. figure:: images/at91-sama5d3-xplained-top.jpg
      :class: with-border
      :width: 300

      SAMA5D3 Xplained Top View

   .. _fig-at91-sama5d3-xplained-bottom:
   .. figure:: images/at91-sama5d3-xplained-bottom.jpg
      :class: with-border
      :width: 300

      SAMA5D3 Xplained Bottom View


Specification
-------------

.. table:: at91-sama5d3-xplained Specification

   +--------------+------------------------------------------------------------------------------------+
   | Model        | SAMA5D3 Xplained                                                                   |
   +==============+====================================================================================+
   | Processor    || Atmel SAMA5D36 (324-ball BGA package)                                             |
   |              || 1 |times| ARM Cortex-A5                                                           |
   |              || frequency up to 536 MHz                                                           |
   |              || [DS60001609]_                                                                     |
   +--------------+------------------------------------------------------------------------------------+
   | Memory       || 256 MiB DDR2 SDRAM                                                                |
   +--------------+------------------------------------------------------------------------------------+
   | Storage      || Full-size SD card slot                                                            |
   |              || 1 |times| SLC NAND Flash (Micron MT29F2G08ABAEAWP, 256 MiB)                       |
   |              || 1 |times| optional serial SPI EEPROM                                              |
   |              || 1 |times| optional 1-Wire EEPROM                                                  |
   +--------------+------------------------------------------------------------------------------------+
   | USB          || 2 |times| USB 2.0 Host with power switch                                          |
   |              || 1 |times| Micro-AB USB 2.0 device                                                 |
   +--------------+------------------------------------------------------------------------------------+
   | Ethernet     || 1 |times| Gigabit Ethernet PHY (KSZ9031, RGMII 10/100/1000)                       |
   |              || 1 |times| Ethernet PHY (KSZ8081, RMII 10/100)                                     |
   +--------------+------------------------------------------------------------------------------------+
   | Debug        || 1 |times| JTAG interface connector                                                |
   |              || 1 |times| Serial debug console interface (3.3V level)                             |
   +--------------+------------------------------------------------------------------------------------+
   | Expansion    || Arduino R3 compatible set of connectors                                           |
   | Connectors   || (GPIO, TWI, SPI, USART, UART, Audio and ISI interfaces                            |
   |              || are accessible through these headersXPRO set of connectors)                       |
   +--------------+------------------------------------------------------------------------------------+
   | Other        || 1 |times| LCD interface connector, LCD TFT Controller with overlay,               |
   |              |            alpha-blending, rotation, scaling and color space conversion            |
   |              || 1 |times| RTC                                                                     |
   |              || 2 |times| User LED                                                                |
   |              || 1 |times| User push button                                                        |
   +--------------+------------------------------------------------------------------------------------+
   | Dimensions   | 127 |times| 75 |times| 20 mm                                                       |
   +--------------+------------------------------------------------------------------------------------+
   | Power Source | Micro-AB USB                                                                       |
   +--------------+------------------------------------------------------------------------------------+
   | Power        | 5V/0.5A from USB                                                                   |
   +--------------+------------------------------------------------------------------------------------+


.. _sec-at91-sama5d3-xplained-targets:

Build Targets
=============

.. _sec-at91-sama5d3-xplained-machines:

Machines
--------

.. _table-at91-sama5d3-xplained-machines:
.. table:: Supported Machines

   +------------------+------------------------------------+--------------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_      | Target YAML\ [#]_                  | Machine\ [#]_                  | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +==================+====================================+================================+====================================+========================+==========================+
   | SAMA5D3 Xplained | ``at91-sama5d3-xplained-sd.yml``   | ``at91-sama5d3-xplained-sd``   | ``tanowrt-image-full-swu``         | SD card                | |ndash|                  |
   |                  +------------------------------------+--------------------------------+------------------------------------+------------------------+--------------------------+
   |                  | ``at91-sama5d3-xplained-nand.yml`` | ``at91-sama5d3-xplained-nand`` | ``tanowrt-image-full-swu-factory`` | Internal NAND flash    | SD card                  |
   +------------------+------------------------------------+--------------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-at91-sama5d3-xplained-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-at91-sama5d3-xplained-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-at91-sama5d3-xplained-images:

Images
------

.. _table-at91-sama5d3-xplained-images:
.. table:: Supported Images
   :widths: 15, 15, 15, 55

   +---------------------------+------------------------------------+------------------------------------+-----------------------------------------------------+
   | Read-Only Root Filesystem | Recipe\ [#]_                       | Supported by Target(s)             | Description                                         |
   | Image                     |                                    |                                    |                                                     |
   +===========================+====================================+====================================+=====================================================+
   | ``tanowrt-image-full``    | ``tanowrt-image-full``             | *All*                              | Standard TanoWrt image.                             |
   |                           +------------------------------------+------------------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu``         | *All*                              | Standard TanoWrt image                              |
   |                           |                                    |                                    | and :ref:`firmware upgrade <sec-firmware-upgrade>`  |
   |                           |                                    |                                    | image. When building this image,                    |
   |                           |                                    |                                    | ``tanowrt-image-full`` will also be built           |
   |                           |                                    |                                    | as dependency.                                      |
   |                           +------------------------------------+------------------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu-factory`` | ``at91-sama5d3-xplained-nand.yml`` | Factory installation image for standard TanoWrt     |
   |                           |                                    |                                    | image. When building this image,                    |
   |                           |                                    |                                    | ``tanowrt-image-full``                              |
   |                           |                                    |                                    | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                                    | as dependencies.                                    |
   +---------------------------+------------------------------------+------------------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-at91-sama5d3-xplained-build` section).


.. _sec-at91-sama5d3-xplained-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-at91-sama5d3-xplained-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-at91-sama5d3-xplained-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-at91-sama5d3-xplained-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for SAMA5D3 Xplained Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For SD Card

.. code-block:: console

   $ kas build targets/kas/at91-sama5d3-xplained-sd.yml

Default images will be produced to boot and run from the SD
card on the SAMA5D3 Xplained target board.

.. rubric:: For Internal NAND

.. code-block:: console

   $ kas build targets/kas/at91-sama5d3-xplained-nand.yml

An initial factory installation image will be generated,
intended to run from the SD card. The installer image
will install the default image to the internal NAND flash
memory and further the SAMA5D3 Xplained board will boot
and run from the NAND flash memory.


.. _sec-at91-sama5d3-xplained-partitioning:

Partitioning Layouts
====================

SD Card
-------

The partitioning and data layout of the SD card image for the SAMA5D3 Xplained board
are shown in the figure below.

.. _fig-at91-sama5d3-xplained-layout-sd:
.. figure:: images/at91-sama5d3-xplained-layout-sd.svg
   :width: 900

   SAMA5D3 Xplained Partitions Layout for SD Card

NAND
----

The partitioning and data layout of the NAND image for the SAMA5D3 Xplained board
are shown in the figure below.

.. _fig-at91-sama5d3-xplained-layout-nand:
.. figure:: images/at91-sama5d3-xplained-layout-nand.svg
   :width: 1200

   SAMA5D3 Xplained Partitions Layout for NAND


.. _sec-at91-sama5d3-xplained-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-at91-sama5d3-xplained-artifacts` for a description of some common (not all) build artifacts.

.. _table-at91-sama5d3-xplained-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | Artifact                                                            | Target(s)                           | Description                                                          |
   +=====================================================================+=====================================+======================================================================+
   | .. centered:: Bootloader (AT91Bootstrap)                                                                                                                                         |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`at91bootstrap.bin-sdcard`                                    | *All*                               | AT91Bootstrap binary for SD card images.                             |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`at91bootstrap.bin-nand`                                      | ``at91-sama5d3-xplained-emcc.yml``  | AT91Bootstrap binary for NAND flash images.                          |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | .. centered:: Bootloader (U-Boot)                                                                                                                                                |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`startup-<MACHINE>.img`                                       | *All*                               | U-Boot startup script.                                               |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`startup-factory-<MACHINE>.img`                               | ``at91-sama5d3-xplained-nand.yml``  | U-Boot startup script for factory installation image.                |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdcard`                         | *All*                               | U-Boot initial environment image for SD card image.                  |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-nand`                           | ``at91-sama5d3-xplained-nand.yml``  | U-Boot initial environment image for internal NAND flash.            |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdcard`                                 | *All*                               | U-Boot binary image for booting from SD card.                        |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-nand`                                   | ``at91-sama5d3-xplained-nand.yml``  | U-Boot binary image for booting from internal NAND flash.            |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                                               |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.bin`                                      | *All*                               | Flattened Image Tree (FIT) image with Linux kernel                   |
   |                                                                     |                                     | and Device Tree Blobs (DTB).                                         |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.ext4`                                     | ``at91-sama5d3-xplained-sd.yml``    | FIT image packed into an ext4 file system image.                     |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`at91-sama5d3_xplained.dtb`                                   | *All*                               | Target Device Tree Blob (DTB).                                       |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>`      | ``at91-sama5d3-xplained-nand.yml``  | FIT image for SWU factory installation image with                    |
   |                                                                     |                                     | initramfs image.                                                     |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                                             |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`                         | ``at91-sama5d3-xplained-sd.yml``    | SD card image including all required partitions for booting          |
   |                                                                     |                                     | and running the system. This image is ready to be written            |
   |                                                                     |                                     | to the SD card using the :command:`dd` utility or similar            |
   |                                                                     |                                     | (see :ref:`sec-at91-sama5d3-xplained-flash`).                        |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`             | ``at91-sama5d3-xplained-nand.yml``  | SD card factory installation image. This image is ready              |
   |                                                                     |                                     | to be written to the SD card using the :command:`dd` utility         |
   |                                                                     |                                     | or similar (see :ref:`sec-at91-sama5d3-xplained-flash`).             |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`tanowrt-image-initramfs-swu-factory-<MACHINE>.cpio.gz`       | ``at91-sama5d3-xplained-nand.yml``  | Root filesystem initramfs image for factory installtion              |
   |                                                                     |                                     | image. This image is included in                                     |
   |                                                                     |                                     | :file:`fitImage-tanowrt-image-initramfs-swu-factory-<MACHINE>`.      |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`                       | *All*                               | Root filesystem image (squashfs with LZO compression).               |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                            | *All*                               | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.                |
   +---------------------------------------------------------------------+-------------------------------------+----------------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-at91-sama5d3-xplained-targets_>`__. ``<rootfs-image>`` is replaced by
          the actual read-only root filesystem `image <sec-at91-sama5d3-xplained-images_>`__ name.

For example, below is the lists of artifacts produced by the ``at91-sama5d3-xplained-nand.yml``
and ``at91-sama5d3-xplained-sd.yml`` target builds. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

Build Artifacts Listings for ``at91-sama5d3-xplained-sd.yml`` Target
--------------------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/at91-sama5d3-xplained-sd]$ ls -gGh | grep -v -e "^l"
         total 134M
         -rwxr-xr-x 2  20K Aug 10 03:16 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  49K Aug 10 03:17 at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.dtb
         -rw-r--r-- 2 4.5M Aug 10 03:17 fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.bin
         -rw-r--r-- 2 6.3M Aug 10 03:17 fitImage-4.19.78+gitAUTOINC+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd.ext4
         -rw-r--r-- 2 6.3M Aug 10 03:17 fitImage-at91-sama5d3-xplained-sd.ext4
         -rw-r--r-- 2 1.6K Aug 10 03:17 fitImage-its--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.its
         -rw-r--r-- 2 4.4M Aug 10 03:17 fitImage-linux.bin--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.bin
         -rw-r--r-- 2 2.0M Aug 10 03:17 modules--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         -rw-r--r-- 2 2.1K Aug 10 03:16 startup.img
         -rw-r--r-- 2   19 Aug 10 03:16 startup.img.version
         -rw-r--r-- 2  65K Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.manifest
         -rw-r--r-- 2 940M Aug 10 03:19 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.sdcard.img
         -rw-r--r-- 2  24M Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Aug 10 03:19 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.version
         -rw-r--r-- 2 342K Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.testdata.json
         -rw-r--r-- 2 5.5K Aug 10 03:18 tanowrt-image-full.env
         -rw-r--r-- 2 2.7K Aug 10 03:18 tanowrt-image-full-sdimage-at91-swu-a-b.wks
         -rw-r--r-- 2  31M Aug 10 03:19 tanowrt-image-full-swu-at91-sama5d3-xplained-sd-20220810001425.swu
         -rw-r--r-- 2  192 Aug 10 03:16 u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         -rw-r--r-- 2 128K Aug 10 03:16 u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2 719K Aug 10 03:16 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin


   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/at91-sama5d3-xplained-sd]$ ls -gGh

         total 134M
         -rwxr-xr-x 2  20K Aug 10 03:16 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  49K Aug 10 03:17 at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.dtb
         lrwxrwxrwx 2  108 Aug 10 03:17 at91-sama5d3_xplained-at91-sama5d3-xplained-sd.dtb -> at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.dtb
         lrwxrwxrwx 2  108 Aug 10 03:17 at91-sama5d3_xplained.dtb -> at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.dtb
         lrwxrwxrwx 2   24 Aug 10 03:16 boot.bin-sdcard -> at91bootstrap.bin-sdcard
         lrwxrwxrwx 2   95 Aug 10 03:17 fitImage -> fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.bin
         -rw-r--r-- 2 4.5M Aug 10 03:17 fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.bin
         -rw-r--r-- 2 6.3M Aug 10 03:17 fitImage-4.19.78+gitAUTOINC+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd.ext4
         lrwxrwxrwx 2   95 Aug 10 03:17 fitImage-at91-sama5d3-xplained-sd.bin -> fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.bin
         -rw-r--r-- 2 6.3M Aug 10 03:17 fitImage-at91-sama5d3-xplained-sd.ext4
         -rw-r--r-- 2 1.6K Aug 10 03:17 fitImage-its--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.its
         lrwxrwxrwx 2   99 Aug 10 03:17 fitImage-its-at91-sama5d3-xplained-sd -> fitImage-its--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.its
         -rw-r--r-- 2 4.4M Aug 10 03:17 fitImage-linux.bin--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.bin
         lrwxrwxrwx 2  105 Aug 10 03:17 fitImage-linux.bin-at91-sama5d3-xplained-sd -> fitImage-linux.bin--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.bin
         -rw-r--r-- 2 2.0M Aug 10 03:17 modules--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.tgz
         lrwxrwxrwx 2   94 Aug 10 03:17 modules-at91-sama5d3-xplained-sd.tgz -> modules--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-sd-20220810001425.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         lrwxrwxrwx 2   11 Aug 10 03:16 startup-at91-sama5d3-xplained-sd.img -> startup.img
         lrwxrwxrwx 2   19 Aug 10 03:16 startup-at91-sama5d3-xplained-sd.img.version -> startup.img.version
         -rw-r--r-- 2 2.1K Aug 10 03:16 startup.img
         -rw-r--r-- 2   19 Aug 10 03:16 startup.img.version
         -rw-r--r-- 2  65K Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.manifest
         -rw-r--r-- 2 940M Aug 10 03:19 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.sdcard.img
         -rw-r--r-- 2  24M Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Aug 10 03:19 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.version
         -rw-r--r-- 2 342K Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.testdata.json
         lrwxrwxrwx 2   74 Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd.manifest -> tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.manifest
         lrwxrwxrwx 2   76 Aug 10 03:19 tanowrt-image-full-at91-sama5d3-xplained-sd.sdcard.img -> tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.sdcard.img
         lrwxrwxrwx 2   78 Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd.squashfs-lzo -> tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.squashfs-lzo
         lrwxrwxrwx 2   72 Aug 10 03:18 tanowrt-image-full-at91-sama5d3-xplained-sd.testdata.json -> tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.testdata.json
         lrwxrwxrwx 2   73 Aug 10 03:19 tanowrt-image-full-at91-sama5d3-xplained-sd.version -> tanowrt-image-full-at91-sama5d3-xplained-sd-20220810001425.rootfs.version
         -rw-r--r-- 2 5.5K Aug 10 03:18 tanowrt-image-full.env
         -rw-r--r-- 2 2.7K Aug 10 03:18 tanowrt-image-full-sdimage-at91-swu-a-b.wks
         -rw-r--r-- 2  31M Aug 10 03:19 tanowrt-image-full-swu-at91-sama5d3-xplained-sd-20220810001425.swu
         lrwxrwxrwx 2   66 Aug 10 03:19 tanowrt-image-full-swu-at91-sama5d3-xplained-sd.swu -> tanowrt-image-full-swu-at91-sama5d3-xplained-sd-20220810001425.swu
         lrwxrwxrwx 2   61 Aug 10 03:16 u-boot-at91-sama5d3-xplained-sd.bin -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   61 Aug 10 03:16 u-boot-at91-sama5d3-xplained-sd.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   61 Aug 10 03:16 u-boot.bin -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   61 Aug 10 03:16 u-boot.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   94 Aug 10 03:16 u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard -> u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         lrwxrwxrwx 2   98 Aug 10 03:16 u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard.bin -> u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2  192 Aug 10 03:16 u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         -rw-r--r-- 2 128K Aug 10 03:16 u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   94 Aug 10 03:16 u-boot-initial-env-sdcard -> u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         lrwxrwxrwx 2   98 Aug 10 03:16 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-at91-sama5d3-xplained-sd-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2 719K Aug 10 03:16 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin


Build Artifacts Listings for ``at91-sama5d3-xplained-nand.yml`` Target
----------------------------------------------------------------------

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/at91-sama5d3-xplained-nand]$ ls -gGh | grep -v -e "^l"
         total 123M
         -rwxr-xr-x 2  15K Aug 10 03:20 at91bootstrap.bin-nand
         -rw-r--r-- 2  16K Aug 10 03:20 at91bootstrap.bin-nand-pmecc
         -rwxr-xr-x 2  20K Aug 10 03:20 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  20K Aug 10 03:20 at91bootstrap.bin-sdcard-pmecc
         -rw-r--r-- 2  49K Aug 10 03:23 at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.dtb
         -rw-r--r-- 2 4.5M Aug 10 03:23 fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         -rw-r--r-- 2 1.6K Aug 10 03:23 fitImage-its--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.its
         -rw-r--r-- 2 2.3K Aug 10 03:23 fitImage-its-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.its
         -rw-r--r-- 2 4.4M Aug 10 03:23 fitImage-linux.bin--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         -rw-r--r-- 2  11M Aug 10 03:23 fitImage-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         -rw-r--r-- 2 2.0M Aug 10 03:23 modules--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         -rw-r--r-- 2  890 Aug 10 03:20 startup-factory.img
         -rw-r--r-- 2   19 Aug 10 03:20 startup-factory.img.version
         -rw-r--r-- 2 2.2K Aug 10 03:20 startup.img
         -rw-r--r-- 2   19 Aug 10 03:20 startup.img.version
         -rw-r--r-- 2  66K Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.manifest
         -rw-r--r-- 2  23M Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.version
         -rw-r--r-- 2 342K Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.testdata.json
         -rw-r--r-- 2  29M Aug 10 03:23 tanowrt-image-full-swu-at91-sama5d3-xplained-nand-20220810001922.swu
         -rw-r--r-- 2  42M Aug 10 03:23 tanowrt-image-full-swu-factory-at91-sama5d3-xplained-nand-20220810001922.sdcard.img
         -rw-r--r-- 2 6.2K Aug 10 03:22 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  843 Aug 10 03:22 tanowrt-image-full-swu-factory-sdimage-at91-swu-factory.wks
         -rw-r--r-- 2 6.6M Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.cpio.gz
         -rw-r--r-- 2 3.4K Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.manifest
         -rw-r--r-- 2   24 Aug 10 03:23 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.version
         -rw-r--r-- 2 348K Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.testdata.json
         -rw-r--r-- 2   95 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         -rw-r--r-- 2 128K Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2  192 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         -rw-r--r-- 2 128K Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2 726K Aug 10 03:20 u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2 719K Aug 10 03:20 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin


   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/at91-sama5d3-xplained-nand]$ ls -gGh
         total 123M
         -rwxr-xr-x 2  15K Aug 10 03:20 at91bootstrap.bin-nand
         -rw-r--r-- 2  16K Aug 10 03:20 at91bootstrap.bin-nand-pmecc
         -rwxr-xr-x 2  20K Aug 10 03:20 at91bootstrap.bin-sdcard
         -rw-r--r-- 2  20K Aug 10 03:20 at91bootstrap.bin-sdcard-pmecc
         -rw-r--r-- 2  49K Aug 10 03:23 at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.dtb
         lrwxrwxrwx 2  110 Aug 10 03:23 at91-sama5d3_xplained-at91-sama5d3-xplained-nand.dtb -> at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.dtb
         lrwxrwxrwx 2  110 Aug 10 03:23 at91-sama5d3_xplained.dtb -> at91-sama5d3_xplained--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.dtb
         lrwxrwxrwx 2   24 Aug 10 03:20 boot.bin-sdcard -> at91bootstrap.bin-sdcard
         lrwxrwxrwx 2   97 Aug 10 03:23 fitImage -> fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         -rw-r--r-- 2 4.5M Aug 10 03:23 fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         lrwxrwxrwx 2   97 Aug 10 03:23 fitImage-at91-sama5d3-xplained-nand.bin -> fitImage--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         -rw-r--r-- 2 1.6K Aug 10 03:23 fitImage-its--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.its
         lrwxrwxrwx 2  101 Aug 10 03:23 fitImage-its-at91-sama5d3-xplained-nand -> fitImage-its--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.its
         -rw-r--r-- 2 2.3K Aug 10 03:23 fitImage-its-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.its
         lrwxrwxrwx 2  164 Aug 10 03:23 fitImage-its-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-at91-sama5d3-xplained-nand -> fitImage-its-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.its
         -rw-r--r-- 2 4.4M Aug 10 03:23 fitImage-linux.bin--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         lrwxrwxrwx 2  107 Aug 10 03:23 fitImage-linux.bin-at91-sama5d3-xplained-nand -> fitImage-linux.bin--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         -rw-r--r-- 2  11M Aug 10 03:23 fitImage-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         lrwxrwxrwx 2  160 Aug 10 03:23 fitImage-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-at91-sama5d3-xplained-nand -> fitImage-tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.bin
         -rw-r--r-- 2 2.0M Aug 10 03:23 modules--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.tgz
         lrwxrwxrwx 2   96 Aug 10 03:23 modules-at91-sama5d3-xplained-nand.tgz -> modules--4.19.78+git0+046113c438-tano0.2.20.20.0.0-at91-sama5d3-xplained-nand-20220810001922.tgz
         drwxr-xr-x 7  260 Jul 27 16:28 sam-ba
         lrwxrwxrwx 2   11 Aug 10 03:20 startup-at91-sama5d3-xplained-nand.img -> startup.img
         lrwxrwxrwx 2   19 Aug 10 03:20 startup-at91-sama5d3-xplained-nand.img.version -> startup.img.version
         lrwxrwxrwx 2   19 Aug 10 03:20 startup-factory-at91-sama5d3-xplained-nand.img -> startup-factory.img
         lrwxrwxrwx 2   27 Aug 10 03:20 startup-factory-at91-sama5d3-xplained-nand.img.version -> startup-factory.img.version
         -rw-r--r-- 2  890 Aug 10 03:20 startup-factory.img
         -rw-r--r-- 2   19 Aug 10 03:20 startup-factory.img.version
         -rw-r--r-- 2 2.2K Aug 10 03:20 startup.img
         -rw-r--r-- 2   19 Aug 10 03:20 startup.img.version
         -rw-r--r-- 2  66K Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.manifest
         -rw-r--r-- 2  23M Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.version
         -rw-r--r-- 2 342K Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.testdata.json
         lrwxrwxrwx 2   76 Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand.manifest -> tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.manifest
         lrwxrwxrwx 2   80 Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand.squashfs-lzo -> tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.squashfs-lzo
         lrwxrwxrwx 2   74 Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand.testdata.json -> tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.testdata.json
         lrwxrwxrwx 2   75 Aug 10 03:23 tanowrt-image-full-at91-sama5d3-xplained-nand.version -> tanowrt-image-full-at91-sama5d3-xplained-nand-20220810001922.rootfs.version
         -rw-r--r-- 2  29M Aug 10 03:23 tanowrt-image-full-swu-at91-sama5d3-xplained-nand-20220810001922.swu
         lrwxrwxrwx 2   68 Aug 10 03:23 tanowrt-image-full-swu-at91-sama5d3-xplained-nand.swu -> tanowrt-image-full-swu-at91-sama5d3-xplained-nand-20220810001922.swu
         -rw-r--r-- 2  42M Aug 10 03:23 tanowrt-image-full-swu-factory-at91-sama5d3-xplained-nand-20220810001922.sdcard.img
         lrwxrwxrwx 2   83 Aug 10 03:23 tanowrt-image-full-swu-factory-at91-sama5d3-xplained-nand.sdcard.img -> tanowrt-image-full-swu-factory-at91-sama5d3-xplained-nand-20220810001922.sdcard.img
         -rw-r--r-- 2 6.2K Aug 10 03:22 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  843 Aug 10 03:22 tanowrt-image-full-swu-factory-sdimage-at91-swu-factory.wks
         -rw-r--r-- 2 6.6M Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.cpio.gz
         -rw-r--r-- 2 3.4K Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.manifest
         -rw-r--r-- 2   24 Aug 10 03:23 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.version
         -rw-r--r-- 2 348K Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.testdata.json
         lrwxrwxrwx 2   92 Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand.cpio.gz -> tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.cpio.gz
         lrwxrwxrwx 2   93 Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand.manifest -> tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.manifest
         lrwxrwxrwx 2   91 Aug 10 03:22 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand.testdata.json -> tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.testdata.json
         lrwxrwxrwx 2   92 Aug 10 03:23 tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand.version -> tanowrt-image-initramfs-swu-factory-at91-sama5d3-xplained-nand-20220810001922.rootfs.version
         lrwxrwxrwx 2   59 Aug 10 03:20 u-boot-at91-sama5d3-xplained-nand.bin -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   59 Aug 10 03:20 u-boot-at91-sama5d3-xplained-nand.bin-nand -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   61 Aug 10 03:20 u-boot-at91-sama5d3-xplained-nand.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   59 Aug 10 03:20 u-boot.bin -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   59 Aug 10 03:20 u-boot.bin-nand -> u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   61 Aug 10 03:20 u-boot.bin-sdcard -> u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   94 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-nand -> u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         lrwxrwxrwx 2   98 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-nand.bin -> u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2   95 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         -rw-r--r-- 2 128K Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   96 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard -> u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         lrwxrwxrwx 2  100 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard.bin -> u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2  192 Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         -rw-r--r-- 2 128K Aug 10 03:20 u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   94 Aug 10 03:20 u-boot-initial-env-nand -> u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         lrwxrwxrwx 2   98 Aug 10 03:20 u-boot-initial-env-nand.bin -> u-boot-initial-env-at91-sama5d3-xplained-nand-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         lrwxrwxrwx 2   96 Aug 10 03:20 u-boot-initial-env-sdcard -> u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2
         lrwxrwxrwx 2  100 Aug 10 03:20 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-at91-sama5d3-xplained-nand-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2 726K Aug 10 03:20 u-boot-nand-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin
         -rw-r--r-- 2 719K Aug 10 03:20 u-boot-sdcard-v2020.01-at91+gitAUTOINC+af59b26c22-tano0.2.bin


.. _sec-at91-sama5d3-xplained-flash:

Writing Images
==============

.. _sec-at91-sama5d3-xplained-flash-sd:

Writing Image to SD Card
-----------------------------

No special information about writing images to SD card
for SAMA5D3 Xplained. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing factory installation image for the ``at91-sama5d3-xplained-nand.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-swu-factory-at91-sama5d3-xplained-nand.sdcard.img of=/dev/mmcblk1

Writing bootable card image for the ``at91-sama5d3-xplained-sd.yml`` target to the SD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-at91-sama5d3-xplained-sd.sdcard.img of=/dev/mmcblk1


.. _sec-at91-sama5d3-xplained-flash-nand:

Writing Image to NAND Flash
---------------------------

For the initial flashing of the internal NAND memory it is recommended to use
the special image of the initial factory installation. If you choose a build target
(see :ref:`sec-at91-sama5d3-xplained-targets` for details) that assumes using the
factory installation image for the initial flashing of the
device, a factory installation image (:file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`)
will be automatically generated during the build process
(see :ref:`sec-at91-sama5d3-xplained-build`).
To write the factory installation image to a SD card, follow the instructions
from :ref:`sec-writing-sd-or-usb` section.

When you boot device from the prepaired SD card with factory installation image the installation
of TanoWrt to the internal NAND flash memory will be done automatically. The detailed
installation log is available on the :ref:`debug UART <sec-at91-sama5d3-xplained-serial>`.
After the installation is complete, the board will power off automatically. You need
to remove SD card with the installation image and power on the board. When the
board boots up, the installed system will be booted from the internal NAND flash memory.

.. caution:: Be aware that during the installation all existing data on the internal NAND
             flash will be permanently lost.



.. _sec-at91-sama5d3-xplained-spi-nand-erase:

Erasing Internal NAND Flash
---------------------------

You can erase the internal NAND flash memory in the following ways:

- Linux command line;
- U-Boot command line;
- special utilities and USB connection with PC.

.. tabs::

   .. tab:: Linux CLI

      .. rubric:: Erasing Internal NAND Flash Using Linux Command Line

      If the device has a bootable Linux system, you can erase the internal NAND flash
      by the following command entered in the Linux terminal:

      .. code-block:: console

         [root@tanowrt ~]# flash_erase /dev/mtd0 0 0
         Erasing 128 Kibyte @ 20000 -- 100 % complete

      .. note:: This command does not erase entire flash memory, but only
                single MTD device (:file:`/dev/mtd0`) of NAND flash memory,
                reserved for the AT91Bootstrap bootloader (see :ref:`fig-at91-sama5d3-xplained-layout-nand`).

      Note that if the running Linux distribution is not TanoWrt, the device
      name of the required MTD device may be different.
      To identify the proper device name, refer to the contents of the :file:`/proc/mtd` file:

      .. code-block:: console
         :emphasize-lines: 3

         [root@tanowrt /]# cat /proc/mtd
         dev:    size   erasesize  name
         mtd0: 00040000 00020000 "at91bootstrap"
         mtd1: 00140000 00020000 "uboot"
         mtd2: 00040000 00020000 "startup"
         mtd3: 00040000 00020000 "ubootenv"
         mtd4: 06800000 00020000 "system_a"
         mtd5: 06800000 00020000 "system_b"
         mtd6: 02e00000 00020000 "rootfs_data"

   .. tab:: U-Boot CLI

      .. rubric:: Erasing Internal NAND Flash Using U-Boot Command Line

      If the device has a functional U-Boot bootloader, you can erase
      internal NAND flash with the following commands entered at the U-Boot command line:

      .. code-block:: console

         => nand erase.chip

         NAND erase.chip: device 0 whole chip
         Erasing at 0xffe0000 -- 100% complete.
         OK
         =>

      Please note that if the running U-Boot bootloader
      is different from the one built as part of the TanoWrt distribution,
      the internal NAND flash erasing procedure may may differ from the one described above.

   .. tab:: USB Connection with PC

      .. rubric:: Erasing Internal NAND Flash Using Special Utilities and USB Connection with PC

      If the device fails to boot, e.g. due to a corrupted boot loader,
      the only way to erase the internal NAND flash memory is to use a USB
      connection with a PC and use special utilities.

      Refer to [AN8995]_ manual for details.


.. _sec-at91-sama5d3-xplained-booting:

Booting and Running
===================

The default boot sequence for SAMA5D3 devices
is shown in the table below.

+----------+----------------+---------------------+
| Sequence | Boot Device    | SAMA5D3 Xplained    |
+==========+================+=====================+
| 1        | SPI0 CS0       | --                  |
+----------+----------------+---------------------+
| 2        | MCI0           | SD Card             |
+----------+----------------+---------------------+
| 3        | MCI1           | --                  |
+----------+----------------+---------------------+
| 4        | EBI NAND       | Internal NAND Flash |
+----------+----------------+---------------------+
| 5        | SPI0 CS1       | --                  |
+----------+----------------+---------------------+
| 6        | TWI0 EEPROM    | --                  |
+----------+----------------+---------------------+
| 7        | SAM-BA Monitor                       |
+----------+--------------------------------------+

Booting from SD Card
--------------------

1. Insert the SD card into the slot on the SAMA5D3 Xplained (power is off).
2. Power on board.
3. TanoWrt will be booting from SD card.
4. Log in to system using default :ref:`credentials <sec-access-creds>`.


Booting from NAND Flash
-----------------------

1. Remove any bootable SD card from the slot on the SAMA5D3 Xplained (power is off).
2. Power on board.
3. TanoWrt will be booting from NAND flash (see :ref:`sec-at91-sama5d3-xplained-flash-nand`
   for details about writing TanoWrt image to NAND flash memory).
4. Log in to system using default :ref:`credentials <sec-access-creds>`.


.. _sec-at91-sama5d3-xplained-serial:

Serial Console
==============

Serial console on SAMA5D3 Xplained board are available through 6-pin
serial communication header J23 Debug.
Connect the USB to TTL serial cable as described below.
Don't connect the VCC wire of the USB to TTL converted,
connect only TX, RX and GND wires.

+-----------------------------------+-----------------+
| SAMA5D3 Xplained J23 6-pin Header | Signal          |
+===================================+=================+
| Pin 2                             | TX              |
+-----------------------------------+-----------------+
| Pin 3                             | RX              |
+-----------------------------------+-----------------+
| Pin 6                             | GND             |
+-----------------------------------+-----------------+

See :numref:`fig-at91-sama5d3-xplained` for example
connection USB to TTL converter with SAMA5D3 Xplained board.

The default serial console settings for SAMA5D3 Xplained for U-Boot and kernel
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


.. _sec-at91-sama5d3-xplained-network-config:

Default Network Configuration
=============================

SAMA5D3 Xplained board has two RJ45 Ethernet ports:

1. Ethernet port 1: interface ``eth0`` (Gigabit Ethernet)
2. Ethernet port 2: interface ``eth1`` (10/100 Ethernet)

By default Ethernet port 1 (``eth0`` interface) are joined into a bridge (``br-lan`` interface).
Bridge (``br-lan``) configured to obtain IP configuration via :term:`DHCP` client.
To see obtained IP configuration use the following command:

.. code-block:: console

   [root@tanowrt ~]# ifstatus lan | jsonfilter -e '@["ipv4-address"][0].address'
   192.168.0.39

In this example, the device got the IP address 192.168.0.39 via :term:`DHCP`.

The Ethernet port 2 (``eth1`` interface) is a separate network interface
included in the WAN firewall zone with enabled translation (NAT) from LAN zone.
The IP address of the WAN (``eth1``) interface is also configured with a :term:`DHCP` client.
A firewall with blocking rules for incoming traffic is enabled on the WAN
interface. Therefore, there is no access to the web configuration interface
through this interface.

To see obtained IP configuration for WAN network use the following command:

.. code-block:: console

   [root@tanowrt ~]# ifstatus wan | jsonfilter -e '@["ipv4-address"][0].address'
   10.10.0.168

In this example, the device got the IP address 10.10.0.168 via :term:`DHCP`
on the WAN interface (``eth1``).

Also you can connect to the board using USB network connection (``usb0`` interface).
USB network interface configured with static IP address 192.168.128.1 with enabled
:term:`DHCP` server with pool with single IP address for client (your PC) 192.168.128.100.

Ethernet ports ``eth0`` and ``eth1`` have enabled :term:`LLDP` for Rx and Tx state by default.


.. _sec-at91-sama5d3-xplained-webui:

Web User Interface
==================

The WebUI can be accessed via any Ethernet port bridged to LAN network
or via USB RNDIS connection through HTTP(s) protocol.
You must see something like this in browser:

.. _fig-at91-sama5d3-xplained-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-at91-sama5d3-xplained-luci-status:
.. figure:: images/at91-sama5d3-xplained-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-at91-sama5d3-xplained-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-at91-sama5d3-xplained-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


Additional Information
======================

Here are sections with various additional information about the
SAMA5D3 Xplained and the operation of TanoWrt on it.

.. toctree::

   factory-installation-to-nand.rst
   bootlog-nand.rst
   bootlog-sd.rst


References
==========

.. [ATSAMA5D3-XPLD] `SAMA5D3 Xplained Evaluation Kit User Guide <https://www.microchip.com/en-us/development-tool/atsama5d3-xpld>`_
.. [DS60001609] `SAMA5D3 Series Datasheet <https://ww1.microchip.com/downloads/aemDocuments/documents/MPU32/ProductDocuments/DataSheets/SAMA5D3-Series-Data-sheet-DS60001609b.pdf>`_
.. [AN8995] `Using SAM-BA for Linux on SAMA5D3 Xplained <http://ww1.microchip.com/downloads/en/DeviceDoc/Atmel-42328-Using-SAM-BA-for-Linux-on-SAMA5D3-Xplained_Training-Manual_AN8995.pdf>`_

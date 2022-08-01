.. SPDX-License-Identifier: MIT

.. _machine-rock-pi-s:

***************
Radxa ROCK Pi S
***************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-rock-pi-s-board:

Board Overview
==============

ROCK Pi S is a Rockchip RK3308 based SBC (Single Board Computer) by Radxa.
It equips a 64 bits quad-core processor, USB, ethernet, wireless connectivity
and voice detection engine at the size of 1.7 inch, make it perfect for IoT
and voice applications. ROCK Pi S comes in two RAxM sizes 256 MiB or 512 MiB DDR3,
and uses microSD card for OS and storage. Optionally, ROCK Pi S can provide
on board storage version with 1/2/4/8 GiB NAND flash.

.. _fig-rock-pi-s:
.. figure:: images/rock-pi-s.jpg
   :width: 1000
   :class: with-border

   ROCK Pi S Board

Photos
------

.. container:: flex

   .. _fig-rock-pi-s-top:
   .. figure:: images/rock-pi-s-top.jpeg
       :width: 350
       :class: with-border

       ROCK Pi S Top View

   .. _fig-rock-pi-s-bottom:
   .. figure:: images/rock-pi-s-bottom.jpeg
       :width: 350
       :class: with-border

       ROCK Pi S Bottom View


Specification
-------------

.. table:: ROCK Pi S Specification

   +------------+-------------------------------------------------------------+
   | Model      | ROCK Pi S                                                   |
   +============+=============================================================+
   | Processor  || SoC Rockchip RK3308                                        |
   |            || Quad Cortex-A35 ARM 64 bits processor                      |
   |            || frequency up to 1.3 GHz                                    |
   +------------+-------------------------------------------------------------+
   | Memory     | 256 MiB or 512 MiB DDR3                                     |
   +------------+-------------------------------------------------------------+
   | Storage    || microSD(TF)                                                |
   |            || optional on board 1/2/4/8 GiB NAND flash                   |
   +------------+-------------------------------------------------------------+
   | Wireless   || 802.11 b/g/n WiFi                                          |
   |            || Bluetooth 4.0 (RTL8723DS)                                  |
   |            || External antenna                                           |
   +------------+-------------------------------------------------------------+
   | USB        || 1 |times| USB 2.0 Type-A HOST                              |
   |            || 1 |times| USB 3.0 Type-C OTG                               |
   +------------+-------------------------------------------------------------+
   | Keys       || 1 |times| Maskrom                                          |
   |            || 1 |times| Reset                                            |
   +------------+-------------------------------------------------------------+
   | Ethernet   | 100 Mbit/s Ethernet, optional PoE (additional HAT requried) |
   +------------+-------------------------------------------------------------+
   | IO         || 26-pin expansion header                                    |
   |            || 4 |times| I2C                                              |
   |            || 3 |times| PWM                                              |
   |            || 2 |times| SPI                                              |
   |            || 3 |times| UART                                             |
   |            || 1 |times| I2S                                              |
   |            || 2 |times| 5V DC power in                                   |
   |            || 2 |times| 3.3 DC power in                                  |
   +------------+-------------------------------------------------------------+
   | Power      | USB Type-C DC 5V                                            |
   +------------+-------------------------------------------------------------+
   | Dimensions | 43.18 mm |times| 43.18 mm (1.7 inch square)                 |
   +------------+-------------------------------------------------------------+


Hardware Models
---------------

ROCK Pi S board comes with different versions. Not all versions are supported and tested with TanoWrt.

.. table:: ROCK Pi S Hardware Models

   +---------------+---------+---------+-----------+-----------+------------------------------------------+
   | SKU           | RAM     | SD NAND | WiFi/BT   | PoE       | TanoWrt Support                          |
   +===============+=========+=========+===========+===========+==========================================+
   | RS308-D2      | 256 MiB | |ndash| | |ndash|   | |ndash|   | Yes (not tested)                         |
   +---------------+---------+---------+-----------+-----------+------------------------------------------+
   | RS308-D4      | 512 MiB | |ndash| | |ndash|   | |ndash|   | Yes (not tested)                         |
   +---------------+---------+---------+-----------+-----------+------------------------------------------+
   | RS308-D4W     | 512 MiB | |ndash| | RTL8723DS | |ndash|   | Yes (not tested)                         |
   +---------------+---------+---------+-----------+-----------+------------------------------------------+
   | RS308-D4WP    | 512 MiB | |ndash| | RTL8723DS | Supported | Yes (not tested)                         |
   +---------------+---------+---------+-----------+-----------+------------------------------------------+
   | RS308-D4WPN8  | 512 MiB | 1 GiB   | RTL8723DS | Supported | Yes                                      |
   +---------------+---------+---------+-----------+-----------+------------------------------------------+
   | RS308-D4WPN64 | 512 MiB | 8 GiB   | RTL8723DS | Supported | Yes (not tested)                         |
   +---------------+---------+---------+-----------+-----------+------------------------------------------+


.. _sec-rock-pi-s-targets:

Build Targets
=============


.. _sec-rock-pi-s-machines:

Machines
--------

.. _table-rock-pi-s-machines:
.. table:: Supported Machines

   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_     | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +=================+==============================+==========================+====================================+========================+==========================+
   | ROCK Pi S       | ``rock-pi-s-sd.yml``         | ``rock-pi-s-sd``         | ``tanowrt-image-full-swu``         | microSD card           | |ndash|                  |
   |                 +------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   |                 | ``rock-pi-s-sdnand.yml``     | ``rock-pi-s-sdnand``     | ``tanowrt-image-full-swu-factory`` | internal SD NAND flash | microSD card             |
   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-rock-pi-s-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-rock-pi-s-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-rock-pi-s-images:

Images
------

.. _table-rock-pi-s-images:
.. table:: Supported Images
   :widths: 25, 25, 50

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
   |                           | ``tanowrt-image-full-swu-factory`` | ``rock-pi-s-sdnand.yml``     | Factory installation image for standard TanoWrt     |
   |                           |                                    |                              | image. When building this image,                    |
   |                           |                                    |                              | ``tanowrt-image-full``                              |
   |                           |                                    |                              | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                              | as dependencies.                                    |
   +---------------------------+------------------------------------+------------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-rock-pi-s-build` section).


.. _sec-rock-pi-s-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-rock-pi-s-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-rock-pi-s-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-rock-pi-s-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for ROCK Pi S Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For microSD Card

.. code-block:: console

   $ kas build targets/kas/rock-pi-s-sd.yml

Default images will be produced to boot and run from the microSD
card on the ROCK Pi S target board.

.. rubric:: For Internal SD NAND Flash

.. code-block:: console

   $ kas build targets/kas/rock-pi-s-sdnand.yml

An initial factory installation image will be generated,
intended to run from the microSD card. The installer image
will install the default image to the internal SD NAND flash
memory and further the ROCK Pi S board will boot
and run from the SD NAND flash memory.


.. _sec-rock-pi-s-partitioning:

Partitioning Layouts
====================

Images for ROCK Pi S board using default partitions layouts described :ref:`here <sec-hsl-rockchip-partitioning>`.


.. _sec-rock-pi-s-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-rock-pi-s-artifacts` for a description of some common (not all) build artifacts.

.. _table-rock-pi-s-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | Artifact                                                | Target(s)                     | Description                                                   |
   +=========================================================+===============================+===============================================================+
   | .. centered:: Bootloader                                                                                                                                |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`idblock.img-<MACHINE>-sdcard`                    | *All*                         | Rockchip IDBLOCK image for booting from SD card.              |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`idblock.img-<MACHINE>-sdnand`                    | ``rock-pi-s-sdnand.yml``      | Rockchip IDBLOCK image for booting from internal SD NAND.     |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | .. centered:: Bootloader (U-Boot)                                                                                                                       |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`startup-<MACHINE>.img`                           | *All*                         | U-Boot startup script.                                        |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`startup-factory-<MACHINE>.img`                   | ``rock-pi-s-sdnand.yml``      | U-Boot startup script for factory installation image.         |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdcard`             | *All*                         | U-Boot initial environment image for SD card image.           |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>-sdnand`             | ``rock-pi-s-sdnand.yml``      | U-Boot initial environment image for internal SD NAND flash.  |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdcard`                     | *All*                         | U-Boot binary image for booting from SD card.                 |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin-sdnand`                     | ``rock-pi-s-sdnand.yml``      | U-Boot binary image for booting from internal SD NAND flash.  |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                      |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.bin`                          | *All*                         | Flattened Image Tree (FIT) image with Linux kernel            |
   |                                                         |                               | and Device Tree Blobs (DTB).                                  |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`fitImage-<MACHINE>.ext4`                         | *All*                         | FIT image packed into an ext4 file system image.              |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`rk3308-rock-pi-s-<MACHINE>.dtb`                  | *All*                         | Target Device Tree Blob (DTB).                                |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                    |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`             | ``rock-pi-s-sd.yml``          | SD card image including all required partitions for booting   |
   |                                                         |                               | and running the system. This image is ready to be written     |
   |                                                         |                               | to the SD card using the :command:`dd` utility or similar     |
   |                                                         |                               | (see :ref:`sec-rock-pi-s-flash`).                             |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img` | ``rock-pi-s-sdnand.yml``      | SD card factory installation image. This image is ready       |
   |                                                         |                               | to be written to the SD card using the :command:`dd` utility  |
   |                                                         |                               | or similar (see :ref:`sec-rock-pi-s-flash`).                  |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`           | *All*                         | Root filesystem image (squashfs with LZO compression).        |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                | *All*                         | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.         |
   +---------------------------------------------------------+-------------------------------+---------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-rock-pi-s-targets_>`__. ``<rootfs-image>`` is replaced
          by the actual read-only root filesystem `image <sec-rock-pi-s-images_>`__ name.

For example, below is the complete list of artifacts produced by the
``rock-pi-s-sd.yml`` target build.

.. code-block:: console

   [~/tanowrt/build/tanowrt-glibc/deploy/images/rock-pi-s-sd]$ ls -gGh
   total 184M
   lrwxrwxrwx 2   84 Jun  7 06:29 fitImage -> fitImage--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.bin
   -rw-r--r-- 2 3.7M Jun  7 06:29 fitImage--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.bin
   -rw-r--r-- 2 5.5M Jun  7 06:29 fitImage-4.19.219+gitAUTOINC+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd.ext4
   -rw-r--r-- 2 1.6K Jun  7 06:29 fitImage-its--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.its
   lrwxrwxrwx 2   88 Jun  7 06:29 fitImage-its-rock-pi-s-sd -> fitImage-its--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.its
   -rw-r--r-- 2 3.7M Jun  7 06:29 fitImage-linux.bin--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.bin
   lrwxrwxrwx 2   94 Jun  7 06:29 fitImage-linux.bin-rock-pi-s-sd -> fitImage-linux.bin--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.bin
   lrwxrwxrwx 2   84 Jun  7 06:29 fitImage-rock-pi-s-sd.bin -> fitImage--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.bin
   -rw-r--r-- 2 5.5M Jun  7 06:29 fitImage-rock-pi-s-sd.ext4
   lrwxrwxrwx 2   72 Jun  7 05:55 idblock.img-rock-pi-s-sd-sdcard -> idblock.img-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   -rwxr-xr-x 2 110K Jun  7 05:55 idblock.img-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   lrwxrwxrwx 2   71 Jun  7 05:55 loader.bin-rock-pi-s-sd-sdcard -> loader.bin-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   -rwxr-xr-x 2 237K Jun  7 05:55 loader.bin-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19
   -rw-r--r-- 2  41M Jun  7 06:29 modules--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.tgz
   lrwxrwxrwx 2   83 Jun  7 06:29 modules-rock-pi-s-sd.tgz -> modules--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.tgz
   -rw-r--r-- 2  63K Jun  7 06:29 rk3308-rock-pi-s--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.dtb
   lrwxrwxrwx 2   92 Jun  7 06:29 rk3308-rock-pi-s.dtb -> rk3308-rock-pi-s--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.dtb
   lrwxrwxrwx 2   92 Jun  7 06:29 rk3308-rock-pi-s-rock-pi-s-sd.dtb -> rk3308-rock-pi-s--4.19.219+git0+40b753b965-tano1.2.20.20.1.0-rock-pi-s-sd-20220607032704.dtb
   -rw-r--r-- 2 2.2K Jun  7 11:40 startup.img
   -rw-r--r-- 2   16 Jun  7 11:40 startup.img.version
   lrwxrwxrwx 2   11 Jun  7 11:40 startup-rock-pi-s-sd.img -> startup.img
   lrwxrwxrwx 2   19 Jun  7 11:40 startup-rock-pi-s-sd.img.version -> startup.img.version
   -rw-r--r-- 2 5.1K Jun  7 12:02 tanowrt-image-full.env
   -rw-r--r-- 2  53K Jun  7 12:02 tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.manifest
   -rw-r--r-- 2 945M Jun  7 12:03 tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.sdcard.img
   -rw-r--r-- 2  26M Jun  7 12:02 tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.squashfs-lzo
   -rw-r--r-- 2   24 Jun  7 12:03 tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.version
   -rw-r--r-- 2 362K Jun  7 12:02 tanowrt-image-full-rock-pi-s-sd-20220607090114.testdata.json
   lrwxrwxrwx 2   62 Jun  7 12:02 tanowrt-image-full-rock-pi-s-sd.manifest -> tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.manifest
   lrwxrwxrwx 2   64 Jun  7 12:03 tanowrt-image-full-rock-pi-s-sd.sdcard.img -> tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.sdcard.img
   lrwxrwxrwx 2   66 Jun  7 12:02 tanowrt-image-full-rock-pi-s-sd.squashfs-lzo -> tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.squashfs-lzo
   lrwxrwxrwx 2   60 Jun  7 12:02 tanowrt-image-full-rock-pi-s-sd.testdata.json -> tanowrt-image-full-rock-pi-s-sd-20220607090114.testdata.json
   lrwxrwxrwx 2   61 Jun  7 12:03 tanowrt-image-full-rock-pi-s-sd.version -> tanowrt-image-full-rock-pi-s-sd-20220607090114.rootfs.version
   -rw-r--r-- 2 2.2K Jun  7 12:02 tanowrt-image-full-sdimage-rockchip-swu-a-b.wks
   -rw-r--r-- 2  34M Jun  7 12:03 tanowrt-image-full-swu-rock-pi-s-sd-20220607090114.swu
   lrwxrwxrwx 2   54 Jun  7 12:03 tanowrt-image-full-swu-rock-pi-s-sd.swu -> tanowrt-image-full-swu-rock-pi-s-sd-20220607090114.swu
   lrwxrwxrwx 2   85 Jun  7 05:55 u-boot-initial-env-rock-pi-s-sd-sdcard -> u-boot-initial-env-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7
   -rw-r--r-- 2  459 Jun  7 05:55 u-boot-initial-env-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7
   -rw-r--r-- 2  32K Jun  7 05:55 u-boot-initial-env-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7.bin
   lrwxrwxrwx 2   89 Jun  7 05:55 u-boot-initial-env-rock-pi-s-sd-sdcard.bin -> u-boot-initial-env-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7.bin
   lrwxrwxrwx 2   85 Jun  7 05:55 u-boot-initial-env-sdcard -> u-boot-initial-env-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7
   lrwxrwxrwx 2   89 Jun  7 05:55 u-boot-initial-env-sdcard.bin -> u-boot-initial-env-rock-pi-s-sd-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7.bin
   lrwxrwxrwx 2   64 Jun  7 05:55 u-boot-rock-pi-s-sd.bin -> u-boot-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7.bin
   lrwxrwxrwx 2   64 Jun  7 05:55 u-boot-rock-pi-s-sd.bin-sdcard -> u-boot-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7.bin
   -rw-r--r-- 2 2.0M Jun  7 05:55 u-boot-sdcard-2017.09+gitAUTOINC+e3ca3c3805_fe66a9be19-tano7.bin


.. _sec-rock-pi-s-flash:

Writing Images
==============


.. _sec-rock-pi-s-flash-sd:

Writing Image to microSD Card
-----------------------------

No special information about writing images to microSD card
for ROCK Pi S board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing factory installation image for the ``rock-pi-s-sdnand.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-swu-factory-rock-pi-s-sdnand.sdcard.img of=/dev/mmcblk1

Writing bootable image for the ``rock-pi-s-sd.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-rock-pi-s-sd.sdcard.img of=/dev/mmcblk1


.. _sec-rock-pi-s-flash-sdnand:

Writing Image to SD NAND Flash
------------------------------

For the initial flashing of the internal SD NAND memory it is recommended to use
the special image of the initial factory installation. If you choose a build target
(see :ref:`sec-rock-pi-s-targets` for details) that assumes using the
factory installation image for the initial flashing of the
device, a factory installation image (:file:`<rootfs-image>-swu-factory-<MACHINE>.sdcard.img`)
will be automatically generated during the build process
(see :ref:`sec-rock-pi-s-build`).
To write the factory installation image to an SD card, follow the instructions
from :ref:`sec-writing-sd-or-usb` section.

When you boot device from the prepaired SD card with factory installation image the installation
of TanoWrt to the internal SD NAND flash memory will be done automatically. The detailed
installation log is available on the :ref:`debug UART <sec-rock-pi-s-serial>`. After the installation is complete,
the board will shut down automatically. When the device is turned on, the installed system
will be booted from the internal SD NAND flash memory.

.. caution:: Be aware that during the installation all existing data on the internal SD NAND
             flash memory will be permanently lost.


.. _sec-rock-pi-s-booting:

Booting and Running
===================

Booting from SD Card
--------------------

1. Insert the micro SD card to the board.
2. Use a USB Type-C to USB Type-A cable, connect the board to your PC.
3. ROCK Pi S will boot, the green power led is on, and after a while, the blue led start blinking.
4. (Optional) Use a USB to TTL serial cable to make a connection between
   your PC and ROCK Pi S. See :ref:`sec-rock-pi-s-serial` section for details.
5. Log in to system using default :ref:`credentials <sec-access-creds>`.

.. attention:: The internal SD NAND flash memory must be empty in order to perform
               a boot from the SD card. See :ref:`sec-rock-pi-s-sdnand-erase`
               section for details about erasing internal SD NAND flash.


Booting from Internal SD NAND Flash
-----------------------------------

1. Use a USB Type-C to USB Type-A cable, connect the board to your PC.
2. ROCK Pi S will boot, the green power led is on, and after a while, the blue led start blinking.
3. (Optional) Use a USB to TTL serial cable to make a connection between
   your PC and ROCK Pi S. See :ref:`sec-rock-pi-s-serial` section for details.
4. Log in to system using default :ref:`credentials <sec-access-creds>`.


.. _sec-rock-pi-s-sdnand-erase:

Erasing Internal SD NAND Flash
------------------------------

You can erase the internal SD NAND flash memory in the following ways:

- Linux command line;
- U-Boot command line;
- special utilities and USB connection with PC.

.. tabs::

   .. tab:: Linux CLI

      .. rubric:: Erasing SD NAND Using Linux Command Line

      If the device has a bootable Linux system, you can erase the SD NAND
      by the following command entered in the Linux terminal:

      .. code-block:: console

         [root@tanowrt ~]# dd if=/dev/zero of=/dev/mmcblk0 bs=1k count=32768

      Note that if the Linux distribution flashed on the SD NAND flash memory
      is not TanoWrt, the device name of the SD NAND flash memory may be different.
      You can usually find out the real device name by analysing the
      system boot log by running the command dmesg. For example:

      .. code-block:: console
         :emphasize-lines: 2,3

         [root@tanowrt ~]# dmesg | grep mmcblk
         [    0.829147] mmcblk0: mmc0:21c9 XTSDA 990 MiB
         [    0.840930]  mmcblk0: p1 p2 p3 p4 p5 p6
         [    5.025192] mount_root: /dev/mmcblk0p1: p1, rw, start 8388608, size 8388608
         [    5.033574] mount_root: /dev/mmcblk0p2: p2, rw, start 16777216, size 50331648
         [    5.034476] mount_root: /dev/mmcblk0p3: p3, rw, start 67108864, size 310378496 [rootfs]
         [    5.042884] mount_root: /dev/mmcblk0p4: p4, rw, start 377487360, size 50331648
         [    5.045888] mount_root: /dev/mmcblk0p5: p5, rw, start 427819008, size 310378496
         [    5.054576] mount_root: /dev/mmcblk0p6: p6, rw, start 738197504, size 298844160 [overlay]
         [    5.055393] mount_root: root filesystem on the /dev/mmcblk0p3 partition of /dev/mmcblk0 (rw) device
         [    5.083521] mount_root: founded suitable overlay partition /dev/mmcblk0p6
         [    5.847535] EXT4-fs (mmcblk0p6): recovery complete
         [    5.849379] EXT4-fs (mmcblk0p6): mounted filesystem with ordered data mode. Opts: (null)
         [    5.892056] EXT4-fs (mmcblk0p6): mounted filesystem with ordered data mode. Opts: (null)
         [   13.972921] mmcblk1: mmc1:aaaa SS16G 14.8 GiB
         [   13.987047]  mmcblk1: p1 p2

      This output shows that device :file:`/dev/mmcblk0` is SD NAND flash
      memory and device :file:`/dev/mmcblk1` is SD card.

   .. tab:: U-Boot CLI

      .. rubric:: Erasing SD NAND Using U-Boot Command Line

      If the device has a functional U-Boot bootloader, you can erase
      SD NAND with the following commands entered at the U-Boot command line:

      .. code-block:: console

         => mmc dev 0
         switch to partitions #0, OK
         mmc0 is current device
         => mmc erase 0 10000

         MMC erase: dev # 0, block # 0, count 65536 ... 65536 blocks erased: OK
         =>

      Please note that if the U-Boot bootloader on the SD NAND flash memory
      is different from the one built as part of the TanoWrt distribution,
      the device number of the SD NAND flash memory may be different from
      the one shown above. Use the :command:`mmc dev`, :command:`mmc list` and :command:`mmc info`
      commands to identify the valid SD NAND flash memory device number:

      .. code-block:: console
         :emphasize-lines: 3,5,8

         => mmc dev 0
         switch to partitions #0, OK
         mmc0 is current device
         => mmc info
         Device: dwmmc@ff490000
         Manufacturer ID: b
         OEM: 5854
         Name: XTSDA
         Timing Interface: Legacy
         Tran Speed: 52000000
         Rd Block Len: 512
         SD version 2.0
         High Capacity: No
         Capacity: 990 MiB
         Bus Width: 4-bit
         Erase Group Size: 512 Bytes
         =>

   .. tab:: USB Connection with PC

      .. rubric:: Erasing SD NAND Using Special Utilities and USB Connection with PC

      If the device fails to boot, e.g. due to a corrupted boot loader,
      the only way to erase the SD NAND flash memory is to use a USB
      connection with a PC and use special utilities. To do this, follow
      `these <https://wiki.radxa.com/RockpiS/dev/sdnand-install>`__
      instructions from the official Radxa wiki page.


.. _sec-rock-pi-s-serial:

Serial Console
==============

.. note:: The default baudrate of ROCK Pi S is 1500000 (1.5 Mbps), please check
          if your USB to TTL cable support 1.5 Mbps baudrate. Some model of CP210X
          and PL2303x have baudrate limitation, please check the specified model.

.. rubric:: Connection

Connect the USB to TTL serial cable as described below. Don't connect the VCC wire,
connect only TX, RX and GND wires.

+--------+-------------------------------------+
| Signal | ROCK Pi S Board                     |
|        +------------------+------------------+
|        | V1.0             | V1.1 and later   |
+========+==================+==================+
| RX     | Pin 9            | Pin 10           |
+--------+------------------+------------------+
| TX     | Pin 11           | Pin 8            |
+--------+------------------+------------------+
| GND    | Pin 25           | Pin 6            |
+--------+------------------+------------------+

See :numref:`fig-rock-pi-s` for example
connection USB to TTL converter with ROCK Pi S V1.1 or later.

.. rubric:: Serial Setting on Host PC

The default serial setting for ROCK Pi S U-Boot and kernel console is:

- baudrate: 1500000
- data bit: 8
- stop bit: 1
- parity: none
- flow control: none


.. _sec-rock-pi-s-network-config:

Default Network Configuration
=============================

By default Ethernet port (``eth0`` interface) are joined into a bridge (``br-lan`` interface).
Bridge (``br-lan``) configured to obtain IP configuration via :term:`DHCP` client.
To see obtained IP configuration use the following command:

.. code-block:: console

   [root@tanowrt ~]# ifstatus lan | jsonfilter -e '@["ipv4-address"][0].address'
   192.168.0.38

In this example, the device got the IP address 192.168.0.38 via :term:`DHCP`.

Also you can connect to the board using USB network connection (``usb0`` interface).
USB network interface configured with static IP address 192.168.128.1 with enabled
:term:`DHCP` server with pool with single IP address for client (your PC) 192.168.128.100.

WiFi module RTL8723DS currently is not supported in TanoWrt.


.. _sec-rock-pi-s-webui:

Web User Interface
=============================

The WebUI can be accessed via Ethernet port or USB network connection through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-rock-pi-s-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-rock-pi-s-luci-status:
.. figure:: images/rock-pi-s-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-rock-pi-s-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-rock-pi-s-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


Additional Information
======================

Here are sections with various additional information about the
Radxa ROCK Pi S board and the operation of TanoWrt on it.

.. toctree::

   bootlog-u-boot.rst
   bootlog-kernel.rst


References
==========

1. ROCK Pi S (https://wiki.radxa.com/RockpiS)
2. GPIO Pinout for ROCK Pi S (https://wiki.radxa.com/RockpiS/hardware/gpio)

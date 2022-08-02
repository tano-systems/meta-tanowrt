.. SPDX-License-Identifier: MIT

.. _machine-rpi3:

*************************
Raspberry Pi 3 Model B/B+
*************************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-rpi3-board:

Board Overview
==============

Single-board computer with wireless LAN and Bluetooth connectivity.
1.4 GHz 64-bit quad-core processor, dual-band wireless LAN, Bluetooth 4.2/BLE, faster Ethernet,
and Power-over-Ethernet support (with separate PoE HAT).


Photos
------

.. container:: flex

   .. _fig-rpi3-angle-1:
   .. figure:: images/rpi3-angle-1.jpg
       :class: with-border
       :width: 400

       Raspberry Pi 3 Model B+ Angle View

   .. _fig-rpi3-angle-2:
   .. figure:: images/rpi3-angle-2.jpg
       :class: with-border
       :width: 400

       Raspberry Pi 3 Model B+ Angle View

   .. _fig-rpi3-top-down:
   .. figure:: images/rpi3-top-down.jpg
       :class: with-border
       :width: 400

       Raspberry Pi 3 Model B+ Top View

   .. _fig-rpi3-detail-1:
   .. figure:: images/rpi3-detail-1.jpg
       :class: with-border
       :width: 400

       Raspberry Pi 3 Model B+ Detail View (Connectors)

   .. _fig-rpi3-detail-2:
   .. figure:: images/rpi3-detail-2.jpg
       :class: with-border
       :width: 400

       Raspberry Pi 3 Model B+ Detail View (microSD Card Slot)


Specification
-------------

.. table:: Raspberry Pi 3 Specification

   +--------------+------------------------------------------------------------------------------+
   | Model        | Raspberry Pi 3                                                               |
   +==============+==============================================================================+
   | Processor    || Broadcom BCM2837B0                                                          |
   |              || 4 x Cortex-A53 (ARMv8) 64-bit                                               |
   |              || frequency up to 1.4 GHz                                                     |
   +--------------+------------------------------------------------------------------------------+
   | Memory       || 1 GiB LPDDR2 SDRAM                                                          |
   +--------------+------------------------------------------------------------------------------+
   | Storage      || microSD                                                                     |
   +--------------+------------------------------------------------------------------------------+
   | USB          || 4 |times| Type A USB 2.0 Host LS/FS/HS                                      |
   +--------------+------------------------------------------------------------------------------+
   | Video        || 1 |times| Full-size HDMI                                                    |
   +--------------+------------------------------------------------------------------------------+
   | Audio        || 1 |times| 3.5 mm Audio Jack                                                 |
   +--------------+------------------------------------------------------------------------------+
   | Ethernet     || 1 |times| Gigabit Ethernet over USB 2.0 (maximum throughput 300 Mbps)       |
   +--------------+------------------------------------------------------------------------------+
   | Wireless     || 2.4 GHz and 5 GHz IEEE 802.11.b/g/n/ac wireless LAN                         |
   |              || Bluetooth 4.2, BLE                                                          |
   +--------------+------------------------------------------------------------------------------+
   | Other        || Externded 40-pin GPIO header                                                |
   |              || CSI camera port for connecting a Raspberry Pi camera                        |
   |              || DSI display port for connecting a Raspberry Pi touchscreen display          |
   |              || Power-over-Ethernet (PoE) support (requires separate PoE HAT)               |
   |              || 4-pole stereo output and composite video port                               |
   +--------------+------------------------------------------------------------------------------+
   | Power        | 5V/2.5A DC                                                                   |
   +--------------+------------------------------------------------------------------------------+


.. _sec-rpi3-targets:

Build Targets
=============

.. _sec-rpi3-machines:

Machines
--------

.. _table-rpi3-machines:
.. table:: Supported Machines

   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Board\ [#]_     | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +=================+==============================+==========================+====================================+========================+==========================+
   | Raspberry Pi 3  | ``rpi3-sd.yml``              | ``rpi3-sd``              | ``tanowrt-image-full``             | microSD card           | |ndash|                  |
   | Model B/B+      |                              |                          |                                    |                        |                          |
   +-----------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-rpi3-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-rpi3-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-rpi3-images:

Images
------

.. _table-rpi3-images:
.. table:: Supported Images
   :widths: 15, 15, 15, 55

   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+
   | Read-Only Root Filesystem | Recipe\ [#]_                       | Supported by Target(s)     | Description                                         |
   | Image                     |                                    |                            |                                                     |
   +===========================+====================================+============================+=====================================================+
   | ``tanowrt-image-full``    | ``tanowrt-image-full``             | *All*                      | Standard TanoWrt image.                             |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-rpi3-build` section).


.. _sec-rpi3-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-rpi3-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-rpi3-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-rpi3-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for Raspberry Pi 3 Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. rubric:: For SD Card

.. code-block:: console

   $ kas build targets/kas/rpi3-sd.yml

Default images will be produced to boot and run from the SD
card on the Raspberry Pi 3 target board.


.. _sec-rpi3-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-rpi3-artifacts` for a description of some common (not all) build artifacts.

.. _table-rpi3-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | Artifact                                                            | Target(s)                     | Description                                                          |
   +=====================================================================+===============================+======================================================================+
   | .. centered:: Bootloader (U-Boot)                                                                                                                                          |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`boot.scr`                                                    | *All*                         | U-Boot startup script.                                               |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-initial-env-<MACHINE>`                                | *All*                         | U-Boot initial environment image for microSD card image.             |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`u-boot-<MACHINE>.bin`                                        | *All*                         | U-Boot binary image for booting from microSD card.                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Linux Kernel and DTB                                                                                                                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`uImage-<MACHINE>.bin`                                        | *All*                         | Linux kernel image                                                   |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`*.dtb`                                                       | *All*                         | Target Device Tree Blobs (:term:`DTB`).                              |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`*.dtbo`                                                      | *All*                         | Target Device Tree Blob overlay files (:term:`DTBO`).                |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                                       |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.sdcard.img`                         | ``rpi3-sd.yml``               | SD card image including all required partitions for booting          |
   |                                                                     |                               | and running the system. This image is ready to be written            |
   |                                                                     |                               | to the SD card using the :command:`dd` utility or similar            |
   |                                                                     |                               | (see :ref:`sec-rpi3-flash`).                                         |
   +---------------------------------------------------------------------+-------------------------------+----------------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifact file names are replaced by
          the actual value of the ``MACHINE`` BitBake variable for the chosen
          `target <sec-rpi3-targets_>`__. ``<rootfs-image>`` is replaced
          by the actual read-only root filesystem `image <sec-rpi3-images_>`__ name.

For example, below is the lists of artifacts produced by the ``rpi3-sd.yml`` target build.
There are two types of listings here |mdash| a complete listing, and a reduced
listing without the symbolic links and :term:`DTBO` files display.

.. rubric:: Build Artifacts Listings for ``rpi3-sd.yml`` Target

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/rpi3-sd]$ ls -gGh | grep -v -e "dtbo$" | grep -v -e "^l"
         total 89M
         -rw-r--r-- 2  28K Jul 25 05:08 bcm2710-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         -rw-r--r-- 2  29K Jul 25 05:08 bcm2710-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         drwxr-xr-x 2   60 Jul 25 05:08 bcm2835-bootfiles
         -rw-r--r-- 2  21K Jul 25 05:08 bcm2837-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         -rw-r--r-- 2  21K Jul 25 05:08 bcm2837-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         drwxr-xr-x 2  440 Jul 25 04:45 bootfiles
         -rw-r--r-- 2  263 Jul 25 04:45 boot.scr
         -rw-r--r-- 2   33 Jul 25 04:58 fw_env.config-rpi3-sd-2021.01-r0.rpi0
         -rw-r--r-- 2  15M Jul 25 05:08 modules-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.tgz
         -rw-r--r-- 2 6.5K Jul 25 05:11 tanowrt-image-full.env
         -rw-r--r-- 2 156K Jul 25 05:10 tanowrt-image-full-rpi3-sd-20220725014408.rootfs.manifest
         -rw-r--r-- 2 127M Jul 25 05:11 tanowrt-image-full-rpi3-sd-20220725014408.rootfs.sdcard.img
         -rw-r--r-- 2   24 Jul 25 05:11 tanowrt-image-full-rpi3-sd-20220725014408.rootfs.version
         -rw-r--r-- 2 343K Jul 25 05:10 tanowrt-image-full-rpi3-sd-20220725014408.testdata.json
         -rw-r--r-- 2 3.9K Jul 25 04:58 u-boot-initial-env-rpi3-sd-2021.01-r0.rpi0
         -rw-r--r-- 2 485K Jul 25 04:58 u-boot-rpi3-sd-2021.01-r0.rpi0.bin
         -rw-r--r-- 2 6.4M Jul 25 05:08 uImage-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.bin


   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/rpi3-sd]$ ls -gGh
         total 89M
         -rw-r--r-- 2 1.6K Jul 25 05:08 at86rf233-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   62 Jul 25 05:08 at86rf233.dtbo -> at86rf233-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   62 Jul 25 05:08 at86rf233-rpi3-sd.dtbo -> at86rf233-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2  28K Jul 25 05:08 bcm2710-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   67 Jul 25 05:08 bcm2710-rpi-3-b.dtb -> bcm2710-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         -rw-r--r-- 2  29K Jul 25 05:08 bcm2710-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   72 Jul 25 05:08 bcm2710-rpi-3-b-plus.dtb -> bcm2710-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   72 Jul 25 05:08 bcm2710-rpi-3-b-plus-rpi3-sd.dtb -> bcm2710-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   67 Jul 25 05:08 bcm2710-rpi-3-b-rpi3-sd.dtb -> bcm2710-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         drwxr-xr-x 2   60 Jul 25 05:08 bcm2835-bootfiles
         -rw-r--r-- 2  21K Jul 25 05:08 bcm2837-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   67 Jul 25 05:08 bcm2837-rpi-3-b.dtb -> bcm2837-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         -rw-r--r-- 2  21K Jul 25 05:08 bcm2837-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   72 Jul 25 05:08 bcm2837-rpi-3-b-plus.dtb -> bcm2837-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   72 Jul 25 05:08 bcm2837-rpi-3-b-plus-rpi3-sd.dtb -> bcm2837-rpi-3-b-plus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         lrwxrwxrwx 2   67 Jul 25 05:08 bcm2837-rpi-3-b-rpi3-sd.dtb -> bcm2837-rpi-3-b-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtb
         drwxr-xr-x 2  440 Jul 25 04:45 bootfiles
         -rw-r--r-- 2  263 Jul 25 04:45 boot.scr
         -rw-r--r-- 2 1.1K Jul 25 05:08 disable-bt-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   63 Jul 25 05:08 disable-bt.dtbo -> disable-bt-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   63 Jul 25 05:08 disable-bt-rpi3-sd.dtbo -> disable-bt-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2  801 Jul 25 05:08 dwc2-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   57 Jul 25 05:08 dwc2.dtbo -> dwc2-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   57 Jul 25 05:08 dwc2-rpi3-sd.dtbo -> dwc2-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   37 Jul 25 04:58 fw_env.config -> fw_env.config-rpi3-sd-2021.01-r0.rpi0
         lrwxrwxrwx 2   37 Jul 25 04:58 fw_env.config-rpi3-sd -> fw_env.config-rpi3-sd-2021.01-r0.rpi0
         -rw-r--r-- 2   33 Jul 25 04:58 fw_env.config-rpi3-sd-2021.01-r0.rpi0
         -rw-r--r-- 2 1.3K Jul 25 05:08 gpio-ir-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 gpio-ir.dtbo -> gpio-ir-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 gpio-ir-rpi3-sd.dtbo -> gpio-ir-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.3K Jul 25 05:08 gpio-ir-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 gpio-ir.dtbo -> gpio-ir-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 gpio-ir-rpi3-sd.dtbo -> gpio-ir-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.1K Jul 25 05:08 gpio-ir-tx-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   63 Jul 25 05:08 gpio-ir-tx.dtbo -> gpio-ir-tx-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   63 Jul 25 05:08 gpio-ir-tx-rpi3-sd.dtbo -> gpio-ir-tx-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.4K Jul 25 05:08 gpio-key-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   61 Jul 25 05:08 gpio-key.dtbo -> gpio-key-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   61 Jul 25 05:08 gpio-key-rpi3-sd.dtbo -> gpio-key-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2  779 Jul 25 05:08 hifiberry-amp-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   66 Jul 25 05:08 hifiberry-amp.dtbo -> hifiberry-amp-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   66 Jul 25 05:08 hifiberry-amp-rpi3-sd.dtbo -> hifiberry-amp-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2  655 Jul 25 05:08 hifiberry-dac-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   66 Jul 25 05:08 hifiberry-dac.dtbo -> hifiberry-dac-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.9K Jul 25 05:08 hifiberry-dacplus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   70 Jul 25 05:08 hifiberry-dacplus.dtbo -> hifiberry-dacplus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   70 Jul 25 05:08 hifiberry-dacplus-rpi3-sd.dtbo -> hifiberry-dacplus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   66 Jul 25 05:08 hifiberry-dac-rpi3-sd.dtbo -> hifiberry-dac-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2  959 Jul 25 05:08 hifiberry-digi-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   67 Jul 25 05:08 hifiberry-digi.dtbo -> hifiberry-digi-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   67 Jul 25 05:08 hifiberry-digi-rpi3-sd.dtbo -> hifiberry-digi-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 6.4K Jul 25 05:08 i2c-rtc-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 i2c-rtc.dtbo -> i2c-rtc-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 i2c-rtc-rpi3-sd.dtbo -> i2c-rtc-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.3K Jul 25 05:08 iqaudio-dac-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   64 Jul 25 05:08 iqaudio-dac.dtbo -> iqaudio-dac-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.5K Jul 25 05:08 iqaudio-dacplus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   68 Jul 25 05:08 iqaudio-dacplus.dtbo -> iqaudio-dacplus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   68 Jul 25 05:08 iqaudio-dacplus-rpi3-sd.dtbo -> iqaudio-dacplus-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   64 Jul 25 05:08 iqaudio-dac-rpi3-sd.dtbo -> iqaudio-dac-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.8K Jul 25 05:08 mcp2515-can0-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   65 Jul 25 05:08 mcp2515-can0.dtbo -> mcp2515-can0-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   65 Jul 25 05:08 mcp2515-can0-rpi3-sd.dtbo -> mcp2515-can0-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.8K Jul 25 05:08 miniuart-bt-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   64 Jul 25 05:08 miniuart-bt.dtbo -> miniuart-bt-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   64 Jul 25 05:08 miniuart-bt-rpi3-sd.dtbo -> miniuart-bt-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2  15M Jul 25 05:08 modules-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.tgz
         lrwxrwxrwx 2   59 Jul 25 05:08 modules-rpi3-sd.tgz -> modules-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.tgz
         -rw-r--r-- 2 1.5K Jul 25 05:08 pitft22-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 pitft22.dtbo -> pitft22-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 pitft22-rpi3-sd.dtbo -> pitft22-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 2.4K Jul 25 05:08 pitft28-capacitive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   71 Jul 25 05:08 pitft28-capacitive.dtbo -> pitft28-capacitive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   71 Jul 25 05:08 pitft28-capacitive-rpi3-sd.dtbo -> pitft28-capacitive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 2.7K Jul 25 05:08 pitft28-resistive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   70 Jul 25 05:08 pitft28-resistive.dtbo -> pitft28-resistive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   70 Jul 25 05:08 pitft28-resistive-rpi3-sd.dtbo -> pitft28-resistive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 2.8K Jul 25 05:08 pitft35-resistive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   70 Jul 25 05:08 pitft35-resistive.dtbo -> pitft35-resistive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   70 Jul 25 05:08 pitft35-resistive-rpi3-sd.dtbo -> pitft35-resistive-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.2K Jul 25 05:08 pps-gpio-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   61 Jul 25 05:08 pps-gpio.dtbo -> pps-gpio-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   61 Jul 25 05:08 pps-gpio-rpi3-sd.dtbo -> pps-gpio-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2  842 Jul 25 05:08 rpi-ft5406-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   63 Jul 25 05:08 rpi-ft5406.dtbo -> rpi-ft5406-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   63 Jul 25 05:08 rpi-ft5406-rpi3-sd.dtbo -> rpi-ft5406-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 2.9K Jul 25 05:08 rpi-poe-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 rpi-poe.dtbo -> rpi-poe-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 rpi-poe-rpi3-sd.dtbo -> rpi-poe-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 6.5K Jul 25 05:11 tanowrt-image-full.env
         -rw-r--r-- 2 156K Jul 25 05:10 tanowrt-image-full-rpi3-sd-20220725014408.rootfs.manifest
         -rw-r--r-- 2 127M Jul 25 05:11 tanowrt-image-full-rpi3-sd-20220725014408.rootfs.sdcard.img
         -rw-r--r-- 2   24 Jul 25 05:11 tanowrt-image-full-rpi3-sd-20220725014408.rootfs.version
         -rw-r--r-- 2 343K Jul 25 05:10 tanowrt-image-full-rpi3-sd-20220725014408.testdata.json
         lrwxrwxrwx 2   57 Jul 25 05:10 tanowrt-image-full-rpi3-sd.manifest -> tanowrt-image-full-rpi3-sd-20220725014408.rootfs.manifest
         lrwxrwxrwx 2   59 Jul 25 05:11 tanowrt-image-full-rpi3-sd.sdcard.img -> tanowrt-image-full-rpi3-sd-20220725014408.rootfs.sdcard.img
         lrwxrwxrwx 2   55 Jul 25 05:10 tanowrt-image-full-rpi3-sd.testdata.json -> tanowrt-image-full-rpi3-sd-20220725014408.testdata.json
         lrwxrwxrwx 2   56 Jul 25 05:11 tanowrt-image-full-rpi3-sd.version -> tanowrt-image-full-rpi3-sd-20220725014408.rootfs.version
         lrwxrwxrwx 2   34 Jul 25 04:58 u-boot.bin -> u-boot-rpi3-sd-2021.01-r0.rpi0.bin
         lrwxrwxrwx 2   42 Jul 25 04:58 u-boot-initial-env -> u-boot-initial-env-rpi3-sd-2021.01-r0.rpi0
         lrwxrwxrwx 2   42 Jul 25 04:58 u-boot-initial-env-rpi3-sd -> u-boot-initial-env-rpi3-sd-2021.01-r0.rpi0
         -rw-r--r-- 2 3.9K Jul 25 04:58 u-boot-initial-env-rpi3-sd-2021.01-r0.rpi0
         -rw-r--r-- 2 485K Jul 25 04:58 u-boot-rpi3-sd-2021.01-r0.rpi0.bin
         lrwxrwxrwx 2   34 Jul 25 04:58 u-boot-rpi3-sd.bin -> u-boot-rpi3-sd-2021.01-r0.rpi0.bin
         lrwxrwxrwx 2   58 Jul 25 05:08 uImage -> uImage-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.bin
         -rw-r--r-- 2 6.4M Jul 25 05:08 uImage-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.bin
         lrwxrwxrwx 2   58 Jul 25 05:08 uImage-rpi3-sd.bin -> uImage-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.bin
         -rw-r--r-- 2 1.5K Jul 25 05:08 vc4-fkms-v3d-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   65 Jul 25 05:08 vc4-fkms-v3d.dtbo -> vc4-fkms-v3d-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   65 Jul 25 05:08 vc4-fkms-v3d-rpi3-sd.dtbo -> vc4-fkms-v3d-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 2.7K Jul 25 05:08 vc4-kms-v3d-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   64 Jul 25 05:08 vc4-kms-v3d.dtbo -> vc4-kms-v3d-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   64 Jul 25 05:08 vc4-kms-v3d-rpi3-sd.dtbo -> vc4-kms-v3d-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.1K Jul 25 05:08 w1-gpio-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 w1-gpio.dtbo -> w1-gpio-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         -rw-r--r-- 2 1.2K Jul 25 05:08 w1-gpio-pullup-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   67 Jul 25 05:08 w1-gpio-pullup.dtbo -> w1-gpio-pullup-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   67 Jul 25 05:08 w1-gpio-pullup-rpi3-sd.dtbo -> w1-gpio-pullup-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo
         lrwxrwxrwx 2   60 Jul 25 05:08 w1-gpio-rpi3-sd.dtbo -> w1-gpio-1-5.10.31-tano0.2.7.20.4-rpi3-sd-20220725014408.dtbo


.. _sec-rpi3-flash:

Writing Images
==============


.. _sec-rpi3-flash-sd:

Writing Image to SD Card
-----------------------------

No special information about writing images to microSD card
for Raspberry Pi 3 board. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing bootable image for the ``rpi3-sd.yml`` target to the microSD
card :file:`/dev/mmcblk1`:

.. code-block:: console

   $ dd if=tanowrt-image-full-rpi3-sd.sdcard.img of=/dev/mmcblk1


.. _sec-rpi3-booting:

Booting and Running
===================

Booting from microSD Card
-------------------------

1. Insert the microSD card into the slot on the Raspberry Pi 3 board (power is off).
2. Power on board.
3. TanoWrt will be booting from microSD card.
4. Log in to system using default :ref:`credentials <sec-access-creds>`.


.. _sec-rpi3-serial:

Serial Console
==============

.. attention:: There is no serial port on Raspberry Pi 3 boards


.. _sec-rpi3-network-config:

Default Network Configuration
=============================

By default Ethernet port (``eth0`` interface) are joined into a bridge
(``br-lan`` interface). Bridge (``br-lan``) configured with static IP address 192.168.0.1/24 with
enabled :term:`DHCP` server.

The Ethernet port (``eth0``) has enabled :term:`LLDP` by default.


.. _sec-rpi3-upgrade:

Firmware Upgrade
================

.. attention:: Currently, the firmware upgrading for the Raspberry Pi 3 boards is not supported.


References
==========

1. https://www.raspberrypi.com/products/raspberry-pi-3-model-b/

.. SPDX-License-Identifier: MIT

.. _machine-mbm:
.. _machine-mbm10:
.. _machine-mbm20:

*******************
MBM 1.0/2.0 (TF307)
*******************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-mbm-board:

Board Overview
==============

A system board in the mini-ITX form factor. The board's compact
and unified design is suitable for any desktop solution compatible
with the mini-ITX form factor.

The figures below shows the MBM 2.0 board (TF307-MB-S-D). The MBM 1.0 board has
a similar design and only minor differences.

.. _fig-mbm20-top:
.. figure:: images/tf307-mb-s-d-top.webp
   :width: 1000

   MBM 2.0 (TF307-MB-S-D) Board Top View

.. _fig-mbm20-front:
.. figure:: images/tf307-mb-s-d-front.webp
   :width: 1000

   MBM 2.0 (TF307-MB-S-D) Board Front View

Photos
------

.. container:: flex

   .. _fig-mbm10-top:
   .. figure:: images/tf307-mb-s-c-top.webp
       :width: 400
       :class: with-border

       MBM 1.0 (TF307-MB-S-C) Board Top View

   .. _fig-mbm20-angled-1:
   .. figure:: images/tf307-mb-s-d-angled-1.jpg
       :width: 400
       :class: with-border

       MBM 2.0 (TF307-MB-S-D) Angled View

   .. _fig-mbm20-angled-2:
   .. figure:: images/tf307-mb-s-d-angled-2.jpg
       :width: 400
       :class: with-border

       MBM 2.0 (TF307-MB-S-D) Another Angled View


Specification
-------------

.. table:: MBM 1.0/2.0 (TF307) Specification

   +-------------+-------------------------------------------------------------+
   | Model       | MBM 1.0/2.0 (TF307)                                         |
   +=============+=============================================================+
   | Processor   || Baikal Electronics BE-M1000                                |
   |             || 8-core ARM Cortex-A57 64 bits processor,                   |
   |             || frequency up to 1.5 GHz                                    |
   +-------------+-------------------------------------------------------------+
   | Memory      || up to 64 GiB DDR4 2133 (ECC supported)                     |
   |             || 2 |times| 288-pin DIMM sockets                             |
   +-------------+-------------------------------------------------------------+
   | Audio       || HD audio codec                                             |
   +-------------+-------------------------------------------------------------+
   | I/O panel   || 1 |times| HDMI output                                      |
   | connectors  || 4 |times| USB 2.0                                          |
   |             || 2 |times| PS/2                                             |
   |             || 2 |times| RJ45 1000BASE-T                                  |
   |             || 1 |times| Linear audio output                              |
   |             || 1 |times| Linear audio input                               |
   |             || 1 |times| Microphone input                                 |
   +-------------+-------------------------------------------------------------+
   | Front panel || 1 |times| HD audio connector                               |
   | I/O         || 1 |times| front panel connector (buttons, LED's)           |
   | connectors  || 2 |times| USB 3.0                                          |
   +-------------+-------------------------------------------------------------+
   | Expansion   || 1 |times| M.2 M key (PCIe 3.0 x4) for NVME SSD             |
   | slots       || 1 |times| PCIe 3.0 x8                                      |
   +-------------+-------------------------------------------------------------+
   | Internal    || 2 |times| SATA 3.0                                         |
   | connectors  || 2 |times| 4-pin connectors for FAN                         |
   |             || 1 |times| LVDS (4 channels)                                |
   |             || 1 |times| LCD backlight connector                          |
   |             || 3 |times| USB 3.0                                          |
   |             || 1 |times| 24-pin power                                     |
   +-------------+-------------------------------------------------------------+
   | Power       | ATX 24                                                      |
   +-------------+-------------------------------------------------------------+
   | Dimensions  | 170 mm |times| 170 mm                                       |
   +-------------+-------------------------------------------------------------+


.. _sec-mbm-targets:

Build Targets
=============

.. _sec-mbm-machines:

Machines
--------

.. _table-mbm-machines:
.. table:: Supported Machines

   +--------------+--------------------------+-----------------------+---------------+----------------------------+------------------------+--------------------------+
   | Board\ [#]_  | Hardware Revision        | Target YAML\ [#]_     | Machine\ [#]_ | Target Recipe(s)\ [#]_     | Running Media\ [#]_    | Installation Media\ [#]_ |
   +==============+==========================+=======================+===============+============================+========================+==========================+
   | MBM 1.0      || TF-307-MB-S-A (Rev 1.0) | ``mbm10.yml``         | ``mbm10``     | ``tanowrt-image-full-swu`` | USB Flash              | |ndash|                  |
   |              || TF-307-MB-S-B (Rev 2.0) |                       |               |                            |                        |                          |
   |              || TF-307-MB-S-C (Rev 3.0) |                       |               |                            |                        |                          |
   +--------------+--------------------------+-----------------------+---------------+                            |                        |                          |
   | MBM 2.0      || TF-307-MB-S-D (Rev 4.0) | ``mbm20.yml``         | ``mbm20``     |                            |                        |                          |
   +--------------+--------------------------+-----------------------+---------------+----------------------------+------------------------+--------------------------+

.. [#] Target board.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-mbm-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-mbm-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.


.. _sec-mbm-images:

Images
------

.. _table-mbm-images:
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
   | ``tanowrt-image-qt5``     | ``tanowrt-image-qt5``              | *All*                      | Standard TanoWrt image with Qt5 support.            |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-qt5-swu``          | *All*                      | Standard TanoWrt image with Qt5 support             |
   |                           |                                    |                            | and :ref:`firmware upgrade <sec-firmware-upgrade>`  |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-qt5`` will also be built            |
   |                           |                                    |                            | as dependency.                                      |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-mbm-build` section).


.. _sec-mbm-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-mbm-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-mbm-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-mbm-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Images for MBM 1.0 Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build targets/kas/mbm10.yml


Build Default Images for MBM 2.0 Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build targets/kas/mbm20.yml


Build ``tanowrt-image-full-qt5`` Image for MBM 2.0 Board
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build --target tanowrt-image-full-qt5 targets/kas/mbm20.yml


.. _sec-mbm-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-mbm-artifacts` for a description of some common (not all) build artifacts.

.. _table-mbm-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | Artifact                                        | Target(s) | Description                                                   |
   +=================================================+===========+===============================================================+
   | .. centered:: Bootloader                                                                                                    |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`grub-efi-bootaa64.efi`                   | *All*     | GRUB bootloader image                                         |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`grub-efi-grubenv`                        | *All*     | GRUB bootloader initial environment                           |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`grub-efi-startup.nsh`                    | *All*     | Startup script for UEFI shell                                 |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`grub.cfg`                                | *All*     | GRUB bootloader configuration file                            |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | .. centered:: Linux Kernel                                                                                                  |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`Image-<MACHINE>.bin`                     | *All*     | Linux kernel binary image                                     |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | .. centered:: Images                                                                                                        |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.wic`            | *All*     | Bootable image including all required partitions for booting  |
   |                                                 |           | and running the system. This image is ready to be written     |
   |                                                 |           | to the USB flash drive using the :command:`dd` utility        |
   |                                                 |           | or similar (see :ref:`sec-mbm-flash`).                        |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`   | *All*     | Root filesystem image (squashfs with LZO compression).        |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`        | *All*     | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.         |
   +-------------------------------------------------+-----------+---------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifacts file names are replaced by the actual
          value of the ``MACHINE`` BitBake variable for the chosen `target <sec-mbm-targets_>`__.
          ``<rootfs-image>`` is replaced by the actual read-only rootfs filesystem `image <sec-mbm-images_>`__ name.

For example, below is the complete list of artifacts produced by the
``mbm20.yml`` target build.

.. code-block:: console

   [~/tanowrt/build/tanowrt-glibc/deploy/images/mbm20]$ ls -gGh
   total 300M
   drwxr-xr-x 2   40 May 20 18:10 grub
   -rwxr-xr-x 2 2.2K May 20 18:10 grub.cfg
   -rw-r--r-- 2 804K May 20 06:13 grub-efi-bootaa64.efi
   -rw-r--r-- 2   23 May 20 06:13 grub-efi-bootaa64.efi.version
   -rwxr-xr-x 2 1.0K May 20 18:10 grub-efi-grubenv
   -rw-r--r-- 2   27 May 20 06:13 grub-efi-startup.nsh
   lrwxrwxrwx 2   57 Jul 23 02:07 Image -> Image--5.4.156-tano3.2.12.20.0.0-mbm20-20220722230057.bin
   -rw-r--r-- 2  17M Jul 23 02:07 Image--5.4.156-tano3.2.12.20.0.0-mbm20-20220722230057.bin
   -rw-r--r-- 2  21M Jul 23 02:07 Image-5.4.156-tano3.2.12.20.0.0-mbm20.ext4
   lrwxrwxrwx 2   57 Jul 23 02:07 Image-mbm20.bin -> Image--5.4.156-tano3.2.12.20.0.0-mbm20-20220722230057.bin
   -rw-r--r-- 2  21M Jul 23 02:07 Image-mbm20.ext4
   -rw-r--r-- 2  13M Jul 23 02:07 modules--5.4.156-tano3.2.12.20.0.0-mbm20-20220722230057.tgz
   lrwxrwxrwx 2   59 Jul 23 02:07 modules-mbm20.tgz -> modules--5.4.156-tano3.2.12.20.0.0-mbm20-20220722230057.tgz
   -rw-r--r-- 2 4.8K Jul 23 02:09 tanowrt-image-full.env
   -rw-r--r-- 2 1.9K Jul 23 02:09 tanowrt-image-full-image-baikal-m-swu-a-b.wks
   -rw-r--r-- 2  85K Jul 23 02:09 tanowrt-image-full-mbm20-20220722230057.rootfs.manifest
   -rw-r--r-- 2  44M Jul 23 02:09 tanowrt-image-full-mbm20-20220722230057.rootfs.squashfs-lzo
   -rw-r--r-- 2   24 Jul 23 02:10 tanowrt-image-full-mbm20-20220722230057.rootfs.version
   -rw-r--r-- 2 915M Jul 23 02:09 tanowrt-image-full-mbm20-20220722230057.rootfs.wic
   -rw-r--r-- 2 336K Jul 23 02:09 tanowrt-image-full-mbm20-20220722230057.testdata.json
   lrwxrwxrwx 2   55 Jul 23 02:09 tanowrt-image-full-mbm20.manifest -> tanowrt-image-full-mbm20-20220722230057.rootfs.manifest
   lrwxrwxrwx 2   59 Jul 23 02:09 tanowrt-image-full-mbm20.squashfs-lzo -> tanowrt-image-full-mbm20-20220722230057.rootfs.squashfs-lzo
   lrwxrwxrwx 2   53 Jul 23 02:09 tanowrt-image-full-mbm20.testdata.json -> tanowrt-image-full-mbm20-20220722230057.testdata.json
   lrwxrwxrwx 2   54 Jul 23 02:10 tanowrt-image-full-mbm20.version -> tanowrt-image-full-mbm20-20220722230057.rootfs.version
   lrwxrwxrwx 2   50 Jul 23 02:09 tanowrt-image-full-mbm20.wic -> tanowrt-image-full-mbm20-20220722230057.rootfs.wic
   -rw-r--r-- 2  66M Jul 23 02:10 tanowrt-image-full-swu-mbm20-20220722230057.swu
   lrwxrwxrwx 2   47 Jul 23 02:10 tanowrt-image-full-swu-mbm20.swu -> tanowrt-image-full-swu-mbm20-20220722230057.swu


.. _sec-mbm-flash:

Writing Images
==============


.. _sec-mbm-flash-usb:

Wriging Image to USB Flash Drive
--------------------------------

No special information about writing images to USB flash drive
for described boards. See common instructions in :ref:`sec-writing-sd-or-usb` section.

.. rubric:: Examples

Writing bootable card image for the ``mbm20.yml`` target to the USB flash drive
:file:`/dev/sdc`:

.. code-block:: console

   $ dd if=tanowrt-image-full-mbm20.wic of=/dev/sdc


.. _sec-mbm-booting:

Booting and Running
===================

To successfully run TanoWrt on MBM 1.0/2.0 boards UEFI firmware from
`Baikal-M SDK 5.2 20210608 <https://www.baikalelectronics.ru/products/238/>`__
or higher is required. Follow the instructions from the SDK to update the
UEFI firmware on MBM 1.0/2.0 board.


Booting from USB Flash Drive
----------------------------

1. Power off board.
2. Insert prepared USB flash drive with TanoWrt to any
   USB 2.0 or 3.0 port (see section :ref:`sec-mbm-board`).
3. Power on board.
4. Press ESC to enter UEFI setup utility.
5. Select :menuselection:`Boot Manager` in menu and press :kbd:`ENTER`.
6. Select your USB flash drive in boot manager menu and press :kbd:`ENTER`.
   TanoWrt will be booting from selected USB flash drive.
7. When the system boots, log in using the default :ref:`credentials <sec-access-creds>`.


.. _sec-mbm-network-config:

Default Network Configuration
=============================

By default, network ports Ethernet 1 (``eth0``) and Ethernet 2 (``eth1``)
are joined into a bridge (``br-lan`` interface) with the :term:`RSTP`
protocol enabled. Bridge (``br-lan``) configured with static IP address
192.168.0.1/24 with enabled :term:`DHCP` server.

Ethernet ports 1 (``eth0``) and 2 (``eth1``) have enabled :term:`LLDP` by default.


.. _sec-mbm-webui:

Web User Interface
=============================

The WebUI can be accessed via Ethernet port or USB network connection through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-mbm-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-mbm-luci-status:
.. figure:: images/tf307-mb-luci-status.webp
   :width: 900

   LuCI WebUI Overview Page


.. _sec-mbm-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-mbm-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


References
==========

#. `TF307 Motherboard (Russian) <https://edelweiss-tech.ru/product/komplektuyushchie-edelveys/platy-edelveys/plata-na-baykal-m/>`__
#. `TF307 Board Operation Manual (Russian) <https://edelweiss-tech.ru/upload/iblock/322/32283684576ee67cc75a8ba5989ddfd5.pdf>`__

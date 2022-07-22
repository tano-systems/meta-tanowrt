.. SPDX-License-Identifier: MIT

.. include:: <xhtml1-lat1.txt>
.. include:: <xhtml1-special.txt>


.. _device-mbm:
.. _device-mbm10:
.. _device-mbm20:

**************************************
Baikal Electronics MBM 1.0/2.0 (TF307)
**************************************

.. _sec-mbm-board:

Board Overview
==============

A system board in the mini-ITX form factor. The board's compact
and unified design is suitable for any desktop solution compatible
with the mini-ITX form factor.


Interfaces and Connectors
-------------------------

The figures below shows the MBM 2.0 board (TF307-MB-S-D). The MBM 1.0 board has
a similar design and only minor differences.

.. _fig-mbm20-top:
.. figure:: images/tf307-mb-s-d-top.webp
    :width: 1000

    Baikal Electronics MBM 2.0 (TF307-MB-S-D) Top View

.. _fig-mbm20-front:
.. figure:: images/tf307-mb-s-d-front.webp
    :width: 1000

    Baikal Electronics MBM 2.0 (TF307-MB-S-D) Front View

More Photos
-----------

.. _fig-mbm10-top:
.. figure:: images/tf307-mb-s-c-top.webp
    :width: 400
    :class: with-border

    Baikal Electronics MBM 1.0 (TF307-MB-S-C) Board Top View


Specification
-------------

.. table:: Baikal Electronics MBM 1.0/2.0 (TF307) Specification

   +-------------+-------------------------------------------------------------+
   | Model       | Baikal Electronics MBM 1.0/2.0                              |
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

Supported Build Targets
=======================

.. _table-mbm-supported-targets:
.. table:: Supported Build Targets

   +----------+--------------------------+-----------------+-------------+------------------------+--------------------+
   | Board    |  Hardware Revision       | Target          | Machine     | Running Media          | Installation Media |
   +==========+==========================+=================+=============+========================+====================+
   | MBM 1.0  || TF-307-MB-S-A (Rev 1.0) | ``mbm10.yml``   | ``mbm10``   | USB Flash              | |ndash|            |
   |          || TF-307-MB-S-B (Rev 2.0) |                 |             |                        |                    |
   |          || TF-307-MB-S-C (Rev 3.0) |                 |             |                        |                    |
   +----------+--------------------------+-----------------+-------------+------------------------+--------------------+
   | MBM 2.0  || TF-307-MB-S-D (Rev 4.0) | ``mbm20.yml``   | ``mbm20``   | USB Flash              | |ndash|            |
   +----------+--------------------------+-----------------+-------------+------------------------+--------------------+

Short explanations for the table columns:

- Board: target board.
- Target: target YML-file located in ``kas/targets`` directory.
- Machine: target machine name stored in the ``MACHINE`` BitBake variable.
- Running Media: external or internal data storage where the TanoWrt operating system is running.
- Installation Media: external storage device for which an installation image is generated. When booting from
  the Installation Media, the TanoWrt system is installed on the Running Media storage.
- Root Filesystem Image: root file system image recipe name (see :ref:`sec-mbm-images` to see supported images).


.. _sec-mbm-images:

Supported Images
================

Supported root filesystem images are listed in the
:numref:`table-mbm-supported-images`.

.. _table-mbm-supported-images:
.. table:: Supported Images

   +-----------------------------------+----------------------+--------------------------+
   | Image Recipe (``<image-recipe>``) | Supported Target(s)  | Description              |
   +===================================+======================+==========================+
   | ``tanowrt-image-full``            | *All*                | Standard TanoWrt image   |
   +-----------------------------------+----------------------+--------------------------+
   | ``tanowrt-image-full-swu``        | *All*                | Standard TanoWrt image   |
   |                                   |                      | (SWUpdate upgrade image) |
   +-----------------------------------+----------------------+--------------------------+
   | ``tanowrt-image-qt5``             | *All*                | Standard TanoWrt image   |
   |                                   |                      | with Qt5 support         |
   +-----------------------------------+----------------------+--------------------------+
   | ``tanowrt-image-qt5-swu``         | *All*                | Standard TanoWrt image   |
   |                                   |                      | with Qt5 support         |
   |                                   |                      | (SWUpdate upgrade image) |
   +-----------------------------------+----------------------+--------------------------+


.. _sec-mbm-build:

Build
=====

.. include:: ../../../../include/kas-common-part.rst.inc

By default, unless otherwise specified, a root file system image specified
in the target configuration is built. The default root filesystem image
recipe name for each supported target can be found in Table
:ref:`table-mbm-supported-targets` (Root Filesystem Image column).
If you need to build a different image for the target, the following build
command should be used:

.. code-block:: console

   $ kas build --target <image-recipe> kas/targets/<target-yml-file>

.. seealso:: 

   - See :ref:`sec-mbm-targets` section to select the required target file.
   - See :ref:`sec-mbm-images` section to select the required root filesystem image recipe.
   - See :ref:`sec-mbm-artifacts` section for detailed information
     about the produced build artifacts.



.. _sec-mbm-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the ``~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>`` directory.

.. note:: ``<MACHINE>`` in the artifacts path is replaced by the actual value of the
          ``MACHINE`` BitBake variable for the chosen `target <sec-mbm-targets_>`__.

Refer to table :ref:`table-mbm-artifacts` for a description of some common (not all) build artifacts.

.. _table-mbm-artifacts:
.. table:: Produced Build Artifacts

   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | Artifact                                    | Target(s) | Description                                                   |
   +=============================================+===========+===============================================================+
   | .. centered:: Bootloader                                                                                                |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``grub-efi-bootaa64.efi``                   | *All*     | GRUB bootloader image                                         |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``grub-efi-grubenv``                        | *All*     | GRUB bootloader initial environment                           |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``grub-efi-startup.nsh``                    | *All*     | Startup script for UEFI shell                                 |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``grub.cfg``                                | *All*     | GRUB bootloader configuration file                            |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | .. centered:: Linux Kernel                                                                                              |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``Image-<MACHINE>.bin``                     | *All*     | Linux kernel binary image                                     |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | .. centered:: Images                                                                                                    |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``<image-recipe>-<MACHINE>.wic``            | *All*     | Bootable image including all required partitions for booting  |
   |                                             |           | and running the system. This image is ready to be written     |
   |                                             |           | to the USB flash drive using the :command:`dd` utility        |
   |                                             |           | or similar (see :ref:`sec-mbm-flash`).                        |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``<image-recipe>-<MACHINE>.squashfs-lzo``   | *All*     | Root filesystem image (squashfs with LZO compression).        |
   +---------------------------------------------+-----------+---------------------------------------------------------------+
   | ``<image-recipe>-swu-<MACHINE>.swu``        | *All*     | SWUpdate image for updating system                            |
   |                                             |           | (see :ref:`sec-mbm-upgrade`).                                 |
   +---------------------------------------------+-----------+---------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifact name is replaced by the actual value of the
          ``MACHINE`` BitBake variable for the chosen `target <sec-mbm-targets_>`__.
          ``<image-recipe>`` is replaced by the actual `image recipe <sec-mbm-images_>`__ name.

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

Flash Images
============


.. _sec-mbm-flash-usb:

Flash Image to USB Flash Drive
-------------------------------

Use the :command:`dd` utility to write the generated ``.wic`` images to the USB
flash drive.

For example:

.. code-block:: console

   $ sudo dd if=~/tanowrt/build/tanowrt-glibc/deploy/images/mbm20/tanowrt-image-full-mbm20.wic \
             of=/dev/sdc \
             bs=1k

Where ``/dev/sdc`` is the device name of the USB flash drive.

.. tip:: For writing USB flash drive under Windows we recommend to use
         `PassMark imageUSB utility <https://www.osforensics.com/tools/write-usb-images.html>`__.


.. _sec-mbm-booting:

Booting and Running
===================

.. important:: To successfully run TanoWrt on MBM 1.0/2.0 boards UEFI firmware from
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
7. TanoWrt from selected USB flash drive will be booting.


.. _sec-mbm-access-credentials:

Access Credentials
------------------

The following credentials are used by default to access the system via
CLI (Command Line Interface), SSH (Secure SHell) or/and WebUI (Web User Interface):

- Username: ``root``
- Password: ``root``


.. _sec-mbm-network-config:

Default Network Configuration
-----------------------------

By default, network ports Ethernet 1 (``eth0``) and Ethernet 2 (``eth1``)
are joined into a bridge (``br-lan`` interface) with the :term:`RSTP`
protocol enabled. Bridge (``br-lan``) configured with static IP address
192.168.0.1/24 with enabled :term:`DHCP` server.

Ethernet ports 1 (``eth0``) and 2 (``eth1``) have enabled :term:`LLDP` by default.


.. _sec-mbm-webui:

WebUI
-----

The WebUI can be accessed via Ethernet port or USB network connection through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-mbm-luci-login:
.. figure:: ../../../../common/images/luci-login.png
    :width: 900

    LuCI WebUI Login Page

.. _fig-mbm-luci-status:
.. figure:: images/luci-status.webp
    :width: 900

    LuCI WebUI Overview Page


.. _sec-mbm-upgrade:

Upgrade Running System
======================

You can upgrade the running system via CLI (Command Line Interface) or WebUI (Web User Interface).


CLI (Command Line Interface)
----------------------------

Use the following commands to upgrade the firmware using the command line:

- Upgrade from a local file (e.g. on an USB flash drive):

  .. code-block:: console

     [root@tanowrt ~]# swupdate-client /mnt/sdb1/<image-recipe>-swu-<MACHINE>.swu

- Upgrade from a file hosted on a remote HTTP server:

  .. code-block:: console

     [root@tanowrt ~]# wget -O- http://remote.server.com/path/to/<image-recipe>-swu-<MACHINE>.swu | swupdate-client


WebUI (Web User Interface)
--------------------------

To upgrade the firmware using the web interface,
choose :menuselection:`System --> Firmware Upgrade` in the main menu.
Next, follow the instructions on the page.

.. _fig-mbm-luci-swupdate:
.. figure:: ../../../../common/images/luci-swupdate.png
    :width: 900

    LuCI WebUI Firmware Upgrade Page


References
==========

#. `TF307 Motherboard (Russian) <https://edelweiss-tech.ru/product/komplektuyushchie-edelveys/platy-edelveys/plata-na-baykal-m/>`__
#. `TF307 Board Operation Manual (Russian) <https://edelweiss-tech.ru/upload/iblock/322/32283684576ee67cc75a8ba5989ddfd5.pdf>`__

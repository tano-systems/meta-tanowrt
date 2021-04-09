.. SPDX-License-Identifier: MIT

*************************
Building TanoWrt with kas
*************************

.. note:: `kas <http://github.com/siemens/kas>`_ is a setup tool for
          bitbake based projects.

.. note:: The recommended Linux distribution on host system
          is Ubuntu 20.04 or Ubuntu 18.04.

Install Required Packages
=========================

For a successful build, additional packages must be installed.
To do this, run the command:

.. code-block:: shell

   # sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
          build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
          xz-utils debianutils iputils-ping

.. note:: Some targets may require additional dependencies for
          a successful build. See the ``README.md`` of the related
          hardware support layer (HSL) for this information.

Install kas
===========

To install kas into your python site-package repository, run:

.. code-block:: shell

   # pip3 install kas

See `official kas documentation <https://kas.readthedocs.io/en/latest/userguide.html#dependencies-installation>`_ for more details

Clone TanoWrt Repository
========================

Clone TanoWrt core layer repository to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: shell

   # cd ~
   # git clone https://github.com/tano-systems/meta-tanowrt tanowrt

Initialize and Update Submodules
================================

When using the kas utility, it is required to have BitBake at the root
of the meta-tanowrt layer. The BitBake submodule must be initialized
and updated before building:

.. code-block:: shell

   # cd ~/tanowrt
   # git submodule update --init

Build TanoWrt
=============

To build TanoWrt images, you have to run the ``kas build`` command with the
path to the YML-target file as an argument.

.. code-block:: shell

   # cd ~/tanowrt
   # kas build <yml-target-file>

For example, to build TanoWrt images for internal eMMC flash of the NXP LS1028A RDB board
you need to run following commands from ``meta-tanowrt`` root directory:

.. code-block:: shell

   # cd ~/tanowrt
   # kas build kas/targets/ls1028ardb-emmc.yml

See `Available Targets <targets_>`__ to select the proper target file.

.. _targets:

Available Targets
=================

YML-target files are located in ``kas/targets`` subfolder of the ``meta-tanowrt`` repository.
Available target files listed in the sections below:

- `Virtual Machines <meta-tanowrt_>`__
- `Microchip (Atmel) SoC's <meta-tanowrt-hsl-atmel_>`__
- `NXP (Freescale) SoC's <meta-tanowrt-hsl-nxp_>`__
- `Raspberry Pi Boards <meta-tanowrt-hsl-rpi_>`__
- `Sierra Wireless LTE Modules <meta-tanowrt-hsl-swi_>`__
- `Texas Instruments SoC's <meta-tanowrt-hsl-ti_>`__
- Targets with extra (commercial) features:

  - `Sierra Wireless LTE Modules <meta-tanowrt-hsl-swi-extras_>`__

.. _meta-tanowrt:

Virtual Machines (`meta-tanowrt <../meta-tanowrt/README.md>`__)
---------------------------------------------------------------

The TanoWrt core layer (`meta-tanowrt <../meta-tanowrt/README.md>`__) provides support for
a bunch of target devices intended to run in a virtual environment (QEMU, VirtualBox, etc.).

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| QEMU/ARMv5 (ARM926EJ-S) machine                   | ARMv5                      | Virtual NAND flash | ``qemuarm.yml``                  |
+---------------------------------------------------+                            |                    +----------------------------------+
| QEMU/ARMv5 (ARM926EJ-S) machine                   |                            |                    | ``qemuarm-screen.yml``           |
| with screen support                               |                            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| QEMU/ARMv8 (aarch64) machine                      | ARMv8 (aarch64)            | Virtual NAND flash | ``qemuarm64.yml``                |
+---------------------------------------------------+                            |                    +----------------------------------+
| QEMU/ARMv8 (aarch64) machine                      |                            |                    | ``qemuarm64-screen.yml``         |
| with screen support                               |                            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| QEMU/Common x86 (32-bit) machine machine          | Generic x86                | Virtual HDD        | ``qemux86.yml``                  |
+---------------------------------------------------+                            |                    +----------------------------------+
| QEMU/Common x86 (32-bit) machine machine          |                            |                    | ``qemux86-screen.yml``           |
| with screen support                               |                            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| QEMU/Common x86 (64-bit) machine machine          | Generic x86-64             | Virtual HDD        | ``qemux86-64.yml``               |
+---------------------------------------------------+                            |                    +----------------------------------+
| QEMU/Common x86 (64-bit) machine machine          |                            |                    | ``qemux86-64-screen.yml``        |
| with screen support                               |                            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-atmel:

Microchip (Atmel) SoC's (`meta-tanowrt-hsl-atmel <../meta-tanowrt-hsl-atmel/README.md>`__)
------------------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| KSZ9477 Managed Switch Evaluation Kit with        | Microchip SAMA5D36         | NAND flash         | ``evb-ksz9477-nand.yml``         |
| SAMA5D36 MPU (EVB-KSZ9477)                        |                            |                    |                                  |
|                                                   |                            +--------------------+----------------------------------+
|                                                   |                            | SD-card            | ``evb-ksz9477-sd.yml``           |
|                                                   |                            |                    |                                  |
+---------------------------------------------------+                            +--------------------+----------------------------------+
| KSZ9563 Plug-in Evaluation Board (EVB-KSZ9563)    |                            | NAND flash         | ``evb-ksz9563-nand.yml``         |
| on SAMA5D3 Ethernet Development System (DM320114) |                            |                    |                                  |
|                                                   |                            +--------------------+----------------------------------+
|                                                   |                            | SD-card            | ``evb-ksz9563-sd.yml``           |
|                                                   |                            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-nxp:

NXP (Freescale) SoC's (`meta-tanowrt-hsl-nxp <../meta-tanowrt-hsl-nxp/README.md>`__)
------------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| NXP LS1028A RDB                                   | NXP LS1028A                | eMMC flash         | ``ls1028ardb-emmc.yml``          |
| (Reference Design Board)                          |                            +--------------------+----------------------------------+
|                                                   |                            | SD-card            | ``ls1028ardb-sd.yml``            |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-rpi:

Raspberry Pi Boards (`meta-tanowrt-hsl-rpi <../meta-tanowrt-hsl-rpi/README.md>`__)
----------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| Raspberry Pi 3 Model B/B+                         | Broadcom BCM2837/BCM2837B0 | SD-card            | ``rpi3-sd.yml``                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-swi:

Sierra Wireless LTE Modules (`meta-tanowrt-hsl-swi <../meta-tanowrt-hsl-swi/README.md>`__)
------------------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| mangOH Green with WP7607                          | Sierra Wireless            | NAND flash         | ``mangoh-green-wp7607.yml``      |
|                                                   | WP7607/WP7607-1            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-ti:

Texas Instruments SoC's (`meta-tanowrt-hsl-ti <../meta-tanowrt-hsl-ti/README.md>`__)
------------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| BeagleBone Black                                  | Texas Instruments AM3358   | eMMC flash         | ``am335x-bbb-emmc.yml``          |
|                                                   |                            +--------------------+----------------------------------+
|                                                   |                            | SD-card            | ``am335x-bbb-sd.yml``            |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Texas Instruments AM3359 ICEv2 EVM (TMDSICE3359)  | Texas Instruments AM3359   | SD-card            | ``am335x-icev2-sd.yml``          |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Texas Instruments AM574x IDK EVM (TMDSIDK574)     | Texas Instruments AM5748   | eMMC flash         | ``am574x-idk-emmc.yml``          |
|                                                   |                            +--------------------+----------------------------------+
|                                                   |                            | SD-card            | ``am574x-idk-sd.yml``            |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

Targets with Extra (Commercial) Features
----------------------------------------

TanoWrt has target YML files that are only available to commercial users. These files
are located in the ``kas/targets/extras`` subfolder relative to the root of the ``meta-tanowrt`` layer. 
Available commercial-only target files listed in the table below.

.. _meta-tanowrt-hsl-swi-extras:

Sierra Wireless LTE Modules (`meta-tanowrt-hsl-swi-extras <../meta-tanowrt-hsl-swi-extras/README.md>`__)
++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

+--------------------------+------------------------+------------+-----------------------------------------+--------------------------------------------------+
|      Board (Device)      |         SoC(s)         | Storage(s) |   Target file (at kas/targets/extras)   |                Extra Features                    |
+==========================+========================+============+=========================================+==================================================+
| mangOH Green with WP7607 | Sierra Wireless        | NAND flash | ``mangoh-green-wp7607.yml``             | Additional features: Legato Framework,           |
|                          | WP7607/WP7607-1        |            |                                         | Full modem functionality, SMS, GNSS, FUOTA, etc. |
+--------------------------+------------------------+------------+-----------------------------------------+--------------------------------------------------+

Examples
========

QEMU (x86_64)
-------------

Clone TanoWrt core layer repository with submodules to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: shell

   # cd ~
   # git clone --recursive https://github.com/tano-systems/meta-tanowrt tanowrt
   # cd ~/tanowrt

Build TanoWrt image by running the ``kas build`` command with the path to the YML-target file as an argument:

.. code-block:: shell

   # kas build kas/targets/qemux86-64-screen.yml

Run the built image in QEMU:

.. code-block:: shell

   # kas shell -c "DISPLAY=:0 runqemu qemux86-64-screen" kas/targets/qemux86-64-screen.yml

or without graphics:

.. code-block:: shell

   # kas shell -c "runqemu qemux86-64-screen nographics" kas/targets/qemux86-64-screen.yml

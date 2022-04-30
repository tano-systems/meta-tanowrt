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
          xz-utils debianutils iputils-ping file

.. note:: If you are using Ubuntu, you will need to configure the default system shell
          command interpreter for shell scripts to bash. You can do it with the command:

          .. code-block:: shell

             # sudo dpkg-reconfigure dash

          Select `No` when it asks you to install dash as `/bin/sh`.

.. note:: Some targets may require additional dependencies for
          a successful build. See the ``README.md`` of the related
          hardware support layer (HSL) for this information.

Install kas
===========

To install kas into your python site-package repository, run:

.. code-block:: shell

   # pip3 install kas

.. note:: Required kas version 2.6 or higher.

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
path to the target YML-file as an argument.

.. code-block:: shell

   # cd ~/tanowrt
   # kas build <target-yml-file>

For example, to build TanoWrt images for internal eMMC flash of the NXP LS1028A RDB board
you need to run following commands from ``meta-tanowrt`` root directory:

.. code-block:: shell

   # cd ~/tanowrt
   # kas build kas/targets/ls1028ardb-emmc.yml

See `Available Targets <targets_>`__ to select the proper target file.

.. tip::

   If you see ``Command 'kas' not found`` just add ``~/.local/bin`` to your ``$PATH``,
   for example by adding the following line to your ``.bashrc`` file:

   .. code-block:: shell

      export PATH="$HOME/.local/bin:$PATH"

   After that, restart your shell and things should work as expected.

.. _targets:

Available Targets
=================

Target YML-files are located in ``kas/targets`` subfolder of the ``meta-tanowrt`` repository.
Available target files listed in the sections below:

- `Virtual Machines <meta-tanowrt_>`__
- `Intel CPU based machines <meta-tanowrt-hsl-intel_>`__
- `Boards based on Microchip/Atmel SoC's <meta-tanowrt-hsl-atmel_>`__
- `Boards based on NXP/Freescale SoC's <meta-tanowrt-hsl-nxp_>`__
- `Boards based on Baikal Electronics SoC's <meta-tanowrt-hsl-baikal_>`__
- `Boards based on Rockchip SoC's <meta-tanowrt-hsl-rockchip_>`__
- `Raspberry Pi Boards <meta-tanowrt-hsl-rpi_>`__
- `Sierra Wireless LTE Modules <meta-tanowrt-hsl-swi_>`__
- `Boards based on Texas Instruments SoC's <meta-tanowrt-hsl-ti_>`__

Targets with extra (commercial) features:

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
| QEMU/Common x86 (32-bit) machine                  | Generic x86                | Virtual HDD        | ``qemux86.yml``                  |
+---------------------------------------------------+                            |                    +----------------------------------+
| QEMU/Common x86 (32-bit) machine                  |                            |                    | ``qemux86-screen.yml``           |
| with screen support                               |                            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| QEMU/Common x86 (64-bit) machine                  | Generic x86-64             | Virtual HDD        | ``qemux86-64.yml``               |
+---------------------------------------------------+                            |                    +----------------------------------+
| QEMU/Common x86 (64-bit) machine                  |                            |                    | ``qemux86-64-screen.yml``        |
| with screen support                               |                            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-intel:

Intel CPU Based Machines (`meta-tanowrt-hsl-intel <../meta-tanowrt-hsl-intel/README.md>`__)
---------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| Intel Core i7 CPU (and later)                     | x86_64 (Intel Core i7)     | USB/SATA/NVMe      | ``intel-x86-64-corei7.yml``      |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Intel Skylake CPU (and later)                     | x86_64 (Intel Skylake)     | USB/SATA/NVMe      | ``intel-x86-64-skylake.yml``     |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-atmel:

Boards based on Microchip/Atmel SoC's (`meta-tanowrt-hsl-atmel <../meta-tanowrt-hsl-atmel/README.md>`__)
--------------------------------------------------------------------------------------------------------

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

.. _meta-tanowrt-hsl-baikal:

Boards based on Baikal Electronics SoC's (`meta-tanowrt-hsl-baikal <../meta-tanowrt-hsl-baikal/README.md>`__)
-------------------------------------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| Baikal Electronics MBM 1.0 Board                  | Baikal-M BE-M1000          | USB/SATA/NVMe      | ``mbm10.yml``                    |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Baikal Electronics MBM 2.0 Board                  | Baikal-M BE-M1000          | USB/SATA/NVMe      | ``mbm20.yml``                    |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-nxp:

Boards based on NXP/Freescale SoC's (`meta-tanowrt-hsl-nxp <../meta-tanowrt-hsl-nxp/README.md>`__)
--------------------------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| NXP LS1028A RDB                                   | NXP LS1028A                | eMMC flash         | ``ls1028ardb-emmc.yml``          |
| (Reference Design Board)                          |                            +--------------------+----------------------------------+
|                                                   |                            | SD-card            | ``ls1028ardb-sd.yml``            |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+

.. _meta-tanowrt-hsl-rockchip:

Boards based on Rockchip SoC's (`meta-tanowrt-hsl-rockchip <../meta-tanowrt-hsl-rockchip/README.md>`__)
-------------------------------------------------------------------------------------------------------

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| Boardcon EM3568 SBC (Single-Board Computer)       | Rockchip RK3568            | SD-card            | ``boardcon-em3568-sd.yml``       |
| (Embedded Development Board)                      |                            +--------------------+----------------------------------+
|                                                   |                            | eMMC flash         | ``boardcon-em3568-emmc.yml``     |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Boardcon EM3566 SBC (Single-Board Computer)       | Rockchip RK3566            | SD-card            | ``boardcon-em3566-sd.yml``       |
| (Embedded Development Board)                      |                            +--------------------+----------------------------------+
|                                                   |                            | eMMC flash         | ``boardcon-em3566-emmc.yml``     |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Radxa ROCK Pi S                                   | Rockchip RK3308            | SD-card            | ``rock-pi-s-sd.yml``             |
|                                                   |                            +--------------------+----------------------------------+
|                                                   |                            | SD NAND flash      | ``rock-pi-s-sdnand.yml``         |
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

Boards based on Texas Instruments SoC's (`meta-tanowrt-hsl-ti <../meta-tanowrt-hsl-ti/README.md>`__)
----------------------------------------------------------------------------------------------------

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

TanoWrt has target YML-files that are only available to commercial users. These files
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

Build TanoWrt image by running the ``kas build`` command with the path to the target YML-file as an argument:

.. code-block:: shell

   # kas build kas/targets/qemux86-64-screen.yml

Run the built image in QEMU:

.. code-block:: shell

   # kas shell -c "DISPLAY=:0 runqemu qemux86-64-screen" kas/targets/qemux86-64-screen.yml

or without graphics:

.. code-block:: shell

   # kas shell -c "runqemu qemux86-64-screen nographics" kas/targets/qemux86-64-screen.yml

Intel Core i7 (x86_64)
----------------------

Clone TanoWrt core layer repository with submodules to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: shell

   # cd ~
   # git clone --recursive https://github.com/tano-systems/meta-tanowrt tanowrt
   # cd ~/tanowrt

Build TanoWrt image by running the ``kas build`` command with the path to the target YML-file as an argument:

.. code-block:: shell

   # kas build kas/targets/intel-x86_64-corei7.yml

Run the built image in QEMU:

.. code-block:: shell

   # kas shell -c "DISPLAY=:0 runqemu intel-x86_64-corei7" kas/targets/intel-x86_64-corei7.yml

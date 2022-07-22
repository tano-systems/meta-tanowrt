.. SPDX-License-Identifier: MIT

Building TanoWrt with kas
=========================

.. note:: `kas <http://github.com/siemens/kas>`_ is a setup tool for
          BitBake based projects.

.. include:: include/kas-common-part.rst.inc

For example, to build TanoWrt images for internal eMMC flash of the NXP LS1028A RDB board
you need to run following commands from ``meta-tanowrt`` root directory:

.. code-block:: console

   # cd ~/tanowrt
   # kas build kas/targets/ls1028ardb-emmc.yml

See `Available Targets <targets_>`__ to select the proper target file.


.. _targets:

Available Targets
-----------------

Target YML-files are located in ``kas/targets`` subfolder of the ``meta-tanowrt`` repository.
Available target files listed in the sections below:

- :ref:`sec-meta-tanowrt`
- :ref:`sec-meta-tanowrt-hsl-intel`
- :ref:`sec-meta-tanowrt-hsl-atmel`
- :ref:`sec-meta-tanowrt-hsl-nxp`
- :ref:`sec-meta-tanowrt-hsl-baikal`
- :ref:`sec-meta-tanowrt-hsl-rockchip`
- :ref:`sec-meta-tanowrt-hsl-rpi`
- :ref:`sec-meta-tanowrt-hsl-swi`
- :ref:`sec-meta-tanowrt-hsl-ti`

Targets with extra (commercial) features:

- :ref:`sec-meta-tanowrt-hsl-swi-extras`


.. _sec-meta-tanowrt:

Virtual Machines
^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt`

The TanoWrt core layer provides support for a target devices
intended to run in a virtual environment (QEMU, VirtualBox, etc.).

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


.. _sec-meta-tanowrt-hsl-intel:

Intel CPU Based Machines
^^^^^^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-intel`

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| Intel Core i7 CPU (and later)                     | x86_64 (Intel Core i7)     | USB/SATA/NVMe      | ``intel-x86-64-corei7.yml``      |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Intel Skylake CPU (and later)                     | x86_64 (Intel Skylake)     | USB/SATA/NVMe      | ``intel-x86-64-skylake.yml``     |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+


.. _sec-meta-tanowrt-hsl-atmel:

Microchip/Atmel SoC's
^^^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-atmel`

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


.. _sec-meta-tanowrt-hsl-baikal:

Baikal Electronics SoC's
^^^^^^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-baikal`

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| Baikal Electronics MBM 1.0 (TF307) Board          | Baikal-M BE-M1000          | USB/SATA/NVMe      | ``mbm10.yml``                    |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
| Baikal Electronics MBM 2.0 (TF307) Board          | Baikal-M BE-M1000          | USB/SATA/NVMe      | ``mbm20.yml``                    |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+


.. _sec-meta-tanowrt-hsl-nxp:

NXP/Freescale SoC's
^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-nxp`

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| NXP LS1028A RDB                                   | NXP LS1028A                | eMMC flash         | ``ls1028ardb-emmc.yml``          |
| (Reference Design Board)                          |                            +--------------------+----------------------------------+
|                                                   |                            | SD-card            | ``ls1028ardb-sd.yml``            |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+


.. _sec-meta-tanowrt-hsl-rockchip:

Fuzhou Rockchip Electronics.SoC's
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-rockchip`

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


.. _sec-meta-tanowrt-hsl-rpi:

Raspberry Pi Boards
^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-rpi`

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| Raspberry Pi 3 Model B/B+                         | Broadcom BCM2837/BCM2837B0 | SD-card            | ``rpi3-sd.yml``                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+


.. _sec-meta-tanowrt-hsl-swi:

Sierra Wireless LTE Modules
^^^^^^^^^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-swi`

+---------------------------------------------------+----------------------------+--------------------+----------------------------------+
|                   Board (Device)                  |           SoC(s)           |     Storage(s)     |   Target file (at kas/targets)   |
+===================================================+============================+====================+==================================+
| mangOH Green with WP7607                          | Sierra Wireless            | NAND flash         | ``mangoh-green-wp7607.yml``      |
|                                                   | WP7607/WP7607-1            |                    |                                  |
+---------------------------------------------------+----------------------------+--------------------+----------------------------------+


.. _sec-meta-tanowrt-hsl-ti:

Texas Instruments SoC's
^^^^^^^^^^^^^^^^^^^^^^^

Support layer: :ref:`meta-tanowrt-hsl-ti`

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
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

TanoWrt has target YML-files that are only available to commercial users. These files
are located in the ``kas/targets/extras`` subfolder relative to the root of the ``meta-tanowrt`` layer. 
Available commercial-only target files listed in the table below.

.. _sec-meta-tanowrt-hsl-swi-extras:

Sierra Wireless LTE Modules (Extra)
+++++++++++++++++++++++++++++++++++

Support layer: :ref:`meta-tanowrt-hsl-swi-extras`

+--------------------------+------------------------+------------+-----------------------------------------+--------------------------------------------------+
|      Board (Device)      |         SoC(s)         | Storage(s) |   Target file (at kas/targets/extras)   |                Extra Features                    |
+==========================+========================+============+=========================================+==================================================+
| mangOH Green with WP7607 | Sierra Wireless        | NAND flash | ``mangoh-green-wp7607.yml``             | Additional features: Legato Framework,           |
|                          | WP7607/WP7607-1        |            |                                         | Full modem functionality, SMS, GNSS, FUOTA, etc. |
+--------------------------+------------------------+------------+-----------------------------------------+--------------------------------------------------+

Examples
--------

QEMU (x86_64)
^^^^^^^^^^^^^

Clone TanoWrt core layer repository with submodules to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: console

   $ cd ~
   $ git clone --recursive https://github.com/tano-systems/meta-tanowrt tanowrt
   $ cd ~/tanowrt

Build TanoWrt image by running the ``kas build`` command with the path to the target YML-file as an argument:

.. code-block:: console

   $ kas build kas/targets/qemux86-64-screen.yml

Run the built image in QEMU:

.. code-block:: console

   $ kas shell -c "DISPLAY=:0 runqemu qemux86-64-screen" kas/targets/qemux86-64-screen.yml

or without graphics:

.. code-block:: console

   $ kas shell -c "runqemu qemux86-64-screen nographics" kas/targets/qemux86-64-screen.yml

Intel Core i7 (x86_64)
^^^^^^^^^^^^^^^^^^^^^^

Clone TanoWrt core layer repository with submodules to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: console

   $ cd ~
   $ git clone --recursive https://github.com/tano-systems/meta-tanowrt tanowrt
   $ cd ~/tanowrt

Build TanoWrt image by running the ``kas build`` command with the path to the target YML-file as an argument:

.. code-block:: console

   $ kas build kas/targets/intel-x86_64-corei7.yml

Run the built image in QEMU:

.. code-block:: console

   $ kas shell -c "DISPLAY=:0 runqemu intel-x86_64-corei7" kas/targets/intel-x86_64-corei7.yml

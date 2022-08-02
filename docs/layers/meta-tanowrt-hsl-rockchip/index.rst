.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-rockchip:

=================================================================
Rockchip SoC's Hardware Support Layer (meta-tanowrt-hsl-rockchip)
=================================================================

meta-tanowrt-hsl-rockchip |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Fuzhou Rockchip Electronics SoC based boards.

.. figure:: /common/images/logos/rockchip.png
   :height: 100

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-rockchip <meta-tanowrt-hsl-rockchip>` layer are described in the table below.

.. list-table::
   :header-rows: 1
   :width: 100%
   :widths: 15, 40, 15, 15, 15

   * - Layer
     - URI
     - Subfolder
     - Branch
     - Configuration YML-file
   * - :ref:`meta-tanowrt <meta-tanowrt>`
     - https://github.com/tano-systems/meta-tanowrt.git
     - meta-tanowrt
     - hardknott
     - :download:`kas/layers/meta-tanowrt.yml <../../../kas/layers/meta-tanowrt.yml>`
   * - meta-rockchip
     - https://github.com/JeffyCN/meta-rockchip.git
     - --
     - hardknott
     - :download:`kas/layers/meta-rockchip.yml <../../../kas/layers/meta-rockchip.yml>`


Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on Rockchip SoC's.
All supported devices are listed in the table below.

.. include:: .layer-boards.rst.inc


.. _sec-hsl-rockchip-partitioning:

Partitioning Layouts
====================

The Rockchip hardware support layer provides default disk partition layout
for A/B systems and for the factory installation image. These layouts are
defined in WKS files
:tanowrt_github_blob:`sdimage-rockchip-swu-a-b.wks.in </meta-tanowrt-hsl-rockchip/wic/sdimage-rockchip-swu-a-b.wks.in>`
and
:tanowrt_github_blob:`sdimage-rockchip-swu-factory.wks.in </meta-tanowrt-hsl-rockchip/wic/sdimage-rockchip-swu-factory.wks.in>`
respectively.

Data layout at the beginning of the disk (from 0 to 8 MiB) is the same
for both layouts and is described in :numref:`table-hsl-rockchip-partitions-beginning`.

.. _table-hsl-rockchip-partitions-beginning:

.. table:: Default Partitions Layout (beginning)
   :widths: 10, 8, 7, 7, 8, 8, 7, 15, 30

   +--------------------+-----------------+------------------------+----------------------------+--------+------------------------------------------+---------------------------+
   | Partition          | Name            | Offset                 | Size                       | GPT    | UUID                                     | Description               |
   |                    |                 +-------------+----------+--------------+-------------+ Number |                                          |                           |
   |                    |                 | Bytes       | Sectors  | Bytes        | Sectors     |        |                                          |                           |
   +====================+=================+=============+==========+==============+=============+========+==========================================+===========================+
   | MBR                | --              |           0 |        0 |          512 |           1 | --     | --                                       | Master Boot Record (MBR)  |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Primary GPT        | --              |         512 |        1 |     31.5 KiB |          63 | --     | --                                       | Primary GPT record        |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | IDBlock            | --              |       32768 |       64 |      992 KiB |        1984 | --     | --                                       | Rockchip IDBlock binary   |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | **Free space**     | --              | **1048576** | **2048** | **2944 KiB** |    **5888** | --     | --                                       | **Free space**            |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Startup            | --              |     4063232 |     7936 |       64 KiB |         128 | --     | --                                       | U-boot startup script     |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | U-boot             | --              |     4128768 |     8064 |       32 KiB |          64 | --     | --                                       | U-boot environment        |
   | Environment 1      |                 |             |          |              |             |        |                                          |                           |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | U-boot             | --              |     4161536 |     8128 |       32 KiB |          64 | --     | --                                       | U-boot environment        |
   | Environment 2      |                 |             |          |              |             |        |                                          | (redundand)               |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | **Free space**     | --              | **4194304** | **8192** |    **4 MiB** |    **8192** | --     | --                                       | **Free space**            |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Continuation of the disk (see :numref:`table-hsl-rockchip-partitions-ab` and :numref:`table-hsl-rockchip-partitions-factory`)                                              |
   +----------------------------------------------------------------------------------------------------------------------------------------------------------------------------+

The continuation of the disk with partitioning for A/B systems is summarized in :numref:`table-hsl-rockchip-partitions-ab`.

.. _table-hsl-rockchip-partitions-ab:
.. table:: Continuation of the Partitions Layout for A/B Systems
   :widths: 10, 8, 7, 7, 8, 8, 7, 15, 30

   +--------------------+-----------------+------------------------+----------------------------+--------+------------------------------------------+---------------------------+
   | Partition          | Name            | Offset                 | Size                       | GPT    | UUID                                     | Description               |
   |                    |                 +-------------+----------+--------------+-------------+ Number |                                          |                           |
   |                    |                 | Bytes       | Sectors  | Bytes        | Sectors     |        |                                          |                           |
   +====================+=================+=============+==========+==============+=============+========+==========================================+===========================+
   | Beginning of the disk (see :numref:`table-hsl-rockchip-partitions-beginning`)                                                                                              |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | U-boot             | ``boot``        |     8388608 |    16384 |        8 MiB |       16384 |      1 | :term:`TANOWRT_PARTUUID_BOOT`            | A-TF, U-boot bootloader,  |
   |                    |                 |             |          |              |             |        |                                          | Device Tree Blob          |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Kernel A           | ``kernel_a``    |    16777216 |    32768 |       64 MiB |      131072 |      2 | :term:`TANOWRT_PARTUUID_KERNELDEV_A`     | Linux kernel with DTB     |
   |                    |                 |             |          |              |             |        |                                          | for system A.             |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Root filesystem A  | ``rootfs_a``    |    83886080 |   163840 |      384 MiB |      786432 |      3 | :term:`TANOWRT_PARTUUID_ROOTDEV_A`       | Read-only root filesystem |
   |                    |                 |             |          |              |             |        |                                          | for system A.             |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Kernel B           | ``kernel_b``    |   486539264 |   950272 |       64 MiB |      131072 |      4 | :term:`TANOWRT_PARTUUID_KERNELDEV_B`     | Linux kernel with DTB     |
   |                    |                 |             |          |              |             |        |                                          | for system B.             |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Root filesystem B  | ``rootfs_b``    |   553648128 |  1081344 |      384 MiB |      786432 |      5 | :term:`TANOWRT_PARTUUID_ROOTDEV_B`       | Read-only root filesystem |
   |                    |                 |             |          |              |             |        |                                          | for system B.             |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Overlay filesystem | ``rootfs_data`` |   956301312 |  1867776 | all          | all         |      6 | :term:`TANOWRT_PARTUUID_OVERLAY`         | Overlay filesystem (user  |
   |                    |                 |             |          | available    | available   |        |                                          | data)                     |
   |                    |                 |             |          | space\ [#]_  | space       |        |                                          |                           |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Secondary GPT      | --              |      at end |   at end |         512  |           1 | --     | --                                       | Secondary GPT record      |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+

.. [#] Partition is automatically resized to fit all available disk space at first boot.

:numref:`table-hsl-rockchip-partitions-factory` shows the continuation
of the disk with partitioning for the factory installation image.

.. _table-hsl-rockchip-partitions-factory:
.. table:: Continuation of the Partitions Layout for Factory Installation Image
   :widths: 10, 8, 7, 7, 8, 8, 7, 15, 30

   +--------------------+-----------------+------------------------+----------------------------+--------+------------------------------------------+---------------------------+
   | Partition          | Name            | Offset                 | Size                       | GPT    | UUID                                     | Description               |
   |                    |                 +-------------+----------+--------------+-------------+ Number |                                          |                           |
   |                    |                 | Bytes       | Sectors  | Bytes        | Sectors     |        |                                          |                           |
   +====================+=================+=============+==========+==============+=============+========+==========================================+===========================+
   | Beginning of the disk (see :numref:`table-hsl-rockchip-partitions-beginning`)                                                                                              |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | U-boot             | ``boot``        |     8388608 |    16384 |        8 MiB |       16384 |      1 | :term:`TANOWRT_PARTUUID_BOOT`            | A-TF, U-boot bootloader,  |
   |                    |                 |             |          |              |             |        |                                          | Device Tree Blob          |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Linux Kernel       | ``kernel``      |    16777216 |    32768 |      dynamic |     dynamic |      2 | --                                       | Linux kernel with DTB     |
   |                    |                 |             |          |              |             |        |                                          | and factory installation  |
   |                    |                 |             |          |              |             |        |                                          | initramfs.                |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Installation       | ``install``     |     dynamic |  dynamic |      dynamic |     dynamic |      3 | --                                       | Partition with            |
   | Partition          |                 |             |          |              |             |        |                                          | installation images.      |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+
   | Secondary GPT      | --              |      at end |   at end |         512  |           1 | --     | --                                       | Secondary GPT record      |
   +--------------------+-----------------+-------------+----------+--------------+-------------+--------+------------------------------------------+---------------------------+

Configuration
=============

Optionally you can customize some build options in :file:`local.conf`
configuration file (see :ref:`sec-build-local-conf` section).

Kernel Version
--------------

The hardware support layer for Rockchip SoC's contains recipes for several
versions of the Linux kernel. Available versions of the Linux kernel are
listed in the table below.

.. _table-hsl-rockchip-linux-kernels:
.. table:: Available Linux Kernel Versions

   +----------------+------------------+-------------------------------+----------------------------+-----------------------+-----------------+
   | Kernel Version | Kernel Type      | Recipe                        | Preferred Provider Package | Preferred Version     | Notes           |
   |                |                  |                               | Value\ [#]_                | Value\ [#]_           |                 |
   +================+==================+===============================+============================+=======================+=================+
   | 4.19.219       | Standard         | |linux-tano-rockchip_4.19|    | ``linux-tano-rockchip``    | ``4.19%``             | Used by default |
   +----------------+------------------+-------------------------------+----------------------------+-----------------------+-----------------+
   | 4.19.209-rt87  | PREEMPT_RT patch | |linux-tano-rockchip-rt_4.19| | ``linux-tano-rockchip-rt`` | ``4.19%``             |                 |
   +----------------+------------------+-------------------------------+----------------------------+-----------------------+-----------------+

.. [#] This value should be used for variable ``PREFERRED_PROVIDER_virtual/kernel``.
.. [#] This value should be used for variable ``PREFERRED_VERSION_<kernel-package>``, where
       ``<kernel-package>`` must be replaced with the value of the ``PREFERRED_PROVIDER_virtual/kernel`` variable.

.. |linux-tano-rockchip_4.19| replace:: :tanowrt_github_blob:`linux-tano-rockchip_4.19.bb </meta-tanowrt-hsl-rockchip/recipes-kernel/linux/linux-tano-rockchip_4.19.bb>`
.. |linux-tano-rockchip-rt_4.19| replace:: :tanowrt_github_blob:`linux-tano-rockchip-rt_4.19.bb </meta-tanowrt-hsl-rockchip/recipes-kernel/linux/linux-tano-rockchip-rt_4.19.bb>`

To choose the kernel version, you need to set variable ``PREFERRED_PROVIDER_virtual/kernel``
in the :ref:`local configuration <sec-build-local-conf>` file :file:`local.conf` to required
Linux kernel package recipe and set variable ``PREFERRED_VERSION_<kernel-package>`` to required
kernel version. The values of these variables for the available kernel versions are shown
in :numref:`table-hsl-rockchip-linux-kernels`.

For example, to choose the Linux kernel version 4.19.209-rt87, the variables
in the :file:`local.conf` must be set as follows:

.. code-block:: bash

   PREFERRED_PROVIDER_virtual/kernel = "linux-tano-rockchip-rt"
   PREFERRED_VERSION_linux-tano-rockchip-rt = "4.19%"


License
============

All metadata is MIT licensed unless otherwise stated.
Source code included in tree for individual recipes is under the
LICENSE stated in each recipe (:file:`.bb` file) unless otherwise stated.

Maintainers
============

Anton Kikin <a.kikin@tano-systems.com>

References
==========

1. `Fuzhou Rockchip Electronics Official Site <http://www.rock-chips.com/a/en/index.html>`__

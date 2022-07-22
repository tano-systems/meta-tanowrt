.. SPDX-License-Identifier: MIT

.. include:: <xhtml1-lat1.txt>
.. include:: <xhtml1-special.txt>

.. _meta-tanowrt-hsl-rockchip:

=================================================================
Rockchip SoC's Hardware Support Layer (meta-tanowrt-hsl-rockchip)
=================================================================

meta-tanowrt-hsl-rockchip |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Fuzhou Rockchip Electronics SoC based boards.

.. image:: images/rockchip-logo.png
   :height: 120

Requirements and Dependencies
=============================

Dependencies
------------

Dependencies of the :ref:`meta-tanowrt-hsl-rockchip <meta-tanowrt-hsl-rockchip>` layer are described in the table below.

.. list-table::
   :header-rows: 1

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

.. _table-hsl-rockchip-supported-boards:
.. table:: Supported Boards

   +---------------------------------------+-----------------------------------------+--------------------+--------------------------+
   | Board                                 | SoC                                     | RAM                | Supported Storage(s)     |
   +=======================================+=========================================+====================+==========================+
   | :ref:`device-boardcon-em3566`         | Rockchip RK3566                         | 2 GiB LPDDR4       || microSD                 |
   |                                       | Quad Cortex-A55 ARM 64 bits processor,  |                    || 8 GB eMMC [#emmc-size]_ |
   |                                       | up to 1.8 GHz                           |                    |                          |
   +---------------------------------------+-----------------------------------------+--------------------+--------------------------+
   | :ref:`device-boardcon-em3568`         | Rockchip RK3568                         | 2 GiB LPDDR4       || microSD                 |
   |                                       | Quad Cortex-A55 ARM 64 bits processor,  |                    || 8 GB eMMC [#emmc-size]_ |
   |                                       | up to 2.0 GHz                           |                    |                          |
   +---------------------------------------+-----------------------------------------+--------------------+--------------------------+
   | :ref:`device-rock-pi-s`               | Rockchip RK3308                         | up to 512 MiB DDR3 || microSD                 |
   |                                       | Quad Cortex-A35 ARM 64 bits processor,  |                    || SD NAND up to 8 GB      |
   |                                       | up to 1.3 GHz                           |                    |                          |
   +---------------------------------------+-----------------------------------------+--------------------+--------------------------+

.. [#emmc-size] Actual eMMC flash size is approximately 7.28 GiB

.. toctree::
   :hidden:
   :numbered:

   boards/boardcon-em3566/index.rst
   boards/boardcon-em3568/index.rst
   boards/rock-pi-s/index.rst

License
============

All metadata is MIT licensed unless otherwise stated.
Source code included in tree for individual recipes is under the
LICENSE stated in each recipe (``.bb`` file) unless otherwise stated.

Maintainers
============

Anton Kikin <a.kikin@tano-systems.com>

References
==========

1. `Fuzhou Rockchip Electronics official site <http://www.rock-chips.com/a/en/index.html>`__

.. SPDX-License-Identifier: MIT

.. include:: <xhtml1-lat1.txt>
.. include:: <xhtml1-special.txt>

.. _meta-tanowrt-hsl-baikal:

=========================================================================
Baikal Electronics SoC's Hardware Support Layer (meta-tanowrt-hsl-baikal)
=========================================================================

meta-tanowrt-hsl-baikal |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Baikal Electronics SoC based boards.

.. image:: images/baikalelectronics-logo.png
   :height: 120

Requirements and Dependencies
=============================

Dependencies
------------

Dependencies of the :ref:`meta-tanowrt-hsl-baikal <meta-tanowrt-hsl-baikal>` layer are described in the table below.

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


Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on Baikal Electronics SoC's.
All supported devices are listed in the table below.

.. _table-hsl-baikal-supported-boards:
.. table:: Supported Boards

   +------------------------+------------------------------------------+---------------------+--------------------------+
   | Board                  | SoC                                      | RAM                 | Supported Storage(s)     |
   +========================+==========================================+=====================+==========================+
   | |mbm10|                | Baikal Electronics BE-M1000              | up to 64 GiB DDR4   || USB Flash               |
   |                        | 8-core ARM Cortex-A57 64 bits processor, |                     || SATA [#not-supported]_  |
   |                        | up to 1.5 GHz                            |                     || NVMe [#not-supported]_  |
   +------------------------+                                          |                     || microSD                 |
   | |mbm20|                |                                          |                     |                          |
   |                        |                                          |                     |                          |
   |                        |                                          |                     |                          |
   +------------------------+------------------------------------------+---------------------+--------------------------+

.. |mbm10| replace:: :ref:`Baikal Electronics MBM 1.0 (TF307) <device-mbm>`
.. |mbm20| replace:: :ref:`Baikal Electronics MBM 2.0 (TF307) <device-mbm>`

.. [#not-supported] Support for HDD and NVMe storages is under development. Currently supported running TanoWrt
                    only from USB flash drive.

.. toctree::
   :hidden:
   :numbered:

   boards/mbm/index.rst

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

1. `Baikal Electronics official site <https://www.baikalelectronics.ru/>`__

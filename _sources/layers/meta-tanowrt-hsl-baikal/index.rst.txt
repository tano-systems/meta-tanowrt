.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-baikal:

=========================================================================
Baikal Electronics SoC's Hardware Support Layer (meta-tanowrt-hsl-baikal)
=========================================================================

meta-tanowrt-hsl-baikal |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Baikal Electronics SoC based boards.

.. figure:: /common/images/logos/baikal-electronics.png
   :height: 120

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-baikal <meta-tanowrt-hsl-baikal>` layer are described in the table below.

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
     - :download:`kas/layers/meta-tanowrt.yml </../kas/layers/meta-tanowrt.yml>`


Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on Baikal Electronics SoC's.
All supported devices are listed in the table below.

.. include:: .layer-boards.rst.inc

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

1. `Baikal Electronics Official Site <https://www.baikalelectronics.ru/>`__

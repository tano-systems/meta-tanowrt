.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-intel:

=====================================================
Intel Hardware Support Layer (meta-tanowrt-hsl-intel)
=====================================================

meta-tanowrt-hsl-intel |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_
hardware support layer for the Intel CPU based devices.

.. figure:: /common/images/logos/intel.svg
   :width: 200

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-intel <meta-tanowrt-hsl-intel>` layer are described in the table below.

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
   * - meta-intel
     - https://git.yoctoproject.org/meta-intel
     - --
     - hardknott
     - :download:`kas/layers/meta-intel.yml <../../../kas/layers/meta-intel.yml>`


Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on intel (Freescale) SoC's.
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

1. `Intel Official Site <https://www.intel.com/>`__

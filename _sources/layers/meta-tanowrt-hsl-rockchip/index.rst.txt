.. SPDX-License-Identifier: MIT

.. include:: <xhtml1-lat1.txt>
.. include:: <xhtml1-special.txt>

.. _meta-tanowrt-hsl-rockchip:

=================================================================
Rockchip SoC's Hardware Support Layer (meta-tanowrt-hsl-rockchip)
=================================================================

meta-tanowrt-hsl-rockchip |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Fuzhou Rockchip Electronics SoC based boards.

.. image:: /common/images/logos/rockchip.png
   :height: 120

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Requirements and Dependencies
=============================

Dependencies
------------

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

1. `Fuzhou Rockchip Electronics official site <http://www.rock-chips.com/a/en/index.html>`__

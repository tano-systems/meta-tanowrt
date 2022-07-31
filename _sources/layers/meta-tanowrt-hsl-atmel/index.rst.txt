.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-atmel:

=================================================================
Microchip (Atmel) Hardware Support Layer (meta-tanowrt-hsl-atmel)
=================================================================

meta-tanowrt-hsl-atmel |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Microchip (Atmel) SoC based boards.

.. container:: flex

   .. figure:: /common/images/logos/microchip.svg
      :width: 350

   .. figure:: /common/images/logos/atmel.svg
      :width: 150

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-atmel <meta-tanowrt-hsl-atmel>` layer are described in the table below.

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
   * - meta-atmel
     - https://github.com/linux4sam/meta-atmel.git
     - --
     - hardknott
     - :download:`kas/layers/meta-atmel.yml <../../../kas/layers/meta-atmel.yml>`

.. _sec-hsl-atmel-supported-boards:

Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on Microchip (Atmel) SoC's.
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

1. `Microchip Technology <https://www.microchip.com/>`__

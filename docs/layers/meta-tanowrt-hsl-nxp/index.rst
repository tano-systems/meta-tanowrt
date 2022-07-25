.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-nxp:

=============================================================
NXP (Freescale) Hardware Support Layer (meta-tanowrt-hsl-nxp)
=============================================================

meta-tanowrt-hsl-nxp |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the NXP (Freescale) SoC based boards.

.. container:: flex

   .. figure:: /common/images/logos/nxp.svg
      :width: 250

   .. figure:: /common/images/logos/freescale.png
      :width: 300


.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-nxp <meta-tanowrt-hsl-nxp>` layer are described in the table below.

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
   * - meta-freescale
     - https://git.yoctoproject.org/meta-freescale
     - --
     - hardknott
     - :download:`kas/layers/meta-freescale.yml <../../../kas/layers/meta-freescale.yml>`


Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on NXP (Freescale) SoC's.
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

1. `NXP Official Site <https://www.nxp.com/>`__

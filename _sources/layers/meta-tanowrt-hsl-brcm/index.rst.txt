.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-brcm:

=======================================================
Broadcom Hardware Support Layer (meta-tanowrt-hsl-brcm)
=======================================================

meta-tanowrt-hsl-brcm |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Broadcom SoC based boards.

.. container:: flex

   .. figure:: /common/images/logos/broadcom.svg
      :width: 250

   .. figure:: /common/images/logos/raspberry-pi.png
      :height: 100

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-brcm <meta-tanowrt-hsl-brcm>` layer are described in the table below.

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
   * - meta-raspberrypi
     - https://git.yoctoproject.org/meta-raspberrypi
     - --
     - hardknott
     - :download:`kas/layers/meta-raspberrypi.yml <../../../kas/layers/meta-raspberrypi.yml>`


.. _sec-hsl-brcm-supported-boards:

Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on Broadcom SoC's.
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

1. `Broadcom Inc. Official Site <https://www.broadcom.com/>`__
2. `Raspberry Pi <https://www.raspberrypi.com>`__

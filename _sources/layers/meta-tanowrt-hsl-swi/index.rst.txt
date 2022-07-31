.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-swi:

=========================================================================
Sierra Wireless LTE Modules Hardware Support Layer (meta-tanowrt-hsl-swi)
=========================================================================

meta-tanowrt-hsl-swi |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Sierra Wireless LTE modules.

.. figure:: /common/images/logos/sierra-wireless.svg
   :width: 250

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-swi <meta-tanowrt-hsl-swi>` layer are described in the table below.

.. list-table::
   :header-rows: 1
   :width: 100%
   :widths: 20, 35, 15, 15, 15

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
   * - meta-swi/common
     - https://github.com/legatoproject/meta-swi.git
     - common
     - yocto-3.0-ref
     - :download:`kas/layers/meta-swi-common.yml <../../../kas/layers/meta-swi-common.yml>`
   * - meta-swi/meta-swi-mdm9x28
     - https://github.com/legatoproject/meta-swi.git
     - meta-swi-mdm9x28
     - yocto-3.0-ref
     - :download:`kas/layers/meta-swi-mdm9x28.yml <../../../kas/layers/meta-swi-mdm9x28.yml>`
   * - meta-swi/meta-swi-mdm9xxx
     - https://github.com/legatoproject/meta-swi.git
     - meta-swi-mdm9xxx
     - yocto-3.0-ref
     - :download:`kas/layers/meta-swi-mdm9xxx.yml <../../../kas/layers/meta-swi-mdm9xxx.yml>`


Requirements
============

.. _sec-hsl-swi-install-swiflash:

Install swiflash Command Line Tool
----------------------------------

This utility is required for writing (flashing) CWE (SPK) image
to the device. If you using Ubuntu 16.04 or higher, you can install the tool
using the following commands:

.. code-block:: console

   $ wget https://downloads.sierrawireless.com/tools/swiflash/swiflash_latest.deb -O /tmp/swiflash_latest.deb
   $ sudo apt-get install /tmp/swiflash_latest.deb

To install the tool on other systems, follow the instructions
outlined `here <https://source.sierrawireless.com/resources/airprime/software/swiflash/>`_.


.. _sec-hsl-swi-supported-boards:

Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on Texas Instruments SoC's.
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

1. `Sierra Wireless <https://www.sierrawireless.com/>`__

.. SPDX-License-Identifier: MIT

.. _meta-tanowrt-hsl-ti:

==============================================================
Texas Instruments Hardware Support Layer (meta-tanowrt-hsl-ti)
==============================================================

meta-tanowrt-hsl-ti |mdash| `TanoWrt <https://github.com/tano-systems/meta-tanowrt>`_ hardware support
layer (:term:`HSL`) for the Texas Instruments (TI) SoC based boards.

.. figure:: /common/images/logos/ti.svg
   :width: 300

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

This layer contains various parts (recipes, files, etc.) from
the `meta-arago <http://arago-project.org/git/meta-arago.git>`_
and `meta-processor-sdk <http://arago-project.org/git/projects/meta-processor-sdk.git>`_
OpenEmbedded layers.

Dependencies
============

Dependencies of the :ref:`meta-tanowrt-hsl-ti <meta-tanowrt-hsl-ti>` layer are described in the table below.

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
   * - meta-ti
     - https://git.yoctoproject.org/meta-ti
     - --
     - hardknott
     - :download:`kas/layers/meta-ti.yml <../../../kas/layers/meta-ti.yml>`
   * - meta-arm
     - https://git.yoctoproject.org/meta-arm
     - meta-arm
     - hardknott
     - :download:`kas/layers/meta-arm.yml <../../../kas/layers/meta-arm.yml>`
   * - meta-arm-toolchain
     - https://git.yoctoproject.org/meta-arm
     - meta-arm-toolchain
     - hardknott
     - :download:`kas/layers/meta-arm-toolchain.yml <../../../kas/layers/meta-arm-toolchain.yml>`

.. _sec-hsl-ti-supported-boards:

Supported Boards
================

TanoWrt has demonstration support for some devices and development boards based on Texas Instruments SoC's.
All supported devices are listed in the table below.

.. include:: .layer-boards.rst.inc


.. _sec-hsl-ti-layer-configuration:

Configuration
=============

Optionally you can customize some build options in :file:`local.conf`
configuration file (see :ref:`sec-build-local-conf` section).

Kernel Version
--------------

Each of the supported machines can be built with one of the following kernels:

- 5.10.30 (TI staging RT kernel);
- 5.4.106 (TI staging RT kernel);
- 4.19.94-rt39 (RT kernel from `TI Processor SDK Linux 06.03.00.106 <http://software-dl.ti.com/processor-sdk-linux/esd/docs/latest/linux/index.html>`_).

To choose the kernel version, you need to set variables
``TANOWRT_HSL_TI_SDK_KERNEL``, ``TI_STAGING_KERNEL_VERSION`` and ``TANOWRT_WIREGUARD_IN_KERNEL`` in the :file:`local.conf`
(see :ref:`sec-build-local-conf` section) file to determine which kernel should be used for the build.

The table below shows the values of the variables for choosing the required kernel version.

+----------------+-------------------------------+-------------------------------+---------------------------------+
| Kernel Version | ``TANOWRT_HSL_TI_SDK_KERNEL`` | ``TI_STAGING_KERNEL_VERSION`` | ``TANOWRT_WIREGUARD_IN_KERNEL`` |
+================+===============================+===============================+=================================+
| 5.10.x         | ``0``                         | ``5.10``                      | ``1``                           |
+----------------+-------------------------------+-------------------------------+---------------------------------+
| 5.4.x          | ``0``                         | ``5.4``                       | ``0``                           |
+----------------+-------------------------------+-------------------------------+---------------------------------+
| 4.19.x         | ``1``                         | *does not matter*             | ``0``                           |
+----------------+-------------------------------+-------------------------------+---------------------------------+

Default kernel version used for each target are described in the table in :ref:`sec-hsl-ti-supported-boards` section.


.. _sec-hsl-ti-uio-config:

TI UIO Support
--------------

By default TI UIO support is disabled. To enable TI UIO support to build add this to your :file:`local.conf`:

.. code-block:: bash

   ENABLE_TI_UIO_DEVICES = "1"
   TANOWRT_HSL_TI_SDK_KERNEL = "1"

.. note:: Currently this feature is supported only for kernel from TI Processor SDK (4.19).
          So you must also set ``TANOWRT_HSL_TI_SDK_KERNEL``
          to ``1`` in the :file:`local.conf` file.

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

1. `Texas Instruments Official Site <https://www.ti.com/>`__

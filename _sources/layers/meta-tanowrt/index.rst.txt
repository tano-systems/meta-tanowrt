.. SPDX-License-Identifier: MIT

.. _meta-tanowrt:

=================================
TanoWrt Core Layer (meta-tanowrt)
=================================

This layer provides OpenEmbedded metadata for TanoWrt Embedded Linux Distribution by Tano Systems.

.. figure:: /common/images/logos/tano.svg
   :width: 250

TanoWrt distribution is based on packages and fixes (patches) from the official `OpenWrt <https://openwrt.org/>`_ distribution.
Many configuration files, scripts, patches and other files for OpenWrt packages, including descriptions
in recipe files, are taken from the official OpenWrt repositories:

- https://github.com/openwrt/openwrt.git |mdash| buildsystem for the OpenWrt Linux distribution;
- https://github.com/openwrt/packages.git |mdash| OpenWrt packages feed.

This layer is initially based on the OpenEmbedded metadata layer for OpenWrt developed by `Khem Raj <https://github.com/kraj/meta-openwrt>`_
(revision `3f94c4f5aa965aa5d65419d6691b40a3870e84a8 <https://github.com/kraj/meta-openwrt/commit/3f94c4f5aa965aa5d65419d6691b40a3870e84a8>`_).

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Dependencies
============

Dependencies of the :ref:`meta-tanowrt <meta-tanowrt>` layer are described in the tables below.

.. rubric:: Mandatory Dependencies

.. list-table::
   :header-rows: 1
   :width: 100%
   :widths: 15, 40, 15, 15, 15

   * - Layer
     - URI
     - Subfolder
     - Branch
     - Configuration YML-file
   * - openembedded-core
     - https://git.openembedded.org/openembedded-core
     - meta
     - hardknott
     - :download:`kas/layers/openembedded-core.yml <../../../kas/layers/openembedded-core.yml>`
   * - meta-openembedded
     - https://git.openembedded.org/meta-openembedded
     - meta-oe
     - hardknott
     - :download:`kas/layers/meta-oe.yml <../../../kas/layers/meta-oe.yml>`
   * - meta-python
     - https://git.openembedded.org/meta-openembedded
     - meta-python
     - hardknott
     - :download:`kas/layers/meta-python.yml <../../../kas/layers/meta-python.yml>`
   * - meta-networking
     - https://git.openembedded.org/meta-openembedded
     - meta-networking
     - hardknott
     - :download:`kas/layers/meta-networking.yml <../../../kas/layers/meta-networking.yml>`
   * - meta-filesystems
     - https://git.openembedded.org/meta-openembedded
     - meta-filesystems
     - hardknott
     - :download:`kas/layers/meta-filesystems.yml <../../../kas/layers/meta-filesystems.yml>`
   * - meta-swupdate
     - https://github.com/sbabic/meta-swupdate.git
     - --
     - hardknott
     - :download:`kas/layers/meta-swupdate.yml <../../../kas/layers/meta-swupdate.yml>`

.. rubric:: Optional Dependencies

These dependencies are optional, depending on the built image or target machine.

.. list-table::
   :header-rows: 1
   :width: 100%
   :widths: 15, 40, 15, 15, 15

   * - Layer
     - URI
     - Subfolder
     - Branch
     - Configuration YML-file
   * - meta-qt5
     - https://github.com/meta-qt5/meta-qt5.git
     - --
     - hardknott
     - :download:`kas/layers/meta-qt5.yml <../../../kas/layers/meta-qt5.yml>`

Supported Virtual Machines
==========================

The TanoWrt core layer provides support for a target devices
intended to run in a virtual environment (QEMU, VirtualBox, etc).

.. include:: .layer-boards.rst.inc

Additional Information
======================

.. toctree::
   :maxdepth: 1

   extra/virtualbox.rst
   extra/services.rst
   variables.rst

License
============

All metadata is MIT licensed unless otherwise stated.
Source code included in tree for individual recipes is under the
LICENSE stated in each recipe (``.bb`` file) unless otherwise stated.

Maintainers
============

Anton Kikin <a.kikin@tano-systems.com>

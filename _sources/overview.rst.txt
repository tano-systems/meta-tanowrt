.. SPDX-License-Identifier: MIT

========
Overview
========

.. todo:: Under development

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:

.. _sec-intro:

Introduction
============

.. todo:: Add content


.. _sec-sources:

Source Code
===========

.. TanoWrt is an open source project that is hosted on the GitHub.

TanoWrt is an open source project. TanoWrt source code is available
on `GitHub <https://github.com/tano-systems/meta-tanowrt>`__.

.. todo:: Add content


.. _sec-license:

License
-------

All metadata in TanoWrt repositories is :tanowrt_github_blob:`MIT licensed </LICENSE>`
unless otherwise stated. Source code included in tree for individual recipes is under the
``LICENSE`` stated in each recipe (:file:`.bb` file) unless otherwise stated.

.. image:: /common/images/logos/mit-license.png
   :width: 150


.. _sec-supported-targets:

Supported Targets
=================

Target YAML-files are located in :file:`kas/targets` subfolder of the
``meta-tanowrt`` repository. Available target files listed in the subsections below
and grouped by support layers.


Virtual Machines
----------------

**Hardware Support Layer:** none, provided by :ref:`meta-tanowrt`

.. include:: /layers/meta-tanowrt/.layer-boards-table.rst.inc


Intel CPU Based Machines
------------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-intel`

.. include:: /layers/meta-tanowrt-hsl-intel/.layer-boards-table.rst.inc


Microchip/Atmel SoC's
---------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-atmel`

.. include:: /layers/meta-tanowrt-hsl-atmel/.layer-boards-table.rst.inc


Baikal Electronics SoC's
------------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-baikal`

.. include:: /layers/meta-tanowrt-hsl-baikal/.layer-boards-table.rst.inc


NXP/Freescale SoC's
-------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-nxp`

.. include:: /layers/meta-tanowrt-hsl-nxp/.layer-boards-table.rst.inc


Fuzhou Rockchip Electronics.SoC's
---------------------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-rockchip`

.. include:: /layers/meta-tanowrt-hsl-rockchip/.layer-boards-table.rst.inc


Raspberry Pi Boards
-------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-rpi`

.. include:: /layers/meta-tanowrt-hsl-rpi/.layer-boards-table.rst.inc


Sierra Wireless LTE Modules
---------------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-swi`

.. include:: /layers/meta-tanowrt-hsl-swi/.layer-boards-table.rst.inc


Texas Instruments SoC's
-----------------------

**Hardware Support Layer:** :ref:`meta-tanowrt-hsl-ti`

.. include:: /layers/meta-tanowrt-hsl-ti/.layer-boards-table.rst.inc


.. _sec-access-creds:

Default Access Credentials
==========================

The following credentials are used by default to access the TanoWrt via
CLI (Command Line Interface), SSH (Secure SHell) and WebUI (Web User Interface):

- Username: ``root``
- Password: ``root``

.. SPDX-License-Identifier: MIT

.. _sec-firmware-upgrade:

================
Firmware Upgrade
================

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Overview
========

.. todo:: Add content


Upgrade Running System
======================

If target device supports the firmware upgrade you can upgrade
the running system via CLI (Command Line Interface) or WebUI (Web User Interface).

CLI (Command Line Interface)
----------------------------

Use the following commands to upgrade the firmware using the command line:

- Upgrade from a local file (e.g. on an USB flash drive):

  .. code-block:: console

     [root@tanowrt ~]# swupdate-client /mnt/sdb1/<rootfs-image>-swu-<MACHINE>.swu

- Upgrade from a file hosted on a remote HTTP server:

  .. code-block:: console

     [root@tanowrt ~]# wget -O- http://remote.server.com/path/to/<rootfs-image>-swu-<MACHINE>.swu | swupdate-client


WebUI (Web User Interface)
--------------------------

To upgrade the firmware using the web interface,
choose :menuselection:`System --> Firmware Upgrade` in the main menu.
Next, follow the instructions on the page.

.. _fig-mbm-luci-swupdate:
.. figure:: /common/images/luci/page-swupdate.png
    :width: 900

    LuCI WebUI Firmware Upgrade Page

References
==========

1. `SWUpdate: Software Update for Embedded Systems <https://sbabic.github.io/swupdate/swupdate.html>`__

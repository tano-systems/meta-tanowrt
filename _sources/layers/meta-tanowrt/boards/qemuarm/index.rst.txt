.. SPDX-License-Identifier: MIT

.. _machine-qemuarm:
.. _machine-qemuarm-screen:
.. _machine-qemuarm64:
.. _machine-qemuarm64-screen:

********************
ARM Virtual Machines
********************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-qemuarm-targets:

Build Targets
=============

.. _sec-qemuarm-machines:

Machines
--------

.. _table-qemuarm-machines:
.. table:: Supported Machines

   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | VM\ [#]_            | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +=====================+==============================+==========================+====================================+========================+==========================+
   | ARMv5 (ARM926EJ-S)  | ``qemuarm.yml``              | ``qemuarm``              | ``tanowrt-image-full``             | Virtual HDD            | |ndash|                  |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | ARMv5 (ARM926EJ-S)  | ``qemuarm-screen.yml``       | ``qemuarm-screen``       | ``tanowrt-image-full``             | Virtual HDD            | |ndash|                  |
   | widh screen support |                              |                          |                                    |                        |                          |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | ARMv8 (aarch64)     | ``qemuarm64.yml``            | ``qemuarm64``            | ``tanowrt-image-full``             | Virtual HDD            | |ndash|                  |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | ARMv8 (aarch64)     | ``qemuarm64-screen.yml``     | ``qemuarm64-screen``     | ``tanowrt-image-full``             | Virtual HDD            | |ndash|                  |
   | with screen support |                              |                          |                                    |                        |                          |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target virtual machine.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-qemuarm-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-qemuarm-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.

.. _sec-qemuarm-images:

Images
------

.. _table-qemuarm-images:
.. table:: Supported Images
   :widths: 15, 15, 15, 55

   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+
   | Read-Only Root Filesystem | Recipe\ [#]_                       | Supported by Target(s)     | Description                                         |
   | Image                     |                                    |                            |                                                     |
   +===========================+====================================+============================+=====================================================+
   | ``tanowrt-image-full``    | ``tanowrt-image-full``             | *All*                      | Standard TanoWrt image.                             |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+
   | ``tanowrt-image-qt5``     | ``tanowrt-image-qt5``              || ``qemuarm-screen.yml``    | Standard TanoWrt image                              |
   |                           |                                    || ``qemuarm64-screen.yml``  | with Qt5 support.                                   |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-qemuarm-build` section).


.. _sec-qemuarm-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-qemuarm-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-qemuarm-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-qemuarm-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Image for ARMv8 (aarch64) Virtual Machine
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build targets/kas/qemuarm64.yml


Build Default Image for ARMv8 (aarch64) with Screen Support Virtual Machine
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build targets/kas/qemuarm64-screen.yml


Build Image with Qt5 for ARMv8 (aarch64) with Screen Support Virtual Machine
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build --target tanowrt-image-full-qt5 targets/kas/qemuarm64-screen.yml


.. _sec-qemuarm-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-qemuarm-artifacts` for a description of some common (not all) build artifacts.

.. _table-qemuarm-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | Artifact                                                      | Target(s)                  | Description                                                   |
   +===============================================================+============================+===============================================================+
   | .. centered:: Linux Kernel                                                                                                                                 |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`Image-<MACHINE>.bin`                                   | *All*                      | Linux kernel binary image.                                    |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                       |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.wic`                          | *All*                      | Bootable image including all required partitions for booting  |
   |                                                               |                            | and running the virtual machine. This image is ready to be    |
   |                                                               |                            | run in QEMU.                                                  |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifacts file names are replaced by the actual
          value of the ``MACHINE`` BitBake variable for the chosen `target <sec-qemuarm-targets_>`__.
          ``<rootfs-image>`` is replaced by the actual read-only root filesystem `image <sec-qemuarm-images_>`__ name.

For example, below is the lists of artifacts produced by the ``qemuarm64-screen.yml``
target build. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/qemuarm64-screen]$ ls -gGh | grep -v -e "^l"
         total 48M
         -rw-r--r-- 2  21M Jul 29 17:29 Image--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemuarm64-screen-20220729134818.bin
         -rw-r--r-- 2 2.3M Jul 29 17:29 modules--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemuarm64-screen-20220729134818.tgz
         -rw-r--r-- 2 5.0K Jul 29 17:31 tanowrt-image-full.env
         -rw-r--r-- 2 1.7K Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.qemuboot.conf
         -rw-r--r-- 2  60K Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.manifest
         -rw-r--r-- 2   24 Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.version
         -rw-r--r-- 2  91M Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.wic
         -rw-r--r-- 2 319K Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.testdata.json

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/qemuarm64-screen]$ ls -gGh
         total 48M
         lrwxrwxrwx 2   81 Jul 29 17:29 Image -> Image--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemuarm64-screen-20220729134818.bin
         -rw-r--r-- 2  21M Jul 29 17:29 Image--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemuarm64-screen-20220729134818.bin
         lrwxrwxrwx 2   81 Jul 29 17:29 Image-qemuarm64-screen.bin -> Image--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemuarm64-screen-20220729134818.bin
         -rw-r--r-- 2 2.3M Jul 29 17:29 modules--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemuarm64-screen-20220729134818.tgz
         lrwxrwxrwx 2   83 Jul 29 17:29 modules-qemuarm64-screen.tgz -> modules--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemuarm64-screen-20220729134818.tgz
         -rw-r--r-- 2 5.0K Jul 29 17:31 tanowrt-image-full.env
         -rw-r--r-- 2 1.7K Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.qemuboot.conf
         -rw-r--r-- 2  60K Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.manifest
         -rw-r--r-- 2   24 Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.version
         -rw-r--r-- 2  91M Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.wic
         -rw-r--r-- 2 319K Jul 29 17:31 tanowrt-image-full-qemuarm64-screen-20220729134818.testdata.json
         lrwxrwxrwx 2   66 Jul 29 17:31 tanowrt-image-full-qemuarm64-screen.manifest -> tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.manifest
         lrwxrwxrwx 2   64 Jul 29 17:31 tanowrt-image-full-qemuarm64-screen.qemuboot.conf -> tanowrt-image-full-qemuarm64-screen-20220729134818.qemuboot.conf
         lrwxrwxrwx 2   64 Jul 29 17:31 tanowrt-image-full-qemuarm64-screen.testdata.json -> tanowrt-image-full-qemuarm64-screen-20220729134818.testdata.json
         lrwxrwxrwx 2   65 Jul 29 17:31 tanowrt-image-full-qemuarm64-screen.version -> tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.version
         lrwxrwxrwx 2   61 Jul 29 17:31 tanowrt-image-full-qemuarm64-screen.wic -> tanowrt-image-full-qemuarm64-screen-20220729134818.rootfs.wic


.. _sec-qemuarm-booting:

Booting and Running
===================

To run the built virtual machine image in QEMU you should run the command:

.. code-block:: console

   $ kas shell -c "runqemu <MACHINE>" kas/targets/<TARGET_YAML>

Where ``<MACHINE>`` is a target machine name for required target
and ``<TARGET_YAML>`` is a target YAML-file located in the :file:`kas/targets` directory.
You can find values for ``<MACHINE>`` and ``<TARGET_YAML>`` in the table :numref:`table-qemuarm-machines`.
For example, to start virtual machine for ``qemuarm64.yml`` target you need
to run the following command:

.. code-block:: console

   $ kas shell -c "runqemu qemuarm64" kas/targets/qemuarm64.yml

When the virtual machine boots, you can log in using the default
:ref:`credentials <sec-access-creds>` or access via :ref:`network <sec-qemuarm-network-config>`
to :ref:`WebUI <sec-qemuarm-webui>`.

For running virtual machine with screen support you also need to set proper value
to environment variable ``DISPLAY``. For example, for ``qemuarm64-screen.yml`` target:

.. code-block:: console

   $ kas shell -c "DISPLAY:=0 runqemu qemuarm64-screen" kas/targets/qemuarm64-screen.yml


.. _sec-qemuarm-network-config:

Default Network Configuration
=============================

By default ``eth0`` interface are joined into a bridge
(``br-lan`` interface). Bridge (``br-lan``) configured with static IP address 192.168.7.2/24 with
enabled :term:`DHCP` server.

The ``eth0`` interface has enabled :term:`LLDP` by default.

Default configuration :file:`/etc/config/network` for ``qemuarm64-screen`` machine:

.. code-block:: console

   [root@tanowrt ~]# cat /etc/config/network

   config interface 'loopback'
           option device 'lo'
           option proto 'static'
           option ipaddr '127.0.0.1'
           option netmask '255.0.0.0'

   config device
           option name 'br-lan'
           option type 'bridge'
           option stp '1'
           list ports 'eth0'

   config interface 'lan'
           option proto 'static'
           option ipaddr '192.168.7.2'
           option netmask '255.255.255.0'
           option gateway '192.168.7.1'
           option ip6assign '60'
           option device 'br-lan'

   config globals 'globals'
           option ula_prefix 'fd05:b980:c78a::/48'


.. _sec-qemuarm-webui:

Web User Interface
==================

The WebUI can be accessed via Ethernet port or USB network connection through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-qemuarm-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-qemuarm-luci-status:
.. figure:: images/qemuarm64-screen-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-qemuarm-upgrade:

Firmware Upgrade
================

.. attention:: Currently, the firmware upgrading for the ARMv5 and ARMv8 virtual machines is not supported.

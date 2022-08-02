.. SPDX-License-Identifier: MIT

.. _sec-build:

================
Building TanoWrt
================

.. rubric:: Contents
.. contents::
   :depth: 2
   :local:

Preparing Build Environment
===========================

The recommended Linux distribution on host system
is Ubuntu 20.04 or higher.

Install Required Packages
-------------------------

For a successful build, additional packages must be installed.
To do this, run the command:

.. code-block:: console

   # sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
          build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
          xz-utils debianutils iputils-ping file

If you are using Ubuntu, you will need to configure the default system shell
command interpreter for shell scripts to bash. You can do it with the command:

.. code-block:: console

   # sudo dpkg-reconfigure dash

Select :menuselection:`No` when it asks you to install dash as :file:`/bin/sh`.

Install kas
-----------

To build TanoWrt the kas utility is used. `kas <http://github.com/siemens/kas>`_ is
a setup tool for BitBake based projects by Siemens.

To install kas into your python site-package repository, run:

.. code-block:: console

   $ pip3 install kas==2.6.3

.. note:: Currently, to build TanoWrt requires kas version 2.6.3. With
          kas version 3.0 or higher you may have problems with the build.

See `official kas documentation <https://kas.readthedocs.io/en/latest/userguide.html#dependencies-installation>`_
for more details about kas utility.

Clone TanoWrt Repository
------------------------

Clone TanoWrt core layer repository to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: console

   $ cd ~
   $ git clone https://github.com/tano-systems/meta-tanowrt tanowrt

Initialize and Update Submodules
--------------------------------

When using the kas utility, it is required to have BitBake at the root
of the meta-tanowrt layer. The BitBake submodule must be initialized
and updated before building:

.. code-block:: console

   $ cd ~/tanowrt
   $ git submodule update --init


Build
=====

In general to build TanoWrt you need to use the following command:

.. code-block:: console

   $ kas build [--target <target-recipe>] <target-yml>

Where:

.. option:: --target <target-recipe>

   **Optional**

   Recipe name for the build. Typically, you need to specify this option when you want
   to build an image (or recipe) other than the default image used for the chosen target.

   This option is not limited to choosing the image to build. This option allows
   you to build any recipe, be it an image or an individual application recipe.

.. option:: <target-yml>

   **Required**

   Path to the target YAML file. All target YAML files in TanoWrt are located
   at :file:`kas/targets` directory of the ``meta-tanowrt`` repository.

.. tip::

   If you see ``Command 'kas' not found`` just add ``~/.local/bin`` to your ``$PATH``,
   for example by adding the following line to your ``.bashrc`` file:

   .. code-block:: console

      export PATH="$HOME/.local/bin:$PATH"

   After that, restart your shell and things should work as expected.

For example, to build TanoWrt images for internal eMMC flash of the NXP LS1028A RDB board
you need to run following commands from :file:`meta-tanowrt` root directory:

.. code-block:: console

   # cd ~/tanowrt
   # kas build kas/targets/ls1028ardb-emmc.yml

See :ref:`sec-supported-targets` to select the proper target YML file.


.. _sec-build-local-conf:

Local Configuration
===================

The build configuration :file:`build/conf/local.conf` is generated
automatically by the kas utility depending on the chosen build target.

To define your own local build configuration, it must be stored in
the :file:`local.conf` file located at the root of the ``meta-tanowrt``
repository (cloned to :file:`~/tanowrt` in this example).

When building, the :file:`local.conf` file is included after the
:file:`build/conf/local.conf` file, allowing any directives declared
in :file:`build/conf/local.conf` to be overridden if necessary.

In :file:`local.conf` file you can specify any standard
configuration variables. In other words, you can do everything
in the :file:`local.conf' file that you do in the standard
:file:`build/conf/local.conf`. For example, you can specify
an external directory for storing downloaded content, as well
as configure build parallelism settings:

.. code-block:: bash

   DL_DIR = "/home/build-user/downloads"
   BB_NUMBER_THREADS = "8"
   PARALLEL_MAKE = "-j 8"

.. note:: Normally, the parallelism configuration variables ``BB_NUMBER_THREADS``
          and ``PARALLEL_MAKE`` do not need to be configured, because if they are
          not defined, the build system sets them automatically, depending on
          the number of available processors.

Examples
========

QEMU (x86_64)
-------------

Clone TanoWrt core layer repository with submodules to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: console

   $ cd ~
   $ git clone --recursive https://github.com/tano-systems/meta-tanowrt tanowrt
   $ cd ~/tanowrt

Build TanoWrt image by running the ``kas build`` command with the path to the target YML-file as an argument:

.. code-block:: console

   $ kas build kas/targets/qemux86-64-screen.yml

Run the built image in QEMU:

.. code-block:: console

   $ kas shell -c "DISPLAY=:0 runqemu qemux86-64-screen" kas/targets/qemux86-64-screen.yml

or without graphics:

.. code-block:: console

   $ kas shell -c "runqemu qemux86-64-screen nographics" kas/targets/qemux86-64-screen.yml

Intel Core i7 (x86_64)
----------------------

Clone TanoWrt core layer repository with submodules to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: console

   $ cd ~
   $ git clone --recursive https://github.com/tano-systems/meta-tanowrt tanowrt
   $ cd ~/tanowrt

Build TanoWrt image by running the ``kas build`` command with the path to the target YML-file as an argument:

.. code-block:: console

   $ kas build kas/targets/intel-x86_64-corei7.yml

Run the built image in QEMU:

.. code-block:: console

   $ kas shell -c "DISPLAY=:0 runqemu intel-x86_64-corei7" kas/targets/intel-x86_64-corei7.yml


Additional Information
======================

.. toctree::
   :maxdepth: 1

   extra/build-docker.rst
   extra/build-optimization.rst

References
==========

1. `Official kas Documentation <https://kas.readthedocs.io/en/latest/userguide.html#dependencies-installation>`__

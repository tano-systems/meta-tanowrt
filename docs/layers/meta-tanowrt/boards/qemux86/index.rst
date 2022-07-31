.. SPDX-License-Identifier: MIT

.. _machine-qemux86:
.. _machine-qemux86-screen:
.. _machine-qemux86-64:
.. _machine-qemux86-64-screen:

********************
x86 Virtual Machines
********************

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:


.. _sec-qemux86-targets:

Build Targets
=============

.. _sec-qemux86-machines:

Machines
--------

.. _table-qemux86-machines:
.. table:: Supported Machines

   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | VM\ [#]_            | Target YAML\ [#]_            | Machine\ [#]_            | Target Recipe(s)\ [#]_             | Running Media\ [#]_    | Installation Media\ [#]_ |
   +=====================+==============================+==========================+====================================+========================+==========================+
   | Common x86          | ``qemux86.yml``              | ``qemux86``              | ``tanowrt-image-full-swu-factory`` | Virtual HDD            | ISO image (Virtual CD)   |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Common x86 with     | ``qemux86-screen.yml``       | ``qemux86-screen``       | ``tanowrt-image-full-swu-factory`` | Virtual HDD            | ISO image (Virtual CD)   |
   | screen support      |                              |                          |                                    |                        |                          |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Common x86-64       | ``qemux86-64.yml``           | ``qemux86-64``           | ``tanowrt-image-full-swu-factory`` | Virtual HDD            | ISO image (Virtual CD)   |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+
   | Common x86-64 with  | ``qemux86-64-screen.yml``    | ``qemux86-64-screen``    | ``tanowrt-image-full-swu-factory`` | Virtual HDD            | ISO image (Virtual CD)   |
   | screen support      |                              |                          |                                    |                        |                          |
   +---------------------+------------------------------+--------------------------+------------------------------------+------------------------+--------------------------+

.. [#] Target virtual machine.
.. [#] Target YAML-file located in the :file:`kas/targets` directory.
.. [#] Target machine name stored in the ``MACHINE`` BitBake variable for selected Target YAML.
.. [#] Recipes that will be built by default for the target. In :numref:`sec-qemux86-images`,
       you can find list of supported recipes for the target images, which you can build in addition
       to the default recipes using optional ``--target`` option in build command
       (see :numref:`sec-qemux86-build`).
.. [#] External or internal data storage where the TanoWrt operating system is running.
.. [#] External storage device for which an installation image is generated. When booting from
       the Installation Media, the TanoWrt system is installed on the Running Media storage.

.. _sec-qemux86-images:

Images
------

.. _table-qemux86-images:
.. table:: Supported Images
   :widths: 15, 15, 15, 55

   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+
   | Read-Only Root Filesystem | Recipe\ [#]_                       | Supported by Target(s)     | Description                                         |
   | Image                     |                                    |                            |                                                     |
   +===========================+====================================+============================+=====================================================+
   | ``tanowrt-image-full``    | ``tanowrt-image-full``             | *All*                      | Standard TanoWrt image.                             |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu``         | *All*                      | Standard TanoWrt image                              |
   |                           |                                    |                            | and :ref:`firmware upgrade <sec-firmware-upgrade>`  |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-full`` will also be built           |
   |                           |                                    |                            | as dependency.                                      |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-full-swu-factory`` | *All*                      | Factory installation image for standard TanoWrt  .  |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-full``                              |
   |                           |                                    |                            | and ``tanowrt-image-full-swu`` will also be built   |
   |                           |                                    |                            | as dependencies.                                    |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+
   | ``tanowrt-image-qt5``     | ``tanowrt-image-qt5``              || ``qemux86-screen.yml``    | Standard TanoWrt image                              |
   |                           |                                    || ``qemux86-64-screen.yml`` | with Qt5 support.                                   |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-qt5-swu``          || ``qemux86-screen.yml``    | Standard TanoWrt image with Qt5 support             |
   |                           |                                    || ``qemux86-64-screen.yml`` | and :ref:`firmware upgrade <sec-firmware-upgrade>`  |
   |                           |                                    |                            | image. When building this image,                    |
   |                           |                                    |                            | ``tanowrt-image-qt5`` will also be built            |
   |                           |                                    |                            | as dependency.                                      |
   |                           +------------------------------------+----------------------------+-----------------------------------------------------+
   |                           | ``tanowrt-image-qt5-swu-factory``  || ``qemux86-screen.yml``    | Factory installation image for standard TanoWrt  .  |
   |                           |                                    || ``qemux86-64-screen.yml`` | image with Qt5 support. When building this image,   |
   |                           |                                    |                            | ``tanowrt-image-qt5``                               |
   |                           |                                    |                            | and ``tanowrt-image-qt5-swu`` will also be built    |
   |                           |                                    |                            | as dependencies.                                    |
   +---------------------------+------------------------------------+----------------------------+-----------------------------------------------------+

.. [#] Image recipe name. This name can be used as argument
       for ``--target`` build command option (see :numref:`sec-qemux86-build` section).


.. _sec-qemux86-build:

Build
=====

Please read the common information on how to perform a TanoWrt
images build and preparing the build environment in section ":ref:`sec-build`".

.. seealso:: 

   - See section :numref:`sec-qemux86-machines` to select the required target YAML file (``<target-yml>``).
   - See section :numref:`sec-qemux86-images` to select the required root filesystem image recipe (``<target-recipe>``).
   - See section :numref:`sec-qemux86-artifacts` for detailed information
     about the produced build artifacts.

Examples
--------

Build Default Image for Common x86-64 Virtual Machine
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build targets/kas/qemux86-64.yml


Build Default Image for Common x86-64 with Screen Support Virtual Machine
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build targets/kas/qemux86-64-screen.yml


Build Image with Qt5 for Common x86-64 with Screen Support Virtual Machine
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

.. code-block:: console

   $ kas build --target tanowrt-image-full-qt5 targets/kas/qemux86-64-screen.yml


.. _sec-qemux86-artifacts:

Produced Build Artifacts
========================

All produced build artifacts are stored in the :file:`~/tanowrt/build/tanowrt-glibc/deploy/images/<MACHINE>` directory.
Refer to table :ref:`table-qemux86-artifacts` for a description of some common (not all) build artifacts.

.. _table-qemux86-artifacts:
.. table:: Produced Build Artifacts
   :widths: 15, 15, 70

   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | Artifact                                                      | Target(s)                  | Description                                                   |
   +===============================================================+============================+===============================================================+
   | .. centered:: UEFI Firmware                                                                                                                                |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`ovmf.qcow2`                                            | *All*                      | Flash image with UEFI Firmware based on TianoCore.            |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | .. centered:: Bootloader                                                                                                                                   |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`grub-efi-bootia32.efi`                                 || ``qemux86.yml``           | GRUB bootloader image for x86 architecture.                   |
   |                                                               || ``qemux86-screen.yml``    |                                                               |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`grub-efi-bootx64.efi`                                  || ``qemux86-64.yml``        | GRUB bootloader image for x86_64 architecture.                |
   |                                                               || ``qemux86-64-screen.yml`` |                                                               |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`grub-efi-grubenv`                                      | *All*                      | GRUB bootloader initial environment.                          |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`grub-efi-startup.nsh`                                  | *All*                      | Startup script for UEFI shell                                 |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`grub.cfg`                                              | *All*                      | GRUB bootloader configuration file                            |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | .. centered:: Linux Kernel                                                                                                                                 |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`bzImage-<MACHINE>.bin`                                 | *All*                      | Compressed Linux kernel binary image.                         |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`bzImage-<MACHINE>.ext4`                                | *All*                      | Compressed Linux kernel binary image packed into              |
   |                                                               |                            | an ext4 file system image.                                    |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | .. centered:: Images                                                                                                                                       |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.wic`                          | *All*                      | Bootable image including all required partitions for booting  |
   |                                                               |                            | and running the virtual machine. This image is ready to be    |
   |                                                               |                            | run with QEMU or write to USB flash drive or HDD drive using  |
   |                                                               |                            | the :command:`dd` utility or similar.                         |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.wic.vmdk`                     | *All*                      | Same as :file:`<rootfs-image>-<MACHINE>.wic` but              |
   |                                                               |                            | in VMDK (Virtual Machine Disk) format.                        |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-<MACHINE>.squashfs-lzo`                 | *All*                      | Read-only root filesystem image                               |
   |                                                               |                            | (squashfs with LZO compression).                              |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-factory-<MACHINE>.iso`              | *All*                      | ISO factory installation image.                               |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`tanowrt-image-initramfs-swu-factory-<MACHINE>.cpio.gz` | *All*                      | Root filesystem initramfs image for factory installtion       |
   |                                                               |                            | image. This image is included in factory installation image   |
   |                                                               |                            | as initramfs image.                                           |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+
   | :file:`<rootfs-image>-swu-<MACHINE>.swu`                      | *All*                      | :ref:`Firmware upgrade <sec-firmware-upgrade>` image.         |
   +---------------------------------------------------------------+----------------------------+---------------------------------------------------------------+

.. note:: ``<MACHINE>`` in the artifacts path and artifacts file names are replaced by the actual
          value of the ``MACHINE`` BitBake variable for the chosen `target <sec-qemux86-targets_>`__.
          ``<rootfs-image>`` is replaced by the actual read-only root filesystem `image <sec-qemux86-images_>`__ name.

For example, below is the lists of artifacts produced by the ``qemux86-64-screen.yml``
target build. There are two types of listings here |mdash|
a complete listing, and a reduced listing without the symbolic links display.

.. tabs::

   .. tab:: Reduced

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/qemux86-64-screen]$ ls -gGh | grep -v -e "^l"
         total 413M
         -rw-r--r-- 2 9.2M Jul 29 16:26 bzImage--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen-20220729132401.bin
         -rw-r--r-- 2  12M Jul 29 16:26 bzImage-5.10.70+gitAUTOINC+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen.ext4
         -rw-r--r-- 2  12M Jul 29 16:26 bzImage-qemux86-64-screen.ext4
         drwxr-xr-x 2 4.0K Mar 15 14:12 grub
         -rwxr-xr-x 2 1.9K Mar 15 14:12 grub.cfg
         -rw-r--r-- 2 712K Mar 15 06:06 grub-efi-bootx64.efi
         -rw-r--r-- 2   23 Mar 15 06:06 grub-efi-bootx64.efi.version
         -rwxr-xr-x 2 1.0K Mar 15 14:12 grub-efi-grubenv
         -rw-r--r-- 2   26 Mar 15 06:06 grub-efi-startup.nsh
         -rw-r--r-- 2 7.3M Jul 29 16:26 modules--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen-20220729132401.tgz
         -rw-r--r-- 2 3.9M Mar 15 05:58 ovmf.code.qcow2
         -rw-r--r-- 2 4.4M Mar 15 05:58 ovmf.qcow2
         -rw-r--r-- 2 896K Mar 15 05:58 ovmf.vars.qcow2
         -rw-r--r-- 2 5.6K Jul 29 16:27 tanowrt-image-full.env
         -rw-r--r-- 2 1.7K Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.qemuboot.conf
         -rw-r--r-- 2  87K Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.manifest
         -rw-r--r-- 2  37M Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 29 16:28 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.version
         -rw-r--r-- 2 1.2G Jul 29 16:28 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.wic
         -rw-r--r-- 2  95M Jul 29 16:28 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.wic.vmdk
         -rw-r--r-- 2 273K Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.testdata.json
         -rw-r--r-- 2 6.0K Jul  4 22:03 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  71M Jul 29 16:28 tanowrt-image-full-swu-factory-qemux86-64-screen-20220729132401.iso
         -rw-r--r-- 2  273 Jul  4 22:03 tanowrt-image-full-swu-factory-qemux86-swu-factory.wks
         -rw-r--r-- 2  50M Jul 29 16:28 tanowrt-image-full-swu-qemux86-64-screen-20220729132401.swu
         -rw-r--r-- 2 2.0K Jul 29 16:27 tanowrt-image-full-tanowrt-image-qemux86.wks
         -rw-r--r-- 2 1.7K Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.qemuboot.conf
         -rw-r--r-- 2  11M Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.cpio.gz
         -rw-r--r-- 2 2.9K Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.manifest
         -rw-r--r-- 2   24 Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.version
         -rw-r--r-- 2 278K Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.testdata.json

   .. tab:: Complete

      .. code-block:: console

         [~/tanowrt/build/tanowrt-glibc/deploy/images/qemux86-64-screen]$ ls -gGh
         total 413M
         lrwxrwxrwx 2   84 Jul 29 16:26 bzImage -> bzImage--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen-20220729132401.bin
         -rw-r--r-- 2 9.2M Jul 29 16:26 bzImage--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen-20220729132401.bin
         -rw-r--r-- 2  12M Jul 29 16:26 bzImage-5.10.70+gitAUTOINC+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen.ext4
         lrwxrwxrwx 2   84 Jul 29 16:26 bzImage-qemux86-64-screen.bin -> bzImage--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen-20220729132401.bin
         -rw-r--r-- 2  12M Jul 29 16:26 bzImage-qemux86-64-screen.ext4
         drwxr-xr-x 2 4.0K Mar 15 14:12 grub
         -rwxr-xr-x 2 1.9K Mar 15 14:12 grub.cfg
         -rw-r--r-- 2 712K Mar 15 06:06 grub-efi-bootx64.efi
         -rw-r--r-- 2   23 Mar 15 06:06 grub-efi-bootx64.efi.version
         -rwxr-xr-x 2 1.0K Mar 15 14:12 grub-efi-grubenv
         -rw-r--r-- 2   26 Mar 15 06:06 grub-efi-startup.nsh
         -rw-r--r-- 2 7,3M Jul 29 16:26 modules--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen-20220729132401.tgz
         lrwxrwxrwx 2   84 Jul 29 16:26 modules-qemux86-64-screen.tgz -> modules--5.10.70+git0+7dda2a9f69-tano0.2.7.20.0-qemux86-64-screen-20220729132401.tgz
         -rw-r--r-- 2 3.9M Mar 15 05:58 ovmf.code.qcow2
         -rw-r--r-- 2 4.4M Mar 15 05:58 ovmf.qcow2
         -rw-r--r-- 2 896K Mar 15 05:58 ovmf.vars.qcow2
         -rw-r--r-- 2 5.6K Jul 29 16:27 tanowrt-image-full.env
         -rw-r--r-- 2 1.7K Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.qemuboot.conf
         -rw-r--r-- 2  87K Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.manifest
         -rw-r--r-- 2  37M Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.squashfs-lzo
         -rw-r--r-- 2   24 Jul 29 16:28 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.version
         -rw-r--r-- 2 1.2G Jul 29 16:28 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.wic
         -rw-r--r-- 2  95M Jul 29 16:28 tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.wic.vmdk
         -rw-r--r-- 2 273K Jul 29 16:27 tanowrt-image-full-qemux86-64-screen-20220729132401.testdata.json
         lrwxrwxrwx 2   67 Jul 29 16:27 tanowrt-image-full-qemux86-64-screen.manifest -> tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.manifest
         lrwxrwxrwx 2   65 Jul 29 16:27 tanowrt-image-full-qemux86-64-screen.qemuboot.conf -> tanowrt-image-full-qemux86-64-screen-20220729132401.qemuboot.conf
         lrwxrwxrwx 2   71 Jul 29 16:27 tanowrt-image-full-qemux86-64-screen.squashfs-lzo -> tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.squashfs-lzo
         lrwxrwxrwx 2   65 Jul 29 16:27 tanowrt-image-full-qemux86-64-screen.testdata.json -> tanowrt-image-full-qemux86-64-screen-20220729132401.testdata.json
         lrwxrwxrwx 2   66 Jul 29 16:28 tanowrt-image-full-qemux86-64-screen.version -> tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.version
         lrwxrwxrwx 2   62 Jul 29 16:28 tanowrt-image-full-qemux86-64-screen.wic -> tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.wic
         lrwxrwxrwx 2   67 Jul 29 16:28 tanowrt-image-full-qemux86-64-screen.wic.vmdk -> tanowrt-image-full-qemux86-64-screen-20220729132401.rootfs.wic.vmdk
         -rw-r--r-- 2 6.0K Jul  4 22:03 tanowrt-image-full-swu-factory.env
         -rw-r--r-- 2  71M Jul 29 16:28 tanowrt-image-full-swu-factory-qemux86-64-screen-20220729132401.iso
         lrwxrwxrwx 2   67 Jul 29 16:28 tanowrt-image-full-swu-factory-qemux86-64-screen.iso -> tanowrt-image-full-swu-factory-qemux86-64-screen-20220729132401.iso
         -rw-r--r-- 2  273 Jul  4 22:03 tanowrt-image-full-swu-factory-qemux86-swu-factory.wks
         -rw-r--r-- 2  50M Jul 29 16:28 tanowrt-image-full-swu-qemux86-64-screen-20220729132401.swu
         lrwxrwxrwx 2   59 Jul 29 16:28 tanowrt-image-full-swu-qemux86-64-screen.swu -> tanowrt-image-full-swu-qemux86-64-screen-20220729132401.swu
         -rw-r--r-- 2 2.0K Jul 29 16:27 tanowrt-image-full-tanowrt-image-qemux86.wks
         -rw-r--r-- 2 1.7K Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.qemuboot.conf
         -rw-r--r-- 2  11M Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.cpio.gz
         -rw-r--r-- 2 2,9K Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.manifest
         -rw-r--r-- 2   24 Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.version
         -rw-r--r-- 2 278K Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.testdata.json
         lrwxrwxrwx 2   83 Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen.cpio.gz -> tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.cpio.gz
         lrwxrwxrwx 2   84 Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen.manifest -> tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.manifest
         lrwxrwxrwx 2   82 Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen.qemuboot.conf -> tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.qemuboot.conf
         lrwxrwxrwx 2   82 Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen.testdata.json -> tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.testdata.json
         lrwxrwxrwx 2   83 Jul 29 16:26 tanowrt-image-initramfs-swu-factory-qemux86-64-screen.version -> tanowrt-image-initramfs-swu-factory-qemux86-64-screen-20220729132401.rootfs.version


.. _sec-qemux86-booting:

Booting and Running
===================

To run the built virtual machine image in QEMU you should run the command:

.. code-block:: console

   $ kas shell -c "runqemu <MACHINE>" kas/targets/<TARGET_YAML>

Where ``<MACHINE>`` is a target machine name for required target
and ``<TARGET_YAML>`` is a target YAML-file located in the :file:`kas/targets` directory.
You can find values for ``<MACHINE>`` and ``<TARGET_YAML>`` in the table :numref:`table-qemux86-machines`.
For example, to start virtual machine for ``qemux86-64.yml`` target you need
to run the following command:

.. code-block:: console

   $ kas shell -c "runqemu qemux86-64" kas/targets/qemux86-64.yml

When the virtual machine boots, you can log in using the default
:ref:`credentials <sec-access-creds>` or access via :ref:`network <sec-qemux86-network-config>`
to :ref:`WebUI <sec-qemux86-webui>`.

For running virtual machine with screen support you also need to set proper value
to environment variable ``DISPLAY``. For example, for ``qemux86-64-screen.yml`` target:

.. code-block:: console

   $ kas shell -c "DISPLAY:=0 runqemu qemux86-64-screen" kas/targets/qemux86-64-screen.yml

See the following figures, showing the boot up process of the ``qemux86-64-screen``
virtual machine on the QEMU virtual screen.

.. container:: flex

   .. figure:: images/qemux86-64-screen-qemuboot-1.png
      :width: 300

      Booting in QEMU 1/5

   .. figure:: images/qemux86-64-screen-qemuboot-2.png
      :width: 300

      Booting in QEMU 2/5

   .. figure:: images/qemux86-64-screen-qemuboot-3.png
      :width: 300

      Booting in QEMU 3/5

   .. figure:: images/qemux86-64-screen-qemuboot-4.png
      :width: 300

      Booting in QEMU 4/5

   .. figure:: images/qemux86-64-screen-qemuboot-5.png
      :width: 300

      Booting in QEMU 5/5

.. seealso:: See :ref:`sec-run-in-virtualbox` for details about running built
             x86 virtual machine in Oracle VirtualBox.

.. _sec-qemux86-network-config:

Default Network Configuration
=============================

By default ``eth0`` interface are joined into a bridge
(``br-lan`` interface). Bridge (``br-lan``) configured with static IP address 192.168.7.2/24 with
enabled :term:`DHCP` server.

The ``eth0`` interface has enabled :term:`LLDP` by default.

Default configuration :file:`/etc/config/network` for ``qemux86-64-screen`` machine:

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


.. _sec-qemux86-webui:

Web User Interface
==================

The WebUI can be accessed via Ethernet port or USB network connection through HTTP(s) protocol.
You must see something like this in browser after you logged in:

.. _fig-qemux86-luci-login:
.. figure:: /common/images/luci/page-login.png
   :width: 900

   LuCI WebUI Login Page

.. _fig-qemux86-luci-status:
.. figure:: images/qemux86-64-screen-luci-status.png
   :width: 900

   LuCI WebUI Overview Page


.. _sec-qemux86-upgrade:

Firmware Upgrade
================

No special information about firmware upgrade.
Use produced :file:`.swu` :ref:`artifact <table-qemux86-artifacts>` for upgrading running system.

.. seealso:: See common instructions in :ref:`sec-firmware-upgrade` section.


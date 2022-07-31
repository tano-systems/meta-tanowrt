Enabling TI UIO
---------------

First of all, you must enable TI UIO build feature (see :ref:`sec-hsl-ti-uio-config` section)
and do a complete image :ref:`sec-am335x-icev2-build`.

When an image with TI UIO support is built, it is necessary to enable this feature
by loading the required devicetree (:file:`.dtb`) file at startup. To do this, you
need to stop booting U-Boot (press any key) when the message ``Hit any key to stop autoboot``
is displayed in the :ref:`serial console <sec-am335x-icev2-serial>` and enter following commands:

.. code-block:: console

   setenv ti_uio 1
   saveenv
   boot

When Linux boots with the TI UIO feature enabled, you should see
:file:`uio*` devices in the file:`/dev` directory:

.. code-block:: console

   [root@tanowrt ~]# ls /dev/uio*
   /dev/uio0             /dev/uio4             /dev/uio_pruss_0_mem
   /dev/uio1             /dev/uio5             /dev/uio_pruss_1_mem
   /dev/uio2             /dev/uio6             /dev/uio_pruss_mem
   /dev/uio3             /dev/uio7             /dev/uio_pruss_mem2

If you later want to boot without the TI UIO feature enabled, you need to set back
``ti_uio`` environment variable to ``0``. To do this, run the following commands in U-Boot prompt:

.. code-block:: console

   setenv ti_uio 0
   saveenv

or in the Linux terminal:

.. code-block:: console

   fw_setenv ti_uio 0

and reboot board.

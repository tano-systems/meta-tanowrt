==========================
TanoWrt Variables Glossary
==========================

This section lists specific variables used in the :ref:`TanoWrt core layer <meta-tanowrt>`
and gives an overview of their function and contents.

A B C
D E F
G H I J
K L M
N O P Q
R S :term:`T <TANOWRT_PARTUUID_BOOT>`
U V W X Y Z

.. glossary::
   :sorted:

   :term:`TANOWRT_PARTUUID_BOOT`

      This variable contains the UUID for the boot partition, specific to each supported board.
      It is usually specified in the machine configuration file. For example,
      :tanowrt_github_blob:`qemuall.inc </meta-tanowrt/conf/machine/include/qemuall.inc#L27-L32>`
      has the following UUID definitions:

      .. code-block:: bash

         TANOWRT_PARTUUID_BOOT        = "bbefcb9e-ac94-43fc-8be2-0023a70c0722"
         TANOWRT_PARTUUID_OVERLAY     = "4473a134-2531-4ab1-a5f9-dfa54376eb78"
         TANOWRT_PARTUUID_ROOTDEV_A   = "d4997f99-3f39-4952-9433-602b66222be5"
         TANOWRT_PARTUUID_ROOTDEV_B   = "fd4aab45-0ed8-48dc-8b07-6514336e2ac1"
         TANOWRT_PARTUUID_KERNELDEV_A = "a1a55b33-b1c9-4226-b2f6-0d7d901f040f"
         TANOWRT_PARTUUID_KERNELDEV_B = "d35fdb7b-4042-4f75-a4af-8983f9584b47"

   :term:`TANOWRT_PARTUUID_KERNELDEV_A`

      This variable contains the UUID for the linux kernel partition for system A,
      specific to each supported board. It is usually specified in the machine configuration file.

   :term:`TANOWRT_PARTUUID_KERNELDEV_B`

      This variable contains the UUID for the linux kernel partition for system B,
      specific to each supported board. It is usually specified in the machine configuration file.

   :term:`TANOWRT_PARTUUID_ROOTDEV_A`

      This variable contains the UUID for the read-only root filesystem for system A,
      specific to each supported board. It is usually specified in the machine configuration file.

   :term:`TANOWRT_PARTUUID_ROOTDEV_B`

      This variable contains the UUID for the read-only root filesystem for system B,
      specific to each supported board. It is usually specified in the machine configuration file.

   :term:`TANOWRT_PARTUUID_OVERLAY`

      This variable contains the UUID for the overlay partition (user data),
      specific to each supported board. It is usually specified in the machine configuration file.

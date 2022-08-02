
========================
Build Optimization Guide
========================

.. note:: Based on https://wiki.yoctoproject.org/wiki/Build_Performance

General Rules
-------------

1. Avoid disk I/O

   - Keep data on memory for as long as possible. See following ``sysctl`` settings:

     .. code-block::

        vm.dirty_background_bytes = 0
        vm.dirty_background_ratio = 90
        vm.dirty_expire_centisecs = 4320000
        vm.dirtytime_expire_seconds = 432000
        vm.dirty_bytes = 0
        vm.dirty_ratio = 60
        vm.dirty_writeback_centisecs = 0

   - Avoid swapping.
   - Lots of RAM help up to certain point. For example, increasing RAM from
     64 GiB to 128 GiB on a machine with 40 CPU cores didn't improve build times.

2. More aggressive parallelization options lead to system trashing, thus slower builds.
3. Profile the build and tune resources and parallelization options.

Speed Up TanoWrt Build
----------------------

- Recommended free disk space: about 100 GiB.
- Put the build directory on its own disk. This is good practice in its
  own right since the build system has a tendency to wear disks heavily.
- Use the ext4 filesystem for the build disk.
- Turn off journaling for ext4:

  .. code-block:: console

     $ tune2fs -O ^has_journal <disk>

- Mount using the options ``noatime,barrier=0,commit=6000``.

  .. warning:: This makes your HDD unreliable in case of power losses.
               Do not store anything of value on this HDD.

- Use a tmpfs for :file:`/tmp`.
- If you have a lot of RAM, use the RAM disk for the build disk.

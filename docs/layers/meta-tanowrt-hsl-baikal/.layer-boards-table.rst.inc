.. table:: Supported Baikal Electronics SoC's Based Boards
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +-----------------------------------------+---------------------------------+---------------------+----------------+--------------------------+--------------------------+
   | Board                                   | SoC                             | RAM                 | Kernel Version | Supported Storage(s)     | Target YAML              |
   |                                         |                                 |                     |                |                          | (at :file:`kas/targets`) |
   +=========================================+=================================+=====================+================+==========================+==========================+
   | :ref:`MBM 1.0 (TF-307) <machine-mbm10>` || **Model:** |BE-M1000-M|        | up to 64 GiB DDR4   | 5.4.156        || USB Flash               | |mbm10.yml|              |
   |                                         || **Cores:** |BE-M1000-C|        |                     | (non-RT)       || SATA\ [#not-supported]_ |                          |
   |                                         || **Frequency:** |BE-M1000-F|    |                     |                || NVMe\ [#not-supported]_ |                          |
   |                                         |                                 |                     |                || microSD                 |                          |
   +-----------------------------------------+                                 |                     |                |                          +--------------------------+
   | :ref:`MBM 2.0 (TF-307) <machine-mbm20>` |                                 |                     |                |                          | |mbm20.yml|              |
   +-----------------------------------------+---------------------------------+---------------------+----------------+--------------------------+--------------------------+

.. |BE-M1000-M| replace:: Baikal Electronics BE-M1000
.. |BE-M1000-C| replace:: 8 x ARM Cortex-A57 64 bits
.. |BE-M1000-F| replace:: up to 1.5 GHz

.. |mbm10.yml| replace:: :tanowrt_github_blob:`mbm10.yml </kas/targets/mbm10.yml>`
.. |mbm20.yml| replace:: :tanowrt_github_blob:`mbm20.yml </kas/targets/mbm20.yml>`

.. [#not-supported] Support for HDD and NVMe storages is under development. Currently supported running TanoWrt
                    only from USB flash drive.

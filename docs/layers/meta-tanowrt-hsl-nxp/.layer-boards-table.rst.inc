.. table:: Supported Freescale/NXP SoC's Based Boards
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +---------------------------------------------------+-----------------------------+----------------------+----------------+----------------------+----------------------------------+
   | Board                                             | SoC                         | RAM                  | Kernel Version | Supported Storage(s) | Target YAML                      |
   |                                                   |                             |                      |                |                      | (at :file:`kas/targets`)         |
   +===================================================+=============================+======================+================+======================+==================================+
   | :ref:`machine-ls1028ardb`                         || **Model:** |LS1028A-M|     | 4 GiB DDR4 SDRAM     | 5.4.3-rt1      | 8 GB eMMC            | |ls1028ardb-emmc.yml|            |
   |                                                   || **Cores:** |LS1028A-C|     |                      | (RT)           +----------------------+----------------------------------+
   |                                                   || **Frequency:** |LS1028A-F| |                      |                | SD card              | |ls1028ardb-sd.yml|              |
   +---------------------------------------------------+-----------------------------+----------------------+----------------+----------------------+----------------------------------+

.. |LS1028A-M| replace:: NXP Layerscape LS1028A
.. |LS1028A-C| replace:: 2 x ARM Cortex-A72 64 bit
.. |LS1028A-F| replace:: up to 1.3 GHz

.. |ls1028ardb-emmc.yml| replace:: :tanowrt_github_blob:`ls1028ardb-emmc.yml </kas/targets/ls1028ardb-emmc.yml>`
.. |ls1028ardb-sd.yml|   replace:: :tanowrt_github_blob:`ls1028ardb-sd.yml   </kas/targets/ls1028ardb-sd.yml>`

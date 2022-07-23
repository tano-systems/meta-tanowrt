.. table:: Supported Freescale/NXP SoC's Based Boards
   :width: 100%
   :widths: 20, 30, 15, 15, 20

   +---------------------------------------------------+-----------------------------+----------------------+----------------------+----------------------------------+
   | Board                                             | SoC                         | RAM                  | Supported Storage(s) | Target YAML                      |
   |                                                   |                             |                      |                      | (at :file:`kas/targets`)         |
   +===================================================+=============================+======================+======================+==================================+
   | NXP LS1028A RDB                                   || **Model:** |LS1028A-M|     | *TODO*               | eMMC flash           | |ls1028ardb-emmc.yml|            |
   | (Reference Design Board)                          || **Cores:** |LS1028A-C|     |                      +----------------------+----------------------------------+
   |                                                   || **Frequency:** |LS1028A-F| |                      | SD-card              | |ls1028ardb-sd.yml|              |
   +---------------------------------------------------+-----------------------------+----------------------+----------------------+----------------------------------+

.. |LS1028A-M| replace:: NXP LS1028A
.. |LS1028A-C| replace:: 2 x ARM Cortex-A72 64 bit
.. |LS1028A-F| replace:: up to 1.5 GHz

.. |ls1028ardb-emmc.yml| replace:: :tanowrt_github_blob:`ls1028ardb-emmc.yml </kas/targets/ls1028ardb-emmc.yml>`
.. |ls1028ardb-sd.yml|   replace:: :tanowrt_github_blob:`ls1028ardb-sd.yml   </kas/targets/ls1028ardb-sd.yml>`

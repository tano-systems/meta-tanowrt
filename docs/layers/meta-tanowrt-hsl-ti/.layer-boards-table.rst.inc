.. table:: Supported Texas Instruments Soc's Based Boards
   :width: 100%
   :widths: 20, 30, 15, 15, 20

   +---------------------------------------------------+----------------------------+----------------------+----------------------+----------------------------------+
   | Board                                             | SoC                        | RAM                  | Supported Storage(s) | Target YAML                      |
   |                                                   |                            |                      |                      | (at :file:`kas/targets`)         |
   +===================================================+============================+======================+======================+==================================+
   | BeagleBone Black                                  || **Model:** |AM3358-M|     | *TODO*               | eMMC flash           | |am335x-bbb-emmc.yml|            |
   |                                                   || **Cores:** |AM3358-C|     |                      +----------------------+----------------------------------+
   |                                                   || **Frequency:** |AM3358-F| |                      | SD card              | |am335x-bbb-sd.yml|              |
   +---------------------------------------------------+----------------------------+----------------------+----------------------+----------------------------------+
   | Texas Instruments AM3359 ICEv2 EVM (TMDSICE3359)  || **Model:** |AM3359-M|     | *TODO*               | SD card              | |am335x-icev2-sd.yml|            |
   |                                                   || **Cores:** |AM3359-C|     |                      |                      |                                  |
   |                                                   || **Frequency:** |AM3359-F| |                      |                      |                                  |
   +---------------------------------------------------+----------------------------+----------------------+----------------------+----------------------------------+
   | Texas Instruments AM574x IDK EVM (TMDSIDK574)     || **Model:** |AM5748-M|     | *TODO*               | eMMC flash           | |am574x-idk-emmc.yml|            |
   |                                                   || **Cores:** |AM5748-C|     |                      +----------------------+----------------------------------+
   |                                                   || **Frequency:** |AM5748-F| |                      | SD card              | |am574x-idk-sd.yml|              |
   +---------------------------------------------------+----------------------------+----------------------+----------------------+----------------------------------+

.. |AM3358-M| replace:: Texas Instruments AM3358
.. |AM3358-C| replace:: 1 x ARM Cortex-A8
.. |AM3358-F| replace:: up to 1 GHz

.. |AM3359-M| replace:: Texas Instruments AM3359
.. |AM3359-C| replace:: 1 x ARM Cortex-A8
.. |AM3359-F| replace:: up to 800 MHz

.. |AM5748-M| replace:: Texas Instruments AM5748
.. |AM5748-C| replace:: 2 x ARM Cortex-A15
.. |AM5748-F| replace:: up to 1.5 GHz

.. |am335x-bbb-emmc.yml| replace:: :tanowrt_github_blob:`am335x-bbb-emmc.yml </kas/targets/am335x-bbb-emmc.yml>`
.. |am335x-bbb-sd.yml|   replace:: :tanowrt_github_blob:`am335x-bbb-sd.yml   </kas/targets/am335x-bbb-sd.yml>`
.. |am335x-icev2-sd.yml| replace:: :tanowrt_github_blob:`am335x-icev2-sd.yml </kas/targets/am335x-icev2-sd.yml>`
.. |am574x-idk-emmc.yml| replace:: :tanowrt_github_blob:`am574x-idk-emmc.yml </kas/targets/am574x-idk-emmc.yml>`
.. |am574x-idk-sd.yml|   replace:: :tanowrt_github_blob:`am574x-idk-sd.yml   </kas/targets/am574x-idk-sd.yml>`

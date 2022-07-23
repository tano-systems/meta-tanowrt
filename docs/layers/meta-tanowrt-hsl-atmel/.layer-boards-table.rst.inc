.. table:: Supported Microchip/Atmel SoC's Based Boards
   :width: 100%
   :widths: 20, 30, 15, 15, 20

   +---------------------------------------------------+--------------------------------+--------------------+--------------------+----------------------------------+
   | Board                                             | SoC                            | RAM                | Supported          | Target YAML                      |
   |                                                   |                                |                    | Storage(s)         | (at :file:`kas/targets`)         |
   +===================================================+================================+====================+====================+==================================+
   | KSZ9477 Managed Switch Evaluation Kit with        || **Model:** |ATSAMA5D36-M|     | 256 MiB DDR2       | NAND flash         | |evb-ksz9477-nand.yml|           |
   | SAMA5D36 MPU (EVB-KSZ9477)                        || **Cores:** |ATSAMA5D36-C|     |                    | (256 MiB)          |                                  |
   |                                                   || **Frequency:** |ATSAMA5D36-F| |                    +--------------------+----------------------------------+
   |                                                   |                                |                    | SD-card            | |evb-ksz9477-sd.yml|             |
   |                                                   |                                |                    |                    |                                  |
   +---------------------------------------------------+                                |                    +--------------------+----------------------------------+
   | KSZ9563 Plug-in Evaluation Board (EVB-KSZ9563)    |                                |                    | NAND flash         | |evb-ksz9563-nand.yml|           |
   | on SAMA5D3 Ethernet Development System (DM320114) |                                |                    | (256 MiB)          |                                  |
   |                                                   |                                |                    +--------------------+----------------------------------+
   |                                                   |                                |                    | SD-card            | |evb-ksz9563-sd.yml|             |
   |                                                   |                                |                    |                    |                                  |
   +---------------------------------------------------+--------------------------------+--------------------+--------------------+----------------------------------+

.. |ATSAMA5D36-M| replace:: Microchip (Atmel) ATSAMA5D36
.. |ATSAMA5D36-C| replace:: 1 x ARM Cortex-A5 processor-based MPU
.. |ATSAMA5D36-F| replace:: up to 536 MHz

.. |evb-ksz9477-nand.yml|  replace:: :tanowrt_github_blob:`evb-ksz9477-nand.yml </kas/targets/evb-ksz9477-nand.yml>`
.. |evb-ksz9477-sd.yml|    replace:: :tanowrt_github_blob:`evb-ksz9477-sd.yml   </kas/targets/evb-ksz9477-sd.yml>`
.. |evb-ksz9563-nand.yml|  replace:: :tanowrt_github_blob:`evb-ksz9563-nand.yml </kas/targets/evb-ksz9563-nand.yml>`
.. |evb-ksz9563-sd.yml|    replace:: :tanowrt_github_blob:`evb-ksz9563-sd.yml   </kas/targets/evb-ksz9563-sd.yml>`



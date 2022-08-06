.. table:: Supported Microchip/Atmel SoC's Based Boards
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +---------------------------------------------------+--------------------------------+--------------------+----------------+--------------------+----------------------------------+
   | Board                                             | SoC                            | RAM                | Kernel Version | Supported          | Target YAML                      |
   |                                                   |                                |                    |                | Storage(s)         | (at :file:`kas/targets`)         |
   +===================================================+================================+====================+================+====================+==================================+
   | :ref:`machine-at91-sama5d2-xplained`              || **Model:** |ATSAMA5D27-M|     | 512 MiB DDR3L      | 4.19.78        | 4 GB eMMC          | |at91-sama5d2-xplained-emmc.yml| |
   |                                                   || **Cores:** |ATSAMA5D27-C|     |                    | (non-RT)       +--------------------+----------------------------------+
   |                                                   || **Frequency:** |ATSAMA5D27-F| |                    |                | SD card            | |at91-sama5d2-xplained-sd.yml|   |
   +---------------------------------------------------+--------------------------------+--------------------+----------------+--------------------+----------------------------------+
   | :ref:`machine-evb-ksz9477`                        || **Model:** |ATSAMA5D36-M|     | 256 MiB DDR2       | 4.19.78        | 256 MB NAND        | |evb-ksz9477-nand.yml|           |
   |                                                   || **Cores:** |ATSAMA5D36-C|     |                    | (non-RT)       +--------------------+----------------------------------+
   |                                                   || **Frequency:** |ATSAMA5D36-F| |                    |                | SD card            | |evb-ksz9477-sd.yml|             |
   +---------------------------------------------------+                                |                    |                +--------------------+----------------------------------+
   | :ref:`machine-evb-ksz9563`                        |                                |                    |                | 256 MB NAND        | |evb-ksz9563-nand.yml|           |
   |                                                   |                                |                    |                +--------------------+----------------------------------+
   |                                                   |                                |                    |                | SD card            | |evb-ksz9563-sd.yml|             |
   +---------------------------------------------------+--------------------------------+--------------------+----------------+--------------------+----------------------------------+

.. |ATSAMA5D27-M| replace:: Microchip (Atmel) ATSAMA5D27
.. |ATSAMA5D27-C| replace:: 1 x ARM Cortex-A5 processor-based MPU with FPU
.. |ATSAMA5D27-F| replace:: up to 500 MHz

.. |at91-sama5d2-xplained-emmc.yml|  replace:: :tanowrt_github_blob:`at91-sama5d2-xplained-emmc.yml </kas/targets/at91-sama5d2-xplained-emmc.yml>`
.. |at91-sama5d2-xplained-sd.yml|  replace:: :tanowrt_github_blob:`at91-sama5d2-xplained-sd.yml </kas/targets/at91-sama5d2-xplained-sd.yml>`

.. |ATSAMA5D36-M| replace:: Microchip (Atmel) ATSAMA5D36
.. |ATSAMA5D36-C| replace:: 1 x ARM Cortex-A5 processor-based MPU
.. |ATSAMA5D36-F| replace:: up to 536 MHz

.. |evb-ksz9477-nand.yml|  replace:: :tanowrt_github_blob:`evb-ksz9477-nand.yml </kas/targets/evb-ksz9477-nand.yml>`
.. |evb-ksz9477-sd.yml|    replace:: :tanowrt_github_blob:`evb-ksz9477-sd.yml   </kas/targets/evb-ksz9477-sd.yml>`
.. |evb-ksz9563-nand.yml|  replace:: :tanowrt_github_blob:`evb-ksz9563-nand.yml </kas/targets/evb-ksz9563-nand.yml>`
.. |evb-ksz9563-sd.yml|    replace:: :tanowrt_github_blob:`evb-ksz9563-sd.yml   </kas/targets/evb-ksz9563-sd.yml>`

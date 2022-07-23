.. table:: Supported Raspberry Pi Boards
   :width: 100%
   :widths: 20, 30, 15, 15, 20

   +-----------------------------+-----------------------------+------------+--------------------+----------------------------------+
   | Board                       | SoC                         | RAM        | Storage(s)         | Target YAML                      |
   |                             |                             |            |                    | (at :file:`kas/targets`)         |
   +=============================+=============================+============+====================+==================================+
   | Raspberry Pi 3 Model B/B+   || **Model:** |BCM2837-M|     | *TODO*     | microSD            | |rpi3-sd.yml|                    |
   |                             || **Cores:** |BCM2837-C|     |            |                    |                                  |
   |                             || **Frequency:** |BCM2837-F| |            |                    |                                  |
   +-----------------------------+-----------------------------+------------+--------------------+----------------------------------+

.. |BCM2837-M| replace:: Broadcom BCM2837/BCM2837B0
.. |BCM2837-C| replace:: 4 x ARM Cortex-A53 64 bits
.. |BCM2837-F| replace:: up to 1.2 GHz

.. |rpi3-sd.yml| replace:: :tanowrt_github_blob:`rpi3-sd.yml </kas/targets/rpi3-sd.yml>`

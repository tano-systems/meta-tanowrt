.. table:: Supported Broadcom SoC Boards
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +-----------------------------+-----------------------------+------------+----------------+--------------------+----------------------------------+
   | Board                       | SoC                         | RAM        | Kernel Version | Storage(s)         | Target YAML                      |
   |                             |                             |            |                |                    | (at :file:`kas/targets`)         |
   +=============================+=============================+============+================+====================+==================================+
   | :ref:`machine-rpi3`         || **Model:** |BCM2837-M|     | *TODO*     | 5.10.31        | microSD card       | |rpi3-sd.yml|                    |
   |                             || **Cores:** |BCM2837-C|     |            | (non-RT)       |                    |                                  |
   |                             || **Frequency:** |BCM2837-F| |            |                |                    |                                  |
   +-----------------------------+-----------------------------+------------+----------------+--------------------+----------------------------------+

.. |BCM2837-M| replace:: Broadcom BCM2837/BCM2837B0
.. |BCM2837-C| replace:: 4 x ARM Cortex-A53 64 bits
.. |BCM2837-F| replace:: up to 1.2 GHz

.. |rpi3-sd.yml| replace:: :tanowrt_github_blob:`rpi3-sd.yml </kas/targets/rpi3-sd.yml>`

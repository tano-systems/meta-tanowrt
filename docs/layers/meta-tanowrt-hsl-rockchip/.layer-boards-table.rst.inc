.. table:: Supported Rockchip SoC's Based Boards
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +---------------------------------------+-----------------------------+--------------------+---------------------+--------------------------+------------------------------+
   | Board                                 | SoC                         | RAM                | Kernel Version(s)   | Supported Storage(s)     | Target YAML                  |
   |                                       |                             |                    |                     |                          | (at :file:`kas/targets`)     |
   +=======================================+=============================+====================+=====================+==========================+==============================+
   | :ref:`machine-boardcon-em3566`        || **Model:** |RK3566-M|      | 2 GiB LPDDR4       || 4.19.219 (non-RT)  | microSD card             | |boardcon-em3566-sd.yml|     |
   |                                       || **Cores:** |RK3566-C|      |                    || 4.19.209-rt87 (RT) +--------------------------+------------------------------+
   |                                       || **Frequency:** |RK3566-F|  |                    |                     | 8 GB eMMC\ [#emmc-size]_ | |boardcon-em3566-emmc.yml|   |
   +---------------------------------------+-----------------------------+--------------------+                     +--------------------------+------------------------------+
   | :ref:`machine-boardcon-em3568`        || **Model:** |RK3568-M|      | 2 GiB LPDDR4       |                     | microSD card             | |boardcon-em3568-sd.yml|     |
   |                                       || **Cores:** |RK3566-C|      |                    |                     +--------------------------+------------------------------+
   |                                       || **Frequency:** |RK3566-F|  |                    |                     | 8 GB eMMC\ [#emmc-size]_ | |boardcon-em3568-emmc.yml|   |
   +---------------------------------------+-----------------------------+--------------------+                     +--------------------------+------------------------------+
   | :ref:`machine-rock-pi-s`              || **Model:** |RK3308-M|      | up to 512 MiB DDR3 |                     | microSD card             | |rock-pi-s-sd.yml|           |
   |                                       || **Cores:** |RK3308-C|      |                    |                     +--------------------------+------------------------------+
   |                                       || **Frequency:** |RK3308-F|  |                    |                     | SD NAND up to 8 GB       | |rock-pi-s-sdnand.yml|       |
   +---------------------------------------+-----------------------------+--------------------+---------------------+--------------------------+------------------------------+

.. |RK3566-M| replace:: Rockchip RK3566
.. |RK3566-C| replace:: 4 x Cortex-A55 ARM 64 bits
.. |RK3566-F| replace:: up to 1.8 GHz

.. |RK3568-M| replace:: Rockchip RK3568
.. |RK3568-C| replace:: 4 x Cortex-A55 ARM 64 bits
.. |RK3568-F| replace:: up to 2.0 GHz

.. |RK3308-M| replace:: Rockchip RK3308
.. |RK3308-C| replace:: 4 x Cortex-A35 ARM 64 bits
.. |RK3308-F| replace:: up to 1.3 GHz

.. |boardcon-em3566-sd.yml|   replace:: :tanowrt_github_blob:`boardcon-em3566-sd.yml   </kas/targets/boardcon-em3566-sd.yml>`
.. |boardcon-em3566-emmc.yml| replace:: :tanowrt_github_blob:`boardcon-em3566-emmc.yml </kas/targets/boardcon-em3566-emmc.yml>`
.. |boardcon-em3568-sd.yml|   replace:: :tanowrt_github_blob:`boardcon-em3568-sd.yml   </kas/targets/boardcon-em3568-sd.yml>`
.. |boardcon-em3568-emmc.yml| replace:: :tanowrt_github_blob:`boardcon-em3568-emmc.yml </kas/targets/boardcon-em3568-emmc.yml>`
.. |rock-pi-s-sd.yml|         replace:: :tanowrt_github_blob:`rock-pi-s-sd.yml         </kas/targets/rock-pi-s-sd.yml>`
.. |rock-pi-s-sdnand.yml|     replace:: :tanowrt_github_blob:`rock-pi-s-sdnand.yml     </kas/targets/rock-pi-s-sdnand.yml>`

.. [#emmc-size] Actual eMMC flash size is approximately 7.28 GiB

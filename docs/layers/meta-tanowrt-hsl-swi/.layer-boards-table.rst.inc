.. table:: Supported Sierra Wireless LTE Modules Based Boards
   :width: 100%
   :widths: 20, 30, 15, 15, 20

   +-----------------------------+-----------------------------+------------+--------------------+----------------------------------+
   | Board                       | SoC                         | RAM        | Storage(s)         | Target YAML                      |
   |                             |                             |            |                    | (at :file:`kas/targets`)         |
   +=============================+=============================+============+====================+==================================+
   | mangOH Green with WP7607    || **Model:** |WP7607-M|      | *TODO*     | NAND flash         | |mangoh-green-wp7607.yml|        |
   |                             || **Cores:** |WP7607-C|      |            |                    |                                  |
   |                             || **Frequency:** |WP7607-F|  |            |                    |                                  |
   +-----------------------------+-----------------------------+------------+--------------------+----------------------------------+

.. |WP7607-M| replace:: Sierra Wireless WP7607 or WP7607-1
.. |WP7607-C| replace:: 1 x ARM Cortex-A7
.. |WP7607-F| replace:: up to 1.3 GHz

.. |mangoh-green-wp7607.yml| replace:: :tanowrt_github_blob:`mangoh-green-wp7607.yml </kas/targets/mangoh-green-wp7607.yml>`

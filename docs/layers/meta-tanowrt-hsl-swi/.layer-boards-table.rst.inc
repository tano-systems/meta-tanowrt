.. table:: Supported Sierra Wireless LTE Modules Based Boards
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +------------------------------------+-----------------------------+---------------+----------------+--------------------+----------------------------------+
   | Board                              | SoC                         | RAM           | Kernel Version | Storage(s)         | Target YAML                      |
   |                                    |                             |               |                |                    | (at :file:`kas/targets`)         |
   +====================================+=============================+===============+================+====================+==================================+
   | :ref:`machine-mangoh-green-wp7607` || **Model:** |WP7607-M|      | 256 MiB\ [#]_ | 3.18.140       | 512 MiB            | |mangoh-green-wp7607.yml|        |
   |                                    || **Cores:** |WP7607-C|      |               | (non-RT)       | NAND flash\ [#]_   |                                  |
   |                                    || **Frequency:** |WP7607-F|  |               |                |                    |                                  |
   +------------------------------------+-----------------------------+---------------+----------------+--------------------+----------------------------------+

.. [#] Only 128 MiB available for the ARM processor
.. [#] Only 210 MiB available for the ARM processor

.. |WP7607-M| replace:: Sierra Wireless WP7607 or WP7607-1
.. |WP7607-C| replace:: 1 x ARM Cortex-A7
.. |WP7607-F| replace:: up to 1.3 GHz

.. |mangoh-green-wp7607.yml| replace:: :tanowrt_github_blob:`mangoh-green-wp7607.yml </kas/targets/mangoh-green-wp7607.yml>`

.. table:: Supported Intel CPU Based Boards
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +---------------------------------------------------+----------------------------+-------------+----------------+----------------------+----------------------------------+
   | Board                                             | SoC                        | RAM         | Kernel Version | Supported Storage(s) | Target YAML                      |
   |                                                   |                            |             |                |                      | (at :file:`kas/targets`)         |
   +===================================================+============================+=============+================+======================+==================================+
   | Intel Core i7 CPU (and later)                     | x86_64 (Intel Core i7)     | At least    | 5.10.78        || USB                 | |intel-x86-64-corei7.yml|        |
   +---------------------------------------------------+----------------------------+ 64 MiB      | (non-RT)       || SATA                +----------------------------------+
   | Intel Skylake CPU (and later)                     | x86_64 (Intel Skylake)     |             |                || NVMe                | |intel-x86-64-skylake.yml|       |
   +---------------------------------------------------+----------------------------+-------------+----------------+----------------------+----------------------------------+

.. |intel-x86-64-corei7.yml|  replace:: :tanowrt_github_blob:`intel-x86-64-corei7.yml  </kas/targets/intel-x86-64-corei7.yml>`
.. |intel-x86-64-skylake.yml| replace:: :tanowrt_github_blob:`intel-x86-64-skylake.yml </kas/targets/intel-x86-64-skylake.yml>`

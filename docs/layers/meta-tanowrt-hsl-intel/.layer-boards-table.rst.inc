.. table:: Supported Intel CPU Based Boards
   :width: 100%
   :widths: 20, 30, 15, 15, 20

   +---------------------------------------------------+----------------------------+-------------+----------------------+----------------------------------+
   | Board                                             | SoC                        | RAM         | Supported Storage(s) | Target YAML                      |
   |                                                   |                            |             |                      | (at :file:`kas/targets`)         |
   +===================================================+============================+=============+======================+==================================+
   | Intel Core i7 CPU (and later)                     | x86_64 (Intel Core i7)     | At least    | USB/SATA/NVMe        | |intel-x86-64-corei7.yml|        |
   +---------------------------------------------------+----------------------------+ 64 MiB      +----------------------+----------------------------------+
   | Intel Skylake CPU (and later)                     | x86_64 (Intel Skylake)     |             | USB/SATA/NVMe        | |intel-x86-64-skylake.yml|       |
   +---------------------------------------------------+----------------------------+-------------+----------------------+----------------------------------+

.. |intel-x86-64-corei7.yml|  replace:: :tanowrt_github_blob:`intel-x86-64-corei7.yml  </kas/targets/intel-x86-64-corei7.yml>`
.. |intel-x86-64-skylake.yml| replace:: :tanowrt_github_blob:`intel-x86-64-skylake.yml </kas/targets/intel-x86-64-skylake.yml>`

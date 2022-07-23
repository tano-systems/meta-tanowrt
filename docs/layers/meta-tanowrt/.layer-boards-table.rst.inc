.. table:: Supported Virtual Machines
   :width: 100%
   :widths: 20, 30, 15, 15, 20

   +---------------------------------------------------+----------------------------+-----------+----------------------+----------------------------------+
   | Virtual Device                                    | SoC                        | RAM       | Supported Storage(s) | Target YAML                      |
   |                                                   |                            |           |                      | (at :file:`kas/targets`)         |
   +===================================================+============================+===========+======================+==================================+
   | QEMU/ARMv5 (ARM926EJ-S) machine                   | Generic ARMv5              | *Virtual* | Virtual NAND flash   | |qemuarm.yml|                    |
   +---------------------------------------------------+                            | (host)    |                      +----------------------------------+
   | QEMU/ARMv5 (ARM926EJ-S) machine                   |                            |           |                      | |qemuarm-screen.yml|             |
   | with screen support                               |                            |           |                      |                                  |
   +---------------------------------------------------+----------------------------+           +----------------------+----------------------------------+
   | QEMU/ARMv8 (aarch64) machine                      | Generic ARMv8 (aarch64)    |           | Virtual NAND flash   | |qemuarm64.yml|                  |
   +---------------------------------------------------+                            |           |                      +----------------------------------+
   | QEMU/ARMv8 (aarch64) machine                      |                            |           |                      | |qemuarm64-screen.yml|           |
   | with screen support                               |                            |           |                      |                                  |
   +---------------------------------------------------+----------------------------+           +----------------------+----------------------------------+
   | QEMU/Common x86 (32-bit) machine                  | Generic x86                |           | Virtual HDD          | |qemux86.yml|                    |
   +---------------------------------------------------+                            |           |                      +----------------------------------+
   | QEMU/Common x86 (32-bit) machine                  |                            |           |                      | |qemux86-screen.yml|             |
   | with screen support                               |                            |           |                      |                                  |
   +---------------------------------------------------+----------------------------+           +----------------------+----------------------------------+
   | QEMU/Common x86 (64-bit) machine                  | Generic x86-64             |           | Virtual HDD          | |qemux86-64.yml|                 |
   +---------------------------------------------------+                            |           |                      +----------------------------------+
   | QEMU/Common x86 (64-bit) machine                  |                            |           |                      | |qemux86-64-screen.yml|          |
   | with screen support                               |                            |           |                      |                                  |
   +---------------------------------------------------+----------------------------+-----------+----------------------+----------------------------------+

.. |qemuarm.yml|           replace:: :tanowrt_github_blob:`qemuarm.yml           </kas/targets/qemuarm.yml>`
.. |qemuarm-screen.yml|    replace:: :tanowrt_github_blob:`qemuarm-screen.yml    </kas/targets/qemuarm-screen.yml>`
.. |qemuarm64.yml|         replace:: :tanowrt_github_blob:`qemuarm64.yml         </kas/targets/qemuarm64.yml>`
.. |qemuarm64-screen.yml|  replace:: :tanowrt_github_blob:`qemuarm64-screen.yml  </kas/targets/qemuarm64-screen.yml>`
.. |qemux86.yml|           replace:: :tanowrt_github_blob:`qemux86.yml           </kas/targets/qemux86.yml>`
.. |qemux86-screen.yml|    replace:: :tanowrt_github_blob:`qemux86-screen.yml    </kas/targets/qemux86-screen.yml>`
.. |qemux86-64.yml|        replace:: :tanowrt_github_blob:`qemux86-64.yml        </kas/targets/qemux86-64.yml>`
.. |qemux86-64-screen.yml| replace:: :tanowrt_github_blob:`qemux86-64-screen.yml </kas/targets/qemux86-64-screen.yml>`

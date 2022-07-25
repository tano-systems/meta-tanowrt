.. table:: Supported Virtual Machines
   :width: 100%
   :widths: 20, 30, 10, 10, 10, 20

   +------------------------+----------------------------+-----------+----------------+----------------------+----------------------------------+
   | Virtual Device         | SoC                        | RAM       | Kernel Version | Supported Storage(s) | Target YAML                      |
   |                        |                            |           |                |                      | (at :file:`kas/targets`)         |
   +========================+============================+===========+================+======================+==================================+
   | |qemux86|              | Generic x86                | *Virtual* | 5.10.70        | Virtual HDD          | |qemux86.yml|                    |
   +------------------------+                            | (host)    | (non-RT)       |                      +----------------------------------+
   | |qemux86-screen|       |                            |           |                |                      | |qemux86-screen.yml|             |
   +------------------------+----------------------------+           |                +----------------------+----------------------------------+
   | |qemux86-64|           | Generic x86-64             |           |                | Virtual HDD          | |qemux86-64.yml|                 |
   +------------------------+                            |           |                |                      +----------------------------------+
   | |qemux86-64-screen|    |                            |           |                |                      | |qemux86-64-screen.yml|          |
   +------------------------+----------------------------+           |                +----------------------+----------------------------------+
   | |qemuarm|              | Generic ARMv5              |           |                | Virtual HDD          | |qemuarm.yml|                    |
   +------------------------+                            |           |                |                      +----------------------------------+
   | |qemuarm-screen|       |                            |           |                |                      | |qemuarm-screen.yml|             |
   +------------------------+----------------------------+           |                +----------------------+----------------------------------+
   | |qemuarm64|            | Generic ARMv8 (aarch64)    |           |                | Virtual HDD          | |qemuarm64.yml|                  |
   +------------------------+                            |           |                |                      +----------------------------------+
   | |qemuarm64-screen|     |                            |           |                |                      | |qemuarm64-screen.yml|           |
   +------------------------+----------------------------+-----------+----------------+----------------------+----------------------------------+

.. |qemux86|           replace:: :ref:`Common x86 (32-bit) <machine-qemux86>`
.. |qemux86-screen|    replace:: :ref:`Common x86 (32-bit) with screen support <machine-qemux86>`
.. |qemux86-64|        replace:: :ref:`Common x86 (64-bit) <machine-qemux86-64>`
.. |qemux86-64-screen| replace:: :ref:`Common x86 (64-bit) with screen support <machine-qemux86-64-screen>`
.. |qemuarm|           replace:: :ref:`ARMv5 (ARM926EJ-S) <machine-qemuarm>`
.. |qemuarm-screen|    replace:: :ref:`ARMv5 (ARM926EJ-S) with screen support <machine-qemuarm-screen>`
.. |qemuarm64|         replace:: :ref:`ARMv8 (aarch64) <machine-qemuarm64>`
.. |qemuarm64-screen|  replace:: :ref:`ARMv8 (aarch64) with screen support <machine-qemuarm64-screen>`

.. |qemux86.yml|           replace:: :tanowrt_github_blob:`qemux86.yml           </kas/targets/qemux86.yml>`
.. |qemux86-screen.yml|    replace:: :tanowrt_github_blob:`qemux86-screen.yml    </kas/targets/qemux86-screen.yml>`
.. |qemux86-64.yml|        replace:: :tanowrt_github_blob:`qemux86-64.yml        </kas/targets/qemux86-64.yml>`
.. |qemux86-64-screen.yml| replace:: :tanowrt_github_blob:`qemux86-64-screen.yml </kas/targets/qemux86-64-screen.yml>`
.. |qemuarm.yml|           replace:: :tanowrt_github_blob:`qemuarm.yml           </kas/targets/qemuarm.yml>`
.. |qemuarm-screen.yml|    replace:: :tanowrt_github_blob:`qemuarm-screen.yml    </kas/targets/qemuarm-screen.yml>`
.. |qemuarm64.yml|         replace:: :tanowrt_github_blob:`qemuarm64.yml         </kas/targets/qemuarm64.yml>`
.. |qemuarm64-screen.yml|  replace:: :tanowrt_github_blob:`qemuarm64-screen.yml  </kas/targets/qemuarm64-screen.yml>`

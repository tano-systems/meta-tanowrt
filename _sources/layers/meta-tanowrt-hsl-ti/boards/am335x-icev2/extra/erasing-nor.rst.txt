
.. _sec-am335x-icev2-erasing-nor:

Erasing SPI NOR Flash Contents
------------------------------

The :ref:`AM3359 ICEv2 <machine-am335x-icev2>` development board from Texas Instruments
comes pre-loaded with a default application in the on-board SPI flash device. To boot Linux
from the microSD card, you must clear the SPI flash so that the
boot process falls back to microSD card boot mode. The following steps clear
the SPI flash.

1. Make sure pins 1 and 2 of Jumper J5 (sysboot) are connected on the board
2. Make sure that USB cable is connected from the AM3359 ICEv2 board to your
   host development machine
3. Launch Code Composer Studio (CCS)

   - If you do not have CCS, download it here: `CCS Download <http://processors.wiki.ti.com/index.php/Download_CCS>`_

4. Create a target configuration file in CCS to connect to the AM3359 ICEv2 board:

   - Click :menuselection:`File --> New --> Target Configuration File`
   - Filename: :file:`AM3359-ice-v2.ccxml`. Check :guilabel:`Use shared location` to be available
     to anyone who uses the workspace. Click :guilabel:`Finish`. A window opens up which is to
     configure the connection details

     - Connection: Texas Instruments XDS100v2 USB Debug Probe
     - Device: ICE_AM3359
     - Click "Save"

5. Launch your AM3359 ICEv2 Target Configuration:

   - Click :menuselection:`Window --> Show View --> Target Configurations`
   - Right click on the :file:`AM3359-ice-v2.ccxml` file and the click :guilabel:`Launch Selected Configuration`
   - This should switch your current perspective to the :guilabel:`CCS Debug` perspective.
     If it doesn't, click :menuselection:`View --> Debug` to get to the :guilabel:`CCS Debug` perspective

6. Connect the debugger to the CortexA8 core:

   - Right click on :guilabel:`Texas Instruments XDS100v2 USB Debug Probe_0/CortxA8` listed in
     the :guilabel:`Debug view` and select :menuselection:`Connect Target`.

7. Load the SPI flash programmer into the CortexA8 core:

   - Download the SPI flash programmer and unzip it: `Isdk_spi_flasher.zip <http://processors.wiki.ti.com/index.php/File:Isdk_spi_flasher.zip>`_
   - Highlight the :guilabel:`Texas Instruments XDS100v2 USB Debug Probe_0/CortexA8` by clicking on it
   - Click :menuselection:`Run --> Load --> Load Program`
   - Browse to the :file:`isdk_spi_flasher.out` file (that you just downloaded and unzipped) and click :guilabel:`OK`

8. Run the SPI flash programmer on the CortexA8:

   - Highlight the :guilabel:`Texas Instruments XDS100v2 USB Debug Probe_0/CortexA8` by clicking on it
   - Click :menuselection:`Run --> Resume`

9. At this point the SPI flash programmer is running on the CortexA8 and we just need to follow the prompts to clear the flash:

   - If it isn't opened already, open the :guilabel:`Console view` by clicking :menuselection:`Window --> Show View --> Console`
   - The program will give you the following prompts, type the responses and press :kbd:`ENTER`:

     .. code-block:: console

        Enter Operation [1 - Flash] [2 - Erase]: 2
        Enter the offset [in Hex]: 0
        Enter size to be erased in Kilo bytes: 64

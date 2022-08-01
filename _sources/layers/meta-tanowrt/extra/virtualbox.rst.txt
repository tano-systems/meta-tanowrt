.. _sec-run-in-virtualbox:

Running TanoWrt Image Inside Oracle VirtualBox
==============================================

.. rubric:: Contents
.. contents::
   :depth: 1
   :local:

Requirements
------------

Required Oracle VirtualBox 6.1 or higher.

Creating and Configuring Virtual Machine
----------------------------------------

From the Oracle VM VirtualBox Manager, create a new Virtual Machine:

.. figure:: images/virtualbox-create.png
   :class: with-shadow

1. Configure parameters in the :guilabel:`Name and operating system` group:

   - Name: tanowrt-qemux86-64;
   - Type: Linux;
   - Version: Other Linux (64-bit).

2. Configure parameters in the :guilabel:`Memory size` group:

   - Select 256 MB (or more).

3. Configure parameters in the :guilabel:`Hard disk` group:

   - Select: :guilabel:`Do not add a virtual hard disk now`.

4. Click :guilabel:`Create` button.

Now the virtual machine has been successfully created. Next you need to do
a small configuration of the created virtual machine.

1. Click on the created virtual machine in the Oracle VM VirtualBox Manager and
   press :guilabel:`Settings` button (or press :kbd:`Ctrl` + :kbd:`S`).

2. Select :guilabel:`System` on the left pane:

   - Select ICH9 in :guilabel:`Chipset` dropdown menu.
   - Enable :guilabel:`Enable EFI (special OSes only)` option.

   .. figure:: images/virtualbox-config-system.png
      :class: with-shadow

3. Select :guilabel:`Storage` on the left pane:

   - Remove existing IDE Controller (select controller and press :guilabel:`Removes selected storage controller` button.
   - Clicks on the :guilabel:`Adds new storage controller button` button and select :guilabel:`AHCI (SATA)`.
   - Select created AHCI Controller and press :guilabel:`Adds hard disk` button.
   - Select (or add) TanoWrt VMDK image (``tanowrt-image-full-qemux86-64-screen.wic.vmdk``) in the opened
     Hard Disk Selector window and press :guilabel:`Choose` button.

   .. figure:: images/virtualbox-config-storage-add.png
      :class: with-shadow

   .. figure:: images/virtualbox-config-storage-ahci.png
      :class: with-shadow

4. Click :guilabel:`OK` button


Running Virtual Machine
-----------------------

In the Oracle VM VirtualBox Manager select virtual machine and start the VM (click on the :guilabel:`Start` button):

.. figure:: images/virtualbox-run.png
   :class: with-shadow
   :width: 800

When system is boots up, you may log in with :ref:`default credentials <sec-access-creds>`:

.. figure:: images/virtualbox-run-login.png
   :class: with-shadow
   :width: 800


References
----------

1. https://www.virtualbox.org/
2. http://gmacario.github.io/howto/gdp/genivi/yocto/virtualbox/embedded/ivi/2015/11/14/running-yocto-image-inside-virtualbox.html

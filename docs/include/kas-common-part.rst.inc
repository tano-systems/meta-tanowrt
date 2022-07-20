Install Required Packages
-------------------------

For a successful build, additional packages must be installed.
To do this, run the command:

.. code-block:: console

   # sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
          build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
          xz-utils debianutils iputils-ping file

.. note:: If you are using Ubuntu, you will need to configure the default system shell
          command interpreter for shell scripts to bash. You can do it with the command:

          .. code-block:: console

             # sudo dpkg-reconfigure dash

          Select `No` when it asks you to install dash as `/bin/sh`.

.. note:: The recommended Linux distribution on host system
          is Ubuntu 20.04 or higher.

Install kas
-----------

To install kas into your python site-package repository, run:

.. code-block:: console

   $ pip3 install kas==2.6.3

.. note:: Required kas version 2.6.3.

See `official kas documentation <https://kas.readthedocs.io/en/latest/userguide.html#dependencies-installation>`_ for more details

Clone TanoWrt Repository
------------------------

Clone TanoWrt core layer repository to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: console

   $ cd ~
   $ git clone https://github.com/tano-systems/meta-tanowrt tanowrt

Initialize and Update Submodules
--------------------------------

When using the kas utility, it is required to have BitBake at the root
of the meta-tanowrt layer. The BitBake submodule must be initialized
and updated before building:

.. code-block:: console

   $ cd ~/tanowrt
   $ git submodule update --init

Build TanoWrt
-------------

To build TanoWrt images, you have to run the ``kas build`` command with the
path to the target YML-file as an argument.

.. code-block:: console

   # cd ~/tanowrt
   # kas build kas/targets/<target-yml-file>

.. tip::

   If you see ``Command 'kas' not found`` just add ``~/.local/bin`` to your ``$PATH``,
   for example by adding the following line to your ``.bashrc`` file:

   .. code-block:: console

      export PATH="$HOME/.local/bin:$PATH"

   After that, restart your shell and things should work as expected.

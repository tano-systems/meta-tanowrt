
==================================
Using Dockerized Build Environment
==================================

Clone TanoWrt repository with submodules to your home directory (this document uses ``~/tanowrt`` for example):

.. code-block:: console

   user@host:/ $ cd ~
   user@host:~ $ git clone --recursive https://github.com/tano-systems/meta-tanowrt tanowrt
   user@host:~ $ cd ~/tanowrt

Build docker image:

.. code-block:: console

   user@host:~/tanowrt $ cd .ci
   user@host:~/tanowrt/.ci $ docker build -t ubuntu:tanowrt-build \
                                          --build-arg host_uid=$(id -u) \
                                          --build-arg host_gid=$(id -g) \
                                          -f Dockerfile .

.. tip:: By default, a user ``build`` is created in the built docker image. You can change the
         user name by adding the following parameter to the docker build command:

         .. code-block::

            --build-arg username=<username>

Run created docker image and mount cloned TanoWrt repository to it:

.. code-block:: console

   user@host:~/tanowrt $ docker run --rm -it -v ~/tanowrt:/tanowrt ubuntu:tanowrt-build /bin/bash
   build@abbc5ae583ff:~$ 

Now TanoWrt repository at :file:`~/tanowrt` on host is mounted to the :file:`/tanowrt`
directory in docker. Now you can use kas utility to building TanoWrt and all other
available build commands described in the :ref:`sec-build` section.

For example, build TanoWrt default images for the ``qemux86-64-screen`` machine:

.. code-block:: console

   build@abbc5ae583ff:~$ cd /tanowrt
   build@abbc5ae583ff:/tanowrt$ kas build kas/targets/qemux86-64-screen.yml

The built images are available both in the
:file:`/tanowrt/build/tanowrt-glibc/deploy/images/qemux86-64-screen`
directory in the docker and in
:file:`~/tanowrt/build/tanowrt-glibc/deploy/images/qemux86-64-screen`
in the host system.


Specifying the Downloads Directory
----------------------------------

If you have a separate directory for storing downloads by the build process
you can use it in the docker by adding following parameter to :command:`docker run` command:

.. code-block::

   -v <path-to-downloads-on-host>:/tanowrt/build/downloads

For example, if you have a :file:`/opt/my-downloads` directory for storing downloads
on the host, your :command:`docker run` command should be like this:

.. code-block:: console
   :emphasize-lines: 2

   user@host:~/tanowrt $ docker run --rm -it -v ~/tanowrt:/tanowrt \
                                             -v /opt/my-downloads:/tanowrt/build/downloads \
                                              ubuntu:tanowrt-build /bin/bash

.. note:: The downloads directory can be mounted in another location than
          :file:`/tanowrt/build/downloads`, but in this case, you must explicitly
          specify the path to this directory in the
          :ref:`local configuration <sec-build-local-conf>`
          in the ``DL_DIR`` variable.

          .. code-block:: console

             build@abbc5ae583ff:~$ echo 'DL_DIR = "<path-to-downloads-dir>"' >> /tanowrt/local.conf

Specifying the Shared State Cache Directory
-------------------------------------------

If you have a separate directory for storing shared state cache by the build process
you can use it in the docker by adding following parameter to :command:`docker run` command:

.. code-block::

   -v <path-to-sstate-cache>:/tanowrt/build/sstate-cache

For example, if you have a :file:`/opt/sstate-cache` directory for storing shared
state cache on the host, your :command:`docker run` command should be like this:

.. code-block:: console
   :emphasize-lines: 2

   user@host:~/tanowrt $ docker run --rm -it -v ~/tanowrt:/tanowrt \
                                             -v /opt/sstate-cache:/tanowrt/build/sstate-cache \
                                              ubuntu:tanowrt-build /bin/bash

.. note:: The shared state cache directory can be mounted in another location than
          :file:`/tanowrt/build/sstate-cache`, but in this case, you must explicitly
          specify the path to this directory in the
          :ref:`local configuration <sec-build-local-conf>`
          in the ``SSTATE_DIR`` variable:

          .. code-block:: console

             build@abbc5ae583ff:~$ echo 'SSTATE_DIR = "<path-to-sstate-cache-dir>"' >> /tanowrt/local.conf


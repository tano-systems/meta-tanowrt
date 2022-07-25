Configuring PPP Connection over UART4
=====================================

Here is an instruction on how to configure PPP connection over UART4
on a :ref:`TI AM3359 ICEv2 (TMDSICE3359) <machine-am335x-icev2>` board.

By default, the pppd service is running on the UART4 port ready for
connection via null-modem cable with PC. UART4 signals are routed to
J3 connector (Host Expansion Connector 2) of ICEv2 board. To connect the
null-modem cable you need to use the following pins of the J3 connector:

+--------+---------------------------------+
| Signal | Host Expansion Connector 2 (J3) |
+========+=================================+
| GND    | Pin 2                           |
+--------+---------------------------------+
| RX     | Pin 7                           |
+--------+---------------------------------+
| TX     | Pin 8                           |
+--------+---------------------------------+

To establish a connection on the PC side you need to run the pppd service with
the following command:

.. code-block:: console

   # pppd nodetach local lock noauth nocrtscts passive <IP-1>:<IP-2> <serial-device> <baudrate> &

Where:

``<IP-1>``
    IP address of host PC (e.g. ``172.16.0.100``) for PPP connection;

``<IP-2>``
    IP address of the ICEv2 board (e.g. ``172.16.0.1``) for PPP connection;

``<serial-device>``
    Serial device name (e.g. :file:`/dev/ttyS1`);

``<baudrate>``
    Serial port baud rate (115200 by default). If you need to change the baud rate, you should also
    do it on the AM3359 ICEv2 board in the configuration file :file:`/etc/config/network``
    (section ``config interface 'uart4'``, option ``pppd_options``) and execute command

    .. code-block:: console

       [root@tanowrt ~]# ubus call network reload


Example
-------

For example, if host PC has a serial device named :file:`/dev/ttyS1`, the IP addresses
of the PPP connection should be assigned to ``172.16.0.100`` (host PC) and ``172.16.0.1``
(ICEv2 board), then pppd should be started with the following command:

.. code-block:: console

   # pppd nodetach local lock noauth nocrtscts passive 172.16.0.100:172.16.0.1 /dev/ttyS1 115200 &

After executing this command on host PC you should see the ``ppp0`` interface with
the assigned IP address ``172.16.0.100``:

.. code-block:: console

   # ifconfig ppp0
   ppp0: flags=4305<UP,POINTOPOINT,RUNNING,NOART,MULTICAST>  mtu 1500
             inet addr:172.16.0.100  netmask 255.255.255.255  destination 172.16.0.1
             ppp  txqueuelen 3  (Point-to-Point Protocol)
             RX packets 14  bytes 293 (293.0 B)
             RX errors 0  dropped 0  overruns 0  frame 0
             TX packets 11  bytes 119 (119.0 B)
             TX errors 0  dropped 0 overruns 0  carrier 0  collisions 0

Now you can access AM3359 ICEv2 board from host PC using IP address ``172.16.0.1``.
For example, you can test it with the :command:`ping` command:

.. code-block:: console

   # ping 172.16.0.1
   PING 172.16.0.1 (172.16.0.1): 56 data bytes
   64 bytes from 172.16.0.1: seq=0 ttl=64 time=21.212 ms
   64 bytes from 172.16.0.1: seq=1 ttl=64 time=22.162 ms
   64 bytes from 172.16.0.1: seq=2 ttl=64 time=22.233 ms
   64 bytes from 172.16.0.1: seq=3 ttl=64 time=22.186 ms
   64 bytes from 172.16.0.1: seq=4 ttl=64 time=20.161 ms
   ^C
   --- 172.16.0.1 ping statistics ---
   5 packets transmitted, 5 packets received, 0% packet loss
   round-trip min/avg/max = 20.161/21.590/22.233 ms


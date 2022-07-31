
.. _sec-mangoh-green-setting-up-modem:

Setting Up a Modem Data Connection
----------------------------------

1. Connect the mangOH Green board (connector CN1100) to your
   PC with ethernet cable (RJ45 connectors at both ends).
2. Go to the web interface (default IP is 192.168.10.1) of the mangOH
   Green board and log in using default :ref:`credentials <sec-access-creds>`.
3. Go to network interfaces configuration page (:menuselection:`Network --> Interfaces` in main menu).
4. Click the :guilabel:`Edit` button on the :guilabel:`MODEM` interface line:

   .. _fig-mdc-config-network-interfaces:
   .. figure:: images/mdc-config-network-interfaces.png
       :width: 800

5. Verify interface settings in the opened modal window:

   - Modem device should be set to ``/dev/ttyAT``;
   - Service type should be set to ``UMTS/GPRS``.

   .. _fig-mdc-config-if-settings:
   .. figure:: images/mdc-config-if-settings.png
       :width: 800

6. Enter the valid values for the APN (Access Point Name) and authentification
   options (PAP/CHAP username and password). The correct values for this options
   depends on your mobile operator. Here is a list of APN and authentification
   values for primary mobile operators in the Russian Federation:

   +-----------------+---------------------+--------------+--------------+
   | Mobile Operator | APN                 | User name    | Password     |
   +=================+=====================+==============+==============+
   | MTS             | internet.mts.ru     | *keep empty* | *keep empty* |
   +-----------------+---------------------+--------------+--------------+
   | Megafon         | internet            | ``gdata``    | ``gdata``    |
   +-----------------+---------------------+--------------+--------------+
   | Beeline         | internet.beeline.ru | ``beeline``  | ``beeline``  |
   +-----------------+---------------------+--------------+--------------+
   | Tele2           | internet.tele2.ru   | *keep empty* | *keep empty* |
   +-----------------+---------------------+--------------+--------------+
   | Yota            | internet.yota       | *keep empty* | *keep empty* |
   +-----------------+---------------------+--------------+--------------+

7. If your SIM card is locked by a PIN code, enter the PIN in the corresponding field.
8. If you need to automatically start the interface at system startup, enable
   :guilabel:`Bring up on boot` checkbox.
9. Click the :guilabel:`Save` button.
10. Click the :guilabel:`Save & Apply` button.

   .. _fig-mdc-config-apply:
   .. figure:: images/mdc-config-apply.png
       :width: 800

11. Wait between 5 to 20 seconds for the connection to be established.
12. The interface of the connected modem on the :guilabel:`Network interfaces`
    page should look as shown in the screenshot:

   .. _fig-mdc-connected-interfaces:
   .. figure:: images/mdc-connected-interfaces.png
       :width: 800

13. You can also see the connection status for the modem interface on the main
    page of the web-interface in the section :guilabel:`Network Interfaces Ports Status`:

   .. _fig-mdc-connected-netports:
   .. figure:: images/mdc-connected-netports.png
       :width: 800

14. A new :guilabel:`IPv4 Upstream` interface for modem should also appear in
    the :guilabel:`Network` section of the status page:

   .. _fig-mdc-connected-ipv4-upstream:
   .. figure:: images/mdc-connected-ipv4-upstream.png
       :width: 400

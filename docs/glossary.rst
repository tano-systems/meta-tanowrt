Glossary
==================================

.. glossary::
   :sorted:

   :term:`HSL`
      *Hardware Support Layer*

   :term:`BSP`
      *Board Support Package*

      In embedded systems, a board support package (BSP) is the layer of software containing
      hardware-specific boot firmware and device drivers and other routines that allow a given
      embedded operating system to function in a given hardware environment (a board), integrated
      with the embedded operating system.

   :term:`HS`
      *High-Speed*

   :term:`SDK`
      *Software Development Kit*

      A Software Development Kt (SDK) is a collection of software development tools in one
      installable package. They facilitate the creation of applications by having a compiler,
      debugger and sometimes a software framework. They are normally specific to a hardware
      platform and operating system combination.

   :term:`eSDK`
      *extensible Software Development Kit*

      The Yocto Project Extensible SDK (eSDK) has tools that allow you to easily add new
      applications and libraries to an image, modify the source of an existing component
      and test changes on the target hardware. The main benefit over the standard :term:`SDK`
      is improved team workflow due to tighter integration with the OpenEmbedded build system.

   :term:`SBC`
      *Single-Board Computer*

   :term:`EDB`
      *Embedded Development Board*

   :term:`RSTP`
      *Rapid Spanning Tree Protocol*

      In 2001, the IEEE introduced Rapid Spanning Tree Protocol (RSTP) as IEEE 802.1w.
      RSTP was then incorporated into IEEE 802.1D-2004 making the original :term:`STP` standard obsolete.
      RSTP was designed to be backward-compatible with standard :term:`STP`.

      RSTP provides significantly faster spanning tree convergence after a topology change,
      introducing new convergence behaviors and bridge port roles to accomplish this. While :term:`STP` can
      take 30 to 50 seconds to respond to a topology change, RSTP is typically able to respond to changes
      within 3 × hello times (default: 3 × 2 seconds) or within a few milliseconds of a physical
      link failure. The hello time is an important and configurable time interval that is used
      by RSTP for several purposes; its default value is 2 seconds.

   :term:`STP`
      *Spanning Tree Protocol*

      The Spanning Tree Protocol (STP) is a network protocol that builds a loop-free logical
      topology for Ethernet networks. The basic function of STP is to prevent bridge loops and
      the broadcast radiation that results from them. Spanning tree also allows a network
      design to include backup links providing fault tolerance if an active link fails.

   :term:`MSTP`
      *Multiple Spanning Tree Protocol*

      The Multiple Spanning Tree Protocol (MSTP), originally defined in IEEE 802.1s-2002 and
      later merged into IEEE 802.1Q-2005, defines an extension to :term:`RSTP` to further develop
      the usefulness of VLANs.

   :term:`DHCP`
      *Dynamic Host Configuration Protocol*

   :term:`LLDP`
      *Link Layer Discovery Protocol*

      The Link Layer Discovery Protocol (LLDP) is a vendor-neutral link layer protocol used
      by network devices for advertising their identity, capabilities, and neighbors on a
      local area network based on IEEE 802 technology, principally wired Ethernet. The protocol
      is formally referred to by the IEEE as Station and Media Access Control Connectivity Discovery
      specified in IEEE 802.1AB with additional support in IEEE 802.3 section 6 clause 79.

   :term:`DTB`
      *Device Tree Blob*

      Device tree is a data structure describing the hardware components of a particular
      computer so that the operating system's kernel can use and manage those components,
      including the CPU or CPUs, the memory, the buses and the integrated peripherals.

   :term:`DTBO`
      *Device Tree Blob Overlay*

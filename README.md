
<img src="./meta-tanowrt/docs/tano-logo.svg" width="200">

# TanoWrt Linux Distribution

This repository contains OpenEmbedded layers of TanoWrt Linux distribution by Tano Systems.

TanoWrt is an operating system (OS) distribution based on the Linux kernel and focused on running in embedded systems, including real-time systems.

## 1 Supported Hardware

TanoWrt Linux distribution [core layer](meta-tanowrt/README.md), located in `meta-tanowrt` subdirectory of this repository, supports only machines for x86 (32-bit and 64-bit), ARMv5 and ARMv8 architectures designed to run in virtual environment (QEMU, Virtual Box, VMWare etc.).

Support for real hardware is provided by additional hardware support layers (HSL) located in the `meta-tanowrt-hsl-*` subdirectories of this repository.

Detailed instructions on how to use each layer to build the TanoWrt Distribution images can be found in the documentation of the appropriate layers:

| Layer                      | Hardware                                          |
| -------------------------- | ------------------------------------------------- |
| [meta-tanowrt-hsl-nxp]     | NXP (Freescale) boards                            |
| [meta-tanowrt-hsl-rpi]     | Raspberry Pi boards                               |
| [meta-tanowrt-hsl-swi]     | Sierra Wireless LTE modules                       |
| [meta-tanowrt-hsl-ti]      | Texas Instruments SoC based devices               |

[meta-tanowrt]: meta-tanowrt/README.md
[meta-tanowrt-hsl-nxp]: meta-tanowrt-hsl-nxp/README.md
[meta-tanowrt-hsl-rpi]: meta-tanowrt-hsl-rpi/README.md
[meta-tanowrt-hsl-swi]: meta-tanowrt-hsl-swi/README.md
[meta-tanowrt-hsl-ti]: meta-tanowrt-hsl-ti/README.md

## 2 Firmware Upgrade

The TanoWrt distribution uses the [SWUpdate](https://sbabic.github.io/swupdate) project for firmware upgrade implementation. For all devices that supports the firmware upgrade feature, a [double copy with fall-back](https://sbabic.github.io/swupdate/overview.html#double-copy) strategy has been implemented.

| Layer                      | Firmware Upgrade Support                                          |
| -------------------------- | ----------------------------------------------------------------- |
| [meta-tanowrt]             | :last_quarter_moon: Partial (only for `qemux86` and `qemux86-64`) |
| [meta-tanowrt-hsl-nxp]     | :heavy_check_mark: Yes                                            |
| [meta-tanowrt-hsl-rpi]     | :new_moon: No                                                     |
| [meta-tanowrt-hsl-swi]     | :new_moon: No (has its own upgrade system)                        |
| [meta-tanowrt-hsl-ti]      | :heavy_check_mark: Yes                                            |

## 3 Prerequisites

The recommended Linux distribution is Ubuntu 18.04 or Ubuntu 20.04.

### 3.1 Install Required Packages

Install the required packages by executing the following commands depending on your distribution:
- Ubuntu or Debian

    ```shell
    sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
         build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
         xz-utils debianutils iputils-ping
    ```

- Fedora

    ```shell
    sudo dnf install gawk make wget tar bzip2 gzip python3 unzip perl patch \
         diffutils diffstat git cpp gcc gcc-c++ glibc-devel texinfo chrpath \
         ccache perl-Data-Dumper perl-Text-ParseWords perl-Thread-Queue perl-bignum \
         socat python3-pexpect findutils which file cpio python python3-pip xz
    ```

- openSUSE

    ```shell
    sudo zypper install python gcc gcc-c++ git chrpath make wget python-xml \
         diffstat makeinfo python-curses patch socat python3 python3-curses tar \
         python3-pip python3-pexpect xz which
    ```

- CentOS

    ```shell
    sudo yum install -y epel-release
    sudo yum makecache
    sudo yum install gawk make wget tar bzip2 gzip python unzip perl patch \
         diffutils diffstat git cpp gcc gcc-c++ glibc-devel texinfo chrpath socat \
         perl-Data-Dumper perl-Text-ParseWords perl-Thread-Queue python34-pip xz \
         which SDL-devel xterm
    ```

### 3.2 Configure Default System Shell

*Only for Ubuntu*

You need to configure the default system shell command interpreter for shell scripts to bash. You can do it with the command:

```shell
sudo dpkg-reconfigure dash
```

Select `No` when it asks you to install dash as `/bin/sh`.

### 3.3 Install Repo Tool

```shell
mkdir -p ~/bin
PATH=~/bin:$PATH
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
```

More detailed information about installing repo tool can be founded [here](https://gerrit.googlesource.com/git-repo).

## 4 Access

The following credentials are used by default to access the operating system (terminal) and the LuCI web-configuration interface:
* User name: `root`
* Password: `root`

## 5 Limitations

Works with OE Release 3.1 (Dunfell).

## 6 License

All metadata is MIT licensed unless otherwise stated. Source code included in tree for individual recipes is under the LICENSE stated in each recipe (.bb file) unless otherwise stated.

## 7 Maintainers

Anton Kikin <a.kikin@tano-systems.com>

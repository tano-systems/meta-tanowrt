
<img src="meta-tanowrt/docs/tano-logo.svg?raw=true" width="200">

# TanoWrt Linux Distribution

This repository contains OpenEmbedded layers of TanoWrt Linux distribution by Tano Systems.

## Prerequisites

### Install Required Packages

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

### Configure Default System Shell

*Only for Ubuntu*

You need to configure the default system shell command interpreter for shell scripts to bash. You can do it with the command:

```shell
sudo dpkg-reconfigure dash
```

Select `No` when it asks you to install dash as `/bin/sh`.

### Install Repo Tool

```shell
mkdir -p ~/bin
PATH=~/bin:$PATH
curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo
chmod a+x ~/bin/repo
```

More detailed information about installing repo tool can be founded [here](https://gerrit.googlesource.com/git-repo).

## Supported Hardware

TanoWrt Linux distribution core layer, located in `meta-tanowrt` subdirectory of this repository, supports only machines designed to run in QEMU.

Detailed instructions on how to use each layer to build the TanoWrt Distribution images can be found in the documentation of the appropriate layers:

| Layer                      | Hardware                                   |
| -------------------------- | ------------------------------------------ |
| [meta-tanowrt]             | QEMU for x86 (32-bit and 64-bit) and ARMv5 |

[meta-tanowrt]: meta-tanowrt/README.md

## Limitations

Works with OE Release 2.7 (Warrior).

## License

All metadata is MIT licensed unless otherwise stated. Source code included in tree for individual recipes is under the LICENSE stated in each recipe (.bb file) unless otherwise stated.

## Maintainers

Anton Kikin <a.kikin@tano-systems.com>

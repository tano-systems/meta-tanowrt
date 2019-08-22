# meta-tano-openwrt

This layer provides OpenEmbedded metadata for TanoWrt Linux distribution by Tano Systems. TanoWrt distribution is based on packages and fixes (patches) from the official [OpenWrt](https://openwrt.org/) distribution.

Some configuration files, scripts, patches and other files for OpenWrt packages, including descriptions in recipe files, are taken from the official OpenWrt repositories:
- https://github.com/openwrt/openwrt.git — buildsystem for the OpenWrt Linux distribution;
- https://github.com/openwrt/packages.git — OpenWrt packages feed.

This layer is initially based on a OE metadata layer for OpenWrt by [Khem Raj](https://github.com/kraj/meta-openwrt) (revision [3f94c4f5aa965aa5d65419d6691b40a3870e84a8](https://github.com/kraj/meta-openwrt/commit/3f94c4f5aa965aa5d65419d6691b40a3870e84a8))

Most OpenWrt packages corresponds to OpenWrt 18.06.2 or higher.

## Supported QEMU Machines

This layer supports the following machines designed to run in QEMU:

| `MACHINE`           | Description                                      | Rootfs   | Overlayfs      |
| ------------------- | ------------------------------------------------ | -------- | -------------- |
| `qemux86`           | Common x86 (32-bit) machine                      | squashfs | ext4 (16 MiB)  |
| `qemux86-screen`    | Common x86 (32-bit) machine with screen support  | squashfs | ext4 (16 MiB)  |
| `qemux86-64`        | Common x86 (64-bit) machine                      | squashfs | ext4 (16 MiB)  |
| `qemux86-64-screen` | Common x86 (64-bit) machine with screen support  | squashfs | ext4 (16 MiB)  |
| `qemuarm`           | ARMv5 (ARM926EJ-S) machine                       | squashfs | ext4 (16 MiB)  |
| `qemuarm-screen`    | ARMv5 (ARM926EJ-S) machine with screen support   | squashfs | ext4 (16 MiB)  |

## Supported Hardware

Support for real hardware is provided by additional layers.

Detailed instructions on how to use each layer to build the TanoWrt Distribution images can be found in the documentation of the appropriate layers.

| Layer                      | Hardware                               |
| -------------------------- | -------------------------------------- |
| [meta-tano-openwrt-rpi]    | Raspberry Pi boards                    |

[meta-tano-openwrt-rpi]: https://github.com/tano-systems/meta-tano-openwrt-rpi

## Getting Started

Install the required packages by executing the following commands depending on your distribution.

### Ubuntu and Debian

```shell
sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
     build-essential chrpath socat cpio python python3 python3-pip python3-pexpect \
     xz-utils debianutils iputils-ping
```
### Fedora

```shell
sudo dnf install gawk make wget tar bzip2 gzip python3 unzip perl patch \
     diffutils diffstat git cpp gcc gcc-c++ glibc-devel texinfo chrpath \
     ccache perl-Data-Dumper perl-Text-ParseWords perl-Thread-Queue perl-bignum socat \
     python3-pexpect findutils which file cpio python python3-pip xz
```

### openSUSE

```shell
sudo zypper install python gcc gcc-c++ git chrpath make wget python-xml \
     diffstat makeinfo python-curses patch socat python3 python3-curses tar python3-pip \
     python3-pexpect xz which
```

### CentOS

```shell
sudo yum install -y epel-release
sudo yum makecache
sudo yum install gawk make wget tar bzip2 gzip python unzip perl patch \
     diffutils diffstat git cpp gcc gcc-c++ glibc-devel texinfo chrpath socat \
     perl-Data-Dumper perl-Text-ParseWords perl-Thread-Queue python34-pip xz \
     which SDL-devel xterm
```

## Initialize Build Environment

Clone openembedded-core repository:
```shell
git clone -b warrior git://github.com/openembedded/openembedded-core.git
```

Go to the openembedded-core repository directory and clone repositories for bitbake and other dependent layers:
```shell
cd openembedded-core
git clone -b 1.42 git://github.com/openembedded/bitbake.git
git clone -b warrior git://github.com/openembedded/meta-openembedded.git
git clone -b warrior git://github.com/tano-systems/meta-tano-openwrt.git
```

The first time you need to add layers and create local.conf from the template. To do this, run the command:
```shell
TEMPLATECONF=meta-tano-openwrt/conf . ./oe-init-build-env
```

This command automatically creates a `build` subfolder with the required configuration (`local.conf` and `bblayers.conf`) based on the specified template.

If the `build` subfolder with configuration has already been created, this command can be used to initialize build environment:
```shell
. ./oe-init-build-env
```

After executing any of the specified commands, the current directory will be automatically changed to `build` subfolder. Any build commands must be run from the `build` subfolder.

## Building

Below we build for `qemux86-64` machine as an example:
```shell
MACHINE=qemux86-64 bitbake openwrt-image-full
```

Other available machines are listed in the section "[Supported QEMU Machines](#Supported-QEMU-Machines)".

## Running

### QEMU

Run command:
```shell
runqemu qemux86-64
```

### Oracle VirtualBox

You can run builded images for x86 machines (32 or 64-bit) in Oracle VirtualBox following this [instruction](docs/virtualbox.md).

## Limitations

Works with OE Release 2.7 (Warrior).

Images are buildable/bootable for arm, x86 and x86_64 based qemu machines MACHINE variable. Support for real hardware is provided by additional layers (see [this](#Supported-Hardware) section).

## Dependencies

This layer depends on:

### openembedded-core
URI: <git://git.openembedded.org/openembedded-core.git>  
Subdirectory: meta  
Branch: warrior  
Revision: 886deb4d0919c7a81036ea14fb8fd0f1619dd3a3

### meta-openembedded
URI: <git://git.openembedded.org/meta-openembedded.git>  
Subdirectory: meta-oe  
Branch: warrior  
Revision: 8d5dcd6522e9d15e68637b6d7dda0401f9bb91d0

### meta-python
URI: <git://git.openembedded.org/meta-openembedded.git>  
Subdirectory: meta-python  
Branch: warrior  
Revision: 8d5dcd6522e9d15e68637b6d7dda0401f9bb91d0

### meta-networking
URI: <git://git.openembedded.org/meta-openembedded.git>  
Subdirectory: meta-networking  
Branch: warrior  
Revision: 8d5dcd6522e9d15e68637b6d7dda0401f9bb91d0

### meta-filesystems
URI: <git://git.openembedded.org/meta-openembedded.git>  
Subdirectory: meta-filesystems  
Branch: warrior  
Revision: 8d5dcd6522e9d15e68637b6d7dda0401f9bb91d0

### bitbake
URI: <git://git.openembedded.org/bitbake>  
Branch: 1.42  
Revision: 34ed28a412af642a993642c14bd8b95d5ef22cd8


## License

All metadata is MIT licensed unless otherwise stated. Source code included in tree for individual recipes is under the LICENSE stated in each recipe (.bb file) unless otherwise stated.

## Maintainers

Anton Kikin <a.kikin@tano-systems.com>


<img src="./docs/tano-logo.svg" width="200">

# meta-tanowrt

[TanoWrt](https://github.com/tano-systems/meta-tanowrt) Linux distribution OpenEmbedded core layer.

This layer provides OpenEmbedded metadata for TanoWrt Linux distribution by Tano Systems. TanoWrt distribution is based on packages and fixes (patches) from the official [OpenWrt](https://openwrt.org/) distribution.

Some configuration files, scripts, patches and other files for OpenWrt packages, including descriptions in recipe files, are taken from the official OpenWrt repositories:
- https://github.com/openwrt/openwrt.git — buildsystem for the OpenWrt Linux distribution;
- https://github.com/openwrt/packages.git — OpenWrt packages feed.

This layer is initially based on a OE metadata layer for OpenWrt by [Khem Raj](https://github.com/kraj/meta-openwrt) (revision [3f94c4f5aa965aa5d65419d6691b40a3870e84a8](https://github.com/kraj/meta-openwrt/commit/3f94c4f5aa965aa5d65419d6691b40a3870e84a8))

Most OpenWrt packages corresponds to OpenWrt 19.07 or higher.

## 1 Supported QEMU Machines

This layer supports the following machines designed to run in QEMU:

| `MACHINE`           | Description                                      | Rootfs   | Overlayfs      |
| ------------------- | ------------------------------------------------ | -------- | -------------- |
| `qemux86`           | Common x86 (32-bit) machine                      | squashfs | ext4 (256 MiB) |
| `qemux86-screen`    | Common x86 (32-bit) machine with screen support  | squashfs | ext4 (256 MiB) |
| `qemux86-64`        | Common x86 (64-bit) machine                      | squashfs | ext4 (256 MiB) |
| `qemux86-64-screen` | Common x86 (64-bit) machine with screen support  | squashfs | ext4 (256 MiB) |
| `qemuarm`           | ARMv5 (ARM926EJ-S) machine                       | squashfs | ext4 (256 MiB) |
| `qemuarm-screen`    | ARMv5 (ARM926EJ-S) machine with screen support   | squashfs | ext4 (256 MiB) |
| `qemuarm64`         | ARMv8 (aarch64) machine                          | squashfs | ext4 (256 MiB) |
| `qemuarm64-screen`  | ARMv8 (aarch64) machine with screen support      | squashfs | ext4 (256 MiB) |

## 2 Supported Images

| Image Recipe                     | Machine(s)       | Description                                                |
| -------------------------------- | -----------------| ---------------------------------------------------------- |
| `tanowrt-image-base`             | *All*            | TanoWrt base root file system image                        |
| `tanowrt-image-full`             | *All*            | TanoWrt full featured root file system image               |
| `tanowrt-image-full-swu`         | Only `qemux86*`  | TanoWrt full featured SWU firmware upgrade image           |
| `tanowrt-image-full-swu-factory` | Only `qemux86*`  | Factory installation ISO image                             |

### 2.1 Images with Qt5

| `tanowrt-image-qt5`              | *All*            | TanoWrt full featured image with Qt5                       |
| `tanowrt-image-qt5-swu`          | Only `qemux86*`  | TanoWrt full featured SWU firmware with Qt5 upgrade image  |
| `tanowrt-image-qt5-swu-factory`  | Only `qemux86*`  | Factory installation ISO image for firmware with Qt5       |

## 3 Prerequisites

Follow the instructions outlined in "[Prerequisites](../README.md#1-Prerequisites)" section of the root README.md.

## 4 Initialize Repositories

Create a working directory (this document uses `~/tanowrt` for example):
```shell
mkdir -p ~/tanowrt
```

Go to the created working directory and execute repo tool:
```shell
cd ~/tanowrt
repo init -u https://github.com/tano-systems/meta-tanowrt \
          -m meta-tanowrt/manifests/tanowrt.xml \
          -b dunfell
```

Synchronize all repositories by executing a command:
```shell
repo sync
```

## 5 Initialize Build Environment

Go to the working directory (`~/tanowrt`):
```shell
cd ~/tanowrt
```

You should see the following working directory tree:
```
.
├── bitbake
├── contrib
├── LICENSE
├── LICENSE.GPL-2.0-only
├── LICENSE.MIT
├── MEMORIAM
├── meta
├── meta-openembedded
├── meta-selftest
├── meta-skeleton
├── meta-tanowrt
├── oe-init-build-env
├── README.OE-Core
├── README.qemu
└── scripts
```

The first time you need to add layers and create `local.conf` from the template. To do this, run the command:
```shell
TEMPLATECONF=meta-tanowrt/meta-tanowrt/templates . ./oe-init-build-env
```

If you want to build image with Qt5 support (e.g. `tanowrt-image-qt5`) you need to use `meta-tanowrt/meta-tanowrt/templates/qt5` path in `TEMPLATECONF`:
```shell
TEMPLATECONF=meta-tanowrt/meta-tanowrt/templates/qt5 . ./oe-init-build-env
```

This command automatically creates a `build` subdirectory with the required configuration (`local.conf` and `bblayers.conf`) based on the specified template.

If the `build` subdirectory with configuration has already been created, this command can be used to initialize build environment (without specifying a template directory):
```shell
. ./oe-init-build-env
```

After executing `oe-init-build-env` script, the current directory will be automatically changed to `build` subdirectory of the working directory. Any build commands must be always run from the `build` subdirectory.

Depending on your processor, set these two options in the `~/tanowrt/build/conf/local.conf` file which control how much parallelism BitBake should use:
```
BB_NUMBER_THREADS = "8"
PARALLEL_MAKE = "-j 8"
```

## 6 Building

Below we build for `qemux86-64` machine as an example:
```shell
MACHINE=qemux86-64 bitbake tanowrt-image-full
```

Other available machines are listed in the section "[Supported QEMU Machines](#1-Supported-QEMU-Machines)".

## 7 Running

### 7.1 QEMU

Run command:
```shell
runqemu qemux86-64
```

### 7.2 Oracle VirtualBox

You can run builded images for x86 machines (32 or 64-bit) in Oracle VirtualBox following this [instruction](docs/virtualbox.md).

## 8 Dependencies

The current exact revisions of all listed dependencies are given in [manifests/tanowrt.xml](manifests/tanowrt.xml).

### 8.1 Mandatory

* openembedded-core  
  URI: <git://git.openembedded.org/openembedded-core.git>  
  Subdirectory: meta  
  Branch: dunfell  
* meta-openembedded  
  URI: <git://git.openembedded.org/meta-openembedded.git>  
  Subdirectory: meta-oe  
  Branch: dunfell  
* meta-python  
  URI: <git://git.openembedded.org/meta-openembedded.git>  
  Subdirectory: meta-python  
  Branch: dunfell  
* meta-networking  
  URI: <git://git.openembedded.org/meta-openembedded.git>  
  Subdirectory: meta-networking  
  Branch: dunfell  
* meta-filesystems  
  URI: <git://git.openembedded.org/meta-openembedded.git>  
  Subdirectory: meta-filesystems  
  Branch: dunfell  
* meta-swupdate  
  URI: <git://github.com/sbabic/meta-swupdate.git>  
  Branch: dunfell  
* bitbake  
  URI: <git://git.openembedded.org/bitbake>  
  Branch: 1.46  

### 8.2 Optional (depending on the image and/or machine)

* meta-qt5  
  URI: <git://github.com/meta-qt5/meta-qt5.git>  
  Branch: dunfell  

## 9 License

All metadata is MIT licensed unless otherwise stated. Source code included in tree for individual recipes is under the LICENSE stated in each recipe (.bb file) unless otherwise stated.

## 10 Maintainers

Anton Kikin <a.kikin@tano-systems.com>

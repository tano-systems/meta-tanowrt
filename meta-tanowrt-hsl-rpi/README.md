# meta-tanowrt-hsl-rpi

[TanoWrt](https://github.com/tano-systems/meta-tanowrt) hardware support layer for the Raspberry Pi boards.

## Supported Hardware

| `MACHINE` | Board(s)                   |
| --------- | -------------------------- |
| `rpi3`    | Raspberry Pi 3 Model B/B+  |

## Supported Images

| Recipe               | Image                          |
| -------------------- | ------------------------------ |
| `tanowrt-image-full` | Full TanoWrt image             |

## Prerequisites

Follow the instructions outlined in "[Prerequisites](../README.md#Prerequisites)" section of the root README.md.

## Initialize Repositories

Create a working directory (this document uses `~/tanowrt` for example):
```shell
mkdir -p ~/tanowrt
```

Go to the created working directory and execute repo tool:
```shell
cd ~/tanowrt
repo init -u https://github.com/tano-systems/meta-tanowrt \
          -m meta-tanowrt-hsl-rpi/manifests/tanowrt.xml \
          -b master
```

## Initialize Build Environment

Go to the working directory (`~/tanowrt`):
```shell
cd ~/tanowrt
```

You should see the following working directory tree:
```
.
├── bitbake
├── build
├── LICENSE
├── LICENSE.GPL-2.0-only
├── LICENSE.MIT
├── meta
├── meta-openembedded
├── meta-raspberrypi
├── meta-selftest
├── meta-skeleton
├── meta-tanowrt
├── oe-init-build-env
├── README.LSB
├── README.OE-Core
├── README.qemu
└── scripts
```

The first time you need to add layers and create `local.conf` from the template. To do this, run the command:
```shell
TEMPLATECONF=meta-tanowrt/meta-tanowrt-hsl-rpi/templates . ./oe-init-build-env
```

This command automatically creates a `build` subdirectory with the required configuration (`local.conf` and `bblayers.conf`) based on the specified template.

If the `build` subdirectory with configuration has already been created, this command can be used to initialize build environment (without specifying a template directory):
```shell
. ./oe-init-build-env
```

After executing `oe-init-build-env` script, the current directory will be automatically changed to `build` subdirectory of the working directory. Any build commands must be always run from the `build` subdirectory.

## Building

Build SD card image for Raspberry Pi 3 Model B or B+ board as an example:

```shell
MACHINE=rpi3 bitbake tanowrt-image-full
```

When the build is complete, the SD card image file will be located in folder
```
./tanowrt-glibc/deploy/images/rpi3/tanowrt-image-full-rpi3.sdcard.img
```

All images supported by this layer are listed in the "[Supported Images](#Supported-Images)" section.

Other available machines are listed in the "[Supported Hardware](#Supported-Hardware)" section.

## Running on Hardware

### Write Bootable SD Card

Go to the deploy directory:

```shell
cd tanowrt-glibc/deploy/images/rpi3
```

Use the `dd` utility to write the generated `.sdcard.img` image to the SD card (256 MiB or larger). For example:

```shell
dd if=tanowrt-image-full-rpi3.sdcard.img of=dev/mmcblk0 bs=512
```

### Booting

Insert the prepared SD card into the slot on the Raspberry Pi board and turn it on. For login use credentials specified in "[Access](#Access)" section.

## Access

The following credentials are used to access the operating system (terminal) and the LuCI web-configuration interface:
* User name: `root`
* Password: `root`

## Dependencies

This layer depends on the [meta-tanowrt](../meta-tanowrt/README.md) layer (TanoWrt Linux distribution core layer) with all its dependencies.

Additional dependencies are listed here:

* meta-raspberrypi  
  URI: <git://git.yoctoproject.org/meta-raspberrypi>  
  Branch: warrior

The current exact revisions of all listed dependencies are given in [manifests/deps.xml](manifests/deps.xml).

## License

All metadata is MIT licensed unless otherwise stated. Source code included in tree for individual recipes is under the LICENSE stated in each recipe (.bb file) unless otherwise stated.

## Maintainers

Anton Kikin <a.kikin@tano-systems.com>

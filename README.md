# meta-tano-openwrt

This layer provides OpenEmbedded metadata for [OpenWrt](http://www.openwrt.org/)
based distribution by Tano Systems.

This layer is based on a OE metadata layer for OpenWrt by
[Khem Raj](https://github.com/kraj/meta-openwrt) (revision
[3f94c4f5aa965aa5d65419d6691b40a3870e84a8](https://github.com/kraj/meta-openwrt/commit/3f94c4f5aa965aa5d65419d6691b40a3870e84a8))

All OpenWrt packages corresponds to OpenWrt 18.06.0 or higher.


## Getting Started

Clone repositories:
```
$ git clone -b morty git://github.com/openembedded/openembedded-core.git

$ cd openembedded-core

$ git clone -b 1.32 git://github.com/openembedded/bitbake.git
$ git clone -b morty git://github.com/openembedded/meta-openembedded.git
$ git clone -b master git://github.com/tano-systems/meta-tano-openwrt.git
```

Initialize build environment and add layers:
```
$ TEMPLATECONF=meta-tano-openwrt/conf . ./oe-init-build-env
```

## Building

Below we build for qemuarm machine as an example:
```
MACHINE=qemuarm bitbake openwrt-image-full
```


## Running

```
runqemu qemuarm
```

## Limitations

Works with OE Release 2.2 (Morty)

Images are buildable/bootable for arm, x86 and x86_64 based qemu machines MACHINE variable.

## Dependencies

This layer depends on:

### openembedded-core
URI: git://git.openembedded.org/openembedded-core.git  
Subdirectory: meta  
Branch: morty  
Revision: 4b32784b8c95047dafbc7048bfe03e9e5ceb3367

### meta-openembedded
URI: git://git.openembedded.org/meta-openembedded.git  
Subdirectory: meta-oe  
Branch: morty  
Revision: b40116cf457b88a2db14b86fda9627fb34d56ae6

### meta-python
URI: git://git.openembedded.org/meta-openembedded.git  
Subdirectory: meta-python  
Branch: morty  
Revision: b40116cf457b88a2db14b86fda9627fb34d56ae6

### meta-networking
URI: git://git.openembedded.org/meta-openembedded.git  
Subdirectory: meta-networking  
Branch: morty  
Revision: b40116cf457b88a2db14b86fda9627fb34d56ae6

### bitbake
Version 1.32


## License

All metadata is MIT licensed unless otherwise stated. Source code included
in tree for individual recipes is under the LICENSE stated in each recipe
(.bb file) unless otherwise stated.

The descriptions in the recipes of OpenWrt packages have been extracted from
original OpenWrt repositories:
- https://github.com/openwrt/openwrt.git --- buildsystem for the OpenWrt Linux distribution;
- https://github.com/openwrt/packages.git --- OpenWrt packages feed.


## Maintainers

Anton Kikin <a.kikin@tano-systems.com>

# meta-tanowrt-hsl-intel

[TanoWrt](https://github.com/tano-systems/meta-tanowrt) hardware support layer for the machines with Intel processors.

## 1 Supported Machines

This layer supports the following machines:

| `MACHINE`               | Description                                                                                                                                                                                                                                        |
| ----------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `intel-x86-64-corei7`   | Machine configuration for 64 bit Intel Core i7 CPU (and later) with MMX, SSE, SSE2, SSE3, and SSSE3 instruction set support. Supports a moderately wide range of drivers that should boot and be usable on "typical" hardware.                     |
| `intel-x86-64-skylake`  | Machine configuration for 64 bit Intel Skylake CPU (and later) with MMX, SSE, SSE2, SSE3, SSE4.1, SSE4.2, AVX, and AVX2 instruction set support. Supports a moderately wide range of drivers that should boot and be usable on "typical" hardware. |

## 2 Supported Images

| Image Recipe                     | Machine(s)  | Description                                                |
| -------------------------------- | ----------- | ---------------------------------------------------------- |
| `tanowrt-image-base`             | *All*       | TanoWrt base root file system image                        |
| `tanowrt-image-full`             | *All*       | TanoWrt full featured root file system image               |
| `tanowrt-image-full-swu`         | *All*       | TanoWrt full featured SWU firmware upgrade image           |
| `tanowrt-image-full-swu-factory` | *All*       | Factory installation ISO image                             |

## 3 Prerequisites

Follow the instructions outlined in "[Prerequisites](../README.md#1-Prerequisites)" section of the root README.md.

## 4 Building

Follow the instructions outlined in the "[Building TanoWrt with kas](../docs/build-kas.rst)" document.

## 5 Access

The following credentials are used to access the operating system (terminal) and the LuCI web-configuration interface:
* User name: `root`
* Password: `root`

## 6 Dependencies

This layer depends on the [meta-tanowrt](../meta-tanowrt/README.md) layer (TanoWrt Linux distribution core layer) with all its dependencies.

No additional dependencies.

## 7 License

All metadata is MIT licensed unless otherwise stated. Source code included in tree for individual recipes is under the LICENSE stated in each recipe (.bb file) unless otherwise stated.

## 8 Maintainers

Anton Kikin <a.kikin@tano-systems.com>

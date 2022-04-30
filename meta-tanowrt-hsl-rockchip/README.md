# meta-tanowrt-hsl-rockchip

[TanoWrt](https://github.com/tano-systems/meta-tanowrt) hardware support layer for the Rockchip SoC based boards.

<img src="./docs/images/rockchip-logo.png" height="100" />

## 1 Supported Hardware

TanoWrt has demonstration support for some devices and development boards based on Rockchip SoC's. All supported devices are listed in the table below.

| Device               | SoC, Cores x Frequency | RAM          | Supported Storage(s)   |
| ---------------------| ---------------------- | -------------| ---------------------- |
| Boardcon EM3566 SBC  | RK3566, 4 x 1.8 GHz    | 2 GiB LPDDR4 | MicroSD, 8 GB eMMC     |
| Boardcon EM3568 SBC  | RK3568, 4 x 2.0 GHz    | 2 GiB LPDDR4 | MicroSD, 8 GB eMMC     |
| Radxa ROCK Pi S      | RK3308, 4 x 1.3 GHz    | 512 MiB DDR3 | MicroSD, SD NAND       |

Follow the links for detailed information about each supported device. The links contain detailed information about the device, images build procedures, flashing procedures and start-up procedures for the device.

## 2 Dependencies

This layer depends on the [meta-tanowrt](../meta-tanowrt/README.md) layer (TanoWrt Linux distribution core layer) with all its dependencies.

Additional dependencies are listed here:

* meta-rockchip  
  URI: <https://github.com/JeffyCN/meta-rockchip.git>  
  Branch: hardknott

The current exact revisions of all listed dependencies are given in [manifests/deps.xml](manifests/deps.xml).

## 3 License

All metadata is MIT licensed unless otherwise stated. Source code included in tree for individual recipes is under the LICENSE stated in each recipe (.bb file) unless otherwise stated.

## 4 Maintainers

Anton Kikin <a.kikin@tano-systems.com>

# meta-tanowrt

## Layer configuration options

### Global

#### `IMAGE_FEATURES`

Available image features:
- `package-management` - package manager opkg
- `cgroup` - cgroups related packages
- `wifi` - wifi related packages

#### `DISTRO_FEATURES` and `MACHINE_FEATURES`

Features provided by this layer:
- `procd` - use procd init manager

Extra features provided by this layer:
- `cgroup` - cgroup support
- `wifi` - wifi support
- `watchdog` - watchdog support

To enable this features for machine you need to add it to the `MACHINE_FEATURES`.

#### `OPENWRT_ZONENAME` and `OPENWRT_TIMEZONE`

Initial startup timezone setting.

Example:
```
OPENWRT_ZONENAME ?= "Europe/Moscow"
OPENWRT_TIMEZONE ?= "MSK-3"
```

#### `OPENWRT_HWCLOCK_DEV` and `OPENWRT_HWCLOCK_LOCALTIME`

Configuration for the hardware clock.

Option `OPENWRT_HWCLOCK_DEV` used for hardware clock
device name (default `/dev/rtc0`).

Possible value for the hardware clock kept option `OPENWRT_HWCLOCK_LOCALTIME`:
- 0: hardware clock kept in UTC
- 1: hardware clock kept in localtime

Example:
```
OPENWRT_HWCLOCK_DEV       = "/dev/rtc0"
OPENWRT_HWCLOCK_LOCALTIME = "1"
```

#### `OPENWRT_HOSTNAME`

System hostname.

Example:
```
OPENWRT_HOSTNAME ?= "openwrt"
```

#### `OPENWRT_VERSION_DIST`

Release distribution.

This is the name of the release distribution.

Example:
```
OPENWRT_VERSION_DIST ?= "OpenWrt"
```

#### `OPENWRT_VERSION_NUMBER`

Release version number.

This is the release version number embedded in the image.

Example:
```
OPENWRT_VERSION_NUMBER ?= "18.06-SNAPSHOT"
```

#### `OPENWRT_VERSION_CODE`

Release version code.

This is the release version code embedded in the image.

Example:
```
OPENWRT_VERSION_CODE ?= "${OPENWRT_SRCREV}"
```

#### `OPENWRT_VERSION_REPO`

Release repository.

This is the repository address embedded in the image.

Example:
```
OPENWRT_VERSION_REPO ?= "http://downloads.openwrt.org/releases/18.06-SNAPSHOT"
```

#### `OPENWRT_VERSION_MANUFACTURER`

Manufacturer name.

This is the manufacturer name embedded in `/etc/device_info`.
Useful for OEMs building OpenWrt based firmware.

Example:
```
OPENWRT_VERSION_MANUFACTURER ?= "OpenWrt"
```

#### `OPENWRT_VERSION_MANUFACTURER_URL`

Manufacturer URL.

This is an URL to the manufacturer's website embedded in `/etc/device_info`.
Useful for OEM's building OpenWrt based firmware.

Example:
```
OPENWRT_VERSION_MANUFACTURER_URL ?= "http://openwrt.org/"
```

#### `OPENWRT_VERSION_BUG_URL`

Bug reporting URL.

This is an URL to provide users for providing bug reports

Example:
```
OPENWRT_VERSION_BUG_URL ?= "http://bugs.openwrt.org/"
```

#### `OPENWRT_VERSION_SUPPORT_URL`

Support URL.

This an URL to provide users seeking support.

Example:
```
OPENWRT_VERSION_SUPPORT_URL ?= "http://forum.openwrt.org/"
```

#### `OPENWRT_VERSION_PRODUCT`

Product name.

This is the product name embedded in `/etc/device_info`
Useful for OEM's building OpenWrt based firmware.

Example:
```
OPENWRT_VERSION_PRODUCT ?= "Generic"
```

#### `OPENWRT_VERSION_HWREV`

Hardware revision.

This is the hardware revision string embedded in `/etc/device_info`.
Useful for OEM's building OpenWrt based firmware.

Example:
```
OPENWRT_VERSION_HWREV ?= "v0"
```


### LuCI

#### `LUCI_LANGUAGES`

List of the languages available in LuCI

For example:
```
LUCI_LANGUAGES ?= "ru en de"
```

#### `LUCI_INITIAL_LANG`

Initial startup LuCI language. This language must be present in `LUCI_LANGUAGES`.

For example:
```
LUCI_INITIAL_LANG ?= "ru"
```

#### `LUCI_INITIAL_MEDIAURLBASE`

Initial startup LuCI theme.

For example:
```
LUCI_INITIAL_MEDIAURLBASE ?= "/luci-static/yourtheme"
```

By default this variable is set to `/luci-static/bootstrap`.

#### `LUCI_DISTNAME`, `LUCI_DISTVERSION`, `LUCI_NAME` and `LUCI_VERSION`

Setup LuCI version displayed in LuCI web UI.

For example:
```
LUCI_DISTNAME    ?= "${DISTRO_NAME}"
LUCI_DISTVERSION ?= "${DISTRO_VERSION}"
LUCI_NAME        ?= "LuCI"
LUCI_VERSION     ?= "Tano"
```

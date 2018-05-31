# meta-tano-openwrt

## Layer configuration options

### `LUCI_LANGUAGES`

	List of the languages available in LuCI

	For example:
	```
	LUCI_LANGUAGES="ru en de"
	```

### `LUCI_INITIAL_LANG`

	Initial startup LuCI language. This language must be present in `LUCI_LANGUAGES`.

	For example:
	```
	LUCI_LANGUAGES="ru"
	```

### `DISTRO_FEATURES`

	Available extra features provided by this layer:
	- `procd` - use procd init manager

# kmod-* recipes

kmod-* recipes is a wrappers for kernel modules with kernel configuration checks.

In addition, these recipes allows to customize the autoloading of the kernel modules in case of that they builded as modules.

kmod-* recipes consists of the following directives:

    KERNEL_CONFIG_DEPENDS += "{\
        option              = CONFIG_OPTION_NAME, \
        required            = y|m, \
        m_rdepends          = kernel-module-mod1, \
        m_rdepends          = kernel-module-mod2, \
        m_autoload          = mod1, \
        m_autoload          = mod2 mod2_param=value, \
        m_autoload_early    = false, \
        m_autoload_priority = 30, \
        m_autoload_script   = modules \
    }"

Each directive refers to a single kernel configiration option, which is specified in the `option` parameter.

Parameter `required` determines the possible values of the kernel configuration option, separated by `|` symbol:
  * `y` — builtin;
  * `m` — module;
  * `n` — not set;
  * any other specific value.

All other parameters are optional and applied only if kernel configuration option has been set to `m`.

Runtime dependencies (`RDEPENDS` variable) may be specified in `m_rdepends` parameter. Parameter `m_rdepends` can be specified several times (one record for each dependency).

Module name with module parameters for automatic loading must be specified in the parameter `m_autoload`. Parameter `m_autoload` can also be specified several times.

By default, a modprobe file is created in the `/etc/modules.d` folder with the contents consisting of lines from `m_autoload` parameters. If `m_autoload_early` set to `true` (default value is `false`) then modprobe file is symlinked to `/etc/modules-boot.d` folder.

Parameter `m_autoload_script` sets the name for the modprobe file in the `/etc/modules.d` or/and `/etc/modules-boot.d` folders.

If the parameter `m_autoload_priority` value is set, then the modprobe file name will be preceded by a priority value using the format `%02d-name`. The priority value must be between 0 and 99.

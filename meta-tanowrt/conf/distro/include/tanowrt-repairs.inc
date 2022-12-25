#
# SPDX-License-Identifier: MIT
# Copyright (c) 2018-2020, 2022 Tano Systems LLC. All rights reserved.
#

#
# Repairs for "No recipes available for" warnings
#

# Hide bootchart2 recipe from oe layer
BBMASK += "bootchart2_0.14.9.bb"

# Hide hostpad and wpa-supplicant
BBMASK += "recipes-connectivity/hostapd/"
BBMASK += "recipes-connectivity/wpa-supplicant/"

#
# Repairs for "No recipes available for" warnings
#

# Hide bootchart2 recipe from oe layer
BBMASK += "bootchart2_0.14.8.bb"

# Hide hostpad and wpa-supplicant
BBMASK += "recipes-connectivity/hostapd/"
BBMASK += "recipes-connectivity/wpa-supplicant/"

#!/bin/sh

set -e

TARGETS=""
TARGETS_EXTRAS=""

EXTRA_YMLS="${EXTRA_YMLS:-}"

for target in kas/targets/*; do
	if [ -f "${target}" ]; then
		TARGETS="${TARGETS} ${target}"
	fi
done

for target in kas/targets/extras/*; do
	if [ -f "${target}" ]; then
		TARGETS_EXTRAS="${TARGETS_EXTRAS} ${target}"
	fi
done

TARGETS_ALL="${TARGETS} ${TARGETS_EXTRAS}"

# Checkout/test
for target in ${TARGETS_ALL}; do
	echo "=== Checkout ${target}..."
	kas checkout ${target}${EXTRA_YMLS}
done

# Build
for target in ${TARGETS_ALL}; do
	echo "=== Build ${target}..."
	kas build ${target}${EXTRA_YMLS}
done

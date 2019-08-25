
GIT_OPENWRT_ORG = "git.openwrt.org"

#
# Official and non-official mirrors on GitHub for some OpenWrt repositories (git.openwrt.org)
# * [official]     git://github.com/openwrt/
# * [non-official] git://github.com/git-openwrt-org-mirror/
#
MIRRORS_prepend = "\
	git://${GIT_OPENWRT_ORG}/openwrt/openwrt.git  git://github.com/openwrt/openwrt.git \n\
	git://${GIT_OPENWRT_ORG}/project/luci.git     git://github.com/openwrt/luci.git \n\
	git://${GIT_OPENWRT_ORG}/(.*)/(.*)            git://github.com/git-openwrt-org-mirror/BASENAME \n\
	gitsm://${GIT_OPENWRT_ORG}/(.*)/(.*)          gitsm://github.com/git-openwrt-org-mirror/BASENAME \n\
	https://${GIT_OPENWRT_ORG}/(.*)/(.*)          https://github.com/git-openwrt-org-mirror/BASENAME \n\
"

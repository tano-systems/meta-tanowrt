PR_append = ".tano0"
do_configure[depends] += "virtual/kernel:do_shared_workdir"
RDEPENDS_${PN} += "kmod-sched-core"

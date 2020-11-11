# Extra procd parameters

This document describes extra procd parameters that are not in the original (upstream) procd.

## Working directory

Parameter `chdir` allows to change current working directory for service before starting it:

Example:
```shell
...
procd_set_param chdir "/path/to/workdir"
...
```

## Scheduler attributes

With parameter `policy` you can select scheduling policy for procd service. Suppported policies are:
  - `SCHED_OTHER` - the standard round-robin time-sharing policy;
  - `SCHED_BATCH` - for "batch" style execution of processes;
  - `SCHED_FIFO` - a first-in, first-out "real-time" policy;
  - `SCHED_RR` - a round-robin "real-time" policy;
  - `SCHED_DEADLINE` - a deadline scheduling policy;
  - `SCHED_IDLE` - idle scheduling policy.

For policies `SCHED_OTHER` and `SCHED_BATCH` you can setup additional `nice` parameter. This parameter specifies the nice value to be set. The nice value is a number in the range -20 (high priority) to +19 (low priority).

For policies `SCHED_FIFO` and `SCHED_RR` you can setup also `priority` parameter. This parameter specifies the static priority to be set when specifying scheduling policy as `SCHED_FIFO` or `SCHED_RR`. The allowed range of priorities usually is in the range 1 (low priority) to 99 (high priority). If parameter `priority` is not specified, value `1` will be used for the priority.

For policy `SCHED_DEADLINE` you must set these additional parameters:
  - `runtime` - this parameter specifies the "Runtime" parameter for deadline scheduling. The value is expressed in nanoseconds (see [sched(7)] for details);
  - `deadline` - this parameter specifies the "Deadline" parameter for deadline scheduling. The value is expressed in nanoseconds (see [sched(7)] for details);
  - `period` - this parameter specifies the "Period" parameter for deadline scheduling. The value is expressed in nanoseconds (see [sched(7)] for details).

Note that Linux kernel requires that `runtime` ≤ `deadline` ≤ `period` and the value of any of these parameters must be at least 1024 (i.e., just over one microsecond).

By default, if no other policy is specified, will be used `SCHED_OTHER` policy with `nice` value 0.

[sched(7)]: http://man7.org/linux/man-pages/man7/sched.7.html

Example of the configuring `SCHED_RR` policy for watchdog service:
```shell
...
procd_open_instance
procd_set_param command "/sbin/watchdog"
procd_append_param command "-T30"
procd_append_param command "-t5"
procd_append_param command "-F"
procd_append_param command "/dev/watchdog"
procd_set_param respawn

procd_set_param policy 'SCHED_RR'
procd_set_param priority '60'

procd_close_instance
...
```

Example of the configuring `SCHED_DEADLINE` policy:
```shell
...
# Creates a 10ms/30ms reservation
procd_set_param policy 'SCHED_DEADLINE'
procd_set_param runtime '10000000'
procd_set_param deadline '30000000'
procd_set_param period '30000000'
...
```

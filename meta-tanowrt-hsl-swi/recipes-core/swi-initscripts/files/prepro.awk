#!/usr/bin/gawk -f

# The idea in this GNU Awk script is to run GNU C preprocessor on the input
# file, in such a way that only the #if, #ifdef, #ifndef, #elif, #else
# and #endif preprocessing lines are seen by the preprocessor. All other lines
# are concealed from the preprocessor, and are re-constituted from the original
# input after preprocessing is done.
#
# The benefit of this is that the preprocessor cannot possibly "choke" on any
# of the material which it didn't see. It also means we cannot use macros.
#
# To achieve this goal, we load the input file, into an associative
# array which maps the original line numbers to original lines.
# At the same time, we produce a filtered representation of the file
# in which all lines that are not #if, #ifdef or #ifndef preprocessing
# directives are replaced by their line number.
#
# Example:
#
#   #if FOO                         #if FOO
#   hello /*                        2
#   #else                --->       #else
#   good // bye                     4
#   #endif                          #fi
#   world                           6
#
# Suppose that FOO is defined and has a nonzero value. Then if the
# right hand side above is preprocessed, it results in:
#
#   2
#   6
#
# Possibly, the output also has junk lines from the preprocessor, such
# as #line directives and whatnot.
#
# Now all we have to do is filter this output, replacing each
# line that contains only a decimal integer with the original line which
# that integer denotes, and delete all other lines.
#
# Thereby, we obtain:
#
#   hello /*
#   world
#
# The preprocessor never saw the /* characters which it would have
# interpreted as a broken comment; they were faithfully reconstructed.
#
# The preprocessor needs some -D options to define symbols. These
# are passed to the awk script via the -v var=value mechanism
# targeting the CPPFLAGS variable:
#
#   prepro.awk -v CPPFLAGS='-DFOO -DBAR=3'
#
# The preprocessor will be invoked with -DFOO -DBAR=3.
#

BEGIN {
 defs = ""                 # -D definitions to pass to preprocessor
 pid = PROCINFO["pid"]
 tmpfile = "temp." pid     # name of temp file passed to preprocessor
}

#
# Store each line into the orig[] array, keyed to its line number.
#
{
  orig[NR] = $0
}

#
# Also store each line to the filt[] array, keyed to its line number;
# if it is a preprocessor line we are interested in.
#
/^@(if |ifdef |ifndef |elif|else|endif)/ {
  sub("@", "#")
  filt[NR] = $0
  next
}

#
# For non-preprocessor lines store the line number into filt[],
# keyed to itself
#
{
  filt[NR] = NR
}

#
# Now, do the preprocessing and substitution to produce the output.
#
END {
  process = "cpp " CPPFLAGS " > " tmpfile  # cpp pipe command line

  #
  # Dump the filt[] array into cpp, whose output is redirected to tmpfile.
  #
  for (i = 1; i <= NR; i++)
    print filt[i] | process

  #
  # Important: close the pipe. This will wait for the process to finish,
  # otherwise our next step will race against cpp and see a truncated,
  # even entirely empty, input file.
  #
  close(process)

  #
  # Read the tmpfile. For any line which is a decimal integer,
  # dump the orig[] line corresponding to that integer
  #
  while (getline < tmpfile > 0)
    if ($0 ~ /^[0-9]+$/)
      print orig[$0]

  system("rm " tmpfile)
}

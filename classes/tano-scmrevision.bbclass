#
# TanoWrt Git revision
#
# Copyright (c) 2018, Tano Systems. All Rights Reserved.
# Anton Kikin <a.kikin@tano-systems.com>
#
# gitrev_run and mark_recipe_dependencies taken from
# gitver.bbclass meta-openembedded/meta-oe
# (git://git.openembedded.org/meta-openembedded)
#

def gitrev_run(cmd, path):
    (output, error) = bb.process.run(cmd, cwd=path)
    return output.rstrip()

def get_git_revision(p):
    import subprocess

    try:
        return subprocess.Popen("git rev-parse HEAD 2>/dev/null ", cwd=p, shell=True, stdout=subprocess.PIPE, universal_newlines=True).communicate()[0].rstrip()
    except OSError:
        return None

def get_git_branch(p):
    import subprocess

    try:
        ref = gitrev_run("git symbolic-ref -q HEAD", p)
        return os.path.basename(ref)
    except OSError:
        return None

def mark_recipe_dependencies(path, d):
    from bb.parse import mark_dependency

    gitdir = os.path.join(path, ".git")

    # Force the recipe to be reparsed so the version gets bumped
    # if the active branch is switched, or if the branch changes.
    mark_dependency(d, os.path.join(gitdir, "HEAD"))

    # Force a reparse if anything in the index changes.
    mark_dependency(d, os.path.join(gitdir, "index"))

    try:
        ref = gitrev_run("git symbolic-ref -q HEAD", gitdir)
    except bb.process.CmdError:
        pass
    else:
        if ref:
            mark_dependency(d, os.path.join(gitdir, ref))

    # Catch new tags.
    tagdir = os.path.join(gitdir, "refs", "tags")
    if os.path.exists(tagdir):
        mark_dependency(d, tagdir)


TANO_OPENWRT_SCM_BRANCH = ""
TANO_OPENWRT_SCM_REVISION = ""

python () {
    tano_base = d.getVar('TANO_OPENWRT_BASE')
    mark_recipe_dependencies(tano_base, d)
    d.setVar('TANO_OPENWRT_SCM_REVISION', get_git_revision(tano_base))
    d.setVar('TANO_OPENWRT_SCM_BRANCH', get_git_branch(tano_base))
}
